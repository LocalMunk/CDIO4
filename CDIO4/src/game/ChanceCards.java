package game;

public class ChanceCards {

	private int value; 
	private String description;
	
	
	public ChanceCards(String desc, int val){
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

	
