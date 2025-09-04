package com.cotiviti.hdap.APIPayLoads;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import com.cotiviti.hdap.APICommonLibrary.ExcelReader;
import com.cotiviti.hdap.APICommonLibrary.apiCommonLib;
import com.cotiviti.hdap.APICommonLibrary.facitilityDataExtractor;
import com.cotiviti.hdap.APICommonLibrary.practitionerDataExtractor;
import com.cotiviti.hdap.APICommonLibrary.requestGroupDataExtractor;
import com.cotiviti.hdap.CommonUtils.PropertiesFileReader;

import io.restassured.response.Response;

/**
 * 
 * @author dinesh.neeli
 *
 */

public class CreateChaseRequestPage extends apiCommonLib {

	public CreateChaseRequestPage() {
		super();
	}

	/**
	 * @author dinesh.neeli
	 * @param inputFilePath
	 * @param outputFilePath
	 * @param authToken
	 */
	public ConcurrentHashMap<String, String> chaseRequestCreation(ConcurrentHashMap<String, String> objMap) {

		boolean reTry = false;
		String suiteName = executionMap.get("SuiteName");
		int noOfIterations;

		String envValue = System.getProperty("NoOfChaseRequests");
		if (envValue != null && !envValue.isEmpty()) {
			noOfIterations = Integer.parseInt(envValue);
//			System.out.println("NoOfChaseRequests -" + noOfIterations);
		} else {
			noOfIterations = Integer.parseInt(objMap.get("NoOfChaseRequests"));
//			System.out.println("NoOfChaseRequests -" + noOfIterations);
		}

		// Variable to store the row index to

		String tcName = executionMap.get("TESTCASENAME"); // start retrying from
		try {
			String authToken = objMap.get("barerToken");
			// Load the input Excel file
			Thread.sleep(2000);
			String rgID = objMap.get("RGID");
			String factId = objMap.get("FacilityID");
			String practId = objMap.get("PractitionerID");
			try {
				if (rgID == null) {
					rgID = objMap.get("REQUESTGROUP_ID");
				}
				if (practId == null) {
					practId = objMap.get("PRACTITIONER_ID");
				}

				if (factId == null) {
					factId = objMap.get("FACILITY_ID");
				}
				if (factId.contains("[") && factId.contains("]")) {
					factId = factId.replaceAll("[\\[\\]]", "");
				}
			} catch (Exception e) {
			}
			// This code should be enabled once facility linking to RG is completed
//				String accountID = objMap.get("SUBACCOUNTID");
//				String subAccountID = objMap.get("SUBACCOUNTID");

			String subAccountID = System.getProperty("subAccountID");
			String accountID = "";

			if (suiteName.equalsIgnoreCase("CreateData")) {
				accountID = objMap.get("ACCOUNTID");
				String cType = objMap.get("ChaseRequestType");

				if (subAccountID == null || subAccountID.isEmpty()) {
					if (cType.equalsIgnoreCase("Pending RG Assignment")) {
						subAccountID = "Q888";
					} else {
						subAccountID = objMap.get("SUBACCOUNTID");
					}
				}

			} else if (suiteName.equalsIgnoreCase("QASanity")) {
				accountID = "O900";
				if (subAccountID == null || subAccountID.isEmpty()) {
					subAccountID = "P634";
				}
			}
			System.out.println("************************************************");
			System.out.println("Chase request process started for below");
			String env = System.getProperty("env");
			System.out.println("Env :" + env);
			System.out.println("RG ID :" + rgID);
			System.out.println("Facility ID :" + factId);
			if (env.equalsIgnoreCase("UAT")) {
				System.out.println("practitioner Id : P-470610020225");
			} else if (env.equalsIgnoreCase("QA")) {
				System.out.println("practitioner Id : P-862084210225");
			} else if (env.equalsIgnoreCase("DEV")) {
				System.out.println("practitioner Id : P-502676300724");
			} else {
				System.out.println("practitionerId is empty");
			}
			System.out.println("NoOfChaseRequests :" + noOfIterations);
			System.out.println("subAccountID : " + subAccountID);
			System.out.println("************************************************");

			//
			String providerType = objMap.get("PROVIDERTYPE");

			// Process the data for RG, Fact, and Pract
			requestGroupDataExtractor rgProcessor = new requestGroupDataExtractor();
			objMap = rgProcessor.getRGData(objMap, rgID, authToken);

			// This code should be enabled once facility linking to RG is completed
			facitilityDataExtractor factProcessor = new facitilityDataExtractor();
			objMap = factProcessor.getFacitilityData(objMap, factId, authToken);

			//practitionerDataExtractor practProcessor = new practitionerDataExtractor();
			//objMap = practProcessor.getPractitionerData(objMap, practId, authToken);

			String extRGStartDate = objMap.get("ExtRGStartDate");
			String extRGEndDate = objMap.get("ExtRGEndDate");

			String overallStartDate = objMap.get("ExtRGStartDate"); // e.g. "2026-07-16"

			   LocalDate currentDate = LocalDate.now();      // current date as LocalDate
		        String endDate = currentDate.toString();  // endDate in yyyy-MM-dd format
			String overallEndDate = endDate; // e.g. "2029-12-31"

			for (int i = 1; i <= noOfIterations; i++) {
				Thread.sleep(2000);
				String[] generatedName = generateUKName();
				String firstName = generatedName[0];
				String lastName = generatedName[1];

				String startDate = generateUniqueStartDate(overallStartDate, overallEndDate);
				//String endDate = generateUniqueEndDate(startDate, overallEndDate);
				objMap.put("RGstartDate", startDate); // startDate in yyyy-MM-dd format
				objMap.put("RGendDate", endDate);
				objMap.put("RGfirstName", firstName);
				objMap.put("RGlastName", lastName);

				// Generate unique DOB in the format YYYY-MM-DD
				String dob = generateDOB(i);
				objMap.put("dob", dob);
				objMap = setCotivitiClaimNumber(objMap);
				System.out.println("************************************************");
				String payLoad = ChaseRequestWithProviderPayLoad.chaseRequestPayLoad(objMap.get("RGProjectTypes"),
						objMap.get("RGAuditTypes"), objMap.get("RGChartTypes"), objMap.get("CotivitiClaimNumber"),
						objMap.get("CotivitiClaimNumber"), objMap.get("RGstartDate"), objMap.get("RGendDate"),
						objMap.get("RGfirstName"), objMap.get("RGlastName"), objMap.get("dob"), objMap.get("Fname"),
						objMap.get("Fstreet"), objMap.get("Fcity"), objMap.get("Fstate"), objMap.get("FpostalCode"),
						objMap.get("Fnpi"), objMap.get("Ftin"), objMap.get("Fphone"), objMap.get("PfirstName"),
						objMap.get("PlastName"), objMap.get("Pstreet"), objMap.get("Pcity"), objMap.get("Pstate"),
						objMap.get("PpostalCode"), objMap.get("Pnpi"), objMap.get("Ptin"), accountID, subAccountID,
						providerType , objMap.get("ProspectiveClaimFlag"));

				if (i == 1) {
					System.err.println(payLoad);
				} else {
					System.out.println("Chase request iteration: " + i);
				}

				String apiUrl = PropertiesFileReader.getAPIProperty(env + ".ChaseRequestEndPoint");
				// String apiUrl ="hi";
				// Fetch response from the API
				Response jsonResponse = sendPostRequest(apiUrl, payLoad, authToken);
				int statusCode = jsonResponse.getStatusCode();
				String correlationId = jsonResponse.jsonPath().getString("CorrelationID");

				objMap.put(i + ".correlationId", correlationId);

				objMap.put(i + ".CotivitiClaimNumber", objMap.get("CotivitiClaimNumber"));

				if (statusCode == 401) {
					authToken = generateBearerToken(objMap);
					continue; // Break out of the for loop to reattempt the process from the failed row
				}
				if (statusCode == 201 || statusCode == 200 || statusCode == 202) {
					 String crID = chaseRequestStatus(executionMap);
					objMap.put(i + ".ChaseRequest", crID);
					 System.err.println("Chase request created successfully: " + crID);
					System.out.println("************************************************");
					executionMap.put(tcName + ".tcStatus", "Passed");

//					executionMap.put(tcName + ".comments", "Chase request created successfully - " + correlationId);

					executionMap.put(tcName + ".comments",
							"Chase request created successfully - " + executionMap.get(i + ".ChaseRequest"));

//					executionMap.put(i + tcName + ".comments",
//							"Chase request created for" +objMap.get("ChaseRequestType")  +" successfully - " + objMap.get(i + ".correlationId"));

					executionMap.put(i + tcName + ".comments",
							"Chase request created for " + objMap.get("ChaseRequestType") + " successfully");
				} else {
					executionMap.put(tcName + ".tcStatus", "Failed");
					executionMap.put(tcName + ".comments", "Chase request not created due to error code " + statusCode);
					executionMap.put(i + tcName + ".comments",
							"Chase request not created due to error code " + statusCode);

					Assert.fail("Chase request not created due to error code " + statusCode);
				}

			}
		} catch (Exception e) {
			executionMap.put(tcName + ".tcStatus", "Failed");
			executionMap.put(tcName + ".comments", "Chase Request not created due to error" + e.getLocalizedMessage());
			Assert.fail("Chase request not created due to error " + e.getLocalizedMessage());
		}
		return objMap;
	}

