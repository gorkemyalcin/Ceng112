package domainLayer;

import exceptionHandlers.*;

/**
 * An interface describing the Payment operations.
 * @author @author Ben-Malik_Gorkem
 *
 * @param <T>
 */
public interface PayInterface<T> {
	
	/**
	 * Grabs any random product from the bag and put it to the checkout desk.
	 * @throws EmptyBagException when the bag is empty.
	 */
	public void putToDesk() throws EmptyBagException;
	
	/**
	 * Detects if a particular product is in the order of the checkout desk.
	 * @param product The object whose order is to be detected.
	 * @throws EmptyQueueException when checkout desk is empty.
	 */
	public void checkOrderOf(T product) throws EmptyQueueException;
	
	/**
	 * Passes the products to the counter where the total bill is calculated and printed alongside
	 * with the final state of the bag.
	 * @throws EmptyBagException Should be performed when the bag is empty.
	 * @throws EmptyQueueException neither when the queue is empty.
	 */
	public void passThroughCounter() throws EmptyBagException, EmptyQueueException;

	/**
	 * Finds the number of a specific product in the bag that is not passed to the counter yet.
	 * @param product The object whose frequency is to be checked.
	 * @throws EmptyBagException 
	 */
	public void numberOfProductNotInOrder(T product) throws EmptyBagException;
} // end PayInterface.
