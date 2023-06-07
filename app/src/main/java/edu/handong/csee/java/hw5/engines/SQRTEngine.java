package edu.handong.csee.java.hw5.engines;
import java.lang.Math;
import edu.handong.csee.java.hw5.exceptions.*;
import org.apache.commons.cli.Options;
import edu.handong.csee.java.hw5.clioptions.*;
/**
 * This class defines an Engine to compute the square root of an input.
 */
public class SQRTEngine implements Computable {
    private static final String engineName ="SQRT";
    private double input;
    private double result;

    /**
     * This function returns the engine name for this pacticular engine
     * @return returns the engine name
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * This function sets the input value to the value passed by the function
     * @param input number for which the square root will be found
     */
    public void setInput(double input) {
        this.input = input;
    }

    /**
     * THis function returns the value of the input
     * @return input value for finding a square root
     */
    public double getInput() {
        return input;
    }

    /**
     * This function sets the result of square root calculation to the values passed in by the function
     * @param result square root of input value
     */
    public void setResult(double result) {
        this.result = result;
    }


    /**
     * This function assigns to the input the value passed in by the args
     * Checks that there is only one number passed and that it is not a negative number.
     * @param args contains the engine name and the input for the square root
     */
    public void setInput(String[] value) throws OneInputException, NegativeNumberException{
    	if (value.length == 0) {
            // No input value provided, do nothing
            return;}
    	if (value.length != 2) {
            throw new OneInputException("Exception-04: You need one input value for "+engineName+".");
       }
       else if (Double.valueOf(value[1]) < 0){
            throw new NegativeNumberException("Exception-03: The input value cannot be negative for " + engineName+ ".");
       }
        else    
            input = Double.parseDouble(value[1]);
    	//System.out.println("Result: ");
    }
   

    /**
     * Computes the square root of an input and assigns it to result
     */
    public void compute(){
        result = Math.sqrt(input);
    }

    /**
     * This function returns the square root of the input
     * @return square root
     */
     public double getResult(){
        return result;
     }

}
