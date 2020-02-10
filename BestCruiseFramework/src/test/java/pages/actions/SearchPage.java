package pages.actions;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import enums.By;
import pages.locators.SearchPageLocators;

public class SearchPage extends BasePage {

	private SearchPageLocators search = new SearchPageLocators();

	public SearchPage() {
		PageFactory.initElements(factory, this.search);
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

	public boolean cheaperThan(double priceLimit) {
		double price = Double.parseDouble(search.firstPriceTag.getText().split(" ")[0]);
		if (price < priceLimit) {
			return true;
		} else {
			return false;
		}
	}
}
