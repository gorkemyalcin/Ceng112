package domainLayer;
import java.util.Random;
import exceptionHandlers.EmptyBagException;

/**
 * A class illustrating the operations of the BagInterface.
 * @author Ben-Malik_Gorkem
 *
 */
public class Bag implements BagInterface<Product> {
	
	private static int extensionFactor = 1; 
	private StackInterface<Product> ourStack;
	
	public Bag(int k) {
		checkExtensionFactor(k);
		extensionFactor = k;
		ourStack = new Stack(extensionFactor);
	} // end constructor.
	
	@Override
	public int getBagSize() {
		return ourStack.getStackSize() + 1;
	}

	@Override
	public void add(Product newEntry) {
		ourStack.push(newEntry);
	}

	@Override
	public Product grab() throws EmptyBagException {
		Product entry = null; 
		int index;
		Random rand = new Random();
		index = rand.nextInt(getBagSize());
		entry = ourStack.pop(index);
		return entry;
	}

	@Override
	public boolean isEmpty() {
		return ourStack.isEmpty();
	}

	@Override
	public void clear() throws EmptyBagException {
		ourStack.clear();
	}

	@Override
	public boolean contains(Product anEntry) throws EmptyBagException {
		boolean result = false;
		StackInterface<Product> tempStack = new Stack(extensionFactor);
		
		while(!ourStack.isEmpty()) {
			if (ourStack.peek().getProductName().equals(anEntry.getProductName())) {
				result = true;
				break;
			}
			else {
				tempStack.push(ourStack.pop());
			}
		} // end while.
		if (!tempStack.isEmpty()) {
			while(!tempStack.isEmpty()) {
				ourStack.push(tempStack.pop());
			}
		}
		return result;
	} // end contains.

	@Override
	public int getFrequencyOf(Product anEntry) throws EmptyBagException {
		Product entry = null;
		int result = 0;
		StackInterface<Product> tempStack = new Stack(extensionFactor);
		if (ourStack.isEmpty()) {
			throw new EmptyBagException("Cannot get the frequency of any entry, when the stack is empty");
		}
		while(!ourStack.isEmpty()) {
			entry = ourStack.pop();
			tempStack.push(entry);
			if (entry.getProductName().equals(anEntry.getProductName())) {
				result++;
			}			
		}		
		// Gets all the products popped back pushed to ourStack.
		while(!tempStack.isEmpty()) {
			ourStack.push(tempStack.pop());
		}		
		return result;
	} // end getFreqencyOf. 
	

	@Override
	public int getBagCapacity() {
		return ourStack.getStackCapacity();
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

} // end Bag.
