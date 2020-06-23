package domainLayer;

import exceptionHandlers.EmptyBagException;

/**
 * A Stack interface that describes the operations of the products of the market basket.
 * @author Ben-Malik_Gorkem
 *
 * @param <T>
 */
public interface StackInterface<T> {
	
	/**
	 * Pushes a new entry to the top of the stack
	 * @param newEntry The object of the new entry to be pushed to the stack. 
	 */
	public void push(T newEntry);
	
	/**
	 * Pops the top most entry of the stack.
	 * @return The top most entry of the stack.
	 * @throws EmptyBagException 
	 */
	public T pop() throws EmptyBagException;
	
	/**
	 * Gets the top most entry of the stack without removing it from the stack.
	 * @return The top most entry of the stack.
	 * @throws EmptyBagException 
	 */
	public T peek() throws EmptyBagException;
	
	/** Checks whether the stack is empty or not.
	 * @return True if the stack is empty and False otherwise.
	 */
	public boolean isEmpty();
	
	/**
	 * Removes all the entries of the stack.
	 * @throws EmptyBagException 
	 */
	public void clear() throws EmptyBagException;
	
	/**
	 * Gets the capacity of the stack
	 * @return The integer number of the stack capacity.
	 */
	public int getStackSize();

	/**
	 * Pop the entry at the specified index.
	 * @param index
	 * @return
	 * @throws EmptyBagException
	 */
	public Product pop(int index) throws EmptyBagException;

	public int getStackCapacity();
} // end StackInterface.
