package entities.fields.ownable;

import desktop_resources.GUI;
import entities.fields.abstracts.Ownable;
import entities.fields.collection.FieldCollection;
import game.Player;

public class Fleet extends Ownable
{
	/**
	 * Sets up different rents
	 */
	private int[] rent;
	private FieldCollection fieldCollection;

	/**
	 * Creates a fleet field. Buying price is predefined at 4.000
	 * 
	 * @param a
	 *            Name of the fleet field (String)
	 * @param b
	 *            Field number (int)
	 */
	public Fleet(String name, String description, int fieldID, int price, int[] rent, FieldCollection fieldCollection)
	{
		super(name, description, fieldID, price);
		this.rent = rent;
		this.fieldCollection = fieldCollection;
	}

	@Override
	public int getRent()
	{
		// TODO Auto-generated method stub
		//int checker =  ; // fix this
		 int num = fieldCollection.getAmountFleetsOwned(owner);
		
		return rent[num-1]; // -1 får at få det til at passe med array'et

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
		else if (owner == null && player.getAccount().getBalance() > price)
		{
			if (GUI.getUserLeftButtonPressed("Do you want to buy this field?", "Yes", "No"))
			{
				owner = player;
				GUI.showMessage("You are the proud owner of this.");
				GUI.setOwner(fieldID + 1, owner.getName());
				player.getAccount().withdraw(price);
				GUI.setBalance(player.getName(), player.getAccount().getBalance());
			}
		}
		/**
		 * If the player is not the owner it then checks how meny fleets is
		 * owned by the other player and with draws it form the players account
		 */
		else if (owner != null && owner != player)
		{
			if (owner.getJailed()==false)
			{
				int payout = this.getRent();
				owner.getAccount().deposit(player.getAccount().withdraw(payout));
				GUI.setBalance(player.getName(), player.getAccount().getBalance());
				GUI.setBalance(owner.getName(), owner.getAccount().getBalance());
			}
			else{
				GUI.showMessage("The owner is jailed so you pass through without paying him money");
			}
			/**
			 * If the players balance is too low,when buying a field - the GUI shows
			 * a message
			 */
		}
			else if (price > player.getAccount().getBalance())
			{
				GUI.showMessage("Your balance is too low");

			}
		
	}

	@Override
	public int getPrice()
	{
		return 0; //burde returne price //burde slet ikk være der, da prisen ligger hvor man opretter feltet
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

}
