/**
 * The MyNumberFormatException is a custom exception class that extends the NumberFormatException.
 * This exception is thrown when a number format is invalid or cannot be parsed.
 * Note: Since this class extends NumberFormatException, it inherits all the methods and behaviors of its parent class.
 * 
 * @author Zapolska Anna
 * @version 1.0
 * @since 14/05/2023
 */
 
package edu.handong.csee.java.hw5.exceptions;

/**
 * The MyNumberFormatException is a custom exception class that extends NumberFormatException.
 */
public class MyNumberFormatException extends NumberFormatException {

	/**
	 * Constructs a new MyNumberFormatException object with no detail message.
	 */
	public MyNumberFormatException() {
		super();
	}
	
	/**
	 * Constructs a new MyNumberFormatException object with the specified detail message.
	 * 
	 * @param message the detail message
	 */
	public MyNumberFormatException(String message) {
		super(message);
	}
}
