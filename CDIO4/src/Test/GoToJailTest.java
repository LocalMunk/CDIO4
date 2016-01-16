package Test;

import entities.fields.GoToJail;
import game.Dice;
import game.Player;
import game.controllers.JailController;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import desktop_resources.GUI;
import entities.fields.collection.FieldCollection;

public class GoToJailTest {

	private Player Bob,Niels;
	private GoToJail tojail;
	private FieldCollection fields;
	private JailController controller;
	private Dice dice ;
	@Before
	public void setUp() throws Exception {
		this.fields = new FieldCollection();

//		fields.initialize();
		this.dice = new Dice(6);
		this.Bob = new Player("Bob", 39475);
		this.Niels = new Player ("Niels",3000);
		GUI.addPlayer(this.Bob.getName(), 39475);
		this.controller = new JailController();
		this.tojail = new GoToJail("To jail", "ja", 30);

	}

	@After
	public void tearDown() throws Exception {
		this.Bob = null;

		this.tojail = null;
		

	}

	@Test
	public void testGoToJail() {
		GUI.getUserButtonPressed("Start", "Move");

		tojail.landOnField(Bob);

		GUI.getUserButtonPressed("Exit test", "Exit");
		assertTrue(Bob.getJailed() == true);
	}
	
	
	

}
