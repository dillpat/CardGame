import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
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
        String[] filenames = { "CardGameFiles\\player1_output.txt", "CardGameFiles\\player2_output.txt",
                "CardGameFiles\\player3_output.txt", "CardGameFiles\\player4_output.txt" };

        // Generate the player decks and add them to the list
        for (int i = 0; i < numPlayers; i++) {
            Deck newDeck = new Deck(i + 1);
            decks[i] = newDeck;
        }

        // Generate the players and add them to the list
        for (int i = 0; i < numPlayers; i++) {
            try {
                FileWriter playerWriter = new FileWriter(filenames[i]);
                Player newPlayer = new Player(i + 1, filenames[i], playerWriter);
                players[i] = newPlayer;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Distribute 4 cards to each player using the round robin method
        for (int i = 0; i < numPlayers * 4; i++) {
            Player p = players[i % numPlayers];
            p.addCardToHand(pack.getCard());
        }

        int restOfCards = pack.cards.size();
        // Distribute remaining cards to the decks using round robin method
        for (int i = 0; i < restOfCards; i++) {
            Deck d = decks[i % numPlayers];
            d.addCard(pack.getCard());
        }

        // Create the player circle/ring
        for (int i = 0; i < numPlayers; i++) {
            // Assign players pull deck
            Player p = players[i];
            p.setPullDeck(decks[i]);
            p.setPutDeck(decks[(i + 1) % numPlayers]);
            try {
                p.getWriter().write("Initial Hand: " + p.toString() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // print out all the players' hands
        for (Player player : players) {
            System.out.print(player.getPlayerNumber() + ": ");
            player.printHand();
        }

        // print out all the decks
        for (Deck deck : decks) {
            System.out.print(deck.getDeckNumber() + ": ");
            deck.printDeck();
        }

        Thread[] threads = new Thread[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            Thread t = new Thread(players[i]);
            threads[i] = t;
            t.start();
        }

        boolean winnerFound = false;
        while (!winnerFound) {
            for (Player p : players) {
                if (p.checkWin()) {
                    System.out.println("Player " + p.getPlayerNumber() + " wins.");
                    for (Player p1 : players) {
                        p1.setWinnerFound(true);
                    }
                    break;
                } else {
                    for (Player p1 : players) {
                        synchronized (p1) {
                            p1.notify();
                        }
                    }
                }
            }            
        }


        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Play the game
        /*
        Boolean winnerFound = false;
        Player winner = null;
        
        // while nobody has won
        while (winnerFound == false) {
            // each player check fir a win, and draws a card if not
            for (Player player : players) {
                decks[player.getPlayerNumber() - 1].printDeck();
                if (player.checkWin()) {
                    winnerFound = true;
                    winner = player;
                    try {
                        player.getWriter().write("Winner is: " + String.valueOf(player.getPlayerNumber() + "\n"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                } else {
                    player.drawCard();
                }
            }
        }*/

        for (Player player : players) {
            try {
                FileWriter w = player.getWriter();
                w.write(player.getPlayerNumber() + " Final Hand: " + player.toString() + "\n");
                w.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}