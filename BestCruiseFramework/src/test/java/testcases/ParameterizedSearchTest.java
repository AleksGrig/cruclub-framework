package testcases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import enums.By;
import enums.City;
import enums.Country;
import enums.Region;
import pages.actions.HomePage;
import utilities.DataProviders;

public class ParameterizedSearchTest extends BaseTest {

	@Test(dataProviderClass = DataProviders.class, dataProvider = "dp")
	public void parameterizedSearchTest(HashMap<String, String> data) throws InterruptedException {
		checkRunmode(data.get("runmode"));
		getDriver().get("https://www.cruclub.ru/");
		Thread.sleep(1500);

		double priceLimit = Double.parseDouble(data.get("priceLimit"));
		HomePage home = new HomePage(getDriver());
		if (home.searchCruises(Region.parseString(data.get("region")), Country.parseString(data.get("country")),
				City.parseString(data.get("initialPort")), (int) Double.parseDouble(data.get("minCruiseLength")),
				data.get("date")).sort(By.Price).isCheaper(priceLimit)) {
			Assert.fail("There is cruise cheaper than " + priceLimit);
		}
	}
}
