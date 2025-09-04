package com.cotiviti.hdap.API.Tests;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.cotiviti.hdap.APICommonLibrary.ExcelReader;
import com.cotiviti.hdap.APICommonLibrary.Group;
import com.cotiviti.hdap.APICommonLibrary.apiCommonLib;
import com.cotiviti.hdap.APIPayLoads.CreateChaseRequestPage;
import com.cotiviti.hdap.APIPayLoads.Search;
import com.cotiviti.hdap.Pages.RequestGroup;

public class QASanity extends apiCommonLib {

	@Test(enabled = true, groups = { Group.QASANITY, Group.REQUESTGROUP }, priority = 1)
	public void rgConfigSettings(ITestContext context) {
		try {
			executionMap.put("MethodName", "Create RG Config Settings");

			String tcName = new Object() {}.getClass().getEnclosingMethod().getName();
			String sheetName = tcName;

			RequestGroup rgObj = new RequestGroup();
			ExcelReader reader = new ExcelReader();

			executionMap = reader.getTestCaseData(executionMap, sheetName, tcName, context);
			generateBearerToken(executionMap);
			executionMap = rgObj.createRGConfig(executionMap);
		} finally {
			concludeTest(executionMap);
		}
	}

	@Test(enabled = true, groups = { Group.QASANITY, Group.REQUESTGROUP }, priority = 2)
	public void rgCreateContact(ITestContext context) {
		try {
			executionMap.put("MethodName", "Create RG Contacts");

			String tcName = new Object() {}.getClass().getEnclosingMethod().getName();
			String sheetName = tcName;

			RequestGroup rgObj = new RequestGroup();
			ExcelReader reader = new ExcelReader();

			executionMap = reader.getTestCaseData(executionMap, sheetName, tcName, context);
			generateBearerToken(executionMap);
			executionMap = rgObj.createRGContact(executionMap);
		} finally {
			concludeTest(executionMap);
		}
	}

	@Test(enabled = true, groups = { Group.QASANITY, Group.REQUESTGROUP }, priority = 3)
	public void rgCreateProcessForRules(ITestContext context) {
		try {
			executionMap.put("MethodName", "Create RG PFR");

			String tcName = new Object() {}.getClass().getEnclosingMethod().getName();
			String sheetName = tcName;

			RequestGroup rgObj = new RequestGroup();
			ExcelReader reader = new ExcelReader();

			executionMap = reader.getTestCaseData(executionMap, sheetName, tcName, context);
			generateBearerToken(executionMap);
			executionMap = rgObj.createRGPFR(executionMap);
		} finally {
			concludeTest(executionMap);
		}
	}

	@Test(enabled = true, groups = { Group.QASANITY, Group.REQUESTGROUP }, priority = 4)
	public void rgCreateFacility(ITestContext context) {
		try {
			executionMap.put("MethodName", "Create Facility and Link to RG");

			String tcName = new Object() {}.getClass().getEnclosingMethod().getName();
			String sheetName = tcName;

			RequestGroup rgObj = new RequestGroup();
			ExcelReader reader = new ExcelReader();

			executionMap = reader.getTestCaseData(executionMap, sheetName, tcName, context);
			generateBearerToken(executionMap);
			executionMap = rgObj.QAcreatefacility(executionMap);
		} finally {
			concludeTest(executionMap);
		}
	}

	@Test(enabled = true, groups = { Group.QASANITY, Group.REQUESTGROUP }, priority = 5)
	public void createChaseRequestFacility(ITestContext context) {
		try {
			String tcName = new Object() {}.getClass().getEnclosingMethod().getName();
			String sheetName = "createChaseRequest";

			ExcelReader reader = new ExcelReader();
			CreateChaseRequestPage crPage = new CreateChaseRequestPage();

			executionMap = reader.getTestCaseData(executionMap, sheetName, tcName, context);
			executionMap.put("MethodName", "Chase Request - Prospective - " + executionMap.get("PROVIDERTYPE"));
			generateBearerToken(executionMap);
			crPage.chaseRequestCreation(executionMap);
		} finally {
			concludeTest(executionMap);
		}
	}

	@Test(enabled = true, groups = { Group.QASANITY, Group.REQUESTGROUP }, priority = 6)
	public void createChaseRequestFacility1(ITestContext context) {
		try {
			String tcName = new Object() {}.getClass().getEnclosingMethod().getName();
			String sheetName = "createChaseRequest";

			ExcelReader reader = new ExcelReader();
			CreateChaseRequestPage crPage = new CreateChaseRequestPage();

			executionMap = reader.getTestCaseData(executionMap, sheetName, tcName, context);
			executionMap.put("MethodName", "Chase Request - Retrospective - " + executionMap.get("PROVIDERTYPE"));
			generateBearerToken(executionMap);
			crPage.chaseRequestCreation(executionMap);
		} finally {
			concludeTest(executionMap);
		}
	}
	

	@Test(enabled = true, groups = { Group.QASANITY, Group.REQUESTGROUP }, priority = 7)
	public void searchRequestGroupId(ITestContext context) {
		try {
			String tcName = new Object() {}.getClass().getEnclosingMethod().getName();

			executionMap.put("MethodName", "Search - Request Group - " + executionMap.get("RGID"));

			generateBearerToken(executionMap);
			new Search().QAsearchRG(executionMap);
		} finally {
			concludeTest(executionMap);
		}
	}

	@Test(enabled = true, groups = { Group.QASANITY, Group.REQUESTGROUP }, priority = 8)
	public void searchFacility(ITestContext context) {
		try {
			String tcName = new Object() {}.getClass().getEnclosingMethod().getName();

			executionMap.put("MethodName", "Search Facility - " + executionMap.get("FacilityID"));

			generateBearerToken(executionMap);
			new Search().QAsearchFacility(executionMap);
		} finally {
			concludeTest(executionMap);
		}
	}

	@Test(enabled = true, groups = { Group.QASANITY, Group.REQUESTGROUP }, priority = 9)
	public void searchChaseRequest(ITestContext context) {
		try {
			String tcName = new Object() {}.getClass().getEnclosingMethod().getName();

			executionMap.put("MethodName", "Search Chase Request - " + executionMap.get("ChaseRequest"));

			generateBearerToken(executionMap);
			new Search().QAsearchChase(executionMap);
		} finally {
			concludeTest(executionMap);
		}
	}

	@Test(enabled = true, groups = { Group.QASANITY, Group.REQUESTGROUP }, priority = 10)
	public void searchPFRofRG(ITestContext context) {
		try {
			String tcName = new Object() {}.getClass().getEnclosingMethod().getName();

			executionMap.put("MethodName", "Search PFR of RG - " + executionMap.get("RGID"));

			generateBearerToken(executionMap);
			new Search().QAsearchProcessesForRulesOfRG(executionMap);
		} finally {
			concludeTest(executionMap);
		}
	}
}
