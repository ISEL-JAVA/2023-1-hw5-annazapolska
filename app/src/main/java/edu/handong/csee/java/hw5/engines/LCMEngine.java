package edu.handong.csee.java.hw5.engines;

import java.util.Arrays;
import edu.handong.csee.java.hw5.exceptions.*;


/**
 * This class defines an Engine to compute the least common multiple for an array of numbers.
 */
public class LCMEngine implements Computable{
    private static final String engineName = "LCM";
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
     * This function returns the array of numbers of type int for LCM calculation
     * @return array of numbers of type int
     */
    public int[] getA() {
        return a;
    }

    /**
     * This function sets the array a to the values from the array a passed into the function
     * @param a input values of type int for LCM engine
     */
    public void setA(int[] a) {
        this.a = a;
    }

    /**
     * This function set the result of LCM calculation to the result value passed by the function
     * @param result Least Common Multiple for nummbers in array a
     */
    public void setResult(double result) {
        this.result = result;
    }


    /**
     * This function assigns values of an array of numbers to the values passed as parameters.
     * It also checks if at least two values are passed and that the values are not negative.
     * @param args contains engine name and values for an array of numbers
     */
    public void setInput(String[] args)throws MinimumInputNumberException, NegativeNumberException{
       if (args.length <3) {
            throw new MinimumInputNumberException ("Exception-02: You need at least 2 input values for "+engineName+".");
       }
        else {
            // Convert the input strings to ints and store them in an array
            int[] inputArr = new int[args.length - 1];
            for (int i = 0; i < inputArr.length; i++) {
                int input = Integer.parseInt(args[i + 1]);
                if (input < 0) {
                	throw new NegativeNumberException("Exception-03: The input value cannot be negative for " + engineName+ ".");
                }
                inputArr[i] = input;
            }
            a = inputArr;
        }
    }

    /**
     * This function computes the least common multiple for an array of numbers and assigns it to the result variable
     */
    public void compute() {
        result = 1;
        // Sort the input array in ascending order
        Arrays.sort(a);

        int lcm = 0; // Initialize lcm to 0

        // Check if any element in the array is 0
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                result = 0;
                return;
            }
        }

        // Compute the LCM using the gcd algorithm
        int gcd = gcd(a[0], a[1]);
        lcm = (a[0] * a[1]) / gcd;

        if (a.length > 2) {
            for (int i = 2; i < a.length; i++) {
                if (a[i] == 0) {
                    result = 0;
                    return;
                }
                gcd = gcd(gcd, a[i]);
                lcm = (lcm * a[i]) / gcd(lcm, a[i]);
            }
        }

        result = lcm;
    }

    /**
     * This function returns the Least Common Multiple
     * @return Least common multiple
     */
    public double getResult(){
        return result;
    }

    //This function computes the greatest common divisor of two numbers a and b using the Euclidean algorithm.
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

}
