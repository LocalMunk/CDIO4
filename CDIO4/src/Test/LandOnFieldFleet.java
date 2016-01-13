package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entities.fields.collection.FieldCollection;
import game.Player;

public class LandOnFieldFleet
{

	private Player p1 = new Player("Henrik",0);
	private Player p2;
	int[] fleetRent = new int[] {500, 1000, 2000, 4000};
	FieldCollection fieldCollection = new FieldCollection();
	
	@Before
	public void setUp() throws Exception
	{
		
		this.p2 = new Player("Bob", 5000);
		fieldCollection.initialize();
	}
	
	@After
	public void tearDown() throws Exception {
		p2 = null;
		fieldCollection = null;
	}

	@Test
	public void testLandOnFleet1() { //Test that a player pays 500 gold for landing on a single owned fleet
		fieldCollection.getField(5).setOwner(p1);
		fieldCollection.getField(5).landOnField(p2);
		Assert.assertEquals(4500, p2.getAccount().getBalance());
		Assert.assertEquals(500, p1.getAccount().getBalance());
		
	}
	@Test
	public void testLandOnFleet2() { //Test rent is 1000 gold if 2 fleets are owned
		fieldCollection.getField(5).setOwner(p1);
		fieldCollection.getField(14).setOwner(p1);
		fieldCollection.getField(14).landOnField(p2);
		Assert.assertEquals(4000, p2.getAccount().getBalance());
		Assert.assertEquals(1000, p1.getAccount().getBalance());
	}
	@Test
	public void testLandOnFleet3() { //Test rent is 2000 gold if 3 fleets are owned
		fieldCollection.getField(5).setOwner(p1);
		fieldCollection.getField(14).setOwner(p1);
		fieldCollection.getField(25).setOwner(p1);
		fieldCollection.getField(25).landOnField(p2);
		Assert.assertEquals(3000, p2.getAccount().getBalance());
		Assert.assertEquals(2000, p1.getAccount().getBalance());
	}
	@Test
	public void testLandOnFleet4() { //Test rent is 4000 gold if 4 fleets are owned
		fieldCollection.getField(5).setOwner(p1);
		fieldCollection.getField(14).setOwner(p1);
		fieldCollection.getField(25).setOwner(p1);
		fieldCollection.getField(34).setOwner(p1);
		fieldCollection.getField(34).landOnField(p2);
		Assert.assertEquals(1000, p2.getAccount().getBalance());
		Assert.assertEquals(4000, p1.getAccount().getBalance());
	}
}