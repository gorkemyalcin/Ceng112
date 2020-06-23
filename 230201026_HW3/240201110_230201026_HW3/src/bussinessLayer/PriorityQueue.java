package bussinessLayer;
/**
 * An implementation of the PriorityQueueInterface using a singlyLinkedList.
 * @author Ben-Malik_Gorkem 
 *
 */
public class PriorityQueue implements PriorityQueueInterface<ItemPriority> {
	
	private int numberOfNodes;
	private Node firstNode;
	private Node lastNode;
	
	public PriorityQueue() {
		initializeDataFields();
	}

	@Override
	public boolean isEmpty() {
		boolean result;
		if (numberOfNodes == 0) {
			assert firstNode == null;
			result = true;
		}
		else {
			assert firstNode != null;
			result = false;
		}
		return result;
	}

	@Override
	public int getSize() {
		return numberOfNodes;
	}

	@Override
	public void clear() {
		for (int counter = 1; counter <= numberOfNodes; counter++) {
			remove();
		}
		numberOfNodes = 0;
		assert ((firstNode == null) && (lastNode == null) && (numberOfNodes == 0));
	}

	@Override
	public void add(ItemPriority newEntry) {
		Node newNode = new Node(newEntry);
		Node currentNode = firstNode;
		int counter;
		if (firstNode == null) {
			firstNode = newNode;
			lastNode = firstNode;
		}
		else {
			for (counter = 1; counter <= numberOfNodes; counter++) {
				if (priorityCompare(currentNode, newNode) == -1) {
					newNode.setNextNode(currentNode);  // TODOS refatory to be made here.
					ensureFirstNode(counter, newNode);
					break;
				}else if (priorityCompare(currentNode, newNode) == 0 ){
					if (priceCompare(currentNode, newNode) == 1) {
						newNode.setNextNode(currentNode);
						ensureFirstNode(counter, newNode);
						break;
					}
					else {
						newNode.setNextNode(currentNode.getNextNode()); // TODOS refactory to be made here.
						currentNode.setNextNode(newNode);
						ensureFirstNode(counter, newNode);
						break;
					}
				}
				else if (priorityCompare(currentNode, newNode) == 1){
					newNode.setNextNode(currentNode.getNextNode());
					currentNode.setNextNode(newNode);
					break;
				}
				currentNode = currentNode.getNextNode();		
			}
		}		
		numberOfNodes++;
	} // end add.

	@Override
	public ItemPriority remove() {
		ItemPriority head = null;
		if (isEmpty()) {
			throw new SecurityException("Cannot remove from an empty queue.");
		}
		else {
			head = firstNode.getItem();
			firstNode.setItem(null);
			firstNode = firstNode.getNextNode();
			numberOfNodes--;
			return head;
		}	
	}

	@Override
	public ItemPriority peek() {
		ItemPriority head = null;
		if (isEmpty()) {
			throw new SecurityException("Cannot peek from an empty queue.");
		}
		head = firstNode.getItem();
		return head;
	}
	

	/**
	 * Initializes the data fileds.
	 */
	private void initializeDataFields() {
		firstNode = null;
		lastNode = null;
		numberOfNodes = 0;
	}

	/**Detects the node of a specific position.
	 * @param position The position whose node is to be detected.
	 * @return The node of the specified position.
	 */
	@SuppressWarnings("unused")
	private Node getNodeAt(int givePosition) {
		assert (firstNode != null) && (givePosition >= 1) && (givePosition <= numberOfNodes);
		Node currentNode = firstNode;
		for (int counter = 1; counter < givePosition; counter++) {
			currentNode = currentNode.getNextNode();
			assert currentNode != null;
		}
		return currentNode;
	}
	
	/**Compares the priorities of two distincts nodes.
	 * @param firstNode The object of the first node.
	 * @param secondNode The object of the second node.
	 * @return 1 if the first node's prioritie is greater than the second one.
	 *         -1 if the first node's prioritie is less than the second one.
	 *         0 if both nodes have the same prioritie.
	 */
	private int priorityCompare(Node firstNode, Node secondNode) {
		int result = 1;
		if (firstNode.getItem().getPriority() < secondNode.getItem().getPriority()) {
			result = -1;
		}
		else if (firstNode.getItem().getPriority() == secondNode.getItem().getPriority()) {
			result = 0;
		}
		return result;
	}
	
	/**Compares the prices of two distincts nodes.
	 * @param firstNode The object of the first node.
	 * @param secondNode The object of the second node.
	 * @return 1 if the first node's price is greater than the second.
	 *         -1 if the first node's price is less than the second one.
	 *         0 if both nodes have the same price.
	 */
	private int priceCompare(Node firstNode, Node secondNode) {
		int result = 1;
		if (firstNode.getItem().getPrice() < secondNode.getItem().getPrice()) {
			result = -1;
		}
		else if (firstNode.getItem().getPrice() == secondNode.getItem().getPrice()) {
			result = 0;
		}
		return result;
	}
	
	/**Detects whehter the newly added item is at the head of the queue and update the firstNode accordingly.
	 * @param counter The index at which the newNode has been added.
	 * @param newNode The object of the new node added.
	 */
	private void ensureFirstNode(int counter, Node newNode) {
		if (counter == 1) {
			firstNode = newNode;
		}
	}
	
	/**
	 * A Node class specifying the features of a particular node.
	 */
	private class Node {
		
		private ItemPriority item;
		private Node next;
		
		@SuppressWarnings("unused")
		public Node() {
			this.item = null;
			this.next = null;
		}
		 
		public Node(ItemPriority item) {
			setItem(item);
			setNextNode(null);
		}
		
		@SuppressWarnings("unused")
		public Node(ItemPriority item, Node next) {
			setItem(item);
			setNextNode(next);
		}
		
		public ItemPriority getItem() {
			return item;
		}
		
		public void setItem(ItemPriority item) {
				this.item = item;
		}
		
		public Node getNextNode() {
			return next;
		}
		
		public void setNextNode(Node next) {
			this.next = next;
		}
	} // end Node.
	
} // end SinglyLinkedList.
