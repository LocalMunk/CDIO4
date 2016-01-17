package Test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entities.fields.abstracts.Field;
import entities.fields.collection.FieldCollection;
import entities.fields.ownable.Territory;
import game.Player;


public class MarketPlaceTest
{

	private FieldCollection fields;
	private Player seb;
	private Player sara;
	
	@Before
	public void setUp() 
	{
		this.fields = new FieldCollection();
		this.seb = new Player("Seb", 30000);
		this.sara = new Player("Sara", 30000);
		
	}
	
	
	@Test
	public void testBuildingChoices()
	{
		((Territory) fields.getField(1)).setOwner(seb);
		((Territory) fields.getField(3)).setOwner(seb);
		Territory chosenField = ((Territory) fields.getField(3));
		chosenField.buyBuildings("House", 3, seb);
		String[] choices = chosenField.getPossibleBuildings();
		String[] expected = new String[] { "Houses: 4", "Hotel: 1" };
		Assert.assertArrayEquals(expected, choices);
		
		((Territory) fields.getField(1)).setOwner(sara);
		chosenField.setOwner(sara);
		chosenField.buyBuildings("Hotel", 1, seb);
		choices = chosenField.getPossibleBuildings();
		expected = null;
		Assert.assertArrayEquals(expected, choices);
	}
	
	@Test
	public void testGetOwnedTerritoryBuildable()
	{
		((Territory) fields.getField(1)).setOwner(seb);
		((Territory) fields.getField(3)).setOwner(seb);
		((Territory) fields.getField(6)).setOwner(seb);
		((Territory) fields.getField(8)).setOwner(seb);
		
		Field[] expected = new Field[] { fields.getField(1), fields.getField(3) };
		Field[] actual = fields.getOwnedTerritoryBuildable(seb);
		
		Assert.assertArrayEquals(expected, actual);
		
		((Territory) fields.getField(9)).setOwner(seb);
		expected = new Field[] { fields.getField(1), fields.getField(3), fields.getField(6), fields.getField(8), fields.getField(9) };
		actual = fields.getOwnedTerritoryBuildable(seb);
		
		Assert.assertArrayEquals(expected, actual);
	}
	
	
}
