package com.cotiviti.hdap.BaseClass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.internal.TestResult;

import com.cotiviti.hdap.APICommonLibrary.ExcelReader;
import com.cotiviti.hdap.APICommonLibrary.apiCommonLib;
import com.cotiviti.hdap.CommonUtils.DataHandler;

import io.cucumber.messages.types.TestStepResultStatus;

public class BaseTest implements ITestListener {

	// List to store test results dynamically
	public List<DataHandler> testResults = new ArrayList<>();

	// ConcurrentHashMap to store all test data dynamically fetched from Excel
	public static ConcurrentHashMap<String, Map<String, String>> testData = new ConcurrentHashMap<>();

//	public ConcurrentHashMap<String, String> executionMap;

	public void setup(String filePath, String sheetName) {
		testData = ExcelReader.getAllTestDataFromExcel(filePath, sheetName);
	}

	public static ConcurrentHashMap<String, String> executionMap;

//	@AfterSuite(alwaysRun = true)
//	public ConcurrentHashMap<String, String> afterSuite(ConcurrentHashMap<String, String> executionMap) {
//		// Dynamically iterate over the test case names retrieved from Excel
//		String tcName = executionMap.get("TESTCASENAME");
//		Set<String> testCaseNames = allTestData.keySet();
//		List<String> testCaseNameList = new ArrayList<>(testCaseNames);
//
//		for (String testCaseName : allTestData.keySet()) {
//			long startTime = System.currentTimeMillis();
//
//			try {
//				if (testCaseName.equalsIgnoreCase(tcName)) {
//					// Simulate a test execution
//					Thread.sleep(2000); // Simulating test duration
//					long endTime = System.currentTimeMillis();
//
//					String status = executionMap.get(tcName + ".tcStatus");
//
//					// Create a new DataHandler object for the result and add it to the list
//					DataHandler testResult = new DataHandler();
//					testResult.setTestCaseName(testCaseName);
//					testResult.setStartTime(formatTime(startTime));
//					testResult.setEndTime(formatTime(endTime));
//					testResult.setStatus(status);
//					testResult.setFailReason(""); // No failure reason if passed
//					testResult.setComments(executionMap.get(tcName + ".comments"));
//
//					System.err.println("testResult: " + testResult);
//					// Add to the test results list
//					testResults.add(testResult);
//
//					// Retrieve additional data from allTestData (ConcurrentHashMap)
////					Map<String, String> testCaseData = allTestData.get(testCaseName);
////					if (testCaseData != null) {
////						System.out.println("TestCase Data from Excel for " + testCaseName + ":");
////						System.out.println("EXECUTION_FLAG: " + testCaseData.get("EXECUTION_FLAG"));
////						System.out.println("RGID: " + testCaseData.get("RGID"));
////					}
//					if (i == testCaseNameList.size() - 1) {
//						generateHTMLReport(testResults, executionMap);
//					}
//
//				}
//			} catch (Exception e) {
//				// In case of failure
//				long endTime = System.currentTimeMillis();
//				String status = "Failed";
//
//				// Create a new DataHandler object for the failure and add it to the list
//				DataHandler testResult = new DataHandler();
//				testResult.setTestCaseName(testCaseName);
//				testResult.setStartTime(formatTime(startTime));
//				testResult.setEndTime(formatTime(endTime));
//				testResult.setStatus(status);
//				testResult.setFailReason(e.getMessage()); // Set failure reason
//
//				// Add to the test results list
//				testResults.add(testResult);
//			}
//		}
//		return executionMap;
//
//		// Generate the HTML report dynamically after all tests are completed
////		generateHTMLReport(testResults, executionMap);
//	}

	public ConcurrentHashMap<String, String> concludeTest(ConcurrentHashMap<String, String> executionMap) {
		// Dynamically iterate over the test case names retrieved from Excel
		String suiteName = executionMap.get("SuiteName");
		String tcName = executionMap.get("TESTCASENAME");
		String methodName = executionMap.get("MethodName");

		switch (suiteName) {
		case "QASanity":
			writeReport(executionMap, tcName, methodName);
			break;
		case "CreateData":
			writeCeateDataReport(executionMap, tcName, methodName);
			break;
		default:
			break;
		}
		return executionMap;
	}

