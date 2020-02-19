package testcases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import builders.Cruise;
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
		Cruise cruise = Cruise.getBuilder().withRegion(Region.valueOf(data.get("region")))
				.withCountry(Country.valueOf(data.get("country"))).withCity(City.valueOf(data.get("departurePort")))
				.withMinCruiseLength((int) Double.parseDouble(data.get("minCruiseLength"))).withDate(data.get("date"))
				.build();
		if (home.findCruise(cruise).sort(By.Price).cheaperThan(priceLimit)) {
			Assert.fail();
		}
	}
}
