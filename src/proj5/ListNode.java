package proj5;

/**
 * The ListNode class is more data-specific than the LinkedList class.  It
 * details what a single node looks like.  This node has one data field,
 * holding a pointer to a String object. 
 *
 * This is the only class where I'll let you use public instance variables.
 * 
 */
public class ListNode<T>
{
    public T data;
    public ListNode next;

    public ListNode(T new_data)
    {
        data = new_data;
        next = null;
    }

    public String toString(){
    	return data.toString();
    }

}
