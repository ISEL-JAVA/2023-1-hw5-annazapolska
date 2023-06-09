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

public class CSVFileCalculator implements Runnable {
	private String inputFilePath;
	private String outputFilePath;
	private String outputFileName;
    String engineName;
	Computable engine;
	private ArrayList<ArrayList<String>> csvData;
	
	public CSVFileCalculator(String engineName, String inputFileDirectory, String outputFilePath){
		this.inputFilePath = inputFilePath;
		this.outputFilePath = outputFilePath;
		this.outputFileName = inputFilePath + "-" + outputFilePath + ".csv";
		this.engineName = engineName;
	}
	
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
			System.out.println("Could not find the file");
			optionHandler.printHelp(options);
	        System.exit(1);
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error reading file: " + filePath);
			optionHandler.printHelp(options);
	        System.exit(1);
			//e.printStackTrace();
		}

		
		return arrayList;
	}
	
	
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
		
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.readCSV(inputFilePath);
		this.calculate(engineName, csvData);
        this.writeCSV(outputFileName, csvData);
		
	}

}
