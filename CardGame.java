import java.io.IOException;
import java.util.Scanner;

//import org.junit.Test;

public class CardGame {

    public static Player[] players;
    public static Deck[] decks;

	public CardGame() {
		// TODO Auto-generated constructor stub
	}
	
	//@Test
	//public void testCardGame() throws IOException {
    public static void main(String[] args) {
        
        // Ask the user for the number of players
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of players:");
        int numPlayers = sc.nextInt();
        
        
        // Loads and validate card pack
        Pack pack = new Pack();
        try {
            pack.loadPack();
        } catch (IOException e) {
            System.out.println("Pack not recognised");
        }
        
        // Generate an array of players and an array of decks of 
        // the correct size
        players = new Player[numPlayers];
        decks = new Deck[numPlayers];

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
            Deck d = decks[i % numPlayers];
            d.addCard(pack.getCard());
        }
        for (Deck d : decks) {
            d.printDeck();
        }
        
        // Create the player circle/ring
        for (int i = 0; i < numPlayers; i++) {
            // Assign players pull deck
            players[i].setPullDeck(decks[i]);

            // Assign players put deck
            if (i == numPlayers - 1) {
                // Last player puts cards to first players deck
                players[i].setPutDeck(decks[0]);
            } else {
                players[i].setPutDeck(decks[i + 1]);
            }
        }
        
        // Play the game
        Boolean winnerFound = false;
        while (winnerFound == false) {
            for (Player player : players) {
                System.out.print(player.getPlayerNumber());
                player.printHand();
                if (player.checkWin()) {
                    winnerFound = true;
                    System.out.println("Winner is: " + String.valueOf(player.getPlayerNumber()));
                    break;
                } else {
                    player.drawCard();
                }
            }
        }
        
        System.out.println("Finish Main Function");
	}

}
