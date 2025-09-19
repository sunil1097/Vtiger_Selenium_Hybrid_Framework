package com.crm.vtiger.dataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.crm.vtiger.utility.XlUtility;

public class OpportunityDataProvider {
	@DataProvider(name = "OppData")
	public String[][] getData() throws IOException {
		String path = "C:\\Users\\dell\\eclipse-workspace\\Vtiger_Project\\src\\test\\resources\\TestScript.xlsx";
		XlUtility xutil = new XlUtility(path);

		int totalRows = xutil.getRowCount("Opportunities");
		int totalCols = xutil.getCellCount("Opportunities", 1);
		String oppData[][] = new String[totalRows][totalCols];

		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j < totalCols; j++) {
				oppData[i - 1][j] = xutil.getCellData("Opportunities", i, j);

			}
		}
		return oppData;
	}
}
