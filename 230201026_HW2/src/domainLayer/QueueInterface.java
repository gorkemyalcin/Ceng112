package domainLayer;

import exceptionHandlers.EmptyQueueException;

/**
 * @author Ben-Malik_Gorkem
 *
 * A queue interface illustrating the operation on products in the cashier.
 * @param <T> 
 */
public interface QueueInterface<T> {
	
	/**
	 * Adds a new entry to the back of the queue.
	 * @param newEntry The object to be added to the queue.
	 */
	public void enqueue(T newEntry);
	
	/**
	 * Removes the entry at the top of the queue.
	 * @return The top most entry of the queue.
	 * @throws EmptyQueueException 
	 */
	public T dequeue() throws EmptyQueueException;
	
	/**
	 * Retrieves the queue's front entry.
	 * @return The queues's front entry.
	 * @throws EmptyQueueException 
	 */
	public T getFront() throws EmptyQueueException;
	
	/**
	 * Removes all the entries.
	 */
	public void clear();
	
	/**
	 * Detects whether the queue is empty. 
	 * @return True is the queue is empty and false otherwise.
	 */
	public boolean isEmpty();

	public boolean isInQueue(Product product);

	
	
	

}
