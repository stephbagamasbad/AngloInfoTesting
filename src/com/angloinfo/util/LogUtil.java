package com.angloinfo.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.testng.Reporter;

public class LogUtil {
	static private FileHandler fileTxt;
	static private SimpleFormatter formatterTxt;

	static private FileHandler fileHTML;
	static private Formatter formatterHTML;
	
	private static LogUtil instance = new LogUtil();
	
	/**
	 * Returns instance of the class
	 * 
	 * @return instance
	 */
	public static LogUtil getInstance(){
		return instance;
	}

	static public void setup() throws IOException {
		// Create Logger
		Logger logger = Logger.getLogger("");
		logger.setLevel(Level.INFO);
		fileTxt = new FileHandler("Logging.txt");
		fileHTML = new FileHandler("Logging.html");

	}
	
	/**
	 * 
	 * @param text
	 */
	public void log(String text){
		System.out.println(text);
		Reporter.log(text);
	}
	
	/**
	 * 
	 * @param xpath
	 * @param value
	 * @param action
	 * @return
	 */
	public String logAction(String locator, String value, int action){
		String log = "";
		switch (action){
			case 1: {log = "ACTION: Type LOCATOR: "+locator+" VALUE: "+value+" "; break;}
			case 2: {log = "ACTION: Click LOCATOR: "+locator+" VALUE: "+value+" "; break;}
			case 3: {log = "ACTION: Select LOCATOR: "+locator+" VALUE: "+value+" "; break;}
			case 4: {log = "ACTION: Check LOCATOR: "+locator+" VALUE: "+value+" "; break;}
			case 5: {log = "ACTION: Get Text LOCATOR: "+locator+" VALUE: "+value+" "; break;}
			default: {log = "Incorrect action"; break;}
		}
		return log;
	}
	
	/**
	 * 
	 * @param xpath
	 * @param value
	 * @param action
	 * @return
	 */
	public String logActionFail(String locator, String value, int action){
		String log = "";
		switch (action){
			case 1: {Reporter.log(logAction(locator, value, action).concat(StringConstants.FAILED)); break;}
			case 2: {Reporter.log(logAction(locator, value, action).concat(StringConstants.FAILED)); break;}
			case 3: {Reporter.log(logAction(locator, value, action).concat(StringConstants.FAILED)); break;}
			case 4: {Reporter.log(logAction(locator, value, action).concat(StringConstants.FAILED)); break;}
			case 5: {Reporter.log(logAction(locator, value, action).concat(StringConstants.FAILED)); break;}
			default: {Reporter.log("Incorrect action"); break;}
		}
		return log;
	}
	
	/**
	 * 
	 * @param xpath
	 * @param value
	 * @param action
	 * @return
	 */
	public String logActionSuccess(String locator, String value, int action){
		String log = "";
		switch (action){
			case 1: {Reporter.log(logAction(locator, value, action).concat(StringConstants.SUCCESS)); break;}
			case 2: {Reporter.log(logAction(locator, value, action).concat(StringConstants.SUCCESS)); break;}
			case 3: {Reporter.log(logAction(locator, value, action).concat(StringConstants.SUCCESS)); break;}
			case 4: {Reporter.log(logAction(locator, value, action).concat(StringConstants.SUCCESS)); break;}
			case 5: {Reporter.log(logAction(locator, value, action).concat(StringConstants.SUCCESS)); break;}
			default: {Reporter.log("Incorrect action"); break;}
		}
		return log;
	}
	
	/**
	 * 
	 * @param locator
	 */
	public void logElementFound(String locator){
		Reporter.log("ELEMENT FOUND: "+locator);
		
	}
	
	/**
	 * 
	 * @param locator
	 */
	public void logElementNotFound(String locator){
		Reporter.log("ELEMENT NOT FOUND: "+locator);
	}
	
	/**
	 * 
	 * @param locator
	 */
	public void logTextFound(String text){
		Reporter.log("TEXT FOUND: "+text);
	}
	
	/**
	 * 
	 * @param locator
	 */
	public void logTextNotFound(String text){
		Reporter.log("TEXT NOT FOUND: "+text);
	}
	
	public void logErrorPage(){
		Reporter.log("FATAL: ERROR OCCURS ON PAGE.");
	}
}
