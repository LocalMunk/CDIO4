package game.controllers;

import desktop_fields.Street;
import desktop_resources.GUI;
import desktop_fields.Field;
import desktop_fields.Ownable;


import entities.ChanceCards.ChanceCard;

import java.awt.Color;

public class GameBoard
{
	private ChanceCard[] chances;
	private static Field[] fields;

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
		    
//		    else if(item instanceof entities.fields)
//	        {
//	            background = Color.yellow;
//	            subtext    = "Chance card";
//	        }
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
