/**
 * The NegativeNumberException is a custom exception class that extends the Exception class.
 * This exception is thrown when a negative number is encountered where only positive numbers are expected or allowed.
 * Note: Since this class extends Exception, it inherits all the methods and behaviors of its parent class.
 * 
 * @author Zapolska Anna
 * @version 1.0
 * @since 14/05/2023
 */
 
package edu.handong.csee.java.hw5.exceptions;

/**
 * The NegativeNumberException is a custom exception class that extends Exception.
 */
public class NegativeNumberException extends Exception {

	/**
	 * Constructs a new NegativeNumberException object with no detail message.
	 */
	public NegativeNumberException(){
		super();
	}
	
	/**
	 * Constructs a new NegativeNumberException object with the specified detail message.
	 * 
	 * @param message the detail message
	 */
	public NegativeNumberException(String message){
		super(message);
	}
}
