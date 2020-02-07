package pages.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

class BasePage {

	WebDriver driver;
	WebDriverWait wait;
	AjaxElementLocatorFactory factory;

	BasePage(WebDriver driver) {
		this.driver = driver;
		factory = new AjaxElementLocatorFactory(driver, 10);
		wait = new WebDriverWait(driver, 5);
	}
}
