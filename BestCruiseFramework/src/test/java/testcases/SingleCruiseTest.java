package testcases;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.DataProviders;

public class SingleCruiseTest extends BaseTest{
	@Test(dataProviderClass = DataProviders.class, dataProvider = "dp")
	public void singleCruiseTest(HashMap<String, String> data) {
		getDriver().get(data.get("link"));
		double cruisePrice = getCruisePrice();
		double priceLimit = Double.parseDouble(data.get("priceLimit"));
		if (cruisePrice < priceLimit) {
			Assert.fail();
		}
	}
	
	private double getCruisePrice() {
		return Double.parseDouble(
				getDriver().findElement(By.id("ctl00_Content_ctlOffer_ctlPrice_lblPrice")).getText().split(" ")[0]);
	}
}
