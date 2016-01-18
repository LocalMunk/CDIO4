package Test;

import static org.junit.Assert.*;

import game.Player;
import entities.fields.GoToJail;
import entities.fields.StartField;
import entities.fields.collection.FieldCollection;
import entities.fields.ownable.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import desktop_resources.GUI;

public class PassStart {
	
	private Player Bob;
	private FieldCollection fields;
	
	@Before
	public void setUp() throws Exception {
		this.Bob = new Player("Bob", 20000);
	}
	
	@After
	public void tearDown() throws Exception {
		this.Bob = null;
	}
	
	@Test
	public void testStartMoney() {	// Tests that a player gets 2000 startmoney when passing the start field
		assertEquals(20000,Bob.getAccount().getBalance()); // Bob should have 20000 
		StartField.getStartMoney(Bob);
		assertEquals(22000,Bob.getAccount().getBalance()); // Bob should now have 22000 because he has passed the StartField
	}
	
}
