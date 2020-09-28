package pages.actions;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import pages.locators.SingleCruisePageLocators;
import testcases.BaseTest;

public class SingleCruisePage {

	private final SingleCruisePageLocators singleCruiseLocators = new SingleCruisePageLocators();

	private SingleCruisePage() {
		PageFactory.initElements(new AjaxElementLocatorFactory(BaseTest.getDriver(), 15), singleCruiseLocators);
	}

	public static SingleCruisePage load() {
		return new SingleCruisePage();
	}

	public static SingleCruisePage load(String url) {
		BaseTest.getDriver().get(url);
		return new SingleCruisePage();
	}

	public void isCheaperThan(String priceLimit) {
		double price = Double.parseDouble(singleCruiseLocators.cruisePrice.getText().split(" ")[0]);
		if (price <= Double.parseDouble(priceLimit))
			Assert.fail("Cruise found");
	}
}
