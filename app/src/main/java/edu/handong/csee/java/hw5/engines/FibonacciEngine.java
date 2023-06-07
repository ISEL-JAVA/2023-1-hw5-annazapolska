package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class defines an Engine to compute the fibonacci sequence of length n.
 */
public class FibonacciEngine implements Computable {
    private static final String engineName ="FIBONACCI";
    private int n;
    private double result;

    /**
     * This function returns the engine name for this pacticular engine
     * @return returns the engine name
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * This function returns the number n for which fibonacci sequence will be found
     * @return number n indicating number in the fibonacci sequence
     */
    public int getN() {
        return n;
    }

    /**
     * This function sets the number n to the number passed in by the function
     * @param n number in the fibonacci sequence, length of the fibonacci sequence
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * This function sets the result of the fibonacci sequence calculation
     * @param result last number is the fibonacci sequence of lenth n
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This function sets the length of fibonacci sequence n to the value passed in as parameters
     * It also checks if only one value is passed and that the value is not negative.
     * @param args passes in the value for engine name and number n for length of fibonacci sequence
     */
    public void setInput(String[] args) throws OneInputException, NegativeNumberException{
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
     * This function computes the fibonacci sequence of lenths n and assigns the result to the last number of the system
     */
    public void compute(){
        int[] fibonacci = new int[n+1];
        if (n==0)
            result = 0;
        else if (n==1)
            result = 1;
        else{
            fibonacci[0] = 0;
            fibonacci[1] = 1;

            for (int i=2; i<=n; i++)
                fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];

            result = fibonacci[fibonacci.length-1];
        }
    }

    /**
     * This function returns the last number of fibonacci sequence of lenth n
     * @return fibonacci of n
     */
    public double getResult(){
        return result;
     }
    
}
