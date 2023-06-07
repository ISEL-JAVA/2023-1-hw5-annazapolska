package edu.handong.csee.java.hw5.clioptions;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * This class handles the CLI options
 * @author annap
 *
 */
public class OptionHandler {

	private String task;
	private String dataInputFilePath;
	private String dataOutputFilePath;
	private String inputValues;
	private boolean helpRequested=false;
	
	/**
	 * Returns the value of the helpRequested field.
	 * @return true if help is requested, false otherwise
	 */
	public boolean getHelpRequested() {
		return helpRequested;
	}
	
	/**
	 * Returns the value of the task field.
	 * @return the task
	 */
	public String getTask() {
		return task;
	}
	
	/**
	 * Sets the value of the helpRequested field.
	 * @param helpRequested the value to set
	 */
	public void setHelpRequested(boolean helpRequested) {
		this.helpRequested = helpRequested;
	}
	
	/**
	 * Sets the value of the task field.
	 * @param task the value to set
	 */
	public void setTask(String task) {
		this.task = task;
	}
	
	/**
	 * Returns the value of the dataInputFilePath field.
	 * @return the dataInputFilePath
	 */
	public String getDataInputFilePath() {
		return dataInputFilePath;
	}
	

	/**
	 * Sets the value of the dataInputFilePath field.
	 * @param dataInputFilePath the value to set
	 */
	public void setDataInputFilePath(String dataInputFilePath) {
		this.dataInputFilePath = dataInputFilePath;
	}
	
	/**
	 * Returns the value of the dataOutputFilePath field.
	 * @return the dataOutputFilePath
	 */
	public String getDataOutputFilePath() {
		return dataOutputFilePath;
	}
	
	/**
	 * Sets the value of the dataOutputFilePath field.
	 * @param dataOutputFilePath the value to set
	 */
	public void setDataOutputFilePath(String dataOutputFilePath) {
		this.dataOutputFilePath = dataOutputFilePath;
	}
	
	/**
	 * Returns the value of the inputValues field.
	 * @return the inputValues
	 */
	public String getInputValues() {
		return inputValues;
	}
	
	/**
	 * Sets the value of the inputValues field.
	 * @param inputValues the value to set
	 */
	public void setInputValues(String inputValues) {
		this.inputValues = inputValues;
	}
	
	/**
	 * Creates and returns an instance of Options with predefined command-line options.
	 * @return an instance of Options
	 */
	public Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("ipath")
				.desc("Set a path for a data input file. It must work with -t SQRT, -t MAX, or -t MIN and -o options together. e.g., -t SQRT -i file.csv -o output.csv")
				.hasArg()
				.argName("A data file/directory path to read")
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("opath")
				.desc("Set a path for a data output file.")
				.hasArg()
				.argName("A data file path to write")
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("t").longOpt("task")
				.desc("Set a task. The -t or -i options must be set as well.")
				.hasArg()
				.argName("A task name")
				.required()
				.build());
			
				
		// add options by using OptionBuilder
		options.addOption(Option.builder("v").longOpt("values")
				.desc("Set input values (separator: space), e.g. \"23 21 2\"")
				.hasArg()
				.argName("Input values for a task.")
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Show the help page")
		        .build());

		return options;
	}
	
	/**
	 * This function parses options
	 * @param options is the set of options created
	 * @param args is the argument from the main function
	 * @return true is the options are parsed successfully
	 */
	public boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);
			dataInputFilePath = cmd.getOptionValue("i");
			dataOutputFilePath = cmd.getOptionValue("o");
			helpRequested = cmd.hasOption("h");
			inputValues = cmd.getOptionValue("v");
			task = cmd.getOptionValue("t");
			

		} catch (Exception e) {
			printHelp(options);
			//return false;
			//System.exit(0);
		}

		return true;
	}
	
	/**
	 * This function prints help page when requested
	 * @param options is the options created
	 */
	public void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "Math Calculator";
		String footer ="\nThis is the 2023-1 HW4 help page.";
		formatter.printHelp("calculator", header, options, footer, true);
	}
}
