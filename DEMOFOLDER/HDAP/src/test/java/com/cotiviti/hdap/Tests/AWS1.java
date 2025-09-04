package com.cotiviti.hdap.Tests;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.signer.Aws4Signer;
import software.amazon.awssdk.auth.signer.Aws4UnsignedPayloadSigner;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.healthlake.HealthLakeClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

public class AWS1 {

	private static final String BASE_URI = "https://healthlake.us-east-1.amazonaws.com/datastore/your-datastore-id/r4";
	private static final String REGION = "us-east-1";

	@Test
	public static void main(String[] args) {
		// Step 1: Send the FHIR Patient Create API Request
		Response apiResponse = createPatientResource();
		if (apiResponse.getStatusCode() == 201) {
			System.out.println("Patient created successfully in AWS HealthLake.");
		} else {
			System.err.println("Failed to create patient: " + apiResponse.getBody().asString());
			return; // Stop execution if API call fails
		}

		// Step 2 (Optional): Verify Patient on the Web UI using Selenium

	}

	private static Response createPatientResource() {
		System.out.println("Sending request to create a patient in AWS HealthLake...");

		String patientJsonPayload = "sk";

		// Configure RestAssured with AWS SigV4 signing
		RestAssured.baseURI = BASE_URI;
		RequestSpecification request = RestAssured.given().header("Content-Type", "application/json")
				.body(patientJsonPayload);

		/*
		 * Sign the request using AWS Signature Version 4 Aws4UnsignedPayloadSigner
		 * signer = new Aws4UnsignedPayloadSigner();
		 * signer.setServiceName("healthlake"); signer.setRegionName(REGION);
		 * DefaultAWSCredentialsProviderChain credentialsProvider =
		 * DefaultAWSCredentialsProviderChain.getInstance();
		 * 
		 * // Attach the AWS SigV4 signing logic
		 * request.auth().aws(credentialsProvider.getCredentials().getAWSAccessKeyId(),
		 * credentialsProvider.getCredentials().getAWSSecretKey(), REGION, signer);
		 */
		// Perform POST request to create the Patient resource
		return request.post("/Practioner");
	}

}
