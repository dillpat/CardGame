import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Queue;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class TestDeck {
    
    private Deck myDeck;
    
    @Before
    public void setUp() {
        myDeck = new Deck(4);
    }
    
    @After
    public void tearDown() {
        myDeck = null;
    }
    
    @Test
    public void testGetDeckNumber(){
        int deckNumber = myDeck.getDeckNumber();
        assertEquals(4, deckNumber);
    }
    
    @Test
    public void testAddCard() {
        try {
            myDeck.addCard(new Card(3));
            myDeck.addCard(new Card(4));
            String myDeckString = "";
            int size = myDeck.getDeck().size();
            for (int i=0; i<size; i++) {
                myDeckString += myDeck.getDeck().remove().readValue() + " ";
            }
    
            assertTrue("3 4 ".equals(myDeckString));
        } catch (InvalidCardException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetCard() {
        try {
            myDeck.addCard(new Card(3));
            myDeck.addCard(new Card(4));
            Card card = myDeck.getCard();
            assertEquals(3, card.readValue());
            assertTrue(card instanceof Card);
        } catch (InvalidCardException | NoSuchElementException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testEmptyGetCard() {
        try {
            myDeck.getCard();
            assertTrue(false);
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }
}