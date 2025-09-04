package com.cotiviti.hdap.Tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import com.cotiviti.hdap.CommonLibrary.ReadDataFromExcel;
import com.cotiviti.hdap.CommonUtils.PropertiesFileReader;
import com.cotiviti.hdap.StepDefinitions.API_StepDefinition;
import com.cotiviti.hdap.StepDefinitions.CommonFunctions_StepDefinition;

import io.cucumber.java.en.Then;
import io.restassured.RestAssured;

public class bulkrun {

	HashMap<String, String> testData;

	public HashMap<String, String> ReadExcelSheetDataFromSheet(String TestCaseID) {
		testData = ReadDataFromExcel.getExcelData("createRG", TestCaseID);
		return testData;
	}

	@Test(invocationCount = 10)
	private void Run_API_With_Single_Matching_RG(String intendedUse) throws InterruptedException {
		// Print request details for debugging purposes
		String endPoint = PropertiesFileReader.getAPIProperty("chaseRequest_url");
		String resource = PropertiesFileReader.getAPIProperty("chaseRequest_resource");
		String intendedUse1 = PropertiesFileReader.getAPIProperty("intendedUse");
		String accountID = PropertiesFileReader.getAPIProperty("AccountID");
		String subAccountID = PropertiesFileReader.getAPIProperty("SubAccountID");
		String authtoken = API_StepDefinition.Create_Auth();
		String cotivitClaimNumber = CommonFunctions_StepDefinition.getUniqueRandomInteger();

		Thread.sleep(3000);
		System.out.println("Creating Chase Request with the following details:");
		System.out.println("Intended Use: " + intendedUse1);
		System.out.println("Account ID: " + accountID);
		System.out.println("Sub Account ID: " + subAccountID);

		// System.out.println("Cotiviti Claim Number: " + cotivitClaimNumber);
		//
		// Handle Authentication Token (this logic seems correct, but make sure you
		// handle token creation correctly)
		if (testData.containsKey("Auth_Token") && StringUtils.isNotBlank(testData.get("Auth_Token"))) {
			authtoken = testData.get("Auth_Token");
			System.out.println(testData.get("Auth_Token"));

			System.out.println("Taking Auth_Token from Excel Sheet");
		} else {
			authtoken = API_StepDefinition.Create_Auth();
			System.out.println("Taking Auth_Token from API");
			ReadDataFromExcel.storeDataInExcel("API", authtoken);
			System.out.println("Storing Auth_Token in Excel Sheet");
		}

		// Make the API request with the necessary headers and payload
		RestAssured.baseURI = endPoint;
		given().header("Content-Type", "application/json").header("Authorization", authtoken)
				.body(com.cotiviti.hdap.APIPayLoads.ChaseRequestPayLoads.payLoad_With_Single_Matching_RG(intendedUse1,
						accountID, subAccountID, cotivitClaimNumber))
				.when().post(resource).then().log().body(true).assertThat().statusCode(202);

		System.out.println("Request completed for Intended Use: " + intendedUse1);
	}

}
