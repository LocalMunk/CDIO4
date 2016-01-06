package entities.fields.ownable;

import desktop_resources.GUI;
import entities.fields.abstracts.Ownable;
import game.Player;

public class Fleet extends Ownable
{
	/**
	 * Sets up different rents
	 */
	private int[] rent;

	/**
	 * Creates a fleet field. Buying price is predefined at 4.000
	 * 
	 * @param a
	 *            Name of the fleet field (String)
	 * @param b
	 *            Field number (int)
	 */
	public Fleet(String name, String description, int fieldID, int price, int[] rent)
	{
		super(name, description, price, fieldID);
		this.rent = rent;
	}

	@Override
	public int getRent()
	{
		// TODO Auto-generated method stub
		int checker = owner.getFieldsOwned();
		int payout = this.rent[checker-1];
		
		return payout;

	}

	@Override
	public void landOnField(Player player)
	{
		/**
		 * Checks whether the player is owner of the field
		 * 
		 */
		if (player == owner)
		{
			GUI.showMessage("You own this field");
		}

		/**
		 * If the field is not owned, the player is asked wether he wants to buy
		 * the field or not It then checks the player's balance to see if there
		 * is sufficient money If the player buys the field he is set as the
		 * owner and withdraws the price from the players account The GUI is
		 * updated with the correct information
		 */
		else if (owned != true && player.getAccount().getBalance() > price)
		{
			if (GUI.getUserLeftButtonPressed("Do you want to buy this field?", "Yes", "No"))
			{
				owned = true;
				owner = player;
				GUI.showMessage("You are the proud owner of this.");
				player.addFieldsOwned();
				GUI.setOwner(fieldID + 1, owner.getName());
				player.getAccount().withdraw(price);
				GUI.setBalance(player.getName(), player.getAccount().getBalance());
			}
		}
		/**
		 * If the player is not the owner it then checks how meny fleets is
		 * owned by the other player and with draws it form the players account
		 */
		else if (owned && owner != player)
		{
			int payout = this.getRent();
			owner.getAccount().deposit(player.getAccount().withdraw(payout));
			GUI.setBalance(player.getName(), player.getAccount().getBalance());
			GUI.setBalance(owner.getName(), owner.getAccount().getBalance());
		}
		else
		{
			System.out.println("An error occured");
		}
	}

	@Override
	public int getPrice()
	{
		return 0;
	}

	@Override
	/**
	 * Sets the owner
	 */

	public void setOwner(Player player)
	{
		// TODO Auto-generated method stub
		this.owner = player;

	}

	/**
	 * Returns the owner
	 */
	@Override
	public Player getOwner()
	{
		// TODO Auto-generated method stub
		return owner;
	}

	/**
	 * Release the fields owned by a given player at his death
	 */
	@Override
	public void setOwned(boolean bool)
	{
		// TODO Auto-generated method stub
		owned = bool;
	}

}
