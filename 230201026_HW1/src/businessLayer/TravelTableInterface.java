package businessLayer;
import java.util.ArrayList;

/**
 * @author Gorkem_Ben-Malik
 * This is an interface for a travel service table to be implemented.
 * It does include the method signatures only
 *
 */
public interface TravelTableInterface {
	
	/**
	 *  Adds a flight between the destinations A and B
	 * @param destinationA The destination to be specified as an input.
	 * @param destinationB The destination to be specified as an input.
	 * @return True if the add operation is performed and False otherwise.
	 */
	public boolean addConnection(String destinationA, String destinationB);
	/**
	 * 
	 * Removes a flight from destinations.
	 * @param destinationA The destination one to be specified as an input.
	 * @param destinationB The destination two to be specified as an input.
	 * @return True if the remove operation is successfully performed and 
	 * False otherwise.
	 * 
	 * 
	 */
	public boolean removeConnection(String destinationA, String destinationB);
	/**
	 * 
	 * Renames a specific destination.
	 * @param destination The name of the destination to be renamed.
	 * @return True if the rename operation gets performed successfully and False otherwise.
	 * 
	 */
	
	public boolean renameDestination(String oldDestination, String newDestination);
	/**
	 * 
	 * Checks if destination exist on the table.
	 * @param destination The destination to be checked whether in the table or not.
	 * @return True if the very destination exist and False otherwise.
	 */
	public boolean isDestination(String destination);
	/**
	 * 
	 * Checks if a particular destination has a fligth or not.
	 * @param destination The destination to be specified.
	 * @return True if the destination has flight and False otherwise.
	 */
	public boolean destinationHasConnection(String destination);
	/**
	 * 
	 * Counts the total number of destination that have flight on the table.
	 * @return the number of flight.
	 */
	public int numberOfDestinationsWithConnections();
	
	
	/**
	 * 
	 * Checks and finds all destinations that have a connection to the given destination. 
	 * @param destination to be specified as an input.
	 * @return an arraylist of the destinations if any and array containing false otherwise.
	 */
	public ArrayList<String> destinationsWithConnection(String destination);
	
	/**
	 * 
	 * Checks and finds all destinations from a departure.
	 * @param departure to be specified as an input.
	 * @return Array of destinations from the given departure if any and an array with false otherwise.
	 */
	public ArrayList<String> destinationsOfADeparture(String departure);
	/**
	 * Counts the total number of flights saved on the table. 
	 * @return  the number of flights that appears on the table.
	 */
	
	public int numberOfConnections();
	/**
	 * 
	 * Tests if there's a flight from destinationA to destinationB.
	 * @param destinationA The destination one to be specified as an input.
	 * @param destinatinB The second destination to be specified as an input.
	 * @return True if there is a flight between both of the destinations and False otherwise.
	 * 
	 */
	public boolean hasConnections(String destinationA, String destinationB);
	/**
	 * Displays the very table.
	 */
	public void displayTable();
	

}
