package Test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import desktop_resources.GUI;
import entities.fields.collection.FieldCollection;
import entities.fields.ownable.Territory;
import game.Player;

public class LandOnFieldTerritory {
	private FieldCollection fields;
	private Player Seb;
	private Player Sara;

	@Before
	public void setUp() throws Exception {
		this.fields = new FieldCollection();
		this.Seb = new Player("Seb", 30000);
		GUI.addPlayer(this.Seb.getName(), Seb.getAccount().getBalance());
		this.Sara = new Player("Sara", 30000);
		GUI.addPlayer(this.Sara.getName(), Sara.getAccount().getBalance());

	}

	@After
	public void tearDown() throws Exception {
		fields = null;
		this.Seb = null;
		this.Sara = null;
		GUI.close();

	}

	@Test
	public void testLandOnTerritoryNoMoney() { // Test that you do not get the
												// option to buy a Territory
												// when your balance is lower
												// than its price
		Sara = new Player("Sara", 1000);
		fields.getField(6).landOnField(Sara);
		Assert.assertEquals(null, ((Territory) fields.getField(6)).getOwner());
	}

	@Test
	public void testBuyTerritoryField() { // Test that you can buy a Territory
		((Territory) fields.getFieldByName("Rådhuspladsen")).landOnField(Seb);
		Assert.assertEquals(Seb, ((Territory) fields.getFieldByName("Rådhuspladsen")).getOwner());
		Assert.assertEquals(22000, Seb.getAccount().getBalance());

	}

	@Test
	public void testPayRent() { // Test that when a Territory is owned by
								// another player rent is paid. (1000 for field
								// 39)
		((Territory) fields.getField(39)).setOwner(Seb);
		((Territory) fields.getField(39)).landOnField(Sara);
		Assert.assertEquals(29000, Sara.getAccount().getBalance());
	}

	@Test
	public void testLandOnFieldOwnerJailed() { // Test that no rent is paid if
												// owner is jailed
		((Territory) fields.getField(39)).setOwner(Seb);
		Seb.setJailed(true);
		((Territory) fields.getField(39)).landOnField(Sara);
		Assert.assertEquals(30000, Sara.getAccount().getBalance());
		Assert.assertEquals(30000, Seb.getAccount().getBalance());

	}

	@Test
	public void testBuyHouse() { // That the if you own all associated
									// Territories that you can buy a house
									// (price = 500) and that the rent is
									// increased.
		((Territory) fields.getField(1)).setOwner(Seb);
		((Territory) fields.getField(3)).setOwner(Seb);
		((Territory) fields.getField(3)).buyBuildings("House", 1, Seb);
		Assert.assertEquals(29500, Seb.getAccount().getBalance());
		Assert.assertEquals(1, ((Territory) fields.getField(3)).getNumberOfHouses());
		((Territory) fields.getField(3)).landOnField(Sara);
		Assert.assertEquals(29750, Sara.getAccount().getBalance());

	}

	@Test
	public void testBuy2Houses() { // That the if you own all associated
									// Territories that you can buy 2 houses
									// (price = 1000) and that the rent is
									// increased.
		((Territory) fields.getField(1)).setOwner(Seb);
		((Territory) fields.getField(3)).setOwner(Seb);
		((Territory) fields.getField(3)).buyBuildings("House", 2, Seb);
		Assert.assertEquals(29000, Seb.getAccount().getBalance());
		Assert.assertEquals(2, ((Territory) fields.getField(3)).getNumberOfHouses());
		((Territory) fields.getField(3)).landOnField(Sara);
		Assert.assertEquals(29250, Sara.getAccount().getBalance());
	}

	@Test
	public void testBuy3Houses() { // That the if you own all associated
									// Territories that you can buy 3 houses
									// (price = 1500) and that the rent is
									// increased.
		((Territory) fields.getField(1)).setOwner(Seb);
		((Territory) fields.getField(3)).setOwner(Seb);
		((Territory) fields.getField(3)).buyBuildings("House", 3, Seb);
		Assert.assertEquals(28500, Seb.getAccount().getBalance());
		Assert.assertEquals(3, ((Territory) fields.getField(3)).getNumberOfHouses());
		((Territory) fields.getField(3)).landOnField(Sara);
		Assert.assertEquals(27750, Sara.getAccount().getBalance());
	}

	@Test
	public void testBuy4Houses() { // That the if you own all associated
									// Territories that you can buy 4 houses
									// (price = 2000) and that the rent is
									// increased.
		((Territory) fields.getField(1)).setOwner(Seb);
		((Territory) fields.getField(3)).setOwner(Seb);
		((Territory) fields.getField(3)).buyBuildings("House", 4, Seb);
		Assert.assertEquals(28000, Seb.getAccount().getBalance());
		Assert.assertEquals(4, ((Territory) fields.getField(3)).getNumberOfHouses());
		((Territory) fields.getField(3)).landOnField(Sara);
		Assert.assertEquals(26000, Sara.getAccount().getBalance());
	}

	@Test
	public void testBuyHotel() { // That the if you own all associated
									// Territories that you can buy a hotel
									// (price = 4000, when no houses are owned)
									// and that the rent is increased.
		((Territory) fields.getField(1)).setOwner(Seb);
		((Territory) fields.getField(3)).setOwner(Seb);
		((Territory) fields.getField(3)).buyBuildings("Hotel", 1, Seb);
		Assert.assertEquals(26000, Seb.getAccount().getBalance());
		Assert.assertEquals(1, ((Territory) fields.getField(3)).getNumberOfHotels());
		((Territory) fields.getField(3)).landOnField(Sara);
		Assert.assertEquals(24000, Sara.getAccount().getBalance());
	}
}