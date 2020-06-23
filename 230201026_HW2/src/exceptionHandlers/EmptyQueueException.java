package exceptionHandlers;

/**
 * @author Ben-Malik_Gorkem
 *
 */
@SuppressWarnings("serial")
public class EmptyQueueException extends Exception {

	public EmptyQueueException(String message) {
		System.out.println("\n" + message);
	}
}
