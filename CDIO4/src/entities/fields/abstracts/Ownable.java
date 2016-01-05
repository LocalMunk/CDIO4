package entities.fields.abstracts;

import game.Player;

public abstract class Ownable extends Field
{
	protected int price;
	protected Player owner;
	protected boolean owned;
	
	public Ownable(String name, String description, int price)
	{
		super(name, description);
		this.price = price;
		this.owned = false;
	}

	public abstract int getRent();

	public abstract int getPrice();

	public abstract void setOwner(Player player);

}
