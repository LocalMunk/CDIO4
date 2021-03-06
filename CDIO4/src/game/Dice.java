package game;

import desktop_resources.GUI;

public class Dice
{

	private int sides, value, a, b;

	public Dice(int a)
	{
		sides = a;
	}

	/**
	 * Rolls 2 dice and returns the sum
	 */
	public int roll()
	{
		a = (int) (Math.random() * sides + 1);
		b = (int) (Math.random() * sides + 1);
		GUI.setDice(a, b);
		value = a + b;
		return value;
	}
	
	public boolean getEquals(){
		if(a == b)
			return true;
		else
			return false;
	}

	public int getValue()
	{
		return value;
	}
}
