package com.cotiviti.hdap.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.java.Scenario;

public class DQATaskPage {
	// Template Start
	public WebDriver driver;

	public DQATaskPage(WebDriver driver, Scenario s) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='menu-item-title' and text()='Work']")
	private WebElement workTab;

	@FindBy(xpath = "//div[@data-layout-id='202312150041260633']")
	private WebElement myWorkBasket;

	@FindBy(xpath = "//iframe[@id='PegaGadget0Ifr']")
	private WebElement iframeHomePage;

	@FindBy(xpath = "//*[text()='My Worklist']")
	private WebElement myWorkList;

	public WebElement getmyWorkList() {
		return myWorkList;
	}

	public WebElement getWorkTab() {
		return workTab;
	}

	public WebElement getMyWorkBasket() {
		return myWorkBasket;
	}

	public WebElement getIframeHomePage() {
		return iframeHomePage;
	}

}
