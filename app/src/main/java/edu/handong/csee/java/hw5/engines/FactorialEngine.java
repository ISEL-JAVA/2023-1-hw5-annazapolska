package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class defines an Engine to compute the factorial of a number.
 */
public class FactorialEngine implements Computable {
    private static final String engineName ="FACTORIAL";
    private int n;
    private double result;

    /**
     * This function returns the engine name for this pacticular engine
     * @return the engine name
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * This function returns the value of n
     * @return values of n
     */
    public int getN() {
        return n;
    }

    /**
     * This function sets the value of n
     * @param n the number for which the factorial is to be found
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * This function sets the result field to the value passed in by the function
     * @param result result of the calculation of fibonacci number
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This function sets the number n for which factorial is found to the value passed in as paramenters
     * It also checks if only one value is passed and that the value is not negative.
     * @param args passes in the value for engine name and number for which factorial will be found
     */
    public void setInput(String[] args) throws OneInputException, NegativeNumberException {
        if (args.length != 2) {
            throw new OneInputException("Exception-04: You need one input value for "+engineName+".");
         }
         else if (Double.valueOf(args[1]) < 0){
        	 throw new NegativeNumberException("Exception-03: The input value cannot be negative for " + engineName+ ".");
          }
          else {
        	  n = Integer.valueOf(args[1]);
          }
    }

    /**
     * This function computes the factorial of the number n
     */
    public void compute(){
        result = 1;
        for (int i = 1; i <= n; i++)
            result *= i;
        
    }

    /**
     * This function returns the factorial of the number n
     * @return factorial of n
     */
     public double getResult(){
        return result;
     }
}
