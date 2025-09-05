package com.crm.vtiger.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.crm.vtiger.pages.HomePage;
import com.crm.vtiger.pages.LoginPage;

public class BaseClass {

	public WebDriver driver;
	public HomePage hp;
	public WebDriverUtility wdUtil;
	public FileUtility fUtil = new FileUtility();
	public static WebDriver sdriver = null;

	@BeforeClass(groups = { "Sanity", "Regression", "Master", "DataDriven" })
	public void setUp() throws IOException {
		// getting the data from prop file
		String BROWSER = fUtil.getDataFromPropFile("bro");
		// cross browser set up

		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver = driver;
		wdUtil = new WebDriverUtility(driver);
		wdUtil.maxWindow();
		wdUtil.implicitWait();
	}

	@BeforeMethod
	public void login() throws IOException {
		String URL = fUtil.getDataFromPropFile("url");
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp();
		hp = new HomePage(driver, wdUtil);
	}

	// log out from the crm
	@AfterMethod(alwaysRun = true)
	public void logOutFromVtiger() {
		hp.logOut();
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master", "DataDriven" })
	public void tearDown() {
		driver.quit();
	}

	public String captureScreen(String tname) {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhss").format(new Date());

		TakesScreenshot tks = (TakesScreenshot) driver;
		File sourceFile = tks.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
