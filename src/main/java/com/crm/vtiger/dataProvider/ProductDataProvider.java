package com.crm.vtiger.dataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.crm.vtiger.utility.XlUtility;

public class ProductDataProvider {

	@DataProvider(name = "productData")
	public String[][] getData() throws IOException {
		String path = "C:\\Users\\dell\\eclipse-workspace\\Vtiger_Project\\src\\test\\resources\\TestScript.xlsx";
		XlUtility xutil = new XlUtility(path);

		int totalRows = xutil.getRowCount("Product");
		int totalCols = xutil.getCellCount("Product", 1);

		String productData[][] = new String[totalRows][totalCols];
		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j < totalCols; j++) {
				productData[i - 1][j] = xutil.getCellData("Product", i, j);

			}

		}
		return productData;

	}
}
