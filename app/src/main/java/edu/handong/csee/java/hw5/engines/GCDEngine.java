package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class defines an Engine to compute the greatest common divisor for two or more numbers.
 */
public class GCDEngine implements Computable {
    private static final String engineName = "GCD";
    private int[] a;
    private double result;

    /**
     * This function returns the engine name for this particular engine
     * @return returns the engine name
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * This function returns the array of numbers a for which GCD will be computed
     * @return a - array of numbers for GCD calculation
     */
    public int[] getA() {
        return a;
    }

    /**
     * This function sets the array of inputs a to the values passed in by the function 
     * @param a array of inputs a of type int
     */
    public void setA(int[] a) {
        this.a = a;
    }

    /**
     * This function sets result of GCD calcualation to the value passed by the function
     * @param result Greatest Common Divisor for array of inputs a
     */
    public void setResult(double result) {
        this.result = result;
    }


    /**
     * This function sets values for numbers from the parameters passed to main function.
     * It also checks if at least two values are passed and that the values are not negative.
     * @param args contains engine name and values for numbers 
     */
    public void setInput(String[] args)throws MinimumInputNumberException, NegativeNumberException{
       if (args.length <3) {
            throw new MinimumInputNumberException ("Exception-02: You need at least 2 input values for "+engineName+".");
       }
       else {
            a = new int[args.length - 1];

            for (int i = 1; i < args.length; i++) {
                int value = Integer.parseInt(args[i]);

                if (value < 0) {
                	throw new NegativeNumberException("Exception-03: The input value cannot be negative for " + engineName+ ".");
                }

                a[i - 1] = value;
            }
        }
    }

    /**
     * Computes the greatest common divisor for two or more numbers and assigns it to result
     */
    public void compute(){
        int gcd = a[0];

        for (int i = 1; i < a.length; i++) {
            gcd = computeGCD(gcd, a[i]);
        }
    
        result = gcd;
    }
    
    //function to compute GCD 
    private int computeGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }


    /**
     * This function returns the result of the GCD calculation
     * @return GCD value
     */
    public double getResult(){
        return result;
    }

}
