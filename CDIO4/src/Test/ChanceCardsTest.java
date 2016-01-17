package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import desktop_resources.GUI;
import entities.ChanceCards.ChanceCardCollection;
import entities.fields.ChanceField;
import entities.fields.collection.FieldCollection;
import game.Dice;
import game.Player;

public class ChanceCardsTest {

	public ChanceCardCollection collection;
	private Player Bob;
	private FieldCollection fields;
	private Dice dice ;
	private ChanceField field;
	private int moneyLastRound = 10000;
	private int moneyDifference = 0;

	@Before
	public void setUp() throws Exception {
		this.fields = new FieldCollection();
		collection = new ChanceCardCollection();
		field = new ChanceField("Chance", "lelelelrl", 2);
//		fields.initialize();
		this.dice = new Dice(6);
		this.Bob = new Player("Bob", 10000);
		GUI.addPlayer(this.Bob.getName(), Bob.getAccount().getBalance());
		GUI.addPlayer("BobLastRound", 10000);
		GUI.addPlayer("Money Difference", 0);

	}
	@Test
	public void test() {
		GUI.setBalance("BobLastRound", Bob.getAccount().getBalance());
		field.landOnField(Bob);
		GUI.setBalance(Bob.getName(), Bob.getAccount().getBalance());
		moneyDifference = Bob.getAccount().getBalance() - moneyLastRound;
		GUI.setBalance("Money Difference", moneyDifference);
		assertEquals(Bob.getAccount().getBalance(), moneyLastRound + moneyDifference);
	}
}
