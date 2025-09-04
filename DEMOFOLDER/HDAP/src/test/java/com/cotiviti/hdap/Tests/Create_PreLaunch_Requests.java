package com.cotiviti.hdap.Tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.cotiviti.hdap.CommonUtils.PropertiesFileReader;
import com.cotiviti.hdap.StepDefinitions.API_StepDefinition;
import com.cotiviti.hdap.StepDefinitions.CommonFunctions_StepDefinition;

import io.cucumber.java.en.Then;
import io.restassured.RestAssured;

public class Create_PreLaunch_Requests {

	static HashMap<String, String> testData;

	/*
	 * @Then("^Read ExcelData from CreateRGSheet  for  (.+)$") public
	 * HashMap<String, String> ReadExcelSheetDataFromSheet(String TestCaseID) {
	 * testData = ReadDataFromExcel.getExcelData("API", TestCaseID); return
	 * testData; }
	 */

	static String endPoint = PropertiesFileReader.getAPIProperty("chaseRequest_url");
	static String resource = PropertiesFileReader.getAPIProperty("chaseRequest_resource");
	static String intendedUse = PropertiesFileReader.getAPIProperty("intendedUse");
	static String accountID = PropertiesFileReader.getAPIProperty("AccountID");
	static String subAccountID = PropertiesFileReader.getAPIProperty("SubAccountID");
	static String authtoken = API_StepDefinition.Create_Auth();

	@Test(invocationCount = 5)
	public static void ChaseRequest_With_Single_Matching_RG_01() throws InterruptedException {
		String cotivitClaimNumber = CommonFunctions_StepDefinition.getUniqueRandomInteger();
		System.out.println(
				"*******************************Creating Chase Request Matching With Single RG with Below Combination*****************************");
		System.out.println("Intended Use-------------------------->" + intendedUse);
		System.out.println("Account ID---------------------------->" + accountID);
		System.out.println("Sub Account ID----------------------->" + subAccountID);
		System.out.println("Cotiviti Claim Number--------------->" + cotivitClaimNumber);
		System.out.println("<----------RESPONSE BODY--------->");

		RestAssured.baseURI = endPoint;
		// JsonPath js =
		given()// .log().all()
				.header("Content-Type", "application/json").header("Authorization", authtoken)
				.body(com.cotiviti.hdap.APIPayLoads.ChaseRequestPayLoads.payLoad_With_Single_Matching_RG(intendedUse,
						accountID, subAccountID, cotivitClaimNumber))

				.when().post(resource)

				.then().log().body(true).assertThat().statusCode(202).extract().response().jsonPath();

	}

}
