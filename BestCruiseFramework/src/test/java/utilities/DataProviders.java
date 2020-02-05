package utilities;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.annotations.DataProvider;

public class DataProviders {
	private static ExcelReader excel = new ExcelReader("src/test/resources/excel/testdata.xlsx");

	@DataProvider(name = "dp")
	public static Object[][] getData(Method method) {
		String sheetName = method.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rows - 1][1];
		HashMap<String, String> map = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			map = new HashMap<>();
			for (int colNum = 0; colNum < cols; colNum++) {
				map.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
			}
			data[rowNum - 2][0] = map;
		}
		return data;
	}
}
