import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CardTest {

    private Card testCard;
    private Card testCard2;
    
    
    @Before
    public void setUp() {
        try {
            testCard = new Card(12);
            testCard2 = new Card(1);
        } catch (InvalidCardException e) {
            e.printStackTrace();
        }
    } 
    
    @After
    public void tearDown () {
        testCard = null;
    }
    
    @Test
    public void testNoCardValue() {
        assertFalse("Has a value ", testCard.readValue() == 0);
        assertTrue("Is negative ", testCard2.readValue() >= 1);
    } 
}