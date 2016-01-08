package entities.ChanceCards;

public class ChanceCard {

	private int value; 
	private String description;
	
	
	public ChanceCard(String desc, int val){
		this.description = desc;
		this.value = val;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getDescription(){
		return this.description;
	}
	
}
