package businessLayer;

import java.util.List;
import java.util.ArrayList;

/**
 * 
 * A class that implements the TravelTable interface using an array. 
 * @author Go 	rkem_Ben-Malik
 *
 * 
 */
public class FlightTable implements TravelTableInterface {
	
	private boolean[][] flightTable;
	private static int tableSize = 0;
	private ArrayList<String> destinationsCopy;
 	
	public FlightTable(ArrayList<String> destinationsArray) {
		if (isInitialized(destinationsArray)) {
			this.destinationsCopy = destinationsArray;
			if (!checkIzmir(this.destinationsCopy)) {
				destinationsArray.add("Izmir");
				System.out.println("Izmir has been added to the destinations Array");
			}
			
			FlightTable.tableSize = destinationsArray.size();
			flightTable = new boolean[FlightTable.tableSize][FlightTable.tableSize];
			falsifyFlightTable();
			addFlightFromIzmir(this.destinationsCopy);
		}
		else {
			throw new IllegalArgumentException("Destination array has to be created and cannot be null");
		}
		
	} // End of constructor.

	
	@Override
	public boolean addConnection(String destinationA, String destinationB) {
		// TODO Auto-generated method stub
		boolean flag = false;

		if (verifyDestinations(destinationA, destinationB)) {
			int destinationAIndex = getIndexOfDestination(destinationA);
			int destinationBIndex = getIndexOfDestination(destinationB);
			flightTable[destinationAIndex][destinationBIndex] = true;
			flightTable[destinationBIndex][destinationAIndex] = true;
			flag = true;
		}
		
		return flag;
	} // end add

	@Override
	public boolean removeConnection(String destinationA, String destinationB) {
		// TODO Auto-generated method stub
		boolean flag = false;

		if (verifyDestinations(destinationA, destinationB)) {
			if (isConnection(destinationA, destinationB)) {
				int destinationAIndex = getIndexOfDestination(destinationA);
				int destinationBIndex = getIndexOfDestination(destinationB);
				flightTable[destinationAIndex][destinationBIndex] = false;
				flightTable[destinationBIndex][destinationAIndex] = false;
				flag = true;
			}
			else {
				throw new IllegalStateException("I'm afraid but there is no flight between " + destinationA + " and " + destinationB + " desitinations!");
			}
		}
		else {
			throw new IllegalStateException("Make sure that the very destinations exist in the destinations array and are not identical");
		}

		return flag;
	} // end remove.

	@Override
	public boolean renameDestination(String oldDestination, String newDestination) {
		int oldDestinationIndex;
		boolean flag = false;
		if (isDestination(oldDestination) ) {
			if (!isDestination(newDestination)) {
				oldDestinationIndex = getIndexOfDestination(oldDestination);
				destinationsCopy.set(oldDestinationIndex,newDestination);
				System.out.println(destinationsCopy);
				flag = true;													
				}					
			else {
				throw new IllegalStateException("Make sure that the new destination does not exist in the destination array.");
			}
		}
		else {
			throw new IllegalStateException("Make sure that the very destination exist in the destinations array");
			}		
		return flag;
	}
	// end remove.
	// end of modifiers

