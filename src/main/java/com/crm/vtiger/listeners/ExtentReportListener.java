	package com.crm.vtiger.listeners;
	
	import java.io.File;
	import java.io.IOException;
	
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.io.FileHandler;
	import org.testng.ISuite;
	import org.testng.ISuiteListener;
	import org.testng.ITestListener;
	import org.testng.ITestResult;
	import org.testng.Reporter;

import com.crm.vtiger.utility.BaseClass;
import com.crm.vtiger.utility.JavaUtility;
	
	public class ExtentReportListener implements ISuiteListener, ITestListener {
		public JavaUtility jUtil;
	
		@Override
		public void onTestStart(ITestResult result) {
			String methodName = result.getMethod().getMethodName();
			Reporter.log(methodName + "started executing");	
		}
	
		@Override
		public void onTestSuccess(ITestResult result) {
			String methodName = result.getMethod().getQualifiedName();
			Reporter.log(methodName + " got passed");
		
		}
	
		@Override
		public void onTestFailure(ITestResult result) {
			String methodName = result.getMethod().getMethodName();
			Reporter.log(methodName + " got failed "); 
			jUtil = new JavaUtility();
			String time = jUtil.getCurrentDateTime();
			
			TakesScreenshot tks = (TakesScreenshot) BaseClass.sdriver;
			File src = tks.getScreenshotAs(OutputType.FILE);
			File dest = new File("C:\\Users\\dell\\eclipse-workspace\\Vtiger_Project\\ScreenShots\\errorShots" + time + ".png");
			
			try {
				FileHandler.copy(src, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
	
		@Override
		public void onTestSkipped(ITestResult result) {
			String methodName =result.getMethod().getMethodName();
			Reporter.log(methodName + "got skipped ");
		
		}
	
		@Override
		public void onStart(ISuite suite) {
			System.out.println("DB connection + report configuration ");
		
		}
	
		@Override
		public void onFinish(ISuite suite) {
			 System.out.println("DB connection + report  backup ");
		}
		
	
	}
