package cardGames;

import java.util.LinkedList;
import java.util.Random; // Only imported temporarily as a substitue for getting the card values from a file

public class Pack {
	LinkedList<Card> cards = new LinkedList<>();
	
	// test data delete after 
	private int[] test = new int[] {1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4};
	
	
	public Pack() {
	}
	
    public Pack(int num) {
		// get cards from text file
		//Random rand = new Random();
		for (int i = 0; i < test.length; i++) {
			//int random = (int) (rand.nextDouble()*num);
			Card newCard = new Card(test[i]);
			this.cards.add(newCard);
		}
		
	}
	
	public Card getCard() {
		Card card = cards.removeFirst();
		return card;
	}
	
	public boolean addCard(Card card) {
		return false;
	}
}