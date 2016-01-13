package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entities.fields.collection.FieldCollection;
import entities.fields.ownable.Fleet;
import game.Player;

public class LandOnFieldFleet
{

	private Player p1 = new Player("Henrik", 30000);
	private Player p2;
	private Fleet fleet1;
	private Fleet fleet2;
	private Fleet fleet3;
	private Fleet fleet4;

	@Before

	public void setUp() throws Exception
	{
		FieldCollection fieldCollection = new FieldCollection();
		int[] fleetRent = new int[] {400, 500, 600, 700};
		this.p2 = new Player("Bob", 1000);
		this.fleet1 = new Fleet("F�rste","Fleet",14,4000 , fleetRent, fieldCollection);
		this.fleet2= new Fleet("Anden","Fleet",15,4000 , fleetRent, fieldCollection);
		this.fleet3 = new Fleet("Tredje","Fleet",16,4000 , fleetRent, fieldCollection);
		this.fleet4 = new Fleet("Fjerde","Fleet",17,4000 , fleetRent, fieldCollection);

	}
	
	@After
	public void tearDown() throws Exception {
		p2 = null;
	}

	@Test
	public void testLandOnFleet1() { //Test that a player pays 400 gold for landing on a single owned fleet
		fleet1.setOwner(p1);
		fleet1.landOnField(p2);
		Assert.assertEquals(600, p2.getAccount().getBalance());
		
	}
	@Test
	public void testLandOnFleet2() { //Test rent is 500 gold if 2 fleets are owned
		fleet2.setOwner(p1);
		fleet2.landOnField(p2);
		Assert.assertEquals(500, p2.getAccount().getBalance());
	}
	@Test
	public void testLandOnFleet3() { //Test rent is 600 gold if 3 fleets are owned
		fleet3.setOwner(p1);
		fleet3.landOnField(p2);
		Assert.assertEquals(400, p2.getAccount().getBalance());
	}
	@Test
	public void testLandOnFleet4() { //Test rent is 700 gold if 4 fleets are owned
		fleet4.setOwner(p1);
		fleet4.landOnField(p2);
		Assert.assertEquals(300, p2.getAccount().getBalance());
	}
}