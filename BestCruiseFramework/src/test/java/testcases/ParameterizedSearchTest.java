package testcases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import builders.Cruise;
import enums.Country;
import enums.Port;
import enums.Region;
import pages.actions.HomePage;
import utilities.DataProviders;

public class ParameterizedSearchTest extends BaseTest {

	@Test(dataProviderClass = DataProviders.class, dataProvider = "dp")
	public void parameterizedSearchTest(HashMap<String, String> data) {
		Cruise cruise = Cruise.getBuilder()
				.withDestinationRegion(Region.valueOf(data.get("destinationRegion")))
				.withDestinationCountry(Country.valueOf(data.get("destinationCountry")))
				.withInitialPort(Port.valueOf(data.get("departurePort")))
				.withMinCruiseLength((int) Double.parseDouble(data.get("minCruiseLength")))
				.withDate(data.get("beforeDate"))
				.build();
		double priceLimit = Double.parseDouble(data.get("priceLimit"));
		
		if (HomePage.load().findCruise(cruise).isCheaperThan(priceLimit)) {
			Assert.fail();
		}
	}
}
