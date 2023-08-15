package proj5;
/** A very-reusable node class, since it only holds any Type.
 *  ABSOLUTE PERFECTION WHEN IT COMES TO GENERIC BINARY TREE NODES
 * 
 * @author Zuhair AlMassri
 * @version 5/29/23
 */
public class BSTNode<T> {

	public T key;
	public BSTNode<T> llink;
	public BSTNode<T> rlink;
	
	/**
	 * non-default constructor
	 * @param newKey string that node will hold
	 */
	public BSTNode(T newKey)
	{
		key = newKey;
		llink = null;
		rlink = null;
	}
	
	/**
	 * returns key as printable string
	 */
	public String toString()
	{
		return key.toString();
	}
	
    
	/**
	 * 
	 * @return true if this node is a leaf, else false
	 */
    public boolean isLeaf() {
    	return this.llink == null && this.rlink == null;
    }
    
    /**
     * 
     * @return true if this node has a non-null right subtree
     * and a null left subtree, else false
     */
    public boolean hasRightChildOnly() {
    	return this.llink == null && this.rlink != null;
    }
    
    /**
     * 
     * @return true if this node has a non-null left subtree
     * and a null right subtree, else false
     */
    public boolean hasLeftChildOnly() {
    	return this.llink != null && this.rlink == null;
    }


}
