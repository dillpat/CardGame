import java.util.LinkedList;
import java.util.Random; // Only imported temporarily as a substitue for getting the card values from a file

public class Pack {
	LinkedList<Card> cards = new LinkedList<>();
	
    public Pack(int num) {
		// get cards from text file
		Random rand = new Random();
		for (int i = 0; i < 8*num; i++) {
			int random = (int) (rand.nextDouble()*num);
			Card newCard = new Card(random);
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