package proj5;
/**
 *  This is the BST ADT.  It contains methods that allow it to
 *  insert new nodes, delete nodes, contain, etc
 *
 *  root (BSTNode) A BSTNode that stores the root of the BinarySearchTree
 *  size (int) stores the number of elements stored in the BinarySearchTree
 *
 * @author Zuhair AlMassri
 * @version 05/22/23
 */
public class BinarySearchTree<T extends Comparable<T>>
{
     private BSTNode<T> root;
     public BinarySearchTree() {
    	 root=null;
     }
     private int size = 0;

    /**

     Recursively inserts a new node into the binary search tree starting from the given node.
     @param startingNode the current node being evaluated during the recursive insertion
     @param newNode the new node to be inserted
     @return the modified subtree with the new node inserted
     */
     private BSTNode<T> recursiveInsert(BSTNode<T> startingNode, BSTNode<T> newNode) {
    	 if (startingNode == null) {
    		 return newNode;
    	 }
    	 else if (startingNode.key.compareTo(newNode.key) < 0) {
    		 startingNode.rlink = recursiveInsert(startingNode.rlink,newNode);
    		 return startingNode;
    	 }
    	 else {
    		 startingNode.llink = recursiveInsert(startingNode.llink,newNode);
    		 return startingNode;
    	 }
     }


    /**
     * Inserts a new element into the binary search tree.
     *
     * @param newString the element to insert
     */
    public void insert(T newString) {
        BSTNode<T> newNode = new BSTNode<>(newString);
        root = recursiveInsert(root, newNode);
        incDecSize(1);
    }


    /**
     * Creates a deep copy/clone of the binary search tree.
     *
     * @return a new BinarySearchTree instance that is a clone of the original tree
     */
    public BinarySearchTree<T> clone() {
        BinarySearchTree<T> cloneTree = new BinarySearchTree<>();
        cloneTree.root = cloneRecursive(root);
        return cloneTree;
    }

    /**
     * Recursively clones the nodes of the binary search tree.
     *
     * @param node the current node being cloned
     * @return the cloned node
     */
    private BSTNode<T> cloneRecursive(BSTNode<T> node) {
        if (node == null) {
            return null;
        }

        BSTNode<T> cloneNode = new BSTNode<>(node.key);
        cloneNode.llink = cloneRecursive(node.llink);
        cloneNode.rlink = cloneRecursive(node.rlink);

        return cloneNode;
    }

    /**
     * Returns the contents of the BST as a string, following an
     * inorder traversal.
     *
     * The string version of the tree uses parentheses to show
     * the subtree structure.  e.g. (( A ) B ( C )) means
     * B is the parent of A (left child) and C (right child).
     *
     * @return the String version
     */
    public String toString()
    {
       return toStringRecursive(root);
    }

    /**
     * Recursively get a String representation of a BinarySearchTree starting with a BST node
     * @param N The root node of a BinarySearchTree
     * @return returns a String representation of a BinarySearchTree
     */
    private String toStringRecursive (BSTNode<T> N){
        String toReturn = "";
        if (N != null){
        T thisRoot = N.key;
        String left = toStringRecursive(N.llink);
        String right = toStringRecursive(N.rlink);

        if (left != null){
            toReturn += "(" + left;
        }
        toReturn += "  " + thisRoot + "  ";

        if (right != null){
            toReturn += right + ")" ;
        }
        return toReturn;
    }
    return toReturn;
    }

    /**
     * Searches for a value in the BST.
     *
     * @param target the value to search for.
     * @return true if and only if 'target' is in the tree
     */
    public boolean contains(T target)
    {
        return containsRecursive(root, target);
    }

    /**
     * Searches recursively for a value in the BST starting from a root node for a BinarySearchTree.
     *
     * @param target the value to search for.
     * @return true if and only if 'target' is in the tree
     */
    private boolean containsRecursive(BSTNode<T> N, T target){
        if(N == null){
            return false;
        }
        int comparison = target.compareTo(N.key);
        if (comparison == 0){
            return true;
        }else if(comparison < 0){
            return containsRecursive(N.llink, target);
        }else {
            return containsRecursive(N.rlink, target);
        }
    }


