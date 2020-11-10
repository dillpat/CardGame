import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class TestDeck {

    @Test
    public void testGetDeckNumber(){
        Deck myDeck = new Deck(4);
        int deckNumber = myDeck.getDeckNumber();
        assertEquals(4, deckNumber);
    }
    
    @Test
    public void testAddCard() {
        Deck myDeck = new Deck(4);
        myDeck.addCard(new Card(3));
        myDeck.addCard(new Card(4));
        String myDeckString = "";
        int size = myDeck.getDeck().size();
        for (int i=0; i<size; i++) {
            myDeckString += myDeck.getDeck().remove().readValue() + " ";
        }

        assertTrue("3 4 ".equals(myDeckString));
    }
    
    @Test
    public void testGetCard() {
        Deck myDeck = new Deck(4);
        myDeck.addCard(new Card(3));
        myDeck.addCard(new Card(4));
        Card card = myDeck.getCard();
        assertEquals(3, card.readValue());
        assertTrue(card instanceof Card);
    }
    
}