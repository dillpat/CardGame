//package cardGame;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Arrays;


public class CardGame {

    public static Player[] players;
    public static Deck[] decks;

    public CardGame() {
        // TODO Auto-generated constructor stub
    }
    
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
            	String fileLocation = new File("").getAbsolutePath();
            	String filename = fileLocation + "\\CardGameFiles\\player" + (i+1) + "_output.txt";
                FileWriter playerWriter = new FileWriter(filename);
                Player newPlayer = new Player(i + 1, filename, playerWriter);
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

        // create the player threads
        Thread[] threads = new Thread[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            Thread t = new Thread(players[i]);
            threads[i] = t;
            t.start();
        }

        // while there is no winner...
        boolean winnerFound = false;
        while (!winnerFound) {
            // check whether each player has won.
            for (Player p : players) {
                if (p.checkWin()) {
                    // if they have, write this to the file, set the winnerFound flag to true...
                    String fileLocation = new File("").getAbsolutePath();
                    try {
                        p.getWriter().write("Player " + p.getPlayerNumber() + " wins.");
                        winnerFound = true;
                        // for each player, write that the player has been informed to stop
                        for (Player p1 : players) {
                            p1.setWinnerFound(true);
                            if (p1 != p) {
                                p1.getWriter().write("Player " + p.getPlayerNumber() + " has informed player "
                                        + p1.getPlayerNumber() + " that player " + p.getPlayerNumber() + " has won." + "\n");
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // interrupt the threads
                    for (Thread t : threads) {
                        t.interrupt();
                    }
                    break;
                } else {
                    // otherwise stop the players from waiting
                    for (Player p1 : players) {
                        synchronized (p1) {
                            p1.notify();
                        }
                    }
                }
            }            
        }


        // join the threads
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // write the players' final hands to the files
        for (Player player : players) {
            try {
                FileWriter w = player.getWriter();
                w.write(player.getPlayerNumber() + " Final Hand: " + player.toString() + "\n");
                w.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // create deck files and write the final state
        String fileLocation = new File("").getAbsolutePath();
        for (Deck d : decks) {
            try {
                String filename = fileLocation + "\\CardGameFiles\\deck" + d.getDeckNumber() + "_ouput.txt";
                FileWriter deckWriter = new FileWriter(filename);
                String deckString = "";
                for (Card c : d.getDeck()) {
                    deckString += c.readValue() + " ";
                }
                deckWriter.write("Deck " + d.getDeckNumber() + " Final Hand: " + deckString);
                deckWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}