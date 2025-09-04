package com.cotiviti.hdap.Pages;

import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;
import org.testng.Assert;

import com.cotiviti.hdap.APICommonLibrary.ExcelReader;
import com.cotiviti.hdap.APICommonLibrary.apiCommonLib;
import com.cotiviti.hdap.APIPayLoads.CreateFacility;
import com.cotiviti.hdap.APIPayLoads.QASanityCreateFacility;
import com.cotiviti.hdap.APIPayLoads.RequestGroupConfigSettings;
import com.cotiviti.hdap.APIPayLoads.RequestGroupCreateContacts;
import com.cotiviti.hdap.APIPayLoads.RequestGroupSaveProcessForRules;
import com.cotiviti.hdap.CommonUtils.PropertiesFileReader;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class RequestGroup extends apiCommonLib {
	/**
	 * @author dinesh.neeli
	 */
	public RequestGroup() {
		super();
	}

	static String rgId;

	/**
	 * @author dinesh.neeli
	 * @param map
	 * @return
	 */
	public ConcurrentHashMap<String, String> createRGConfig(ConcurrentHashMap<String, String> map) {

		String suiteName = map.get("SuiteName");
		rgId = "RG-" + getCurrentDateTime();
		String authToken = map.get("barerToken");
		String tcName = map.get("TESTCASENAME");
		try {

			if (suiteName.equalsIgnoreCase("CreateData")) {
				tcName = "rgConfigSettings";
				map = new ExcelReader().getData("rgConfigSettings", tcName, "CreateData.xlsx");
			}
			String apiUrl = PropertiesFileReader.getAPIProperty(env + ".RGConfigSettings").replace("{{RequestGroupID}}",
					rgId);
			String payLoad = new RequestGroupConfigSettings().requestGroupConfigSettingPayload(map);
			Response response = sendPostRequest(apiUrl, payLoad, authToken);
			int statusCode = response.getStatusCode();
			String responseMsg = response.getBody().asPrettyString().toString();
			JSONObject objRes = new JSONObject(responseMsg);

			if (statusCode == 201 || statusCode == 200) {
				System.err.println("RG settings created successfully : " + rgId);
				map.put(tcName + ".tcStatus", "Passed");
				map.put(tcName + ".RGID", rgId);
				map.put(tcName + ".comments", "RG settings created successfully : " + rgId);
				map.put("RGID", rgId);
			} else {
				String errorMessage = objRes.getString("ErrorMessage");
				map.put(tcName + ".tcStatus", "Failed");
				map.put(tcName + ".comments", "RG settings failed due to error " + statusCode + ":" + errorMessage);
				Assert.fail("RG settings failed due to error " + statusCode + ":" + errorMessage);
			}

		} catch (Exception e) {
			assertReportFailure(e);
		}
		return map;
	}

	/**
	 * @author dinesh.neeli
	 * @param map
	 * @return
	 */
	public ConcurrentHashMap<String, String> createRGContact(ConcurrentHashMap<String, String> map) {

		String suiteName = map.get("SuiteName");
		String authToken = map.get("barerToken");
		String tcName = map.get("TESTCASENAME");
		try {
			if (suiteName.equalsIgnoreCase("CreateData")) {
				tcName = "rgCreateContact";
				map = new ExcelReader().getData("rgCreateContact", tcName, "CreateData.xlsx");
			}
			String apiUrl = PropertiesFileReader.getAPIProperty(env + ".RGCreateContacts").replace("{{RequestGroupID}}",
					rgId);
			String payLoad = new RequestGroupCreateContacts().requestGroupCreateContactsPayload(map);
			Response response = sendPostRequest(apiUrl, payLoad, authToken);
			int statusCode = response.getStatusCode();
			String responseMsg = response.getBody().asPrettyString().toString();
			JSONObject objRes = new JSONObject(responseMsg);

			if (statusCode == 201 || statusCode == 200) {
				System.err.println("RG Contacted created successfully : " + rgId);
				map.put(tcName + ".tcStatus", "Passed");
				map.put(tcName + ".comments", "Contact created successfully for " + rgId);
			} else {
				String errorMessage = objRes.getString("ErrorMessage");
				map.put(tcName + ".tcStatus", "Failed");
				map.put(tcName + ".comments",
						"Contact not created due to error code " + statusCode + ":" + errorMessage);
				Assert.fail("Contact not created due to error code " + statusCode + ":" + errorMessage);

			}

		} catch (Exception e) {
			assertReportFailure(e);
		}
		return map;
	}

	/**
	 * @author dinesh.neeli
	 * @param map
	 * @return
	 */
	public ConcurrentHashMap<String, String> createRGPFR(ConcurrentHashMap<String, String> map) {

		String suiteName = map.get("SuiteName");
		String authToken = map.get("barerToken");
		String tcName = map.get("TESTCASENAME");
		try {

			if (suiteName.equalsIgnoreCase("CreateData")) {
				tcName = "rgCreateProcessForRules";
				map = new ExcelReader().getData("rgCreateProcessForRules", tcName, "CreateData.xlsx");
			}
			String apiUrl = PropertiesFileReader.getAPIProperty(env + ".RGCreatePFR").replace("{{RequestGroupID}}",
					rgId);
			String payLoad = new RequestGroupSaveProcessForRules().requestGroupPFRPayload(map);

			Response response = sendPostRequest(apiUrl, payLoad, authToken);
			int statusCode = response.getStatusCode();
			String responseMsg = response.getBody().asPrettyString().toString();
			JSONObject objRes = new JSONObject(responseMsg);

			if (statusCode == 202 || statusCode == 200) {
				System.err.println("RG PFR created successfully : " + rgId);
				map.put(tcName + ".tcStatus", "Passed");
				map.put(tcName + ".comments", "PFR created successfully for " + rgId);
			} else {
				try {
					String errorMessage = objRes.getString("ErrorMessage");
					map.put(tcName + ".tcStatus", "Failed");
					map.put(tcName + ".comments",
							"PFR not created due to error code " + statusCode + ":" + errorMessage);
					Assert.fail("PFR not created due to error code " + statusCode + ":" + errorMessage);
				} catch (Exception e) {
					map.put(tcName + ".tcStatus", "Failed");
					map.put(tcName + ".comments", "PFR not created due to error code :" + statusCode);
					Assert.fail("PFR not created due to error code : " + statusCode);
				}

			}

		} catch (Exception e) {
			assertReportFailure(e);
		}
		return map;
	}

	/**
	 * @author shankar.kudupudi
	 * @param map
	 * @return
	 */
	public ConcurrentHashMap<String, String> QAcreatefacility(ConcurrentHashMap<String, String> map) {

		String suiteName = map.get("SuiteName");
		String authToken = map.get("barerToken");
		String tcName = map.get("TESTCASENAME");
		String newFacID;
		String rid = rgId;

		try {
			if (suiteName.equalsIgnoreCase("CreateData")) {
				tcName = "rgCreateFacility";
				map = new ExcelReader().getData("rgCreateFacility", tcName, "QASanity.xlsx");
			}
			if(rid==null||rid =="") {
				rid = map.get("REQUESTGROUP_ID");
			}
			String apiUrl = PropertiesFileReader.getAPIProperty(env + ".RGCreateFacility").replace("{{RequestGroupID}}",
					rid);
//			String payLoad = new CreateFacility().requestGroupFacility(map);
			String payLoad = new QASanityCreateFacility().getPayloadFromExcelData();

			Response response = sendPostRequest(apiUrl, payLoad, authToken);
			int statusCode = response.getStatusCode();
			String responseMsg = response.getBody().asPrettyString().toString();
			JSONObject objRes = new JSONObject(responseMsg);

			if (statusCode == 202 || statusCode == 200) {
				System.err.println("RG Facility created successfully : " + rgId);
				map.put(tcName + ".tcStatus", "Passed");
				newFacID = response.jsonPath().getString("NewProviders[0].ProviderID");
				System.err.println(newFacID);
				map.put("FacilityID", newFacID);
				map.put("ProviderID", newFacID);
				map.put(tcName + ".comments", "Facility ID " + response.jsonPath().getString("NewProviders.ProviderID")
						+ "  created successfully for " + rgId);
			} else {
				try {
					String errorMessage = objRes.getString("ErrorMessage");
					map.put(tcName + ".tcStatus", "Failed");
					map.put(tcName + ".comments",
							"Facility not created due to error code " + statusCode + ":" + errorMessage);
					Assert.fail("Facility not created due to error code " + statusCode + ":" + errorMessage);
				} catch (Exception e) {
					map.put(tcName + ".tcStatus", "Failed");
					map.put(tcName + ".comments", "Facility not created due to error code :" + statusCode);
					Assert.fail("Facility not created due to error code : " + statusCode);
				}
			}
		} catch (Exception e) {
			map.put(tcName + ".tcStatus", "Failed");
			map.put(tcName + ".comments", "Facility not created due to error :" + e.getLocalizedMessage());
			assertReportFailure(e);
		}
		return map;
	}
	
	
	public ConcurrentHashMap<String, String> createfacility(ConcurrentHashMap<String, String> map) {

		String suiteName = map.get("SuiteName");
		String authToken = map.get("barerToken");
		String tcName = map.get("TESTCASENAME");
		String newFacID;
		String rid = rgId;

		try {
//			if (suiteName.equalsIgnoreCase("CreateData")) {
//				tcName = "rgCreateFacility";
//				map = new ExcelReader().getData("rgCreateFacility", tcName, "QASanity.xlsx");
//			}
			if(rid==null||rid =="") {
				rid = map.get("REQUESTGROUP_ID");
			}
			String apiUrl = PropertiesFileReader.getAPIProperty(env + ".RGCreateFacility").replace("{{RequestGroupID}}",
					rid);
//			String payLoad = new CreateFacility().requestGroupFacility(map);
			String payLoad = new CreateFacility().getPayloadFromExcelData();

			Response response = sendPostRequest(apiUrl, payLoad, authToken);
			int statusCode = response.getStatusCode();
			String responseMsg = response.getBody().asPrettyString().toString();
			JSONObject objRes = new JSONObject(responseMsg);

			if (statusCode == 202 || statusCode == 200) {
				System.err.println("RG Facility created successfully : " + rgId);
				map.put(tcName + ".tcStatus", "Passed");
				newFacID = response.jsonPath().getString("NewProviders[0].ProviderID");
//				System.err.println(newFacID);
				map.put("FacilityID", newFacID);
				map.put("ProviderID", newFacID);
				map.put(tcName + ".comments", "Facility ID " + response.jsonPath().getString("NewProviders.ProviderID")
						+ "  created successfully for " + rgId);
			} else {
				try {
					String errorMessage = objRes.getString("ErrorMessage");
					map.put(tcName + ".tcStatus", "Failed");
					map.put(tcName + ".comments",
							"Facility not created due to error code " + statusCode + ":" + errorMessage);
					Assert.fail("Facility not created due to error code " + statusCode + ":" + errorMessage);
				} catch (Exception e) {
					map.put(tcName + ".tcStatus", "Failed");
					map.put(tcName + ".comments", "Facility not created due to error code :" + statusCode);
					Assert.fail("Facility not created due to error code : " + statusCode);
				}
			}
		} catch (Exception e) {
			map.put(tcName + ".tcStatus", "Failed");
			map.put(tcName + ".comments", "Facility not created due to error :" + e.getLocalizedMessage());
			assertReportFailure(e);
		}
		return map;
	}
}
