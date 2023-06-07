/**
 * The OneInputException is a custom exception class that extends the Exception class.
 * This exception is thrown when an operation or process requires at least one input, but no input is provided.
 * Note: Since this class extends Exception, it inherits all the methods and behaviors of its parent class.
 * 
 * @author Zapolska Anna
 * @version 1.0
 * @since 14/05/2023
 */
 
package edu.handong.csee.java.hw5.exceptions;

/**
 * The OneInputException is a custom exception class that extends Exception.
 */
public class OneInputException extends Exception {

	/**
	 * Constructs a new OneInputException object with no detail message.
	 */
	public OneInputException() {
		super();
	}
	
	/**
	 * Constructs a new OneInputException object with the specified detail message.
	 * 
	 * @param message the detail message
	 */
	public OneInputException(String message) {
		super(message);
	}
}
