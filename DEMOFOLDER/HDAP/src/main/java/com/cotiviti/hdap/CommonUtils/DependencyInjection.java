package com.cotiviti.hdap.CommonUtils;

import org.openqa.selenium.WebDriver;

import com.cotiviti.hdap.Pages.CommonFunctionsPage;
import com.cotiviti.hdap.Pages.CreateRGPage;
import com.cotiviti.hdap.Pages.DQATaskPage;
import com.cotiviti.hdap.Pages.ProjectLaunchePage;

import io.cucumber.java.Scenario;

public class DependencyInjection {

	public WebDriver driver;
	CommonFunctionsPage pagecommon;
	CreateRGPage pageCreateRG;
	DQATaskPage pageDQATask;
	ProjectLaunchePage pageProjectLaunches_Pg;
	KeyWords keys;

	public WebDriver getDriver() {
		return driver;
	}

	public CommonFunctionsPage getPagecommon() {
		return pagecommon;
	}

	public CreateRGPage getPageCreateRG() {
		return pageCreateRG;
	}

	public DQATaskPage getPageDQATask() {
		return pageDQATask;
	}

	public ProjectLaunchePage getProjectLaunches_Pg() {
		return pageProjectLaunches_Pg;
	}

	public KeyWords keyWords() {
		return keys;
	}

	public void initializePageObject(WebDriver driver, Scenario s) {

		pagecommon = new CommonFunctionsPage(driver, s);
		pageCreateRG = new CreateRGPage(driver, s);
		pageDQATask = new DQATaskPage(driver, s);
		pageProjectLaunches_Pg = new ProjectLaunchePage(driver, s);
		keys = new KeyWords(driver);

	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

}
