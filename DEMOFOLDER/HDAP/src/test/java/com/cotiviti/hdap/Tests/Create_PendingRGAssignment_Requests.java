package com.cotiviti.hdap.Tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cotiviti.hdap.CommonLibrary.ReadDataFromExcel;
import com.cotiviti.hdap.CommonUtils.DependencyInjection;
import com.cotiviti.hdap.CommonUtils.PropertiesFileReader;
import com.cotiviti.hdap.StepDefinitions.API_StepDefinition;
import com.cotiviti.hdap.StepDefinitions.CommonFunctions_StepDefinition;

import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Create_PendingRGAssignment_Requests {

	static String endPoint = PropertiesFileReader.getAPIProperty("chaseRequest_url");
	static String resource = PropertiesFileReader.getAPIProperty("chaseRequest_resource");
	static String intendedUse = PropertiesFileReader.getAPIProperty("intendedUse");
	static String accountID = PropertiesFileReader.getAPIProperty("AccountID");
	static String subAccountID = PropertiesFileReader.getAPIProperty("SubAccountID");
	static String authtoken = API_StepDefinition.authtoken;

	// static String authtoken =
	// "eyJraWQiOiJlOUVra2FxWHlwRjlhV2hmNTh3b1d4U3k2dnA1WGpyY1BFc2FYUGFVV0ZBIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULlJZQ3Fib2xoQmZ0NzNETG15UUp2QWxSWE5yV3RoVUUxX0JtZXZvWGw4N1kiLCJpc3MiOiJodHRwczovL2NvdGl2aXRpLWV4dC1kZXZ0ZXN0Lm9rdGFwcmV2aWV3LmNvbS9vYXV0aDIvZGVmYXVsdCIsImF1ZCI6ImFwaTovL2RlZmF1bHQiLCJpYXQiOjE3MzIxMDQ2OTAsImV4cCI6MTczMjEwODI5MCwiY2lkIjoiMG9hMjVod20zNHpablZVck0waDgiLCJzY3AiOlsiaGRhcF9zY29wZSJdLCJzdWIiOiIwb2EyNWh3bTM0elpuVlVyTTBoOCJ9.ebRmW-orghxWMRuIeHNRCfA4nSItGqAHbwk8_hl82sOIXD_pq7D2BmxqFCJczNg6LWKKv8idqFaKArKStOH2_SjIqw94Cke-NbCtoFnXMQI3bjqeSHhe1N-PIGwo0NUxM69A_1cBoZyWvNybWa3938G_w-E9uubTGlaZ4TMPzS8e_DS_946whYsbvvKp5Y4xSf4pgGO3_7WquWD2zANntILwas_G17D0FBryHLE1L0g5IOJXOnjVxFNm2NLqirtHQW89IXOznvB-CC1SwX7VudesmUlSMCQLmIHYllKtuB53IZZqcdsRwlS_Bw7-Dq--Ua8k_ZhTJ_xHZYI9hGdwpw";

	HashMap<String, String> testData;

	@Then("^Read ExcelData from CreateRGSheet  for  (.+)$")
	public HashMap<String, String> ReadExcelSheetDataFromSheet(String TestCaseID) {
		testData = ReadDataFromExcel.getExcelData("createRG", TestCaseID);
		return testData;
	}

	private static int invocationCounter = 0;

	@BeforeMethod
	public void beforeMethod() {
		invocationCounter++; // Increment the counter before each test execution
	}

	@Test(invocationCount = 150)
	public static void ChaseRequest_With_No_Matching_RG() throws InterruptedException

	{

		System.out.println("****************************** Execution Count: " + invocationCounter
				+ " ******************************");

		String cotivitClaimNumber = CommonFunctions_StepDefinition.getUniqueRandomInteger();
		System.out.println(
				"*******************************Creating Chase Request with NO Matching Single RG with Below Combination*****************************");
		System.out.println("Intended Use------------------------->" + intendedUse);
		System.out.println("Account ID---------------------------->" + accountID);
		System.out.println("Sub Account ID----------------------->" + subAccountID);
		System.out.println("Cotiviti Claim Number-------------->" + cotivitClaimNumber);
		System.out.println("<----------RESPONSE BODY--------->");

		// Create_Auth_Token auth = new Create_Auth_Token();

		// String authtoken = Create_PendingRGAssignment_Requests.Create_Auth();
		RestAssured.baseURI = endPoint;
		// JsonPath js =
		given()// .log().all()
				.header("Content-Type", "application/json").header("Authorization", authtoken)
				.body(com.cotiviti.hdap.APIPayLoads.ChaseRequestPayLoads.payLoad_With_Single_Matching_RG(intendedUse,
						accountID, subAccountID, cotivitClaimNumber))
				.when().post(resource)

				.then().log().body(true).assertThat().statusCode(202).extract().response().jsonPath();

	}

//	public static String Create_Auth() {
//
//		DependencyInjection dp = new DependencyInjection();
//
//		System.out.println("Create Auth Running ");
//		// Set the base URI for the authorization server
//		RestAssured.baseURI = "https://cotiviti-ext-devtest.oktapreview.com"; // Your auth endpoint
//
//		String authorizationHeader = "Basic MG9hMjVod20zNHpablZVck0waDg6M21jR21jNk91MGplZFZwX2NTVHdiWFIwTWUybURVWW5ZNWRXSmt2YnpuQ1NwZXI1YkdZVGFHTHNTSEZ4ZFpxYg==";
//		String sessionCookie = "E3FDDF4D92FD562312A9540EDFCA370A";
//
//		// Send a POST request with the necessary headers
//		Response response = RestAssured.given().header("Authorization", authorizationHeader) // Add the Authorization
//																								// header
//				.header("Cookie", "JSESSIONID=" + sessionCookie) // Add the session cookie
//				.contentType(ContentType.URLENC) // Content type is URL encoded
//				.accept(ContentType.JSON) // Accepting JSON responses
//				.formParam("audience", "api://default") // Example form parameter
//				.formParam("grant_type", "client_credentials") // Example form parameter
//				.formParam("scope", "hdap_scope") // Example form parameter
//				.when().post("/oauth2/default/v1/token") // Example endpoint
//				.then().statusCode(200) // Expect HTTP 200 OK status code
//				.extract().response(); // Extract the response
//
//		String responseBody = response.getBody().asString(); // Get the response body as a string
//		System.out.println("Response Body: " + responseBody);
//
//		System.out.println("authToken" + response);
//		// Extract the access_token from the response JSON
//		String authToken = response.jsonPath().getString("access_token");
//		System.out.println("authToken" + authToken);
//
//		// Extract the access_token from the response JSON
//		authToken = response.jsonPath().getString("access_token"); // Adjust the path if necessary
//
//		if (authToken == null || authToken.isEmpty()) {
//			org.testng.Assert.fail("Authorization token not generated.");
//		}
//
//		System.out.println("Generated Auth Token: " + authToken); // Optional: Print the token for debugging
//		// return authToken;
//
//		// dp.setAuth(authToken);
//
//		return authToken;
//	}

}