	public String chaseRequestStatus(ConcurrentHashMap<String, String> objMap) {
		String tcName = executionMap.get("TESTCASENAME");
		String authToken = objMap.get("barerToken");
		String requestId = "";
		try {

			Thread.sleep(15000);
			String jsonPayload = "{" + "\"CotivitiClaimNumbers\": [\"" + objMap.get("CotivitiClaimNumber") + "\"]"
					+ "}";

			String apiUrl = PropertiesFileReader.getAPIProperty(env + ".ChaseRequestSearchEndPoint");

			// Fetch response from the API
			Response jsonResponse = sendPostRequest(apiUrl, jsonPayload, authToken);
			int statusCode = jsonResponse.getStatusCode();
			requestId = jsonResponse.path("Results[0].Result.ChaseRequest.RequestID");
//			String status = jsonResponse.jsonPath().getString("Results[0].Result.ChaseRequest.CaseStatus.Status");

//			System.out.println("requestId " + requestId);
			objMap.put("ChaseRequest", requestId);
			// objMap.put("ChaseRequestType", status);

		} catch (Exception e) {
			executionMap.put(tcName + ".tcStatus", "Failed");
			executionMap.put(tcName + ".comments", "Chase Request not created due to error" + e.getLocalizedMessage());
			Assert.fail("Chase request not created due to error " + e.getLocalizedMessage());
		}
		return requestId;
	}

}
