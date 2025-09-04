package com.cotiviti.hdap.CommonUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTagReader {

	/**
	 * Returns all tags from the given Excel file as a list (no joining operator).
	 * Handles line breaks, commas, or semicolons within cells. Filters tags based
	 * on the ExecutionFlag ('Y').
	 */
	public static List<String> getTagExpression(String filePath) {
		List<String> tags = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {
			Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

			// Iterating over all rows
			for (Row row : sheet) {
				// Check if row has enough cells (0 and 1 index)
				if (row.getCell(0) != null && row.getCell(1) != null) {
					Cell tagCell = row.getCell(0); // Tag column
					Cell executeCell = row.getCell(1); // ExecuteFlag column

					// Check if both cells are not null and have string data
					if (tagCell.getCellType() == CellType.STRING && executeCell.getCellType() == CellType.STRING) {

						String tag = tagCell.getStringCellValue().trim();
						String executeFlag = executeCell.getStringCellValue().trim();

						// Ensure both tag and executeFlag are not null/empty
						if (!tag.isEmpty() && !executeFlag.isEmpty()) {
							// Add the tag if ExecutionFlag is "Y" and tag starts with "@"
							if ("Y".equalsIgnoreCase(executeFlag) && tag.startsWith("@")) {
								tags.add(tag);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tags;
	}

	public static List<Pair<String, String>> getTagAndFeaturePath(String excelFilePath) {
		List<Pair<String, String>> tagPathList = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(excelFilePath);
				Workbook workbook = WorkbookFactory.create(fis)) {

			Sheet sheet = workbook.getSheetAt(0);
			for (Row row : sheet) {
				Cell tagCell = row.getCell(0);
				Cell pathCell = row.getCell(1);

				if (tagCell != null && pathCell != null) {
					String tag = tagCell.getStringCellValue().trim();
					String path = pathCell.getStringCellValue().trim();
					tagPathList.add(new ImmutablePair<>(tag, path));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tagPathList;
	}
}
