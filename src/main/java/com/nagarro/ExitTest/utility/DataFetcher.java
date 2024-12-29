package com.nagarro.ExitTest.utility;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.nagarro.ExitTest.config.ExcelReader;

public class DataFetcher {

	private static final String FILE_PATH = "TestData.xlsx";

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        String sheetName = "Sheet3";
        List<Object[]> data = ExcelReader.getTestData(FILE_PATH, sheetName);
        return data.toArray(new Object[0][]);
    }
    
    @DataProvider(name = "searchData")
    public Object[][] getSearchData() {
        String sheetName = "Sheet1";
        List<Object[]> data = ExcelReader.getTestData(FILE_PATH, sheetName);
        return data.toArray(new Object[0][]);
    }
    
    @DataProvider(name = "groceryData")
    public Object[][] getGroceryData() {
        String sheetName = "Sheet2";
        List<Object[]> data = ExcelReader.getTestData(FILE_PATH, sheetName);
        return data.toArray(new Object[0][]);
    }
    
    
}
