package com.cotiviti.hdap.APIPayLoads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mozilla.javascript.Context;

import com.cotiviti.hdap.APICommonLibrary.apiCommonLib;

/**
 * 
 * @author dinesh.neeli
 *
 */
public class RequestGroupSaveProcessForRules extends apiCommonLib {
	/**
	 * @author dinesh.neeli
	 * @param objMap
	 * @return
	 */
	public String requestGroupPFRPayload(ConcurrentHashMap<String, String> objMap) {
		JSONObject requestBody = saveRGPFR(objMap);
		return requestBody.toString();
	}

	/**
	 * @author dinesh.neeli
	 * @param objMap
	 * @return
	 */
	public JSONObject saveRGPFR(ConcurrentHashMap<String, String> objMap) {
		// Create JSON body
		JSONObject requestBody = null;
		try {
			String caseID = objMap.get("CaseID");
			String startDate = objMap.get("StartDate");
			String endDate = objMap.get("EndDate");

			// Extract dynamic fields
			List<String> projectTypes = extractList(objMap.get("ProjectTypes"));

			String env = System.getProperty("env");
			String clientIDs = System.getProperty("clientIDs");


			List<String> chartTypes = extractList(objMap.get("ChartTypes"));
			List<String> auditTypes = extractList(objMap.get("AuditTypes"));

			// Patient Name
			String patientNamePart = objMap.get("PatientNamePart");

			requestBody = new JSONObject();
			requestBody.put("CaseID", caseID);

			// ProcessesForRules object
			JSONObject processesForRules = new JSONObject();

			// ProjectTypes
			processesForRules.put("ProjectTypes", convertToJSONArray(projectTypes));
		
			
			// ClientIDs
			if (clientIDs != null && !clientIDs.isEmpty()) {
				List<String> clientIDs2 = extractList(clientIDs);				
				System.out.println("clientIDs -" + clientIDs);
				processesForRules.put("ClientIDs", convertToJSONArray(clientIDs2));
			} else {
				List<String> clientIDs1 = extractList(objMap.get("ClientIDs"));
				System.out.println("clientIDs -" + clientIDs1);
				processesForRules.put("ClientIDs", convertToJSONArray(clientIDs1));
			}
			
			
			// PatientName
			JSONObject patientNameObj = new JSONObject();
			patientNameObj.put("NamePart", patientNamePart);

			JSONArray rangesArray = new JSONArray();
			JSONObject rangeObj = new JSONObject();
			rangeObj.put("StartChar", objMap.get("StartChar"));
			rangeObj.put("EndChar", objMap.get("EndChar"));
			rangesArray.put(rangeObj);

			patientNameObj.put("Ranges", rangesArray);
			processesForRules.put("PatientName", patientNameObj);

			// ChartTypes
			processesForRules.put("ChartTypes", convertToJSONArray(chartTypes));

			// DatesOfService
			JSONArray datesOfServiceArray = new JSONArray();
			if (startDate != null && endDate != null) {
				JSONObject datesOfServiceObj = new JSONObject();
				datesOfServiceObj.put("StartDate", startDate);
				datesOfServiceObj.put("EndDate", endDate);
				datesOfServiceArray.put(datesOfServiceObj);
			}
			processesForRules.put("DatesOfService", datesOfServiceArray);

			// AuditTypes
			processesForRules.put("AuditTypes", convertToJSONArray(auditTypes));

			requestBody.put("ProcessesForRules", processesForRules);
		} catch (JSONException e) {
//			assertReportFailure(e);

		}

		return requestBody;
	}

	/**
	 * @author dinesh.neeli
	 * @param data
	 * @return
	 */
	private List<String> extractList(String data) {
		if (data == null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(Arrays.asList(data.split("\\|")));
	}

	/**
	 * @author dinesh.neeli
	 * @param items
	 * @return
	 */
	public JSONArray convertToJSONArray(List<String> items) {
		JSONArray jsonArray = new JSONArray();
		for (String item : items) {
			jsonArray.put(new JSONObject().put("Type", item));
		}
		return jsonArray;
	}

}
