package com.crm.vtiger.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configUtility {
	private static final String EXCEL_PATH = "C:\\Users\\dell\\eclipse-workspace\\Vtiger_Project\\src\\test\\resources\\commonData.properties";

	public String getDataFromPropFile(String key) throws IOException {

		// fileinputStream will read/open the file
		FileInputStream fis = new FileInputStream(EXCEL_PATH);
		// actual reading will be done by Properties file , then store in memory
		Properties pObj = new Properties();
		pObj.load(fis);

		// by using getProperty we will get the value
		String value = pObj.getProperty(key);
		return value;
	}

}
