package testcases;

import java.util.HashMap;

import org.testng.annotations.Test;

import pages.actions.SingleCruisePage;
import utilities.DataProviders;

public class SingleCruiseTest extends BaseTest{
	@Test(dataProviderClass = DataProviders.class, dataProvider = "dp")
	public void singleCruiseTest(HashMap<String, String> data) {
		SingleCruisePage.load(data.get("link"))
			.isCheaperThan(Double.parseDouble(data.get("priceLimit")));
	}
}
