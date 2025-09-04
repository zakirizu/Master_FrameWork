package com.cotiviti.hdap.CommonLibrary;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.cotiviti.hdap.CommonUtils.BaseClass;

public class Listeners extends BaseClass implements ITestListener {
	static Logger myLogger = LogManager.getLogger(Listeners.class.getName());
	ExtentReports extent = ExtendReport.extendReportsUtils();
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		String testCaseName = result.getMethod().getMethodName();
		myLogger.info(
				"**************************************************************************************************************************************************************");
		myLogger.info(
				"**************************************************************************************************************************************************************");
		myLogger.info("***************************************************TEST CASE START: " + testCaseName
				+ "******************************");
		myLogger.info(
				"**************************************************************************************************************************************************************");
		myLogger.info(
				"**************************************************************************************************************************************************************");
		test = extent.createTest(result.getMethod().getMethodName());
		String filePath = null;

		try {
			filePath = takeScreenShot(result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "TEST CASE PASSED");
		String filePath = null;
		try {
			filePath = takeScreenShot(result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		// test.addScreenCaptureFromBase64String(filePath,
		// result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		test.log(Status.FAIL, "TEST CASE FAILED");
		String filePath = null;
		try {
			filePath = takeScreenShot(result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		String testCaseName = ((ITestResult) context).getMethod().getMethodName();
		myLogger.info("----------------------------Test Case START: " + testCaseName + "----------------------------");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.fail(result.getThrowable());
		test.log(Status.WARNING, "TRY TO CONNECT TO GLOBAL PROTECT ANG RUN AGAIN");
		String filePath = null;

		try {
			filePath = takeScreenShot(result.getMethod().getMethodName());
			Assert.assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

	}

}