	private void writeReport(ConcurrentHashMap<String, String> executionMap, String tcName, String methodName) {
		// Get the list of test case names from allTestData
		Set<String> testCaseNames = testData.keySet();

		// Convert to a list to easily access the last test case
		List<String> testCaseNameList = new ArrayList<>(testCaseNames);

		for (int i = 0; i < testCaseNameList.size(); i++) {
			String testCaseName = testCaseNameList.get(i);
			long startTime = System.currentTimeMillis();

			try {
				if (testCaseName.equalsIgnoreCase(tcName)) {
					// Simulate a test execution
					Thread.sleep(2000); // Simulating test duration
					long endTime = System.currentTimeMillis();

					String status = executionMap.get(tcName + ".tcStatus");

					// Create a new DataHandler object for the result and add it to the list
					DataHandler testResult = new DataHandler();
					testResult.setTestCaseName(methodName);
					testResult.setStartTime(formatTime(startTime));
					testResult.setEndTime(formatTime(endTime));
					testResult.setStatus(status);
					//testResult.setFailReason(""); // No failure reason if passed
					testResult.setComments(executionMap.get(tcName + ".comments"));

					System.err.println("testResult: " + testResult);
					// Add to the test results list
					testResults.add(testResult);

					// Check if it's the last test case in the list
//					if (i == testCaseNameList.size() - 1) {
					generateHTMLReport(testResults, executionMap);
//					}
				}
			} catch (Exception e) {
				// In case of failure
				long endTime = System.currentTimeMillis();
				String status = "Failed";

				// Create a new DataHandler object for the failure and add it to the list
				DataHandler testResult = new DataHandler();
				testResult.setTestCaseName(methodName);
				testResult.setStartTime(formatTime(startTime));
				testResult.setEndTime(formatTime(endTime));
				testResult.setStatus(status);
				//testResult.setFailReason(e.getMessage()); // Set failure reason

				// Add to the test results list
				testResults.add(testResult);

				// Check if it's the last test case in the list
//				if (i == testCaseNameList.size() - 1) {
				generateHTMLReport(testResults, executionMap);
//				}
			}
		}
	}

