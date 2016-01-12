package game;

public class Player
{
	private Account account;
	private String name;
	private int position;
	private int fieldsOwned;
	private boolean alive;
	private int laborCampsOwned;
	private int fleetsOwned;
	private boolean jailed;

	public Player(String x, int a)
	{
		name = x;
		account = new Account(a);
		position = 0;
		alive = true;
		fieldsOwned = 0;
		setLaborCampsOwned(0);
		jailed = false;
	}

	public String getName()
	{
		return name;
	}

	public Account getAccount()
	{
		return account;
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int x)
	{
		position = x;
	}

	public void addFieldsOwned()
	{
		fieldsOwned++;
	}
	
	public void addFleetsOwned() 
	{
		fleetsOwned++;
	}

	public int getFieldsOwned()
	{
		return fieldsOwned;
	}
	
	public int getFleetsOwned()
	{
		return fleetsOwned;
	}

	public boolean isAlive()
	{
		return alive;
	}

	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}

	public int getLaborCampsOwned()
	{
		return laborCampsOwned;
	}

	public void setLaborCampsOwned(int laborCampsOwned)
	{
		this.laborCampsOwned = laborCampsOwned;
	}
	public boolean getJailed() {
		return jailed;
	}
	public void setJailed(boolean jailed) {
		this.jailed = jailed;
	}
}
