package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class defines an Engine to find a maximum number from the sequence of numbers provided.
 */
public class MaxEngine implements Computable {
    private static final String engineName ="MAX";
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
     * This function returns the inputs for the maximum number calculation
     * @return array of inputs of type double for finding a max number
     */
    public double[] getInputs() {
        return inputs;
    }

    /**
     * This function sets the inputs values to the values of inputs passed in by the funcion
     * @param inputs array of numbers of type double for which max number is found
     */
    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

    /**
     * This function sets result of the max number calculation to the number passed in by the function
     * @param result maximum number from the inputs
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
       if (args.length <3) {
            throw new MinimumInputNumberException ("Exception-02: You need at least 2 input values for "+engineName+".");
       }
        else{
            inputs = new double[args.length-1];
            for (int i=0; i<args.length-1; i++)
                inputs[i] = Double.parseDouble(args[i+1]);
        }
    }

    /** 
     * This function finds the maximum number from the sequence of numbers provided (at least 2) and assigns the maximum number to result
     */
    public void compute(){
        double max = Double.MIN_VALUE;
        for (double input: inputs){
            if (input>max)
                max = input;
        }
        result = max;
    }

    /**
     * This function returns the result of the Max computation
     * @return max number
     */
     public double getResult(){
        return result;
     }
    }
