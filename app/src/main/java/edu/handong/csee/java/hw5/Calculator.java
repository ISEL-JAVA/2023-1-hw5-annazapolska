package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.engines.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.commons.cli.Options;
import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.exceptions.*;
import edu.handong.csee.java.hw5.fileutil.FileManager;
/**
 * This class defines a class to compute various operations with engines.
 */
public class Calculator {

	/**
	 * This function creates an instance of class calculator.
	 * @param args passes arguments to the program
	 * @throws MinimumInputNumberException 
	 * @throws NegativeNumberException 
	 * @throws OneInputException 
	 * @throws MyNumberFormatException 
	 * @throws InvalidCommandException 
	 */
	public static void main(String[] args) throws MyNumberFormatException, OneInputException, NegativeNumberException, MinimumInputNumberException, InvalidCommandException, MinimumInputNumberException {

		Calculator myCalculator = new Calculator();

		myCalculator.run(args);
	}

	/**
	 * This function determines which engine to run based on the argument and then outputs the result from the engine
	 * @param args passes engine name and the values for calculation
	 * @throws MinimumInputNumberException 
	 * @throws NegativeNumberException 
	 * @throws OneInputException 
	 * @throws MyNumberFormatException 
	 * @throws InvalidCommandException
	 */
	public void run(String[] args) throws MyNumberFormatException, OneInputException, NegativeNumberException, MinimumInputNumberException, InvalidCommandException {
		try {
			
			OptionHandler optionHandler = new OptionHandler();
			Options options = optionHandler.createOptions();

			if (optionHandler.parseOptions(options, args)) {
				if (optionHandler.getHelpRequested()) {
					optionHandler.printHelp(options);
				} 
			}
			else {
				System.exit(1);
			}

			
			/*if (args.length==0){
				throw new InvalidCommandException("Exception-01: Invalid command: \n"
						+ "Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
			}*/
			
			
			
			String engineName = optionHandler.getTask();
			if (engineName==null) {
				//optionHandler.printHelp(options);
				System.exit(1);
			}
			else {
				engineName=engineName.toUpperCase();
			}
			
			
			Computable engine =null;

			switch(engineName) {
			case "LCM":
				engine = new LCMEngine();
				break;
			case "GCD":
				engine = new GCDEngine();
				break;
			case "SQRT":
				engine = new SQRTEngine();
				break;
			case "FACTORIAL":
				engine = new FactorialEngine();
				break;
			case "FIBONACCI":
				engine = new FibonacciEngine();
				break;
			case "MAX":
				engine = new MaxEngine();
				break;
			case "MIN":
				engine = new MinEngine();
				break;
			case "CUBEVOL":
				engine = new CubeVolEngine();
				break;
			case "SPHEREVOL":
				engine = new SphereVolEngine();
				break;
			default:
				if (optionHandler.getInputValues()!=null) {
					throw new InvalidCommandException("Exception-01: Invalid command: "+optionHandler.getTask().toUpperCase()+"\n"
							+ "Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
				}
				else{
					optionHandler.printHelp(options);
					System.exit(1);
				}	
			}
			
			
			
			//When -v option is provided for any engine and no files
			if (optionHandler.getInputValues()!=null && optionHandler.getDataInputFilePath()==null && optionHandler.getDataOutputFilePath()==null) {
				String[] values = optionHandler.getInputValues().split("\\s+");
				//checking if the values can be converted into a double
				try {
					Double.parseDouble(values[0]);
				} catch (NumberFormatException e) {
					throw new MyNumberFormatException("Exception-05: The input value should be converted into a number. ("  + values[0] + " is not a number value for " + optionHandler.getTask().toUpperCase() +".)");
				}
				//getting the values and calculating the output one by one
				String[] inputs = new String[values.length+1];
				inputs[0]= engineName;
				for (int i=1; i<inputs.length; i++) {
					inputs[i]=values[i-1];
				}
				engine.setInput(inputs);
				engine.compute();
			}

			//when input and output files are provided for the SQRT engine
			else if (engineName.equals("SQRT") && optionHandler.getDataInputFilePath()!=null && optionHandler.getDataOutputFilePath()!=null) {
				if(optionHandler.getInputValues()!=null) {
					optionHandler.printHelp(options);
					System.exit(1);
				}
				ArrayList<String> list = FileManager.readLinesFromATxtFile(optionHandler.getDataInputFilePath());
				ArrayList<String> updatedList = new ArrayList<String>();
				updatedList.add(list.get(0));
				updatedList.add("\n");
				for (int i=1; i<list.size(); i++) {
					String[] array=list.get(i).split(",");
					for (int j=0; j<array.length; j++) {
						String[] inputNumber = {engineName, array[j]};
						engine.setInput(inputNumber);
						engine.compute();
						
						if (j==array.length-1) {
							updatedList.add(String.valueOf(engine.getResult()));
						}
						else {
							updatedList.add(String.valueOf(engine.getResult())+",");
						}
						
					}
					updatedList.add("\n");
				}
				
				FileManager.writeAtxtFile(optionHandler.getDataOutputFilePath(), updatedList);
			}

			//all the other scenarios 
			else {
				optionHandler.printHelp(options);
				System.exit(1);
			}
			
			
			//in the end, if the -v option was used, the result is printed out 
			if (optionHandler.getInputValues()!=null) {
				System.out.println("The result of " +  engineName + " is " + engine.getResult() + ".");
			}
			
			
			
		}catch (InvalidCommandException e) {
			System.out.println(e.getMessage());
		}catch (OneInputException e) {
			System.out.println(e.getMessage());
		} catch (NegativeNumberException e) {
			System.out.println(e.getMessage());
		} catch (MinimumInputNumberException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
	    } 
	}

}


