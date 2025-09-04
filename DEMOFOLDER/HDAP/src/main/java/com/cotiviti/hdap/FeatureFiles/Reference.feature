Feature: Request Group Scenarios

Background:
Given Launch the Application


@CreateRGZR                    @RunNow
 Scenario Outline:  Valdiate Whether User is able to create an RG with Single Contact
 Given Read ExcelData from CreateRGSheet  for  <TestCaseID>
Given 	Click on CreateRGButton
And     	Click on NextButton
Then 		Under Contact section Add a Single Contact
And     Under RG Setting Tab Enter Request Group Name
And     Under RG Setting Tab Enter date under Contact Day Time Preference Section
And     Under RG Setting Tab Enter date for Special Instructions Section
And     Under RG Setting Tab Enter date for Retrieval Method  Section
And     Under RG Setting Tab Enter date for Workflow Settings Section
And     Under RG Setting Tab Enter date for Main address Section
And     Under RG Setting Tab Enter date for Letter Preference Section
And     Under RG Setting Tab Enter date for Payment Preference  Section
And     Under RG Setting Tab Enter date for Return Preference  Section
And     Under RG Setting Tab Enter date for Send Preference  Section
And     Under Process For Rules Section for Valid PFRs
And     Click on the Submit Button
Then   Validate the Success Message on the Create RG Screen

Examples:
|		TestCaseID		|
|		CreateRG_AllSections		|




@CreateRGZR                    @RunNow
 Scenario Outline:  Valdiate Whether User is able to create an RG with Single Contact
 Given Read ExcelData from CreateRGSheet  for  <TestCaseID>
Given 	Click on CreateRGButton
And     	Click on NextButton
Then 		Under Contact section Add a Single Contact
Then 		Enter Data in the  Request Group Settings section
Examples:
|		TestCaseID		|
|		CreateRG_AllSections		|


@CreateRG 	@CreateRG_With_SingleContact_SP_Mail         @RunNow
 Scenario Outline:  Valdiate Whether User is able to create an RG with Single Contact
 Given Read ExcelData from CreateRGSheet  for  <TestCaseID>
Given 	Click on CreateRGButton
And     Click on NextButton
Then 		Under Contact section Add a Single Contact
Then 		Enter Data in the  Request Group Settings section with SendPreference of Mail

Examples:
|		TestCaseID	                  |
|		SP_Mail  |


@CreateRG 	@CreateRG_With_SingleContact_SP_Upload        @RunNowZR
 Scenario Outline:  Valdiate Whether User is able to create an RG with Single Contact
 Given Read ExcelData from CreateRGSheet  for  <TestCaseID>
Given 	Click on CreateRGButton
And     Click on NextButton
Then 		Under Contact section Add a Single Contact
Then 		Enter Data in the  Request Group Settings section with SendPreference of Fax

Examples:
|		TestCaseID		|
|		SP_Fax		|


@CreateRG 	@CreateRG_With_SingleContact_SP_Fax       @RunNow
 Scenario Outline:  Valdiate Whether User is able to create an RG with Single Contact
 Given Read ExcelData from CreateRGSheet  for  <TestCaseID>
Given 	Click on CreateRGButton
And     Click on NextButton
Then 		Under Contact section Add a Single Contact
Then 		Enter Data in the  Request Group Settings section with SendPreference of Email

Examples:
|		TestCaseID		|
|		SP_Email		|

@CreateRG 	@CreateRG_With_SingleContact_SP_Fax          @RunNow
 Scenario Outline:  Valdiate Whether User is able to create an RG with Single Contact
 Given Read ExcelData from CreateRGSheet  for  <TestCaseID>
Given 	Click on CreateRGButton
And     Click on NextButton
Then 		Under Contact section Add a Single Contact
Then 		Enter Data in the  Request Group Settings section with SendPreference of Upload

Examples:
|		TestCaseID		|
|		SP_Upload		|


@CreateRG 	@CreateRG_With_SingleContact_SP_PrimayMail_CheckBox       @RunNow
 Scenario Outline:  Valdiate Whether User is able to create an RG with Single Contact
 Given Read ExcelData from CreateRGSheet  for  <TestCaseID>
Given 	Click on CreateRGButton
And     	Click on NextButton
Then 		Under Contact section Add a Single Contact
Then 		Enter Data in the  Request Group Settings section with SendPreference as Use primary contacts Mail address

Examples:
|		TestCaseID		|
|		SP_Mail_CheckBox		|


@CreateRG 					@CreateRG_With_SingleContact_SP_PrimayEmail_CheckBox      @RunNow
 Scenario Outline:  Valdiate Whether User is able to create an RG with Single Contact
 Given Read ExcelData from CreateRGSheet  for  <TestCaseID>
Given 	Click on CreateRGButton
And     	Click on NextButton
Then 		Under Contact section Add a Single Contact
Then 		Enter Data in the  Request Group Settings section with SendPreference as Use primary contacts Email address

Examples:
|		TestCaseID		|
|		SP_Email_CheckBox		|


@CreateRG 					@CreateRG_With_SingleContact_SP_PrimayFax_CheckBox     @RunNow
 Scenario Outline:  Valdiate Whether User is able to create an RG with Single Contact
 Given Read ExcelData from CreateRGSheet  for  <TestCaseID>
Given 	Click on CreateRGButton
And     Click on NextButton
Then 		Under Contact section Add a Single Contact
Then 		Enter Data in the  Request Group Settings section with SendPreference as Use primary contacts Fax address

Examples:
|		TestCaseID		|
|		SP_Fax_CheckBox		|


@CreateRG 					@CreateRG_With_SingleContact_SP_PrimayFax_CheckBox      @RunNow
 Scenario Outline:  Valdiate Whether User is able to create an RG with Single Contact
 Given Read ExcelData from CreateRGSheet  for  <TestCaseID>
Given 	Click on CreateRGButton
And     Click on NextButton
Then 		Under Contact section Add a Single Contact
Then 		Enter Data in the  Request Group Settings section with All Mandatory Fields

Examples:
|		TestCaseID		|
|		CreateRG_MandatoryFields  |


@CreateRG 					@CreateRG_With_SingleContact_SP_PrimayFax_CheckBox        @RunNow
 Scenario Outline:  Valdiate Whether User is able to create an RG with Single Contact
 Given Read ExcelData from CreateRGSheet  for  <TestCaseID>
Given 	Click on CreateRGButton
And     Click on NextButton
Then 		Under Contact section Add a Multiple Contacts

Examples:
|		TestCaseID		|
|		Multiple_Contacts  |


