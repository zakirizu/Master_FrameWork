package com.cotiviti.hdap.APICommonLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.ITestContext;

/**
 * 
 * @Reviewer dinesh.neeli
 *
 */
public class ExcelReader extends apiCommonLib {

	// Common method to retrieve all test case data from Excel and store it in a
	// ConcurrentHashMap
	/**
	 * @author shankar.k
	 * @param filePath
	 * @param sheetName
	 * @return
	 */
	public static ConcurrentHashMap<String, Map<String, String>> getAllTestDataFromExcel(String filePath,
			String sheetName) {
		ConcurrentHashMap<String, Map<String, String>> testDataMap = new ConcurrentHashMap<>();

		try {
			// Create a FileInputStream object to read the Excel file
			FileInputStream fis = new FileInputStream(new File(filePath));

			// Create a workbook instance from the file
			Workbook workbook = new XSSFWorkbook(fis);

			// Get the sheet by name
			Sheet sheet = workbook.getSheet(sheetName);

			// Get the header row (assumed to be the first row)
			Row headerRow = sheet.getRow(0);
			int testCaseNameColumn = -1;
			int executionFlagColumn = -1;

			// Find the indices of the columns
			for (Cell cell : headerRow) {
				if (cell.getStringCellValue().equalsIgnoreCase("TESTCASENAME")) {
					testCaseNameColumn = cell.getColumnIndex();
				} else if (cell.getStringCellValue().equalsIgnoreCase("EXECUTION_FLAG")) {
					executionFlagColumn = cell.getColumnIndex();

				}
			}

			// Check if all required columns are found
			if (testCaseNameColumn == -1 || executionFlagColumn == -1) {
				System.out.println("Required columns not found in the Excel file.");
				workbook.close();
				return null;
			}

			// Iterate through all rows to collect test case data
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Cell testCaseCell = row.getCell(testCaseNameColumn);

				// Skip the header row
				if (testCaseCell == null || testCaseCell.getStringCellValue().equalsIgnoreCase("TESTCASENAME")) {
					continue;
				}

				// Store test case data in a map
				String testCaseName = testCaseCell.getStringCellValue();
				String executionFlag = row.getCell(executionFlagColumn) != null
						? row.getCell(executionFlagColumn).getStringCellValue()
						: "";
				// String rgid = row.getCell(rgidColumn) != null ?
				// row.getCell(rgidColumn).getStringCellValue() : "";

				// Map to hold the row data for each test case
				Map<String, String> testCaseData = new ConcurrentHashMap<>();
				testCaseData.put("EXECUTION_FLAG", executionFlag);
				// testCaseData.put("RGID", rgid);

				// Add the data for the test case to the main map
				testDataMap.put(testCaseName, testCaseData);
			}

			workbook.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return testDataMap;
	}

	/**
	 * @author dinesh.neeli
	 * @param row
	 * @param columnIndexMap
	 * @param columnName
	 * @return
	 */
	public static String getCellValueByColumnName(Row row, ConcurrentHashMap<String, Integer> columnIndexMap,
			String columnName) {
		Integer columnIndex = columnIndexMap.get(columnName);
		if (columnIndex != null) {
			Cell cell = row.getCell(columnIndex);
			if (cell != null) {
				return cell.getStringCellValue();
			}
		}
		return ""; // Return empty string if column is not found or cell is empty
	}

