package pages.actions;

import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import testcases.BaseTest;

class BasePage {

	WebDriverWait wait;
	AjaxElementLocatorFactory factory;

	BasePage() {
		factory = new AjaxElementLocatorFactory(BaseTest.getDriver(), 10);
		wait = new WebDriverWait(BaseTest.getDriver(), 5);
	}
}
