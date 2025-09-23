package com.crm.vtiger.Org;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.vtiger.pages.OrgPage;
import com.crm.vtiger.pages.VerifyOrgPage;
import com.crm.vtiger.utility.BaseClass;
import com.crm.vtiger.utility.JavaUtility;
import com.crm.vtiger.utility.XlUtility;

@Listeners(com.crm.vtiger.listeners.ExtentReportListener.class)
public class OrgFlowTest extends BaseClass {
	String createdOrgName;
	OrgPage op;
	VerifyOrgPage vOP;

	@Test(priority = 1, groups = { "smoke",
			"org" }, dataProvider = "orgData", dataProviderClass = com.crm.vtiger.dataProvider.OrgDataProvider.class)
	public void createProductTest(String OrgNameFromExcel) throws IOException, InterruptedException {
		hp.navigateToOrg();
		op = new OrgPage(driver);
		createdOrgName = OrgNameFromExcel + JavaUtility.getRandomNumber();
		op.createOrganization(createdOrgName);
		System.out.println("title after Org creation " + driver.getTitle());
		// store created org name into excel for Opportunity module

		String path = "C:\\Users\\dell\\eclipse-workspace\\Vtiger_Project\\src\\test\\resources\\TestScript.xlsx";
		XlUtility xutil = new XlUtility(path);
		xutil.setCellData("OrgData", 1, 0, createdOrgName);

	}

	@Test(priority = 2, dependsOnMethods = "createProductTest", groups = { "sanity", "functional",
			"Org" }, enabled = false)
	public void editTheExistingOrg() {
		hp.navigateToOrg();
		op = new OrgPage(driver);
		String updatedOrgName = "samal" + JavaUtility.getRandomNumber();
		op.editExistOrg(createdOrgName, updatedOrgName);
		Reporter.log("successfully edited ", true);
		createdOrgName = updatedOrgName;
	}

	@Test(priority = 3, dependsOnMethods = "editTheExistingOrg", groups = { "regression", "org" }, enabled = false)
	public void deleteTheExistitngOrg() {
		hp.navigateToOrg();
		op = new OrgPage(driver);
		op.deleteExistOrg(createdOrgName);
		Reporter.log("successfully deleted a product", true);
	}

}
