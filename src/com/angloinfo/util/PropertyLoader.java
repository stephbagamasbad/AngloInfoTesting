package com.angloinfo.util;

import java.util.HashMap;

import java.util.Properties;

public class PropertyLoader{
	
	private static PropertyLoader instance = new PropertyLoader();
	
	/**
	 * Returns instance of the class
	 * 
	 * @return instance
	 */
	public static PropertyLoader getInstance(){
		return instance;
	}
	
	public HashMap<String, String> getPropFileValues(Properties prop){
		HashMap<String, String> values = new HashMap<String, String>();
		
		values.put("IMG_FOLDER_LOC", prop.getProperty("IMG_FOLDER_LOC"));
		
		values.put("AUTH_USER", prop.getProperty("AUTH_USER"));
		values.put("AUTH_PW", prop.getProperty("AUTH_PW"));
		

		return values;
	}
}
