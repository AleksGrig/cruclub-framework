package testcases;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.DataProviders;

public class SingleCruiseTest extends BaseTest {

	@Test(dataProviderClass = DataProviders.class, dataProvider = "dp")
	public void singleCruiseTest(HashMap<String, String> data) {
		checkRunmode(data.get("runmode"));
		getDriver().get(data.get("link"));
		double price = Double.parseDouble(getDriver().findElement(By.id(data.get("priceTag"))).getText().split(" ")[0]);
		double priceLimit = Double.parseDouble(data.get("priceLimit"));
		if (price < priceLimit) {
			Assert.fail();
		}
	}
}
