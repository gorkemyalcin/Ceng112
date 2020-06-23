package bussinessLayer;

/**
 * A ListSorter class describing methods for sorting a doublyLinkedList.
 * @author Ben-Malik_Gorkem
 *
 */
public class ListSorter {
	
	/** Sortes the given shoppingList in place and return nothing.
	 * @param shoppingList The list of the objects to be sorted in accordance with their prices.
	 */
	public static void sort(ListInterface<ItemPrice> shoppingList) {
		ItemPrice currentItem = null;
		for (int counter = 2; counter <= shoppingList.getLength(); counter++) {
			ItemPrice temp = shoppingList.remove(counter);
			int j = counter;
			
			while (j > 1 && (shoppingList.getEntry(j - 1).getPrice() > temp.getPrice())) {
				currentItem = shoppingList.remove(j - 1);
				shoppingList.add(currentItem, j);
				j--;
			}
			shoppingList.add(temp, j);
		}
	}// end sort.
	
	
	/** Takes a shoppingList, makes a copy of it, sots the copy list and returns it.
	 * @param shoppingList The object of the shoppingList to be sorted.
	 * @return A copy of the sorted version of the list.
	 */
	public static ListInterface<ItemPrice> sorted(ListInterface<ItemPrice> shoppingList) {
		ListInterface<ItemPrice> sortedList = copyItems(shoppingList);
		for (int counter = 2; counter <= sortedList.getLength(); counter++) {
			ItemPrice temp = sortedList.remove(counter);
			int j = counter;
			
			while (j > 1 && (sortedList.getEntry(j - 1).getPrice() > temp.getPrice())) {
				sortedList.add(sortedList.remove(j - 1), j);
				j--;
			}
			sortedList.add(temp, j);
		}
		
		return sortedList;
	}// end sorted.
	
	/** Makes a copy of the shoppingList. To be used as a helper method while sorting the shoppingList.
	 * @param items The items whose copy is to be made.
	 * @return A copy of the given item list.
	 */
	private static ListInterface<ItemPrice> copyItems(ListInterface<ItemPrice> items) {
		ListInterface<ItemPrice> shoppingList = new List<ItemPrice>();
		for (int counter = 1; counter <= items.getLength(); counter++) {
			shoppingList.add(items.getEntry(counter));
		}
		return shoppingList;
	} 
	
} // end ListSorter.
