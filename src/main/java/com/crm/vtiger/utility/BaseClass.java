package com.crm.vtiger.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.crm.vtiger.pages.HomePage;
import com.crm.vtiger.pages.LoginPage;

public class BaseClass {

	public WebDriver driver;
	public HomePage hp;
	public WebDriverUtility wdUtil;
	public FileUtility fUtil = new FileUtility();
	public static WebDriver sdriver = null;

	@Parameters({ "browser", "os" })
	@BeforeClass(groups = { "Sanity", "Regression", "Master", "DataDriven" })
	public void setUp(String browser, String os) throws IOException {
		// if browser is not passed in xml , fall back to prop file
		if (browser == null || browser.isEmpty()) {
			browser = fUtil.getDataFromPropFile("bro");
		}

		// cross browser set up

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("browser not supported :" + browser);
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
