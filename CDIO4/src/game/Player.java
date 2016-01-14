package game;

public class Player
{
	private Account account;
	private String name;
	private int position;
	private boolean alive;
	private boolean jailed;
	private int timeJailed;

	public Player(String x, int a)
	{
		name = x;
		account = new Account(a);
		position = 0;
		alive = true;
		jailed = false;
		timeJailed = 0;
		
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

	public boolean isAlive()
	{
		return alive;
	}

	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}

	public boolean getJailed() {
		return jailed;
	}
	public void setJailed(boolean jailed) {
		this.jailed = jailed;
	}
	
	public int getTimeJailed(){
		return timeJailed;
	}
	
	public void setTimeJailed(int timeJailed){
		this.timeJailed = timeJailed;
	}
}
