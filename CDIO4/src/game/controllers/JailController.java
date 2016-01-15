package game.controllers;

import desktop_resources.GUI;
import game.Dice;
import game.Player;

public class JailController
{
	public void handle(Player player, Dice dice)
	{
		if(player.getTimeJailed() >= 3){
			player.setJailed(false);
			player.setTimeJailed(0);
			GUI.showMessage("You have served your time in jail, and you have been released");
			player.setPosition(10);
			//GUI isn't 0 indexed so we add 1
			GUI.setCar(player.getPosition()+1, player.getName());
			
		}
		else{
			if (GUI.getUserButtonPressed(player.getName() + ", You are in jail, roll two equal dice to get out of jail, or pay 3000", "Roll", "Pay")
		        .equals("Roll")){
				dice.roll();
				
				if(dice.getEquals()){
					player.setJailed(false);
					GUI.showMessage("You rolled two equal dice, and therefore the guards are inclined to release you");
					player.setPosition(10);
					//GUI isn't 0 indexed so we add 1
					GUI.setCar(player.getPosition()+1, player.getName());
					
				}
				else{
					GUI.showMessage("You failed to roll two equal die, and therefore you are still jailed");
					player.setTimeJailed(player.getTimeJailed() + 1);
				}
						}
			else{
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
