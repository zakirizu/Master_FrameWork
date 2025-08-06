package utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;



public class ExcelFileReader{
static Logger log = LogManager.getLogger(ExcelFileReader.class.getName());
 static HashMap<String, String> hmap = null;
	
	
 public static HashMap<String,String> getExcelData(String sheetName, String tcID) throws IOException {
  
  try {
	  log.info("Reading the data from Excel Sheet."+"SheetName: "+sheetName+", TestCase ID:"+tcID);
	  hmap = new HashMap<String, String>();
	  String path = Constants.excelFilePath;  
	  System.out.println(path);
	  FileInputStream fis = new FileInputStream(path);  
	  XSSFWorkbook workbook= new XSSFWorkbook(fis);  
	  XSSFSheet sheet = workbook.getSheet(sheetName);   
	  int lastRowCount = sheet.getLastRowNum();
  
  
  
  for(int i=0;i<lastRowCount;i=i+2)
		  {
		   String temp = sheet.getRow(i).getCell(0).getStringCellValue();
		   if(temp.equalsIgnoreCase(tcID))
		   {
		    XSSFRow targetKeyRow = sheet.getRow(i);
		    XSSFRow targetValueRow = sheet.getRow(i+1);
		    int lastColumn = targetKeyRow.getLastCellNum();
		    
		    	for(int j=1; j<lastColumn-1; j++)      
				    {
				     String ktemp = targetKeyRow.getCell(j).getStringCellValue();     
				     String k =ktemp.replaceAll("\"","");     
				     String vtemp = targetValueRow.getCell(j).getStringCellValue();
				     String v =vtemp.replaceAll("\"","");
				     hmap.put(k, v);
				    }
		  
		   }
		  }
 }
	
 catch(Exception e)
 {
	 log.info("Exception Occured While Reading the Excel Data"+e);
 }

  return hmap;
 }

  
 }