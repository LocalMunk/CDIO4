package game.controllers;

import java.awt.Color;
import java.util.Random;

import desktop_codebehind.Car;
import desktop_resources.GUI;
import entities.ChanceCards.ChanceCardCollection;
import entities.fields.StartField;
import entities.fields.abstracts.Field;
import entities.fields.abstracts.Ownable;
import entities.fields.collection.FieldCollection;
import entities.fields.ownable.Territory;
import game.Dice;
import game.Player;
import game.Turn;

public class GameController
{

	private Player[] players;
	private Dice dice;
	private Turn turn;
	private FieldCollection fieldCollection;
	private MarketPlaceController marketPlace;
	private JailController jail;
	private String y;
	private int amountofplayers;
	private int equaldicecounter;

	private boolean checkName(String name)
	{
		if (name.length() >= 12 || name.contains(" "))
			return false;

		for (Player player : players)
		{
			if (player != null && player.getName().equals(name))
				return false;
		}

		return true;
	}

	/**
	 * Builds the necessary objects for the game to begin
	 */
	public GameController()
	{
		fieldCollection = new FieldCollection();
		marketPlace = new MarketPlaceController();
		jail = new JailController();
		// chanceCardCollection = new ChanceCardCollection();

		this.amountofplayers = 0;
		amountofplayers = GUI.getUserInteger("How many players(2-6 players)", 2, 6);
		this.players = new Player[amountofplayers];

		Color[] playerColors = new Color[] { Color.blue, Color.red, Color.yellow, Color.green, Color.white,
		        Color.black };

		for (int i = 0; i < amountofplayers; i++)
		{
			boolean doContinue = true;
			String name;
			name = GUI.getUserString("What is your name?");

			do
			{
				if (checkName(name))
					doContinue = false;
				else
					name = GUI.getUserString("What is your name?");

			}
			while (doContinue);

			this.players[i] = new Player(name, 30000);
			GUI.addPlayer(name, 30000, new Car.Builder().primaryColor(playerColors[i]).build());

			GUI.setCar(1, name);
		}

		turn = new Turn(this);
		dice = new Dice(6);
	}

	/**
	 * loops through the different players turns
	 */
	public void loop()
	{
		while (true)
		{
			game(players[turn.getCheck() - 1]);
		}
	}

	public int getamountop()
	{
		return this.amountofplayers;
	}

	/**
	 * asks the player to press the button , then rolls the dice and moves the
	 * player calls the method land on field if the player reaches 0 money, it
	 * will terminate the player from the game, by excluding him from the player
	 * array and removing ownership from his owned fields
	 */
	public void game(Player player)
	{
		if (player.isAlive())
		{
			if (player.getJailed())
			{
				jail.handle(player, dice);
			}
			else
			{
				if (GUI.getUserButtonPressed(player.getName() + "'s turn, Press ENTER to roll the dice", "ENTER")
				        .equals("ENTER"))
				{
					dice.roll();
				}
				for (int i = 0; i < dice.getValue(); i++)
				{
					if (player.getPosition() < 39)
					{
						GUI.removeAllCars(player.getName());
						player.setPosition(player.getPosition() + 1);
						// GUI isn't 0 indexed so we add 1
						GUI.setCar(player.getPosition() + 1, player.getName());
					}
					else
					{
						GUI.removeAllCars(player.getName());
						player.setPosition(0);
						// GUI isn't 0 indexed so we add 1
						GUI.setCar(player.getPosition() + 1, player.getName());
						StartField.getStartMoney(player);
					}
				}

				fieldCollection.getField(player.getPosition()).landOnField(player);
			}

			marketPlace.buyBuildings(player, fieldCollection);
			marketPlace.sellOwnedFields(player, players, fieldCollection);

			if (player.getAccount().getBalance() == 0)
			{
				GUI.showMessage(player.getName() + " is dead");
				GUI.removeAllCars(player.getName());

				player.setAlive(false);
				fieldCollection.removePlayerOwnership(player);

				this.amountofplayers--;
			}

			if (this.amountofplayers == 1)
			{
				GUI.showMessage("You have won the game");
				GUI.showMessage("Press enter to close the game");
				GUI.close();
				System.exit(0);
			}
		}
		if (dice.getEquals())
		{
			equaldicecounter++;
			if (equaldicecounter == 3)
			{
				jail.goToJail(player);
				equaldicecounter = 0;
			}
			else
			{
				turn.extraTurnToCurrentPlayer();
				GUI.showMessage(
				        "You rolled two equal dice, and your grandmother gave you the dice back, you get an extra turn!");
			}
		}
		else
		{
			equaldicecounter = 0;
		}
		turn.change();
	}

}
// public String draw (Player player){
// int rnd = new Random().nextInt(chanceCardCollection.getCardList().length);
// if (rnd <= 4){
// player.getAccount().deposit(chanceCardCollection.getCardList()[rnd].getValue());
// }
// else {
// player.getAccount().withdraw(chanceCardCollection.getCardList()[rnd].getValue());
// }
// GUI.displayChanceCard(chanceCardCollection.getCardList()[rnd].getDescription());
// return chanceCardCollection.getCardList()[rnd].getDescription();
// }
