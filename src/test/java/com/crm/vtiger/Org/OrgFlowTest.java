package com.crm.vtiger.Org;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.vtiger.pages.OrgPage;
import com.crm.vtiger.pages.VerifyOrgPage;
import com.crm.vtiger.utility.BaseClass;
import com.crm.vtiger.utility.FileUtility;
import com.crm.vtiger.utility.JavaUtility;

@Listeners(com.crm.vtiger.listeners.ExtentReportListener.class)
public class OrgFlowTest extends BaseClass {
	String createdOrgName;
	OrgPage op;
	VerifyOrgPage vOP;

	@Test(priority = 1, groups = { "smoke", "org" })
	public void createProductTest() throws IOException, InterruptedException {

		// getting data from prop by utility file

		FileUtility fUtil = new FileUtility();
		// get data from excel file
		String Org = fUtil.getDataFromExcelFile2("Organization", 1, 0);
		hp.navigateToOrg();
		op = new OrgPage(driver);
		createdOrgName = Org + JavaUtility.getRandomNumber();
		op.createOrganization(createdOrgName);
		System.out.println("title after Org creation " + driver.getTitle());

		vOP = new VerifyOrgPage(driver);
		String actualOrgName = vOP.getOrgNameVerify();
		Assert.assertEquals(actualOrgName, createdOrgName);
		Reporter.log("Successfully Org is created ");

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
