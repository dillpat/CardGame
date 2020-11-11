import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestPlayer {
    Player player;
    Deck pullDeck = new Deck(1);
    Deck putDeck = new Deck(2);

    @Before
    public void setUp() {
        String filename = "player1_output.txt";
        try {
            FileWriter writer = new FileWriter(filename);
            player = new Player(1, filename, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.setPutDeck(putDeck);
        player.setPullDeck(pullDeck);
        try {
            for (int i=1; i<5; i++) {
                player.addCardToHand(new Card(i));
            }        
        } catch (InvalidCardException e) {
            e.printStackTrace();
        }
    }
    
    @After
    public void tearDown() {
        player = null;
    }
    
    @Test
    public void testDrawCard() {
        try {
            int length = pullDeck.getDeck().size();
            int length2 = putDeck.getDeck().size();
            pullDeck.addCard(new Card(1));
            player.drawCard();
            assertEquals(length, pullDeck.getDeck().size());
            assertEquals(length2+1, putDeck.getDeck().size());
        } catch (InvalidCardException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testToString() {
        String expectedString = "1 2 3 4 ";
        System.out.println(player.toString());
        assertEquals(expectedString, player.toString());
    }
    
        
    @Test
    public void testAddToFullHand() {
        try {
            player.addCardToHand(new Card(5));
        } catch  (InvalidCardException e) {
            e.printStackTrace();
        }
    }
    
}
