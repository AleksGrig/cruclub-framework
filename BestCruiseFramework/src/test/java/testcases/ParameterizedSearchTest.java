package testcases;

import java.util.HashMap;

import org.testng.annotations.Test;

import builders.Cruise;
import enums.By;
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
				.withDeparturePort(Port.valueOf(data.get("departurePort")))
				.withMinCruiseLength((int) Double.parseDouble(data.get("minCruiseLength")))
				.withBeforeDate(data.get("beforeDate"))
				.build();
		
		HomePage.load()
				.findCruises(cruise)
				.sort(By.Price)
				.loadFirstCruise()
				.isCheaperThan(Double.parseDouble(data.get("priceLimit"))); 
	}
}
