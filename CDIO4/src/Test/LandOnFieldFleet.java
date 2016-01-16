package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entities.fields.collection.FieldCollection;
import entities.fields.ownable.Brewery;
import entities.fields.ownable.Fleet;
import game.Player;

public class LandOnFieldFleet {

	private Player p1 = new Player("Henrik", 5000);
	private Player p2;
	int[] fleetRent = new int[] { 500, 1000, 2000, 4000 };
	FieldCollection fieldCollection = new FieldCollection();
	Fleet fleet1 = new Fleet("Fleet1", "Desc", 5, 400, fleetRent, fieldCollection);

	@Before
	public void setUp() throws Exception {

		this.p2 = new Player("Bob", 5000);
	}

	@After
	public void tearDown() throws Exception {
		p2 = null;
		fieldCollection = null;
	}

	@Test
	public void testLandOnFieldBuy() { // Tests the landOnField method when
										// buying a Fleet.
		fieldCollection.getField(5).landOnField(p1);
		Assert.assertEquals(((Fleet) fieldCollection.getField(5)).getOwner(), p1);
		Assert.assertEquals(1000, p1.getAccount().getBalance());
	}

	@Test
	public void testLandOnFieldNoMoney() { // Tests that it you cannot buy a
											// field with low money
		p1 = new Player("Frank", 1000);
		fieldCollection.getField(5).landOnField(p1);
		Assert.assertEquals(((Fleet) fieldCollection.getField(5)).getOwner(), null);
	}

	@Test
	public void testLandOnFleet1() { // Test that a player pays 500 gold for
										// landing on a single owned fleet
		((Fleet) fieldCollection.getField(5)).setOwner(p1);
		fieldCollection.getField(5).landOnField(p2);
		Assert.assertEquals(4500, p2.getAccount().getBalance());
		Assert.assertEquals(5500, p1.getAccount().getBalance());

	}

	@Test
	public void testLandOnFleet2() { // Test rent is 1000 gold if 2 fleets are
										// owned
		((Fleet) fieldCollection.getField(5)).setOwner(p1);
		((Fleet) fieldCollection.getField(14)).setOwner(p1);
		fieldCollection.getField(14).landOnField(p2);
		Assert.assertEquals(4000, p2.getAccount().getBalance());
		Assert.assertEquals(6000, p1.getAccount().getBalance());
	}

	@Test
	public void testLandOnFleet3() { // Test rent is 2000 gold if 3 fleets are
										// owned
		((Fleet) fieldCollection.getField(5)).setOwner(p1);
		((Fleet) fieldCollection.getField(14)).setOwner(p1);
		((Fleet) fieldCollection.getField(25)).setOwner(p1);
		fieldCollection.getField(25).landOnField(p2);
		Assert.assertEquals(3000, p2.getAccount().getBalance());
		Assert.assertEquals(7000, p1.getAccount().getBalance());
	}

	@Test
	public void testLandOnFleet4() { // Test rent is 4000 gold if 4 fleets are
										// owned
		((Fleet) fieldCollection.getField(5)).setOwner(p1);
		((Fleet) fieldCollection.getField(14)).setOwner(p1);
		((Fleet) fieldCollection.getField(25)).setOwner(p1);
		((Fleet) fieldCollection.getField(34)).setOwner(p1);
		fieldCollection.getField(34).landOnField(p2);
		Assert.assertEquals(1000, p2.getAccount().getBalance());
		Assert.assertEquals(9000, p1.getAccount().getBalance());
	}

	@Test
	public void testLandOnFieldOwnerJailed() { // Tests that if owner is jailed,
												// no rent is taken
		((Fleet) fieldCollection.getField(14)).setOwner(p1);
		p1.setJailed(true);
		fieldCollection.getField(14).landOnField(p2);
		Assert.assertEquals(5000, p2.getAccount().getBalance());
		Assert.assertEquals(5000, p1.getAccount().getBalance());
	}

}