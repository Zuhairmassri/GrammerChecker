package proj5;

/**
 * The ThesaurusNode class represents a node in the thesaurus data structure
 * It contains a key word and a linked list of synonyms associated with that word
 * The class implements the Comparable interface to support comparison based on the key word
 *
 * It provides methods for adding synonyms, retrieving synonyms, checking if a word is a synonym,
 * and getting the number of synonyms
 *
 * The class also overrides the toString() method to provide a string representation of the node
 *
 * @author Zuhair AlMassri
 * @version 05/30/23
 */
public class ThesaurusNode implements Comparable<ThesaurusNode> {
    public final String key;
    private final LinkedList<String> synonyms;


    /**
     * Constructs a ThesaurusNode object with the specified key.
     *
     * @param key the key word for the node
     */
    public ThesaurusNode(String key) {
        this.key = key;
        this.synonyms = new LinkedList<>();
    }

    /**
     * Adds a synonym to the node's synonym list.
     *
     * @param toAdd the synonym to add
     */
    public void addSynonym(String toAdd) {
        synonyms.insertAtTail(toAdd);
    }

    /**
     * Retrieves the synonym at the specified index.
     *
     * @param index the index of the synonym to retrieve
     * @return the synonym at the specified index
     */
    public String getSynonym(int index) {
        return synonyms.get(index);
    }

    /**
     * Returns the linked list of synonyms for the node.
     *
     * @return the linked list of synonyms
     */
    public LinkedList<String> getSynonymList(){
        return synonyms;
    }

    /**
     * Returns a string representation of the ThesaurusNode.
     *
     * @return a string representation of the node
     */
    public String toString() {
        return key;
    }

    /**
     * Checks if a word is a synonym for the node.
     *
     * @param toCheck the word to check
     * @return true if the word is a synonym, false otherwise
     */
    public boolean isSynonym(String toCheck) {
        return synonyms.contains(toCheck);
    }

    /**
     * Returns the number of synonyms for the node.
     *
     * @return the number of synonyms
     */
    public int numOfSynonyms(){
        return this.synonyms.getLength();
    }

    /**
     * Compares the ThesaurusNode with another ThesaurusNode based on the key word.
     *
     * @param toCompare the ThesaurusNode to compare with
     * @return a negative integer, zero, or a positive integer as this node is less than,
     *         equal to, or greater than the specified node
     */
    @Override
    public int compareTo(ThesaurusNode toCompare) {
        return this.key.compareTo(toCompare.key);
    }


    public static void main(String[] args) {
        LineReader read = new LineReader("C:/Users/Zuhai/OneDrive/Desktop/CSC 151/project 5/src/proj5/smallThesaurus.txt", ",");
        String[] words = read.getNextLine();
        ThesaurusNode myNode = new ThesaurusNode(words[0]);
        for(int i = 1; i < words.length; i++){
            myNode.addSynonym(words[i]);
        }
        System.out.println(myNode);
        System.out.println(myNode.isSynonym("WHAT?"));
        System.out.println(myNode.isSynonym("with truth"));
    }

}
