import java.util.LinkedList;

public class Deck {

    private int deckNumber;
    private Pack pack;
    private LinkedList<Card> deck = new LinkedList<>();


    public Deck(int deckNumber, Pack pack) {
        this.deckNumber = deckNumber;
        this.pack = pack;
    }
    
    public void addCard(Card card) {
        deck.addLast(card);
    }

    public Card getCard() {
        return deck.removeFirst();
    }

    public void printDeck() {
        System.out.println(deck);
    }

}