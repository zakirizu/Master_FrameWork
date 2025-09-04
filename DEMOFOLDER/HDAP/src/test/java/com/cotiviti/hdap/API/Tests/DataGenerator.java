package com.cotiviti.hdap.API.Tests;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.annotations.Test;

import com.cotiviti.hdap.APICommonLibrary.ExcelReader;
import com.cotiviti.hdap.APICommonLibrary.apiCommonLib;
import com.cotiviti.hdap.APICommonLibrary.createChaseRequest;

/**
 * @author dinesh.neeli
 */
public class DataGenerator extends apiCommonLib {

	@Test
	public void createData() {

		String name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		ConcurrentHashMap<String, Map<String, String>> dataMap = new ConcurrentHashMap<>();
		ConcurrentHashMap<String, String> myMap = new ConcurrentHashMap<>();

		String filePath = "input.xlsx"; // Path to your Excel file
		String sheetName = "API_UAT_DataSheet";

		dataMap = ExcelReader.getAllTestDataFromExcel(filePath, sheetName);

		System.err.println(dataMap);
		String token = "";
		token = generateBearerToken(myMap);
		processDataAndGenerateOutput("input.xlsx", "output.csv", token);
	}
}
