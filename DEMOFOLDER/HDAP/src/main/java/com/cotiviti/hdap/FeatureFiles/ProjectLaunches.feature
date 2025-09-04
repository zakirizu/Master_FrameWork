Feature: DQA Task Related Scenarios

Background:
Given Launch the Application

@Mas
 Scenario Outline:  Valdiate Whether User is view the Current Launch Page
  Given Read ExcelData from projectLaunchSheet  for  <TestCaseID>
 Given User Clicks on the Project Launches Tab
 Then   System should display the Project Launch screen
 And     Validate whether user is able to view LaunchAllReadyProjects
 


    
    
