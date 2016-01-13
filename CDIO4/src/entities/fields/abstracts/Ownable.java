package entities.fields.abstracts;

import game.Player;

public abstract class Ownable extends Field
{
	protected int price;
	protected Player owner;
	
	public Ownable(String name, String description, int fieldID, int price)
	{
		super(name, description, fieldID);
		this.price = price;
	}

	public abstract int getRent();

	public abstract int getPrice();

	public abstract void setOwner(Player player);

}
