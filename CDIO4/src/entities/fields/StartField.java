package entities.fields;

import desktop_resources.GUI;
import entities.fields.abstracts.Field;
import game.Player;

public class StartField extends Field
{
	public StartField(String name, int fieldID, String description)
	{
		super(name, description, fieldID);
	}
	@Override
	public void landOnField(Player player)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Player getOwner()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOwned(boolean bool)
	{
		// TODO Auto-generated method stub

	}
	public static void getStartMoney(Player player) {
		player.getAccount().deposit(200);
		GUI.setBalance(player.getName(), player.getAccount().getBalance());
		// TODO Auto-generated method stub
		
	}

}
