package com.cotiviti.hdap.StepDefinitions;

import com.cotiviti.hdap.APICommonLibrary.ChaseRequestAPI;
import com.cotiviti.hdap.APICommonLibrary.apiCommonLib;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateChaseRequest_StepDefinition {
	apiCommonLib api = new apiCommonLib();

	@When("I create chase request with IntendedUse as (.+), AuditType as (.+), ChartType as (.+), DOS as (.+), DOE as (.+), Prospectiveflag as (.+)$")
	public void I_create_chase_request(String IntendedUse, String AuditType, String ChartType, String DOS, String DOE,
			boolean Prospectiveflag) {
		try {
			new ChaseRequestAPI().createChaseRequest(IntendedUse, AuditType, ChartType, DOS, DOS, Prospectiveflag);
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}
	}

	@When("I validate schema of Chase Request Response using file as (.+)$")
	public void I_validate_schema_of_Chase_Request_Response(String schemaFile) {
		try {
			new ChaseRequestAPI().schemavalidationCR(schemaFile);
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}
	}

	@Then("I verify status code for chase request as (.+)$")
	public void I_verify_status_code_for_chase_request(String statusCode) {
		api.statuscodeValidator(statusCode);
		System.out.println("Response code validated successfully");
	}

	@Then("I verify response message for chase request as (.+)$")
	public void I_verify_response_message_for_chase_request(String responseMessage) {
		api.validateResponse(responseMessage);
	}

	@Then("I capture the chase requestId created")
	public void i_Capture_The_Chase_Request_Id_Created() {
		try {
			new ChaseRequestAPI().getChasereqID();
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}
	}

//	@When("I create chase request with Cotiviti Claim Number as (null|[^,]+), IntendedUse as (.+), AuditType as (.+), ChartType as (.+), DOS as (.+), DOE as (.+), Prospectiveflag as (.+)$")
//	public void createChaseRequestWithNullClaim(String IntendedUse, String AuditType, String ChartType, String DOS,
//			String DOE, boolean Prospectiveflag) {
//		try {
//			String cotivitiClaimNumber = "null";
//			ChaseRequestAPI chaseRequestAPI = new ChaseRequestAPI();
//			chaseRequestAPI.createChaseRequest(cotivitiClaimNumber, IntendedUse, AuditType, ChartType, DOS, DOE,
//					Prospectiveflag);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	@When("I create chase request with Cotiviti Claim Number as (null|[^,]+), IntendedUse as (.+), AuditType as (.+), ChartType as (.+), DOS as (.+), DOE as (.+), Prospectiveflag as (.+)$")
	public void createChaseRequestWithNullClaim(String cotivitiClaimNumber, String intendedUse, String auditType,
			String chartType, String dos, String doe, boolean prospectiveFlag) {

		try {
			if ("null".equalsIgnoreCase(cotivitiClaimNumber)) {
				cotivitiClaimNumber = null;
			}
			new ChaseRequestAPI().createChaseRequest(cotivitiClaimNumber, intendedUse, auditType, chartType, dos, doe,
					prospectiveFlag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}