package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import desktop_resources.GUI;
import entities.fields.collection.FieldCollection;
import game.Dice;
import game.Player;

public class TaxTest {

	

	private Player Bob;
	private FieldCollection fields;
	
	

	@Before
	public void setUp() throws Exception {
		
		fields = new FieldCollection();
		//		fields.initialize();
		fields.getFieldByName("Pay Tax");
	
		
		this.Bob = new Player("Bob", 10000);
		
		GUI.addPlayer(this.Bob.getName(), Bob.getAccount().getBalance());
		
		
	}
	@Test
	public void Pay10p() {
	
		fields.getFieldByName("Pay Tax").landOnField(Bob);
		assertEquals(9000,Bob.getAccount().getBalance());
	}

	@Test
	public void Pay4000() {
	
		fields.getFieldByName("Pay Tax").landOnField(Bob);
		assertEquals(6000,Bob.getAccount().getBalance());
	}
	
	
	
}
