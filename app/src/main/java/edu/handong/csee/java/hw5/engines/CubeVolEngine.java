package edu.handong.csee.java.hw5.engines;
import java.lang.Math;
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class defines an Engine to compute the Volume of the Cube based on sidelength.
 */
public class CubeVolEngine implements Computable {
    private static final String engineName ="CUBEVOL";
    private double sideLength;
    private double volume;
    
    /**
     * This function returns the engine name for this particular engine
     * @return returns the engine name
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * This function returns the side lengths of the cube
     * @return side length of cube
     */
    public double getSideLength() {
        return sideLength;
    }

    /**
     * This function sets the value of the side length to the value passed in to the function
     * @param sideLength is the side length of the cube
     */
    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    /**
     * This function returns the volume of the cube
     * @return volume of the cube
     */
    public double getVolume() {
        return volume;
    }

    /**
     * This function sets the volume of the cube to the value passed into the function
     * @param volume is volume of the cube
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * This function sets the side length of cube to the value passed in as parameters
     * It also checks if only one value is passed and that the value is not negative.
     * @param args passes in the value for engine name and side length
     */
    public void setInput(String[] args) throws OneInputException, NegativeNumberException{
       if (args.length != 2) {
            throw new OneInputException("Exception-04: You need one input value for "+engineName+".");
       }
       else if (Double.valueOf(args[1]) < 0){
            throw new NegativeNumberException("Exception-03: The input value cannot be negative for " + engineName+ ".");
       }
       else {
          sideLength = Double.valueOf(args[1]);
       }

    }

    /**
     * This function computes the volume of the cube based on its sidelengths
     */
    public void compute(){
        volume = Math.pow(sideLength, 3.0);
    }

    /**
     * This function returns the volume of the cube
     * @return volume of the cube
     */
     public double getResult(){
        return volume;
     }
}
