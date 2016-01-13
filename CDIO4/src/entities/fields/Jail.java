package entities.fields;

import entities.fields.abstracts.Field;
import game.Player;

public class Jail extends Field {
	public Jail(String name, String description, int fieldID) {
		super(name, description, fieldID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void landOnField(Player player) {
		// TODO Auto-generated method stub
		
	}

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
