package domainLayer;

import exceptionHandlers.EmptyBagException;

/**
 * An interface that demonstrates the shop class operations making use of a bag.
 * @author Ben-Malik_Gorkem
 *
 */
public interface ShopInterface<T> {
	
	/**
	 * Adds a product to the bag.
	 * @param product The object to be added to the bag. 
	 */
	void addProduct(T product);

	/**
	 * Checks whether  a product is in the bag yet or not.
	 * @param product The object whose existence in the bag is to be checked.
	 * @throws EmptyBagException in case the bag is empty.
	 */
	public void isProductInBag(T product) throws EmptyBagException;
	
	/**
	 * Detects the frequency of a particular product in the bag.
	 * @param product The object whose frequency is to be detected.
	 * @throws EmptyBagException Cannot check the frequency when the bag is empty itself.
	 */
	public void getFrequencyOf(T product) throws EmptyBagException;
	
	/**
	 * Takes the basket object created above.
	 * @return basket.
	 */
	public BagInterface<T> getBasket();

	
}
