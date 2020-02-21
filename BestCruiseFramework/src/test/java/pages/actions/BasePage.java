package pages.actions;

import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import testcases.BaseTest;

abstract class BasePage {

	WebDriverWait wait;
	AjaxElementLocatorFactory factory;

	BasePage() {
		factory = new AjaxElementLocatorFactory(BaseTest.getDriver(), 15);
		wait = new WebDriverWait(BaseTest.getDriver(), 15);
	}
}
