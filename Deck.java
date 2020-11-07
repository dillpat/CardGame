import java.util.LinkedList;

public class Deck {

    private int deckNumber;  
    private LinkedList<Card> deck = new LinkedList<>();


    public Deck(int deckNumber) {
        this.deckNumber = deckNumber;
    }
  
    // return the deck number
    public int getDeckNumber() {
        return deckNumber;
    }
    
    // add a card to the back of the deck
    public void addCard(Card card) {
        deck.addLast(card);
    }

    // get the top card from the deck
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