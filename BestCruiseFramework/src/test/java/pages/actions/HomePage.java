package pages.actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.SkipException;

import builders.Cruise;
import enums.Country;
import enums.Port;
import enums.Region;
import pages.locators.HomePageLocators;
import testcases.BaseTest;

public class HomePage {

	private HomePageLocators homeLocators = new HomePageLocators();	
	private AjaxElementLocatorFactory factory;

	public HomePage() {
		factory = new AjaxElementLocatorFactory(BaseTest.getDriver(), 15);
		PageFactory.initElements(factory, homeLocators);
		BaseTest.getDriver().get("https://www.cruclub.ru/");
	}
	
	public static HomePage load() {
		return new HomePage();
	}

	public SearchPage findCruises() {
		return findCruises(Cruise.getBuilder().build());
	}

	public SearchPage findCruises(Cruise cruise) {
		chooseRegion(cruise.getRegion());
		chooseCountry(cruise.getCountry());
		chooseDeparturePort(cruise.getDeparturePort());
		chooseBeforeDate(cruise.getDate());
		chooseMinLength(cruise.getMinCruiseLength());
		checkNumberOfCruises();
		homeLocators.submitCruiseOptionsButton.click();
		return new SearchPage();
	}

	private void checkNumberOfCruises() {
		if (homeLocators.numberOfCruises.getText().strip().equalsIgnoreCase("0")) {
			throw new SkipException("No Cruises found!");
		}
	}

	private void chooseBeforeDate(String beforeDate) {
		String[] dayAndMonth = beforeDate.split(" ");
		homeLocators.calendarLink.click();
		chooseMonth(dayAndMonth[1]);
		chooseDay(dayAndMonth[0]);
		homeLocators.applyCalendar.click();
	}

	private void chooseDay(String date) {
		List<WebElement> dates = homeLocators.dateBlock.findElements(By.tagName("td"));		
		dates.stream()
			.filter(e -> e.getText().equals(date))
			.filter(e -> e.getAttribute("class").contains("available"))
			.findFirst()
			.ifPresent(WebElement::click);
	}

	private void chooseMonth(String month) {
		while (!homeLocators.currentMonth.getText().contains(month)) {
			homeLocators.next.click();
		}
	}

	private void chooseRegion(Region region) {
		homeLocators.regionLink.click();
		region.choose(homeLocators);
	}

	private void chooseCountry(Country country) {
			homeLocators.countryLink.click();
			country.choose(homeLocators);
	}

	private void chooseDeparturePort(Port departurePort) {
		homeLocators.depaturePortLink.click();
		departurePort.choose(homeLocators);
	}

	private void chooseMinLength(int minCruiseLength) {
		homeLocators.numberOfDaysLink.click();
		homeLocators.daySlider.click();
		Actions action = new Actions(BaseTest.getDriver());
		for (int i = 0; i < minCruiseLength; i++) {
			action.sendKeys(Keys.ARROW_RIGHT).perform();
		}
		homeLocators.numberOfDaysLink.click();
	}
}
