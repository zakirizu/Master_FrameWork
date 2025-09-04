package com.cotiviti.hdap.APIPayLoads;

import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;
import org.testng.Assert;

import com.cotiviti.hdap.APICommonLibrary.ExcelReader;
import com.cotiviti.hdap.APICommonLibrary.apiCommonLib;
import com.cotiviti.hdap.APICommonLibrary.facitilityDataExtractor;
import com.cotiviti.hdap.APICommonLibrary.practitionerDataExtractor;
import com.cotiviti.hdap.APICommonLibrary.requestGroupDataExtractor;
import com.cotiviti.hdap.CommonUtils.PropertiesFileReader;

import io.restassured.response.Response;

public class Search extends apiCommonLib {
	static String authToken = executionMap.get("barerToken");
	static String RGID = executionMap.get("RGID");	
	static String chaseRequest = executionMap.get("ChaseRequest");
	static String factId = executionMap.get("FacilityID");
	static String practId = executionMap.get("PractitionerID");
	
	
	public ConcurrentHashMap<String, String> QAsearchFacility(ConcurrentHashMap<String, String> map) {
	    String suiteName = map.get("SuiteName");
	    String authToken = map.get("barerToken");
	    String tcName = map.get("TESTCASENAME");
	    String facilityId = map.get("FacilityID");
	    
	    try {
	        if ("SearchData".equalsIgnoreCase(suiteName)) {
	            tcName = "rgSearchFacility";
	            map = new ExcelReader().getData("rgSearchFacility", tcName, "QASanity.xlsx");
	            facilityId = map.get("FacilityID");
	        }
	        
	      String apiUrl = PropertiesFileReader.getAPIProperty(env + ".FacilityEndPoint").replace("{{FacilityId}}",facilityId);
	       // String apiUrl = PropertiesFileReader.getAPIProperty(env + ".FacilityEndPoint").replace("{{FacilityId}}","12345");

	        // If your search API requires a payload, build it here. Otherwise, send GET request.
	        Response response = sendGetRequestResponse(apiUrl, authToken);
	        
	        int statusCode = response.getStatusCode();
	        String responseMsg = response.getBody().asPrettyString();
	        JSONObject objRes = new JSONObject(responseMsg);
	        
	        if (statusCode == 200) {
	            System.err.println("Facility search successful for FacilityID: " + facilityId);
	            map.put(tcName + ".tcStatus", "Passed");
	            map.put(tcName + ".comments", "Facility search successful");
	            
	            // Optionally extract some data from response and put in map
	            String facilityName = objRes.optString("FacilityName", "N/A");
	            map.put("FacilityName", facilityName);
	        } else {
	            try {
	                String errorMessage = objRes.getString("ErrorMessage");
	                map.put(tcName + ".tcStatus", "Failed");
	                map.put(tcName + ".comments", "Facility search failed: " + errorMessage);
	                Assert.fail("Facility search failed: " + errorMessage);
	            } catch (Exception e) {
	                map.put(tcName + ".tcStatus", "Failed");
	                map.put(tcName + ".comments", "Facility search failed with status code: " + statusCode);
	                Assert.fail("Facility search failed with status code: " + statusCode);
	            }
	        }
	    } catch (Exception e) {
	        map.put(tcName + ".tcStatus", "Failed");
	        map.put(tcName + ".comments", "Facility search exception: " + e.getLocalizedMessage());
	        assertReportFailure(e);
	    }
	    
	    return map;
	}

