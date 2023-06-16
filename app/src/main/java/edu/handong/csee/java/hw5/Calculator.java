package edu.handong.csee.java.hw5;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.cli.Options;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.engines.Computable;
import edu.handong.csee.java.hw5.engines.CubeVolEngine;
import edu.handong.csee.java.hw5.engines.FactorialEngine;
import edu.handong.csee.java.hw5.engines.FibonacciEngine;
import edu.handong.csee.java.hw5.engines.GCDEngine;
import edu.handong.csee.java.hw5.engines.LCMEngine;
import edu.handong.csee.java.hw5.engines.MaxEngine;
import edu.handong.csee.java.hw5.engines.MinEngine;
import edu.handong.csee.java.hw5.engines.SQRTEngine;
import edu.handong.csee.java.hw5.engines.SphereVolEngine;
import edu.handong.csee.java.hw5.exceptions.InvalidCommandException;
import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.fileutil.FileManager;
import edu.handong.csee.java.hw5.thread.CSVFileCalculator;

/**
 * This class defines a calculator that can perform various operations using different engines.
 * The calculator supports operations such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL.
 * The calculator can take input values as command-line arguments or read values from input files.
 * The results can be printed to the console or written to an output file.
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



			// if the data input and output file paths are provided
			else if (optionHandler.getDataInputFilePath() != null && optionHandler.getDataOutputFilePath() != null && optionHandler.getInputValues() == null) {
				ArrayList<CSVFileCalculator> calculators = new ArrayList<>(); //array of CSVFileCalculator instances for each of the files

				File inputFile = new File(optionHandler.getDataInputFilePath());
				File outputFile = new File(optionHandler.getDataOutputFilePath());

				File[] files = inputFile.listFiles();
				int numFiles = files.length;
				int numThreads = files.length;
				Thread[] threads = new Thread[numThreads];

				//if the inputPath is a directory
				if (inputFile.isDirectory()) {
					// Process all files in the directory

					if (files != null && files.length > 0) {
						for (File file : files) {
							if (file.isFile()) {
								// Create a CSVFileCalculator instance for each file
								CSVFileCalculator calculator = new CSVFileCalculator(engineName,inputFile, file, outputFile);
								calculators.add(calculator); //add new calculator to the array of CSVFile Calculators
								Thread thread = new Thread(calculator);
								thread.start();
							}
						}
					}
//					for (Thread thread : threads) {
//						try {
//							thread.join();
//						} catch (InterruptedException e) {
//							// Handle interrupted exception
//							e.printStackTrace();
//							return;
//						}
//					}

					//if the input path is filePath
				} else if (inputFile.isFile()) {

					//when SQRT engine is used
					if (engineName.equals("SQRT")) {
						ArrayList<String> list = FileManager.readLinesFromATxtFile(optionHandler.getDataInputFilePath());
						ArrayList<String> updatedList = new ArrayList<String>();
						//adding headers to the list
						updatedList.add(list.get(0));

						//adding the rows
						for (int i=1; i<list.size(); i++) {
							StringBuilder row = new StringBuilder();
							String[] array=list.get(i).split(",");
							for (int j=0; j<array.length; j++) {
								String[] inputNumber = {engineName, array[j]};
								engine.setInput(inputNumber);
								engine.compute();

								if (j == array.length - 1) {
									row.append(String.valueOf(engine.getResult()));
								} else {
									row.append(String.valueOf(engine.getResult())).append(",");
								}

							}
							updatedList.add(row.toString());
						}

						FileManager.writeAtxtFile(optionHandler.getDataOutputFilePath(), updatedList);
					}

					//when the Max option is used
					else if (engineName.equals("MAX")) {
						ArrayList<String> list = FileManager.readLinesFromATxtFile(optionHandler.getDataInputFilePath());
						ArrayList<String> updatedList = new ArrayList<String>();

						updatedList.add(list.get(0)+","+"MAX");

						for (int i=1; i<list.size(); i++) {
							String[] array = list.get(i).split(",");
							String[] inputNumber = new String[array.length + 1];
							inputNumber[0] = engineName;
							// Copy the original elements starting from index 0 to index 1
							System.arraycopy(array, 0, inputNumber, 1, array.length);
							engine.setInput(inputNumber);
							engine.compute();
							int result = (int) engine.getResult();
							updatedList.add(list.get(i)+","+String.valueOf(result));
						}

						FileManager.writeAtxtFile(optionHandler.getDataOutputFilePath(), updatedList);
					}

					//when Min option is used
					else if (engineName.equals("MIN")) {
						ArrayList<String> list = FileManager.readLinesFromATxtFile(optionHandler.getDataInputFilePath());
						ArrayList<String> updatedList = new ArrayList<String>();

						updatedList.add(list.get(0)+","+"MIN");

						for (int i=1; i<list.size(); i++) {
							String[] array = list.get(i).split(",");
							String[] inputNumber = new String[array.length + 1];
							inputNumber[0] = engineName;
							// Copy the original elements starting from index 0 to index 1
							System.arraycopy(array, 0, inputNumber, 1, array.length);
							engine.setInput(inputNumber);
							engine.compute();
							int result = (int) engine.getResult();
							updatedList.add(list.get(i)+","+String.valueOf(result));
						}

						FileManager.writeAtxtFile(optionHandler.getDataOutputFilePath(), updatedList);
					}

				}
				else {
					optionHandler.printHelp(options);
					System.exit(1);
				}
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


