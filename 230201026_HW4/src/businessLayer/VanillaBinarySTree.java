package businessLayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**A VanillaBinarySTree class describing the operations of the IBinarySearchTree interface.
 * @author Ben-Malik_Gorkem
 *
 * @param <K> The object of the Keys type to be specified during usage.
 * @param <V> The object of the values Type to be specified during usage.
 */
public class VanillaBinarySTree<K extends Comparable<?super K>, V> implements IBinarySearchTree<K, V> {
	
	private BinaryNode<K, V>[] binaryTree;
	private static int numberOfNodes;
	private List<K> keys;
	private List<V> values;
	private boolean initialized = false;
	int rootIndex, capacity;

	@SuppressWarnings("unchecked")
	public VanillaBinarySTree() {
		this.binaryTree = new BinaryNode[0];
	}
	
	public VanillaBinarySTree(List<K> keys, List<V> values) {
		initializeDataFields(keys, values);
	}

	@Override
	public V get(K key) {
		int result = binarySearch(key, 0, false);
		if (result < 0) {
			return null;
		}
		return binaryTree[result].getValue();
	}

	@Override
	public void remove(K key) {
		int position = binarySearch(key, 0, false);
		if (position >= 0) {
			if (isLeaf(position)) {
				binaryTree[position] = null;
				numberOfNodes--;
			}
			else if (hasOneChild(position)) {
				if (hasLeft(position)) {
					binaryTree[position] = binaryTree[(position * 2) + 1];
					binaryTree[(position * 2) + 1] = null;
					numberOfNodes--;
				}
				else {
					binaryTree[position] = binaryTree[(position * 2) + 2];
					binaryTree[(position * 2) + 2] = null;
					numberOfNodes--;
				}
			}
			else { // if the has two children.
				int largestPosition = leftLargestEntryFinder(position);
				if (binaryTree[largestPosition] == null) {
					largestPosition = (largestPosition - 2) / 2;
				}
				binaryTree[position] = binaryTree[largestPosition];
				binaryTree[largestPosition] = null;
				numberOfNodes--;
			}
		}
		else { // key not found.
			throw new IllegalArgumentException("The key: " + key + " does not appear in the tree and " + 
		                                      "cannot therefore be removed!");
		}	
	}

	/** Finds the node with the largest entry in the left subtree of the node to be removed.
	 * @param rootIndex The interger position of the node to be removed from the binaryTree array.
	 * @return The integer number of the position of the largest entry in the left subtree.
	 */
	private int leftLargestEntryFinder(int rootIndex) {
		int result  = (rootIndex * 2) + 1;
		while(true) {
			if (binaryTree[result] == null) {
				break;
			}
			result = (result * 2) + 2;
		}
		return result;
	}
	
	/**Detects whether the node at a particular index of the binaryTree array has one child.
	 * @param rootIndex The integer index of the node whose number of children is to be found.
	 * @return True it has one child and false otherwise.
	 */
	private boolean hasOneChild(int rootIndex) {
		boolean result = false;
		if ((!hasLeft(rootIndex) && hasRight(rootIndex)) || 
				(hasLeft(rootIndex) && !hasRight(rootIndex)))
			result = true;
		return result;
	}
		
	@Override
	public void update(K key, V value) {
		int keyIndex = binarySearch(key, 0, false);
		if (keyIndex >= 0) {
			binaryTree[keyIndex].setValue(value);
		}
		else throw new IllegalArgumentException("The key: " + key + " does not appear in the tree, therefore cannot be updated.");
	}

	@Override
	public List<K> keys() {
		List<K> result = new ArrayList<K>();
		getKeys(0, result);
		return result;
	}
	
	/** Traverse the tree in an inorder manner and collects the keys at each node which are in turn added to the given keys list.
	 * @param rootIndex The integer index where to start the recursion.
	 * @param result The object of the list where the keys are to be added.
	 */
	private void getKeys(int rootIndex, List<K> result) {
		for(int counter = 0; counter < capacity; counter++) {
			if (binaryTree[counter] != null) {
				result.add(binaryTree[counter].getKey());
			}
		}
	}

	@Override
	public List<V> values() {
		checkInitialization();
		List<V> result = new ArrayList<V>();
		getValues(0, result);
		return result;
	}

	/** Traverse the tree in an inorder manner and collects the values at each node which are in turn added to the given values list.
	 * @param rootIndex The integer index where to start the recursion.
	 * @param result The object of the list where the values are to be added.
	 */
	private  void getValues(int rootIndex, List<V> result) {
		for (int counter = 0; counter < capacity; counter++) {
			if (binaryTree[counter] != null) {
				result.add(binaryTree[counter].getValue());
			}
		}
	}
	
