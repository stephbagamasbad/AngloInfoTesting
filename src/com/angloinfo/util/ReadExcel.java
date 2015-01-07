package com.angloinfo.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.thoughtworks.selenium.DefaultSelenium;

public class ReadExcel{
	

	String userName;
	String pwd;
	/**
	 * Returns instance of the class
	 * 
	 * @return instance
	 */
	private static ReadExcel instance = new ReadExcel();
	LogUtil log = LogUtil.getInstance();
	Utility utility = Utility.getInstance();
	
	public static ReadExcel getInstance(){
		return instance;
	}
 

	/**
	* This method is used to display the Excel content to command line.
	*
	* @param xlsPath
	*/
	@SuppressWarnings ("unchecked")
	public HashMap getFromExcel (String xlsPath, DefaultSelenium selenium, String filenameProd) throws Exception{
		//HashMap loginDetails = new HashMap();
		InputStream inputStream = null;
		HashMap userDetails = new HashMap();
		String temp = "";
		
		try{
			inputStream = new FileInputStream(xlsPath);
		}catch(FileNotFoundException e){
			System.out.println ("File not found in the specified path.");
			e.printStackTrace ();
		}
 
		POIFSFileSystem fileSystem = null;
 
			fileSystem = new POIFSFileSystem(inputStream);
 
			HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
			HSSFSheet sheet = workBook.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
 
			while (rows.hasNext ()){
				Row row = rows.next ();
				
				// display row number in the console.
				System.out.println ("Row No.: " + row.getRowNum ());
 
				// once get a row its time to iterate through cells.
				Iterator<Cell> cells = row.cellIterator ();
				while (cells.hasNext()){
					Cell cell2 = cells.next ();
					switch (cell2.getCellType ())
					{
						case HSSFCell.CELL_TYPE_NUMERIC:
						{
							// cell type numeric.
							cell2.setCellType(cell2.CELL_TYPE_STRING);
							log.log("Numeric value: " + cell2.getNumericCellValue());
							break;
						}
						case HSSFCell.CELL_TYPE_STRING:
						{
							// cell type string.
							HSSFRichTextString richTextStringPwd = (HSSFRichTextString) cell2.getRichStringCellValue ();
							temp = richTextStringPwd.getString();
							log.log("String value: " + temp);
							
							String cellNum = new Short(((HSSFCell) cell2).getCellNum()).toString();
							//Thread.sleep(Long.parseLong(StringConstants.WAIT_SEC));
							if(cellNum.equals("0")){
								System.out.println("Cell 0: "+temp);
								userDetails.put("username", temp);
							}
							if(cellNum.equals("1")){
								System.out.println("Cell 1: "+temp);
								userDetails.put("password", temp);
							}
							if(cellNum.equals("2")){
								System.out.println("Cell 2: "+temp);
								userDetails.put("count", temp);
							}
							
							break;
						}
						default:
						{
							log.log("Type not supported.");
							break;
						}
					}
				}
		}//while try
		return userDetails;
	}
}

	
	
