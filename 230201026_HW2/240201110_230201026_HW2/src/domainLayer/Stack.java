package domainLayer;

import java.util.Arrays;
import exceptionHandlers.EmptyBagException;

/**
 * @author Ben-Malik_Gorkem
 *
 */
public class Stack implements StackInterface<Product> {
	
	private Product stack[];
	private boolean isInitialized = false;
	private static int capacity = 2;
	private static int extensionFactor = 1;
	private int topIndex;
	
	public Stack(int k) {
		stack = new Product[capacity];
		checkExtensionFactor(k);
		extensionFactor = k;
		topIndex = -1;
		isInitialized = true;
	} // end constructor.
	

	@Override
	public void push(Product newEntry) {
		checkInitialization();
		ensureCapacity();
		stack[topIndex + 1] = newEntry;
		topIndex++;		
	} // end push.

	@Override
	public Product pop() throws EmptyBagException {
		Product top = null;
		checkInitialization();
		if(isEmpty()) {
			throw new EmptyBagException("An entry cannot be popped from an empty Bag!");
		}
		top = stack[topIndex];
		stack[topIndex] = null;
		topIndex--;
		return top;
	} // end pop.

	@Override
	public Product peek() throws EmptyBagException {
		Product top = null;
		checkInitialization();
		if (isEmpty()) {
			throw new EmptyBagException("Cannot peek from an empty Bag");
		}
		else {
			top = stack[topIndex];
		}
		return top;
	} 

	@Override
	public boolean isEmpty() {
		boolean result = false;
		checkInitialization();
		if (topIndex == -1) {
			result = true;
		}
		return result;
	}

	@Override
	public void clear() throws EmptyBagException {
		checkInitialization();
		if (isEmpty()) {
			throw new EmptyBagException("The bag is currently empty itself.");
		}
 		for (int i = 0; i < topIndex; i++) {
 			stack[i] = null;
 		}
 		topIndex = -1;
	}
	
	@Override
	public int getStackSize() {
		return topIndex;
	}


	@Override
	public Product pop(int index) throws EmptyBagException {
		Product top = null;
		checkInitialization();
		if (isEmpty()) {
			throw new EmptyBagException("Cannot peek from an empty stack");
		} 
		if (index == topIndex) {
			top = stack[topIndex];
			stack[topIndex] = null; 
			topIndex--;
		}
		else {
			top = stack[index];
			shiftEntries(index);
		}
		return top;
	} // end pop.
	
	@Override
	public int getStackCapacity() {
		return capacity;
	}
	
	
	private void checkInitialization() {
		if (!isInitialized) {
			throw new IllegalStateException("The given array is not initialized");
		}
	} 
	
	private void ensureCapacity() {
		if (topIndex + 1 == stack.length) {
			int newLength = extensionFactor * capacity;
			capacity = newLength;
			stack = Arrays.copyOf(stack, capacity);
		} // end if.
	} // end ensure capacity.

	private void shiftEntries(int index) throws EmptyBagException {
		for (int i = index + 1; i <= topIndex; i++) {
			stack[i - 1] = stack[i];
		}
		topIndex--;
	} // end shiftEntries.
	
	private void checkExtensionFactor(int factor) {
		if (factor != 2 && factor != 3) {
			throw new IllegalArgumentException("The extension factor k should be either 2 or 3");
		}
	}

} // end Stack.
