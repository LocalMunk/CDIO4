package entities.fields;

import java.util.Random;

import desktop_resources.GUI;
import entities.ChanceCards.ChanceCard;
import entities.ChanceCards.ChanceCardCollection;
import entities.fields.abstracts.Field;
import game.Player;
import game.controllers.GameController;


public class ChanceField extends Field {

	
	private ChanceCardCollection chanceCardCollection;

	public ChanceField(String name, String description, int fieldID) {
		super(name, description, fieldID);
		// TODO Auto-generated constructor stub
	}
	
	public String draw (Player player){
		chanceCardCollection = new ChanceCardCollection();
		int rnd = new Random().nextInt(chanceCardCollection.getCardList().length);
		
		if (rnd <= 4){
			player.getAccount().deposit(chanceCardCollection.getCard(rnd).getValue());
		}
		else {
			player.getAccount().withdraw(chanceCardCollection.getCard(rnd).getValue());
		}
		GUI.displayChanceCard(chanceCardCollection.getCard(rnd).getDescription());
		GUI.setBalance(player.getName(), player.getAccount().getBalance());
		return chanceCardCollection.getCard(rnd).getDescription();
	}

	@Override
	public void landOnField(Player player) {
		if (GUI.getUserButtonPressed("Draw a Card", "Draw") != null){
			draw(player);
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
	public void setOwner(Player player) {
		// TODO Auto-generated method stub
		
	}
}
