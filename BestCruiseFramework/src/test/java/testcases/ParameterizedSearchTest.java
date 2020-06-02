package testcases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import builders.Cruise;
import enums.By;
import enums.Port;
import enums.Country;
import enums.Region;
import pages.actions.HomePage;
import utilities.DataProviders;

public class ParameterizedSearchTest extends BaseTest {

	@Test(dataProviderClass = DataProviders.class, dataProvider = "dp")
	public void parameterizedSearchTest(HashMap<String, String> data) {
		Cruise cruise = Cruise.getBuilder().withRegion(Region.valueOf(data.get("destinationRegion")))
				.withCountry(Country.valueOf(data.get("destinationCountry")))
				.withInitialPort(Port.valueOf(data.get("departurePort")))
				.withMinCruiseLength((int) Double.parseDouble(data.get("minCruiseLength")))
				.withDate(data.get("beforeDate"))
				.build();
		double priceLimit = Double.parseDouble(data.get("priceLimit"));
		HomePage home = new HomePage();
		
		if (home.findCruise(cruise).sort(By.Price).isCheaperThan(priceLimit)) {
			Assert.fail();
		}
	}
}
