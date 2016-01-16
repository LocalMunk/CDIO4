package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import desktop_resources.GUI;
import entities.fields.GoToJail;
import entities.fields.collection.FieldCollection;
import game.Dice;
import game.Player;
import game.controllers.JailController;

public class BailJail {

	private Player Rasmus, Søren;
	private GoToJail tojail;
	private FieldCollection fields;
	private JailController controller;
	private Dice dice;

	@Before
	public void setUp() throws Exception {
		this.fields = new FieldCollection();

		// fields.initialize();
		this.dice = new Dice(6);
		this.Rasmus = new Player("Rasmus", 39475);
		this.Søren = new Player("Søren", 3500);
		GUI.addPlayer(this.Rasmus.getName(), 39475);
		this.controller = new JailController();
		this.tojail = new GoToJail("To jail", "ja", 30);

	}

	@After
	public void tearDown() throws Exception {
		this.Rasmus = null;

		this.tojail = null;

	}

	@Test
	public void testPayToBailJail() {

		int expectedBalance = 500;
		tojail.landOnField(Søren);
		controller.handle(Søren, dice);
		assertFalse(Søren.getJailed());
		assertEquals(expectedBalance, Søren.getAccount().getBalance());
	}
	@Test
	public void testBailJail3Rounds() {

		tojail.landOnField(Søren);
		Søren.setTimeJailed(3);
		controller.handle(Søren, dice);
		assertFalse(Søren.getJailed());

	}

}
