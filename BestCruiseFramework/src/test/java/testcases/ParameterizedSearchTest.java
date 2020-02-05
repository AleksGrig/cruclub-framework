package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import enums.By;
import enums.City;
import enums.Country;
import enums.Region;
import pages.actions.HomePage;

public class ParameterizedSearchTest extends BaseTest {

	@BeforeMethod
	public void prepare() {
		driver.get("https://www.cruclub.ru/");
	}

	@Test
	public void parameterizedSearchTest() {
		double priceLimit = 70000;
		HomePage home = new HomePage(driver);
		if (home.searchCruises(Region.parseString("BritishIslands"), Country.parseString("GreatBritain"), City.Genoa,
				"31 декабрь").sort(By.Price).isCheaper(priceLimit)) {
			Assert.fail("There is cruise cheaper than " + priceLimit);
		}
	}
}
