package entities.fields;

import entities.fields.abstracts.Field;
import game.Player;

public class StartField extends Field
{

	public StartField(String name, String description)
	{
		super(name, description);
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

}