	/**
	 * @deprecated
	 * @param sheetName
	 * @param tcNam
	 * @param path
	 * @return
	 */
	public ConcurrentHashMap<String, String> getDataOld(String sheetName, String tcNam, String path) {
		File file = new File(path);
		try {
			FileInputStream fisInput = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(fisInput);
			Sheet sheet = workbook.getSheet(sheetName);

			for (int j = 1; j <= sheet.getLastRowNum(); j++) {
				if (sheet.getRow(j).getCell(0).getStringCellValue().equalsIgnoreCase(tcNam)) {
					for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
						String key = sheet.getRow(0).getCell(k).getStringCellValue().trim();
						String value = "";
						try {
							if (sheet.getRow(j).getCell(k) != null) {
								switch (sheet.getRow(j).getCell(k).getCellType()) {
								case STRING:
									value = sheet.getRow(j).getCell(k).getStringCellValue().trim();
									break;
								case NUMERIC:
									value = String.valueOf(sheet.getRow(j).getCell(k).getNumericCellValue()).trim();
									break;
								case BOOLEAN:
									value = String.valueOf(sheet.getRow(j).getCell(k).getBooleanCellValue()).trim();
									break;
								case FORMULA:
									value = sheet.getRow(j).getCell(k).getCellFormula().trim();
									break;
								default:
									value = "";
								}
							}
						} catch (Exception e) {
							value = "";
						}
						executionMap.put(key, value);
					}
				}
			}
		} catch (Exception e) {
			assertReportFailure(e);
		}
		return executionMap;
	}

	/**
	 * @author dinesh.neeli
	 * @param sheetName
	 * @param tcNam
	 * @param path
	 * @return
	 */
	public ConcurrentHashMap<String, String> getData(String sheetName, String tcNam, String path) {
		File file = new File(path);

		try (FileInputStream fisInput = new FileInputStream(file);
				Workbook workbook = WorkbookFactory.create(fisInput)) {

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet " + sheetName + " not found");
			}

			// Remove evaluator.evaluateAll();
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

			Row headerRow = sheet.getRow(0);
			if (headerRow == null) {
				throw new IllegalArgumentException("Header row is missing in sheet " + sheetName);
			}

			for (int j = 1; j <= sheet.getLastRowNum(); j++) {
				Row currentRow = sheet.getRow(j);
				if (currentRow == null || currentRow.getCell(0) == null)
					continue;

				String tcValue = currentRow.getCell(0).getStringCellValue().trim();
				if (tcValue.equalsIgnoreCase(tcNam)) {
					for (int k = 0; k < headerRow.getLastCellNum(); k++) {
						Cell headerCell = headerRow.getCell(k);
						if (headerCell == null)
							continue;

						String key = headerCell.getStringCellValue().trim();
						String value = "";
						try {
							Cell cell = currentRow.getCell(k);
							if (cell != null) {
								switch (cell.getCellType()) {
								case STRING:
									value = cell.getStringCellValue().trim();
									break;
								case NUMERIC:
									value = String.valueOf(cell.getNumericCellValue()).trim();
									break;
								case BOOLEAN:
									value = String.valueOf(cell.getBooleanCellValue()).trim();
									break;
								case FORMULA:
									// Use cached formula result only — no evaluation!
									switch (cell.getCachedFormulaResultType()) {
									case STRING:
										value = cell.getStringCellValue().trim();
										break;
									case NUMERIC:
										value = String.valueOf(cell.getNumericCellValue()).trim();
										break;
									case BOOLEAN:
										value = String.valueOf(cell.getBooleanCellValue()).trim();
										break;
									case ERROR:
										value = "";
										break;
									default:
										value = "";
									}
									break;
								default:
									value = "";
								}
							}
						} catch (Exception e) {
							value = "";
						}
						executionMap.put(key, value);
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			assertReportFailure(e);
		}
		return executionMap;
	}

	public String getDataPath(ITestContext context) {
		String suiteName = context.getCurrentXmlTest().getSuite().getName();
		String path = "";
		try {
			switch (suiteName) {
			case "QASanity":
				path = "QASanity.xlsx";
				break;
			case "UATSanity":
				path = "";
				break;
			case "QARegression":
				path = "";
				break;
			case "CreateData":
				path = "CreateData.xlsx";
				break;
			default:
				path = "QASanity.xlsx";
				break;
			}
		} catch (Exception e) {
			assertReportFailure(e);
		}
		return path;
	}
}