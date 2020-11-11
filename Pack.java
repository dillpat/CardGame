import java.util.LinkedList;
import java.util.Queue;
import java.util.NoSuchElementException;
import java.util.Random; // Only imported temporarily as a substitue for getting the card values from a file
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class Pack {
	Queue<Card> cards = new LinkedList<>();
	private int length;	
	
	public Pack(int num) {
		this.length = num * 8;
	}
	
	// get the name of the file to read and read it
	public void loadPack() throws IOException, InvalidPackException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter location of pack to load: ");
		String filename = sc.nextLine();
		this.readFile(filename);
	}
	
    // load the contents of the text file into the pack
	public void readFile(String filename) throws InvalidPackException {
		try {
			File myObj = new File(filename);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				try {
					int cardValue = myReader.nextInt();
					Card newCard = new Card(cardValue);
					this.cards.add(newCard);
				} catch (NoSuchElementException | InvalidCardException e) {
					if (e instanceof InvalidCardException) {
						throw new InvalidPackException();
					} else {
						break;
					}
				}
			}
			if (cards.size() != length) {
				throw new InvalidPackException();
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("The given file name was not recognised");
			throw new InvalidPackException();
		}
	}
	
	// get a card from the top of the pack
	public Card getCard() throws NoSuchElementException{
		if (cards.size() > 0) {
			Card card = cards.remove();
			return card;
		} else {
			throw new NoSuchElementException();
		}
		
	}
	
	// add a card to the back of the pack
	public void addCard(Card card) {
		this.cards.add(card);
	}

}