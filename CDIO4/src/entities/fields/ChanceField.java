package entities.fields;

import desktop_resources.GUI;
import entities.fields.abstracts.Field;
import game.Player;
import java.util.Random;
import game.ChanceCards;

public class ChanceField extends Field {

	// private String [] description = {
	// "You've won 100 in the lottery",
	// "You've won 200 in the lottery",
	// "You got a ticket for driving and snapping, pay 100",
	// "You drive too fast, get a speeding ticket, pay 300"
	// };
	// private int [] lottery = {
	// 100, 200, 100, 300
	// };
	//

	public ChanceField(String name, String description, int fieldID) {
		super(name, description, fieldID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void landOnField(Player player) {
		if (GUI.getUserButtonPressed("Draw a Card", "Draw") != null){
		}
			
	}

	// TODO Auto-generated method stub
	// if (GUI.getUserLeftButtonPressed("Draw a card", "Yes", null)){
	// int rnd = new Random().nextInt(description.length);
	// GUI.setChanceCard(description[rnd]);
	// switch (rnd) {
	// case 0 : player.getAccount().deposit(lottery[0]);
	// break;
	// case 1 : player.getAccount().deposit(lottery[1]);
	// break;
	// case 2 : player.getAccount().withdraw(lottery[2]);
	// break;
	// case 3 : player.getAccount().withdraw(lottery[3]);
	// break;
	//
	// }
	// System.out.println(rnd);
	// GUI.setBalance(player.getName(), player.getAccount().getBalance());
	// System.out.println(player.getAccount().getBalance());
	// GUI.displayChanceCard();
	// }
	//

	@Override
	public Player getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOwned(boolean bool) {
		// TODO Auto-generated method stub

	}

}
