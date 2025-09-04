package com.cotiviti.hdap.APICommonLibrary;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * @author dinesh.neeli
 *
 */
public class apiAuthorization extends apiCommonLib {

	public apiAuthorization() {
		super();
	}

	public String createUATAuth() {
		// Extract the access_token from the response JSON
		String authToken = "";
		try {
			System.out.println("Create UAT Auth Token ");
			// Set the base URI for the authorization server
			RestAssured.baseURI = "https://login-preview.cotiviti.com"; // Your auth endpoint

			String authorizationHeader = "Basic MG9hMjlmNXA5bHV5M3hXa2QwaDg6TDVheTFXenl3U3B4ZVNMOHAweVVLWkRTRWRiM1FXcFZEdnRpMmVQOGpmOXBZeWFXY3BLbHF1M0E0UXEtOHV2Nw==";

			// Send a POST request with the necessary headers
			Response response = RestAssured.given().header("Authorization", authorizationHeader)
					.contentType(ContentType.URLENC) // Content type is URL encoded
					.accept(ContentType.JSON) // Accepting JSON responses
					.formParam("audience", "api://default") // Example form parameter
					.formParam("grant_type", "client_credentials") // Example form parameter
					.formParam("scope", "hdap_scope") // Example form parameter
					.when().post("/oauth2/default/v1/token") // Example endpoint
					.then().statusCode(200) // Expect HTTP 200 OK status code
					.extract().response(); // Extract the response

			authToken = response.jsonPath().getString("access_token");
			// Extract the access_token from the response JSON
			authToken = response.jsonPath().getString("access_token"); // Adjust the path if necessary

			if (authToken == null || authToken.isEmpty()) {
				Assert.fail("Authorization token not generated in UAT");
			}
			System.out.println("Generated Auth Token: " + authToken); // Optional: Print the token for debugging

		} catch (Exception e) {
			Assert.fail(e.getLocalizedMessage());
		}
		return authToken;
	}

	public String createQAAuth() {
		String authToken = "";
		try {
			System.out.println("Create QA Auth Token");
			// Set the base URI for the authorization server
			RestAssured.baseURI = "https://cotiviti-ext-devtest.oktapreview.com"; // Your auth endpoint

			String authorizationHeader = "Basic MG9hMjVod20zNHpablZVck0waDg6M21jR21jNk91MGplZFZwX2NTVHdiWFIwTWUybURVWW5ZNWRXSmt2YnpuQ1NwZXI1YkdZVGFHTHNTSEZ4ZFpxYg==";

			// Send a POST request with the necessary headers
			Response response = RestAssured.given().header("Authorization", authorizationHeader)
					.contentType(ContentType.URLENC) // Content type is URL encoded
					.accept(ContentType.JSON) // Accepting JSON responses
					.formParam("audience", "api://default") // Example form parameter
					.formParam("grant_type", "client_credentials") // Example form parameter
					.formParam("scope", "hdap_scope") // Example form parameter
					.when().post("/oauth2/default/v1/token") // Example endpoint
					.then().statusCode(200) // Expect HTTP 200 OK status code
					.extract().response(); // Extract the response

			// Extract the access_token from the response JSON
			authToken = response.jsonPath().getString("access_token");

			// Extract the access_token from the response JSON
			authToken = response.jsonPath().getString("access_token"); // Adjust the path if necessary

			if (authToken == null || authToken.isEmpty()) {
				Assert.fail("Authorization token not generated in QA");
			}

			System.out.println("Generated Auth Token: " + authToken); // Optional: Print the token for debugging

		} catch (Exception e) {
			Assert.fail(e.getLocalizedMessage());
		}
		return authToken;
	}

}
