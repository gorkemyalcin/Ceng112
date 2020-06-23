package domainLayer;

import exceptionHandlers.EmptyBagException;

/**
 * A Bag interface of generic describing the operations of the market basket.
 * @param <T> Generic type to be specified during usage.
 * 
 * @author Ben-Malik_Gorkem
 */
public interface BagInterface<T> {
	
	/** 
	 * Gets the number of entries currently in the bag.
	 * @return the integer number of the current entries that are in the bag.
	 */
	int getBagSize();
	
	/**
	 * Adds a new entry to the bag.
	 * @param newEntry The object to be added as a new entry.
	 * @return true if the addition is successful and false otherwise.
	 */
	public void add(T newEntry);
	
	/**
	 * Removes any random entry from the bag, if exists.
	 * @return Either the removed entry if any, or null.
	 * @throws EmptyBagException if the bag is empty.
	 */
	public T grab() throws EmptyBagException;
	
	/**
	 * Sees whether the bag is empty.
	 * @return True if the bag is empty, and false otherwise.
	 */
	public boolean isEmpty();
	
	/**
	 * Clears the entire bag.
	 * @throws EmptyBagException if the bag is empty during operation.
	 */
	public void clear() throws EmptyBagException;
	
	/** Checks whether a specific entry is contained in the bag.
	 * @param anEntry The  object whose existence in the bag is to be checked.
	 * @return True if the entry is found in the bag and false otherwise.
	 * @throws EmptyBagException if the bag is empty during operation.
	 */
	public boolean contains(T anEntry) throws EmptyBagException;
	
	/** Gets the frequency of an entry in the bag if it does exist.
	 * @param anEntry The object whose frequency is to be looked for.
	 * @return The integer number of the specific object's frequency.
	 * @throws EmptyBagException if the bag is empty during operation.
	 */
	public int getFrequencyOf(T anEntry) throws EmptyBagException;

	/**
	 * Detects the current capacity of the bag.
	 * @return the integer number of the bag's capacity.
	 */
	public int getBagCapacity();


}
