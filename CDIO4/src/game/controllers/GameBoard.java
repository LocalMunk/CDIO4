package game.controllers;

<<<<<<< HEAD
import java.util.Random;

import desktop_fields.Street;
import desktop_resources.GUI;
import entities.fields.ChanceField;
import entities.fields.FreeParking;
import entities.fields.StartField;
import entities.fields.abstracts.Field;
import entities.fields.ownable.Fleet;
import entities.fields.ownable.Territory;
import game.ChanceCards;
=======
import desktop_fields.Start;
import desktop_fields.Street;
import desktop_resources.GUI;
>>>>>>> origin/FieldCollections
import game.Dice;
import game.Player;
import desktop_fields.Tax;

import java.awt.Color;

import desktop_fields.Brewery;
import desktop_fields.Field;
import desktop_fields.Jail;
import desktop_fields.Refuge;
import desktop_fields.Start;
import desktop_fields.Shipping;

public class GameBoard
{

<<<<<<< HEAD
	private Field[] fields = new Field[22];
	private ChanceCards[] chance = new ChanceCards[10];
=======
	private static Field[] fields = new Field[41];
>>>>>>> origin/FieldCollections
//	private desktop_fields.Field[] fields = new desktop_fields.Field[22];

	/**
	 * builds the gameboard, the first part builds the GUI board, and the second
	 * builds the code board.
	 */
	public static void initializeGUI(entities.fields.abstracts.Field[] fields)
	{
		Field[] fieldsGUI = new Field[fields.length];
		int i = 0;
		for(entities.fields.abstracts.Field item : fields)
		{
			Color  background = Color.lightGray;
		    String subtext    = "Territory";
		    
		    if(item instanceof entities.fields.Refuge)
		    {
		        background = Color.green;
		        subtext    = "Refuge";
		    }
		    
		    else if(item instanceof entities.fields.Tax)
	        {
	            background = Color.magenta;
	            subtext    = "Tax";
	        }
		    
		    else if(item instanceof entities.fields.GoToJail)
	        {
	            background = Color.lightGray;
	            subtext    = "Go to jail";
	        }
		    
		    else if(item instanceof entities.fields.Jail)
	        {
	            background = Color.gray;
	            subtext    = "Jail";
	        }
		    
		    else if(item instanceof entities.fields.ChanceCards)
	        {
	            background = Color.yellow;
	            subtext    = "Chance card";
	        }
		    else if(item instanceof entities.fields.FreeParking)
	        {
	            background = Color.lightGray;
	            subtext    = "Free parking";
	        }
		    else if(item instanceof entities.fields.StartField)
	        {
	            background = Color.red;
	            subtext    = "Start";
	        }
		    
		    
		    //Ownable fields
		    else if(item instanceof entities.fields.ownable.Fleet)
		    {
		        background = Color.cyan;
		        subtext    = "Fleet";
		    }
		    
		    else if(item instanceof entities.fields.ownable.Brewery)
		    {
		        background = Color.orange;
		        subtext    = "Brewery";
		    }
		    
		    else if(item instanceof entities.fields.ownable.Territory)
		    {
		        background = Color.blue;
		        subtext    = "Territory";
		    }
		    
		    /*
		     * The index is reduced by one to fit into
		     * the array.
		     */
		    fieldsGUI[i] = new Street.Builder()
	            .setTitle(item.getName())
	            .setSubText(subtext)
	            .setDescription(item.getDescription())
	            .setBgColor(background)
	            .build();
			
		    i++;
		}
			GUI.create(fieldsGUI);

<<<<<<< HEAD
		fields[0] = new StartField("Start",1,"Start felt");
		fields[1] = new Territory("R�dovrevej","Street",2,60,10);
		fields[2] = new ChanceField("Try your luck","IS your luck shit?",3);
		fields[3] = new Territory ("Hvidovrevej","Street",4,60 ,10);
		fields[4] = new Tax("Pay Tax","You need to pay taxes",5,200,0,1);
		fields[5] = new Fleet ("�resundsredderiet","Fleet",6,200, null);
		fields[6] = new Territory ("Roskildevej","Street",7, 100 ,10);
		fields[7] = new ChanceField("Try your luck","Is your luck shit?",8);
		fields[8] = new Territory ("Valby langgade","Street",9,100 ,10);
		fields[9] = new Territory ("Alle gad�","Street",10,60 ,10);
		fields[11] = new Jail ("Jail","Your jailed bitch",11);
		fields[12] = new Territory ("Frederisksberg all�","Street",12,140 ,10);
		fields[13] = new Brewery ("Tuborg","Brewery",13, 150, 10, diceone);
		fields[14] = new Territory ("B�lwsvej","Street",14,140 ,10);
		fields[15] = new Territory ("Gammelkonge vej","Street",15,60 ,10);
		fields[16] = new Territory ("Hvidovrevej","Street",16,140 	 ,10);
		fields[17] = new Fleet("DFDS","Fleet",17,200 , null);
		fields[18] = new Territory ("Bernsstoffvej","Street",18,180 ,10);
		fields[19] = new ChanceField("Try your luck","ChanceCard",19);
		fields[20] = new Territory ("Hellerupvej","Street",20,180 ,10);
		fields[21] = new Territory ("Strandvejen","Street",21,180 ,10);
		fields[22] = new FreeParking ("Helle","Park free here",22);
		fields[23] = new Territory ("Trianglen","Street",23,220 ,10);
		fields[24] = new ChanceField ("Try your luck","Street", 24);
		fields[25] = new Territory("�sterbrogade", "Street", 25, 220, 10);
		fields[26] = new Territory("Gr�nningen", "Street", 26, 240, 10);
		fields[27] = new Fleet("�.S. redderiet", "Fleet", 27, 200);
		fields[28] = new Territory("Bredgade", "Street", 28, 260,10);
		fields[29] = new Territory("Kgs. Nytorv", "Street", 29, 260, 10);
		fields[30] = new Brewery("Carlsberg", "Brewery", 30, 150);
		fields[31] = new Territory("�stergade", "Street", 31, 280,10);
		fields[32] = new GoToJail("Go to jail", "go to jail", 32);
		fields[33] = new Territory("Amagertorv", "Street", 33, 300, 10);
		fields[33] = new Territory("Vimmelskaftet", "Street", 34, 300, 10);
		fields[34] = new ChanceField("Try your luck", "is your luck shit?", 35);
		fields[35] = new Territory("Nygade", "Street", 36, 320,10);
		fields[36] = new Fleet("Bornholm", "Fleet", 37, 200);
		fields[37] = new ChanceField("Try your luck", "is your luck shit?", 38);
		fields[38] = new Territory("Frederiksberggade", "Street", 39, 350, 10);
		fields[39] = new Tax("Skat", "Pay your damned taxes", 40);
		fields[40] = new Territory("R�dhuspladsen", "Street", 41, 400, 10);	
		
	
		chance[0] = new ChanceCards("You've won 100 in the lottery", 100);
		chance[1] = new ChanceCards("You've won 200 in the lottery", 200);
		chance[2] = new ChanceCards("The queen is visiting the city and she gave you, 250", 250);
		chance[3] = new ChanceCards("You sell some lemonade, 300", 300);
		chance[4] = new ChanceCards("You see an old lady drop some money, you pick'em up, 500", 500);
		chance[5] = new ChanceCards("You got a ticket for driving and snapping, pay 100", 100);
		chance[6] = new ChanceCards("You drive too fast, get a speeding ticket, pay 300", 300);
		chance[7] = new ChanceCards("Pay your dentist bill, 500", 500);
		chance[8] = new ChanceCards("Your car is broken, pay the repair bill, 200", 200);
		chance[9] = new ChanceCards("Shoe sale, you went crazy and spent 250", 250);
	
	}

	public Field getField(int a)
	{
		a--;
		a--;
		return fields[a];
	}

	public Field[] getAreaList()
	{
		return fields;
=======
>>>>>>> origin/FieldCollections
	}
	public ChanceCards[] getCardList()
	{
		return chance;
	}
}
