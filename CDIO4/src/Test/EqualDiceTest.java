package Test;

import entities.fields.GoToJail;
import game.Player;
import game.controllers.GameController;
import game.Dice;
import game.Turn;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import desktop_resources.GUI;
import entities.fields.collection.FieldCollection;
import entities.fields.ownable.Brewery;

public class EqualDiceTest {
	
	private Player Bob;
	private Player Niels;
	private GoToJail tojail;
	private FieldCollection fields;
	private Dice dice;
	private Dice dice2;
	private Turn turn;
	
	@Before
	public void setUp() throws Exception {
		fields = new FieldCollection();
		//		fields.initialize();
		this.dice = new Dice(1);
		this.dice2 = new Dice(1); // The dice now only have 1 side so they will always be equal
		this.Bob = new Player("Bob", 10000);
		this.Niels = new Player("Niels", 10000);
		GUI.addPlayer(this.Bob.getName(), Bob.getAccount().getBalance());
		GUI.addPlayer(this.Niels.getName(), Niels.getAccount().getBalance());
	}
	
	@After
	public void tearDown() throws Exception {
		this.Niels = null;
		this.Bob = null;
		GUI.close();
		fields = null;
	}
	
	@Test
	public void testEqualDice(){
		int tur, tur2;
		tur = turn.getCheck();
		dice.roll();
		assertTrue(dice.getEquals());
		tur2 = turn.getCheck();
		assertTrue(tur==tur2);
		
		
		
		
		
		//tur2 = turn.getCheck();
		//assertTrue(tur==tur2);
	}

}
