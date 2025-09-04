package com.cotiviti.hdap.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.java.Scenario;

public class CommonFunctionsPage {
	// Template Start
	public WebDriver driver;

	public CommonFunctionsPage(WebDriver driver, Scenario s) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*---SYNTAX TO ADD THE WEBELEMENT------------
	 * 
		@FindBy(xpath="//tagName")
	private  WebElement ElementName;		
	public WebElement getelementName() {
		return ElementName;
	}
	
	 */

	@FindBy(xpath = "(//div[@aria-label=\"My Worklist\"]/following::a[@aria-label=\"RG ID Actions\"])[1]")
	private WebElement RGID_MyWorkList_KebabMenu;

	@FindBy(xpath = "//div[@aria-label=\"My Workbasket\"]/following::a[@aria-label=\"RG ID Actions\"]")
	private WebElement RGID_MyWorkBasket_KebabMenu;

	@FindBy(xpath = "//table[@pl_prop='pySearchResults.pxResults']//tr[2]//td[1]//a	")
	private WebElement clickFirstRG;

	public WebElement getclickFirstRG() {
		return clickFirstRG;
	}

	@FindBy(xpath = "//iframe[@id='PegaGadget0Ifr']")
	private WebElement firstFrame;

	public WebElement getFramefirstFrame() {
		return firstFrame;
	}

	public WebElement dynamic_AddTask(String x) {
		WebElement ele = driver.findElement(By.xpath("(//a[text()='" + x + "'])[1]"));
		return ele;
	}

	@FindBy(xpath = "//button[text()='Upload MR']")
	private WebElement UploadMRButton;

	public WebElement getUploadMRButton() {
		return UploadMRButton;
	}

	@FindBy(xpath = "//span//button[@title='Add tasks']")
	private WebElement btn_addTasks;

	public WebElement getbtn_addTasks() {
		return btn_addTasks;
	}

	@FindBy(xpath = "//li[@title='Search']//span[text()='Search']")
	private WebElement searchButtonTab;

	public WebElement getSearchButtonTab() {
		return searchButtonTab;
	}

	@FindBy(xpath = "//input[@name='$PAdvancedSearchCriteriaPage$pRequestGroupID']")
	private WebElement RequestGroupIDSearchTextBox;

	public WebElement getRequestGroupIDSearchTextBox() {
		return RequestGroupIDSearchTextBox;
	}

	@FindBy(xpath = "//button[@name='RequestGroupSearch_pyDisplayHarness_10']")
	private WebElement SearchButtonInSearchSection;

	public WebElement getSearchButtonInSearchSection() {
		return SearchButtonInSearchSection;
	}

	@FindBy(xpath = "//tr[contains(@id,'$PpySearchResults$ppxResults$l')]//td[1]//a")
	private WebElement FirstSearchResult;

	public WebElement getFirstSearchResult() {
		return FirstSearchResult;
	}

	@FindBy(xpath = "//iframe[@name='PegaGadget1Ifr']")
	private WebElement SecondFrame;

	public WebElement getSecondFrame() {
		return SecondFrame;
	}

	@FindBy(xpath = "(//*[text()='Request Group Settings'])[2]")
	private WebElement requestGroupSettingsTab;

	@FindBy(xpath = "//iframe[@id='PegaGadget0Ifr']")
	private WebElement iframeHomePage;

	@FindBy(xpath = "//tbody[@id='gridTableBody']//tr[@data-test-id='-R1']//a")
	private WebElement firstRGfromWB;

	@FindBy(xpath = "//iframe[@id='PegaGadget0Ifr']")
	private WebElement RGTabFrame;

	@FindBy(xpath = "//div[@class='panelOverFlowClass']//span//button[@title='Add task']")
	private WebElement addTaskBtn;

	@FindBy(xpath = "//input[@placeholder='Search for action']")
	private WebElement searchTaskTxtBox;

	@FindBy(xpath = "//div[@class='content-item content-field item-1 flex flex-row dataValueWrite']//a[@title='Select  Configure Request Group  ']")
	private WebElement configureRequestGroupBtn;

	@FindBy(xpath = "(//button[@title='Close'])[2]")
	private WebElement closeButton;

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getRequestGroupSettingsTab() {
		return requestGroupSettingsTab;
	}

	public WebElement getFirstRGfromWB() {
		return firstRGfromWB;
	}

	public WebElement getRGTabFrame() {
		return RGTabFrame;
	}

	public WebElement getAddTaskBtn() {
		return addTaskBtn;
	}

	public WebElement getSearchTaskTxtBox() {
		return searchTaskTxtBox;
	}

	public WebElement getConfigureRequestGroupBtn() {
		return configureRequestGroupBtn;
	}

	public WebElement getIframeHomePage() {
		return iframeHomePage;
	}

	public WebElement getRGID_MyWorkList_KebabMenu() {
		return RGID_MyWorkList_KebabMenu;
	}

	public WebElement getRGID_MyWorkBasket_KebabMenu() {
		return RGID_MyWorkBasket_KebabMenu;
	}

	public WebElement getCloseButton() {
		return closeButton;
	}

}
