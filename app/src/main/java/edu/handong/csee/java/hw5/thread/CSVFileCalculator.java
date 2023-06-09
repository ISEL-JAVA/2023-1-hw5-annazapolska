package edu.handong.csee.java.hw5.thread;

import java.io.File;
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
	
	public CSVFileCalculator(String inputFileDirectory, String outputFilePath){
		this.inputFilePath = inputFilePath;
		this.outputFilePath = outputFilePath;
		this.outputFileName = inputFilePath + "-" + outputFilePath + ".csv";
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
	
	public void calculate(String engineName, ArrayList<ArrayList<String>> csvData, Computable engine ) { //a method for calculating SQRT, MAX, and MIN tasks
		if (engineName=="SQRT") {
			for (int i=1; i<csvData.size(); i++) {
				ArrayList<String> row = csvData.get(i);
				for (int j=1; j<row.size(); j++) {
					String[] inputNumber = {engineName, row.get(j)};
					//engine.setInput(inputNumber);
					engine.compute();
				}
						
			}
				
		}
	}
		
	@Override
	public void run() {
		
		 
		// TODO Auto-generated method stub
		
	}

}
