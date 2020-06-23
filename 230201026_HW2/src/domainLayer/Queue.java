package domainLayer;

import java.util.Arrays;
import exceptionHandlers.EmptyQueueException;

/**
 * A class of Queue which stores entries into an array.
 * @author Ben-Malik_Gorkem
 *
 */
public class Queue implements QueueInterface<Product> {
	private static int queueCapacity = 2;
	private  Product[] queue;
	private boolean isInitialized = false;
	private static int extensionFactor = 1;
	private static int numberOfEntries;
	
	public Queue(int k) {
		checkExtensionFactor(k);
		queue = new Product[queueCapacity];
		extensionFactor = k;
		numberOfEntries = 0;
		isInitialized = true;
	} // end constructor.
	
	@Override
	public void enqueue(Product newEntry) {
		checkInitialization();
		ensureCapacity();
		queue[numberOfEntries] = newEntry;
		numberOfEntries++;
	} // end enqueue.

	@Override
	public Product dequeue() throws EmptyQueueException {
		Product head = null;
		checkInitialization();
		if (isEmpty()) {
			throw new EmptyQueueException("Cannot dequeue from an empty queue");
		}
		head = queue[0];
		if (numberOfEntries != 0) { // shift the next entries of the queue to the head.
			shiftQueueEntries();
		}
		numberOfEntries--;
		return head;
	} // end dequeue

	@Override
	public Product getFront() throws EmptyQueueException {
		checkInitialization();
		Product front = null;
		if (isEmpty()) {
			throw new EmptyQueueException("The Queue is empty");
		}
		front = queue[0];
		return front;
	} // end getFront.

	@Override
	public void clear() {
		for (int i = 0; i <= numberOfEntries; i++) {
			queue[i] =  null;
		}		
	} // end clear.

	@Override
	public boolean isEmpty() {
		boolean result = false;
		if (queue.length == 0) {
			result = true;
		}
		return result;
	} // end isEmpty.
	
	public boolean isInQueue(Product entry) {
		boolean result = false;
		for (int i = 0; i < numberOfEntries; i++) {
			if (queue[i].getProductName().equals(entry.getProductName())) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	/**
	 * Checks whether the queue has successfully been initialised. 
	 * To be used before enqueuing, dequeuing and getting front of the queue.
	 * @throws IllegalStateException accordingly.
	 */
	private void checkInitialization() {
		if (!isInitialized) {
			throw new IllegalStateException("The given array is not initialized");
		}
	} // checkInitialization.
	
	/**
	 * Detects whether the size of the queue is equal to the queueCapacity and 
	 * accordingly multiply it with the extensionFactor k.
	 * @param k The extension factor to be multiplied to the queueCapacity in order to 
	 *        ensure that the queue is enlarge automatically as it gets full.
	 */
	private void ensureCapacity() {
		if (numberOfEntries + 1 == queueCapacity) {
			queueCapacity = extensionFactor * queueCapacity;
			queue = Arrays.copyOf(queue, queueCapacity);
		}
	} // end ensureCapacity.

	/**
	 * Shifts all the entries of the queue to left.
	 */
	private void shiftQueueEntries() {
		Product[] tempQueue = new Product[numberOfEntries - 1];
		for (int i = 1; i < numberOfEntries; i++) {
			tempQueue[i - 1] = queue[i];
		}
		queue = tempQueue;
	} // end shiftQueueEntries.
	
	/**
	 * Detects whether the given extension factor is either 2 or 3 
	 * @param factor The integer factor to be checked.
	 * @throws IllegalArgumentException if the factor does not match 2 or 3.
	 */
	private void checkExtensionFactor(int factor) {
		if (factor != 2 && factor != 3) {
			throw new IllegalArgumentException("The extension factor has to be either 2 or 3");
		}
	}
	
} // end Queue.
