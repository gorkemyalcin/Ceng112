package bussinessLayer;

/**
 * A PriorityQueueInterface defining the operations of the shoppingitem priorities.
 * @author Ben-Malik_Gorkem
 *
 */
public interface PriorityQueueInterface<T> {
	
	/** Adds a new Entry to the queue.
	 * 
	 * Post-condition: The newEntry is sorted in accordance with its priority.
	 * @param newEntry The object of the newEntry to be added.
	 */
	public void add(T newEntry);
	
	/** Removes the head of the queue.
	 * Pre-condition: isEmpty() is called and ensured to return false.
	 * 				  @throws SecurityException accordingly.
	 * @return The head of the queue.
	 */
	public T remove();
	
	/** Detects the front of the queue without removing it.
	 *Pre-condition: isEmpty() is called and ensured to return false.
	 * 				  @throws SecurityException accordingly.
	 * @return the front of the queue.
	 */
	public T peek();
	
	/** Detects whether the queue is empty or not.
	 * @return True if the queue is empty and false accordingly.
	 */
	public boolean isEmpty();
	
	/** Detects the number of entries in queue.
	 * Pre-condition: isEmpty() is called and ensured to return false.
	 * 				  @throws SecurityException accordingly.
	 * @return The integer number of entries in the queue.
	 */
	public int getSize();
	
	/** Dequeues all entries from the queue.
	 * Pre-condition: isEmpty() is called and ensured to return false.
	 * 				  @throws SecurityException accordingly.
	 * Post-condition: the queue is null and the number of entries == 0.
	 */
	public void clear();
	
} // end PriorityQueueInterface.
