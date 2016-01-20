package entities.ChanceCards;

import entities.fields.*;

public class ChanceCardCollection
{
	private ChanceCard[] chances;
	public ChanceCardCollection() {
		chances = new ChanceCard[10];
		chances[0] = new ChanceCard("You've won 100 in the lottery", 100);
		chances[1] = new ChanceCard("You've won 200 in the lottery", 200);
		chances[2] = new ChanceCard("The queen is visiting the city and she gave you, 250", 250);
		chances[3] = new ChanceCard("You sell some lemonade, 300", 300);
		chances[4] = new ChanceCard("You see an old lady drop some money, you pick'em up, 500", 500);
		chances[5] = new ChanceCard("You got a ticket for driving and snapping, pay 100", 100);
		chances[6] = new ChanceCard("You drive too fast, get a speeding ticket, pay 300", 300);
		chances[7] = new ChanceCard("Pay your dentist bill, 500", 500);
		chances[8] = new ChanceCard("Your car is broken, pay the repair bill, 200", 200);
		chances[9] = new ChanceCard("Shoe sale, you went crazy and spent 250", 250);
	}
		
	public ChanceCard[] getCardList() //returneger listen
	{
		return chances;
	}
	
	public ChanceCard getCard(int index)// returnerer et kort
	{
		return chances[index];
	}
}
