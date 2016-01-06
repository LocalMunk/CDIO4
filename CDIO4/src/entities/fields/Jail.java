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
	public void setOwned(boolean bool) {
		// TODO Auto-generated method stub
		
	}
// add metode til at være i fængsel
}
