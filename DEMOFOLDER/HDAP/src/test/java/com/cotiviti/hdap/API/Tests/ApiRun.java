package com.cotiviti.hdap.API.Tests;

import org.apache.poi.ss.usermodel.*;
import java.util.concurrent.ConcurrentHashMap;

public class ApiRun {

	public void processExcelData(Sheet inputSheet) {
		// Iterate over rows of the input sheet
		for (Row inputRow : inputSheet) {
			if (inputRow.getRowNum() == 0) {
				continue; // Skip the header row
			}

			// Read data from the Excel cells
			String testCaseName = inputRow.getCell(0).getStringCellValue(); // Test Case Name
			String executionFlag = inputRow.getCell(1).getStringCellValue(); // Execution Flag
			String rgId = inputRow.getCell(2).getStringCellValue();
			String factId = inputRow.getCell(3).getStringCellValue();
			String practId = inputRow.getCell(4).getStringCellValue();
			String accountID = inputRow.getCell(5).getStringCellValue();
			String subAccountID = inputRow.getCell(6).getStringCellValue();
			String providerType = inputRow.getCell(7).getStringCellValue();

			// Initialize the data map for this row
			ConcurrentHashMap<String, String> dataMap = new ConcurrentHashMap<>();
			dataMap.put("RGID", rgId);
			dataMap.put("FACTID", factId);
			dataMap.put("PRACTID", practId);
			dataMap.put("ACCOUNTID", accountID);
			dataMap.put("SUBACCOUNTID", subAccountID);
			dataMap.put("PROVIDERTYPE", providerType);

			// Add test case and execution flag to the map
			dataMap.put("TestCaseName", testCaseName);
			dataMap.put("ExecutionFlag", executionFlag);

			// Print the current row data (for debugging purposes)
			System.out.println("Processing Row: " + testCaseName + ", ExecutionFlag: " + executionFlag);

			// Execute the test case based on the execution flag
			if ("Y".equalsIgnoreCase(executionFlag)) {
				// Call the method to run the test case
				executeTestCase(testCaseName, dataMap);
			} else {
				System.out.println("Skipping test case: " + testCaseName);
			}
		}
	}

	// Method to execute test case based on its name
	public void executeTestCase(String testCaseName, ConcurrentHashMap<String, String> dataMap) {
		System.out.println("Executing Test Case: " + testCaseName);

		// Example of running a test case based on the testCaseName
		switch (testCaseName) {
		case "A":
			runTestCaseA(dataMap);
			break;
		case "B":
			runTestCaseB(dataMap);
			break;
		// Add more test cases as needed
		default:
			System.out.println("Unknown Test Case: " + testCaseName);
		}
	}

	// Specific logic for Test Case A
	public void runTestCaseA(ConcurrentHashMap<String, String> dataMap) {
		System.out.println("Running Test Case A with data: " + dataMap);
		// Implement actual test case logic here
	}

	// Specific logic for Test Case B
	public void runTestCaseB(ConcurrentHashMap<String, String> dataMap) {
		System.out.println("Running Test Case B with data: " + dataMap);
		// Implement actual test case logic here
	}

	public static void main(String[] args) {
		// Assuming you have a method to get the Excel sheet
		Sheet inputSheet = getExcelSheet(); // Implement this method to load the sheet
//		TestCaseExecutor executor = new TestCaseExecutor();
//		executor.processExcelData(inputSheet);
	}

	// Mock method to get the Excel sheet (implement this method to load your Excel
	// file)
	public static Sheet getExcelSheet() {
		// You would load your Excel file here
		// For this example, let's assume you've already parsed the sheet
		return null; // Replace with actual sheet loading logic
	}
}
