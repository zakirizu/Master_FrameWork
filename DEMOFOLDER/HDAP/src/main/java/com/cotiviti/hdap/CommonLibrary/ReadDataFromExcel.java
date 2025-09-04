package com.cotiviti.hdap.CommonLibrary;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cotiviti.hdap.CommonLibrary.Constants;

public class ReadDataFromExcel {
	static HashMap<String, String> hmap = null;
	static String path = Constants.UI_TESTDATA;
	static FileInputStream fis = null;
	static Logger mylogger = LogManager.getLogger(ReadDataFromExcel.class.getName());

	public static HashMap<String, String> getExcelData(String sheetName, String tcID) {
		try {
			mylogger.info("RECOMMENDED: PLEASE MAKE THE TEST DATA SHEET TO READ ONLY MODE.");
			mylogger.info("Reading Data from the Excel Sheet for TestCaseID: " + tcID);
			hmap = new HashMap<String, String>();
			fis = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int lastRowCount = sheet.getLastRowNum();
			System.out.println("fails here ");

			for (int i = 0; i < lastRowCount; i = i + 2) {
				String temp = sheet.getRow(i).getCell(0).getStringCellValue();
				if (temp.equalsIgnoreCase(tcID)) {
					XSSFRow targetKeyRow = sheet.getRow(i);
					XSSFRow targetValueRow = sheet.getRow(i + 1);
					int lastColumn = targetKeyRow.getLastCellNum();

					for (int j = 1; j < lastColumn - 1; j++) {
						String ktemp = targetKeyRow.getCell(j).getStringCellValue();
						String k = ktemp.replaceAll("\"", "");
						String vtemp = targetValueRow.getCell(j).getStringCellValue();
						String v = vtemp.replaceAll("\"", "");
						hmap.put(k, v);
					}
					mylogger.info("Successfully Read the Data from the Excel Sheet for TestCaseID: " + tcID);
					break;

				}
			}
		}

		catch (Exception e) {
			mylogger.info("Exception Ouccred while Reading data from Excel Sheet:" + path);
			e.printStackTrace();
			System.out.println(e.toString());
		}

		return hmap;
	}

	// Method to store data in Excel
	public static void storeDataInExcel(String sheetName, String data) {
		try {
			mylogger.info("Storing Data in the Excel Sheet at B2 (Second row, second column)");
			fis = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			// Create the second row (index 1) if it doesn't exist, and write to the second
			// column (index 1)
			if (sheet.getRow(1) == null) {
				sheet.createRow(1); // Create the second row if it doesn't exist
			}
			sheet.getRow(1).createCell(1).setCellValue(data); // B2 is at row index 1, column index 1

			// Save the updated data back to the file
			try (FileOutputStream fileOut = new FileOutputStream(path)) {
				workbook.write(fileOut);
			}

			workbook.close();
			mylogger.info("Successfully stored data at B2 in Excel Sheet");
		} catch (Exception e) {
			mylogger.error("Error while writing data to the Excel Sheet: " + path, e);
			e.printStackTrace();
		}
	}

}
