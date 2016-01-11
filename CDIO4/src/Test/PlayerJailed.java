package Test;

import static org.junit.Assert.*;

import game.Player;
import entities.fields.GoToJail;
import entities.fields.collection.FieldCollection;
import entities.fields.ownable.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import desktop_resources.GUI;

public class PlayerJailed {

	private Player Rasmus;
	private Player Søren;
	private FieldCollection fields;

	@Before
	public void setUp() throws Exception {
		fields = new FieldCollection();
		fields.initialize();
		this.Rasmus = new Player("Rasmus", 20000, true);
		this.Søren = new Player("Søren", 20000, true);
		GUI.addPlayer("Rasmus", 20000);
		GUI.addPlayer("Søren", 20000);

	}

	@After
	public void tearDown() throws Exception {
		this.Rasmus = null;
		this.Søren = null;
		GUI.close();
		fields = null;
		

	}


	
	
	
	
	
	
	@Test
	public void testBrewery() {
		fields.getField(28).landOnField(Søren);
		Søren.setJailed(true);
		fields.getField(28).landOnField(Rasmus);
		GUI.setCar(29, this.Rasmus.getName());
		assertEquals(17000, Søren.getAccount().getBalance());
		assertEquals(20000, Rasmus.getAccount().getBalance());

	}

	@Test
	public void testTerritory() {
		fields.getField(31).landOnField(Søren);
		Søren.setJailed(true);
		fields.getField(31).landOnField(Rasmus);
		GUI.setCar(32, this.Rasmus.getName());
		assertEquals(14000, Søren.getAccount().getBalance());
		assertEquals(20000, Rasmus.getAccount().getBalance());

	}

	@Test
	public void testFleet() {
		fields.getField(14).landOnField(Søren);
		Søren.setJailed(true);
		fields.getField(14).landOnField(Rasmus);
		GUI.setCar(15, this.Rasmus.getName());
		assertEquals(16000, Søren.getAccount().getBalance());
		assertEquals(20000, Rasmus.getAccount().getBalance());

	}

}
