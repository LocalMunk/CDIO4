package entities.fields;

import desktop_resources.GUI;
import entities.fields.abstracts.Field;
import game.Player;

public class Tax extends Field
{

	private int taxAmount;
	private int taxRate;

	/**
	 * Creates a tax field, that withdraws money from player
	 * 
	 * @param a
	 *            Tax amount (int)
	 * @param b
	 *            Field name (string)
	 */
	public Tax(String name, String description, int fieldID, int taxAmount, int taxRate)
	{
		super(name, description, fieldID);
		this.taxAmount = taxAmount;
		this.taxRate = taxRate;
	}

	/**
	 * Checks wether the field is == Caravan. The Player is then asked to choose
	 * between 10% of total assets or 4000 It then widraws 10% or 4000 from the
	 * players account
	 */
	@Override
	public void landOnField(Player player)
	{
		if (this.taxRate > 0)
		{
			if (GUI.getUserLeftButtonPressed("Pay 10% of total assets?, or do you want to pay 4000?", "10%", "4000"))
			{
				player.getAccount().withdraw(player.getAccount().getBalance() / taxRate);
			}
			else
			{
				player.getAccount().withdraw(taxAmount);
			}
			/**
			 * If the field is gold mine it withdraws 2000 from the players
			 * account The GUI then updates the balance
			 */
		}
		else
			player.getAccount().withdraw(taxAmount);
		GUI.setBalance(player.getName(), player.getAccount().getBalance());

	}

	/**
	 * Returns the taxamount of a given field
	 * 
	 */
	public int getTaxAmount()
	{
		return taxAmount;
	}

	/**
	 * Sets the taxamount for a given field
	 */
	public void setTaxAmount(int taxAmount)
	{
		this.taxAmount = taxAmount;
	}
	
	/**
	 * Returns the taxrate of a given field
	 * 
	 */
	public int getTaxRate()
	{
		return taxRate;
	}

	/**
	 * Sets the taxrate for a given field
	 */
	public void setTaxRate(int taxRate)
	{
		this.taxRate = taxRate;
	}

	@Override
	public Player getOwner()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