	@Override
	public boolean isDestination(String destination) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		for (String arrayDestination: destinationsCopy) {
			if (arrayDestination.toLowerCase().equals(destination.toLowerCase())) {
				flag = true;
			}
		}
		return flag;
	} // end check if is destination.

	@Override
	public boolean destinationHasConnection(String destination) {
		boolean flag = false;
		if (isDestination(destination)) {
			int indexOfDestination = getIndexOfDestination(destination);
			for(int i = 0; (i < tableSize) && (i != indexOfDestination) ; i++) {
				if (flightTable[indexOfDestination][i] == true) {
					flag = true;
					break;
				}				
			}
		}
		else {
			throw new IllegalStateException("Given destination " + destination + " does not exist.");
		}
		return flag;
	} // end check if has a flight or not.

	@Override
	public int numberOfDestinationsWithConnections() {
		int result = 0;
		
		if (isInitialized(destinationsCopy)) {
			for (int i = 0; i < tableSize; i++) {
				for (int j = 0; j < tableSize; j++) {
					if (flightTable[i][j] == true) {
						result++;
						break;
					}
				}
			}
		}
		else {
			throw new IllegalArgumentException("Destination array has to be created and cannot be null");
		}
		return result;
	} // end method.
 
	@Override
	public int numberOfConnections() {
		int result = 0;
		
		if (isInitialized(destinationsCopy)) {
			for (int i = 0; i < tableSize; i++) {
				for (int j = 0; j < tableSize; j++) {
					if (flightTable[i][j] == true) {
						result++;
					}
				}
			}
		}
		else {
			throw new IllegalArgumentException("Destination array has to be created and cannot be null");
		}
		return result;
		
	} // end check of the number of flights.

	@Override
	public boolean hasConnections(String destinationA, String destinationB) {
		boolean flag = false;
		int indexOfDestinationA = getIndexOfDestination(destinationA);
		int indexOfDestinationB = getIndexOfDestination(destinationB);
		if(flightTable[indexOfDestinationA][indexOfDestinationB] == true) {
			flag = true;			
		}
		return flag;
	} // end check of the existance of a flight between two destinations.

	@Override
	public void displayTable() {
        for(boolean[] row : flightTable) {
            printFlightTable(row);
        }
	}
	@Override
	public ArrayList<String> destinationsWithConnection(String destination) {
		List<String> result = new ArrayList<String>();
		int indexOfDestination=getIndexOfDestination(destination);
		for(int i = 0; i < tableSize; i++) {
			if (flightTable[indexOfDestination][i] == true) {
				result.add(destinationsCopy.get(i));
			}		
		}
		return (ArrayList<String>) result;
	}

	@Override
	public ArrayList<String> destinationsOfADeparture(String departure) {
		List<String> result = new ArrayList<String>();
		int indexOfDeparture=getIndexOfDestination(departure);
		for(int i = 0; i < tableSize; i++) {
			if (flightTable[i][indexOfDeparture] == true) {
				result.add(destinationsCopy.get(i));
			}		
		}
		return (ArrayList<String>) result;
	}

	
	// end of Inspectors.
	
	private boolean isInitialized(ArrayList<String> destinationsArray) {
		boolean flag = true;
		if (destinationsArray.size() == 0 || destinationsArray == null) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 
	 * Checks the array to see if it contains Izmir 
	 * @param destinationsArray to be given as an input.
	 * @return True if the given array contains Izmir and false otherwise.
	 */
	private boolean checkIzmir(ArrayList<String> destinationsArray) {
		boolean flag = false;
		for (String destination: destinationsArray) {
			if (destination.toLowerCase().equals("izmir")) {
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * Adds a flight from izmir to all other destinations.
	 * @return True if the add operation get performed successfully and false otherwise.
	 */
	private boolean addFlightFromIzmir(ArrayList<String> destinationsArray) {
		boolean flag = false;
		int izmirIndex = getIndexOfDestination("Izmir");
		for (int i = 0; i < FlightTable.tableSize && i != izmirIndex; i++) {
			flightTable[izmirIndex][i] = true;
			flightTable[i][izmirIndex] = true;
			flag = true;
		}
		return flag;
	}
	
	private int getIndexOfDestination(String destination) {
		int index = 0;
		index = destinationsCopy.indexOf(destination);
		return index;
	}
	
	private boolean falsifyFlightTable() {
		boolean flag = false;
		for (int i = 0; i < FlightTable.tableSize; i++) {
			for (int j = 0; j < FlightTable.tableSize; j++) {
				flightTable[i][j] = false;
			}
			flag = true;
		}
		return flag;
	}
	
	public boolean[][] getFlightTable(){
		return flightTable;
	}
	
	// Pre-Conditions
	/**
	 * 
	 * Checks whether the given destinations are in the destination Copy on one hand, and on the other
	 * whether both of destinations are distinct or not.
	 * @param destinationA to be given as an input.
	 * @param destinationB to be given as an input.
	 * @return True if both destinations exist and are distinct and False otherwise.
	 */
	private boolean verifyDestinations(String destinationA, String destinationB) {
		boolean flagA = false;
		boolean flagB = false;
		boolean flagC = true;
		for (String destination: destinationsCopy) {
			if (destination.equals(destinationA)) {
				flagA = true;
			}
			else if (destination.equals(destinationB)) {
				flagB =  true;
			}
		}
		if (destinationA.equals(destinationB)) {
			flagC = false;
		}
		
		if (flagA && flagB && flagC) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean isConnection(String destinationA, String destinationB) {
		boolean flag = false;
		if (verifyDestinations(destinationA,destinationB)) {
			int destinationAIndex = getIndexOfDestination(destinationA);
			int destinationBIndex = getIndexOfDestination(destinationB);
			if (flightTable[destinationAIndex][destinationBIndex]) {
				// dbi and dai together?
				flag = true;
			}
		}
		return flag;
	}
	
	public ArrayList<String> getDestinationArray(){
		return this.destinationsCopy;
	}
	private static void printFlightTable(boolean[] row) {
        for (boolean i : row) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println();
    }
} // end FlightTable
