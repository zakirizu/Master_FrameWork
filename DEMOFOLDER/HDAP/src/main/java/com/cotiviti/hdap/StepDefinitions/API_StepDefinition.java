package com.cotiviti.hdap.StepDefinitions;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import com.cotiviti.hdap.APIPayLoads.ChaseRequestPayLoads;
import com.cotiviti.hdap.CommonLibrary.ReadDataFromExcel;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class API_StepDefinition {

	HashMap<String, String> testData;
	public static String authtoken;
	// Extract other values from testData

	@Given("^Read ExcelData from API_Sheet for  (.+)$")
	public HashMap<String, String> ReadExcelSheetDataFromSheet(String TestCaseID) {

		testData = ReadDataFromExcel.getExcelData("API", TestCaseID);
		System.out.println("ZR");
		System.out.println(testData);
		// return testData;

		return testData;

	}

	// Modify the original createChaseRequestWithSingleMatchingRG() to handle
	// dynamic invocation count
	@Given("^Create Chase Request with Single Matching RG$")
	public void createChaseRequestWithSingleMatchingRG() throws InterruptedException {

		// Get the "InvocationCount" field from the Excel sheet (this determines how
		// many times to run the API)
		int dynamicInvocationCount = Integer.parseInt(testData.get("InvocationCount"));
		System.out.println("Dynamic Invocation Count: " + dynamicInvocationCount);

		// Loop to run the same logic multiple times based on InvocationCount
		for (int i = 0; i < dynamicInvocationCount; i++) {
			// Here, you could call `executeChaseRequest` as needed
			// If you're passing the same value for each iteration, this will call the API
			// multiple times
			String intendedUseField = testData.get("intendedUse");
			String[] intendedUseValues = intendedUseField.split("\\|");

			for (String intendedUse : intendedUseValues) {
				intendedUse = intendedUse.trim();
				System.out.println("Iteration " + (i + 1) + " - Running API for Intended Use: " + intendedUse);
				Run_API_With_Single_Matching_RG(intendedUse);
			}
		}
	}

	// Modify the original createChaseRequestWithSingleMatchingRG() to handle
	// dynamic invocation count
	@Given("^Create Chase Request with No Matching RG$")
	public void createChaseRequestWithNoMatchingRG() throws InterruptedException {
		// Get the "InvocationCount" field from the Excel sheet (this determines how
		// many times to run the API)
		int dynamicInvocationCount = Integer.parseInt(testData.get("InvocationCount"));
		System.out.println("Dynamic Invocation Count: " + dynamicInvocationCount);

		// Loop to run the same logic multiple times based on InvocationCount
		for (int i = 0; i < dynamicInvocationCount; i++) {
			// Here, you could call `executeChaseRequest` as needed
			// If you're passing the same value for each iteration, this will call the API
			// multiple times
			String intendedUseField = testData.get("intendedUse");
			String[] intendedUseValues = intendedUseField.split("\\|");

			for (String intendedUse : intendedUseValues) {
				intendedUse = intendedUse.trim();
				System.out.println("Iteration " + (i + 1) + " - Running API for Intended Use: " + intendedUse);
				Run_API_With_No_Matching_RG(intendedUse);
			}
		}
	}

	// Modify the original createChaseRequestWithSingleMatchingRG() to handle
	// dynamic invocation count
	@Given("^Create Chase Request with Multiple Matching RG$")
	public void createChaseRequestWithMultipleMatchingRG() throws InterruptedException {
		// Get the "InvocationCount" field from the Excel sheet (this determines how
		// many times to run the API)
		int dynamicInvocationCount = Integer.parseInt(testData.get("InvocationCount"));
		System.out.println("Dynamic Invocation Count: " + dynamicInvocationCount);

		// Loop to run the same logic multiple times based on InvocationCount
		for (int i = 0; i < dynamicInvocationCount; i++) {
			// Here, you could call `executeChaseRequest` as needed
			// If you're passing the same value for each iteration, this will call the API
			// multiple times
			String intendedUseField = testData.get("intendedUse");
			String[] intendedUseValues = intendedUseField.split("\\|");

			for (String intendedUse : intendedUseValues) {
				intendedUse = intendedUse.trim();
				System.out.println("Iteration " + (i + 1) + " - Running API for Intended Use: " + intendedUse);
				Run_API_With_Multiple_Matching_RG(intendedUse);
			}
		}
	}

	private void Run_API_With_Single_Matching_RG(String intendedUse) throws InterruptedException {

		ChaseRequestPayLoads cpl = new ChaseRequestPayLoads();
		// Print request details for debugging purposes
		String endPoint = testData.get("chaseRequest_url");
		String resource = testData.get("chaseRequest_resource");
		String accountID = testData.get("AccountID");
		String subAccountID = testData.get("SubAccountID");
		String cotivitClaimNumber = com.cotiviti.hdap.StepDefinitions.CommonFunctions_StepDefinition
				.getUniqueRandomInteger();

		Thread.sleep(3000);
		System.out.println("Creating Chase Request with the following details:");
		System.out.println("Intended Use: " + intendedUse);
		System.out.println("Account ID: " + accountID);
		System.out.println("Sub Account ID: " + subAccountID);
		System.out.println("Cotiviti Claim Number: " + cotivitClaimNumber);

		// Handle Authentication Token (this logic seems correct, but make sure you
		// handle token creation correctly)
		if (testData.containsKey("Auth_Token") && StringUtils.isNotBlank(testData.get("Auth_Token"))) {
			authtoken = testData.get("Auth_Token");
			System.out.println(testData.get("Auth_Token"));

			System.out.println("Taking Auth_Token from Excel Sheet");
		} else {
			authtoken = Create_Auth();
			System.out.println("Taking Auth_Token from API");
			ReadDataFromExcel.storeDataInExcel("API", authtoken);
			System.out.println("Storing Auth_Token in Excel Sheet");
		}

		// Make the API request with the necessary headers and payload
		RestAssured.baseURI = endPoint;
		given().header("Content-Type", "application/json").header("Authorization", authtoken).body(ChaseRequestPayLoads
				.payLoad_With_Single_Matching_RG(intendedUse, accountID, subAccountID, cotivitClaimNumber)).when()
				.post(resource).then().log().body(true).assertThat().statusCode(202);

		System.out.println("Request completed for Intended Use: " + intendedUse);
	}

	private void Run_API_With_No_Matching_RG(String intendedUse) throws InterruptedException {
		// Print request details for debugging purposes
		String endPoint = testData.get("chaseRequest_url");
		String resource = testData.get("chaseRequest_resource");
		String accountID = testData.get("AccountID");
		String subAccountID = testData.get("SubAccountID");
		String cotivitClaimNumber = com.cotiviti.hdap.StepDefinitions.CommonFunctions_StepDefinition
				.getUniqueRandomInteger();

		System.out.println("Creating Chase Request with the following details:");
		System.out.println("Intended Use: " + intendedUse);
		System.out.println("Account ID: " + accountID);
		System.out.println("Sub Account ID: " + subAccountID);
		System.out.println("Cotiviti Claim Number: " + cotivitClaimNumber);

		// Handle Authentication Token (this logic seems correct, but make sure you
		// handle token creation correctly)
		if (testData.containsKey("Auth_Token") && StringUtils.isNotBlank(testData.get("Auth_Token"))) {
			authtoken = testData.get("Auth_Token");
			System.out.println(testData.get("Auth_Token"));

			System.out.println("Taking Auth_Token from Excel Sheet");
		} else {
			authtoken = Create_Auth();
			System.out.println("Taking Auth_Token from API");
			ReadDataFromExcel.storeDataInExcel("API", authtoken);
			System.out.println("Storing Auth_Token in Excel Sheet");
		}

		// Make the API request with the necessary headers and payload
		RestAssured.baseURI = endPoint;
		given().header("Content-Type", "application/json").header("Authorization", authtoken).body(ChaseRequestPayLoads
				.payLoad_With_No_Matching_RG(intendedUse, accountID, subAccountID, cotivitClaimNumber)).when()
				.post(resource).then().log().body(true).assertThat().statusCode(202);

		System.out.println("Request completed for Intended Use: " + intendedUse);
	}

	private void Run_API_With_Multiple_Matching_RG(String intendedUse) throws InterruptedException {
		ChaseRequestPayLoads cpl = new ChaseRequestPayLoads();
		// Print request details for debugging purposes
		String endPoint = testData.get("chaseRequest_url");
		String resource = testData.get("chaseRequest_resource");
		String accountID = testData.get("AccountID");
		String subAccountID = testData.get("SubAccountID");
		String cotivitClaimNumber = com.cotiviti.hdap.StepDefinitions.CommonFunctions_StepDefinition
				.getUniqueRandomInteger();
		System.out.println("Creating Chase Request with the following details:");
		System.out.println("Intended Use: " + intendedUse);
		System.out.println("Account ID: " + accountID);
		System.out.println("Sub Account ID: " + subAccountID);
		System.out.println("Cotiviti Claim Number: " + cotivitClaimNumber);

		// Handle Authentication Token (this logic seems correct, but make sure you
		// handle token creation correctly)
		if (testData.containsKey("Auth_Token") && StringUtils.isNotBlank(testData.get("Auth_Token"))) {
			authtoken = testData.get("Auth_Token");
			System.out.println(testData.get("Auth_Token"));

			System.out.println("Taking Auth_Token from Excel Sheet");
		} else {
			authtoken = Create_Auth();
			System.out.println("Taking Auth_Token from API");
			ReadDataFromExcel.storeDataInExcel("API", authtoken);
			System.out.println("Storing Auth_Token in Excel Sheet");
		}

		// Make the API request with the necessary headers and payload
		RestAssured.baseURI = endPoint;
		given().header("Content-Type", "application/json").header("Authorization", authtoken).body(ChaseRequestPayLoads
				.payLoad_With_Multile_Matching_RG(intendedUse, accountID, subAccountID, cotivitClaimNumber)).when()
				.post(resource).then().log().body(true).assertThat().statusCode(202);

		System.out.println("Request completed for Intended Use: " + intendedUse);
	}

	public static String Create_Auth() {

		com.cotiviti.hdap.CommonUtils.DependencyInjection dp = new com.cotiviti.hdap.CommonUtils.DependencyInjection();

		System.out.println("Create Auth Running ");
		// Set the base URI for the authorization server
		RestAssured.baseURI = "https://cotiviti-ext-devtest.oktapreview.com"; // Your auth endpoint

		String authorizationHeader = "Basic MG9hMjVod20zNHpablZVck0waDg6M21jR21jNk91MGplZFZwX2NTVHdiWFIwTWUybURVWW5ZNWRXSmt2YnpuQ1NwZXI1YkdZVGFHTHNTSEZ4ZFpxYg==";
		String sessionCookie = "E3FDDF4D92FD562312A9540EDFCA370A";

		// Send a POST request with the necessary headers
		Response response = RestAssured.given().header("Authorization", authorizationHeader) // Add the Authorization
																								// header
				.header("Cookie", "JSESSIONID=" + sessionCookie) // Add the session cookie
				.contentType(ContentType.URLENC) // Content type is URL encoded
				.accept(ContentType.JSON) // Accepting JSON responses
				.formParam("audience", "api://default") // Example form parameter
				.formParam("grant_type", "client_credentials") // Example form parameter
				.formParam("scope", "hdap_scope") // Example form parameter
				.when().post("/oauth2/default/v1/token") // Example endpoint
				.then().statusCode(200) // Expect HTTP 200 OK status code
				.extract().response(); // Extract the response

		String responseBody = response.getBody().asString(); // Get the response body as a string
		System.out.println("Response Body: " + responseBody);

		System.out.println("authToken" + response);
		// Extract the access_token from the response JSON
		String authToken = response.jsonPath().getString("access_token");
		System.out.println("authToken" + authToken);

		// Extract the access_token from the response JSON
		authToken = response.jsonPath().getString("access_token"); // Adjust the path if necessary

		if (authToken == null || authToken.isEmpty()) {
			org.testng.Assert.fail("Authorization token not generated.");
		}

		System.out.println("Generated Auth Token: " + authToken); // Optional: Print the token for debugging
		// return authToken;

		// dp.setAuth(authToken);

		return authToken;
	}

}