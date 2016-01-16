package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import desktop_resources.GUI;
import entities.ChanceCards.ChanceCardCollection;
import entities.fields.ChanceField;
import entities.fields.collection.FieldCollection;
import entities.fields.ownable.Brewery;
import game.Dice;
import game.Player;

public class Brewerytest {

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
		((Brewery) fields.getFieldByName("Tuborg")).setOwner(Bob);
	}
	@Test
	public void test() {
		for(int i = 0; i < 100; i++){
			int a = ((Brewery) fields.getFieldByName("Tuborg")).getRent();
			System.out.println(a);
			assertTrue(a >= 200 && a <= 1200);
		}
		((Brewery) fields.getFieldByName("Coca-Cola")).setOwner(Bob);
		
		for(int i = 0; i < 100; i++){
			int a = ((Brewery) fields.getFieldByName("Tuborg")).getRent();
			System.out.println(a);
			assertTrue(a >= 400 && a <= 2400);
		}
	}
}
