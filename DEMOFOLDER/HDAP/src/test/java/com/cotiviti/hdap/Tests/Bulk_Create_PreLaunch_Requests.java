package com.cotiviti.hdap.Tests;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import com.cotiviti.hdap.CommonUtils.PropertiesFileReader;
import com.cotiviti.hdap.StepDefinitions.CommonFunctions_StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;

public class Bulk_Create_PreLaunch_Requests {
	static String endPoint = PropertiesFileReader.getAPIProperty("chaseRequest_url");
	static String resource = PropertiesFileReader.getAPIProperty("chaseRequest_resource");
	static String intendedUse = PropertiesFileReader.getAPIProperty("intendedUse");
	static String accountID = PropertiesFileReader.getAPIProperty("AccountID");
	static String subAccountID = PropertiesFileReader.getAPIProperty("SubAccountID");

	@Test(invocationCount = 15)
	@Given("^Execute ChaseRequest_With_Single_Matching_RG_01$")
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
				.header("Content-Type", "application/json")
				.body(com.cotiviti.hdap.APIPayLoads.ChaseRequestPayLoads.payLoad_With_Single_Matching_RG(intendedUse,
						accountID, subAccountID, cotivitClaimNumber))

				.when().post(resource)

				.then().log().body(true).assertThat().statusCode(202).extract().response().jsonPath();

	}

	@Test(invocationCount = 10)
	public static void ChaseRequest_With_Single_Matching_RG_02() throws InterruptedException {
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
				.header("Content-Type", "application/json")
				.body(com.cotiviti.hdap.APIPayLoads.ChaseRequestPayLoads.payLoad_With_Single_Matching_RG(intendedUse,
						accountID, subAccountID, cotivitClaimNumber))

				.when().post(resource)

				.then().log().body(true).assertThat().statusCode(202).extract().response().jsonPath();

	}

	@Test(invocationCount = 10)
	public static void ChaseRequest_With_Single_Matching_RG_03() throws InterruptedException {
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
				.header("Content-Type", "application/json")
				.body(com.cotiviti.hdap.APIPayLoads.ChaseRequestPayLoads.payLoad_With_Single_Matching_RG(intendedUse,
						accountID, subAccountID, cotivitClaimNumber))

				.when().post(resource)

				.then().log().body(true).assertThat().statusCode(202).extract().response().jsonPath();

	}

	@Test(invocationCount = 10)
	public static void ChaseRequest_With_Single_Matching_RG_04() throws InterruptedException {
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
				.header("Content-Type", "application/json")
				.body(com.cotiviti.hdap.APIPayLoads.ChaseRequestPayLoads.payLoad_With_Single_Matching_RG(intendedUse,
						accountID, subAccountID, cotivitClaimNumber))

				.when().post(resource)

				.then().log().body(true).assertThat().statusCode(202).extract().response().jsonPath();

	}

	@Test(invocationCount = 10)
	public static void ChaseRequest_With_Single_Matching_RG_05() throws InterruptedException {
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
				.header("Content-Type", "application/json")
				.body(com.cotiviti.hdap.APIPayLoads.ChaseRequestPayLoads.payLoad_With_Single_Matching_RG(intendedUse,
						accountID, subAccountID, cotivitClaimNumber))

				.when().post(resource)

				.then().log().body(true).assertThat().statusCode(202).extract().response().jsonPath();

	}

	@Test(invocationCount = 10)
	public static void ChaseRequest_With_Single_Matching_RG_06() throws InterruptedException {
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
				.header("Content-Type", "application/json")
				.body(com.cotiviti.hdap.APIPayLoads.ChaseRequestPayLoads.payLoad_With_Single_Matching_RG(intendedUse,
						accountID, subAccountID, cotivitClaimNumber))

				.when().post(resource)

				.then().log().body(true).assertThat().statusCode(202).extract().response().jsonPath();

	}

	@Test(invocationCount = 10)
	public static void ChaseRequest_With_Single_Matching_RG_07() throws InterruptedException {
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
				.header("Content-Type", "application/json")
				.body(com.cotiviti.hdap.APIPayLoads.ChaseRequestPayLoads.payLoad_With_Single_Matching_RG(intendedUse,
						accountID, subAccountID, cotivitClaimNumber))

				.when().post(resource)

				.then().log().body(true).assertThat().statusCode(202).extract().response().jsonPath();

	}

	@Test(invocationCount = 10)
	public static void ChaseRequest_With_Single_Matching_RG_08() throws InterruptedException {
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
				.header("Content-Type", "application/json")
				.body(com.cotiviti.hdap.APIPayLoads.ChaseRequestPayLoads.payLoad_With_Single_Matching_RG(intendedUse,
						accountID, subAccountID, cotivitClaimNumber))

				.when().post(resource)

				.then().log().body(true).assertThat().statusCode(202).extract().response().jsonPath();

	}

	@Test(invocationCount = 10)
	public static void ChaseRequest_With_Single_Matching_RG_09() throws InterruptedException {
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
				.header("Content-Type", "application/json")
				.body(com.cotiviti.hdap.APIPayLoads.ChaseRequestPayLoads.payLoad_With_Single_Matching_RG(intendedUse,
						accountID, subAccountID, cotivitClaimNumber))

				.when().post(resource)

				.then().log().body(true).assertThat().statusCode(202).extract().response().jsonPath();

	}

	@Test(invocationCount = 10)
	public static void ChaseRequest_With_Single_Matching_RG_10() throws InterruptedException {
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
				.header("Content-Type", "application/json")
				.body(com.cotiviti.hdap.APIPayLoads.ChaseRequestPayLoads.payLoad_With_Single_Matching_RG(intendedUse,
						accountID, subAccountID, cotivitClaimNumber))

				.when().post(resource)

				.then().log().body(true).assertThat().statusCode(202).extract().response().jsonPath();

	}
}