	@Override
	public V min() {
		V result = null;
		checkInitialization();
		int currentIndex = 0;
		while(binaryTree[currentIndex] != null) {
			currentIndex = (currentIndex * 2) + 1 ;
		}
		currentIndex = (currentIndex -1) / 2 ;
		result = binaryTree[currentIndex].getValue();
		return result;
	}

	@Override
	public V max() {
		V result = null;
		checkInitialization();
		int currentIndex = 0;
		while(binaryTree[currentIndex] != null) {
			currentIndex = (currentIndex * 2) + 2 ;
		}
		currentIndex = (currentIndex - 2) / 2 ;
		result = binaryTree[currentIndex].getValue();
		return result;
	}

	@Override
	public List<V> lessThan(K key) {
		List<V> result = new ArrayList<>();
		lessThanHelper(0, key, result);
		return result;
	}
	
	/**Traverse the entire tree and detects all entries less than the given key which are in turn added to the given result list.
	 * @param rootIndex The index of the binaryTree where to start the traverse from; general 0.
	 * @param key The key object whose inferior entries are to be found.
	 * @param result The object of the list where the found entries are to be added.
	 */
	private void lessThanHelper(int rootIndex, K key, List<V> result) {
		  if (binaryTree[rootIndex] == null)
	            return;
		  if (binaryTree[rootIndex].getKey().compareTo(key) < 0) {
			  result.add(binaryTree[rootIndex].getValue());
		  }
 		  lessThanHelper(rootIndex * 2 + 1, key, result);
	        
 		  lessThanHelper(rootIndex * 2 + 2, key, result);
	}

	@Override
	public List<V> greaterThan(K key) {
		List<V> result = new ArrayList<>();
		greaterThanHelper(0, key, result);
		return result;
	}
	
	/**Traverse the entire tree and detects all entries greater than the given key which are in turn added to the given result list.
	 * @param rootIndex The index of the binaryTree where to start the traverse from; general 0.
	 * @param key The key object whose superior entries are to be found.
	 * @param result The object of the list where the found entries are to be added.
	 */
	private void greaterThanHelper(int rootIndex, K key, List<V> result) {
		  if (binaryTree[rootIndex] == null)
	            return;
		  if (binaryTree[rootIndex].getKey().compareTo(key) > 0) {
			  result.add(binaryTree[rootIndex].getValue());
		  }
		  greaterThanHelper(rootIndex * 2 + 1, key, result);
	        
		  greaterThanHelper(rootIndex * 2 + 2, key, result);
	}
	
	@Override
	public int size() {
		return numberOfNodes;
	}

	@Override
	public int height() {
		checkInitialization();
		int result = heightFinder(0);
		return result - 1;
	}

	/**Traverses the binaryTree array from root and print all entries.
	 * @param index The integer index where to start to traverse.
	 * @param counter The integer value of how much of space is to be printed before displaying the left of right entry of an index.
	 */
	private void preOrder(int index, int counter) {
		checkInitialization();
	    if (index*2+2 >= capacity) {
	        return;
	    }
	    leftPrinter((2 * index) + 1, counter);
		preOrder((2 * index)+1, counter + 3);
		rightPrinter((2 * index) + 2, counter);
		preOrder((2 * index) + 2, counter + 3);
		

	 }
	
	@Override
	public void display() {
		System.out.println("Root: (" + binaryTree[0].getKey() + ", " + binaryTree[0].getValue() + ")");
		preOrder(0, 1);
	}
	
	/**Prints the left entry of a particular index.
	 * @param index The integer index of the binaryTree to be printed.
     * @param counter The integer value of how much of space is to be printed before displaying the left of right entry of an index.
	 */
	private void leftPrinter(int index, int counter) {
		spaceHandler(counter);
		if (binaryTree[index] == null) {
			System.out.println("|--- Left: -");
		}
		else {
			System.out.println("|--- Left: (" + binaryTree[index].getKey() + ", " + binaryTree[index].getValue() + ")");
		}
	}
	
	/**Prints the right entry of a particular index.
	 * @param index The integer index of the binaryTree to be printed.
     * @param counter The integer value of how much of space is to be printed before displaying the right of right entry of an index.
	 */
	private void rightPrinter(int index, int counter) {
		spaceHandler(counter);
	
		if (binaryTree[index] == null) {
			System.out.println("\\--- Right: -");
		}
		else {
			System.out.println("\\--- Right: (" + binaryTree[index].getKey() + ", " + binaryTree[index].getValue() + ")");
		}
	}
	
