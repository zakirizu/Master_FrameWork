package com.cotiviti.hdap.APICommonLibrary;

import java.util.concurrent.ConcurrentHashMap;

import org.testng.Assert;

import com.cotiviti.hdap.CommonUtils.PropertiesFileReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class facitilityDataExtractor extends apiCommonLib {

	public ConcurrentHashMap<String, String> getFacitilityData(ConcurrentHashMap<String, String> dataMap,
			String facilityId, String authToken) {

		String myMethod = new Object() {
		}.getClass().getEnclosingMethod().getName();

		String env = System.getProperty("env");
		String tcName = dataMap.get("TESTCASENAME");
		try {
			Thread.sleep(15000);
			String apiUrl = PropertiesFileReader.getAPIProperty(env + ".FacilityEndPoint").replace("{{FacilityId}}",
					facilityId);

			// Fetch response from the API
			String jsonResponse = sendGetRequest(apiUrl, authToken);
//			System.err.println("Facility response : " + jsonResponse);
			// Parse the JSON response
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonResponse);

			// Extract the required values from the response (update field names)
			String name = rootNode.path("Name").asText();
			String tin = rootNode.path("TIN").asText();
			String npi = rootNode.path("NPI").asText();
			String phone = rootNode.path("Phone").asText();
			phone = phone.replaceAll("[^0-9]", "").replaceAll("(\\d{3})(\\d{3})(\\d{4})", "$1-$2-$3");
			// Extract address info (addresses is an array)
			String addressLine1 = "", city = "", state = "", postalCode = "";
			JsonNode addressesNode = rootNode.path("Addresses");
			if (addressesNode.isArray() && addressesNode.size() > 0) {
				JsonNode addressNode = addressesNode.get(0); // Get the first address
				String address = addressNode.path("Address").asText();

				// Use regular expressions to split the address into street, city, state, and
				// postal code
				String[] addressParts = parseAddress(address);
				int l = addressParts.length;
				if (l == 4) {
					addressLine1 = addressParts[0];
					city = addressParts[1];
					state = addressParts[2];
					postalCode = addressParts[3];
				} else if (l == 5) {
					addressLine1 = addressParts[0];
					city = addressParts[2];
					state = addressParts[3];
					postalCode = addressParts[4];
				}
			}

			// Extract Facility from the response (assuming it's in the API response)
			String facility = rootNode.path("Facility").asText();

			// Add extracted values to the data map
			dataMap.put("Fname", name);
			dataMap.put("Ftin", tin);
			dataMap.put("Fnpi", npi);
			dataMap.put("Fstreet", addressLine1);
			dataMap.put("Fcity", city);
			dataMap.put("Fstate", state);
			dataMap.put("FpostalCode", postalCode);
			dataMap.put("Fphone", phone);
			dataMap.put("Facility", facility);
			System.out.println("Fetch Facility data completed");

		} catch (Exception e) {
			executionMap.put(tcName + ".tcStatus", "Failed");
			executionMap.put(tcName + ".comments", "TC Failed due to error" + e.getLocalizedMessage());
			Assert.fail(e.getLocalizedMessage());
		}
		return dataMap;
	}

}
