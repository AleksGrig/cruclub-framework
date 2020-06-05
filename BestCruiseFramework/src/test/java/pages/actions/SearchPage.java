package pages.actions;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import enums.By;
import pages.locators.SearchPageLocators;
import testcases.BaseTest;

public class SearchPage {

	private SearchPageLocators searchLocators = new SearchPageLocators();
	private AjaxElementLocatorFactory factory;

	public SearchPage() {
		factory = new AjaxElementLocatorFactory(BaseTest.getDriver(), 15);
		PageFactory.initElements(factory, searchLocators);
	}

	public SearchPage sort(By by) {
		searchLocators.sortDrop.click();
		by.choose(searchLocators);
		return this;
	}

	public boolean isFirstCheaperThan(double priceLimit) {
		double price = Double.parseDouble(searchLocators.firstPriceTag.getText().split(" ")[0]);
		if (price < priceLimit) {
			return true;
		} else {
			return false;
		}
	}
}