	/** Display a 1 times the given couter space.
	 * @param counter To be specicied.
	 */
	private static void spaceHandler(int counter) {
		for (int i = 0; i < counter; i++) {
			System.out.print(" ");
		}
	}
	
	@Override
	public boolean isFullTree() {
		return checkIfFull(0);
	}

	@Override
	public void add(K key, V value) {
		checkInitialization();
		int newIndex = findProperPosition(key, 0);
		ensureCapacity(newIndex);
		binaryTree[newIndex] = new BinaryNode<>(key, value);
		numberOfNodes++;
}
	
	/** Find the proper position where a given key is to be added.
	 * @param key The object of the key whose position is to be found.
	 * @param rootIndex The integer index of the root of the binaryTree.
	 * @param found The boolean attribute to check whether the position is found.
	 * @return The integer position where the new entry is to be put.
	 */
	private int findProperPosition(K key, int rootIndex) {
	    if (binaryTree[rootIndex]==null) {
	        return rootIndex;
	    }
	    if (binaryTree[rootIndex].getKey().compareTo(key) > 0) {
	        return findProperPosition(key,rootIndex * 2 + 1);
	    }
	    return findProperPosition(key,rootIndex * 2 + 2);
	}
	
	/**Detects wether the node at a particular key index in the binaryTree has a left child.
	 * @param keyIndex The integer index of the node to be investigated.
	 * @return True if yes and false otherwise.
	 */
	private boolean hasLeft(int keyIndex) {
		boolean result = false;
		if (binaryTree[(keyIndex * 2) + 1] != null) {
			result = true;
		}
		return result;
	}
	
	/**Detects wether the node at a particular key index in the binaryTree has a right child.
	 * @param keyIndex The integer index of the node to be investigated.
	 * @return True if yes and false otherwise.
	 */
	private boolean hasRight(int keyIndex) {
		boolean result = false;
		if (binaryTree[(keyIndex * 2) + 2] != null) {
			result = true;
		}
		return result;
	}
	
	/**Detects if a given rootIndex is a leaf that is it's right and left children are null.
	 * @param rootIndex The integer index of the node to be investigated.
	 * @return True if the node at the specified index is a leaf and false otherwise.
	 */
	private boolean isLeaf(int rootIndex) {
		boolean result = false;
		if (!hasLeft(rootIndex) && (!hasRight(rootIndex))) {
			result = true;
		}
		return result;
	}
	
	/**Computes the hight of the binaryTree.
	 * @param rootIndex The index of the root node in the tree.
	 * @return The integer number of the height of the binaryTree.
	 */
	private int heightFinder(int rootIndex) {
		if (binaryTree[rootIndex] == null) {
			return 0;
		}
		int leftSubTreeHeight = heightFinder(rootIndex * 2 + 1);
		int rightSubTreeHeight = heightFinder(rootIndex * 2 + 2);
		return Math.max(leftSubTreeHeight, rightSubTreeHeight) + 1;
	}
	
	/**Checks wether the binaryTree is full or not.
	 * @param keyIndex The index of the root node of the tree.
	 * @return True if yes and false otherwise.
	 */
	private boolean checkIfFull(int keyIndex) {
		{
	        if(binaryTree[keyIndex] == null) {
	        	return true;
	        }
	        if(binaryTree[keyIndex * 2 + 1] == null && binaryTree[keyIndex * 2 + 2] == null ) {
	            return true;
	        }
	        if((binaryTree[keyIndex * 2 + 1]!=null) && (binaryTree[keyIndex * 2 + 2] !=null)) {
	        	return (checkIfFull(keyIndex * 2 + 1) && checkIfFull(keyIndex * 2 + 2));
	        }
	        return false;
	    }
	}

	/**Traverses the binaryTree and checks if the specified key is found in the tree.
	 * @param key The key object whose value is requested.
	 * @param rootIndex The integer index of the binaryTree.
	 * @return The value of the given key if found and null if not.
	 */
	private int binarySearch(K key, int rootIndex, boolean found) {
		int result = -1;
		if (!found && rootIndex <= capacity) {
			if (binaryTree[rootIndex] != null) {
				if (key.compareTo(binaryTree[rootIndex].getKey()) == 0) {
					found = true;
					result = rootIndex;
					return result;
				}
				else if (key.compareTo(binaryTree[rootIndex].getKey()) > 0) {
					result = binarySearch(key, (rootIndex * 2) + 2, found);
				}
				else if (key.compareTo(binaryTree[rootIndex].getKey()) < 0) {
					result = binarySearch(key, (rootIndex * 2) + 1, found);
				}
			}
		}
		return result;
	}
	
