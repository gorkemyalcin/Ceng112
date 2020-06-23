package bussinessLayer;

/** A class illustrating the operations of the list using doublyLinkedList.
 * @author Ben-Malik_Gorkem
 *
 * @param <T>
 */
public class List<T> implements ListInterface<T> {
	
	private int numberOfNodes;
	private Node firstNode; // holds informatin of the firstNode.
    private Node lastNode; // holds information of the lastNode.
    
	public List() {
		initializeDataFields();
	} // end constructor.
	
	@Override
	public void add(T item) {
		Node newNode = new Node(item);
		newNode.setNextNode(null);
		
		if (firstNode == null) {
			newNode.setPreviousNode(null);
			firstNode = newNode;
		}
		else {
			lastNode.setNextNode(newNode);
		}
		newNode.setPreviousNode(lastNode);
		lastNode = newNode;
		numberOfNodes++;
	} // end add.
	
	
	@Override
	public void add(T item, int newPosition) {
		if (newPosition >= 1 && newPosition <= numberOfNodes + 1) {
			Node newNode = new Node(item);
			if (newPosition == 1) {
				newNode.setNextNode(firstNode);
				firstNode.setPreviousNode(newNode);
				firstNode = newNode;
				lastNode = newNode;
				numberOfNodes++;
			}
			else {
				Node nodeBefore = getNodeAt(newPosition - 1);
				if (nodeBefore.getNextNode() == null) {
					lastNode.setNextNode(newNode);
					newNode.setPreviousNode(lastNode);
					lastNode = newNode;
					numberOfNodes++;
				}
				else {
					Node nodeAfter = nodeBefore.getNextNode();
					nodeAfter.setPreviousNode(newNode);
					newNode.setNextNode(nodeAfter);
					newNode.setPreviousNode(nodeBefore);
					nodeBefore.setNextNode(newNode);
					numberOfNodes++;
				}
			}
			
		}
		else {
			throw new IndexOutOfBoundsException("The new position should be in bound in order to be able to add an element at itself.");
		}
		
	} // end add.
	
	@Override
	public void clear() {
		for (int counter = 1; counter < numberOfNodes; counter++) {
			remove();
			numberOfNodes--;
		}
		assert ((firstNode == null) && (lastNode == null) && (numberOfNodes == 0));
	} // end clear.
	
	@Override
	public T remove() {
		T last = null;
		if (isEmpty()) {
			throw new SecurityException(" Dear user, you cannot remove from an empty list");
		}
		last = lastNode.getItem();
		lastNode = lastNode.getPreviousNode();
		return last;
	} // end remove.
	
	@Override
	public T remove(int givenPosition) {
		T entry = null;
		checkPosition(givenPosition);
		assert !isEmpty();
		Node desiredNode = getNodeAt(givenPosition);
		entry = desiredNode.getItem();
		if (desiredNode.getPreviousNode() == null) {
			firstNode = firstNode.getNextNode();
		}
		else if (desiredNode.getNextNode() == null) {
			lastNode = desiredNode.getPreviousNode();
			lastNode.setNextNode(null);
		}
		else {
			Node nodeBefore = desiredNode.getPreviousNode();
			Node nodeAfter = desiredNode.getNextNode();
			nodeBefore.setNextNode(nodeAfter);
			nodeAfter.setPreviousNode(nodeBefore);
		}
		numberOfNodes--;
		return entry;
	} // end remove at givenPosition.

	@Override
	public T replace(int givenPosition, T newEntry) {
		checkPosition(givenPosition);
		assert !isEmpty();
		Node desiredNode = getNodeAt(givenPosition);
		T originalItem = desiredNode.getItem();
		desiredNode.setItem(newEntry);
		return originalItem;
	} // end replace.

	@Override
	public T getEntry(int givenPosition) {
		T result = null;
		checkPosition(givenPosition);
		assert !isEmpty();
		Node desiredNode = getNodeAt(givenPosition);
		result = desiredNode.getItem();
		return result;
	} // end getEntry.

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[numberOfNodes];
		int index = 0; 
		Node currentNode = firstNode;
		while ((index < numberOfNodes) && (currentNode != null)) {
			result[index] = currentNode.getItem();
			currentNode = currentNode.getNextNode();
			index++;
		}
		return result;
	} // end toArray.
	
	@Override
	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.getItem())) {
				found = true;
				break;
			}
			else {
				currentNode = currentNode.getNextNode();
			}
		}
		return found;
	} // end contains.
	 
	@Override
	public int getLength() {
		return numberOfNodes;
	} // end getLength.

	@Override
	public boolean isEmpty() {
			boolean result = false;
			if (numberOfNodes == 0) {
				assert firstNode == null;
				result = true;
			}
			else {
				assert firstNode != null;
			}
			return result;
	} // end isEmpty.

	
	/**
	 * Initializes the datafileds. to be used in constructor.
	 */
	private void initializeDataFields() {
		firstNode = null;
		lastNode = null;
		numberOfNodes = 0;
	}
	
	/** Detects whehter the givenPositin is inbounds
	 * @throws IndexOutOfBoundsException in accordance with the check result.
	 * @param newPosition The position whose match within the bound is the checked.
	 */
	private void checkPosition(int newPosition) {
		if ((newPosition >= 1) && (newPosition <= numberOfNodes)) {
			return;
		}
		else {
			throw new IndexOutOfBoundsException("Invalid input for the newPosition. Do check and retry again please.");
		}
	}
	
	/** A helper mehtod that get the node at a givenPosition.
	 * @param position The position whose node is desired.
	 * @return The node at the position.
	 */
	private Node getNodeAt(int position) {
		assert firstNode != null && (1 <= position) && (position <= numberOfNodes);
		Node currentNode = firstNode;
		for (int counter = 1; counter < position; counter++) {
			currentNode = currentNode.getNextNode();
			assert currentNode != null;
		}
		return currentNode;
	} // end getNodeAt.
	
	 /**An Immutable class defining the properties of a node. */
	private class Node {
	        T item;
	        Node next;
	        Node previous;
	 
	        public Node(Node next, T item, Node previous) {
	            this.item = item;
	            this.next = next;
	            this.previous = previous;
	           
	        }
	        
	        public Node(T item) {
	        	setItem(item);
	        	this.next = null;
	        	this.previous = null;
	        }
	        
	        @SuppressWarnings("unused")
			public Node() {
	        	this(null, null, null);
	        }
	        
	        public void setNextNode(Node next) {
	        	this.next = next;
	        }
	        
	        public void setPreviousNode(Node previous) {
	        	this.previous = previous;
	        }
	        
	        public Node getPreviousNode() {
	        	return previous;
	        }
	        
	        public Node getNextNode() {
        		return next;
	        }
	        
	        public T getItem() {
	        	return item;
	        }
	        
	        public void setItem(T item) {
	        	this.item = item;
	        } 
	    } // end Node.

} // end DoublyLinkedList
