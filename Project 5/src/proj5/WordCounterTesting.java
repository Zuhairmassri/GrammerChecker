package  proj5;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;

public class WordCounterTesting {

    @Rule
    public Timeout timeout = Timeout.millis(100);

    private WordCounter myCounter;

    @Before
    public void setUp() throws Exception {
        myCounter = new WordCounter();
        myCounter.findFrequencies("C:/Users/Zuhai/OneDrive/Desktop/CSC 151/project 5/src/proj5/test.txt");
    }

    @After
    public void tearDown() throws Exception {
        myCounter = null;
    }

    @Test
    public void testGetFrequency(){
        assertEquals(3, myCounter.getFrequency("grungy"));
        assertEquals(4, myCounter.getFrequency("zUhAir"));
        assertEquals(0, myCounter.getFrequency("hi"));
    }

    @Test
    public void testContains(){
        assertTrue(myCounter.contains("gRunGY"));
        assertTrue(myCounter.contains("zuhair"));
        assertFalse(myCounter.contains("potato"));
    }

    @Test
    public void testFindFrequencies(){
        assertEquals(2, myCounter.getFrequency("union"));
        assertEquals(4, myCounter.getFrequency("zuhair"));
        assertEquals(3, myCounter.getFrequency("grungy"));
    }

}
