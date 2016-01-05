package Test;

import game.Dice;

import game.Player;
import game.controllers.GameBoard;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entities.fields.Brewery;
import entities.fields.Refuge;
import entities.fields.abstracts.Ownable;
import entities.fields.ownable.LaborCamp;
import Fields.Refuge;
>>>>>>> 55faa3ee7a20ae435203cf56ba78d8770efe71c7

public class LandOnFieldLaborCamp
{
	private Player player;
	private Player player2;
	private Player player3;

	private Brewery Laborcamp;

	@Before

	public void setUp() throws Exception
	{
		// Sets up 3 new players with diffrent balance and Name
		this.player = new Player("Henrik", 5000, true);
		this.player2 = new Player("Bob", 5000, true);
		this.player3 = new Player("Bo,", 200, true);
		this.Laborcamp = new Brewery(2500, 2);

	}

	@Test

	public void testEntities()
	{

		this.player = new Player("Henrik", 5000, true);

		// Tests that our 3 playr entities is entities of the Player class
		// Tests the same in regard to Labor camp;
		Assert.assertNotNull(this.player);
		Assert.assertNotNull(this.player2);
		Assert.assertNotNull(this.player3);
		Assert.assertNotNull(this.Laborcamp);
		Assert.assertTrue(this.Laborcamp instanceof Brewery);
	}

	@Test

	public void testLandOnFieldLaborCamp()
	{

		int expected = 5000;

		int actual = this.player.getAccount().getBalance();

		Assert.assertEquals(expected, actual);

		// Tests the LandOnField method from LaborCamp

		this.Laborcamp.landOnField(this.player);

		expected = 2500;

		actual = this.player.getAccount().getBalance();

		Assert.assertEquals(expected, actual);
		// Tests that the player is actually the owner of the field he just
		// bought
		Assert.assertEquals(player, Laborcamp.getOwner());

	}

	@Test
	public void testInsufficientFunds()
	{
		// Tests that player 3 is not able to buy the field//
		this.Laborcamp.landOnField(this.player3);
		Assert.assertNotEquals(player3, this.Laborcamp.getOwner());

	}

	@Test
	public void testPlayer2landOnField()
	{
		// Testing that the players blance is as expected

		int expected = 5000;
		int actual = this.player.getAccount().getBalance();
		Assert.assertEquals(expected, actual);
		int expected2 = 5000;
		int actual1 = this.player2.getAccount().getBalance();
		Assert.assertEquals(expected2, actual);

		this.Laborcamp.landOnField(this.player);

		this.Laborcamp.landOnField(this.player2);

		Assert.assertTrue(actual1 < 5000);

	}

}
