public class Player {
    private int playerNumber;
    private Card[] hand;
    private int cardCounter;
    private Deck pullDeck;
    private Deck putDeck;

    // Constructor. Initialise attributes 
    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        this.hand = new Card[4];
        this.cardCounter = 0;
    }
    
    // Set the deck that the player will draw from
    public void setPullDeck(Deck deck) {
        this.pullDeck = deck;
    }
    
    // Set the deck that the player will discard too
    public void setPutDeck(Deck deck) {
    	this.putDeck = deck;
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    // Draw a card from the deck
    public void drawCard() {

        // get a card from the pull deck
        Card newCard = this.pullDeck.getCard();
        int value = newCard.readValue();

        // log that the player has drawn
        System.out.println("Player " + String.valueOf(playerNumber) + " draws a " + String.valueOf(value)
                + " from deck " + String.valueOf(playerNumber));

        // get the card to be discarded
        int discardLocation = findDiscardCardLocation();
        Card discard = hand[discardLocation];
        hand[discardLocation] = newCard;

        // discard it
        discardCard(discard);
    }

    // dsicard a given card
    public void discardCard(Card card) {
        // put it in the discard deck
        this.putDeck.addCard(card);

        // log that it is discarded
        System.out.println("Player " + String.valueOf(playerNumber) + " discards a " + String.valueOf(card.readValue())
                + " to deck " + String.valueOf(putDeck.getDeckNumber()));
    }

    // get the put deck
    public Deck getPutDeck() {
        return putDeck;
    }

    // find the location of the first card to be discarded
    public int findDiscardCardLocation() {
        for (int i = 0; i < 4; i++) {
            Card card = hand[i];
            if (card.readValue() != playerNumber) {
                return i;
            }
        }
        return -1;
    }

    // check the players hand and see if they have won
    public boolean checkWin() {
        int target = hand[0].readValue();
        for (int i = 1; i < 4; i++) {
            if (hand[i].readValue() != target) {
                return false;
            }
        }
        return true;
    }
    
    // add a card to the players hand
    public void addCardToHand(Card card) {
    	if (cardCounter < 4) {
    		this.hand[cardCounter] = card;
    		this.cardCounter++;
    	}
    }

    
    public void printHand() {
        // just for testing, can be removed in final version
        for (Card c : hand) {
            System.out.print(String.valueOf(c.readValue()) + " ");
        }
        System.out.println("");
    }
}