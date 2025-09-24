package com.crm.vtiger.opportunities;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.vtiger.pages.OpportunitiesPage;
import com.crm.vtiger.pages.OpportunityDetailsPage;
import com.crm.vtiger.utility.BaseClass;
import com.crm.vtiger.utility.JavaUtility;
import com.crm.vtiger.utility.XlUtility;

public class OpportunitiesCreateTest extends BaseClass {
	OpportunitiesPage op;
	OpportunityDetailsPage opd;
	String createdOppName;

	@Test(priority = 1, groups = { "smoke",
			"Opportunities" }, dataProvider = "oppData", dataProviderClass = com.crm.vtiger.dataProvider.OpportunityDataProvider.class)
	public void createOpportunityTest(String OppNameFromExcel) throws IOException {
		hp.navigateToOpp();
		op = new OpportunitiesPage(driver);
		createdOppName = OppNameFromExcel + JavaUtility.getRandomNumber();

		// get the org name for Opptest
		String path = "C:\\Users\\dell\\eclipse-workspace\\Vtiger_Project\\src\\test\\resources\\TestScript.xlsx";

		// Create XlUtility object
		XlUtility xutil = new XlUtility(path);

		// Now fetch organisation name
		String orgName = xutil.getCellData("OrgData", 1, 0);
		op.createOpportunities(createdOppName, orgName);

		OpportunityDetailsPage opd = new OpportunityDetailsPage(driver);

		// Assertions
		Assert.assertEquals(opd.getOpportunityName(), createdOppName);
		Assert.assertEquals(opd.getOrgName(), orgName);

		System.out.println("Org name fetched from Excel: " + orgName);

	}

	// @AfterMethod
	public void cleanUP() {
		if (createdOppName != null) {
			opd.getOpportunityName();
			opd.clickDelete();
		}

	}

}
