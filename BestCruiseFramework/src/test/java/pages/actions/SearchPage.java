package pages.actions;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import enums.By;
import pages.locators.SearchPageLocators;
import testcases.BaseTest;

public class SearchPage {

	private SearchPageLocators search = new SearchPageLocators();
	private WebDriverWait wait;
	private AjaxElementLocatorFactory factory;

	public SearchPage() {
		factory = new AjaxElementLocatorFactory(BaseTest.getDriver(), 15);
		wait = new WebDriverWait(BaseTest.getDriver(), 15);
		PageFactory.initElements(factory, search);
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

	public boolean isCheaperThan(double priceLimit) {
		sort(By.Price);
		double price = Double.parseDouble(search.firstPriceTag.getText().split(" ")[0]);
		if (price < priceLimit) {
			return true;
		} else {
			return false;
		}
	}
}
