package game.controllers;

import desktop_fields.Street;
import desktop_resources.GUI;
import entities.fields.ChanceCards;
import entities.fields.FreeParking;
import entities.fields.StartField;
import entities.fields.abstracts.Field;
import entities.fields.ownable.Fleet;
import entities.fields.ownable.Territory;
import game.Dice;
import desktop_fields.Tax;
import desktop_fields.Brewery;
import desktop_fields.Jail;
import desktop_fields.Refuge;
import desktop_fields.Start;
import desktop_fields.Shipping;

public class GameBoard
{

	private Field[] fields = new Field[22];
//	private desktop_fields.Field[] fields = new desktop_fields.Field[22];

	/**
	 * builds the gameboard, the first part builds the GUI board, and the second
	 * builds the code board.
	 */
	public GameBoard()
	{
		Dice diceone = new Dice(6);

		fields[0] = new StartField("Start",1,"Start felt");
		fields[1] = new Territory("Rødovrevej","Street",2,60,10);
		fields[2] = new ChanceCards("Try your luck","IS your luck shit?",3);
		fields[3] = new Territory ("Hvidovrevej","Street",4,60 ,10);
		fields[4] = new Tax("Pay Tax","You need to pay taxes",5,200,0,1);
		fields[5] = new Fleet ("Øresundsredderiet","Fleet",6,200, null);
		fields[6] = new Territory ("Roskildevej","Street",7, 100 ,10);
		fields[7] = new ChanceCards("Try your luck","Is your luck shit?",8);
		fields[8] = new Territory ("Valby langgade","Street",9,100 ,10);
		fields[9] = new Territory ("Alle gadé","Street",10,60 ,10);
		fields[11] = new Jail ("Jail","Your jailed bitch",11);
		fields[12] = new Territory ("Frederisksberg allé","Street",12,140 ,10);
		fields[13] = new Brewery ("Tuborg","Brewery",13, 150, 10, diceone);
		fields[14] = new Territory ("Bülwsvej","Street",14,140 ,10);
		fields[15] = new Territory ("Gammelkonge vej","Street",15,60 ,10);
		fields[16] = new Territory ("Hvidovrevej","Street",16,140 	 ,10);
		fields[17] = new Fleet("DFDS","Fleet",17,200 , null);
		fields[18] = new Territory ("Bernsstoffvej","Street",18,180 ,10);
		fields[19] = new ChanceCards("Try your luck","ChanceCard",19);
		fields[20] = new Territory ("Hellerupvej","Street",20,180 ,10);
		fields[21] = new Territory ("Strandvejen","Street",21,180 ,10);
		fields[22] = new FreeParking ("Helle","Park free here",22);
		fields[23] = new Territory ("Trianglen","Street",23,220 ,10);
		fields[24] = new ChanceCards ("Try your luck","Street", 24);
		fields[25] = new Territory("Østerbrogade", "Street", 25, 220, 10);
		fields[26] = new Territory("Grønningen", "Street", 26, 240, 10);
		fields[27] = new Fleet("Ø.S. redderiet", "Fleet", 27, 200);
		fields[28] = new Territory("Bredgade", "Street", 28, 260,10);
		fields[29] = new Territory("Kgs. Nytorv", "Street", 29, 260, 10);
		fields[30] = new Brewery("Carlsberg", "Brewery", 30, 150);
		fields[31] = new Territory("Østergade", "Street", 31, 280,10);
		fields[32] = new GoToJail("Go to jail", "go to jail", 32);
		fields[33] = new Territory("Amagertorv", "Street", 33, 300, 10);
		fields[33] = new Territory("Vimmelskaftet", "Street", 34, 300, 10);
		fields[34] = new ChanceCards("Try your luck", "is your luck shit?", 35);
		fields[35] = new Territory("Nygade", "Street", 36, 320,10);
		fields[36] = new Fleet("Bornholm", "Fleet", 37, 200);
		fields[37] = new ChanceCards("Try your luck", "is your luck shit?", 38);
		fields[38] = new Territory("Frederiksnerggade", "Street", 39, 350, 10);
		fields[39] = new Tax("Skat", "Pay your damned taxes", 40);
		fields[40] = new Territory("Rådhuspladsen", "Street", 41, 400, 10);	}

	public Field getField(int a)
	{
		a--;
		a--;
		return fields[a];
	}

	public Field[] getAreaList()
	{
		return fields;
	}
}
