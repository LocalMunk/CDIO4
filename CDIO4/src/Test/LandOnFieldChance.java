package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import desktop_resources.GUI;
import entities.fields.ChanceField;
import game.Player;

public class LandOnFieldChance {

	private ChanceField chance1;
	private Player p2;
	private Player p1;

	@Before
	public void setUp() throws Exception {
		this.p1 = new Player("Henrik", 30000, true);
		this.p2 = new Player("Bob", 10000, true);
		this.chance1 = new ChanceField("Sea Grover","dewrp", 10);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		chance1.landOnField(p1);
		System.out.println(p1.getAccount().getBalance());
		chance1.landOnField(p2);
		System.out.println(p2.getAccount().getBalance());
		GUI.getUserButtonPressed("Exit", "Exit");
	}

}
