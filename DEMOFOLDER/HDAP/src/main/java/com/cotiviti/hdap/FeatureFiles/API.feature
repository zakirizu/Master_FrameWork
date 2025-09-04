Feature: Test feature
   
      @dryTest @Dinesh
    Scenario Outline: Create Chase Request with Single Matching RG
    Given Read ExcelData from API_Sheet for  <TestCaseID>
    Given Create Chase Request with Single Matching RG
  
     Examples:
|		TestCaseID		|
|		API5	|

   
   @SingleMatchingRG 
    Scenario Outline: Create Chase Request with Single Matching RG
    Given Read ExcelData from API_Sheet for  <TestCaseID>
    Given Create Chase Request with Single Matching RG
  
     Examples:
|		TestCaseID		|
|		API1	|
|		API2	|
|		API3	|
|		API4	|
|		API5	|
|		API6	|
|		API7	|
|		API8	|


   @NoMatchingRG
    Scenario Outline: Create Chase Request with No Matching RG
    Given Read ExcelData from API_Sheet for  <TestCaseID>
    Given Create Chase Request with No Matching RG
  
   Examples:
|		TestCaseID		|
|		API1	|


   @MultipleMatchingRGs
    Scenario Outline: Create Chase Request with Multiple Matching RG
    Given Read ExcelData from API_Sheet for  <TestCaseID>
    Given Create Chase Request with Multiple Matching RG
     Examples:
|		TestCaseID		|
|		API1	|
