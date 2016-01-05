package game;

import desktop_resources.GUI;
import game.controllers.GameController;

public class Run
{
	/**
	 * Runs the game.
	 */
	public static void main(String[] args)
	{
		GameController control = new GameController();
		control.loop();
		GUI.close();
	}
}