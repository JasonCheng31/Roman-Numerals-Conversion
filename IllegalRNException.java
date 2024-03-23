/**
 * 
 * @author Jason Cheng
 * @version 5/4/2023
 */
public class IllegalRNException extends IllegalArgumentException {
	/**
	 * Constructor to be used when throwing an exception. Instead of throwing IllegalArgumentException, we throw IllegalRNException with a String.
	 * @param message
	 */
public IllegalRNException(String message) {
	super(message);
}
}
