package com.cotiviti.hdap.API.Tests;

import java.util.concurrent.ConcurrentHashMap;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.cotiviti.hdap.APICommonLibrary.ExcelReader;
import com.cotiviti.hdap.APICommonLibrary.Group;
import com.cotiviti.hdap.APICommonLibrary.apiCommonLib;
import com.cotiviti.hdap.APIPayLoads.CreateChaseRequestPage;
import com.cotiviti.hdap.Pages.RequestGroup;
//Zakir First Commit
/**
 * 
 * @author dinesh.neeli
 *
 */
public class CreateData extends apiCommonLib {

	public ConcurrentHashMap<String, String> executionMap = new ConcurrentHashMap<String, String>();

	@Test(enabled = true, groups = { Group.DATAGENERATE })
	public void createChaseRequest(ITestContext context) {
		try {
			ConcurrentHashMap<String, String> dataMap = new ConcurrentHashMap<String, String>();

//			String txt = System.getProperty("ABC");
//			System.out.println("Bamboo value  :"+txt);
//			
//			String txt1 = System.getenv("bamboo_ABC");
//			System.out.println("Bamboo value 2 from java :"+txt1);
//			
			
//			System.out.println("env	 -"+System.getProperty("env"));
//			System.out.println("subAccountID -"+System.getProperty("subAccountID"));
//			System.out.println("NoOfChaseRequests -"+System.getProperty("NoOfChaseRequests"));
//			System.out.println("clientIDs -"+System.getProperty("clientIDs"));
			
			String tcName = new Object() {
			}.getClass().getEnclosingMethod().getName();

			String sheetName = tcName;

			ExcelReader reader = new ExcelReader();
			RequestGroup rgObj = new RequestGroup();
			CreateChaseRequestPage crPage = new CreateChaseRequestPage();

			executionMap = reader.getTestCaseData(dataMap, sheetName, tcName, context);
			generateBearerToken(executionMap);
			if (isRequestGroup(executionMap)) {
				executionMap = rgObj.createRGConfig(executionMap);
				executionMap = rgObj.createRGContact(executionMap);
				executionMap = rgObj.createRGPFR(executionMap);
			}
			if (isFacility(executionMap)) {
				executionMap = rgObj.createfacility(executionMap);
			}
		executionMap = crPage.chaseRequestCreation(executionMap);
			
			
			
		} finally {
			concludeTest(executionMap);
		}
	}

	private boolean isRequestGroup(ConcurrentHashMap<String, String> dataMap) {
		boolean createRGFlag = false;
		try {
			if (dataMap.get("REQUESTGROUP_ID").equalsIgnoreCase("new")) {
				createRGFlag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createRGFlag;
	}

	private boolean isFacility(ConcurrentHashMap<String, String> dataMap) {
		boolean createRGFlag = false;
		try {
			if (dataMap.get("FACILITY_ID") == null || dataMap.get("FACILITY_ID").isEmpty()
					|| dataMap.get("FACILITY_ID") == "" || dataMap.get("FACILITY_ID").equalsIgnoreCase("new")) {
				createRGFlag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createRGFlag;
	}

}

