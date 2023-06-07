/**
 * The MinimumInputNumberException is an exception class that is thrown when the minimum required input number is not met.
 * This exception is typically thrown when the input provided is less than the required minimum.
 * 
 * @author Zapolska Anna
 * @version 1.0
 * @since 14/05/2023
 */
 
package edu.handong.csee.java.hw5.exceptions;

/**
 * The MinimumInputNumberException is an exception class that is thrown when the minimum required input number is not met.
 */
public class MinimumInputNumberException extends Exception {

	/**
	 * Constructs a new MinimumInputNumberException object with no detail message.
	 */
	public MinimumInputNumberException() {
		super();
	}
	
	/**
	 * Constructs a new MinimumInputNumberException object with the specified detail message.
	 * 
	 * @param message the detail message
	 */
	public MinimumInputNumberException(String message) {
		super(message);
	}
}
