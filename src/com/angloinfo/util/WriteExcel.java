package com.angloinfo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


/**
 * 
 * @author MSRBagamasbad
 *
 */
public class WriteExcel {

	private static WriteExcel instance = new WriteExcel();
	private String inputFile;
	/**
	 * 
	 * @return
	 */
	public static WriteExcel getInstance(){
		return instance;
	}
	
	public void setOutputFile(String inputFile) {
		this.inputFile = inputFile;
	}
	
	@SuppressWarnings("deprecation")
	public void appendData(String xlsPath, List cells, List values) {
		
		InputStream inputStream = null;
		HashMap userDetails = new HashMap();
		HashMap oaDetails = new HashMap();
		String temp = "";
		
		try{
			inputStream = new FileInputStream(xlsPath);
		}catch(FileNotFoundException e){
			System.out.println("File not found in the specified path.");
			e.printStackTrace();
		}

	    try {
	       POIFSFileSystem fileSystem = null;
		   fileSystem = new POIFSFileSystem(inputStream);
	       HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
	       HSSFSheet sheet = workBook.getSheetAt(0);

	       HSSFRow row = sheet.createRow((short) sheet.getPhysicalNumberOfRows());
	       for(int i=0; i<cells.size();i++){
	    	   row.createCell((short) new Short(cells.get(i).toString())).setCellValue(values.get(i).toString());
	       }
	      
	       
	       FileOutputStream fileOut =  new FileOutputStream(xlsPath);
	       workBook.write(fileOut);
	       fileOut.close();
	       

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}

	public void writeResultsToExcel(int rowNum, int cellNum, String result)
			throws Exception {
		setOutputFile("C:\\Grid\\pricing\\reports\\PricingDisplay_Testing_Results.xls");
		HSSFRichTextString richTextResult = new HSSFRichTextString(result);
		FileInputStream file = new FileInputStream(new File(inputFile));
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheetAt(0);

		HSSFRow row = sheet.getRow(rowNum);
		HSSFCell cell = row.getCell(cellNum);
		if(cell==null){
			row.createCell(cellNum);
		}
		cell.setCellValue(richTextResult);
		cell.setCellStyle(getCellStyle(result, workbook));
		FileOutputStream fileOut =  new FileOutputStream(inputFile);
		workbook.write(fileOut);
	    fileOut.close();


	}
	private HSSFCellStyle getCellStyle(String result, HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("Calibri");
		if(result.contains(StringConstants.PASSED)){
			font.setColor(HSSFColor.GREEN.index);
		}
		else{
			font.setColor(HSSFColor.RED.index);
		}
		style.setFont(font);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		return style;
	}
	
}


