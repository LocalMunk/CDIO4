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
import game.Player;
//import desktop_fields.Tax;
//import desktop_fields.Brewery;
//import desktop_fields.Jail;
//import desktop_fields.Refuge;
//import desktop_fields.Start;
//import desktop_fields.Shipping;

public class GameBoard
{

	private Field[] fields = new Field[22];
//	private desktop_fields.Field[] fields = new desktop_fields.Field[22];

	/**
	 * builds the gameboard, the first part builds the GUI board, and the second
	 * builds the code board.
	 */
	public GameBoard(Field[] fields)
	{
		Dice diceone = new Dice(6);

		this.fields = fields;
	}
}
