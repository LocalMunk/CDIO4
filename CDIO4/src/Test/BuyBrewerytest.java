package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import desktop_resources.GUI;
import entities.fields.collection.FieldCollection;
import entities.fields.ownable.Brewery;
import game.Dice;
import game.Player;

public class BuyBrewerytest {

	
	private Player Bob, Niels;
	private FieldCollection fields;
	private Dice dice ;
	

	@Before
	public void setUp() throws Exception {
		dice = new Dice(6);
		fields = new FieldCollection();
		//		fields.initialize();
		this.dice = new Dice(6);
		this.Bob = new Player("Bob", 10000);
		this.Niels = new Player("Niels", 10000);
		GUI.addPlayer(this.Bob.getName(), Bob.getAccount().getBalance());
		GUI.addPlayer(this.Niels.getName(), Niels.getAccount().getBalance());
		
	}
	@Test
	public void test() {
		((Brewery) fields.getFieldByName("Tuborg")).landOnField(Bob);
		assertTrue(((Brewery) fields.getFieldByName("Tuborg")).getOwner().equals(Bob));
	}

}
