package edu.handong.csee.java.hw5.fileutil;

import java.io.FileNotFoundException;
//import java.io.BufferedWriter;
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

/**
 * This class is used to read from and write to the files provided by the user
 * @author annap
 *
 */
public class FileManager {

	/**
	 * This class reads information from the file and returns an arrayList containing the data
	 * @param fileName is the name of the file to read from 
	 * @return
	 */
	public static ArrayList<String> readLinesFromATxtFile(String fileName) {
		OptionHandler optionHandler = new OptionHandler();
		Options options = optionHandler.createOptions();
		
		ArrayList<String> arrayList = new ArrayList<>();
		
		//using CVS Default 
		
		try {
			FileReader fileReader;
			fileReader = new FileReader(optionHandler.getDataInputFilePath());
			CSVParser csvParser;
			csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
			String line;
			
			for (CSVRecord csvRecord : csvParser) {
                StringBuilder row = new StringBuilder();
                for (String value : csvRecord) {
                    row.append(value).append(","); // Add each value separated by a comma
                }
                // Remove the trailing comma
                if (row.length() > 0) {
                    row.deleteCharAt(row.length() - 1);
                }
                arrayList.add(row.toString());
            } 
	         csvParser.close();
	         fileReader.close();
	         
	         
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the file");
			optionHandler.printHelp(options);
	        System.exit(1);
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error reading file: " + fileName);
			optionHandler.printHelp(options);
	        System.exit(1);
			//e.printStackTrace();
		}

		
		

//	    File file = new File(fileName);
//
//	    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//	        String line;
//	        while ((line = reader.readLine()) != null) {
//	            arrayList.add(line);
//	        }
//	    } catch (FileNotFoundException e) {
//	    	optionHandler.printHelp(options);
//	        System.exit(1);
//	    } catch (IOException e) {
//	        System.out.println("Error reading file: " + fileName);
//	    }
		
		return arrayList;
	}

	/**
	 * This function writes the information to the file
	 * @param fileName is the name for the file to write to 
	 * @param arrayList is an array containing the data to write to the file
	 */
	public static void writeAtxtFile(String fileName, ArrayList<String> arrayList) {
		try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(fileName), CSVFormat.DEFAULT)) {

            for (String line : arrayList) {
                csvPrinter.printRecord(line);
            }

            System.out.println("The " + fileName + " file has been successfully written.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing data to the file: " + fileName);
            e.printStackTrace();
        }
    }

		
}
