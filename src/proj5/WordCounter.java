package proj5;

/**
 * The WordCounter class is responsible for counting the occurrences of words in a given text file.
 */
public class WordCounter {
    private final BinarySearchTree<WordNode> words;
    private final String PUNCTUATION = ".,!?;:";

    /**
     * Constructs a WordCounter object and initializes the binary search tree.
     *
     */
    public WordCounter() {
        words = new BinarySearchTree<>();
    }

    /**
     * Checks if the specified word exists in the word counter.
     *
     * @param toCheck the word to check
     * @return true if the word exists in the counter, false otherwise
     */
    public boolean contains(String toCheck) {
        WordNode toCheckNode = new WordNode(toCheck.toLowerCase());
        return words.contains(toCheckNode);
    }

    /**
     * Adds a new wordNode into the WordCounter BinarySearchTree
     */
    private void addWord(WordNode word) {
        words.insert(word);
    }

    /**
     * Finds the number of occurrences of each word in a provided text file
     * @param file a String of the path of the text file that has the words to be counted
     */
    public void findFrequencies(String file) {
        LineReader reader = new LineReader(file, " ");
        String[] line;
        while ((line = reader.getNextLine()) != null) {
            countWords(line);
        }
    }

    /**
     * Counts the number of occurrences of each given word in the provided String Array
     * @param wordsArray An Array of Strings
     */
    private void countWords(String[] wordsArray) {
        for (String word : wordsArray) {
            word = word.toLowerCase();

            WordNode node;
            boolean isPunctuated = false;
            if (word.length() > 0) {
                char lastCharacter = word.charAt(word.length() - 1);
                isPunctuated = PUNCTUATION.contains(String.valueOf(lastCharacter));
            }
            if (isPunctuated) {
                node = new WordNode(word.substring(0, word.length() - 1));
            } else {
                node = new WordNode(word);
            }
            WordNode existingNode = this.words.getElement(node);
            if (existingNode != null) {
                existingNode.incrementCount();
            } else {
                this.addWord(node);
            }
        }
    }

    /**
     * Returns a string representation of the word counter.
     *
     * @return a string containing the words and their counts in the counter
     */
    public String toString() {
        return inOrderTraversalToString(words.getRoot());
    }

    /**
     * In-order traversal. Goes through the words stored in the words BST and returns a printable String of
     * the key of each word stored in the BST
     * @param node The starting node of in-order traversal
     * @return a printable String of the key of each word stored in the BST
     */
    private String inOrderTraversalToString(BSTNode<WordNode> node) {
        if (node == null) {
            return "";
        }
        String leftSubtree = inOrderTraversalToString(node.llink);
        String rightSubtree = inOrderTraversalToString(node.rlink);

        WordNode wordNode = node.key;
        return leftSubtree + wordNode + ": " + wordNode.getCount() + "\n" + rightSubtree;
    }

    /**
     * Checks the count of the specified word in the counter.
     *
     * @param word the word to check the count for
     * @return the count of the word in the counter, or 0 if the word does not exist
     */
    public int getFrequency(String word) {
        WordNode newNodeLow = new WordNode(word.toLowerCase());
        if (!(words.contains(newNodeLow))) {
            return 0;
        } else {
            return words.getElement(newNodeLow).getCount();
        }
    }

    public static void main(String[] args) {
        WordCounter counter = new WordCounter();
        counter.findFrequencies("C:/Users/Zuhai/OneDrive/Desktop/CSC 151/project 5/src/proj5/test.txt");
        System.out.println(counter.getFrequency("Zuhair"));
    }
}