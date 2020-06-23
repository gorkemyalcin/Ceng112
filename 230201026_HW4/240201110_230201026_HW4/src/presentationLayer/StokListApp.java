package presentationLayer;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import businessLayer.IBinarySearchTree;
import businessLayer.VanillaBinarySTree;

/** A client to the IBinarySearchTree interface.
 * @author Ben-Malik_Gorkem
 */
public class StokListApp {

	public static void main(String[] args) {
		ArrayList<String> k = new ArrayList<String>();
		k.add("tea");
		k.add("towel");
		k.add("juice");
		k.add("glass");
		k.add("baklava");
		k.add("irun");
		k.add("soap");
		k.add("soup");
		k.add("rice");
		k.add("bread");
		k.add("beer");
		k.add("coke");
		k.add("pasta");
		k.add("water");
		k.add("coffee");


		ArrayList<Integer> v = new ArrayList<Integer>();
		v.add(17);
		v.add(24);
		v.add(5);
		v.add(36);
		v.add(24);
		v.add(29);
		v.add(100);
		v.add(70);
		v.add(56);
		v.add(44);
		v.add(45);
		v.add(26);
		v.add(4);
		v.add(41);
		v.add(48);

		simulator(k, v);
		
	} // end main.
	
	public static void printMenu() {
		System.out.println("\n.........................Welcome to IZTECH StockList.........................\n");
		System.out.println("For your option of choice do enter its corresponding number:\n");
		System.out.println("0.Get the value associated with a particular key");
		System.out.println("1.Add a key alongside with its value into the tree.");
		System.out.println("2.Remove a value associated with a particular key from the tree");
		System.out.println("3.Update a particular key's value with a new value");
		System.out.println("4.Display all keys in the tree");
		System.out.println("5.Display all values in the tree");
		System.out.println("6.Display the value associated with the smallest key in the tree");
		System.out.println("7.Display the value associated with the largest key in the tree");
		System.out.println("8.Display all values associated with all keys less than a particular key.");
		System.out.println("9.Display all values associated with all keys greater than a particular key.");
		System.out.println("10.Display the number of keys in the tree");
		System.out.println("11.Display the largest height of the tree");
		System.out.println("12.Display the the tree");
		System.out.println("13.Detects whether the tree is full or not");
		System.out.println("14.Stop");
	} // end printMenu.
	
	public static void simulator(ArrayList<String> k, ArrayList<Integer> v) {
		IBinarySearchTree<String, Integer> ourTree = new VanillaBinarySTree<>(k, v);
		while(true) {
			printMenu();
			@SuppressWarnings("resource")
			Scanner userInput = new Scanner(System.in);
			int choice = userInput.nextInt();
			if (choice == 14) {
				System.out.println("Exiting the program...");
				break;
			}
			else if (choice == 0) {
				System.out.println("Enter the key of whose value you'd like to request");
				userInput = new Scanner(System.in);
				String key = userInput.nextLine();
				Integer result = ourTree.get(key);
				if (result == null) {
					System.out.println(key + " does not appear in the tree!");
				}
				else {
					System.out.println("The value associated with "  + key + ": " + result);
				}
			}
			else if (choice == 1) {
				System.out.println("Enter the new key to be added");
				userInput = new Scanner(System.in);
				String key = userInput.nextLine();
				System.out.println("Enter the new value to be added");
				userInput = new Scanner(System.in);
				Integer value = userInput.nextInt();
				ourTree.add(key, value);
			}
			else if (choice == 2) {
				System.out.println("Enter the key to be removed");
				userInput = new Scanner(System.in);
				String key = userInput.nextLine();
				ourTree.remove(key);
			}
			else if (choice == 3) {
				System.out.println("Enter the key whose value is to be updated");
				userInput = new Scanner(System.in);
				String key = userInput.nextLine();
				System.out.println("Enter the new value to be replaced with the existing one");
				userInput = new Scanner(System.in);
				Integer newValue= userInput.nextInt();
				ourTree.update(key, newValue);
			}
			else if (choice == 4) {
				List<String> result = ourTree.keys();
				System.out.println("\nThe keys in the tree are: ");
				for (String key: result) {
					System.out.printf("%15s", key);
				}
				System.out.println("\n\n");
			}
			else if (choice == 5) {
				List<Integer> result = ourTree.values();
				System.out.println("\nThe values in the tree are: ");
				for (Integer value: result) {
					System.out.printf("%10s", value);
				}
				System.out.println("\n\n");
			}
			else if(choice == 6) {
				System.out.println("The value of the smallest key in the tree: " + ourTree.min());
			}
			else if(choice == 7) {
				System.out.println("The value of the largest key in the tree: " + ourTree.max());
			}			
			else if (choice == 8) {
				System.out.println("Enter the key whose smallest elements' values you request:");
				userInput = new Scanner(System.in);
				String key = userInput.nextLine();
				List<Integer> result = ourTree.lessThan(key);
				for (int counter = 0; counter < result.size(); counter++) {
					System.out.printf("%8s", result.get(counter));
				}
			}
			else if (choice == 9) {
				System.out.println("Enter the keys whose largest elements' values you request:");
				userInput = new Scanner(System.in);
				String key = userInput.nextLine();
				java.util.List<Integer> result = ourTree.greaterThan(key);
				for (int counter = 0; counter < result.size(); counter++) {
					System.out.printf("%8s", result.get(counter));
				}
			}
			else if (choice == 10) {
				System.out.println("The number of keys in the tree: " + ourTree.size());
			}
			else if (choice == 11) {
				System.out.println("The largest height of the tree: " + ourTree.height());
			}
			else if(choice == 12) {
				System.out.println("....................................Current Tree....................................");
				ourTree.display();
			}
			else if (choice == 13) {
				System.out.println("The tree is full: " + ourTree.isFullTree());
			}
		}// end while.
		
	}

} // end StokListApp.
