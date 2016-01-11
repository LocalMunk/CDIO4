package entities.fields;

import desktop_resources.GUI;
import entities.fields.abstracts.Field;
import game.Player;

public class GoToJail extends Field {

	public GoToJail(String name, String description, int fieldID) {
		super(name, description, fieldID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void landOnField(Player player) {
		if (GUI.getUserButtonPressed("You're going to jail", "Okay") != null){
			player.setPosition(12);
			player.setJailed(true);
			GUI.removeAllCars(player.getName());
			GUI.setCar(12, player.getName());
			}
		
	}

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
