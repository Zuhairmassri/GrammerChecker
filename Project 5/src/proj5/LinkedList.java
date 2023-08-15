package proj5;


/**
 * The LinkedList class represents a singly linked list of Strings.
 *
 * Length of the LinkedList is the number of elements it is storing
 *
 * @author Zuhair AlMassri
 * Date: 04/27/23
 */
public class LinkedList<T>
{
    private int length;
    private ListNode<T> firstNode;

    public LinkedList()
    {
        length=0;
        firstNode=null;
    }

    /**
     * checks the number of elements stored in the LinkedList
     * @return returns the number of elements stored
     */
    public int getLength()
    {
        return length;
    }

    /**
     * Inserts a given String at the beginning of the LinkedList
     * @param data the String to be inserted at the beginning of the LinkedList
     */
    public void insertAtHead(T data)
    {
    	ListNode<T> newNode = new ListNode<>(data);
        if (isEmpty()) {
            firstNode=newNode;
        }
        else
        {
            newNode.next=firstNode;
            firstNode=newNode;
        }
        length++;
    }

    /**
     * Inserts a given String value at the end of a LinkedList. If list is
     * empty, inserts value at the beginning of the LinkedList
     * @param data the String to be inserted at the end of the LinkedList
     */
    public void insertAtTail(T data){
        ListNode<T> newNode = new ListNode<>(data);
        if (isEmpty()){
            insertAtHead(data);
        } else{
            ListNode<T> runner = firstNode;
            while(runner.next != null){
                runner = runner.next;
            }
            runner.next = newNode;
            length++;
        }
    }

    /**
     * removes the element at the end of the LinkedList
     */
    public void removeTail(){
        removeAtIndex(getLength() - 1);
    }

    /**
     * Checks whether a given String is stored in the LinkedList or not
     * @param toFind the String value which will be checked
     * @return True iff LinkedList stores given value. Otherwise, false
     */
    public boolean contains(T toFind){
        if (!isEmpty()){
            ListNode<T> runner = firstNode;
            while (runner != null){
                if (toFind.equals(runner.data)){
                    return true;
                }else{
                    runner = runner.next;
                }
            }
        }
        return false;
    }

    /**
     * Checks whether the LinkedList is empty or not
     * @return True iff LinkedList is empty. Otherwise, false
     */
    public boolean isEmpty(){
        return getLength() == 0;
    }

    /**
     * A method that gives back the element at a given index. If index is not valid, returns null
     * @param index index of requested element in LinkedList
     * @return element requested if index is valid. otherwise, returns null
     */
    public T get(int index){
        if(!isEmpty() && isValidIndex(index)){
            ListNode<T> runner = firstNode;
                for(int i = 0; i < index; i++){
                    runner = runner.next;
                }
                return (T) runner.data;
        } return null;
}


    /**
     * Removes an element at a given index. If index is not valid, does nothing
     * @param index the index of which element is to be removed
     */
    public void removeAtIndex(int index){
        if(!isEmpty() && isValidIndex(index)){
            ListNode<T> runner = firstNode;
            ListNode<T> previous = null;
            for(int i = 0; i < index; i++){
                previous = runner;
                runner = runner.next;
            }
            if(previous != null){
                previous.next = runner.next;
            } else{
                firstNode = runner.next;
            }
            length--;
        }
    }

    /**
     * Checks whether an index is valid in the LinkedList or not
     * @param index the index in LinkedList to be checked
     * @return true iff the index valid. False, otherwise
     */
    private boolean isValidIndex(int index){
        return index < length && index >= 0;
    }

    /**
     * Sets the element at index to whatever given value.
     * If index does not exist, the value gets added to the end of the LinkedList
     * @param index the index where the new value is to be set
     * @param toSet the new value to be set
     */
    public void set(int index, T toSet) {
        if(isEmpty() || index >= length){
            insertAtTail(toSet);
        }
        else if (!isEmpty() && index < length) {
            ListNode<T> runner = firstNode;
                for (int i = 0; i < index; i++) {
                    runner = runner.next;
                }
                runner.data = toSet;
            }
        }

    /**
     * Organizes the contents of the LinkedList in an organized way within parenthesis
     * @return returns String of the contents of LinkedList
     */
    public String toString(){
		String toReturn = "(";
		ListNode<T> runner = firstNode;
		while(runner != null){
			toReturn = toReturn + runner.toString();
			runner = runner.next;
			if(runner != null){
				toReturn = toReturn + ", ";
			}
		}
		toReturn = toReturn + ")";
		return toReturn;
	}

    /**
     * A method that checks whether two LinkedList Objects are equal or not
     * @param other the other LinkedList to check for equality
     * @return true iff the other is equal. False, otherwise
     */
    public boolean equals(LinkedList<T> other){
        if(this.getLength() != other.getLength()){
            return false;
        }
        for (int i = 0; i < getLength(); i++){
            if(!this.get(i).equals(other.get(i))){
                return false;
            }
        }
        return true;
    }
}


