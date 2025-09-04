package com.cotiviti.hdap.APIPayLoads;
 
import java.util.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import com.cotiviti.hdap.APICommonLibrary.ExcelUtils;
import com.cotiviti.hdap.APICommonLibrary.apiCommonLib;
 
public class CreateFacility extends apiCommonLib {
 
	public String getPayloadFromExcelData() {
		Map<String, String> rowData = ExcelUtils.getNextUnusedRowData(); // this should fetch and mark the row as used
		//error is here for qa sanity
		if (rowData == null || rowData.isEmpty()) {
			throw new RuntimeException("No unused row found in Excel.");
		}
 
		ConcurrentHashMap<String, String> objMap = new ConcurrentHashMap<>(rowData);
		return requestGroupFacility(objMap);
	}
 
	public String requestGroupFacility(ConcurrentHashMap<String, String> objMap) {
		JSONObject requestBody = facility(objMap);
		return requestBody.toString();
	}
 
	public JSONObject facility(ConcurrentHashMap<String, String> objMap) {
		// Create JSON body
		String env = System.getProperty("env");
		JSONObject requestBody = null;
		String healthSystemID = "";
		try {
			String caseID = "PR-1237";
 
			// Extract Facility data
			String correlationID = "CR-01";
			String name = objMap.get("Organization Name");
 
			if (env.equalsIgnoreCase("QA")) {
				healthSystemID = "https://rsbhg449x8-vpce-05e94df8b14040064.execute-api.us-east-1.amazonaws.com/qa/HDAP/Workflow/v1/HealthSystems/cf85ea18-ba19-419d-9522-bc5ec648013f";
			} else if (env.equalsIgnoreCase("UAT")) {
				healthSystemID = "https://is8i4ayzcg.execute-api.us-east-1.amazonaws.com/uat/HDAP/Workflow/v1/HealthSystems/6ed2b34e-645b-450b-93fa-aded387148da";
			} else if (env.equalsIgnoreCase("DEV")) {
				healthSystemID = "https://bc76dg4vwl.execute-api.us-east-1.amazonaws.com/dev/HDAP/Workflow/v1/HealthSystems/04d6ee06-b837-4e53-8c03-ae81863b9bfc";
			}
			String phone = objMap.get("Phone Number");
			String npi = objMap.get("NPI Number");
			String tin = objMap.get("TIN");
			boolean isActive = true;
			boolean isValidated = false;
 
			// Addresses data
			JSONArray addressesArray = new JSONArray();
 
			String address1Type = "Physical";
			String address1Line1 = objMap.get("Address Line 1");
			String address1Line2 = objMap.get("Address1Line2");
			String address1City = objMap.get("City");
			String address1State = objMap.get("State");
			String address1Zip = objMap.get("Postal Code");
			boolean address1IsPrimary = true;
			boolean address1IsActive = true;
 
			// Create Address object for Address 1
			JSONObject address1 = new JSONObject();
			address1.put("Type", address1Type);
			address1.put("Line1", address1Line1);
			address1.put("Line2", address1Line2);
			address1.put("City", address1City);
			address1.put("State", address1State);
			address1.put("Zip", address1Zip);
			address1.put("IsPrimary", address1IsPrimary);
			address1.put("IsActive", address1IsActive);
			address1.put("IsValidated", isValidated);
 
			addressesArray.put(address1);
//secondary random address
			List<String> addressList = Arrays.asList("10 TOWER DR, STREET, SUN PRAIRIE, WI, 53590-1239",
					"123 MAIN ST, APT 4B, MADISON, WI, 53703", "45 LAKE VIEW, BLVD, MILWAUKEE, WI, 53202",
					"88 HILLTOP LN, UNIT 2, GREEN BAY, WI, 54303", "200 RIVER RD, SUITE 500, OSHKOSH, WI, 54901",
					"67 FOREST DR, FL 1, WAUSAU, WI, 54401", "15 OAK ST, FLOOR 3, RACINE, WI, 53403",
					"501 WEST WASHINGTON AVE, SUITE 120, MADISON, WI, 53703",
					"305 N MAIN ST, UNIT B, APPLETON, WI, 54911", "12 MARKET SQUARE, SUITE 2, LA CROSSE, WI, 54601",
					"820 PARK ST, 2ND FLOOR, FOND DU LAC, WI, 54935", "440 EAST GRAND AVE, ROOM 112, BELOIT, WI, 53511",
					"955 MAPLEWOOD LN, APT 6, JANESVILLE, WI, 53545", "709 PINE ST, SUITE 101, SHEBOYGAN, WI, 53081",
					"600 CEDAR ST, BUILDING A, MENASHA, WI, 54952", "100 W MAIN ST, OFFICE 205, BARABOO, WI, 53913",
					"345 RIVER RIDGE DR, STE 300, EAU CLAIRE, WI, 54703",
					"1340 UNIVERSITY AVE, ROOM 401, GREEN BAY, WI, 54302", "987 LAKE DR, APT 15, MANITOWOC, WI, 54220",
					"870 INDUSTRIAL BLVD, BAY A, NEENAH, WI, 54956", "2000 LINCOLN AVE, FLOOR 4, SUPERIOR, WI, 54880");
 
			// Desired number of secondary addresses
			int secondaryAddressCount = Integer.parseInt(executionMap.get("SecondaryAddressCount"));
 
			// JSONArray addressesArray2 = new JSONArray();
			// Define the possible address types
			String[] addressTypes = { "Medical Records/Release of Information", "Billing"};
			Random random = new Random();
 
			// Shuffle to get random addresses
			Collections.shuffle(addressList);
			int totalNeeded = secondaryAddressCount;
 
			for (int i = 0; i < totalNeeded; i++) {
				String[] parts = addressList.get(i).split(",\\s*");
				JSONObject address = new JSONObject();
 
				// Randomly select an address type
				String randomType = addressTypes[random.nextInt(addressTypes.length)];
 
				address.put("Type", randomType);
				address.put("Line1", parts[0]);
				address.put("Line2", parts[1]);
				address.put("City", parts[2]);
				address.put("State", parts[3]);
				address.put("Zip", parts[4]);
				address.put("IsPrimary", false);
				address.put("IsActive", true);
				address.put("IsValidated", isValidated);
 
				addressesArray.put(address);
			}
 
			// Create Facility object
			JSONObject facilityObj = new JSONObject();
			facilityObj.put("CorrelationID", correlationID);
			facilityObj.put("Name", name);
//			facilityObj.put("HealthSystemID", healthSystemID);
			facilityObj.put("Phone", phone);
			facilityObj.put("NPI", npi);
			facilityObj.put("TIN", tin);
			facilityObj.put("IsActive", isActive);
			facilityObj.put("Addresses", addressesArray);
			facilityObj.put("IsValidated", isValidated);
 
			// Create the root object with CaseID and Facilities
			requestBody = new JSONObject();
			requestBody.put("CaseID", caseID);
 
			JSONArray facilitiesArray = new JSONArray();
			facilitiesArray.put(facilityObj);
 
			requestBody.put("Facilities", facilitiesArray);
 
		} catch (JSONException e) {
			// Handle the exception (logging, rethrow, etc.)
			e.printStackTrace();
		}
 
		return requestBody;
	}
}
