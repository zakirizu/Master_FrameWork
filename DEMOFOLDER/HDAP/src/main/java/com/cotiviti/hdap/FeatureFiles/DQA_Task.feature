Feature: DQA Task Related Scenarios
Background:
Given Launch the Application

@MaskZR @Smoke @Sanity
 Scenario Outline:  Valdiate Whether User is able to perform DQA Task
 Given   Read ExcelData from DQATaskSheet  for  <TestCaseID>
 Given 		Search for the User RG ID <RequestGroupID>
Given 		Add the task <Task>
Given Read ExcelData from <SheetName> and <TestCaseID>
Examples: 
      | RequestGroupID 						| Task 																																						| SheetName | TestCaseID |
      | RET-9642869539    						| Exception Request      																					| alphaSheet |TestCasesID1|
       | RET-9642869539    						| Email      																																	| betaSheet |TestCasesID10|
      | RET-9642869539    						| Chase Request	      																								| realTime |HighLightElement|

      
      
      
@DQATask @Smoke
 Scenario Outline:  Valdiate Whether User is able to perform DQA Task
 Given 		Search for the User RG ID <RequestGroupID>
 Given 		Add the task <Task>
 Given 		Click on CreateRGButton

Examples: 
      | RequestGroupID 						| Task 																																							|
      | RET-9642869539    						| Exception Request     																						|
      | RET-9642869539    						| Chase Request																			   									|
      | RET-9642869539    						| Email																			    																	|
      | RET-9642869539    						| General service request																			|
      | RET-9642869539    						| Update Request Group Settings					   						|
      | RET-9642869539    						| Generate Request Document							   						|
      | RET-9642869539    						| Update Contact Details													   						|
      | RET-9642869539    						| Update Processes For Rules								    						|
      | RET-9642869539    						| Upload Medical Record														  					|
      | RET-9642869539    						| Verify and Confirm Dates												 						|
      | RET-9642869539    						| Resolve No Patients/No Records											|



    
    
