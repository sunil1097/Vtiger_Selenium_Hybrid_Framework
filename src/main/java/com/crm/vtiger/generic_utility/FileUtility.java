package com.crm.vtiger.generic_utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileUtility {
	public String getDataFromPropFile(String key) throws IOException {
		
		// fileinputStream will read/open the file
		FileInputStream fis = new FileInputStream("C:\\Users\\dell\\eclipse-workspace\\Vtiger_Project\\src\\test\\resources\\commonData.properties");
		// actual reading will be done by Properties file , then store in memory
		Properties pObj = new Properties();
		pObj.load(fis);
		
		//by using getProperty  we will get the value
		String VALUE = pObj.getProperty(key);
		return VALUE;
	}
	// Product excel file 
	
	public String getDataFromExcelFile(String sheetName,int rowNum, int cellNum) throws EncryptedDocumentException, IOException {
		FileInputStream fis2 = new FileInputStream("C:\\Users\\dell\\eclipse-workspace\\Vtiger_Project\\src\\test\\resources\\TestScript.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis2);
		String value = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		return value;
	}
	// org excel file
	public String getDataFromExcelFile2(String sheetName, int rowName,int cellNum) throws EncryptedDocumentException, IOException {
		FileInputStream fis3 = new FileInputStream("C:\\Users\\dell\\eclipse-workspace\\Vtiger_Project\\src\\test\\resources\\GetDataFromOrg.xlsx");
		Workbook wb = WorkbookFactory.create(fis3);
		String value =wb.getSheet(sheetName).getRow(rowName).getCell(cellNum).getStringCellValue();
		wb.close();
		fis3.close();
		return value;
		
		
	}
	// contact file
	public String getDataFromContact(String sheetName, int rowName,int cellNum) throws EncryptedDocumentException, IOException {
		try(FileInputStream fis4 = new FileInputStream("C:\\Users\\dell\\eclipse-workspace\\Vtiger_Project\\src\\test\\resources\\GetDataFromContact.xlsx");
		Workbook wb = WorkbookFactory.create(fis4)){
		String value =wb.getSheet(sheetName).getRow(rowName).getCell(cellNum).getStringCellValue();
		return value;
		}
		
	}
	
	// Opportunities 
	public String getDataFromOppotunity(String sheetName,int rowNum,int cellNum,) throws EncryptedDocumentException, IOException {
		try(FileInputStream fis5 = new FileInputStream("path of file");
		Workbook wb = WorkbookFactory.create(fis5)){
		Sheet sh = wb.getSheet(sheetName);
		
		for(int i=0;i<=sh.getLastRowNum();i++) {
			
			Row row = sh.getRow(i);
			
			String first1 = row.getCell(0).getStringCellValue();
			String second1 = row.getCell(1).getStringCellValue();
			
		}
		
		}
		return first + "|" + second;
		
	}
	// documents 
	public String getDataFromDocuments(String sheetName,int rowNum,int cellNum) throws EncryptedDocumentException, IOException {
		FileInputStream fis6 = new FileInputStream("path of the file");
		Workbook wb = WorkbookFactory.create(fis6);
		String value = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		return value;
	}

}
