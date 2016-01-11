package Test;

import entities.fields.GoToJail;
import game.Player;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import desktop_resources.GUI;
import entities.fields.collection.FieldCollection;

public class GoToJailTest {

	private Player Rasmus;
	private GoToJail tojail;
	private FieldCollection fields;

	@Before
	public void setUp() throws Exception {
		this.fields = new FieldCollection();

		fields.initialize();

		this.Rasmus = new Player("Rasmus", 39475, true);

		GUI.addPlayer(this.Rasmus.getName(), 39475);

		this.tojail = new GoToJail("To jail", "ja", 30);

	}

	@After
	public void tearDown() throws Exception {
		this.Rasmus = null;

		this.tojail = null;

	}

	@Test
	public void testGoToJail() {
		GUI.getUserButtonPressed("Start", "Move");

		tojail.landOnField(Rasmus);

		GUI.getUserButtonPressed("Exit test", "Exit");

	}

}
