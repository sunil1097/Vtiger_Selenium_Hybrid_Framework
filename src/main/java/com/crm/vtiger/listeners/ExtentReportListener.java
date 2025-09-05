package com.crm.vtiger.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.vtiger.utility.BaseClass;

public class ExtentReportListener implements ITestListener {
	public ExtentSparkReporter sparkReporter; // for UI
	public ExtentReports extent; // for env info ,os version

	private ThreadLocal<ExtentTest> test = new ThreadLocal<>(); // Thread-safe per-test storage

	String repName;

	@Override
	public void onStart(ITestContext context) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		repName = "Test-Report- " + timeStamp + ".html";
		// // Initialize ExtentSparkReporter for UI
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // specify location of the report

		sparkReporter.config().setDocumentTitle("OVTiger CRM Automation Project"); // Title of report
		sparkReporter.config().setReportName("VTiger CRM Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		// Initialize main ExtentReports
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		// Add report metadata
		extent.setSystemInfo("Application", "Vtiger CRM");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "sunil samal");
		// Read OS and browser parameters from testng.xml
		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("browser", browser);
		// Include TestNG groups info
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("groups", includedGroups.toString());
		}
	}

	// ------------------- onTestStart() -------------------

	@Override
	public void onTestStart(ITestResult result) {
		// Create a test entry in the report for this test method
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());

		// Assign module dynamically based on class name
		String moduleName = getModuleName(result);
		extentTest.assignCategory(moduleName);

		// Assign groups
		extentTest.assignCategory(result.getMethod().getGroups());

		// Store in ThreadLocal for parallel-safe logging
		test.set(extentTest);
	}
	// ------------------- onTestSuccess() -------------------

	public void onTestSuccess(ITestResult result) {
		test.get().log(Status.PASS, result.getName() + " executed successfully");
	}

	// ------------------- onTestFailure() -------------------
	@Override
	public void onTestFailure(ITestResult result) {
		test.get().log(Status.FAIL, result.getName() + " failed");
		test.get().log(Status.INFO, result.getThrowable());

		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.get().addScreenCaptureFromPath(imgPath);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// ------------------- onTestSkipped() -------------------
	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().log(Status.SKIP, result.getName() + " skipped");
		test.get().log(Status.INFO, result.getThrowable());
	}
	// ------------------- onFinish() -------------------

	public void onFinish(ITestContext context) {
		extent.flush();

		// Open report automatically in default browser
		try {
			Desktop.getDesktop().browse(new File(".\\reports\\" + repName).toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ------------------- Helper method: getModuleName() -------------------
	private String getModuleName(ITestResult result) {
		String className = result.getTestClass().getName().toLowerCase();
		if (className.contains("product")) {
			return "Product Module";
		} else if (className.contains("org") || className.contains("organization")) {
			return "Organization Module";
		} else {
			return "General Module";
		}
	}

}
