package proj5;

/**
 * Represents a node in the word counter.
 */
public class WordNode implements Comparable<WordNode> {
    private final String word;
    private int count;

    /**
     * Constructs a WordNode with the specified word and an initial count of 1.
     *
     * @param word the word represented by this node
     */
    public WordNode(String word) {
        this.word = word;
        this.count = 1;
    }

    /**
     * Increments the count of this word node.
     */
    public void incrementCount() {
        this.count++;
    }

    /**
     * Compares this WordNode with another WordNode based on their word strings.
     *
     * @param other the other WordNode to compare
     * @return a negative integer if this word comes before the other word in lexicographic order,
     *         zero if the words are equal, or a positive integer if this word comes after the other word
     */
    @Override
    public int compareTo(WordNode other) {
        return this.word.compareTo(other.word);
    }

    /**
     * Returns the count of this word node.
     *
     * @return the count of this word node
     */
    public int getCount() {
        return count;
    }

    /**
     * Returns the string representation of this word node.
     *
     * @return the word string of this node
     */
    public String toString() {
        return word;
    }
}
