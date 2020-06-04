package pages.actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import builders.Cruise;
import enums.Country;
import enums.Port;
import enums.Region;
import pages.locators.HomePageLocators;
import testcases.BaseTest;

public class HomePage {

	private HomePageLocators home = new HomePageLocators();	
	private WebDriverWait wait;
	private AjaxElementLocatorFactory factory;

	public HomePage() {
		factory = new AjaxElementLocatorFactory(BaseTest.getDriver(), 15);
		wait = new WebDriverWait(BaseTest.getDriver(), 15);
		PageFactory.initElements(factory, home);
		BaseTest.getDriver().get("https://www.cruclub.ru/");
	}
	
	public static HomePage load() {
		return new HomePage();
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
		// Thread.sleep(500);
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
		dates.stream()
			.filter(e -> e.getText().equals(date))
			.filter(e -> e.getAttribute("class").contains("available"))
			.findFirst()
			.ifPresent(WebElement::click);
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

	private void chooseDeparturePort(Port departurePort) {
		wait.until(ExpectedConditions.elementToBeClickable(home.depaturePortLink));
		home.depaturePortLink.click();
		
		switch (departurePort) {
		case Rome:
			home.Rome.click();
			break;
		case Genoa:
			home.Genoa.click();
			break;
		case Savona:
			home.Savona.click();
			break;
		case Marseille:
			home.Marseille.click();
			break;
		case AnyCity:
		default:
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