	private void writeCeateDataReport(ConcurrentHashMap<String, String> executionMap, String tcName,
			String methodName) {
		// Get the list of test case names from allTestData

		int noOfIterations;
	// 	String reportdateAndTime = new apiCommonLib().getCurrentDateTime("yyyyMMddHHmmss");
		String reportName = "TestResults-Report.html";
		executionMap.put("ReportName", reportName);

		String envValue = System.getProperty("NoOfChaseRequests");
		if (envValue != null && !envValue.isEmpty()) {
			noOfIterations = Integer.parseInt(envValue);
//			System.out.println("NoOfChaseRequests -" + noOfIterations);
		} else {
			noOfIterations = Integer.parseInt(executionMap.get("NoOfChaseRequests"));
//			System.out.println("NoOfChaseRequests -" + noOfIterations);
		}

		// int iterationCount = Integer.parseInt(executionMap.get("NoOfChaseRequests"));
		String providerType = executionMap.get("PROVIDERTYPE");
		String requestID = executionMap.get("RGID");
		String provider = "";
		if (providerType.equalsIgnoreCase("facility")) {
			provider = executionMap.get("FacilityID");
			if (provider == null) {
				provider = executionMap.get("FACILITY_ID");
			}
		} else if (providerType.equalsIgnoreCase("practitioner")) {
			provider = executionMap.get("PRACTITIONER_ID");
		}

		if (requestID == null) {
			requestID = executionMap.get("REQUESTGROUP_ID");
		}

		for (int i = 1; i <= noOfIterations; i++) {
			try {

				Thread.sleep(2000);

				// Create a new DataHandler object for the result and add it to the list
				DataHandler testResult = new DataHandler();

				testResult.setRequestGroup(requestID);
//				testResult.setChaseRequest(executionMap.get(i + ".correlationId"));
				testResult.setChaseRequest(executionMap.get(i + ".ChaseRequest"));
				testResult.setCotivitiClaimNumber(executionMap.get(i + ".CotivitiClaimNumber"));
				testResult.setProvider(provider);
				testResult.setChaseStatus(executionMap.get("ChaseRequestType"));
				testResult.setFailReason("");
				testResult.setComments(executionMap.get(i + tcName + ".comments"));

				System.err.println("testResult: " + testResult);
				// Add to the test results list
				testResults.add(testResult);

				// Check if it's the last test case in the list
//					if (i == testCaseNameList.size() - 1) {
				generateNewHTMLReport(testResults, executionMap);
//					}

			} catch (Exception e) {
				// In case of failure
				long endTime = System.currentTimeMillis();
				String status = "Failed";

				// Create a new DataHandler object for the failure and add it to the list
				DataHandler testResult = new DataHandler();
				testResult.setRequestGroup(requestID);
				testResult.setChaseRequest(executionMap.get(i + ".correlationId"));
				testResult.setCotivitiClaimNumber(executionMap.get(i + ".CotivitiClaimNumber"));
				testResult.setProvider(provider);
				testResult.setChaseStatus(executionMap.get("ChaseRequestType"));
				testResult.setComments(executionMap.get(tcName + ".comments"));
				testResult.setFailReason(e.getLocalizedMessage());

				// Add to the test results list
				testResults.add(testResult);

				// Check if it's the last test case in the list
//				if (i == testCaseNameList.size() - 1) {
				generateNewHTMLReport(testResults, executionMap);
//				}
			}
		}
	}

	public void generateHTMLReport(List<DataHandler> testResults, ConcurrentHashMap<String, String> executionMap) {
		String env = System.getProperty("env");
		String dateAndTime = new apiCommonLib().getCurrentDateTime("yyyy-MM-dd 'AT' HH:mm:ss z");
		String reportName = "TestResults-Report.html";
//		String tcName = executionMap.get("TESTCASENAME");
		// Folder path to store the HTML file
		String folderPath = "TestResults";

		// Create the folder if it doesn't exist
		File folder = new File(folderPath);
		if (!folder.exists()) {
			folder.mkdir();
		}

		// Path of the template file
		String templateFilePath = folderPath + "/ReportTemplate.html";
		// Path of the output file
		String outputFilePath = folderPath + "/" + reportName;

		// Read the template file and modify it
		try (BufferedReader reader = new BufferedReader(new FileReader(templateFilePath));
				BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

			String line;
			StringBuilder content = new StringBuilder();
			boolean inTableBody = false;

			// Read the template line by line
			while ((line = reader.readLine()) != null) {
				// Write the template lines to the content
				if (line.contains("{{ENV}}")) {
					line = line.replaceAll("\\{\\{ENV\\}\\}", env);
				}

				if (line.contains("{{DATETIME}}")) {
					line = line.replaceAll("\\{\\{DATETIME\\}\\}", dateAndTime);
				}

				if (line.contains("{{TEST_RESULTS}}")) {
					// Dynamically build the table rows with test results
					for (DataHandler result : testResults) {
						String statusColor = "Passed".equalsIgnoreCase(result.getStatus()) ? "green" : "red";
						String tcName = result.getTestCaseName();
						String row = "<tr>" +
							"<td>" + result.getTestCaseName() + "</td>" +
							"<td>" + result.getStartTime() + "</td>" +
							"<td>" + result.getEndTime() + "</td>" +
							"<td style='color:" + statusColor + "'>" + result.getStatus() + "</td>" +
							"<td>" + result.getComments() + "</td>" +
							"</tr>\n";
						content.append(row);
					}
				} else {
					// Otherwise, append the line as-is
					content.append(line).append("\n");
				}
			}

			// Write the modified content to the output file
			writer.write(content.toString());
			System.out.println("HTML report generated successfully at: " + outputFilePath);

//			System.err.println("testResults: " + testResults);
		} catch (IOException e) {
			System.out.println("Error while generating HTML report: " + e.getMessage());
		}
	}

