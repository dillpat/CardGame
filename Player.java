import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Player implements Runnable {
    private int playerNumber;
    private Card[] hand = new Card[4];
    private int cardCounter = 0;
    private Deck pullDeck;
    private Deck putDeck;
    private String filename;
    private FileWriter writer;
    private Boolean winnerFound = false;

    // Constructor. Initialise attributes 

    public Player(int playerNumber, String filename, FileWriter writer) {
        this.playerNumber = playerNumber;
        this.filename = filename;
        this.writer = writer;
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
        Card newCard;
        int value;
        try {
            newCard = this.pullDeck.getCard();   		
            value = newCard.readValue();
        } catch (NoSuchElementException e) {
            newCard = null;
            return;
        }

        // log that the player has drawn
        try {
            writer.write("Player " + String.valueOf(playerNumber) + " draws a " + String.valueOf(value)
                    + " from deck " + String.valueOf(playerNumber) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        try {
            writer.write("Player " + String.valueOf(playerNumber) + " discards a " + String.valueOf(card.readValue())
                    + " to deck " + String.valueOf(putDeck.getDeckNumber()) + "\n");
            writer.write("Player " + String.valueOf(playerNumber) + " current hand: " + toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // get the put deck
    public Deck getPutDeck() {
        return putDeck;
    }

    // find the location of a random card to be discarded
    public int findDiscardCardLocation() {
        Random rand = new Random();
        List<Integer> values = new ArrayList<Integer>();

        for (int i = 0; i < 4; i++) {
            Card card = hand[i];
            if (card.readValue() != playerNumber) {
                values.add(i);
            }
        }
        int position = rand.nextInt(values.size());
        return values.get(position);
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

    public String getFilename() {
        return filename;
    }

    public FileWriter getWriter() {
        return writer;
    }

    public String toString() {
        String handString = "";
        for (Card c : hand) {
            handString += String.valueOf(c.readValue()) + " ";
        }
        return handString;
    }
    
    public void printHand() {
        // just for testing, can be removed in final version
        for (Card c : hand) {
            System.out.print(String.valueOf(c.readValue()) + " ");
        }
        System.out.println("");
    }

    public void setWinnerFound(Boolean winnerFound) {
        this.winnerFound = winnerFound;
    }

    @Override
    public void run() {
        while (!this.winnerFound) {
            synchronized (this) {
                try {
                    if (checkWin()) {
                        winnerFound = true;
                        writer.write("Player " + playerNumber + " wins\n");
                        break;
                    } else if (this.winnerFound) {
                        break;
                    }
                    drawCard();
                    // wait for the 

                    this.wait();

                } catch (InterruptedException | IOException e) {
                    break;
                }
            }
        }

        try {
            writer.write("Player " + playerNumber + " exits\n");
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
}

