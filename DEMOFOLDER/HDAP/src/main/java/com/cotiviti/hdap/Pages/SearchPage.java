package com.cotiviti.hdap.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.cucumber.java.Scenario;

public class SearchPage {
	// Template Start
	public WebDriver driver;

	public SearchPage(WebDriver driver, Scenario s) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*---SYNTAX TO ADD THE WEBELEMENT------------
		@FindBy(xpath="//tagName")
	private  WebElement ElementName;		
	public WebElement getelementName() {
		return ElementName;
	}
	 */

	// ---Added Today

	// providers

	@FindBy(xpath = "//h3[text()='Request Group']")
	private WebElement RequestGroup_btn;

	@FindBy(xpath = "//h3[text()='Chase Request' and @role='presentation']")
	private WebElement ChaseRequest_btn;

	@FindBy(xpath = "//h3[text()='Provider' and @role='presentation']")
	private WebElement Provider_btn;

	// Request Group

	@FindBy(xpath = "//input[@id='dc89b4dd']")
	private WebElement RG_RequestGroupID_txtbox;

	@FindBy(xpath = "//input[@id='f7993e3e']")
	private WebElement RG_RequestGroupName_txtbox;

	@FindBy(xpath = "//div[@bsimplelayout='true']//button[@name='RequestGroupSearch_pyDisplayHarness_9']")
	private WebElement RG_Clear_btn;

	@FindBy(xpath = "//div[@bsimplelayout='true']//button[@name='RequestGroupSearch_pyDisplayHarness_10']")
	private WebElement RG__Search_btn;

	// Chase Request
	@FindBy(xpath = "//input[@id='1f4ac622']")
	private WebElement CR__RequestID_txtbox;

	@FindBy(xpath = "//input[@id='35290546']")
	private WebElement CR__MemberID_txtbox;

	@FindBy(xpath = "//input[@id='13f1429f']")
	private WebElement CR__AccountID_txtbox;

	@FindBy(xpath = "//input[@id='4f063712']")
	private WebElement CR__ClientID_txtbox;

	@FindBy(xpath = "//input[@id='b3880855']")
	private WebElement CR__ProjectName_txtbox;

	@FindBy(xpath = "//input[@id='86ef0a8d']")
	private WebElement CR_ClientNumber_txtbox;

	@FindBy(xpath = "//input[@id='d71b42c7']")
	private WebElement CR_PatientHICN_txtbox;

	@FindBy(xpath = "//input[@id='1c10abb7']")
	private WebElement CR_PatientMBI_txtbox;

	@FindBy(xpath = "//input[@id='37e79c9c']")
	private WebElement CR_MedicalRecordNumber_txtbox;

	@FindBy(xpath = "//input[@id='6395fa2f']")
	private WebElement CR_PatientName_txtbox;

	@FindBy(xpath = "//input[@id='b70ebd95']")
	private WebElement CR_PatientDOB_txtbox;

	@FindBy(xpath = "//input[@id='7402d43b']")
	private WebElement CR_PractitionerName_txtbox;

	@FindBy(xpath = "//input[@id='017de87a']")
	private WebElement CR_PractitionerID_txtbox;

	@FindBy(xpath = "//input[@id='67b2b220']")
	private WebElement CR_ClainEntityID_txtbox;

	@FindBy(xpath = "//input[@id='8e174215']")
	private WebElement CR_RetrievalExpirationDatefromChaseRequest_txtbox;

	@FindBy(xpath = "//button[@name='ChaseRequestSearch_pyDisplayHarness_22']")
	private WebElement CR_Clear_btn;

	@FindBy(xpath = "//button[@name='ChaseRequestSearch_pyDisplayHarness_23']")
	private WebElement CR_Search_btn;

	// Provider
	@FindBy(xpath = "//h3[text()='Practitioner']")
	private WebElement P_Practitioner_btn;

	@FindBy(xpath = "//h3[text()='Facility']")
	private WebElement P_Facility_btn;

	@FindBy(xpath = "//input[@id='722fec97']")
	private WebElement P_PractionerID_txtbox;

	@FindBy(xpath = "//input[@id='0e11c4ff']")
	private WebElement P_PractitionerFirstName_txtbox;

	@FindBy(xpath = "//input[@id='109c3e77']")
	private WebElement P_PractionerLastName_txtbox;

	@FindBy(xpath = "//input[@id='109c3e77']")
	private WebElement P_PractitionerAddressLine1;

	@FindBy(xpath = "//input[@id='f8884e5b']")
	private WebElement P_PractitionerAddressLine2;

	@FindBy(xpath = "//input[@id='ffc8040b']")
	private WebElement P_PractitionerCity_txtbox;

	@FindBy(xpath = "//input[@id='15276cc0']")
	private WebElement P_PractitionerState_txtbox;

	@FindBy(xpath = "//input[@id='c10c9c28']")
	private WebElement P_PractitionerZipCode_txtbox;

	@FindBy(xpath = "//input[@id='52e728be']")
	private WebElement P_Practitioner_TIN_txtbox;

	@FindBy(xpath = "//input[@id='463232a3']")
	private WebElement P_Practitioner_NPI_txtbox;

	@FindBy(xpath = "//input[@id='7ad79990']")
	private WebElement P_PractitionerPhoneNumber_txtbox;

	@FindBy(xpath = "//input[@id='dfa26493']")
	private WebElement P_PractitionerFaxNumber_txtbox;

	@FindBy(xpath = "//button[@name='PractitionerSearch_pyDisplayHarness_23']")
	private WebElement P_Practitioner_Clear_Btn;

	@FindBy(xpath = "//button[@name='PractitionerSearch_pyDisplayHarness_24']")
	private WebElement P_Practitioner_Search_Btn;

	@FindBy(xpath = "//input[@id='6bd607c8']")
	private WebElement P_FacilityID_txtbox;

	@FindBy(xpath = "//input[@id='92448d43']")
	private WebElement P_FacilityName_txtbox;

	@FindBy(xpath = "//input[@id='3b84f52b']")
	private WebElement P_FacilityAddressLine1;

	@FindBy(xpath = "//input[@id='a28da491']")
	private WebElement P_FacilityAddressLine2;

	@FindBy(xpath = "//input[@id='e13cf171']")
	private WebElement P_FacilityCity;

}
