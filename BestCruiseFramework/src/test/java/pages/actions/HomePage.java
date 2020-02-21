package pages.actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;

import builders.Cruise;
import enums.City;
import enums.Country;
import enums.Region;
import pages.locators.HomePageLocators;
import testcases.BaseTest;

public class HomePage extends BasePage {

	private HomePageLocators home = new HomePageLocators();

	public HomePage() {
		PageFactory.initElements(factory, this.home);
		BaseTest.getDriver().get("https://www.cruclub.ru/");
	}

	public SearchPage findCruise() {
		return findCruise(Cruise.getBuilder().build());
	}

	public SearchPage findCruise(Cruise cruise) {
		chooseRegion(cruise.getRegion());
		chooseCountry(cruise.getCountry());
		chooseDeparturePort(cruise.getDeparturePort());
		chooseBeforeDate(cruise.getDate());
		chooseMinLength(cruise.getMinCruiseLength());
		checkNumberOfCruises();
		home.submitCruiseOptionsButton.click();
		return new SearchPage();
	}

	private void checkNumberOfCruises() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (home.numberOfCruises.getText().strip().equalsIgnoreCase("0")) {
			throw new SkipException("No Cruises found!");
		}
	}

	private void chooseBeforeDate(String beforeDate) {
		String[] dayAndMonth = beforeDate.split(" ");
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

	private void chooseDeparturePort(City departurePort) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		wait.until(ExpectedConditions.elementToBeClickable(home.depaturePortLink));
		home.depaturePortLink.click();
		switch (departurePort) {
		case Rome:
			wait.until(ExpectedConditions.elementToBeClickable(home.Rome));
			home.Rome.click();
			break;
		case Genoa:
			wait.until(ExpectedConditions.elementToBeClickable(home.Genoa));
			home.Genoa.click();
			break;
		case Savona:
			wait.until(ExpectedConditions.elementToBeClickable(home.Rome));
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
		Actions action = new Actions(BaseTest.getDriver());
		for (int i = 0; i < minCruiseLength; i++) {
			action.sendKeys(Keys.ARROW_RIGHT).perform();
		}
		home.numberOfDaysLink.click();
	}
}
