import java.util.LinkedList;

public class Deck {

    private int deckNumber;  
    //private Pack pack;
    private LinkedList<Card> deck = new LinkedList<>();


    public Deck(int deckNumber) {
        this.deckNumber = deckNumber;
    }
    public int getDeckNumber() {
    	return deckNumber;
    }
    
    public void addCard(Card card) {
        deck.addLast(card);
    }

    public Card getCard() {
        return deck.removeFirst();
    }
    
    public void printDeck() {
        // just for testing, can be removed in final version
        for (Card c : deck) {
            System.out.print(String.valueOf(c.readValue()) + " ");
        }
        System.out.println("");
    }
}
