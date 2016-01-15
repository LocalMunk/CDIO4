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
import game.controllers.marketplace.MarketPlaceController;

public class GameController
{

	private Player[] players;
	private Dice dice;
	private Turn turn;
	private FieldCollection fieldCollection;
	private MarketPlaceController marketPlace;
	private String y;
	private int amountofplayers;
	private int equaldicecounter;
	
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
		marketPlace = new MarketPlaceController();
//		chanceCardCollection = new ChanceCardCollection();
		
		this.amountofplayers = 0;
		amountofplayers = GUI.getUserInteger("How many players(2-6 players)", 2, 6);
		this.players = new Player[amountofplayers];
		
		Color[] playerColors = new Color[] {
				Color.blue,
				Color.red,
				Color.yellow,
				Color.green,
				Color.white,
				Color.black
		};
		
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
			
			this.players[i] = new Player(name, 30000);
			GUI.addPlayer(
					name,
					30000,
					new Car.Builder().primaryColor(playerColors[i]).build()
			);
			
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
	 * will terminate the player from the game, by excluding him from the
	 * player array and removing ownership from his owned fields
	 */
	public void game(Player player)
	{
		if(player.isAlive()){
		if(player.getJailed()){
			if(player.getTimeJailed() >= 3){
				player.setJailed(false);
				player.setTimeJailed(0);
				GUI.showMessage("You have served your time in jail, and you have been released");
				player.setPosition(10);
				//GUI isn't 0 indexed so we add 1
				GUI.setCar(player.getPosition()+1, player.getName());
				
			}
			else{
				if (GUI.getUserButtonPressed(player.getName() + ", You are in jail, roll two equal dice to get out of jail, or pay 3000", "Roll", "Pay")
			        .equals("Roll")){
					dice.roll();
					
					if(dice.getEquals()){
						player.setJailed(false);
						GUI.showMessage("You rolled two equal dice, and therefore the guards are inclined to release you");
						player.setPosition(10);
						//GUI isn't 0 indexed so we add 1
						GUI.setCar(player.getPosition()+1, player.getName());
						
					}
					else{
						GUI.showMessage("You failed to roll two equal die, and therefore you are still jailed");
						player.setTimeJailed(player.getTimeJailed() + 1);
					}
							}
				else{
					player.getAccount().withdraw(3000);
					GUI.setBalance(player.getName(), player.getAccount().getBalance());
					player.setJailed(false);
					GUI.showMessage("Your grandmother paid the bail, and you gave her the money back, you are a free man");
					player.setPosition(10);
					//GUI isn't 0 indexed so we add 1
					GUI.setCar(player.getPosition()+1, player.getName());
				}
				
			}
		}
		else{
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
					//GUI isn't 0 indexed so we add 1
					GUI.setCar(player.getPosition() + 1, player.getName());
				}
				else
				{
					GUI.removeAllCars(player.getName());
					player.setPosition(0);
					//GUI isn't 0 indexed so we add 1
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
			for (Field x : fieldCollection.getFieldList())
			{
				if (x instanceof Ownable && x.getOwner() == player)
				{
					((Ownable)x).setOwner(null);
				}
			}
			
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
		if(dice.getEquals()){
			equaldicecounter++;
			if(equaldicecounter==3){
				player.setJailed(true);
				GUI.removeAllCars(player.getName());
				player.setPosition(10);
				//GUI isn't 0 indexed so we add 1
				GUI.setCar(player.getPosition()+1, player.getName());
				GUI.showMessage("You rolled two equal dice 3 times in a row. Go to jail.");
				equaldicecounter = 0;
			}
			else{
			turn.subCheck();
			GUI.showMessage("You rolled two equal dice, and your grandmother gave you the dice back, you get an extra turn!");
			}
		}
		else{
			equaldicecounter = 0;
		}
		turn.change();
	}
	
}
//	public String draw (Player player){
//		int rnd = new Random().nextInt(chanceCardCollection.getCardList().length);
//		if (rnd <= 4){
//			player.getAccount().deposit(chanceCardCollection.getCardList()[rnd].getValue());
//		}
//		else {
//			player.getAccount().withdraw(chanceCardCollection.getCardList()[rnd].getValue());
//		}
//		GUI.displayChanceCard(chanceCardCollection.getCardList()[rnd].getDescription());
//		return chanceCardCollection.getCardList()[rnd].getDescription();
//	}
 