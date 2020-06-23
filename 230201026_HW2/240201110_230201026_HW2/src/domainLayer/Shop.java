package domainLayer;

import exceptionHandlers.EmptyBagException;


/**
 * A shop class that implements the operations defined by the ShopInterface.
 * @author Ben-Malik_Gorkem
 *
 */
public class Shop implements ShopInterface<Product> {
private BagInterface<Product> basket;
	
	public Shop(int k) {
		basket = new Bag(k);
	} // end constructor.
	
	@Override
	public void addProduct(Product product) {
		basket.add(product);
	}
	
	@Override
	public void isProductInBag(Product product) throws EmptyBagException {
		if (basket.contains(product)) {
			System.out.println(product.getProductName() + " already exists in the bag");
		}
	}
	
	@Override
	public void getFrequencyOf(Product product) throws EmptyBagException {
		System.out.println("Number of " + product.getProductName() + " in the basket: " + basket.getFrequencyOf(product));
	}
	
	public void shoppingBagSize() {
		System.out.println("The bag's current size: " + basket.getBagSize());
	}
	
	public void shoppingBagCapacity() {
		System.out.println("The bag's current capacity: " + basket.getBagCapacity());
	}
	
	public BagInterface<Product> getBasket() {
		return basket;
	}

} // end Shop.
