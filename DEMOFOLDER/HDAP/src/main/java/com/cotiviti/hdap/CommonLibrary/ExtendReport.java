package com.cotiviti.hdap.CommonLibrary;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendReport {

	public static ExtentSparkReporter reporter;
	public static ExtentReports extent;

	public static ExtentReports extendReportsUtils() {
		String Reportspath = com.cotiviti.hdap.CommonLibrary.Constants.REPORTS;
		String Env = com.cotiviti.hdap.CommonLibrary.Constants.Environment;

		reporter = new ExtentSparkReporter(Reportspath);
		reporter.config().setDocumentTitle("ROW Automation Test Results");
		reporter.config().setReportName("ROW MDM Application");
		reporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Application", "ROW");
		extent.setSystemInfo("Manager Name", "Yedire, Srinivasulu");
		extent.setSystemInfo("Tester Name", "Zakir Hussain Shaik");
		extent.setSystemInfo("Environment", Env);
		return extent;

	}

}
