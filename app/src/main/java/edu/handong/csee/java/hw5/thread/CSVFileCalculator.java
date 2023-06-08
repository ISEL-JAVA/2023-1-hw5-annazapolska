package edu.handong.csee.java.hw5.thread;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.cli.Options;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.fileutil.FileManager;

public class CSVFileCalculator implements Runnable {
	
	CSVFileCalculator(){
		
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
		
	}
	
	public void calculate() { //a method for calculating SQRT, MAX, and MIN tasks
		
	}
		
	@Override
	public void run() {
		 
		// TODO Auto-generated method stub
		
	}

}