	public ConcurrentHashMap<String, String> QAsearchRG(ConcurrentHashMap<String, String> map) {
	    String suiteName = map.get("SuiteName");
	    String authToken = map.get("barerToken");
	    String tcName = map.get("TESTCASENAME");
	    String rgId = map.get("RGID");

	    try {
	        if ("SearchData".equalsIgnoreCase(suiteName)) {
	            tcName = "rgSearchRequestGroup";
	            map = new ExcelReader().getData("rgSearchRequestGroup", tcName, "QASanity.xlsx");
	            rgId = map.get("RGID");
	        }

	        String apiUrl = PropertiesFileReader.getAPIProperty(env + ".RGEndPoint").replace("{{RequestGroupID}}",rgId);
	      //  String apiUrl = PropertiesFileReader.getAPIProperty(env + ".RGEndPoint").replace("{{RequestGroupID}}","12345");
	        Response response = sendGetRequestResponse(apiUrl, authToken);

	        int statusCode = response.getStatusCode();
	        String responseMsg = response.getBody().asPrettyString();
	        JSONObject objRes = new JSONObject(responseMsg);

	        if (statusCode == 200) {
	            System.err.println("Request Group search successful for RGID: " + rgId);
	            map.put(tcName + ".tcStatus", "Passed");
	            map.put(tcName + ".comments", "Request Group search successful");

	            // Extract data if needed
	            String rgName = objRes.optString("RequestGroupName", "N/A");
	            map.put("RequestGroupName", rgName);
	        } else {
	            try {
	                String errorMessage = objRes.getString("ErrorMessage");
	                map.put(tcName + ".tcStatus", "Failed");
	                map.put(tcName + ".comments", "Request Group search failed: " + errorMessage);
	                Assert.fail("Request Group search failed: " + errorMessage);
	            } catch (Exception e) {
	                map.put(tcName + ".tcStatus", "Failed");
	                map.put(tcName + ".comments", "Request Group search failed with status code: " + statusCode);
	                Assert.fail("Request Group search failed with status code: " + statusCode);
	            }
	        }
	    } catch (Exception e) {
	        map.put(tcName + ".tcStatus", "Failed");
	        map.put(tcName + ".comments", "Request Group search exception: " + e.getLocalizedMessage());
	        assertReportFailure(e);
	    }

	    return map;
	}

	
	public ConcurrentHashMap<String, String> QAsearchChase(ConcurrentHashMap<String, String> map) {
	    String suiteName = map.get("SuiteName");
	    String authToken = map.get("barerToken");
	    String tcName = map.get("TESTCASENAME");
	    String chaseRequestId = map.get("ChaseRequest");

	    try {
	        if ("SearchData".equalsIgnoreCase(suiteName)) {
	            tcName = "rgSearchChase";
	            map = new ExcelReader().getData("rgSearchChase", tcName, "QASanity.xlsx");
	            chaseRequestId = map.get("ChaseRequest");
	        }

	        // Build the API URL, assuming the placeholder is {{ChaseRequest}}
	     String apiUrl = PropertiesFileReader.getAPIProperty(env + ".ChaseRequestSearch").replace("{{CaseRequestID}}",	chaseRequestId);
	       // String apiUrl = PropertiesFileReader.getAPIProperty(env + ".ChaseRequestSearch").replace("{{CaseRequestID}}","12345");

	        // Assuming search is a GET request
	        Response response = sendGetRequestResponse(apiUrl, authToken);

	        int statusCode = response.getStatusCode();
	        String responseMsg = response.getBody().asPrettyString();
	        JSONObject objRes = new JSONObject(responseMsg);

	        if (statusCode == 200) {
	            System.err.println("Chase search successful for ChaseRequest: " + chaseRequestId);
	            map.put(tcName + ".tcStatus", "Passed");
	            map.put(tcName + ".comments", "Chase search successful");

	            // Optionally extract some chase-related data from response
	            String chaseStatus = objRes.optString("ChaseStatus", "N/A");
	            map.put("ChaseStatus", chaseStatus);

	        } else {
	            try {
	                String errorMessage = objRes.getString("ErrorMessage");
	                map.put(tcName + ".tcStatus", "Failed");
	                map.put(tcName + ".comments", "Chase search failed: " + errorMessage);
	                Assert.fail("Chase search failed: " + errorMessage);
	            } catch (Exception e) {
	                map.put(tcName + ".tcStatus", "Failed");
	                map.put(tcName + ".comments", "Chase search failed with status code: " + statusCode);
	                Assert.fail("Chase search failed with status code: " + statusCode);
	            }
	        }
	    } catch (Exception e) {
	        map.put(tcName + ".tcStatus", "Failed");
	        map.put(tcName + ".comments", "Chase search exception: " + e.getLocalizedMessage());
	        assertReportFailure(e);
	    }

	    return map;
	}

	public ConcurrentHashMap<String, String> QAsearchProcessesForRulesOfRG(ConcurrentHashMap<String, String> map) {
		String suiteName = map.get("SuiteName");
		String authToken = map.get("barerToken");
		String tcName = map.get("TESTCASENAME");
		String rgId = map.get("RGID");

		try {
			if ("SearchData".equalsIgnoreCase(suiteName)) {
				tcName = "rgSearchProcessesForRules";
				map = new ExcelReader().getData("rgSearchProcessesForRules", tcName, "QASanity.xlsx");
				rgId = map.get("RGID");
			}

			String apiUrl = PropertiesFileReader.getAPIProperty(env + ".ProcessesForRulesEndPoint").replace("{{RequestGroupID}}", rgId);
			//String apiUrl = PropertiesFileReader.getAPIProperty(env + ".ProcessesForRulesEndPoint").replace("{{RequestGroupID}}", "12234");

			Response response = sendGetRequestResponse(apiUrl, authToken);

			int statusCode = response.getStatusCode();
			String responseMsg = response.getBody().asPrettyString();
			JSONObject objRes = new JSONObject(responseMsg);

			if (statusCode == 200) {
				System.err.println("ProcessesForRules search successful for RGID: " + rgId);
				map.put(tcName + ".tcStatus", "Passed");
				map.put(tcName + ".comments", "ProcessesForRules search successful");

				// Optionally extract data from response
				map.put("ProcessesForRulesResponse", responseMsg);
			} else {
				try {
					String errorMessage = objRes.getString("ErrorMessage");
					map.put(tcName + ".tcStatus", "Failed");
					map.put(tcName + ".comments", "ProcessesForRules search failed: " + errorMessage);
					Assert.fail("ProcessesForRules search failed: " + errorMessage);
				} catch (Exception e) {
					map.put(tcName + ".tcStatus", "Failed");
					map.put(tcName + ".comments", "ProcessesForRules search failed with status code: " + statusCode);
					Assert.fail("ProcessesForRules search failed with status code: " + statusCode);
				}
			}
		} catch (Exception e) {
			map.put(tcName + ".tcStatus", "Failed");
			map.put(tcName + ".comments", "ProcessesForRules search exception: " + e.getLocalizedMessage());
			assertReportFailure(e);
		}

		return map;
	}

}





