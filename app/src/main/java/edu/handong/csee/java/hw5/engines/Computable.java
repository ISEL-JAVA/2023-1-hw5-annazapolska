package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This interface defines three functions: setInputs, compute, and getResult.
 */
public interface Computable {
    /**
     * This functions sets the input to the engine to the paramenters passed into the functions
     * @param args contains an array of values inputs
     */
    public void setInput(String[] args)  throws  MyNumberFormatException, OneInputException, NegativeNumberException, MinimumInputNumberException;

    /**
     * This function computes the result for the function
     */
    public void compute();

    /**
     * This function returns the result of the calculation from the particular engine 
     * @return returns the result of double type
     */
    public double getResult();
}

// 1 3 6 8 36