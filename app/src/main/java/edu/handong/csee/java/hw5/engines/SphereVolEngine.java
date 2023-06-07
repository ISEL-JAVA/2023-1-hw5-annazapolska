package edu.handong.csee.java.hw5.engines;
import java.lang.Math;
import edu.handong.csee.java.hw5.exceptions.*;


/**
 * This class defines an Engine to compute the Volume of the Shpere based on radius
 */
public class SphereVolEngine implements Computable {
    private static final String engineName ="SPHEREVOL";
    private double radius;
    private double result;

     /**
     * This function returns the engine name for this pacticular engine
     * @return returns the engine name
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * This function set the radius of the sphere to the value passed in to the function
     * @param radius is the radius of the sphere
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * This function returns the radius of the sphere
     * @return radius of the sphere
     */
    public double getRadius() {
        return radius;
    }


    /**
     * This function sets the result of the volume of sphere to the value passed in to the function
     * @param result is the volume of the sphere
     */
    public void setResult(double result) {
        this.result = result;
    }


    /**
     * This function sets the radius of the sphere to the value passed in as paramenters
     * It also checks if only one value is passed and that the value is not negative.
     * @param args passes in the value for engine name and radius
     */
    public void setInput(String[] args) throws OneInputException, NegativeNumberException{
       if (args.length != 2) {
            throw new OneInputException("Exception-04: You need one input value for "+engineName+".");
       }
       else if (Double.valueOf(args[1]) < 0){
            throw new NegativeNumberException("Exception-03: The input value cannot be negative for " + engineName+ ".");
       }
        else
            radius = Double.valueOf(args[1]);
        
    }

    /**
     * This function computes the volume of the sphere based on radius
     */
    public void compute(){
        result = (4.0/3.0) * Math.PI * Math.pow(radius, 3.0);
    }

    /**
     * This function returns the volume of the sphere
     * @return volume of the sphere
     */
     public double getResult(){
        return result;
     }
}
