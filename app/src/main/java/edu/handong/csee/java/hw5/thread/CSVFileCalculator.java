package edu.handong.csee.java.hw5.thread;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.cli.Options;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.engines.Computable;


/**
 * Performs calculations on a CSV file when file directory is provided.
 * This class provides methods to calculate values based on a CSV file.
 * Class uses threads to operate on multiple files simultaneously
 * @version 1.0
 */
public class CSVFileCalculator implements Runnable {
	private String inputFilePath;
	private String outputFilePath;
	private String outputFileName;
	private String engineName;
	private ArrayList<ArrayList<String>> csvData;


	/**
	 * Constructs a new CSVFileCalculator instance.
	 * @param engineName         the name of the calculation engine
	 * @param inputFilePath		 the path to the input CSV file
	 * @param outputFilePath     the output CSV file path
	 */
	public CSVFileCalculator(String engineName, String inputFilePath, String outputFilePath){
		this.inputFilePath = inputFilePath;
		this.outputFilePath = outputFilePath;
		this.outputFileName = inputFilePath + "-" + outputFilePath + ".csv";
		this.engineName = engineName;
	}

	/**
	 * Retrieves the input file path.
	 * @return the input file path
	 */
	public String getInputFilePath() {
		return inputFilePath;
	}

	/**
	 * Sets the input file path.
	 * @param inputFilePath the input file path to set
	 */
	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	/**
	 * Retrieves the output file path.
	 * @return the output file path
	 */
	public String getOutputFilePath() {
		return outputFilePath;
	}

	/**
	 * Sets the output file path.
	 * @param outputFilePath the output file path to set
	 */
	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}

	/**
	 * Retrieves the output file name.
	 * @return the output file name
	 */
	public String getOutputFileName() {
		return outputFileName;
	}

	/**
	 * Sets the output file name.
	 * @param outputFileName the output file name to set
	 */
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	/**
	 * Retrieves the calculation engine name.
	 * @return the calculation engine name
	 */
	public String getEngineName() {
		return engineName;
	}

	/**
	 * Sets the calculation engine name.
	 * @param engineName the calculation engine name to set
	 */
	public void setEngineName(String engineName) {
		this.engineName = engineName;
	}

	/**
	 * Retrieves the CSV data.
	 * @return the CSV data
	 */
	public ArrayList<ArrayList<String>> getCsvData() {
		return csvData;
	}

	/**
	 * Sets the CSV data.
	 * @param csvData the CSV data to set
	 */
	public void setCsvData(ArrayList<ArrayList<String>> csvData) {
		this.csvData = csvData;
	}

	/**
	 * Reads a CSV file and returns the data as a 2D ArrayList.
	 * @param filePath the path of the CSV file to read
	 * @return the data read from the CSV file as a 2D ArrayList
	 * @throws FileNotFoundException if the specified file is not found
	 * @throws IOException if an error occurs while reading the file
	 */
	public ArrayList<ArrayList<String>> readCSV(String filePath){
		ArrayList<ArrayList<String>> arrayList = new ArrayList<>();

		OptionHandler optionHandler = new OptionHandler();
		Options options = optionHandler.createOptions();


		try {
			FileReader fileReader = new FileReader(filePath);
			CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);

			for (CSVRecord csvRecord : csvParser) {
				ArrayList<String> row = new ArrayList<>();
				for (String value : csvRecord) {
					row.add(value);
				}
				arrayList.add(row);
			}

			csvParser.close();
			fileReader.close();

		} catch (FileNotFoundException e) {
			//System.out.println("Could not find the file");
			optionHandler.printHelp(options);
			System.exit(1);
			//e.printStackTrace();
		} catch (IOException e) {
			//System.out.println("Error reading file: " + filePath);
			optionHandler.printHelp(options);
			System.exit(1);
			//e.printStackTrace();
		}


		return arrayList;
	}

	/**
	 * Writes the CSV data to a file.
	 * @param filePath the path of the output CSV file
	 * @param csvData the CSV data to write
	 * @throws IOException if an error occurs while reading the file
	 */
	public void writeCSV(String filePath, ArrayList<ArrayList<String>> csvData) {
		try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(filePath), CSVFormat.DEFAULT.withQuote(null))) {

			for (ArrayList<String> row : csvData) {
				csvPrinter.printRecord(row);
			}

			System.out.println("The " + filePath + " file has been successfully written.");
		} catch (IOException e) {
			System.out.println("An error occurred while writing data to the file: " + filePath);
			e.printStackTrace();
		}
	}

	/**
	 * Performs calculations on the CSV data based on the calculation engine.
	 * The calculated values are updated in the CSV data.
	 * @param engineName the name of the calculation engine
	 * @param csvData the CSV data to perform calculations on
	 */
	public void calculate(String engineName, ArrayList<ArrayList<String>> csvData) { //a method for calculating SQRT, MAX, and MIN tasks
		ArrayList<ArrayList<String>> updatedList = new ArrayList<>();


		//if the engine is SQRT
		if (engineName.equals("SQRT")) {
			ArrayList<String> headerRow = csvData.get(0);
			updatedList.add(headerRow); // Append the header row as it is

			for (int i = 1; i < csvData.size(); i++) {
				ArrayList<String> row = csvData.get(i);
				ArrayList<String> updatedRow = new ArrayList<>();

				for (int j = 0; j < row.size(); j++) {
					String value = row.get(j);
					try {
						double number = Double.parseDouble(value);
						double sqrt = Math.sqrt(number);
						updatedRow.add(String.valueOf(sqrt));
					} catch (NumberFormatException e) {
						// Handle non-numeric values if needed
						updatedRow.add(value);
					}
				}

				updatedList.add(updatedRow);
			}
		}

		else if (engineName.equals("MIN")) {
			ArrayList<String> headerRow = csvData.get(0);
			headerRow.add("MIN"); // Add "MIN" header at the end
			updatedList.add(headerRow); // Append the header row as it is

			for (int i = 1; i < csvData.size(); i++) {
				ArrayList<String> row = csvData.get(i);
				ArrayList<String> updatedRow = new ArrayList<>(row);

				double min = Double.POSITIVE_INFINITY;
				for (int j = 0; j < row.size(); j++) {
					String value = row.get(j);
					try {
						double number = Double.parseDouble(value);
						min = Math.min(min, number);
					} catch (NumberFormatException e) {
						// Handle non-numeric values if needed
					}
				}

				updatedRow.add(String.valueOf(min)); // Add min value at the end
				updatedList.add(updatedRow);
			}
		} else if (engineName.equals("MAX")) {
			ArrayList<String> headerRow = csvData.get(0);
			headerRow.add("MAX"); // Add "MAX" header at the end
			updatedList.add(headerRow); // Append the header row as it is

			for (int i = 1; i < csvData.size(); i++) {
				ArrayList<String> row = csvData.get(i);
				ArrayList<String> updatedRow = new ArrayList<>(row);

				double max = Double.NEGATIVE_INFINITY;
				for (int j = 0; j < row.size(); j++) {
					String value = row.get(j);
					try {
						double number = Double.parseDouble(value);
						max = Math.max(max, number);
					} catch (NumberFormatException e) {
						// Handle non-numeric values if needed
					}
				}

				updatedRow.add(String.valueOf(max)); // Add max value at the end
				updatedList.add(updatedRow);
			}
		}
	}


	/**
	 * This function adds the implementation for the threads to process the csv files.
	 */
	public void run() {
		// TODO Auto-generated method stub
		this.readCSV(inputFilePath);
		this.calculate(engineName, csvData);
		this.writeCSV(outputFileName, csvData);

	}

}
