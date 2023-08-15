package proj5;

/**
 * The GrammarChecker class is responsible for improving the grammar of a given text file by replacing frequent words with synonyms from a thesaurus.
 * It provides methods to process lines of text, replace words with synonyms, and check for punctuation characters.
 * The class requires a thesaurus file and a frequency threshold for word replacement.
 *
 * threshold (int) stores the maximum accepted number of occurrences of a word in a given text file
 * Thesaurus (Thesaurus) Thesaurus Object from which to get the synonyms of different words
 *
 * @author Zuhair AlMassri
 * @version 06/01/23
 */

public class GrammarChecker {
    private final Thesaurus thesaurus;
    private final int threshold;
    private final String PUNCTUATION = ".,!?;:";

    /**
     * Constructs a GrammarChecker object with the specified thesaurus file and threshold.
     *
     * @param thesaurusFile the path to the thesaurus file
     * @param threshold     the frequency threshold for word replacement
     */
    public GrammarChecker(String thesaurusFile, int threshold){
        this.thesaurus = new Thesaurus(thesaurusFile);
        this.threshold = threshold;

    }

    /**
     * Improves the grammar of the given text file by replacing frequent words with synonyms
     * from the thesaurus. The improved grammar is printed to the console.
     *
     * @param textFile the path to the text file
     */
    public void improveGrammar(java.lang.String textFile) {
        WordCounter wordsInText = new WordCounter();
        wordsInText.findFrequencies(textFile);
        LineReader reader = new LineReader(textFile, " ");
        String[] line;
        while ((line = reader.getNextLine()) != null) {
            String modifiedLine = processLine(line, wordsInText);
            System.out.println(modifiedLine);
        }
    }

    /**
     * Processes a line of text by replacing frequent words with synonyms from the thesaurus.
     *
     * @param line         the line of text to process
     * @param wordsInText  the WordCounter containing word frequencies
     * @return the modified line of text with replaced words
     */
    private String processLine(String[] line, WordCounter wordsInText){
        String modifiedLine = "";
        for (String word : line) {
            String modifiedWord = "";
            String strippedWord;
            if (hasPunctuation(word)) {
                strippedWord = word.substring(0, word.length() - 1);
            } else {
                strippedWord = word;
            }
            int wordFreq = wordsInText.getFrequency(strippedWord);
            if (wordFreq > threshold) {
                modifiedWord = processWord(word);
            }else{
                modifiedWord += word;
            }
            modifiedLine += modifiedWord + " ";
        }
        return modifiedLine;
    }

    /**
     * Processes a word by replacing it with a synonym from the thesaurus.
     *
     * @param word the word to process
     * @return the modified word with replaced synonym
     */
    private String processWord(String word){
        String modifiedWord;
        String strippedWord = stripWord(word);
        String lastCharacter = getPunctuation(word);
        if(thesaurus.containsEntry(strippedWord)){
            modifiedWord = thesaurus.getSynonymFor(strippedWord) + lastCharacter;

        }else{
            modifiedWord = strippedWord + lastCharacter;
        }


        return modifiedWord;
    }

    /**
     * Strip word from any punctuation at the end of the word, if any
     * @param word the word to be stripped of any punctuation at its end
     * @return returns the word stripped from punctuation
     */
    private String stripWord(String word){
        String strippedWord;
        if (hasPunctuation(word)) {
            strippedWord = word.substring(0, word.length() - 1);
        } else{
            strippedWord = word;
        }
        return strippedWord;
    }


    /**
     * Checks if a word ends with a punctuation character.
     *
     * @param word the word to check
     * @return true if the word ends with a punctuation character, false otherwise
     */
    private boolean hasPunctuation(String word){
        String lastCharacter = "";
        boolean isPunctuation = false;
        if(word.length() > 0){
            lastCharacter += word.charAt(word.length() - 1);
            isPunctuation = PUNCTUATION.contains(lastCharacter);
        }
        return isPunctuation;
    }

    /**
     * Get the punctuation at the end of a given word, if any
     * @param word The word to get the punctuation at the end of, if any
     * @return the punctuation at the end of word
     */
    private String getPunctuation(String word){
        String lastCharacter = "";
        if(hasPunctuation(word)){
            lastCharacter += word.charAt(word.length() - 1);
        }
        return lastCharacter;
    }



    public static void main(String[] args) {
        GrammarChecker checker = new GrammarChecker("C:/Users/Zuhai/OneDrive/Desktop/CSC 151/project 5/src/proj5/smallThesaurus.txt", 4);
        checker.improveGrammar("C:/Users/Zuhai/OneDrive/Desktop/CSC 151/project 5/src/proj5/test.txt");
    }

}
