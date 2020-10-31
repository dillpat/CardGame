package cardGames;
import java.util.Scanner;

import org.junit.Test;

public class CardGame {

    public static Player[] players;
    public static Deck[] decks;

	public CardGame() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testCardGame() {
    //public static void main(String[] args) {
        
        // ask the user for the number of players
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of players:");
        int numPlayers = sc.nextInt();
        sc.close();

        // generate an array of players and an array of decks of 
        // the correct size
        players = new Player[numPlayers];
        decks = new Deck[numPlayers];

        Pack pack = new Pack(numPlayers);

        // Generate the player decks and add them to the list
        for (int i = 0; i < numPlayers; i++) {
            Deck newDeck = new Deck(i);
            decks[i] = newDeck;
        }

        // Generate the players and add them to the list
        for (int i = 0; i < numPlayers; i++) {
            Player newPlayer = new Player(i);
            players[i] = newPlayer;
        }

        // Distribute 4 cards to each player using the round robin method
        for (int i = 0; i < numPlayers*4; i++) {
        	Player p = players[i%numPlayers];
        	p.addCardToHand(pack.getCard());        			
        }
        
        // Distribute remaining cards to the decks using round robin method
        for (int i = 0; i < pack.cards.size(); i++) {
        	Deck d = decks[i%numPlayers];
        	d.addCard(pack.getCard());
        } 
        
        // Create the player circle/ring
        for (int i = 0; i<numPlayers; i++) {
        	// Assign players pull deck
        	players[i].setPullDeck(decks[i]);
        	
        	// Assign players put deck
        	if (i == numPlayers-1) {
        		// Last player puts cards to first players deck
        		players[i].setPutDeck(decks[0]);
        	}
        	else {
        		players[i].setPutDeck(decks[i+1]);
        	}
        }
        System.out.println("Finish Main Function");
	}

}