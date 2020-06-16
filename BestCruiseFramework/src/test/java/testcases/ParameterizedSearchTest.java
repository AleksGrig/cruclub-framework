package testcases;

import java.util.HashMap;

import org.testng.annotations.Test;

import builders.Cruise;
import enums.By;
import pages.actions.HomePage;
import utilities.DataProviders;

public class ParameterizedSearchTest extends BaseTest {

	@Test(dataProviderClass = DataProviders.class, dataProvider = "dp")
	public void parameterizedSearchTest(HashMap<String, String> data) {
		HomePage.load()
				.findCruises(Cruise.build(cruise -> cruise
						.withDestinationRegion(data.get("destinationRegion"))
						.withDestinationCountry(data.get("destinationCountry"))
						.withDeparturePort(data.get("departurePort"))
						.withMinCruiseLength(data.get("minCruiseLength"))
						.withBeforeDate(data.get("beforeDate"))))
				.sort(By.Price)
				.loadFirst()
				.isCheaperThan(data.get("priceLimit")); 
	}
}
