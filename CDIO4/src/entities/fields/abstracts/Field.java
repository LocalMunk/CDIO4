package entities.fields.abstracts;

import game.Player;

public abstract class Field
{
	protected String name;
	protected String description;
	
	public Field(String name, String description)
	{
		this.name = name;
		this.description = description;
	}
	

	/**
	 * landOnField describes what happens for a player when they land on a
	 * field. All subclasses implements different behaviors, depending on the
	 * field type.
	 * 
	 * @param player
	 */
	public abstract void landOnField(Player player);

	public abstract Player getOwner();

	public abstract void setOwned(boolean bool);
}