	/** Initiliazes the data fields in the constructor.
	 * @param keys The keys list object to be added into the tree.
	 * @param values The values list object to be added into the tree.
	 * Pre-conditions: The given keys and values list are not null.
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	
	private void initializeDataFields(List<K> keys, List<V> values) {
		if (keys.size() <= 0 || values.size() <= 0) {
			throw new IllegalArgumentException("Make sure the given list of keys and values is initialized and not empty.");
		}
		ensureKeysValuesSizes(keys, values);
		this.keys = new ArrayList<>();
		this.values = new ArrayList<>();
		numberOfNodes = keys.size();
		capacity = numberOfNodes;
		binaryTree = new BinaryNode[capacity];
		copyKeys(keys);
		copyValues(values);
		sortByKeys();
		setTree(0, numberOfNodes - 1, 0);
		initialized = true;
	}
	
	/**
	 * Create an initial binary tree making use of the given lists of keys and values.
	 * @param root The current root object where the subtree is to be built.
	 * @param first The integer index of the first element of the list.
	 * @param last The integer index of the last element of the list.
	 */
	private BinaryNode<K, V> setTree(int start, int end, int rootIndex) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        ensureCapacity(rootIndex);
        BinaryNode<K, V> node = new BinaryNode<>(keys.get(mid), values.get(mid));
        binaryTree[rootIndex] = node;
        binaryTree[rootIndex * 2 + 1] = setTree(start, mid - 1, rootIndex * 2 + 1);
        binaryTree[rootIndex * 2 + 2] = setTree(mid + 1, end, rootIndex * 2 + 2);
        return node;
    }

	/**Creates a copy of the values in order not make any further change on the very values affect the original list.
	 * @param values The object of the original values list.
	 */
	private void copyValues(List<V> values) {
		int index;
		for (index = 0; index < values.size(); index++) {
				this.values.add(index, values.get(index));;
		}
	}
	
	/**Creates a copy of the keys in order not make any further change on the very keys affect the original list.
	 * @param keys The object of the original keys list.
	 */
	private void copyKeys(List<K> keys) {
		int index;
		for (index = 0; index < keys.size(); index++) {
			this.keys.add(index, keys.get(index));
		}
	}
	
	/**Checks and ensures that the given keys and values list have the same size.
	 * @param keys The object of the list of keys to be added into the tree.
	 * @param values The object of the list of values to be added into the tree.
	 * @throws SecurtiyException in accordance.
	 */
	private void ensureKeysValuesSizes(List<K> keys, List<V> values) {
		if (keys.size() != values.size()) {
			throw new SecurityException("The given list of keys and values appear to have differents sizes!");
		}
	}
	
	/**Ensures that the initialization of the data fields has been carried out successfully.
	 * Unless, no other operation are not to be allowed. */
	private void checkInitialization() {
		if (!initialized) {
			throw new IllegalStateException("The data fields could not get initialized successfully and no further operations are allowed!");
		}
	}
		
	/**Resizes the binaryTree array if necessary.
	 * @param root The integer index of the root in the binaryTree array.
	 */
	private void ensureCapacity(int root) {
		if (root * 2 + 2 >= binaryTree.length) {
			int newLength = 2* capacity;
			capacity = newLength;
			binaryTree = Arrays.copyOf(binaryTree, capacity);
		} // end if.
	} // ensureCapacity;
	
	/**Sorts the keys and values lists alphabetically.*/
	private void sortByKeys() {
		int n = keys.size();
		for (int index = 0; index < n - 1; index++) {
			for (int j = 0; j < n - index - 1; j++) {
				K keyTemp = keys.get(j);
				V valueTemp = values.get(j);
				if (keys.get(j).compareTo(keys.get(j + 1)) > 0) {
					keys.set(j, keys.get(j + 1));
					keys.set(j + 1, keyTemp);
					values.set(j, values.get(j + 1));
					values.set(j + 1, valueTemp);
				}
			}
		}
	} // end sortByKeys.

	

	
	/** The BinaryNode private class illustrating the properties and behaviours of any Node in the tree.
	 * @param <K> The type of the Keys of the node.
	 * @param <V> The type of the values of the node.
	 */
	@SuppressWarnings("hiding")
	public class BinaryNode<K extends Comparable<?super K>, V> {
		
		private K key;
		private V value;
		
		public BinaryNode() {
			setData(null, null);
		
		} // end default constructor.

	    public BinaryNode(K key, V value) {
	    	setData(key, value);
	    
	    } // end constructor.
		
		public void setData(K key, V value) {
			this.key = key;
			this.value = value;
		}
	
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public void setKey(K key) {
			this.key = key;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
	
	} // end BinaryNode.

} // end VanillaBinarySTree.
