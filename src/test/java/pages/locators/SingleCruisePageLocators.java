package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SingleCruisePageLocators {

	@FindBy(id = "ctl00_Content_ctlOffer_ctlPrice_lblPrice")
	public WebElement cruisePrice;
}
