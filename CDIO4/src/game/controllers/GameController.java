package game.controllers;

import java.util.Random;

import desktop_resources.GUI;
import entities.ChanceCards.ChanceCardCollection;
import entities.fields.StartField;
import entities.fields.abstracts.Field;
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
	private ChanceCardCollection chanceCardCollection;
	private String y;
	private int amountofplayers;
	
	private boolean checkName(String name)
	{
		if(name.length() >= 12 || name.contains(" "))
			return false;
		
		for(Player player : players)
		{
			if(player != null && player.getName().equals(name))
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
		fieldCollection.initialize();
		chanceCardCollection = new ChanceCardCollection();
		
		this.amountofplayers = 0;
		amountofplayers = GUI.getUserInteger("How many players(2-6 players)", 2, 6);
		this.players = new Player[amountofplayers];
		for (int i = 0; i < amountofplayers; i++)
		{
			boolean doContinue = true;
			String name;
			name = GUI.getUserString("What is your name?");
			
			do
			{
				if(checkName(name))
					doContinue = false;
				else
					name = GUI.getUserString("What is your name?");
				
			} while(doContinue);
			
			this.players[i] = new Player(name, 30000, true);
			GUI.addPlayer(this.players[i].getName(), 30000);
			GUI.setCar(1, this.players[i].getName());
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
	 * will terminate the player from the game, by excluding him from the
	 * player array and removing ownership from his owned fields
	 */
	public void game(Player player)
	{
		if (GUI.getUserButtonPressed(player.getName() + "'s turn, Press ENTER to roll the dice", "ENTER")
		        .equals("ENTER"))
		{
			dice.roll();
			for (int i = 0; i < dice.getValue(); i++)
			{
				if (player.getPosition() < 41)
				{
					GUI.removeAllCars(player.getName());
					player.setPosition(1);
					GUI.setCar(player.getPosition(), player.getName());
				}
				else
				{
					GUI.removeAllCars(player.getName());
					player.setPosition(-40);
					GUI.setCar(player.getPosition(), player.getName());
					StartField.getStartMoney(player);
				}
			}

			fieldCollection.getField(player.getPosition() + 1).landOnField(player);
			
			int hej = player.getFieldsOwned();
			
			if(player.getFieldsOwned() != 0)
			{
				Field[] ownedFields = fieldCollection.getOwnedTerritory(player);
				Field[] ownedFieldBuildable = null;
				if(GUI.getUserLeftButtonPressed("Do you wish to buy any houses/hotels?", "Yes", "No") && ownedFields.length != 0)
				{
					String[] fieldNames = fieldCollection.getFieldNames(ownedFields);
					
					//Get the field that the player selects
					Territory chosenField = (Territory) fieldCollection.getFieldByName(GUI.getUserSelection("Choose a property", fieldNames));
					
					String choice = GUI.getUserSelection("What do you wish to build on this property?", chosenField.getPossibleBuildings());
					
					
					
					
					
				}
			}
			
			
			if (player.getAccount().getBalance() == 0)
			{
				GUI.showMessage(player.getName() + " is dead");
				GUI.removeAllCars(player.getName());
			}
			if (player.getAccount().getBalance() <= 0)
			{
				this.amountofplayers--;
				int length = this.players.length;

				this.players[turn.getCheck() - 1] = this.players[length - 1];
				for (Field x : fieldCollection.getFieldList())
				{
					try
					{
						if (x.getOwner().getName().equals(player.getName()))
						{
							x.setOwned(false);
						}
					}
					catch (Exception e)
					{

					}
				}
			}
			if (this.amountofplayers == 1)
			{
				GUI.showMessage("You have won the game");
				GUI.showMessage("Press enter to close the game");
				GUI.close();
				System.exit(0);
			}
			turn.change();

		}
	}
	public String draw (Player player){
		int rnd = new Random().nextInt(chanceCardCollection.getCardList().length);
		if (rnd <= 4){
			player.getAccount().deposit(chanceCardCollection.getCardList()[rnd].getValue());
		}
		else {
			player.getAccount().withdraw(chanceCardCollection.getCardList()[rnd].getValue());
		}
		return chanceCardCollection.getCardList()[rnd].getDescription();
	}
}