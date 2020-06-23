package presentationLayer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import bussinessLayer.*;

/**A TestClass that tests the shopping operations. 
 * @author Ben-Malik_Gorkem
 *
 */
public class ShoppingApp {
	
	public static void main(String args[]) {
		System.out.println("...............................Very warm welcome to Iztech Shopping Center ...............................\n\n");

		String fileName1 = "//home//ben-malik//Documents//Java_Ceng112//240201110_230201026_HW3//src//presentationLayer//ShoppingList.txt";
		String fileName2 = "//home//ben-malik//Documents//Java_Ceng112//240201110_230201026_HW3//src//presentationLayer//ShoppingPriority.txt";
		
		ListInterface<ItemPrice> shoppingItems = shoppingListReader(fileName1); // Read the shoppingList file.
		shoppingItems = ListSorter.sorted(shoppingItems); // sort the shoppingList in place.

		PriorityQueueInterface<ItemPriority> itemPriorities = shoppingPriorityReader(fileName2); // Read the shoppingPriority file.
		PriorityQueueInterface<ItemPriority> mergedShoppingList = listAndPrioritiesMerger(shoppingItems, itemPriorities); // merge the shoppingList and the shoppingPriority.
		double budget = getBudget(); // get the budget from the user.
		
		budget = itemsToBeBoughtDisplayer(mergedShoppingList, budget); // display the items to be bought to the console.
		System.out.println("Remaining budget: " + budget + " Tl"); // display the remaining of the budget.
		
		System.out.println("\n\n\n --------------------------Thank you for concidering Iztech Shopping Center. Do come again --------------------");
	} // end main.
	
	
	
	
	
	
	/** Reads the shoppingList file, creates an ItemPrice objet and adds it to the list.
	 * @param fileName The fileName to be read.
	 * @return The shoppingList of the file read.
	 */
	public static ListInterface<ItemPrice> shoppingListReader(String fileName) {
		ListInterface<ItemPrice> shoppingList = new List<ItemPrice>();
		try {
			FileReader f = new FileReader(fileName);
			BufferedReader inputStream = new BufferedReader(f);
			StringTokenizer wordFinder;
			String line = inputStream.readLine();
			String name = null;
			double price = 0.0;
			while (line != null) {
				wordFinder = new StringTokenizer(line, ",");
				while (wordFinder.hasMoreTokens()) {
					
					name = wordFinder.nextToken();
					price = Double.parseDouble(wordFinder.nextToken());
					shoppingList.add(new ItemPrice(name, price));
				}	

				line = inputStream.readLine();
			}
			inputStream.close();
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		catch (IOException a) {
			System.out.println("Error reading from " + fileName);
			System.out.println(a.getMessage());
		}	
		return shoppingList;
	} // end shoppingListRea
	
	
	/** Reads the shoppingPriority file, stores the itemPriority into the priorirtyQueue.
	 * @param fileName The name of the file to be read.
	 * @return The ItemPriority stored as priorityQueue.
	 */
	public static PriorityQueueInterface<ItemPriority> shoppingPriorityReader(String fileName) {
		PriorityQueueInterface<ItemPriority> shoppingPriorities = new PriorityQueue();
		try {
			FileReader f = new FileReader(fileName);
			BufferedReader inputStream = new BufferedReader(f);
			StringTokenizer wordFinder;
			String line = inputStream.readLine();
			String name = null;
			int priority = 1;
			while (line != null) {
				wordFinder = new StringTokenizer(line, ",");
				while (wordFinder.hasMoreTokens()) {
					name = wordFinder.nextToken();
					priority = Integer.parseInt(wordFinder.nextToken());
					ItemPriority p = new ItemPriority(name, priority);
					shoppingPriorities.add(p);
				}	

				line = inputStream.readLine();
			}
			inputStream.close();
		}
 		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		catch (IOException a) {
			System.out.println("Error reading from " + fileName);
			System.out.println(a.getMessage());
		}
		return shoppingPriorities;
	} // end shoppingPriorityReader.
	
	/** Merges the shoppingList and the shoppingPriority into a priorityQueue.
	 * @param shoppingItems The object of the shoppingItems.
	 * @param itemPriorities The object of the itemPriority.
	 * @return The merged Priority queue.
	 */
	private static PriorityQueueInterface<ItemPriority> listAndPrioritiesMerger(ListInterface<ItemPrice> shoppingItems, PriorityQueueInterface<ItemPriority> itemPriorities) { 
		PriorityQueueInterface<ItemPriority> mergedShoppingList = new PriorityQueue();
		assert (shoppingItems.getLength() == itemPriorities.getSize());
		int counter = shoppingItems.getLength();
		while (counter >= 1) {
			ItemPriority newItem = itemPriorities.remove();
			ItemPrice item= shoppingItems.getEntry(counter);
			
			newItem.setPrice(item.getPrice());
			mergedShoppingList.add(newItem);
			counter--;
		}
			
		return mergedShoppingList;
	} // end listAndPrioritiesMerger.
	
	/** Gets the budget from the user.
	 * @return the double number of the user.
	 */
	private static double getBudget() {
		Scanner reader = new Scanner(System.in); 
		System.out.println("Enter your budget in order to visualize available products: ");
		double budget = reader.nextDouble(); 
		reader.close();
		return budget;
	} // end getBudget.
	
	/** Displays the items to be bought.
	 * @param mergedShoppingList The object of the mergedShoppingList from which the items to be bought are to be displayed.
	 * @param budget The budget of the user.
	 */
	private static double itemsToBeBoughtDisplayer(PriorityQueueInterface<ItemPriority> mergedShoppingList, double budget) {
		double price = 0.0;
		double remainingBudget = budget;
		System.out.println("Products that could be bought with a budget of " + budget + " Tl: \n");

		while (!mergedShoppingList.isEmpty() && remainingBudget >= mergedShoppingList.peek().getPrice()) {
			price = mergedShoppingList.peek().getPrice();
			remainingBudget = remainingBudget - price;
			System.out.println(mergedShoppingList.remove().toString());
		}
		if (budget < mergedShoppingList.peek().getPrice()) {
			System.out.println("Your very budget does not allow you to purchase any of the products of the day, I'm afraid! ");
		}
		System.out.println();
		return remainingBudget;
	} // end itemsToBeBoughtDisplayer.

} // end ShoppingApp.
