package com.crm.vtiger.Org;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.vtiger.pages.OrgPage;
import com.crm.vtiger.pages.VerifyOrgPage;
import com.crm.vtiger.utility.BaseClass;
import com.crm.vtiger.utility.JavaUtility;

@Listeners(com.crm.vtiger.listeners.ExtentReportListener.class)
public class OrgFlowTest extends BaseClass {
	String createdOrgName;
	OrgPage op;
	VerifyOrgPage vOP;

	@Test(priority = 1, groups = { "smoke",
			"org" }, dataProvider = "orgData", dataProviderClass = com.crm.vtiger.dataProvider.OrgDataProvider.class)
	public void createProductTest(String OrgNameFromExcel) throws IOException, InterruptedException {
		if (hp == null) {
			throw new IllegalStateException("HomePage not initialized. Login might have failed.");
		}
		hp.navigateToOrg();
		op = new OrgPage(driver);
		createdOrgName = OrgNameFromExcel + JavaUtility.getRandomNumber();
		op.createOrganization(createdOrgName);
		System.out.println("title after Org creation " + driver.getTitle());

	}

	@Test(priority = 2, dependsOnMethods = "createProductTest", groups = { "sanity", "functional", "Org" })
	public void editTheExistingOrg() {
		hp.navigateToOrg();
		op = new OrgPage(driver);
		String updatedOrgName = "samal" + JavaUtility.getRandomNumber();
		op.editExistOrg(createdOrgName, updatedOrgName);
		Reporter.log("successfully edited ", true);
		createdOrgName = updatedOrgName;
	}

	@Test(priority = 3, dependsOnMethods = "editTheExistingOrg", groups = { "regression", "org" })
	public void deleteTheExistitngOrg() {
		hp.navigateToOrg();
		op = new OrgPage(driver);
		op.deleteExistOrg(createdOrgName);
		Reporter.log("successfully deleted a product", true);
	}

}
