package pages.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import enums.By;
import pages.locators.SearchPageLocators;

public class SearchPage {

	private WebDriverWait wait;
	private SearchPageLocators search;

	public SearchPage(WebDriver driver) {
		this.search = new SearchPageLocators();
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
		PageFactory.initElements(factory, this.search);
		wait = new WebDriverWait(driver, 5);
	}

	public SearchPage sort(By by) {
		search.sortDrop.click();
		wait.until(ExpectedConditions.elementToBeClickable(search.minPriceDrop));
		switch (by) {
		case Price:
			search.minPriceDrop.click();
			break;
		default:
			search.minPriceDrop.click();
		}
		return this;
	}

	public boolean isCheaper(double priceLimit) {
		double price = Double.parseDouble(search.firstPriceTag.getText().split(" ")[0]);
		if (price < priceLimit) {
			return true;
		} else {
			return false;
		}
	}
}
