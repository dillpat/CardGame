public class Player {
    private int playerNumber;
    private Card[] hand;
    private int cardCounter;
    private int cardTracker;
    private Deck pullDeck;
    private Deck putDeck;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        this.hand = new Card[4];
        this.cardCounter = 0;
        this.cardTracker = 0;
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

    public void drawCard() {
        Card newCard = this.pullDeck.getCard();
        int discardLocation = findDiscardCardLocation(playerNumber);
        if (discardLocation > -1) {
        	Card discard = hand[discardLocation];
            hand[discardLocation] = newCard;
            discardCard(discard);
        }
        else {
        	this.pullDeck.addCard(newCard);
        }
    }

    public void discardCard(Card card) {
        this.putDeck.addCard(card);
    }

    public int findDiscardCardLocation(int playerNumber) {    	
    	int swapCard = cardTracker;
        for (int i = 0; i < 4; i++) {
            Card card = hand[swapCard];
            if (card.readValue() != playerNumber) {
            	cardTracker = (swapCard + 1) % 4;
                return swapCard;
            }
            swapCard = (swapCard + 1) % 4;
        }
        return -1;
    }

    public boolean checkWin() {
        int target = hand[0].readValue();
        for (int i = 1; i < 4; i++) {
            if (hand[i].readValue() != target) {
                return false;
            }
        }
        return true;
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
