package domainLayer;

import exceptionHandlers.*;

/**
 * A Pay class illustrating the operations of the PayInterface.
 * @author Ben-Malik_Gorkem
 *
 */
public class Pay implements PayInterface<Product> {
	

	private QueueInterface<Product> checkoutDesk;
	private BagInterface<Product> shoppingBasket;
	private static int extensionFactor = 1;
	
	public Pay(int k, BagInterface<Product> shoppingBasket) {
		checkExtensionFactor(k);
		extensionFactor = k;
		checkoutDesk = new Queue(extensionFactor);
		this.shoppingBasket = shoppingBasket;
	} // end constructor.
	
	
	@Override
	public void putToDesk()  throws EmptyBagException {
		checkoutDesk.enqueue(shoppingBasket.grab());
		System.out.println("A product has been put to the checkout desk.");

	}

	@Override
	public void checkOrderOf(Product product)	throws EmptyQueueException {
			if (checkoutDesk.isInQueue(product)) {
				System.out.println(product.getProductName() + " is already in the checkout desk!");
			}
			else {
				System.out.println(product.getProductName() + " is not in the checkout desk yet");
			}
	}

	
	@Override
	public void passThroughCounter() throws EmptyQueueException, EmptyBagException {
		double bill = 0.0;
		BagInterface<Product> finalBag = (BagInterface<Product>) new Bag(extensionFactor);
		while(!checkoutDesk.isEmpty()) {
			bill = bill + checkoutDesk.getFront().getProductPrice();
			finalBag.add(checkoutDesk.dequeue());
		}
		System.out.println("Your total bill:" + bill + " Tl\n") ;
		System.out.println("Final Bag contents:");
		while(!finalBag.isEmpty()) {
			System.out.println(finalBag.grab().toString());
		}
		System.out.println();
	
	}
	
	@Override
	public void numberOfProductNotInOrder(Product product) throws EmptyBagException {
		int result = 0;
		result = shoppingBasket.getFrequencyOf(product);
		System.out.println("Number of " + product.getProductName() + " not in the checkout desk yet: " + result);
	}
	
	/**
	 * Detects whether the given extension factor is either 2 or 3 
	 * @param factor The integer factor to be checked.
	 * @throws IllegalArgumentException if the factor does not match 2 or 3.
	 */
	private void checkExtensionFactor(int factor) {
		if (factor != 2 && factor != 3) {
			throw new IllegalArgumentException("The extension factor could only be either 2 or 3");
		}
	}


	

}
