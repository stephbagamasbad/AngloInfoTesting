package com.angloinfo.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * 
 * @author MSRBagamasbad
 *
 */
public class WriteTextFile {

	private static WriteTextFile instance = new WriteTextFile();
	FileWriter fw;
	
	
	/**
	 * Returns instance of the class
	 * 
	 * @return instance
	 */
	public static WriteTextFile getInstance(){
		return instance;
	}
	
	/**
	 * 
	 * @param fName
	 * @param lName
	 * @param email
	 * @param phone
	 * @param pwd
	 */
	public void saveToTextFile(String currentTime, String envType, String fName, String lName, String email, String phone, String pwd){
		try
		{
		    String filenameProd= "C:\\Grid\\onsale\\resources\\ACCOUNTS_CREATED.txt";
		    String filenameStage= "C:\\Grid\\onsale\\resources\\ACCOUNTS_CREATED_STAGE.txt";
		    
		    if(envType.equalsIgnoreCase("production")){
		    	fw = new FileWriter(filenameProd,true); //the true will append the new data
		    }else{
		    	fw = new FileWriter(filenameStage,true); //the true will append the new data
		    }
		    
		    
		    BufferedWriter bufferedWriter = new BufferedWriter (fw);
		    bufferedWriter.write("\n".replaceAll("\n", System.getProperty("line.separator")));
		    bufferedWriter.write(currentTime+
		    		","+fName+
		    		","+lName+
		    		","+email+
		    		","+phone+
		    		","+pwd);
		    //bufferedWriter.write("\n".replaceAll("\n", System.getProperty("line.separator")));
		    bufferedWriter.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
	}
	
	/*public void saveCouponToTextFile(String envType, String email, String couponID, String purchaseDate, String expDate, String desc){
		try
		{
		    String filenameProd= "C:\\Grid\\onsale\\resources\\COUPONS.txt";
		    String filenameStage= "C:\\Grid\\onsale\\resources\\COUPONS_STAGE.txt";
		    
		    if(envType.equalsIgnoreCase("production")){
		    	fw = new FileWriter(filenameProd,true); //the true will append the new data
		    }else{
		    	fw = new FileWriter(filenameStage,true); //the true will append the new data
		    }
		    
		    //FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		    BufferedWriter bufferedWriter = new BufferedWriter (fw);
		    bufferedWriter.write("\n".replaceAll("\n", System.getProperty("line.separator")));
		    bufferedWriter.write(envType+
		    		","+email+
		    		","+couponID+
		    		","+purchaseDate+
		    		","+expDate+
		    		","+desc);
		    //bufferedWriter.write("\n".replaceAll("\n", System.getProperty("line.separator")));
		    bufferedWriter.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
	}*/
	
	public void saveToCSVFile(String userName, String password, String billingSt, String billingTown, String filenameProd){
		try
		{
		    //String filenameProd= "C:\\Grid\\onsale\\resources\\BD_LOGIN.csv";
		    
		    fw = new FileWriter(filenameProd,true); //the true will append the new data
		    
		    
		    //FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		    BufferedWriter bufferedWriter = new BufferedWriter (fw);
		    bufferedWriter.write("\n".replaceAll("\n", System.getProperty("line.separator")));
		    bufferedWriter.write(userName+
		    		","+password+
		    		","+billingSt+
		    		","+billingTown);
		    //bufferedWriter.write("\n".replaceAll("\n", System.getProperty("line.separator")));
		    bufferedWriter.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
	}
	
	//BD USER Data Migration
	public void saveToCSVFile(HashMap result, String filenameProd){
		try
		{		    
		    fw = new FileWriter(filenameProd,true); //the true will append the new data
		    
		    //FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		    BufferedWriter bufferedWriter = new BufferedWriter (fw);
		    bufferedWriter.write("\n".replaceAll("\n", System.getProperty("line.separator")));
		    bufferedWriter.write(result.get("username")+
		    		","+result.get("password")+
		    		","+result.get("aeInfo")+
		    		","+result.get("billingAddress")+
		    		","+result.get("oaDetails")+
		    		","+result.get("creditLimit")+
		    		","+result.get("shippingAddress")+
		    		","+result.get("shippingCount")+
		    		","+result.get("paymentCount")+
		    		","+result.get("users")+
		    		","+result.get("globalLogin")+
		    		","+result.get("customFields")+
		    		","+result.get("catalogs")+
		    		","+result.get("reqPerUser")+
		    		","+result.get("reqPerCompany")+
		    		","+result.get("savedCart"));
		    //bufferedWriter.write("\n".replaceAll("\n", System.getProperty("line.separator")));
		    bufferedWriter.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
	}
}
