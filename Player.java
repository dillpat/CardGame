public class Player extends Thread {
	private int playerNumber;
	private Card[] hand;
	private int cardCounter;
	private int cardTracker;
	private Deck pullDeck;
	private Deck putDeck;
	private Winner winnerId;

	// Constructor. Initialise attributes 
	public Player(int playerNumber, Winner winnerId) {
		this.playerNumber = playerNumber;
		this.hand = new Card[4];
		this.cardCounter = 0;
		this.cardTracker = 0;
		this.winnerId = winnerId;
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
	public boolean drawCard() throws InterruptedException {
		// get a card from the pull deck
		Card newCard;
		while (true) {
			newCard = this.pullDeck.getCard();
			if (newCard == null) {
				if (winnerId.getWinnerId() != 0) {
					return false;
				}
			}
			else {
				break;
			}
		}
		int value = newCard.readValue();

		// log that the player has drawn
		System.out.println("Player " + String.valueOf(playerNumber) + " draws a " + String.valueOf(value)
		+ " from deck " + String.valueOf(playerNumber));

		int discardLocation = findDiscardCardLocation();
		if (discardLocation > -1) {
			Card discard = hand[discardLocation];
			hand[discardLocation] = newCard;
			discardCard(discard);
		}
		else {
			this.pullDeck.addCard(newCard);
		}
		return true;
	}

	// discard a given card
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

	public void run() {
		// Play the game
		boolean playGame = true;
		while (playGame) {
			// first check if player win, draw card if not
			if (this.checkWin()) {
				if (winnerId.getWinnerId() == 0) {
					winnerId.setWinnerId(this.playerNumber);
					System.out.println("Winner is: " + String.valueOf(this.getPlayerNumber()));
				}
				return;
			}
			else {
				try {
					if (winnerId.getWinnerId() != 0) {
						break;
					}
					playGame = this.drawCard();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}