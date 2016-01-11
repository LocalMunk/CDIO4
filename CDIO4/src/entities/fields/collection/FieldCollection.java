package entities.fields.collection;

import java.util.ArrayList;

import entities.ChanceCards.*;
import entities.fields.ChanceField;
import entities.fields.FreeParking;
import entities.fields.GoToJail;
import entities.fields.Jail;
import entities.fields.StartField;
import entities.fields.Tax;
import entities.fields.abstracts.Field;
import entities.fields.ownable.Brewery;
import entities.fields.ownable.Fleet;
import entities.fields.ownable.Territory;
import game.Dice;
import game.Player;
import game.controllers.GameBoard;

public class FieldCollection
{
	private Field[] fields = new Field[40];
//	private desktop_fields.Field[] fields = new desktop_fields.Field[22];

	/**
	 * builds the gameboard, the first part builds the GUI board, and the second
	 * builds the code board.
	 */
	public Field[] initialize()
	{
		Dice diceone = new Dice(6);
		
		int[] fleetRent = new int[] {400, 500, 600, 700};

		//Start fields
		fields[0] = new StartField("Start",0,"Start felt");
		
		//Chance cards
		fields[2] = new ChanceField("Try your luck","IS your luck shit?",2);
		fields[7] = new ChanceField("Try your luck","Is your luck shit?",7);
		fields[15] = new ChanceField("Try your luck","Street", 15);
		fields[24] = new ChanceField("Try your luck","ChanceCard",24);
		fields[33] = new ChanceField("Try your luck", "is your luck shit?", 33);
		fields[36] = new ChanceField("Try your luck", "is your luck shit?", 36);
		
		//Territory
		fields[1] 	= new Territory("Rødovrevej",			"Street", "Blue",1,1200, new int[] {50,250,750,2250,4000,6000} );
		fields[3] 	= new Territory ("Hvidovrevej",			"Street", "Blue",3,1200, new int[] {50,250,750,2250,4000,6000} );
		fields[6] 	= new Territory ("Roskildevej",			"Street", "Salmon",6, 2000 ,new int[]{100,600,1800,5400,8000,11000});
		fields[8] 	= new Territory ("Valby langgade",		"Street", "Salmon",8,2000,new int[]{100,600,1800,5400,8000,11000} );
		fields[9] 	= new Territory ("Alle gadé",			"Street", "Salmon",9,2400 ,new int[]{150,800,2000,6000,9000,12000});
		fields[11]	= new Territory ("Frederisksberg allé",	"Street", "Green",11,2800 ,new int[]{200,1000,3000,9000,12500,15000});
		fields[12]	= new Territory ("Bülwsvej",			"Street", "Green",12,2800 ,new int[]{200,1000,3000,9000,12500,15000});
		fields[13] 	= new Territory ("Gammelkonge vej", 	"Street", "Green",13,3200 ,new int[]{250,1250,3750,1000,14000,18000});
		fields[17] 	= new Territory ("Bernsstoffvej",		"Street", "Grey",17,3600 ,new int[]{300,1400,4000,11000,15000,19000});
		fields[18] 	= new Territory ("Strandvejen",			"Street", "Grey",18,4000 ,new int[]{350,600,4400,12000,16000,19000});
		fields[19] 	= new Territory ("Hellerupvej",			"Street", "Grey",19,3600 ,new int[]{300,1400,4000,11000,15000,1900});
		fields[21] 	= new Territory ("Trianglen",			"Street", "Red",21,4400 ,new int[]{300,1800,5000,14000,17500,21000});
		fields[22] 	= new Territory("Østerbrogade", 		"Street", "Red", 22, 4400, new int[]{350,1800,5000,14000,17500,21000});
		fields[23] 	= new Territory("Grønningen", 			"Street", "Red", 23, 4800, new int[]{300,1800,5000,14000,17500,21000});
		fields[26] 	= new Territory("Bredgade", 			"Street", "White", 26, 5200,new int[]{450,2200,6600,16000,19500,23000});
		fields[27] 	= new Territory("Kgs. Nytorv", 			"Street", "White", 27, 5200, new int[]{450,2200,6600,16000,19500,23000});
		fields[29] 	= new Territory("Østergade", 			"Street", "White", 29, 5600,new int[]{500,2400,7200,17000,20500,24000});
		fields[31] 	= new Territory("Amagertorv", 			"Street", "Yellow", 31, 6000,new int[]{550,2600,7800,18000,22000,25000});
		fields[32] 	= new Territory("Vimmelskaftet", 		"Street", "Yellow", 32, 6000,new int[]{550,2600,7800,18000,22000,25000});
		fields[35] 	= new Territory("Nygade",				"Street", "Yellow", 35, 6400,new int[]{600,3000,9000,20000,24000,28000});
		fields[38] 	= new Territory("Rådhuspladsen", 		"Street", "Purple", 38, 8000,new int[]{1000,4000,12000,28000,34000,40000});
		fields[39] 	= new Territory("Frederiksberggade", 	"Street", "Purple", 39, 7000,new int[]{700,3500,10000,22000,26000,30000});
		
		
		//Tax
		fields[4] = new Tax("Pay Tax","You need to pay taxes",4,200,10);
		fields[37] = new Tax("Skat", "Pay your damned taxes", 37, 200, 10);
		
		//Fleet
		fields[5] = new Fleet ("Øresundsredderiet","Fleet",5,4000, fleetRent);
		fields[14] = new Fleet("DFDS","Fleet",14,4000 , fleetRent);
		fields[25] = new Fleet("A/S. redderiet", "Fleet", 25, 4000, fleetRent);
		fields[34] = new Fleet("Bornholm", "Fleet", 34, 4000, fleetRent);
		
		//Jail
		fields[10] = new Jail ("Jail","Your jailed bitch",10);
		fields[30] = new GoToJail("Go to jail", "go to jail", 30);
		
		//Brewery
		fields[16] = new Brewery ("Tuborg","Brewery",16, 3000, 10, diceone);
		fields[28] = new Brewery("Coca-Cola", "Brewery", 28, 3000, 100, diceone);
		
		//Parking
		fields[20] = new FreeParking ("Helle","Park free here",20);
		
		//Initialize the GUI
		GameBoard.initializeGUI(fields);
		
		return this.fields;
	}
	

