package game;

import game.controllers.GameController;

public class Turn
{

	private int check;

	public Turn()
	{
		check = 1;

	}

	/**
	 * changes the turn of the players
	 */
	public void change(int numberOfPlayers)
	{
		if (check == numberOfPlayers)
		{
			check = 1;
		}
		else
		{
			check++;
		}
	}

	public int getCheck()
	{
		return check;
	}
	
	public void extraTurnToCurrentPlayer(){
		check--;
	}
}
