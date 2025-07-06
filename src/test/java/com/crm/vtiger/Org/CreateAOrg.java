package com.crm.vtiger.Org;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.crm.vtiger.generic_utility.BaseClass;
import com.crm.vtiger.generic_utility.FileUtility;
import com.crm.vtiger.generic_utility.JavaUtility;


import Object.repos.OrgPage;
import Object.repos.VerifyOrgPage;



public class CreateAOrg extends BaseClass {
	OrgPage op;
	String createdAOrgName;
	VerifyOrgPage vOP;
	@Test(priority =1)
	public void createProductTest() throws IOException, InterruptedException {
		
		// getting data from prop by utility file 
		
		FileUtility fUtil = new FileUtility();
		// get data from excel file 
		String Org = fUtil.getDataFromExcelFile2("Organization", 1, 0);
		hp.navigateToOrg();	
		op = new OrgPage(driver);
		createdAOrgName = Org + JavaUtility.getRandomNumber();
		op.createOrganization(createdAOrgName);
		System.out.println("title after Org creation "+ driver.getTitle());
		
		vOP = new VerifyOrgPage(driver);
		String actualOrgName = vOP.getOrgNameVerify();
		Assert.assertEquals(actualOrgName, createdAOrgName);
		Reporter.log("Successfully Org is created ");
		
	}
	@Test(priority=2 ,dependsOnMethods ="createProductTest")
	public void editTheExistingOrg() {
		hp.navigateToOrg();
		op = new OrgPage(driver);
		op.editExistOrg(createdAOrgName);
		String updatedOrgName = "samal"+  JavaUtility.getRandomNumber();
		op.editTheInternalOrgPage(updatedOrgName);
		Reporter.log("successfully edited ", true);
		createdAOrgName=updatedOrgName;
	}
	@Test(priority=3, dependsOnMethods ="editTheExistingOrg")
	public void deleteTheExistitngOrg() {
		hp.navigateToOrg();
		op = new OrgPage(driver);
		op.deleteExistOrg(createdAOrgName);
		Reporter.log("successfully deleted a product", true);
	}

}
