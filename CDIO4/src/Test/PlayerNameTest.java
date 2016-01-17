package Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import game.Player;
import game.controllers.MarketPlaceController;

public class PlayerNameTest
{
	private MarketPlaceController marketPlace;
	private Player[] players;
	
	@Before
	public void setUp() 
	{
		this.marketPlace = new MarketPlaceController();
		
		this.players = new Player[]
		{
			new Player("Seb", 30000),
			new Player("Alexander", 30000),
			new Player("Sara", 30000),
			new Player("Kristian", 30000),
			new Player("Martin", 30000)
		};
		
	}
	
	@Test
	public void testGetPlayerNamesExcept()
	{
		Assert.assertArrayEquals(new String[] {
				"Alexander", "Sara", "Kristian", "Martin"
			},
			marketPlace.getPlayerNamesExcept(players, players[0])
		);
	}
	
	@Test
	public void testGetPlayerNames()
	{
		String[] names = marketPlace.getPlayerNames(players);
		
		Assert.assertArrayEquals(
		new String[]
		{
			"Seb", "Alexander", "Sara", "Kristian", "Martin"
		}
		, names);
		
	}
	
	@Test
	public void testGetPlayerByName()
	{
		Assert.assertEquals(players[0], marketPlace.getPlayerByName("Seb", players));
		Assert.assertEquals(players[1], marketPlace.getPlayerByName("Alexander", players));
		
		Assert.assertEquals(players[4], marketPlace.getPlayerByName("Martin", players));
	}

}
