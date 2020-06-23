package presentationLayer;

import domainLayer.*;
import exceptionHandlers.*;

/**
 * @author Ben-Malik_Gorkem
 *
 */
public class ShoppingApp {

	public static void main(String[] args) throws EmptyBagException, EmptyQueueException {
		
		System.out.println("\n..............Welcome to the Iztech Shopping Center ................. \n");
		/* Our market's products.*/
		Product product0 = new Product(0, "Milk", "Beverage", 0.75);
		Product product1 = new Product(1, "Pasta", "Food", 1.75);
		Product product2 = new Product(2, "Cheese", "Food", 3.75);
		Product product3 = new Product(3, "Chocolate", "Food", 1.00);
		Product product4 = new Product(4, "Soap", "Hygiene", 3.50);
		Product product5 = new Product(5, "Water", "Beverage", 0.50);
		Product product6 = new Product(6, "Coffee", "Beverage", 7.75);
		Product product7 = new Product(7, "Chewing Gum", "Food", 1.25);
		Product product8 = new Product(8, "Orange juice", "Drink", 1.45);
		Product product9 = new Product(9, "Banana", "Fruit", 0.65);
		Product product10 = new Product(10, "Baklava", "Dessert", 10.0);
		

		// Test Shop class.
		Shop shoppingBag = new Shop(2);
		System.out.println("shop class test");

		shoppingBag.isProductInBag(product7);
		
		shoppingBag.addProduct(product0);
		shoppingBag.addProduct(product1);
		shoppingBag.addProduct(product2);
		shoppingBag.addProduct(product3);
		shoppingBag.addProduct(product5);
		shoppingBag.addProduct(product5);
		shoppingBag.addProduct(product5);
		shoppingBag.addProduct(product6);
		shoppingBag.addProduct(product6);
		shoppingBag.addProduct(product6);
		shoppingBag.addProduct(product6);
		shoppingBag.addProduct(product7);
		shoppingBag.addProduct(product6);
		shoppingBag.addProduct(product6);
		shoppingBag.addProduct(product4);
		shoppingBag.isProductInBag(product3);
		shoppingBag.isProductInBag(product7);
		shoppingBag.getFrequencyOf(product2);

		
		System.out.print("Frequency of " + product7.getProductName() + ": ");
		shoppingBag.getFrequencyOf(product7);
		shoppingBag.shoppingBagSize();
		shoppingBag.shoppingBagCapacity();

		
		
		StackInterface<Product> stack = new Stack(2);
		stack.push(product2);	
		stack.push(product1);
		System.out.println(stack.getStackSize());

		stack.push(product7);
		stack.push(product6);
		System.out.println("Second element in the stack: \n" + stack.pop(1) + "\n");
		System.out.println(stack.getStackSize());
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.peek());
		
		
		// Test Queue class.
		
		QueueInterface<Product> queue = new Queue(2);
		System.out.println(" \n Queue class test \n" );
		queue.enqueue(product7);
		queue.enqueue(product3);
		System.out.println(queue.dequeue());
		
		/* Test Pay class.*/
		Pay pay = new Pay(2, shoppingBag.getBasket());
		System.out.println("  \n Pay class \n");
		pay.checkOrderOf(product6);
		pay.putToDesk();
		pay.putToDesk();
		pay.putToDesk();
		pay.putToDesk();
		pay.checkOrderOf(product6);
		System.out.println("\n");
		pay.passThroughCounter();
		pay.numberOfProductNotInOrder(product6);
	}

} // end ShoppingApp.