	public void generateNewHTMLReport(List<DataHandler> testResults, ConcurrentHashMap<String, String> executionMap) {
		String env = System.getProperty("env");
		String dateAndTime = new apiCommonLib().getCurrentDateTime("yyyy-MM-dd 'AT' HH:mm:ss z");
		String reportName = executionMap.get("ReportName");
//		String tcName = executionMap.get("TESTCASENAME");
		// Folder path to store the HTML file
		String folderPath = "TestResults";

		// Create the folder if it doesn't exist
		File folder = new File(folderPath);
		if (!folder.exists()) {
			folder.mkdir();
		}

		// Path of the template file
		String templateFilePath = folderPath + "/CreateDatatTemplate.html";
		// Path of the output file
		String outputFilePath = folderPath + "/" + reportName;

		// Read the template file and modify it
		try (BufferedReader reader = new BufferedReader(new FileReader(templateFilePath));
				BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

			String line;
			StringBuilder content = new StringBuilder();
			boolean inTableBody = false;

			// Read the template line by line
			while ((line = reader.readLine()) != null) {
				// Write the template lines to the content
				if (line.contains("{{ENV}}")) {
					line = line.replaceAll("\\{\\{ENV\\}\\}", env);
				}

				if (line.contains("{{DATETIME}}")) {
					line = line.replaceAll("\\{\\{DATETIME\\}\\}", dateAndTime);
				}

				if (line.contains("{{TEST_RESULTS}}")) {
					// Dynamically build the table rows with test results
					for (DataHandler result : testResults) {
						String tcName = result.getTestCaseName();
						String row = "<tr>" + "<td>" + result.getRequestGroup() + "</td>" + "<td>"
								+ result.getChaseRequest() + "</td>" + "<td>" + result.getCotivitiClaimNumber()
								+ "</td>" + "<td>" + result.getProvider() + "</td>" + "<td>" + result.getChaseStatus()
								+ "</td>" + "<td>" + result.getFailReason() + "</td>" + "<td>" + result.getComments()
								+ "</td>" + "</tr>\n";
						content.append(row);
					}
				} else {
					// Otherwise, append the line as-is
					content.append(line).append("\n");
				}
			}

			// Write the modified content to the output file
			writer.write(content.toString());
			System.out.println("HTML report generated successfully at: " + outputFilePath);

//			System.err.println("testResults: " + testResults);
		} catch (IOException e) {
			System.out.println("Error while generating HTML report: " + e.getMessage());
		}
	}

	@BeforeSuite(alwaysRun = true)
	public void setupExecutionMap() {
		executionMap = new ConcurrentHashMap<>();
//		executionMap.put("configKey", "configValue");
//		System.out.println("Initialized in @BeforeClass: " + executionMap.get("configKey"));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// Handle success result
		long endTime = System.currentTimeMillis();

		// Create a new DataHandler object for success
		DataHandler testResult = new DataHandler();
		testResult.setTestCaseName(result.getName());
		testResult.setStartTime(formatTime(result.getStartMillis()));
		testResult.setEndTime(formatTime(endTime));
		testResult.setStatus("Passed");
		testResult.setFailReason(""); // No failure reason for success

		// Add to the test results list
		testResults.add(testResult);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// Handle failure result
		long endTime = System.currentTimeMillis();

		// Create a new DataHandler object for failure
		DataHandler testResult = new DataHandler();
		testResult.setTestCaseName(result.getName());
		testResult.setStartTime(formatTime(result.getStartMillis()));
		testResult.setEndTime(formatTime(endTime));
		testResult.setStatus("Failed");
		testResult.setFailReason(result.getThrowable().getMessage()); // Capture failure reason

		// Add to the test results list
		testResults.add(testResult);
	}

	public String formatTime(long timeInMillis) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(new Date(timeInMillis));
	}
}
