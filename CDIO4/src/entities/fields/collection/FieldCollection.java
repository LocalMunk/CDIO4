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
		
		int[] fleetRent = new int[] {400, 500, 600};

		//Start fields
		fields[0] = new StartField("Start",0,"Start felt");
		
		//Chance cards
		fields[2] = new ChanceField("Try your luck","IS your luck shit?",2);
		fields[7] = new ChanceField("Try your luck","Is your luck shit?",7);
		fields[18] = new ChanceField("Try your luck","ChanceCard",18);
		fields[17] = new ChanceField("Try your luck","Street", 17);
		fields[33] = new ChanceField("Try your luck", "is your luck shit?", 33);
		fields[36] = new ChanceField("Try your luck", "is your luck shit?", 36);
		
		//Territory
		fields[1] 	= new Territory("R�dovrevej",			"Street", "Blue",1,60,10);
		fields[3] 	= new Territory ("Hvidovrevej",			"Street", "Blue" ,3,60 ,10);
		fields[6] 	= new Territory ("Roskildevej",			"Street", "Salmon",6, 100 ,10);
		fields[8] 	= new Territory ("Valby langgade",		"Street", "Salmon",8,100 ,10);
		fields[9] 	= new Territory ("Alle gad�",			"Street", "Salmon",9,60 ,10);
		fields[10]	= new Territory ("Frederisksberg all�",	"Street", "Green",10,140 ,10);
		fields[12]	= new Territory ("B�lwsvej",			"Street", "Green",12,140 ,10);
		fields[13] 	= new Territory ("Gammelkonge vej", 	"Street", "Green",13,60 ,10);
		fields[15] 	= new Territory ("Bernsstoffvej",		"Street", "Grey",15,180 ,10);
		fields[21] 	= new Territory ("Hellerupvej",			"Street", "Grey",21,180 ,10);
		fields[19] 	= new Territory ("Strandvejen",			"Street", "Grey",19,180 ,10);
		fields[22] 	= new Territory ("Trianglen",			"Street", "Red",22,220 ,10);
		fields[23] 	= new Territory("�sterbrogade", 		"Street", "Red", 23, 220, 10);
		fields[24] 	= new Territory("Gr�nningen", 			"Street", "Red", 24, 240, 10);
		fields[26] 	= new Territory("Bredgade", 			"Street", "White", 26, 260,10);
		fields[27] 	= new Territory("Kgs. Nytorv", 			"Street", "White", 27, 260, 10);
		fields[29] 	= new Territory("�stergade", 			"Street", "White", 29, 280,10);
		fields[31] 	= new Territory("Amagertorv", 			"Street", "Yellow", 31, 300, 10);
		fields[32] 	= new Territory("Vimmelskaftet", 		"Street", "Yellow", 32, 300, 10);
		fields[35] 	= new Territory("Nygade",				"Street", "Yellow", 35, 320,10);
		fields[39] 	= new Territory("Frederiksberggade", 	"Street", "Purple", 39, 350, 10);
		fields[38] 	= new Territory("R�dhuspladsen", 		"Street", "Purple", 38, 400, 10);
		
		//Tax
		fields[4] = new Tax("Pay Tax","You need to pay taxes",4,200,10);
		fields[37] = new Tax("Skat", "Pay your damned taxes", 37, 200, 10);
		
		//Fleet
		fields[5] = new Fleet ("�resundsredderiet","Fleet",5,200, fleetRent);
		fields[14] = new Fleet("DFDS","Fleet",14,200 , fleetRent);
		fields[25] = new Fleet("�.S. redderiet", "Fleet", 25, 200, fleetRent);
		fields[34] = new Fleet("Bornholm", "Fleet", 34, 200, fleetRent);
		
		//Jail
		fields[11] = new Jail ("Jail","Your jailed bitch",11);
		fields[30] = new GoToJail("Go to jail", "go to jail", 30);
		
		//Brewery
		fields[16] = new Brewery ("Tuborg","Brewery",16, 150, 10, diceone);
		fields[28] = new Brewery("Carlsberg", "Brewery", 28, 150, 100, diceone);
		
		//Parking
		fields[20] = new FreeParking ("Helle","Park free here",20);
		
		//Initialize the GUI
		GameBoard.initializeGUI(fields);
		
		return this.fields;
	}
	

	public Field getField(int a)
	{
		a--;
		a--;
		return fields[a];
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
			if(field != null && field instanceof Territory && owner != null && owner == player) 
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
			if(field != null && (((Territory) field).isTerritoryBuildable((Territory)field, fields, this.fields)))
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
