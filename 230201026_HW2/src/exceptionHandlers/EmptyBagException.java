package exceptionHandlers;

/**
 * 
 * @author Ben-Malik_Gorkem
 *
 */
@SuppressWarnings("serial")
public class EmptyBagException extends Exception {
	
	public EmptyBagException(String message) {
		System.out.println("\n" + message );

	}

}
