package com.crm.vtiger.opportunities;

import org.testng.ITestContext;

import com.crm.vtiger.pages.OpportunitiesPage;
import com.crm.vtiger.utility.BaseClass;

public class OppotunitiesEditTest extends BaseClass {
	OpportunitiesPage op;

	public void editOpportunityTest(ITestContext context) {
		hp.navigateToOpp();
		op = new OpportunitiesPage(driver);
		op.editOpportunities();
		String opportunityName = (String) context.getAttribute("OppNameFromExcel");

	}

}
