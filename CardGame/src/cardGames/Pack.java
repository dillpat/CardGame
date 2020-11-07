package cardGames;

import java.util.LinkedList;
import java.util.Random; // Only imported temporarily as a substitue for getting the card values from a file
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;
	  
	  
public class Pack {
	LinkedList<Card> cards = new LinkedList<>();
	
	// test data delete after 
	private int[] test = new int[] {1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4};
	
    public Pack(int num) {
		// get cards from text file
		//Random rand = new Random();
		for (int i = 0; i < test.length; i++) {
			//int random = (int) (rand.nextDouble()*num);
			Card newCard = new Card(test[i]);
			this.cards.add(newCard);
		}	
	}
    
    public Pack() {
    	
    }
    
    public void loadPack() throws IOException{
    	Scanner sc = new Scanner(System.in);
        System.out.println("Please enter location of pack to load: ");
        String filename = sc.nextLine();
        System.out.println("test1");
    	this.readFile(filename);
    }
    
    public void readFile(String filename) {
    	try {
			File myObj = new File(filename);
			//System.out.println("Test2");
			Scanner myReader = new Scanner(myObj);
			//System.out.println("Test3");
			while (myReader.hasNextLine()) {
				int cardValue = myReader.nextInt();
				System.out.println(cardValue);
				Card newCard = new Card(cardValue);
				this.cards.add(newCard);
			}
			System.out.println("abc");
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
    	System.out.println("xyz" + this.cards);
    }
	
	public Card getCard() {
		Card card = cards.removeFirst();
		return card;
	}
	
	public boolean addCard(Card card) {
		return false;
	}

}