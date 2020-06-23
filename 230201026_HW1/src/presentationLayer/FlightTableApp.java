package presentationLayer;

import java.util.ArrayList;

import businessLayer.FlightTable;

/**
 * @author Gorkem_Ben-Malik
 *
 */
/**
 * @author ben-malik
 *
 */
public class FlightTableApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> array = new ArrayList<String>();
		array.add("Ankara");
		array.add("Istanbul");
		array.add("Antalya");
		
		FlightTable flightTable = new FlightTable(array);
		flightTable.addConnection("Ankara","Antalya");	  
		// TODOS Decide on whether to make a menu or not.
	    flightTable.renameDestination("Izmir","Benin");
	    System.out.println(flightTable.isDestination("Benin"));
	    System.out.println(flightTable.getDestinationArray());
	    System.out.println(flightTable.isDestination("Izmir"));
	    System.out.println(flightTable.numberOfDestinationsWithConnections());
	    System.out.println(array);
	    System.out.println(flightTable.removeConnection("Benin","Ankara"));
	    System.out.println(flightTable.destinationsOfADeparture("Benin"));
	    System.out.println(flightTable.destinationsWithConnection("Antalya"));
	    flightTable.displayTable();
            
           	
        System.out.println(flightTable.numberOfConnections());
        System.out.println(flightTable.destinationHasConnection("Antalya"));
	}
	
	
	
	/**
	 *  Displays each entry of the the flightTable.
	 * @param row each row of the flightTable given as an input.
	 */
	public static void printFlightTable(boolean[] row) {
        for (boolean i : row) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println();
    }


}
