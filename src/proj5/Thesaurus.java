package proj5;

import java.util.Random;


public class Thesaurus {

    private final BinarySearchTree<ThesaurusNode> contents;

    public Thesaurus(){
        contents = new BinarySearchTree<>();
    }

    /**
     * Non-default constructor of the Thesaurus Object
     * @param file a comma-separated file's location
     */
    public Thesaurus(String file) {
        contents = new BinarySearchTree<>();
        buildThesaurus(file);
    }

    /**
     * Delete the given entry from the Thesaurus
     * @param toDelete A String of the entry to be deleted
     */
    public void delete(String toDelete){
        if(contents.contains(new ThesaurusNode(toDelete.toLowerCase()))){
            ThesaurusNode toDeleteNode = new ThesaurusNode(toDelete);
            contents.delete(toDeleteNode);
        }
    }

    /**
     * Check if the given String has an entry in the Thesaurus, case-insensitive
     * @param toCheck the word to check whether it has an entry or not in the thesaurus
     * @return true, if the provided word has an entry in Thesaurus, false otherwise
     */
    public boolean containsEntry(String toCheck){
        return contents.contains(new ThesaurusNode(toCheck.toLowerCase()));
    }

    /**
     * Insert an entry to the Thesaurus and add its provided list of synonyms to it. If entry exists,
     * add only the given synonyms which are not already in the synonyms list
     * @param entry the entry to be added to Thesaurus, if it does not already exist
     * @param synonyms an Array of synonymous words (String) to entry
     */
    public void insert(String entry, String[] synonyms) {
        ThesaurusNode entryNode = new ThesaurusNode(entry.toLowerCase());
        if(!contents.contains(entryNode)){
            for (String synonym : synonyms) {
                entryNode.addSynonym(synonym);
            }
            contents.insert(entryNode);
        }else{
            ThesaurusNode existingNode = contents.getElement(entryNode);
            for (String synonym : synonyms) {
                if(!existingNode.getSynonymList().contains(synonym)){
                    existingNode.addSynonym(synonym);
                }
            }
        }
    }

    /**
     * Build the Thesaurus contents using a provided comma-separated text file with entries and synonyms
     * @param file A String of the location of the file that has the entries and synonyms
     */
    private void buildThesaurus(String file){
        LineReader reader = new LineReader(file, ",");
        String[] line;
        while((line = reader.getNextLine()) != null){
            int synsSize = line.length - 1;
            String[] syns = new String[synsSize];
            for (int i = 0; i < syns.length; i++) {
                syns[i] = line[i + 1];
            }
            insert(line[0], syns);
        }
    }

    /**
     * Get a list of all the synonyms of a given entry stored in the Thesaurus, if entry exists
     * @param word the entry whose synonyms are to be retrieved, case-insensitive
     * @return returns a LinkedList of all synonyms of an entry in the Thesaurus
     */
    public LinkedList<String> getAllSynonyms(String word) {
        ThesaurusNode keywordNode = new ThesaurusNode(word.toLowerCase());
        LinkedList<String> toReturn = null;
        if (contents.contains(keywordNode)){
            toReturn = contents.getElement(keywordNode).getSynonymList();
        }
        return toReturn;
    }

    /**
     * Get a random synonym of an entry, if entry exists in Thesaurus
     * @param word the entry to get a random synonym for
     * @return returns a random synonym for a given entry
     */
    public String getSynonymFor(String word){
        LinkedList<String> synonyms = getAllSynonyms(word.toLowerCase());
        if(synonyms != null){
            Random rand = new Random();
            int randIndex = rand.nextInt(synonyms.getLength());
            return synonyms.get(randIndex);
        }
        return "";
    }

    /**
     * A by key lexicographically-arranged String representation of the Thesaurus
     * @return returns a string representation of the Thesaurus Object
     */
    public String toString() {
        ThesaurusNode[] thesaurusNodes = inOrderTraversal(contents.getRoot());
        String toReturn = "";
        for(ThesaurusNode thisNode: thesaurusNodes){
            toReturn += thisNode.key + " - {";
            int synsLength = thisNode.getSynonymList().getLength();
            for (int synonymIndex = 0; synonymIndex < synsLength; synonymIndex++){
                toReturn += thisNode.getSynonym(synonymIndex);
                if (synonymIndex != synsLength - 1){
                    toReturn += ", ";
                }
            }
            toReturn += "}\n";
        }
        return toReturn;
    }


    /**
     * Get a lexicographically arranged Array of ThesaurusNodes
     * @param node The BST root node for which to get an arranged Array of Thesaurus nodes
     * @return return an Array of ThesaurusNodes
     */
    private ThesaurusNode[] inOrderTraversal(BSTNode<ThesaurusNode> node) {
        if (node == null) {
            return new ThesaurusNode[0];
        }

        ThesaurusNode[] leftSubtree = inOrderTraversal(node.llink);
        ThesaurusNode[] rightSubtree = inOrderTraversal(node.rlink);

        int leftSize = leftSubtree.length;
        int rightSize = rightSubtree.length;
        int totalSize = leftSize + rightSize + 1;
        ThesaurusNode[] result = new ThesaurusNode[totalSize];
        System.arraycopy(leftSubtree, 0, result, 0, leftSize);
        result[leftSize] = node.key;
        System.arraycopy(rightSubtree, 0, result, leftSize + 1, rightSize);

        return result;
    }

    public static void main(String[] args) {
        Thesaurus myT = new Thesaurus("C:/Users/Zuhai/OneDrive/Desktop/CSC 151/project 5/src/proj5/smallThesaurus.txt");
//        String entry = "happy";
//        String[] syns = {"glad", "content", "joyful"};
//        myT.insert(entry, syns);
        String entry1 = "jump";
        ThesaurusNode entry1Node = new ThesaurusNode(entry1);
        String[] syns1 = {"leap", "Aref"};
        String[] syns2 = {"bound", "Aref"};
        myT.insert(entry1, syns1);
        myT.insert(entry1, syns2);
        myT.delete("awesome");
        System.out.println(myT);
        System.out.println(myT.contents.getElement(entry1Node).getSynonym(0));
    }


}