package com.crm.vtiger.opportunities;

import java.io.IOException;

import org.testng.annotations.Test;

import com.crm.vtiger.pages.OpportunitiesPage;
import com.crm.vtiger.utility.BaseClass;
import com.crm.vtiger.utility.JavaUtility;
import com.crm.vtiger.utility.XlUtility;

public class OpportunitiesFlowTest extends BaseClass {
	OpportunitiesPage op;
	String createdOppName;

	@Test(priority = 1, groups = { "smoke",
			"Opportunities" }, dataProvider = "oppData", dataProviderClass = com.crm.vtiger.dataProvider.OpportunityDataProvider.class)
	public void createOpportunityTest(String OppNameFromExcel) throws IOException {
		hp.navigateToOpp();
		op = new OpportunitiesPage(driver);
		createdOppName = OppNameFromExcel + JavaUtility.getRandomNumber();
		op.createOpportunities(createdOppName, OppNameFromExcel);

		// get the org name for Opptest
		String path = "C:\\Users\\dell\\eclipse-workspace\\Vtiger_Project\\src\\test\\resources\\TestScript.xlsx";

		// 2️⃣ Create XlUtility object
		XlUtility xutil = new XlUtility(path);

		// 3️⃣ Now fetch organisation name
		String orgName = xutil.getCellData("OrgData", 1, 0);

	}

}
