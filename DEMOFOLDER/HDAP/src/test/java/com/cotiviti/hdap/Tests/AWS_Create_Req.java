package com.cotiviti.hdap.Tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.cotiviti.hdap.CommonLibrary.Constants;

public class AWS_Create_Req {

	@Test
	public void testAWSRequestGeneration() {
		String inputExcelFile = Constants.UI_TESTDATA; // Path to your input Excel file
		String outputExcelFile = "updated_requests.xlsx"; // Path to save updated requests
		String jsonTemplate = "{ \"resourceType\": \"Practitioner\", \"id\": \"17ef037a-7fcb-4164-8e8b-6f9f518f02cd\", \"text\": { \"status\": \"generated\", \"div\": \"<div xmlns='http://www.w3.org/1999/xhtml'>Haidar Almohammad</div>\" }, \"active\": true, \"identifier\": [ { \"use\": \"official\", \"type\": { \"coding\": [ { \"system\": \"http://terminology.hl7.org/CodeSystem/v2-0203\", \"code\": \"NPI\", \"display\": \"National Provider Identifier\" } ] }, \"system\": \"http://hl7.org/fhir/sid/us-npi\", \"value\": \"Change1\" }, { \"use\": \"official\", \"type\": { \"coding\": [ { \"system\": \"http://terminology.hl7.org/CodeSystem/v2-0203\", \"code\": \"TAX\", \"display\": \"Tax Identifier\" } ] }, \"system\": \"http://hl7.org/fhir/sid/us-tax\", \"value\": \"646591556\" }, { \"use\": \"official\", \"type\": { \"coding\": [ { \"system\": \"http://cotiviti.com/friendly-id\", \"code\": \"ID\", \"display\": \"Friendly Identifier\" } ] }, \"system\": \"http://cotiviti.com/friendly-id\", \"value\": \"change2\" } ], \"name\": [ { \"use\": \"official\", \"text\": \"test\", \"family\": \"New P\", \"given\": [ \"Unique\" ], \"prefix\": [ \"Prof\" ], \"period\": { \"start\": \"2024-01-10\", \"end\": \"2039-04-12\" } } ], \"birthDate\": \"1968-08-28\", \"gender\": \"other\", \"telecom\": [ { \"system\": \"email\", \"value\": \"change3\", \"use\": \"home\", \"rank\": 1, \"period\": { \"start\": \"2024-01-10\", \"end\": \"2024-01-31\" } }, { \"system\": \"phone\", \"value\": \"change4\", \"use\": \"mobile\", \"rank\": 1, \"period\": { \"start\": \"2024-01-10\", \"end\": \"2024-02-20\" } }, { \"system\": \"fax\", \"value\": \"(644) 123-7486\", \"use\": \"work\", \"rank\": 1, \"period\": { \"start\": \"2024-01-10\", \"end\": \"2024-01-17\" } }, { \"system\": \"pager\", \"value\": \"664012011\", \"use\": \"temp\", \"rank\": 1, \"period\": { \"start\": \"2023-01-02\", \"end\": \"2023-01-05\" } } ], \"communication\": [ { \"coding\": [ { \"system\": \"urn:ietf:bcp:47\", \"code\": \"en\", \"display\": \"English\" }, { \"system\": \"urn:ietf:bcp:47\", \"code\": \"dsb\", \"display\": \"Lower Sorbian\" } ] } ], \"meta\": { \"lastUpdated\": \"2024-11-26T08:25:45.482937721Z\", \"versionId\": \"1\" } }";

		try {
			// Read input Excel file
			FileInputStream fis = new FileInputStream(inputExcelFile);
			Workbook inputWorkbook = new XSSFWorkbook(fis);
			Sheet inputSheet = inputWorkbook.getSheetAt(0); // Adjust as needed to get the correct sheet

			// Create a new workbook to store updated requests
			Workbook outputWorkbook = new XSSFWorkbook();
			Sheet outputSheet = outputWorkbook.createSheet("Requests");

			// Write header row in output Excel
			Row headerRow = outputSheet.createRow(0);
			headerRow.createCell(0).setCellValue("Request ID");
			headerRow.createCell(1).setCellValue("Request JSON");

			// Iterate through rows in input Excel
			Iterator<Row> iterator = inputSheet.iterator();
			int outputRowIndex = 1;

			// Skip the header row
			if (iterator.hasNext())
				iterator.next();

			while (iterator.hasNext()) {
				Row row = iterator.next();

				// Safely read values from Excel
				String name = getCellValue(row.getCell(0));
				String address = getCellValue(row.getCell(1));
				String change1 = getCellValue(row.getCell(2)); // Using column for Change1
				String change2 = getCellValue(row.getCell(3)); // Using column for Change2
				String change3 = getCellValue(row.getCell(4)); // Using column for Change3
				String change4 = getCellValue(row.getCell(5)); // Using column for Change4

				// Populate JSON template dynamically
				String updatedJson = jsonTemplate.replace("Change1", change1).replace("change2", change2)
						.replace("change3", change3).replace("change4", change4);

				// Write to output Excel
				Row outputRow = outputSheet.createRow(outputRowIndex++);
				outputRow.createCell(0).setCellValue("Request_" + outputRowIndex);
				outputRow.createCell(1).setCellValue(updatedJson);
			}

			// Save the output Excel file
			try (FileOutputStream fos = new FileOutputStream(outputExcelFile)) {
				outputWorkbook.write(fos);
			}

			inputWorkbook.close();
			System.out.println("Updated requests saved to: " + outputExcelFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Helper method to get String values safely
	private String getCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			return String.valueOf((int) cell.getNumericCellValue());
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case FORMULA:
			return cell.getCellFormula();
		default:
			return "";
		}
	}
}