	public Field getField(int index)
	{
		return fields[index];
	}

	public Field[] getFieldList()
	{
		return fields;
	}
	
	public Field getFieldByName(String fieldName)
	{
		for(Field field : fields)
		{
			if(field.getName().equals(fieldName))
				return field;
		}
		
		return null;
	}
	
	public Field[] getOwnedTerritoryBuildable(Player player)
	{
		Field[] fields = new Field[player.getFieldsOwned()];
		
		int i = 0;
		
		for(Field field : this.getFieldList())
		{
			Player owner = field.getOwner();
			if(		field != null
					&& field instanceof Territory
					&& owner != null
					&& owner == player)
			{
				fields[i] = field;
				i++;
			}
		}
		
		if(fields[0] == null)
			return new Field[0];
		
		ArrayList<Territory> returnFields = new ArrayList<Territory>();
		for(Field field : fields)
		{
			if(	field != null
				&& (((Territory) field).isTerritoryBuildable((Territory)field, fields, this.fields))
				&& ((Territory)field).getNumberOfHotels() != ((Territory)field).getMaxNumberOfHotels()
				&& ((Territory)field).getNumberOfHouses() != ((Territory)field).getMaxNumberOfHouses()
				)
				returnFields.add((Territory)field);
		}
		
		return returnFields.toArray(new Field[returnFields.size()]);
	}
	
	public Field[] getOwnedTerritory(Player player)
	{
		Field[] fields = new Field[player.getFieldsOwned()];
		int i = 0;
		
		for(Field field : this.getFieldList())
		{
			Player owner = field.getOwner();
			if(field instanceof Territory && owner != null && owner == player) 
			{
				fields[i] = field;
				i++;
			}
		}
		
		return fields;
	}
	
	public String[] getFieldNames(Field[] fields)
	{
		String[] fieldNames = new String[fields.length];
		
		int i = 0;
		for(Field field : fields)
		{
			fieldNames[i] = field.getName();
			i++;
		}
		
		return fieldNames;
	}
}
