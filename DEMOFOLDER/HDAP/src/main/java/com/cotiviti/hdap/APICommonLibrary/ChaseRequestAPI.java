package com.cotiviti.hdap.APICommonLibrary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cotiviti.hdap.APIPayLoads.ChaseRequestPOJO;
import com.cotiviti.hdap.CommonUtils.PropertiesFileReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

//@Test
public class ChaseRequestAPI extends ChaseRequestPOJO {

	String json;
	apiCommonLib api = new apiCommonLib();
	apiAuthorization auth = new apiAuthorization();
	Response response;

	public void createChaseRequest(String cotivitiClaimNumber, String intendedUse, String auditType, String chartType,
			String dosStartDate, String dosEndDate, boolean prospectiveClaimFlag) throws JsonProcessingException {
		try {
			String env = System.getProperty("env");
			buildChaseRequest();

			if (intendedUse.equals("MRA") || intendedUse.equals("RCA") || intendedUse.equals("CCV")
					|| intendedUse.equals("CV") || intendedUse.equals("CRA") || intendedUse.equals("RADV")
					|| intendedUse.equals("HEDIS")) {

				header.replace("IntendedUse", intendedUse);

			} else if ("null".equalsIgnoreCase(intendedUse)) {
				header.replace("IntendedUse", null);

			} else {
				header.replace("IntendedUse", intendedUse);
			}

			if (auditType.equals("DRG") || auditType.equals("POS") || auditType.equals("RADM")
					|| auditType.equals("SNF") || auditType.equals("IRF") || auditType.equals("IBR")
					|| auditType.equalsIgnoreCase("SS") || auditType.equals("ER") || auditType.equals("ASC")
					|| auditType.equals("HH") || auditType.equals("OP") || auditType.equals("APC")
					|| auditType.equals("OBV")) {
				header.replace("AuditType", auditType);
			} else if ("null".equalsIgnoreCase(auditType)) {
				header.replace("AuditType", null);

			} else {
				header.replace("AuditType", auditType);
			}

			if (chartType.equals("Medical") || chartType.equals("Billing") || chartType.equals("Pharmacy")) {
				header.replace("ChartType", chartType);
			} else if ("null".equalsIgnoreCase(chartType)) {
				header.replace("ChartType", null);
			} else {
				header.replace("ChartType", chartType);
			}

			header.replace("ProspectiveClaimFlag", prospectiveClaimFlag);
			LocalDate providedstartDate = null;
			if (dosStartDate != null && !dosStartDate.trim().isEmpty()) {
				providedstartDate = LocalDate.parse(dosStartDate);
			}

			// Current date
			LocalDate currentDate = LocalDate.now();
			if (providedstartDate.isBefore(currentDate)) {
				claim.replace("DateOfServiceStart", dosStartDate);
			} else if ("null".equalsIgnoreCase(dosStartDate)) {
				claim.replace("DateOfServiceStart", dosStartDate);

			} else {
				claim.replace("DateOfServiceStart", dosStartDate);
			}

			LocalDate providedEndDate = LocalDate.parse(dosEndDate);
			if (providedEndDate.isAfter(providedstartDate) && providedEndDate.isBefore(currentDate)) {
				claim.replace("DateOfServiceEnd", dosEndDate);
			} else if ("null".equalsIgnoreCase(dosEndDate)) {
				claim.replace("DateOfServiceEnd", dosEndDate);

			} else {
				claim.replace("DateOfServiceEnd", dosEndDate);
			}

			claim.replace("CotivitiClaimNumber", cotivitiClaimNumber);
			
			chasePayload.put("ChaseRequestHeader", header);
			chasePayload.put("ClaimDetails", claim);
			chasePayload.put("ChaseDetails", details);
			chasePayload.put("Members", members);
			chasePayload.put("Providers", providers);

			chaseRequest.put("ChaseRequest", chasePayload);
			ObjectMapper mapper2 = new ObjectMapper();
			chasereqpayload = mapper2.writerWithDefaultPrettyPrinter().writeValueAsString(chaseRequest);
			System.out.println(chasereqpayload);
			response = api.sendPostRequestWithStatusValidation(
					PropertiesFileReader.getAPIProperty("QA.ChaseRequestEndPoint"), chasereqpayload,
					auth.createQAAuth());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void createChaseRequest(String intendedUse, String auditType, String chartType, String dosStartDate,
			String dosEndDate, boolean prospectiveClaimFlag) throws JsonProcessingException {
		try {
			String env = System.getProperty("env");
			buildChaseRequest();

			if (intendedUse.equals("MRA") || intendedUse.equals("RCA") || intendedUse.equals("CCV")
					|| intendedUse.equals("CV") || intendedUse.equals("CRA") || intendedUse.equals("RADV")
					|| intendedUse.equals("HEDIS")) {

				header.replace("IntendedUse", intendedUse);

			} else if ("null".equalsIgnoreCase(intendedUse)) {
				header.replace("IntendedUse", null);

			} else {
				header.replace("IntendedUse", intendedUse);
			}

			if (auditType.equals("DRG") || auditType.equals("POS") || auditType.equals("RADM")
					|| auditType.equals("SNF") || auditType.equals("IRF") || auditType.equals("IBR")
					|| auditType.equalsIgnoreCase("SS") || auditType.equals("ER") || auditType.equals("ASC")
					|| auditType.equals("HH") || auditType.equals("OP") || auditType.equals("APC")
					|| auditType.equals("OBV")) {
				header.replace("AuditType", auditType);
			} else if ("null".equalsIgnoreCase(auditType)) {
				header.replace("AuditType", null);

			} else {
				header.replace("AuditType", auditType);
			}

			if (chartType.equals("Medical") || chartType.equals("Billing") || chartType.equals("Pharmacy")) {
				header.replace("ChartType", chartType);
			} else if ("null".equalsIgnoreCase(chartType)) {
				header.replace("ChartType", null);
			} else {
				header.replace("ChartType", chartType);
			}

			header.replace("ProspectiveClaimFlag", prospectiveClaimFlag);
			LocalDate providedstartDate = null;
			if (dosStartDate != null && !dosStartDate.trim().isEmpty()) {
				providedstartDate = LocalDate.parse(dosStartDate);
			}

			// Current date
			LocalDate currentDate = LocalDate.now();
			if (providedstartDate.isBefore(currentDate)) {
				claim.replace("DateOfServiceStart", dosStartDate);
			} else if ("null".equalsIgnoreCase(dosStartDate)) {
				claim.replace("DateOfServiceStart", dosStartDate);

			} else {
				claim.replace("DateOfServiceStart", dosStartDate);
			}

			LocalDate providedEndDate = LocalDate.parse(dosEndDate);
			if (providedEndDate.isAfter(providedstartDate) && providedEndDate.isBefore(currentDate)) {
				claim.replace("DateOfServiceEnd", dosEndDate);
			} else if ("null".equalsIgnoreCase(dosEndDate)) {
				claim.replace("DateOfServiceEnd", dosEndDate);

			} else {
				claim.replace("DateOfServiceEnd", dosEndDate);
			}

			chasePayload.put("ChaseRequestHeader", header);
			chasePayload.put("ClaimDetails", claim);
			chasePayload.put("ChaseDetails", details);
			chasePayload.put("Members", members);
			chasePayload.put("Providers", providers);

			chaseRequest.put("ChaseRequest", chasePayload);
			ObjectMapper mapper2 = new ObjectMapper();
			chasereqpayload = mapper2.writerWithDefaultPrettyPrinter().writeValueAsString(chaseRequest);
			System.out.println(chasereqpayload);
			response = api.sendPostRequestWithStatusValidation(
					PropertiesFileReader.getAPIProperty("QA.ChaseRequestEndPoint"), chasereqpayload,
					auth.createQAAuth());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void schemavalidationCR(String schemaFile) {
		// createChaseRequest();
		try {
			api.sendPostRequestWithSchemavalidation(schemaFile);
			System.out.println("Schema validation is successful");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getChasereqID() throws JsonProcessingException {
		try {
			String cotivitclaim = cotiviticlaimnumber;

			String chaseRequestId;
			Map<String, Object> payload = new HashMap<>();
			List<String> claimNumbers = new ArrayList<>();
			claimNumbers.add(cotivitclaim);
			// Add key-value pairs to the HashMap
			payload.put("CotivitiClaimNumbers", claimNumbers);
			System.out.print(payload);

			ObjectMapper objectMapper = new ObjectMapper();

			String jsonString = objectMapper.writeValueAsString(payload);

			String strResponse = api.sendPostRequestWithStatusValidation(
					PropertiesFileReader.getAPIProperty("QA.ChaseRequestSearchEndPoint"), jsonString,
					auth.createQAAuth()).asString();
			System.out.println(strResponse);
			JsonPath jsonBody = new JsonPath(strResponse);

			chaseRequestId = jsonBody.getString("Results[0].Result.ChaseRequest.RequestID");
			System.out.println("extracted ChaseRequest ID:" + chaseRequestId + " for the created ChaseRequest ");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
