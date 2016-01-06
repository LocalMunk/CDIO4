package game.controllers;

import desktop_fields.Start;
import desktop_fields.Street;
import desktop_resources.GUI;
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

	private static Field[] fields = new Field[41];
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

	}
}
