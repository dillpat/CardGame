package cardGames;

public class Player {
    private int playerNumber;
    private Card[] hand;
    private int cardCounter;
    private Deck pullDeck;
    private Deck putDeck;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        this.hand = new Card[4];
        this.cardCounter = 0;
    }
    
    public void setPullDeck(Deck deck) {
    	this.pullDeck = deck;
    }
    
    public void setPutDeck(Deck deck) {
    	this.putDeck = deck;
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }
    
    public void addCardToHand(Card card) {
    	if (cardCounter < 4) {
    		this.hand[cardCounter] = card;
    		this.cardCounter++;
    	}
    }
 
/*
    private Card[] generateHand() {
        Card[] playerHand = new Card[4];

        for (int i = 0; i < 4; i++) {
            playerHand[i] = this.pack.getCard();
        }

        return playerHand;
    }
*/

    public void printHand() {
        // just for testing, can be removed in final version
        for (Card c : hand) {
            System.out.print(String.valueOf(c.readValue()) + " ");
        }
        System.out.println("");
    }
}