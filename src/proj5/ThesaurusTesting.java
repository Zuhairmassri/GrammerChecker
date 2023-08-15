package proj5;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class ThesaurusTesting {

    @Rule
    public Timeout timeout = Timeout.millis(100);

    private Thesaurus myT;

    @Before
    public void setUp() throws Exception {
        myT = new Thesaurus();
    }

    @After
    public void tearDown() throws Exception {
        myT = null;
    }

    @Test
    public void testInsert(){
        String entry = "happy";
        String[] syns0 = {};
        myT.insert(entry, syns0);
        assertEquals("happy - {}\n", myT.toString());
        String[] syns = {"glad", "content", "joyful", "potato"};
        String[] syns1 = {"he"};
        myT.insert(entry, syns);
        assertEquals("happy - {glad, content, joyful, potato}\n", myT.toString());
        myT.insert(entry, syns1);
        assertEquals("happy - {glad, content, joyful, potato, he}\n", myT.toString());
        myT.insert(entry, syns);
        assertEquals("happy - {glad, content, joyful, potato, he}\n", myT.toString());
    }

    @Test
    public void testDelete(){
        String entry = "happy";
        String[] syns = {"glad", "content", "joyful", "potato"};
        myT.insert(entry, syns);
        assertEquals("happy - {glad, content, joyful, potato}\n", myT.toString());
        myT.delete(entry);
        assertEquals("", myT.toString());
        myT.delete(entry);
        assertEquals("", myT.toString());
        myT.insert(entry, syns);
    }

    @Test
    public void testContainsEntry(){
        String entry = "happy";
        String[] syns = {"glad", "content", "joyful", "potato"};
        myT.insert(entry, syns);
        assertTrue(myT.containsEntry(entry));
        myT.delete(entry);
        assertFalse(myT.containsEntry(entry));
    }
    @Test
    public void testGetAllSynonyms(){
        String entry = "happy";
        String[] syns = {"glad", "content", "joyful", "potato"};
        myT.insert(entry, syns);
        assertEquals("(glad, content, joyful, potato)", myT.getAllSynonyms(entry).toString());
        String entry1 = "mad";
        String[] syns1 = {"furious", "angry"};
        myT.insert(entry1, syns1);
        assertEquals("(furious, angry)", myT.getAllSynonyms(entry1).toString());
    }

    @Test
    public void testGetSynonymFor_ValidEntryWithMultipleSynonyms() {
        String entry = "happy";
        String synonym1 = "glad";
        String synonym2 = "content";
        String synonym3 = "joyful";
        myT.insert(entry, new String[]{synonym1, synonym2, synonym3});
        String randomSynonym = myT.getSynonymFor(entry);
        assertTrue(randomSynonym.equals(synonym1) || randomSynonym.equals(synonym2) || randomSynonym.equals(synonym3));
    }

    @Test
    public void testGetSynonymFor_InvalidEntry() {
        String entry = "unknown";
        String randomSynonym = myT.getSynonymFor(entry);
        assertEquals("", randomSynonym);
    }

    @Test
    public void testGetSynonymFor_ValidEntryWithNoSynonyms() {
        String entry = "car";
        String randomSynonym = myT.getSynonymFor(entry);
        assertEquals("", randomSynonym);
    }

    @Test
    public void testGetSynonymFor_ValidEntryWithSingleSynonym() {
        String entry = "sad";
        String[] synonyms = {"unhappy"};
        myT.insert(entry, synonyms);
        String randomSynonym = myT.getSynonymFor(entry);
        assertEquals(synonyms[0], randomSynonym);
    }




}
