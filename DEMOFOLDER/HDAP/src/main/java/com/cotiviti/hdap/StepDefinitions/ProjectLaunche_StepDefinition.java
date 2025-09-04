package com.cotiviti.hdap.StepDefinitions;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.cotiviti.hdap.CommonLibrary.ReadDataFromExcel;
import com.cotiviti.hdap.CommonUtils.DependencyInjection;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

/**
 * ---------------------------------------------- RULES
 * ------------------------------ Write the Xpath here itself as these are
 * common functions and canBe and willBe used by other features Common functions
 * that can be used across the Create RG Function that will take input as
 * Request ID and Open that RG Function that will take input as task name and
 * create that task Give an RG I should be able to filter with RG ID on Work
 * List Give an RG I should be able to filter with RG ID on Work List Give an RG
 * I should be able to filter with RG ID on Work Basket Give an RG I should be
 * able to filter with RG ID on Work Basket
 * 
 */
public class ProjectLaunche_StepDefinition {
	Scenario scn;
	public WebDriver driver = null;
	public DependencyInjection obj;

	public ProjectLaunche_StepDefinition(DependencyInjection obj) {
		this.obj = obj;
	}

	static Logger myLogger = LogManager.getLogger(ProjectLaunche_StepDefinition.class.getName());

	// Add this piece of code to read data from Excel Sheet. Change the Sheet Name
	HashMap<String, String> testData;

	@Then("^Read ExcelData from projectLaunchSheet  for  (.+)$")
	public HashMap<String, String> ReadExcelSheetDataFromSheet(String TestCaseID) {
		testData = ReadDataFromExcel.getExcelData("preLaunch", TestCaseID);
		return testData;
	}

	@Given("User Clicks on the Project Launches Tab")
	public void UserClicksontheProjectLaunchesTab() {
		obj.keyWords().clickElement(obj.getProjectLaunches_Pg().getQA_Retrieval_btn());
		obj.keyWords().clickElement(obj.getProjectLaunches_Pg().getSwitchRole_btn());
		obj.keyWords().clickElement(obj.getProjectLaunches_Pg().getHDAP_Admin_btn());
	}

}
