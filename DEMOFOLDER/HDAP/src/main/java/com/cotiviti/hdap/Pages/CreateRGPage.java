package com.cotiviti.hdap.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.cucumber.java.Scenario;

public class CreateRGPage {
	// Template Start
	public WebDriver driver;

	public CreateRGPage(WebDriver driver, Scenario s) {
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

	@FindBy(xpath = "//button[@type='button']")
	private WebElement Create_RG_btn;

	@FindBy(xpath = "//*[text()='Next >>']")
	private WebElement Next_btn;

	// Contacts
	@FindBy(xpath = "//button[text()='Add Contact']")
	private WebElement AddContact_btn;

	@FindBy(xpath = "//select[@id='80e67c33']")
	private WebElement contactType_ddl;

	@FindBy(xpath = "//select[@id='c1a346d0']")
	private WebElement Department_ddl;

	@FindBy(xpath = "//input[@id='45da4e1f']")
	private WebElement Name_txtbox;

	@FindBy(xpath = "//input[@id='6da723dd']")
	private WebElement role_txtbox;

	@FindBy(xpath = "//input[@id='5aea26b5']")
	private WebElement Email_txtbox;

	@FindBy(xpath = "//input[@id='d0a169f1']")
	private WebElement Phone_txtbox;

	@FindBy(xpath = "//input[@id='edf46543']")
	private WebElement Ext_txtbox;

	@FindBy(xpath = "//input[@id='95a33135']")
	private WebElement Fax_txtbox;

	@FindBy(xpath = "//select[@id='89268613']")
	private WebElement Preferred_method_txtbox;

	@FindBy(xpath = "//input[@id='1eb724cf']")
	private WebElement DayTimePreference_checkbox;

	@FindBy(xpath = "//select[@id='364c75d1']")
	private WebElement Timezone_ddl;

	@FindBy(xpath = "//button[@title='Complete this assignment']")
	private WebElement Contacts_Nextbtn;

	@FindBy(xpath = "//div[text()='<< Back']")
	private WebElement Contacts_Backbtn;

//Request Group Settings-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@FindBy(xpath = "//input[@id='e12165d4']")
	private WebElement Request_Group_Name_Txtbox;

	@FindBy(xpath = "//select[@id='af741cb3']")
	private WebElement TimezoneRequestGroupSetting_ddl;

	@FindBy(xpath = "//textarea[@id='91c190b1']")
	private WebElement Instruction_Txtbox;

	@FindBy(xpath = "//input[@id='a1ed4d32']")
	private WebElement Next_Review_Txtbox;

	@FindBy(xpath = "//select[@id='a4b25490']")
	private WebElement RetrivalMethod_ddl;

	@FindBy(xpath = "//input[@id='afe23f1f']")
	private WebElement EMRsystem_Txtbox;

	@FindBy(xpath = "//input[@id='afe23f1f']")
	private WebElement CopyService_Txtbox;

	@FindBy(xpath = "//input[@id='63add1cc']")
	private WebElement Owner_Txtbox;

	@FindBy(xpath = "//input[@id='eeea0ea9']")
	private WebElement Only_Owner_Checkbox;

	@FindBy(xpath = "//input[@id='71fade16']")
	private WebElement ContactWillVerify_Checkbox;

	@FindBy(xpath = "//input[@id='2d6f9879']")
	private WebElement Contact_Will_Confirm_Checkbox;

	@FindBy(xpath = "//input[@id='0b196e37']")
	private WebElement Excluded_Checkbox;

	@FindBy(xpath = "//select[@id='bf9366c2']")
	private WebElement ReasonforExclusion_Txtbox;

	@FindBy(xpath = "//input[@id='e8312b1e']")
	private WebElement Website_Txtbox;

	@FindBy(xpath = "//input[@id='347175fd']")
	private WebElement mainAddressAttn_Txtbox;

	@FindBy(xpath = "//input[@id='ae2e142d']")
	private WebElement mainAddressAddress_Txtbox;

	@FindBy(xpath = "//input[@id='37274597']")
	private WebElement mainAddressAddress2_Txtbox;

	@FindBy(xpath = "//input[@id='2e457787']")
	private WebElement mainAddressCity_Txtbox;

	@FindBy(xpath = "//select[@id='f1f82eb8']")
	private WebElement mainAddressState_ddl;

	@FindBy(xpath = "//input[@id='1668d1dc']")
	private WebElement mainAddressZipcode_Txtbox;

	@FindBy(xpath = "(//select[@ID='c7dd8e21'])[2]")
	private WebElement LetterPreference_ddl;

	@FindBy(xpath = "(//input[@id='9da9e070'])[2]")
	private WebElement Use_facility_checkbox;

	@FindBy(xpath = "(//input[@id='7736f9b2'])[2]")
	private WebElement Group_letter_checkbox;

	@FindBy(xpath = "//input[@id='70c38c10']")
	private WebElement Lettersperbatch_sendkeys;

	@FindBy(xpath = "//input[@id='16ce340d']")
	private WebElement Claim_Entity_checkbox;

	@FindBy(xpath = "//input[@id='a604d21b']")
	private WebElement Facility_address_checkbox;;

	@FindBy(xpath = "//input[@id='582392d2']")
	private WebElement Patient_Name_checkbox;

	@FindBy(xpath = "//input[@id='e9f502dd']")
	private WebElement Patient_DOB_checkbox;

	@FindBy(xpath = "//input[@id='75054e8b']")
	private WebElement Patient_address_checkbox;

	@FindBy(xpath = "//input[@id='1769dbf7']")
	private WebElement Patient_age_checkbox;

	@FindBy(xpath = "//input[@id='c5f781fc']")
	private WebElement Patient_phone_checkbox;;

	@FindBy(xpath = "//input[@id='662a6a55']")
	private WebElement Patient_Email_checkbox;;

	@FindBy(xpath = "//input[@id='7a6abdf9']")
	private WebElement Provider_name_checkbox;;

	@FindBy(xpath = "//input[@id='9d59949b']")
	private WebElement Provider_verification_checkbox;;

	@FindBy(xpath = "//input[@id='f81891d7']")
	private WebElement Project_checkbox;

	@FindBy(xpath = "//input[@id='fb55bb9f']")
	private WebElement Date_of_service_checkbox;;

	@FindBy(xpath = "//input[@id='47e41542']")
	private WebElement Additional_checkbox;

	@FindBy(xpath = "//textarea[@id='6974773c']")
	private WebElement AdditionalNote_TextBox;
	
	
	
	
	@FindBy(xpath = "//select[@id='42316691']")
	private WebElement Payment_Preference_ddl;

	@FindBy(xpath = "//input[@id='ms1e9c8969']")
	private WebElement Cardtype_txtbox;

	@FindBy(xpath = "//select[@id='48dddff2']")
	private WebElement PayVia_ddl;

	@FindBy(xpath = "//select[@id='8ef5e811']")
	private WebElement ReturnPreference_ddl;

	@FindBy(xpath = "//select[@id='3012c854']")
	private WebElement send_preference_ddl;

	@FindBy(xpath = "//input[@id='f295d67c']")
	private WebElement SPUsemainaddress_checkbox;

	@FindBy(xpath = "//input[@id='3ad2e389']")
	private WebElement SPAttnTo_Textbox;

	@FindBy(xpath = "//input[@id='359b12ab']")
	private WebElement SPAddress_Textbox;

	@FindBy(xpath = "//input[@id='ac924311']")
	private WebElement SPAddressLines2_Textbox;

	@FindBy(xpath = "//input[@id='37caddcf']")
	private WebElement SPCity_Textbox;

	@FindBy(xpath = "//Select[@id='89e668b0']")
	private WebElement SPState_ddl;

	@FindBy(xpath = "//input[@id='2d54ce90']")
	private WebElement SPZipCode_textbox;

	@FindBy(xpath = "//div[text()='<< Back']")
	private WebElement RG_Back_Btn;

	// override

	public WebElement getRG_Back_Btn() {
		return RG_Back_Btn;
	}

	@FindBy(xpath = "//button[text()='Add Override']")
	private WebElement ADDOverride_BTN;

	public WebElement getADDOverride_BTN() {
		return ADDOverride_BTN;
	}

	public WebElement getSPUsedPrimarycontactEmail_Checkbox() {
		return SPUsedPrimarycontactEmail_Checkbox;
	}

	public WebElement getSPUsedPrimarycontactMail_Checkbox() {
		return SPUsedPrimarycontactMail_Checkbox;
	}

	public WebElement getSPUsedPrimarycontactFax_Checkbox() {
		return SPUsedPrimarycontactFax_Checkbox;
	}

	@FindBy(xpath = "//select[@id='804c8b80']")
	private WebElement SPOverride_ddl;

	@FindBy(xpath = "//input[@id='754c82d8']")
	private WebElement SPOverride_UseRequestGroup__Checkbox;

	@FindBy(xpath = "//input[@id='eb259518']")
	private WebElement SPOverride_AttnTo_textbox;

	@FindBy(xpath = "//input[@id='02c9b33b']")
	private WebElement SPOverride_Address_textbox;

	@FindBy(xpath = "//input[@id='9bc0e281']")
	private WebElement SPOverride_AddressLines2_textbox;

	@FindBy(xpath = "//input[@id='4fba27ee']")
	private WebElement SPOverride_City_textbox;

	@FindBy(xpath = "//input[@id='22ae9cbf']")
	private WebElement SPUsedPrimaryContact_textbox;

	@FindBy(xpath = "//input[@id='679227ed']")
	private WebElement SPEmail_textbox;

	@FindBy(xpath = "//input[@id='9984a1b8']")
	private WebElement SPFax_Checkbox;

	@FindBy(xpath = "//input[@id='1da89e30']")
	private WebElement SPFax_textbox;

	@FindBy(xpath = "//input[@id='22ae9cbf']")
	private WebElement SPUsedPrimarycontactEmail_Checkbox;

	@FindBy(xpath = "//input[@id='f295d67c']")
	private WebElement SPUsedPrimarycontactMail_Checkbox;

	@FindBy(xpath = "//input[@id='9984a1b8']")
	private WebElement SPUsedPrimarycontactFax_Checkbox;

	public WebElement getSPFax_Checkbox() {
		return SPFax_Checkbox;
	}

	public WebElement getSPFax_textbox() {
		return SPFax_textbox;
	}

	public WebElement getSPUpload_textbox() {
		return SPUpload_textbox;
	}

	@FindBy(xpath = "//input[@id='78fd16f6']")
	private WebElement SPUpload_textbox;

	public WebElement getPayment_Preference_ddl() {
		return Payment_Preference_ddl;
	}

	public WebElement getSPUsedPrimaryContact_textbox() {
		return SPUsedPrimaryContact_textbox;
	}

	public WebElement getSPEmail_textbox() {
		return SPEmail_textbox;
	}

	public void setSPEmail_textbox(WebElement sPEmail_textbox) {
		SPEmail_textbox = sPEmail_textbox;
	}

	public WebElement getSPUsemainaddress_checkbox() {
		return SPUsemainaddress_checkbox;
	}

	public WebElement getSPAttnTo_Textbox() {
		return SPAttnTo_Textbox;
	}

	public WebElement getSPAddress_Textbox() {
		return SPAddress_Textbox;
	}

	public WebElement getSPAddressLines2_Textbox() {
		return SPAddressLines2_Textbox;
	}

	public WebElement getSPCity_Textbox() {
		return SPCity_Textbox;
	}

	public WebElement getSPState_ddl() {
		return SPState_ddl;
	}

	public WebElement getSPZipCode_textbox() {
		return SPZipCode_textbox;
	}

	public WebElement getSPOverride_ddl() {
		return SPOverride_ddl;
	}

	public WebElement getSPOverride_UseRequestGroup__Checkbox() {
		return SPOverride_UseRequestGroup__Checkbox;
	}

	public WebElement getSPOverride_AttnTo_textbox() {
		return SPOverride_AttnTo_textbox;
	}

	public WebElement getSPOverride_Address_textbox() {
		return SPOverride_Address_textbox;
	}

	public WebElement getSPOverride_AddressLines2_textbox() {
		return SPOverride_AddressLines2_textbox;
	}

	public WebElement getSPOverride_City_textbox() {
		return SPOverride_City_textbox;
	}

	public WebElement getSPOverride_State_ddl() {
		return SPOverride_State_ddl;
	}

	public WebElement getSPOverride_ZipCode_textbox() {
		return SPOverride_ZipCode_textbox;
	}

	public WebElement getCustomerAccSummary() {
		return customerAccSummary;
	}

	@FindBy(xpath = "//select[@id='c5f70814']")
	private WebElement SPOverride_State_ddl;

	@FindBy(xpath = "//input[@id='8b39a549']")
	private WebElement SPOverride_ZipCode_textbox;

	@FindBy(xpath = "//div[text()='Next >>']")
	private WebElement Next_button;

	// select[@id='91c190b1']
	// Process for rules

	@FindBy(xpath = "//div[text()='Project Types']")
	private WebElement Project_type_click;

	@FindBy(xpath = "//input[@id='ms9b9a7bf0']")
	private WebElement Project_type_Txtbox;

	@FindBy(xpath = "//div[text()='Chart Types']")
	private WebElement Chart_types_click;

	@FindBy(xpath = "//input[@id='msd5af1aa3']")
	private WebElement Chart_types_textbox;

	@FindBy(xpath = "//div[text()='Audit Types']")
	private WebElement Audit_types_click;

	@FindBy(xpath = "//input[@id='msa1eca9e8']")
	private WebElement Audit_types_textbox;

	@FindBy(xpath = "//div[text()='Dates of Service Availability']")
	private WebElement Dates_of_service_availability;

	@FindBy(xpath = "//button[text()='Add DOS Range']")
	private WebElement DOS_Click;

	@FindBy(xpath = "//input[@id='992a9b8b']")
	private WebElement DOSAFrom_textbox;

	@FindBy(xpath = "//input[@id='6d47affd']")
	private WebElement DOSATo_textbox;

	@FindBy(xpath = "//input[@id='5ec16eb5']")
	private WebElement Present_checkbox;

	@FindBy(xpath = "//button[@name='DateRangesForServiceAvailability_pyWorkPage.ProcessForRules.DatesOfServiceRanges(1)_8']//i")
	private WebElement DOSA_DelectBtn;

	@FindBy(xpath = "//button[text()='Add DOS Range']")
	private WebElement Add_DOS_Range_btn;

	@FindBy(xpath = "//div[text()='Clients']")
	private WebElement Clients;

	@FindBy(xpath = "//input[@id='ms824e1fae']")
	private WebElement Clients_textbox;

	@FindBy(xpath = "//div[text()='Patient Names']")
	private WebElement Patient_names_click;

	@FindBy(xpath = "//select[@id='2fc9efc6']")
	private WebElement Patient_name_ddl;

	@FindBy(xpath = "//select[@id='3b2b098']")
	private WebElement PatientName_From_txtbox;

	@FindBy(xpath = "//select[@id='29481f2a']")
	private WebElement PatientName_To_txtbox;

	@FindBy(xpath = "//button[text()='Add Letter Range']")
	private WebElement Add_letter_Range_btn;

	@FindBy(xpath = "//button[@name='DisplayLetterRanges_pyWorkPage.ProcessForRules.PatientNameLetterRange(1)_3']//i")
	private WebElement PatientName_Delete;

	@FindBy(xpath = "//div[text()='Finish']")
	private WebElement Finish_button;

	public WebElement getDOSAFrom_textbox() {
		return DOSAFrom_textbox;
	}

	public WebElement getDOSATo_textbox() {
		return DOSATo_textbox;
	}

	public WebElement getDOSA_DelectBtn() {
		return DOSA_DelectBtn;
	}

	public WebElement getPatientName_From_txtbox() {
		return PatientName_From_txtbox;
	}

	public WebElement getPatientName_To_txtbox() {
		return PatientName_To_txtbox;
	}

	public WebElement getPatientName_Delete() {
		return PatientName_Delete;
	}

	public WebElement getDelect_button() {
		return Delect_button;
	}

	@FindBy(xpath = "//button[@name='ProjectTypes_pyWorkPage.ProcessForRules_17']//i")
	private WebElement Delect_button;

	//

	// ----Added Today

	@FindBy(xpath = "//button[text()='Create RG']")
	private WebElement btn_createRG;

	@FindBy(xpath = "(//iframe[contains(@title,'RG-')])[1]")
	private WebElement frame_CreateRG;

	@FindBy(xpath = "//iframe[@name='PegaGadget1Ifr']")
	private WebElement frame_Req_Grp;

	@FindBy(xpath = "//button[@title='Complete this assignment']")
	private WebElement btn_Next;

	@FindBy(xpath = "//h2[text()='Customer account summary']")
	private WebElement customerAccSummary;

	public WebElement getcustomerAccSummary_btn() {
		return customerAccSummary;
	}

	public WebElement getBtn_createRG() {
		return btn_createRG;
	}

	public WebElement getFrame_CreateRG() {
		return frame_CreateRG;
	}

	public WebElement getBtn_Next() {
		return btn_Next;
	}

	public WebElement getNext_btn() {
		return Next_btn;
	}

	public WebElement getAddContact_btn() {
		return AddContact_btn;
	}

	public WebElement getContactType_ddl() {
		return contactType_ddl;
	}

	public WebElement getDepartment_ddl() {
		return Department_ddl;
	}

	public WebElement getName_txtbox() {
		return Name_txtbox;
	}

	public WebElement getRole_txtbox() {
		return role_txtbox;
	}

	public WebElement getEmail_txtbox() {
		return Email_txtbox;
	}

	public WebElement getPhone_txtbox() {
		return Phone_txtbox;
	}

	public WebElement getExt_txtbox() {
		return Ext_txtbox;
	}

	public WebElement getFax_txtbox() {
		return Fax_txtbox;
	}

	public WebElement getPreferred_method_txtbox() {
		return Preferred_method_txtbox;
	}

	public WebElement getTimezone_ddl() {
		return Timezone_ddl;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getRequest_Group_Name_Txtbox() {
		return Request_Group_Name_Txtbox;
	}

	public WebElement getTimezone2_ddl() {
		return TimezoneRequestGroupSetting_ddl;
	}

	public WebElement getInstruction_Txtbox() {
		return Instruction_Txtbox;
	}

	public WebElement getNext_Review_Txtbox() {
		return Next_Review_Txtbox;
	}

	public WebElement getOwner_Txtbox() {
		return Owner_Txtbox;
	}

	public WebElement getOnly_Owner_Checkbox() {
		return Only_Owner_Checkbox;
	}

	public WebElement getContact_Will_Checkbox() {
		return ContactWillVerify_Checkbox;
	}

	public WebElement getContact_Will_Confirm_Checkbox() {
		return Contact_Will_Confirm_Checkbox;
	}

	public WebElement getExcluded_Checkbox() {
		return Excluded_Checkbox;
	}

	public WebElement getWebsite_Txtbox() {
		return Website_Txtbox;
	}

	public WebElement getAttn_Txtbox() {
		return mainAddressAttn_Txtbox;
	}

	public WebElement getAddress_Txtbox() {
		return mainAddressAddress_Txtbox;
	}

	public WebElement getAddress2_Txtbox() {
		return mainAddressAddress2_Txtbox;
	}

	public WebElement getCity_Txtbox() {
		return mainAddressCity_Txtbox;
	}

	public WebElement getState_ddl() {
		return mainAddressState_ddl;
	}

	public WebElement getZipcode_Txtbox() {
		return mainAddressZipcode_Txtbox;
	}

	public WebElement getSending_letter_ddl() {
		return LetterPreference_ddl;
	}

	public WebElement getUse_facility_checkbox() {
		return Use_facility_checkbox;
	}

	public WebElement getGroup_letter_checkbox() {
		return Group_letter_checkbox;
	}

	public WebElement getLetters_per_sendkeys() {
		return Lettersperbatch_sendkeys;
	}

	public WebElement getClaim_Entity_checkbox() {
		return Claim_Entity_checkbox;
	}

	public WebElement getFacility_address_checkbox() {
		return Facility_address_checkbox;
	}

	public WebElement getPatient_Name_checkbox() {
		return Patient_Name_checkbox;
	}

	public WebElement getPatient_DOB_checkbox() {
		return Patient_DOB_checkbox;
	}

	public WebElement getPatient_address_checkbox() {
		return Patient_address_checkbox;
	}

	public WebElement getPatient_age_checkbox() {
		return Patient_age_checkbox;
	}

	public WebElement getPatient_phone_checkbox() {
		return Patient_phone_checkbox;
	}

	public WebElement getPatient_Email_checkbox() {
		return Patient_Email_checkbox;
	}

	public WebElement getProvider_name_checkbox() {
		return Provider_name_checkbox;
	}

	public WebElement getProvider_verification_checkbox() {
		return Provider_verification_checkbox;
	}

	public WebElement getProject_checkbox() {
		return Project_checkbox;
	}

	public WebElement getDate_of_service_checkbox() {
		return Date_of_service_checkbox;
	}

	public WebElement getAdditional_checkbox() {
		return Additional_checkbox;
	}

	public WebElement getAdditionalNote_Txtbox() {
		return AdditionalNote_TextBox;
	}
	
	public WebElement getSend_preference_ddl() {
		return send_preference_ddl;
	}

	public WebElement getNext_button() {
		return Next_button;
	}

	public WebElement getProject_type_click() {
		return Project_type_click;
	}

	public WebElement getProject_type_Txtbox() {
		return Project_type_Txtbox;
	}

	public WebElement getChart_types_click() {
		return Chart_types_click;
	}

	public WebElement getChart_types_textbox() {
		return Chart_types_textbox;
	}

	public WebElement getAudit_types_click() {
		return Audit_types_click;
	}

	public WebElement getAudit_types_textbox() {
		return Audit_types_textbox;
	}

	public WebElement getDates_of_service_availability() {
		return Dates_of_service_availability;
	}

	public WebElement getPresent_checkbox() {
		return Present_checkbox;
	}

	public WebElement getAdd_DOS_Range_btn() {
		return Add_DOS_Range_btn;
	}

	public WebElement getClients() {
		return Clients;
	}

	public WebElement getClients_textbox() {
		return Clients_textbox;
	}

	public WebElement getPatient_names_click() {
		return Patient_names_click;
	}

	public WebElement getPatient_name_ddl() {
		return Patient_name_ddl;
	}

	public WebElement getAdd_letter_Range_btn() {
		return Add_letter_Range_btn;
	}

	public WebElement getFinish_button() {
		return Finish_button;
	}

	public WebElement getCreate_RG_btn() {
		return Create_RG_btn;
	}

	public WebElement getDayTimePreference_checkbox() {
		return DayTimePreference_checkbox;
	}

	public WebElement getContacts_Nextbtn() {
		return Contacts_Nextbtn;
	}

	public WebElement getTimezoneRequestGroupSetting_ddl() {
		return TimezoneRequestGroupSetting_ddl;
	}

	public WebElement getRetrivalMethod_ddl() {
		return RetrivalMethod_ddl;
	}

	public WebElement getEMRsystem_Txtbox() {
		return EMRsystem_Txtbox;
	}

	public WebElement getCopyService_Txtbox() {
		return CopyService_Txtbox;
	}

	public WebElement getContactWillVerify_Checkbox() {
		return ContactWillVerify_Checkbox;
	}

	public WebElement getReasonforExclusion_Txtbox() {
		return ReasonforExclusion_Txtbox;
	}

	public WebElement getMainAddressAttn_Txtbox() {
		return mainAddressAttn_Txtbox;
	}

	public WebElement getMainAddressAddress_Txtbox() {
		return mainAddressAddress_Txtbox;
	}

	public WebElement getMainAddressAddress2_Txtbox() {
		return mainAddressAddress2_Txtbox;
	}

	public WebElement getMainAddressCity_Txtbox() {
		return mainAddressCity_Txtbox;
	}

	public WebElement getMainAddressState_ddl() {
		return mainAddressState_ddl;
	}

	public WebElement getMainAddressZipcode_Txtbox() {
		return mainAddressZipcode_Txtbox;
	}

	public WebElement getLetterPreference_ddl() {
		return LetterPreference_ddl;
	}

	public WebElement getLettersperbatch_sendkeys() {
		return Lettersperbatch_sendkeys;
	}

	public WebElement getCardtype_txtbox() {
		return Cardtype_txtbox;
	}

	public WebElement getPayVia_ddl() {
		return PayVia_ddl;
	}
	
	@FindBy(xpath = "(//span[contains(text(),'Arrow down to open')]/preceding::i[1])[1]")
	private WebElement CardType_ddl;
	
	public WebElement getCardType_ddl() {
		return CardType_ddl;
	}

	@FindBy(xpath = "//span[contains(text(),'Mastercard')]")
	public WebElement cardTypeValue;
	
	
	public WebElement getReturnPreference_ddl() {
		return ReturnPreference_ddl;
	}

	@FindBy(xpath = "(//div[@node_name='DQAPaymentPreference']//child::select)[1]")
	private WebElement payment_preference_ddl;

	@FindBy(xpath = "(//div[@node_name='DQAPaymentPreference']//child::select)[1]/option[contains(text(),'None')]")
	public WebElement payment_preference_ddl_value;
	
	
	public WebElement getPayment_preference_ddl() {
		return payment_preference_ddl;
	}

	@FindBy(xpath = "//select[@id='8ef5e811']")
	private WebElement return_prefernce_ddl;

	public WebElement getReturn_prefernce_ddl() {
		return return_prefernce_ddl;
	}

	public WebElement getFrame_Req_Grp() {
		return frame_Req_Grp;
	}

	public void setFrame_Req_Grp(WebElement frame_Req_Grp) {
		this.frame_Req_Grp = frame_Req_Grp;
	}

	public WebElement getContacts_Backbtn() {
		return Contacts_Backbtn;
	}

	public WebElement getDOS_Click() {
		return DOS_Click;
	}

	
	
	@FindBy(xpath = "//div[contains(text(),' Create Request Group')]")
	public WebElement createRGSuccessMsg;
	
	/*
	 * @FindBy(xpath = "test") private WebElement test; public WebElement
	 * getOverride_Address_textbox() { return Override_Address_textbox; }
	 * 
	 * @FindBy(xpath = "test") private WebElement test; public WebElement
	 * getOverride_AddressLines2_textbox() { return Override_AddressLines2_textbox;
	 * }
	 * 
	 * @FindBy(xpath = "test") private WebElement test; public WebElement
	 * getOverride_City_textbox() { return Override_City_textbox; }
	 * 
	 * @FindBy(xpath = "test") private WebElement test; public WebElement
	 * getOverride_State_ddl() { return Override_State_ddl; }
	 * 
	 * @FindBy(xpath = "test") private WebElement test; public WebElement
	 * getOverride_ZipCode_textbox() { return Override_ZipCode_textbox; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

}
