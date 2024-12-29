package com.nagarro.ExitTest.config;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

	public static List<Object[]> getTestData(String filePath, String sheetName) {
		List<Object[]> testData = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet " + sheetName + " does not exist in file " + filePath);
			}

			int rowCount = sheet.getPhysicalNumberOfRows();
			int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

			for (int i = 1; i < rowCount; i++) { // Skip header row
				Row row = sheet.getRow(i);
				if (row == null)
					continue;

				boolean executionRequired = row.getCell(colCount - 1).getBooleanCellValue(); // Last column for Execution
																							// Required
				if (executionRequired) {
					Object[] data = new Object[colCount - 1]; // Exclude Execution Required column
					for (int j = 0; j < colCount - 1; j++) {
						Cell cell = row.getCell(j);
						data[j] = cell != null ? getCellValue(cell) : "";
					}
					testData.add(data);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return testData;
	}

	private static Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue();
			} else {
				return cell.getNumericCellValue();
			}
		case BOOLEAN:
			return cell.getBooleanCellValue();
		case FORMULA:
			return cell.getCellFormula();
		default:
			return "";
		}
	}
}
