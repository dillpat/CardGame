import java.util.Scanner;

public class Player {
    private int playerNumber;
    private Card[] hand;
    private Pack pack;
    private Deck deck;

    public Player(int playerNumber, Pack pack, Deck deck) {
        this.playerNumber = playerNumber;
        this.pack = pack;
        this.deck = deck;
        this.hand = generateHand();
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    private Card[] generateHand() {
        Card[] playerHand = new Card[4];

        for (int i = 0; i < 4; i++) {
            playerHand[i] = this.pack.getCard();
        }

        return playerHand;
    }


    public void printHand() {
        // just for testing, can be removed in final version
        for (Card c : hand) {
            System.out.print(String.valueOf(c.readValue()) + " ");
        }
        System.out.println("");
    }

    
}