    /**
     * Get element in BST that equals to the given target. If there's no equal element, return null
     * @param target T as target to search
     * @return T as element in BST if founded, null otherwise
     */
    public T getElement(T target){
        return getElement(target, root);
    }

    /**
     * Private helper for getElement. Given a root current and a target,
     * search for the node in the subtree rooted at current that has key equals to target,
     * then return the key of that node
     * @param target T as target to search
     * @param currentRoot BST Node as root of BST to start searching
     * @return T as element in BST if founded, null otherwise
     *
     */
    private T getElement(T target, BSTNode<T> currentRoot){
        if (currentRoot == null) {
            return null;
        }
        else {
            if (target.compareTo(currentRoot.key) == 0) {
                return currentRoot.key;
            } else if (target.compareTo(currentRoot.key) > 0) {
                return getElement(target, currentRoot.rlink);
            } else {
                return getElement(target, currentRoot.llink);
            }
        }
    }

    /**
     * Removes a value from the BST. If the value is not present, does
     * nothing.
     *
     * @param target the value to remove from the tree
     */
    public void delete(T target) {
        root = delete(root, target);
        incDecSize(-1);
    }

    /**
     * Increment or Decrement size by the amount of the given value
     * @param value the value by which amount to change the size
     */
    private void incDecSize(int value){
        size += value;
    }

    /**
     * Recursively delete a BSTNode from a BinarySearchTree and rearrange the tree back into a correct BST form
     * @param root root of BinarySearchTree to be deleted from
     * @param target the target node to be deleted from a BST
     * @return returns the root of the BST after removal of target
     */
    private BSTNode<T> delete(BSTNode<T> root, T target) {
        if (root == null) {
            return null;
        }
        int comparison = root.key.compareTo(target);
        if (comparison > 0) {
            root.llink = delete(root.llink, target);
        } else if (comparison < 0) {
            root.rlink = delete(root.rlink, target);
        } else {
            if (root.isLeaf()) {
                return null;
            } else if (root.llink == null) {
                return root.rlink;
            } else if (root.rlink == null) {
                return root.llink;
            } else {
                BSTNode<T> successor = findMin(root.rlink);
                root.key = successor.key;
                root.rlink = delete(root.rlink, successor.key);
            }
        }
        return root;
    }
    private BSTNode<T> findMin(BSTNode<T> node) {
        while (node.llink != null) {
            node = node.llink;
        }
        return node;
    }

    /**
     * @return the number of elements in the tree
     */
    public int size()
    {
	return size;
    }

    /**
     * @return the height of the tree (i.e. the depth of the deepest node)
     */
    public int height()
    {
        return findHeight(root) -1;
    }

    /**
     * Finds the height of the binary search tree rooted at the specified node.
     * The height of a tree is defined as the number of edges in the longest path
     * from the root to a leaf node.
     *
     * @param root the root node of the binary search tree
     * @return the height of the binary search tree
     */
    private int findHeight(BSTNode<T> root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = findHeight(root.llink);
            int rightHeight = findHeight(root.rlink);

            if (leftHeight > rightHeight) {
                return leftHeight + 1;
            } else {
                return rightHeight + 1;
            }
        }
    }


    /**
     * A method that returns the root of the BST
     * @return the root BSTNode of the BST
     */
    public BSTNode<T> getRoot(){
        return root;
    }


    public static void main(String[] args) {
        BinarySearchTree<String> ne = new BinarySearchTree<>();
        ne.insert(String.valueOf(1));
        ne.insert(String.valueOf(2));
        ne.insert(String.valueOf(3));
        ne.insert(String.valueOf(6));
        ne.insert(String.valueOf(5));
        System.out.println(ne);
        ne.delete(String.valueOf(2));
        System.out.println(ne);
        ne.delete(String.valueOf(3));
        System.out.println(ne);


    }
}
