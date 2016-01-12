package game.controllers;

import desktop_fields.Street;
import desktop_resources.GUI;
import desktop_fields.Field;
import desktop_fields.Ownable;

import entities.ChanceCards.ChanceCard;

import java.awt.Color;

public class GameBoard {
	private ChanceCard[] chances;
	private static Field[] fields;

	/**
	 * builds the gameboard, the first part builds the GUI board, and the second
	 * builds the code board.
	 */
	public static void initializeGUI(entities.fields.abstracts.Field[] fields) {
		Field[] fieldsGUI = new Field[fields.length];
		int i = 0;
		for (entities.fields.abstracts.Field item : fields) {
			Color background = Color.lightGray;
			String subtext = "Territory";

			if (i == 1 || i == 3) {
				background = Color.orange;
				subtext = "Territory";
			} else if (i == 6 || i == 8 || i == 9) {
				background = Color.pink;
				subtext = "Territory";
			} else if (i == 11 || i == 12 || i == 13) {
				background = Color.green;
				subtext = "Territory";
			} else if (i == 17 || i == 18 || i == 19) {
				background = Color.blue;
				subtext = "Territory";
			} else if (i == 21 || i == 22 || i == 23) {
				background = Color.red;
				subtext = "Territory";
			} else if (i == 26 || i == 27 || i == 29) {
				background = Color.white;
				subtext = "Territory";
			} else if (i == 31 || i == 32 || i == 35) {
				background = Color.yellow.brighter();
				subtext = "Territory";
			} else if (i == 38 || i == 39) {
				background = Color.magenta.darker();
				subtext = "Territory";
			} else if (item instanceof entities.fields.Tax) {
				background = Color.lightGray;
				subtext = "Tax";
			}

			else if (item instanceof entities.fields.GoToJail) {
				background = Color.darkGray;
				subtext = "Go to jail";
			} else if (item instanceof entities.fields.Jail) {
				background = Color.darkGray;
				subtext = "Jail";
			} else if (item instanceof entities.fields.FreeParking) {
				background = Color.darkGray;
				subtext = "Free Parking!";
			} else if (item instanceof entities.fields.ChanceField) {
				background = Color.cyan;
				subtext = "Chance Card";
			} else if (item instanceof entities.fields.StartField) {
				background = Color.red;
				subtext = "Start";
			}

			// Ownable fields
			else if (item instanceof entities.fields.ownable.Fleet) {
				background = Color.red.darker();
				subtext = "Fleet";
			}

			else if (item instanceof entities.fields.ownable.Brewery) {
				background = Color.orange.darker();
				subtext = "Brewery";
			}

			/*
			 * The index is reduced by one to fit into the array.
			 */
			fieldsGUI[i] = new Street.Builder().setTitle(item.getName()).setSubText(subtext)
					.setDescription(item.getDescription()).setBgColor(background).build();

			i++;
		}
		GUI.create(fieldsGUI);

	}
}
