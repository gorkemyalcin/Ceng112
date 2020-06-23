package bussinessLayer;

/**
 * The ListInterface specifying the operations doable on any type of list.
 * @param T generic type to be specified later on in usage.
 * 
 * @author Ben-Malik_Gorkem
 *
 */
public interface ListInterface<T> {
	
	/** Adds an item to the list.
	 * @param item The object to be added  to the list.
	 */
	public void add(T item);
	
	/** Adds the given item to the given position.
	 * 
	 * Pre-condition: checkPosition() is called.
	 * 				 @throws IndexOutOfBoundsException() accordingly.
	 * @param item The object to be added to the list.
	 * @param position The position at which the element is to be added.
	 */
	public void add(T item, int position);
	
	/**
	 * Removes all the elements of the list.
	 * Pre-conditions: isEmpty() is called and ensured that it return false otherwise a SecurityException is thrown.
	 */
	public void clear();
	
	/** Removes the item at the given position.
	 * @param givenPosition The position of the object to remove.
	 * @return The removed item.
	 */
	public T remove(int givenPosition);
	
	/** Replace an item at a particular position by the given newEntry.
	 * @param givenPosition The position whose item is to be replaced.
	 * @param newEntry The object of the newEntry to be replaced.
	 * @return The original item previous the the givenPosition.
	 * 
	 * Pre-condition: isEmpty() is called and made sure that it returns false otherwise a SecurityException is thrown.
	 * 				  The number of entries >= givenPosition.
	 * 				  newEntry is not null.
	 * Post-condition: the number of entrie is the same. 
	 */
	public T replace(int givenPosition, T newEntry);
	
	/** Detects the item at a givenPosition.
	 * @param givenPosition The position whose item is desired.
	 * @return The object of the item at the givenPosition.
	 * 
	 * Pre-condition: isEmpty() is called and is ensured to return false
	 * 				  otherwise a SecurityException is thrown
	 */
	public T getEntry(int givenPosition);
	
	/** Traverses the list and adds each item into an array.
	 * @return The array of items of the list.
	 */
	public T[] toArray();
	
	/** Detects whether anEntry is in the list.
	 * @param anEntry The object of the entry whose existence in the list in to be checked.
	 * @return True if the object is included in the list and flase otherwise.
	 * 
	 * Pre-condition: isEmpty() is called and ensured to return false
	 * 				  otherwise a SecurityException is thrown
	 */
	public boolean contains(T anEntry);
	
	/** Detects the size of the list.
	 * @return the number of entries in the list.
	 * 
	 * Pre-condition: anEntry is not null.
	 * 				  isEmpty() is called and ensured to return false 
	 * 				  otherwise a SecurityException is thrown.
	 * 
	 */
	public int getLength();
	
	/** Detects whether is list empty.
	 * @return True if the list is empty and false otherwise.
	 */
	public boolean isEmpty();

	/** Removes the last item in the list.
	 * @return The object of the removed item.
	 * 
	 * Pre-condition: isEmpty() is called and ensured to return false
	 * 				 otherwise a SecurityException is thrown
	 */
	public T remove();
	
} // end ListInterface.
