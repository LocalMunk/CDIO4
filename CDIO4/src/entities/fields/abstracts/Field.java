package entities.fields.abstracts;

import game.Player;

public abstract class Field
{
	protected String name;
	protected String description;
	protected int fieldID;
	
	public Field(String name, String description, int fieldID)
	{
		this.name = name;
		this.description = description;
		this.fieldID = fieldID;
	}
	

	/**
	 * landOnField describes what happens for a player when they land on a
	 * field. All subclasses implements different behaviors, depending on the
	 * field type.
	 * 
	 * @param player
	 */
	public abstract void landOnField(Player player);

	public String getName()
	{
		return this.name;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public int getFieldID()
	{
		return this.fieldID;
	}
}