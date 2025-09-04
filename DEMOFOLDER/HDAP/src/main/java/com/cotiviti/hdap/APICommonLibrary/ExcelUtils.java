package com.cotiviti.hdap.APICommonLibrary;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ExcelUtils {

    private static final String FILE_PATH = "NPI_Data.xlsx";
    private static final String SHEET_NAME = "NPI Data";

    public static ConcurrentHashMap<String, String> getNextUnusedRowData() {
    	ConcurrentHashMap<String, String> dataMap = new ConcurrentHashMap<>();

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(SHEET_NAME);
            Row headerRow = sheet.getRow(0);

            int usedColIndex = -1;
            for (Cell cell : headerRow) {
                if (cell.getStringCellValue().equalsIgnoreCase("Used")) {
                    usedColIndex = cell.getColumnIndex();
                    break;
                }
            }

            // Find first unused row
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Cell usedCell = row.getCell(usedColIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                if (usedCell.getCellType() == CellType.BLANK || usedCell.getStringCellValue().isEmpty()) {
                    // Map each column to the header
                    for (Cell cell : row) {
                        int colIndex = cell.getColumnIndex();
                        String key = headerRow.getCell(colIndex).getStringCellValue();
                        String value = getCellValue(cell);
                        dataMap.put(key, value);
                    }

                    // Mark row as used
                    Cell markCell = row.getCell(usedColIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    markCell.setCellValue("Used");

                    // Save changes
                    try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
                        workbook.write(fos);
                    }

                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataMap;
    }

    private static String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
