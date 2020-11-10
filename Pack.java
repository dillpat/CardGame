//package cardGame;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random; // Only imported temporarily as a substitue for getting the card values from a file
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//import org.junit.Test;
	  
	  
public class Pack {
	LinkedList<Card> cards = new LinkedList<>();
	
	// test data delete after 
	private int[] test = new int[] { 1, 3, 4, 4, 1, 3, 2, 2, 3, 3, 2, 4, 1, 1, 2, 4, 3, 2, 1, 2, 3, 4, 2, 3, 2, 2, 4, 3,
			2, 1, 2, 3 };
	
	public Pack() {

	}
	
	public Pack(int num) {
		// get cards from text file
		for (int i = 0; i < test.length; i++) {
			Card newCard = new Card(test[i]);
			this.cards.add(newCard);
		}
	}
	
	// get the name of the file to read and read it
	public void loadPack() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter location of pack to load: ");
		String filename = sc.nextLine();
		String fileLocation = new File("").getAbsolutePath();
		filename = fileLocation + "\\Card Packs\\" + filename;
		System.out.println(filename + "filename");
		this.readFile(filename);
	}
	
    // load the contents of the text file into the pack
	public void readFile(String filename) {
		try {
			File myObj = new File(filename);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				try {
					int cardValue = myReader.nextInt();
					Card newCard = new Card(cardValue);
					this.cards.add(newCard);
				} catch (NoSuchElementException e) {
					break;
				}

			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("The given file name was not recognised");

			for (int i = 0; i < test.length; i++) {
				Card newCard = new Card(test[i]);
				this.cards.add(newCard);
			}

		}
	}
	
	// get a card from the top of the pack
	public Card getCard() {
		Card card = cards.removeFirst();
		return card;
	}
	
	// add a card to the back of the pack
	public void addCard(Card card) {
		this.cards.addLast(card);
	}

}