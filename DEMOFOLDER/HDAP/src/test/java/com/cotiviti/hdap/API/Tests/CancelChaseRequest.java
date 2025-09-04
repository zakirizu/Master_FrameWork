package com.cotiviti.hdap.API.Tests;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.annotations.Test;

import com.cotiviti.hdap.APICommonLibrary.apiCommonLib;
import com.cotiviti.hdap.APIPayLoads.CancelChaseRequestPage;

/**
 * @author dinesh.neeli
 */
public class CancelChaseRequest extends apiCommonLib {

	@Test(enabled = false)
	public void createRGConfigSettings() {

		String name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		ConcurrentHashMap<String, String> dataMap = new ConcurrentHashMap<>();

		String filePath = "input.xlsx";
		System.err.println(dataMap);

		String token = "";
		token = generateBearerToken(dataMap);
		ChaseRequestSearch("input.xlsx", "output.xlsx", "CancelCR", token);

	}

	@Test()
	public void createRGContacts() {

		String name = new Object() {
		}.getClass().getEnclosingMethod().getName();
		ConcurrentHashMap<String, String> dataMap = new ConcurrentHashMap<>();
		CancelChaseRequestPage ccp = new CancelChaseRequestPage();

		String filePath = "output.xlsx"; // Path to your Excel file
		String sheetName = "CancelCR";

//		System.err.println(dataMap);
		String token = "";
		token = generateBearerToken(dataMap);
		cancelCRdata(filePath, sheetName, token);

	}
}
