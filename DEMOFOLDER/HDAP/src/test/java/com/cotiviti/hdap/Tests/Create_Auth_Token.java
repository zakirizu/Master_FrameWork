package com.cotiviti.hdap.Tests;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import com.cotiviti.hdap.CommonUtils.DependencyInjection;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Create_Auth_Token {

	/*
	 * static String endPoint =
	 * PropertiesFileReader.getAPIProperty("chaseRequest_url"); static String
	 * resource = PropertiesFileReader.getAPIProperty("chaseRequest_resource");
	 * static String intendedUse =
	 * PropertiesFileReader.getAPIProperty("intendedUse"); static String accountID =
	 * PropertiesFileReader.getAPIProperty("AccountID"); static String subAccountID
	 * = PropertiesFileReader.getAPIProperty("SubAccountID");
	 * 
	 */
	// @Test(invocationCount = 1)
	public static String Create_Auth() {

		DependencyInjection dp = new DependencyInjection();

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
				.when().post("/oauth2/default/v1/token"); // Example endpoint
		// .then()
		// .statusCode(200) // Expect HTTP 200 OK status code
		// .extract()
		// .response(); // Extract the response

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
