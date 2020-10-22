import java.util.Scanner;

public class CardGame {

    public static Player[] players;
    public static Deck[] decks;

	public CardGame() {
		// TODO Auto-generated constructor stub
	}

    public static void main(String[] args) {
        
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

        // generate the decks and add them to the list
        for (int i = 0; i < numPlayers; i++) {
            Deck newDeck = new Deck(i, pack);
            decks[i] = newDeck;
        }

        // generate the players and add them to the list
        for (int i = 0; i < numPlayers; i++) {
            Deck playerDeck = decks[i];
            Player newPlayer = new Player(i, pack, playerDeck);
            players[i] = newPlayer;
        }

        int cardsLeft = (pack.cards.size()) / numPlayers;

        for (Deck deck : decks) {
            for (int i = 0; i < cardsLeft; i++) {
                deck.addCard(pack.getCard());
            }
            deck.printDeck();
        }
    
	}

}