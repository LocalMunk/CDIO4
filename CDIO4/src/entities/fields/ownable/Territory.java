package entities.fields.ownable;

import desktop_resources.GUI;
import entities.fields.abstracts.Ownable;
import game.Player;

public class Territory extends Ownable
{
	private int rent, price;
	
	private int houses;
	private int hotels;
	
	private final int MAX_HOUSES = 4;
	private final int MAX_HOTELS = 1;
	
	private final int HOUSE_PRICE = 500;
	private final int HOTEL_PRICE = 2000;

	/**
	 * Creates a territory field
	 * 
	 * @param name
	 *            Field name (string)
	 * @param description
	 *            Field description (string)
	 * @param price
	 *            Field Ownable price (int)
	 * @param rent
	 *            rent (int)
	 */
	public Territory(String name, String description, int fieldID, int price, int rent)
	{
		super(name, description, price, fieldID);
		this.rent = rent;
		this.houses = 0;
		this.hotels = 0;
	}

	@Override
	public int getRent()
	{
		return rent;
	}

	@Override
	public void landOnField(Player player)
	{
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
		else if (owned != true && player.getAccount().getBalance() >= price)
		{
			if (GUI.getUserLeftButtonPressed("Do you want to buy this field", "Yes", "No"))
			{
				owned = true;
				owner = player;
				GUI.showMessage("You are the proud owner of this.");
				GUI.setOwner(fieldID + 1, owner.getName());
				player.getAccount().withdraw(price);
				GUI.setBalance(player.getName(), player.getAccount().getBalance());
			}
		}
		/**
		 * If the player is not the owner it then withdraws the rent of the
		 * given field and deposit it on the players account
		 */

		else if (owned && owner != player)
		{
			owner.getAccount().deposit(player.getAccount().withdraw(getRent()));
			GUI.setBalance(player.getName(), player.getAccount().getBalance());
			GUI.setBalance(owner.getName(), owner.getAccount().getBalance());
		}

		/**
		 * If the players balance is too low,when buying a field - the GUI shows
		 * a message
		 */
		else if (price > player.getAccount().getBalance())
		{
			GUI.showMessage("Your balance is too low");

		}
	}

	/**
	 * Returns the price of a given field
	 */
	public int getPrice()
	{
		return price;
	}

	/**
	 * Returns the owner of a given field
	 */
	public Player getOwner()
	{
		return owner;
	}

	/**
	 * Sets the owner of a given field
	 */
	public void setOwner(Player player)
	{
		// TODO Auto-generated method stub
		owner = player;
	}
	
	public int getNumberOfHouses()
	{
		return this.houses;
	}
	
	public int getNumberOfHotels()
	{
		return this.hotels;
	}
	
	public String[] getPossibleBuildings()
	{
//		int maximumNumberOfBuildings = this.MAX_HOUSES + this.MAX_HOTELS;
		
		//If they already have the maximum number of houses/hotels we return null
		if(this.houses == this.MAX_HOUSES || this.hotels == this.MAX_HOTELS)
			return null;
		
		//Get possible houses
		int numberOfHousesLeft = this.MAX_HOUSES - this.houses;
		String[] possibleHouses = new String[numberOfHousesLeft];
		
		int w = 0;
		for(int i = numberOfHousesLeft; i >= 1; i--)
		{
			possibleHouses[w] = "Houses: " + (this.MAX_HOUSES - i);
			w++;
		}
		
		//Get possible Hotels
		int numberOfHotelsLeft = this.MAX_HOTELS - this.hotels;
		String[] possibleHotels = new String[numberOfHotelsLeft];
		
		w = 0;
		for(int i = numberOfHotelsLeft; i >= 1; i--)
		{
			possibleHotels[w] = "Hotel: " + (this.MAX_HOTELS - i);
			w++;
		}
		
		
		return new String[numberOfHousesLeft + numberOfHotelsLeft];
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
