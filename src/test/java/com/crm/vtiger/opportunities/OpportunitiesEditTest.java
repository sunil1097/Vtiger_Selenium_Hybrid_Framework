package com.crm.vtiger.opportunities;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.crm.vtiger.pages.OpportunitiesPage;
import com.crm.vtiger.pages.OpportunityDetailsPage;
import com.crm.vtiger.utility.BaseClass;
import com.crm.vtiger.utility.JavaUtility;
import com.crm.vtiger.utility.XlUtility;

public class OpportunitiesEditTest extends BaseClass {
	OpportunitiesPage op;
	OpportunityDetailsPage opd;
	String createdOppName;
	String editedOppName;
	String orgName;

	@Test
	public void editOpportunityTest() throws IOException {
		hp.navigateToOpp();
		op = new OpportunitiesPage(driver);

		// fetch org name from excel
		String path = System.getProperty("user.dir") + "/src/test/resources/TestScript.xlsx";
		XlUtility xutil = new XlUtility(path);
		String orgName = xutil.getCellData("OrgData", 1, 0); // fetch first org

		// create a new opportunity for this test
		createdOppName = "TestOpp_" + JavaUtility.getRandomNumber();

		op.createOpportunities(createdOppName, orgName);

		opd = new OpportunityDetailsPage(driver);

		editedOppName = createdOppName + "_edited123";
		opd.clickEdit();
		opd.editTheOppName(editedOppName);
		opd.saveChanges();
		Assert.assertEquals(opd.getOpportunityName(), editedOppName, "Opportunity name mismatch after edit");
		Assert.assertEquals(opd.getOrgName().trim(), orgName.trim(), "Org name mismatch after edit");

	}

	@AfterMethod
	public void cleanUp() {
		if (editedOppName != null) {
			opd.clickDelete();
		}
	}

}
