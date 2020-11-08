import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;


public class Deck {

    private int deckNumber;  
    private LinkedBlockingDeque<Card> deck = new LinkedBlockingDeque<>();


    public Deck(int deckNumber) {
        this.deckNumber = deckNumber;
    }
  
    // return the deck number
    public int getDeckNumber() {
        return deckNumber;
    }
    
    // add a card to the back of the deck
    public void addCard(Card card) {
        deck.add(card);
    }

    // get the top card from the deck
    public Card getCard() throws InterruptedException {
    	return deck.poll(10, TimeUnit.MILLISECONDS);
    }

    public void printDeck() {
    	// just for testing, can be removed in final version
    	for (Card c : deck) {
    		System.out.print(String.valueOf(c.readValue()) + " ");
    	}
        System.out.println("");
    }
}