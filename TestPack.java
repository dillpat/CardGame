import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPack {
    Pack pack;

    @Before
    public void setUp() {
        try {
            pack = new Pack(4);
            pack.readFile("testPack.txt");  
        } catch(InvalidPackException e) {
            e.printStackTrace();
        }
    }


    @After
    public void tearDown() {
        pack = null;
    }
    
    @Test
    public void testReadFile() {
        assertEquals(4, pack.cards.size());
    }
    
    @Test
    public void testReadEmptyFile() {
        try {
            Pack testPack = new Pack(4);
            testPack.readFile("notapack.txt");
            assertTrue(false);
        } catch(InvalidPackException e) {
            assertTrue(true);
        }
    }
    
    @Test
    public void testGetCard() {
        assertEquals(1, pack.getCard().readValue());
    }

    @Test
    public void testBadFileName() {
        try {
            Pack testPack = new Pack(4);
            testPack.readFile("notapack.txt");
            assertTrue(false);
        } catch(InvalidPackException e) {
            assertTrue(true);
        }
    }
}
