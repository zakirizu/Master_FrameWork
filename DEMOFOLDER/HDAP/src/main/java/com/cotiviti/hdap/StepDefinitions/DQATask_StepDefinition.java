package com.cotiviti.hdap.StepDefinitions;

import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cotiviti.hdap.CommonLibrary.ReadDataFromExcel;
import com.cotiviti.hdap.CommonUtils.DependencyInjection;

import io.cucumber.java.en.Then;

public class DQATask_StepDefinition {
	DependencyInjection obj;

	public DQATask_StepDefinition(DependencyInjection obj) {
		this.obj = obj;
	}

	static Logger myLogger = LogManager.getLogger(DQATask_StepDefinition.class.getName());

	// Add this piece of code to read data from Excel Sheet. Change the Sheet Name
	HashMap<String, String> testData;

	@Then("^Read ExcelData from DQATaskSheet  for  (.+)$")
	public HashMap<String, String> ReadExcelSheetDataFromSheet(String TestCaseID) {
		testData = ReadDataFromExcel.getExcelData("DQA", TestCaseID);
		return testData;
	}

}
