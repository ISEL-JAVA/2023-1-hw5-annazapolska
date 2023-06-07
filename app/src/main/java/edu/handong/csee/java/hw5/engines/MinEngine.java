package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class defines an Engine to compute the minimal number from the sequence of numbers provided
 */
public class MinEngine implements Computable {
    private static final String engineName ="MIN";
    private double[] inputs;
    private double result;

     /**
     * This function returns the engine name for this particular engine
     * @return returns the engine name
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * This function sets the inputs to the values passed in by the function
     * @param inputs is an array of doubles for which min value will be computed
     */
    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

    /**
     * This function returns the inputs for the engine
     * @return array of inputs of type double
     */
    public double[] getInputs() {
        return inputs;
    }

    /**
     * This function sets the result of min value calculation to the value passed in by the function
     * @param result minimim number from the inputs
     */
    public void setResult(double result) {
        this.result = result;
    }
    /** 
     * This function assigns to the inputs array number values passed by the args.
     * it also checks that there are at least two numbers passed
     * @param args contains the engine name and sequence of numbers
     */
    public void setInput(String[] args)throws MinimumInputNumberException{
       if (args.length <3 ) {
            throw new MinimumInputNumberException ("Exception-02: You need at least 2 input values for "+engineName+".");
       }
        else{
            inputs = new double[args.length-1];
            for (int i=0; i<args. length-1; i++)
                inputs[i] = Double.valueOf(args[i+1]);
        }
    }

    /** 
     * This function finds the minimum number from the sequence of numbers provided (at least 2) and assigns the minimum number to result
     */
    public void compute(){
        double min = Double.MAX_VALUE;
        for (double input: inputs){
            if (input<min)
                min = input;
        }
        result = min; 
    }

    /**
     * This function returns the result of the Min computation
     * @return min number
     */
     public double getResult(){
        return result;
     }
    
}
