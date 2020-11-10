import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Deck {

    private int deckNumber;  
    private ConcurrentLinkedQueue<Card> deck = new ConcurrentLinkedQueue<>();


    public Deck(int deckNumber) {
        this.deckNumber = deckNumber;
    }
    
    public ConcurrentLinkedQueue<Card> getDeck() {
        return deck;
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
    public Card getCard() {
        return deck.poll();
    }
    
    
    public void printDeck() {
        // just for testing, can be removed in final version
        for (Card c : deck) {
            System.out.print(String.valueOf(c.readValue()) + " ");
        }
        System.out.println("");
    }
}