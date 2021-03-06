package entities.fields.ownable;

import java.util.Properties;

import desktop_resources.GUI;
import entities.fields.abstracts.Field;
import entities.fields.abstracts.Ownable;
import game.Account;
import game.Player;

public class Territory extends Ownable
{
	private int rent[] = new int [6];
	
	private String groupName;
	
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
	public Territory(String name, String description, String groupName, int fieldID, int price, int[] rent)
	{
		super(name, description, fieldID, price);
		this.rent = rent;
		this.houses = 0;
		this.hotels = 0;
		this.groupName = groupName;
	}

	public Territory() //konstruktør uden parametre, som bruges til getOwnedFieldsOfType, da den kun bruger typen af felt, og ikke behøver alle parametrene
	{
		super("", "", 0, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int  getRent()
	{
		return rent[houses + hotels];
	}

	@Override
	public void landOnField(Player player)
	{
		if (player == owner)
		{

			GUI.showMessage("You own this field");

		}

		/**
		 * If the field is not owned, the player is asked whether he wants to buy
		 * the field or not It then checks the player's balance to see if there
		 * is sufficient money If the player buys the field he is set as the
		 * owner and withdraws the price from the players account The GUI is
		 * updated with the correct information
		 */
		else if (owner == null && player.getAccount().getBalance() >= price)
		{
			if (GUI.getUserLeftButtonPressed("Do you want to buy this field", "Yes", "No"))
			{
				owner = player;
				GUI.showMessage("You are the proud owner of this.");
				//GUI isn't 0 indexed so we add 1
				GUI.setOwner(fieldID + 1, owner.getName());
				player.getAccount().withdraw(price);
				GUI.setBalance(player.getName(), player.getAccount().getBalance());
			}
		}
		/**
		 * If the player is not the owner it then withdraws the rent of the
		 * given field and deposit it on the players account
		 */

		else if (owner != null && owner != player)
		{
			if (owner.getJailed()== false)
			{
			owner.getAccount().deposit(player.getAccount().withdraw(getRent()));
			GUI.setBalance(player.getName(), player.getAccount().getBalance());
			GUI.setBalance(owner.getName(), owner.getAccount().getBalance());
			}
			else{
				GUI.showMessage("The owner is jailed so you pass through without paying him money");
			}
		}

		/**
		 * If the players balance is too low, when buying a field - the GUI shows
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
	
	public int getMaxNumberOfHouses()
	{
		return this.MAX_HOUSES;
	}
	
	public int getMaxNumberOfHotels()
	{
		return this.MAX_HOTELS;
	}
	
	public String[] getPossibleBuildings()
	{
//		int maximumNumberOfBuildings = this.MAX_HOUSES + this.MAX_HOTELS;
		
		//If they already have the maximum number of hotels we return null
		if(this.hotels == this.MAX_HOTELS)
			return null;
		
		//Get possible houses
		int numberOfHousesLeft = this.MAX_HOUSES - this.houses;
		String[] possibleHouses = new String[numberOfHousesLeft];
		
		int w = 0;
		for(int i = numberOfHousesLeft; i >= 1; i--)
		{
			possibleHouses[w] = "Houses: " + (this.MAX_HOUSES - i +1);
			w++;
		}
		
		//Get possible Hotels
		int numberOfHotelsLeft = this.MAX_HOTELS - this.hotels;
		String[] possibleHotels = new String[numberOfHotelsLeft];
		
		w = 0;
		for(int i = numberOfHotelsLeft; i >= 1; i--)
		{
			possibleHotels[w] = "Hotel: " + (this.MAX_HOTELS - i + 1);
			w++;
		}
		
		return concatString(possibleHouses, possibleHotels);
	}
	
	public int getNumberOfFieldsInGroup(Territory territory, Field[] allFields)
	{
		int i = 0;
		for(Field field : allFields)
		{
			if(field instanceof Territory && ((Territory) field).getGroupName().equals(territory.getGroupName()))
				i++;
		}
		
		return i; //finder antal grønne felter
	}
	
	public boolean isTerritoryBuildable(Territory territory, Field[] playerFields, Field[] allFields)
	{
		int i = 0;
		for(Field field : playerFields)
		{
			if(		field instanceof Territory
					&& ((Territory) field).getGroupName().equals(territory.getGroupName())
			)
				i++;
		}
		
		if(i == getNumberOfFieldsInGroup(territory, allFields))
			return true;
		else
			return false;
	}
	
	public String getGroupName()
	{
		return this.groupName;
	}
	
	public void buyBuildings(String buildingType, int numberOfBuildings, Player player)
	{
		Account account = player.getAccount();
		if(buildingType == "Hotel")
		{
			int hotelPrice = 	((this.MAX_HOUSES - this.getNumberOfHouses()) * this.HOUSE_PRICE)
							+	(this.HOTEL_PRICE * numberOfBuildings);
			
			if(hotelPrice >= account.getBalance())
			{
				GUI.showMessage("You can't afford to buy a hotel");
			} else
			{
				account.withdraw(hotelPrice);
				this.hotels = numberOfBuildings;
				this.houses = this.MAX_HOUSES; 
				GUI.setHotel(this.fieldID+1, true);
			}
			
		} else if(buildingType == "House") {
			int housePrice = HOUSE_PRICE * (numberOfBuildings - houses);
			
			if(housePrice >= account.getBalance())
			{
				GUI.showMessage("You can't afford to buy a house");
			} else 
			{
				account.withdraw(housePrice);
				this.houses = numberOfBuildings;
				GUI.setHouses(this.fieldID+1, this.houses);
			}
		}
		
		GUI.setBalance(player.getName(), player.getAccount().getBalance());
	}
	
	public String[] concatString(String[] a, String[] b)
	{
		int aLen = a.length;
		int bLen = b.length;
		String[] c= new String[aLen+bLen];
		System.arraycopy(a, 0, c, 0, aLen);
		System.arraycopy(b, 0, c, aLen, bLen);
		return c;
	}

}
