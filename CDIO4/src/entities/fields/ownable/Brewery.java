package entities.fields.ownable;

import desktop_resources.GUI;
import entities.fields.abstracts.Ownable;
import entities.fields.collection.FieldCollection;
import game.Dice;
import game.Player;

public class Brewery extends Ownable
{
	private int rate;
	private Dice dice;
	private FieldCollection fieldCollection;

	/**
	 * Creates a LaborCamp field
	 * 
	 * @param a
	 *            Field price (int)
	 * @param b
	 *            Field number (int)
	 */
	public Brewery(String name, String description, int fieldID, int price, int rate, Dice dice, FieldCollection fieldCollection)
	{
		super(name, description, fieldID, price);
		this.rate = rate;
		this.dice = dice;
		this.fieldCollection = fieldCollection;
	}

	@Override
	public int getRent()
	{
		return dice.roll() * rate * fieldCollection.getOwnedFieldsOfType(this, this.owner).length;
	}

	@Override
	public void landOnField(Player player)
	{
		if (player == owner)
		{

			GUI.showMessage("You own this field");

		}
		/**
		 * Checks whether the player is owner of the field
		 * 
		 */
		else if (owner == null)
		{

			/**
			 * If the field is not owned, the player is asked wether he wants to
			 * buy the field or not It then checks the player's balance to see
			 * if there is sufficient money If the player buys the field he is
			 * set as the owner and withdraws the price from the players account
			 * The GUI is updated with the correct information
			 */
			if (player.getAccount().getBalance() >= price
			        && GUI.getUserLeftButtonPressed("Do you want to buy this field?", "Yes", "No"))
			{
				owner = player;
				GUI.setOwner(fieldID + 1, owner.getName());
				player.getAccount().withdraw(price);
				GUI.setBalance(player.getName(), player.getAccount().getBalance());
				}
		}

		/**
		 * Withdraws a dice roll times 100 times how many LaborCamps is other by
		 * the otherplayer
		 * 
		 */
		else if (owner != null && owner != player)
		{
			if (owner.getJailed()==false)
			{
				int getmoney = this.getRent();
				owner.getAccount().deposit(player.getAccount().withdraw(getmoney));
				GUI.setBalance(player.getName(), player.getAccount().getBalance());
				GUI.setBalance(owner.getName(), owner.getAccount().getBalance());
			}
			else{
				GUI.showMessage("The owner is jailed so you pass through without paying him money");
			}
		}
		else if (price > player.getAccount().getBalance())
			GUI.showMessage("Your balance is too low");
	}

	/**
	 * Returns the price of the field
	 */
	@Override
	public int getPrice()
	{
		// TODO Auto-generated method stub
		return price;
	}

	/**
	 * Sets the owner
	 */
	@Override
	public void setOwner(Player player)
	{
		this.owner = player;

	}

	/**
	 * Returns the owner of a given field
	 */
	@Override
	public Player getOwner()
	{
		// TODO Auto-generated method stub
		return owner;
	}
}
