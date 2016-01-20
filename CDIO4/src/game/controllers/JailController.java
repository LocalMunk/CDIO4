package game.controllers;

import desktop_resources.GUI;
import game.Dice;
import game.Player;

public class JailController
{
	
	
	/**
	*metode der holder styr på spilleren i fængsel
	*tjekker først hvor lang tid de har været i fængsel
	*giver så valget mellem at slå 2 ens eller betale
	*hvis de ikke kommer ud opdateres deres TimeJailed
	*/
	public void handle(Player player, Dice dice)
	{
		
		if(player.getTimeJailed() >= 3){ //Først tjekker den om spilleren har været jailed i 3 runder. Hvis ja så bliver hans jailed sat til false
			player.setJailed(false);
			player.setTimeJailed(0);
			GUI.showMessage("You have served your time in jail, and you have been released");
			player.setPosition(10);
			//GUI isn't 0 indexed so we add 1
			GUI.setCar(player.getPosition()+1, player.getName());
			
		}
		else{ //hvis hans TimeJailed ikke er 3 eller større kører den videre til næste statement
			if (GUI.getUserButtonPressed(player.getName() + ", You are in jail, roll two equal dice to get out of jail, or pay 3000", "Roll", "Pay") 
		        .equals("Roll"))	//Valget kommer frem på GUI'en: Betal 3000 eller prøv at rolle 2 ens terninger
				{
				dice.roll();
				
				if(dice.getEquals()){ //Hvis han vælger at rolle og får 2 ens terninger løslades han.
					player.setJailed(false);
					GUI.showMessage("You rolled two equal dice, and therefore the guards are inclined to release you");
					player.setPosition(10);
					//GUI isn't 0 indexed so we add 1
					GUI.setCar(player.getPosition()+1, player.getName());
					
				}
				else{ //hvis han vælger at rolle og ikke får 2 ens forbliver han jailed og hans TimeJailed opdateres med +1
					GUI.showMessage("You failed to roll two equal die, and therefore you are still jailed");
					player.setTimeJailed(player.getTimeJailed() + 1);
				}
						}
			else{ //Hvis han vælger at betale 3000 trækkes pengene fra hans account og han løslades
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
	/**
	*Metode der bruges hvis en spiller har rollet 2 ens terninger fra runder i træk.
	*hans jailed = true og han flyttes til jail feltet
	*/
	public void goToJail(Player player) 
	{
		player.setJailed(true);
		GUI.removeAllCars(player.getName());
		player.setPosition(10);
		// GUI isn't 0 indexed so we add 1
		GUI.setCar(player.getPosition() + 1, player.getName());
		GUI.showMessage("You rolled two equal dice 3 times in a row. Go to jail.");
	}
}
