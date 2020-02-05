package pages.actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import enums.City;
import enums.Country;
import enums.Region;
import pages.locators.HomePageLocators;

public class HomePage extends BasePage {

	private pages.locators.HomePageLocators home = new HomePageLocators();
	private static final int MIN_CRUISE_LENGTH = 6;
	private static final String END_SEARCH_DATE = "1 июнь";

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(factory, this.home);
	}

	public SearchPage searchCruises(City initialPort) {
		return searchCruises(Region.AnyRegion, Country.SkipCountry, initialPort, MIN_CRUISE_LENGTH, END_SEARCH_DATE);
	}

	public SearchPage searchCruises(City initialPort, String date) {
		return searchCruises(Region.AnyRegion, Country.SkipCountry, initialPort, MIN_CRUISE_LENGTH, date);
	}

	public SearchPage searchCruises(City initialPort, int minCruiseLength) {
		return searchCruises(Region.AnyRegion, Country.SkipCountry, initialPort, minCruiseLength, END_SEARCH_DATE);
	}

	public SearchPage searchCruises(City initialPort, int minCruiseLength, String date) {
		return searchCruises(Region.AnyRegion, Country.SkipCountry, initialPort, minCruiseLength, date);
	}

	public SearchPage searchCruises(Region region, int minCruiseLength) {
		return searchCruises(region, Country.AnyCountry, City.AnyCity, minCruiseLength, END_SEARCH_DATE);
	}

	public SearchPage searchCruises(Region region, int minCruiseLength, String date) {
		return searchCruises(region, Country.AnyCountry, City.AnyCity, minCruiseLength, date);
	}

	public SearchPage searchCruises(Region region, Country country) {
		return searchCruises(region, country, City.AnyCity, MIN_CRUISE_LENGTH, END_SEARCH_DATE);
	}

	public SearchPage searchCruises(Region region, Country country, String date) {
		return searchCruises(region, country, City.AnyCity, MIN_CRUISE_LENGTH, date);
	}

	public SearchPage searchCruises(Region region, City initialPort) {
		return searchCruises(region, Country.AnyCountry, initialPort, MIN_CRUISE_LENGTH, END_SEARCH_DATE);
	}

	public SearchPage searchCruises(Region region, City initialPort, String date) {
		return searchCruises(region, Country.AnyCountry, initialPort, MIN_CRUISE_LENGTH, date);
	}

	public SearchPage searchCruises(Region region, City initialPort, int minCruiseLength) {
		return searchCruises(region, Country.AnyCountry, initialPort, minCruiseLength, END_SEARCH_DATE);
	}

	public SearchPage searchCruises(Region region, City initialPort, int minCruiseLength, String date) {
		return searchCruises(region, Country.AnyCountry, initialPort, minCruiseLength, date);
	}

	public SearchPage searchCruises(Region region, Country country, City initialPort) {
		return searchCruises(region, country, initialPort, MIN_CRUISE_LENGTH, END_SEARCH_DATE);
	}

	public SearchPage searchCruises(Region region, Country country, City initialPort, String date) {
		return searchCruises(region, country, initialPort, MIN_CRUISE_LENGTH, date);
	}

	public SearchPage searchCruises(Region region, Country country, City initialPort, int minCruiseLength,
			String date) {
		chooseRegion(region);
		chooseCountry(country);
		wait.until(ExpectedConditions.elementToBeClickable(home.initialPortLink));
		chooseInitialPort(initialPort);
		chooseEndDate(date);
		chooseMinLength(minCruiseLength);
		home.submitCruiseOptionsButton.click();
		return new SearchPage(driver);
	}

	private void chooseEndDate(String date) {
		String[] dayAndMonth = date.split(" ");
		home.calendarLink.click();
		chooseMonth(dayAndMonth[1]);
		chooseDay(dayAndMonth[0]);
		home.applyCalendar.click();
	}

	private void chooseDay(String date) {
		List<WebElement> dates = home.dateBlock.findElements(By.tagName("td"));

		for (int i = 0; i < dates.size(); i++) {
			WebElement element = dates.get(i);
			if (element.getText().equals(date)) {
				if (element.getAttribute("class").contains("available")) {
					element.click();
					break;
				}
			}
		}
	}

	private void chooseMonth(String month) {
		while (!home.currentMonth.getText().contains(month)) {
			home.next.click();
		}
	}

	private void chooseRegion(Region region) {
		home.regionLink.click();
		switch (region) {
		case Africa:
			home.Africa.click();
			break;
		case BritishIslands:
			home.BritishIslands.click();
			break;
		case Mediterranean:
			home.Mediterrenean.click();
			break;
		case NearEast:
			home.NearEast.click();
			break;
		case AnyRegion:
		default:
			home.anyRegion.click();
		}
	}

	private void chooseCountry(Country country) {
		if (country != Country.SkipCountry) {
			home.countryLink.click();
			switch (country) {
			case GreatBritain:
				home.GreatBritain.click();
				break;
			case Israel:
				home.Israel.click();
				break;
			case AnyCountry:
			default:
				home.anyCountry.click();
			}
		}
	}

	private void chooseInitialPort(City initialPort) {
		home.initialPortLink.click();
		switch (initialPort) {
		case Rome:
			wait.until(ExpectedConditions.elementToBeClickable(home.Rome));
			home.Rome.click();
			break;
		case Genoa:
			wait.until(ExpectedConditions.elementToBeClickable(home.Genoa));
			home.Genoa.click();
			break;
		case Savona:
			wait.until(ExpectedConditions.elementToBeClickable(home.Savona));
			home.Savona.click();
			break;
		case Marseille:
			wait.until(ExpectedConditions.elementToBeClickable(home.Marseille));
			home.Marseille.click();
			break;
		case AnyCity:
		default:
			wait.until(ExpectedConditions.elementToBeClickable(home.anyCity));
			home.anyCity.click();
		}
	}

	private void chooseMinLength(int minCruiseLength) {
		home.numberOfDaysLink.click();
		home.daySlider.click();
		Actions action = new Actions(driver);
		for (int i = 0; i < minCruiseLength; i++) {
			action.sendKeys(Keys.ARROW_RIGHT).perform();
		}
		home.numberOfDaysLink.click();
	}
}
