
/**
*The InvalidCommandException is an exception class that is thrown when an invalid command is encountered.
*This exception is typically thrown when a command is not recognized or does not conform to the expected format.
*
*@author Zapolska Anna
*@version 1.0
*@since 14/05/2023
**/ 

package edu.handong.csee.java.hw5.exceptions;

/**

The InvalidCommandException is an exception class that is thrown when an invalid command is encountered.
*/
public class InvalidCommandException extends Exception {

	/**
    *Constructs a new InvalidCommandException object with no detail message.
    */
    public InvalidCommandException() {
    super();
    }

    /**
    *Constructs a new InvalidCommandException object with the specified detail message.
    *@param message the detail message
    */
    public InvalidCommandException(String message) {
    super(message);
    }
}
