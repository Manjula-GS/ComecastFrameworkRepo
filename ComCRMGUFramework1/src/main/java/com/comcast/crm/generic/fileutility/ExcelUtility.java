package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getDataFromExcel(String sheetName, int rowNum, int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./testData/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).toString();
		wb.close();
		
		return data;
	}
	
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./testData/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rcount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		
		return rcount;
	}
	
	public void setDataIntoExcel(String sheetName, int rowNum, int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./testData/TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
		FileOutputStream fos=new FileOutputStream("./testData/TestData.xlsx");
		wb.write(fos);
		wb.close();
	}

}
