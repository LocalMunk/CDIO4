package game.controllers;

import desktop_resources.GUI;
import entities.fields.abstracts.Field;
import entities.fields.abstracts.Ownable;
import entities.fields.collection.FieldCollection;
import entities.fields.ownable.Territory;
import game.Player;

public class MarketPlaceController
{
	
	/*
	 * Code to determine if the player can buy any hotels/houses
	 * Also allows the player to buy buildings
	 */
	public void buyBuildings(Player player, FieldCollection fieldCollection)
	{
		Field[] ownedFieldBuildable = fieldCollection.getOwnedTerritoryBuildable(player);
		if(ownedFieldBuildable != null && ownedFieldBuildable.length != 0 && player.getJailed()==false) 
		{
			while(ownedFieldBuildable != null && ownedFieldBuildable.length != 0 && GUI.getUserLeftButtonPressed("Do you wish to buy any houses/hotels?", "Yes", "No"))
			{
				ownedFieldBuildable = offerPlayerToBuyHouses(player, fieldCollection, ownedFieldBuildable);
			}
		}
	}


	private Field[] offerPlayerToBuyHouses(
		Player player,
		FieldCollection fieldCollection,
		Field[] ownedFieldBuildable)
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
		
		//Fjerner alt der ikke er tal med ingenting (dvs fjerner det helt) så vi kun har antal af huse/hoteller som skal bygges
		int numberOfBuildings = Integer.parseInt(choice.replaceAll("[\\D]", ""));
		
		chosenField.buyBuildings(buildingType, numberOfBuildings, player);
		/*
		 * Have to get owned territory again to check whether or they still have buildable territories
		 */
		ownedFieldBuildable = fieldCollection.getOwnedTerritoryBuildable(player);
		return ownedFieldBuildable;
	}
	
	
	/*
	 * Ask the player if he wants to sell any of his ownable fields
	 */
	public void sellOwnedFields(Player player, Player[] players, FieldCollection fieldCollection)
	{
		Field[] ownedFields = fieldCollection.getOwnedFields(player);
		if(ownedFields != null && ownedFields.length != 0)
		{
			while(ownedFields != null && ownedFields.length != 0 && GUI.getUserLeftButtonPressed("Do you want to sell any territories?", "Yes", "No"))
			{	
				Player buyingPlayer = getPlayerByName(GUI.getUserSelection("Choose a player to sell to", getPlayerNamesExcept(players, player)), players);
				
				String[] fieldNames = fieldCollection.getFieldNames(ownedFields);
				Ownable chosenField = (Ownable) fieldCollection.getFieldByName(GUI.getUserSelection("Choose a property", fieldNames));
				
				int price = GUI.getUserInteger("How much does " + chosenField.getName() + " cost?", 0, buyingPlayer.getAccount().getBalance() - 1);

				
				chosenField.setOwner(buyingPlayer);
				buyingPlayer.getAccount().withdraw(price);
				GUI.setBalance(buyingPlayer.getName(), buyingPlayer.getAccount().getBalance());
				player.getAccount().deposit(price);
				GUI.setBalance(player.getName(), player.getAccount().getBalance());
				GUI.setOwner(chosenField.getFieldID() + 1, buyingPlayer.getName());
				
				/*
				 * Have to get owned territory again to check whether or they still have fields to sell
				 */
				ownedFields = fieldCollection.getOwnedFields(player);
			}
		}
	}
	
	public String[] getPlayerNames(Player[] players)
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
	
	public String[] getPlayerNamesExcept(Player[] players, Player player)
	{
		String exceptPlayerName = player.getName();
		String[] allPlayerNames = getPlayerNames(players);
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
	
	
	public Player getPlayerByName(String name, Player[] players)
	{
		for(Player player : players)
		{
			if(player.getName() == name)
				return player;
		}
		
		return null;
	}
}
