import java.util.LinkedList;
import java.util.Queue;

public class Deck {

    private int deckNumber;  
    private Queue<Card> deck = new LinkedList<>();


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
    public Card getCard() {
        return deck.remove();
    }
    
    
    public void printDeck() {
        // just for testing, can be removed in final version
        for (Card c : deck) {
            System.out.print(String.valueOf(c.readValue()) + " ");
        }
        System.out.println("");
    }
=======
//package cardGame;
import java.util.LinkedList;
import java.util.Queue;

public class Deck {

    private int deckNumber;  
    private Queue<Card> deck = new LinkedList<>();


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
    public Card getCard() {
        return deck.remove();
    }

    public int[] display() {
        int[] values = new int[deck.size()];
        Queue<Card> duplicate = deck;

        for (int i = 0; i < deck.size(); i++) {
            values[i] = duplicate.remove().readValue();
        }
        return values;
    }
    
    public Queue<Card> getDeck() {
        return deck;
    }
    
    public void printDeck() {
        // just for testing, can be removed in final version
        for (Card c : deck) {
            System.out.print(String.valueOf(c.readValue()) + " ");
        }
        System.out.println("");
    }
}