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
	public void parameterizedSearchTest(HashMap<String, String> data) {
		checkRunmode(data.get("runmode"));
		double priceLimit = Double.parseDouble(data.get("priceLimit"));
		HomePage home = new HomePage();
		if (home.findCruise(Region.parseRegion(data.get("region")), Country.parseCountry(data.get("country")),
				City.parseCity(data.get("departurePort")), (int) Double.parseDouble(data.get("minCruiseLength")),
				data.get("date")).sort(By.Price).cheaperThan(priceLimit)) {
			Assert.fail();
		}
	}
}
