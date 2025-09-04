package com.cotiviti.hdap.APIPayLoads;

import java.util.concurrent.ConcurrentHashMap;

import org.testng.Assert;

import com.cotiviti.hdap.APICommonLibrary.apiCommonLib;
import com.cotiviti.hdap.CommonUtils.PropertiesFileReader;

import io.restassured.response.Response;

public class CancelChaseRequestPage extends apiCommonLib {
	public CancelChaseRequestPage() {
		super();
	}

	public String cancelChaseRequestPayload(String intendedUse, String CotivitiClaimNumber) {

		String payLoad = "{\r\n" + "    \"IntendedUse\": \"" + intendedUse + "\",\r\n"
				+ "    \"CotivitiClaimNumber\": \"" + CotivitiClaimNumber + "\",\r\n"
				+ "    \"CancelReason\": \"Client Request\"\r\n" + "}";

//		System.err.println(payLoad);
		return payLoad;
	}

	public Response cancelChaseRequests(String intendedUse, String cotivitiClaimnum, String chaseReq, String authToken) {
		String env = System.getProperty("env");
		Response response = null;
		String jsonResponse = "";
		apiCommonLib obj = new apiCommonLib();
		try {
			String apiUrl = PropertiesFileReader.getAPIProperty(env + ".CancelChaseRequestEndPoint")
					.replace("{{CaseRequestID}}", chaseReq);
			String reqBody = cancelChaseRequestPayload(intendedUse, cotivitiClaimnum);
			response = obj.sendPostRequest(apiUrl, reqBody, authToken);
			jsonResponse = response.getBody().asString();
//			System.err.println(jsonResponse);
		} catch (Exception e) {
			Assert.fail(e.getLocalizedMessage());
		}
		return response;
	}

}
