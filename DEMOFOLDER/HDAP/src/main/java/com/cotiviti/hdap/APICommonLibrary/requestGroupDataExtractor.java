package com.cotiviti.hdap.APICommonLibrary;

import java.util.concurrent.ConcurrentHashMap;

import org.testng.Assert;

import com.cotiviti.hdap.CommonUtils.PropertiesFileReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import groovyjarjarasm.asm.commons.Method;

public class requestGroupDataExtractor extends apiCommonLib {

	public ConcurrentHashMap<String, String> getRGData(ConcurrentHashMap<String, String> dataMap, String rgId,
			String authToken) {

		String myMethod = new Object() {
		}.getClass().getEnclosingMethod().getName();

		String env = System.getProperty("env");
		String tcName = executionMap.get("TESTCASENAME"); // start retrying from
		try {
			Thread.sleep(1000);
			String apiUrl = PropertiesFileReader.getAPIProperty(env + ".RGEndPoint").replace("{{RequestGroupID}}",
					rgId);

			// Fetch response from the API (we don't need the dates from the API anymore)
			String jsonResponse = sendGetRequest(apiUrl, authToken);

			// Parse the JSON response (still needed for other fields)
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonResponse);

			// Process ProjectTypes dynamically
			String projectType = extractType(rootNode.path("ProjectTypes"));
			// Process AuditTypes dynamically
			String auditType = extractType(rootNode.path("AuditTypes"));
			// Process ChartTypes dynamically
			String chartType = extractType(rootNode.path("ChartTypes"));

			// Process PatientName dynamically (still extracting from the response)
			String patientName = extractName(rootNode.path("PatientName"));

			dataMap = extractDateRangeMap(dataMap,rootNode.path("DatesOfService"));

			
			//int tempStart1 =	dataMap.get("ExtRGStartDate");
			//		int tempEnd1 = dataMap.get("ExtRGEndDate");
			
		//	int tempStart1 = extractYear(dataMap.get("ExtRGStartDate"));
			//int tempEnd1 = extractYear(dataMap.get("ExtRGEndDate"));
			
			
			System.out.println("tempStart1  -" +dataMap.get("ExtRGStartDate"));
			System.out.println("tempEnd1  -" +dataMap.get("ExtRGEndDate"));
			
			// Add to data map
			dataMap.put("RGProjectTypes", projectType);
			dataMap.put("RGAuditTypes", auditType);
			dataMap.put("RGChartTypes", chartType);
			dataMap.put("PatientName", patientName);
			System.out.println("Fetch RG data completed");
		} catch (Exception e) {
			executionMap.put(tcName + ".tcStatus", "Failed");
			executionMap.put(tcName + ".comments", "TC Failed due to" + e.getLocalizedMessage());
			Assert.fail(e.getLocalizedMessage());
		}
		return dataMap;
	}

}
