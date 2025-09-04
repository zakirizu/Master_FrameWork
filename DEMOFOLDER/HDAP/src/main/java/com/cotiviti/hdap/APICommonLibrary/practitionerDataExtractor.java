package com.cotiviti.hdap.APICommonLibrary;

import java.util.concurrent.ConcurrentHashMap;

import org.testng.Assert;

import com.cotiviti.hdap.CommonUtils.PropertiesFileReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class practitionerDataExtractor extends apiCommonLib {

	public ConcurrentHashMap<String, String> getPractitionerData(ConcurrentHashMap<String, String> dataMap,
			String practitionerId, String authToken) {

		String tcName = new Object() {
		}.getClass().getEnclosingMethod().getName();

		String env = System.getProperty("env");
		if (env.equalsIgnoreCase("UAT")) {
			practitionerId = "P-470610020225";
		} else if (env.equalsIgnoreCase("QA")) {
			practitionerId = "P-862084210225";
		} else if (env.equalsIgnoreCase("DEV")) {
			practitionerId = "P-502676300724";
		} else {
			System.out.println("practitionerId is empty");
		}

		try {
			Thread.sleep(1000);
			// Construct the API URL dynamically with the Practitioner ID
			String apiUrl = PropertiesFileReader.getAPIProperty(env + ".PractitionerEndPoint")
					.replace("{{PractitionerId}}", practitionerId);

			// Fetch response from the API
			String jsonResponse = sendGetRequest(apiUrl, authToken);

			// Parse the JSON response
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonResponse);

			// Extract the required values (adjusting for the correct JSON field names)
			String firstName = rootNode.path("FirstName").asText();
			String lastName = rootNode.path("LastName").asText();
			String tin = rootNode.path("TIN").asText();
			String npi = rootNode.path("NPI").asText();
			String phone = rootNode.path("Phone").asText();
			String email = rootNode.path("Email").asText();
			String fax = rootNode.path("Fax").asText();
			String pager = rootNode.path("Pager").asText();
			String languages = rootNode.path("Languages").asText();
			String specialty = rootNode.path("Specialty").asText();

			// Extract address info (addresses is an array)
			String street = "", city = "", state = "", postalCode = "";
			JsonNode addressesNode = rootNode.path("Addresses");
			if (addressesNode.isArray() && addressesNode.size() > 0) {
				JsonNode addressNode = addressesNode.get(0); // Get the first address
				String address = addressNode.path("Address").asText();

				// Use regular expressions to split the address into street, city, state, and
				// postal code
				String[] addressParts = parseAddress(address);
				street = addressParts[0];
				city = addressParts[1];
				state = addressParts[2];
				postalCode = addressParts[3];
			}

			// Add extracted values to the dataMap
			dataMap.put("PfirstName", firstName);
			dataMap.put("PlastName", lastName);
			dataMap.put("Ptin", tin);
			dataMap.put("Pnpi", npi);
			dataMap.put("Pphone", phone);
			dataMap.put("Pemail", email);
			dataMap.put("Pfax", fax);
			dataMap.put("Ppager", pager);
			dataMap.put("Planguages", languages);
			dataMap.put("Pspecialty", specialty);
			dataMap.put("Pstreet", street);
			dataMap.put("Pcity", city);
			dataMap.put("Pstate", state);
			dataMap.put("PpostalCode", postalCode);
			dataMap.put("PpractitionerId", practitionerId);

			System.out.println("Fetch Practitioner data completed");

		} catch (Exception e) {
			executionMap.put(tcName + ".tcStatus", "Failed");
			executionMap.put(tcName + ".comments", "TC Failed due to error" + e.getLocalizedMessage());
			Assert.fail(e.getLocalizedMessage());
		}
		return dataMap;
	}

}
