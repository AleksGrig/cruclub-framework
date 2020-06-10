package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPageLocators {

	@FindBy(id = "drpSortBy")
	public WebElement sortDrop;
	@FindBy(xpath = "//a[@data-sbv='price']")
	public WebElement minPriceDrop;
	@FindBy(id = "ctl00_Content_rptrOffers_ctl01_ctlOffer_ctlPrice_lblPrice")
	public WebElement firstPriceTag;
	@FindBy(id = "ctl00_Content_rptrOffers_ctl01_ctlOffer_hlOffer")
	public WebElement firstCruiseLink;

}
