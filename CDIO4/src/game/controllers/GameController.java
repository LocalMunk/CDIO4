package game.controllers;

import java.util.Random;

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
//	private ChanceCardCollection chanceCardCollection;
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
//		chanceCardCollection = new ChanceCardCollection();
		
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
			
			this.players[i] = new Player(name, 30000);
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
			
			/*
			 * Code to determine if the player can buy any hotels/houses
			 * Also allows the player to buy buildings
			 */
			Field[] ownedFieldBuildable = fieldCollection.getOwnedTerritoryBuildable(player);
			if(ownedFieldBuildable != null && ownedFieldBuildable.length != 0 && player.getJailed()==false) 
			{
				while(ownedFieldBuildable != null && ownedFieldBuildable.length != 0 && GUI.getUserLeftButtonPressed("Do you wish to buy any houses/hotels?", "Yes", "No"))
				{
					String[] fieldNames = fieldCollection.getFieldNames(ownedFieldBuildable);
					
					//Get the field that the player selects
					Territory chosenField = (Territory) fieldCollection.getFieldByName(GUI.getUserSelection("Choose a property", fieldNames));
					
					String choice = GUI.getUserSelection("What do you wish to build on this property?", chosenField.getPossibleBuildings());
					String buildingType;
					
					if(choice.startsWith("Hotel: "))
						buildingType = "Hotel";
					else
						buildingType = "House";
					
					int numberOfBuildings = Integer.parseInt(choice.replaceAll("[\\D]", ""));
					
					chosenField.buyBuildings(buildingType, numberOfBuildings, player);
					/*
					 * Have to get owned territory again to check whether or they still have buildable territories
					 */
					ownedFieldBuildable = fieldCollection.getOwnedTerritoryBuildable(player);
				}
			}
			
			
			/*
			 * Ask the player if he wants to sell any of his buildings
			 */
			Field[] ownedFields = fieldCollection.getOwnedFields(player);
			if(ownedFields != null && ownedFields.length != 0)
			{
				while(ownedFields != null && ownedFields.length != 0 && GUI.getUserLeftButtonPressed("Do you want to sell any territories?", "Yes", "No"))
				{
					String[] fieldNames = fieldCollection.getFieldNames(ownedFields);
					
					Player buyingPlayer = getPlayerByName(GUI.getUserSelection("Choose a player to sell to", getPlayerNamesExcept(player)));
					
					Territory chosenField = (Territory) fieldCollection.getFieldByName(GUI.getUserSelection("Choose a property", fieldNames));
					
					int price = GUI.getUserInteger("How much does " + chosenField.getName() + " cost?", 0, buyingPlayer.getAccount().getBalance() - 1);

					
					chosenField.setOwner(buyingPlayer);
					buyingPlayer.getAccount().withdraw(price);
					GUI.setBalance(buyingPlayer.getName(), buyingPlayer.getAccount().getBalance());
					player.getAccount().deposit(price);
					GUI.setBalance(player.getName(), player.getAccount().getBalance());
					
					/*
					 * Have to get owned territory again to check whether or they still have fields to sell
					 */
					ownedFields = fieldCollection.getOwnedFields(player);
				}
			}
			
			
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
			turn.subCheck();
			GUI.showMessage("You rolled two equal dice, and your grandmother gave you the dice back, you get an extra turn!");
		}

		turn.change();
	}
	
	public String[] getPlayerNames()
	{
		String[] playerNames = new String[players.length];
		
		int i = 0;
		for(Player player : players)
		{
			playerNames[i] = player.getName();
			i++;
		}
		
		return playerNames;
	}
	
	public String[] getPlayerNamesExcept(Player player)
	{
		String exceptPlayerName = player.getName();
		String[] allPlayerNames = getPlayerNames();
		String[] playerNamesExceptPlayer = new String[allPlayerNames.length - 1];
		
		int i = 0;
		for(String playerName : allPlayerNames)
		{
			if(!playerName.equals(exceptPlayerName))
			{
				playerNamesExceptPlayer[i] = playerName;
				i++;
			}
		}
		
		return playerNamesExceptPlayer;
	}
	
	
	public Player getPlayerByName(String name)
	{
		for(Player player : players)
		{
			if(player.getName() == name)
				return player;
		}
		
		return null;
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
 