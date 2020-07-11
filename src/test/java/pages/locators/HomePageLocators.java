package pages.locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class HomePageLocators {

	// @FindBys ({ @FindBy, @FindBy}) - finding element inside another element
	// @FindAll({@FindBy, @FindBy}) - finding element with both attributes

	@FindAll({ @FindBy(name = "ctl24$Login$UserName"), @FindBy(id = "ctl24_Login_UserName") })
	public WebElement userNameField;
	@FindBy(id = "ctl24_Login_Password")
	public WebElement passwordField;
	@FindBy(id = "ctl24_Login_LoginButton")
	public WebElement loginButton;
	@FindBy(id = "ddRegion_link")
	public WebElement regionLink;
	@FindBy(id = "ddRegion_li_0")
	public WebElement anyRegion;
	@FindBy(id = "ddRegion_li_1")
	public WebElement Mediterrenean;
	@FindBy(id = "ddRegion_li_12")
	public WebElement BritishIslands;
	@FindBy(id = "ddRegion_li_10")
	public WebElement Africa;
	@FindBy(id = "ddRegion_li_5")
	public WebElement NearEast;
	@FindBy(id = "ddCountry_link")
	public WebElement countryLink;
	@FindBy(id = "ddCountry_li_0")
	public WebElement anyCountry;
	@FindBy(id = "ddCountry_li_1")
	public WebElement GreatBritain;
	@FindBy(id = "ddCountry_li_2")
	public WebElement Israel;
	@FindBy(id = "rpDuration")
	public WebElement numberOfDaysLink;
	@FindBy(css = ".ui-slider-handle.ui-state-default.ui-corner-all")
	public WebElement daySlider;
	@FindBy(id = "drpStartDate")
	public WebElement calendarLink;
	@FindBy(xpath = "//div[@class='calendar first right']/div/table/tbody")
	public WebElement dateBlock;
	@FindBys({ @FindBy(xpath = "//div[@class='calendar first right']"), @FindBy(xpath = "//th[@class='month']") })
	public WebElement currentMonth;
	@FindBys({ @FindBy(xpath = "//div[@class='calendar first right']"),
			@FindBy(xpath = "//*[@class='next available']") })
	public WebElement next;
	@FindBy(xpath = "//button[@class='applyBtn bs-btn bs-btn-xs bs-btn-primary']")
	public WebElement applyCalendar;
	@FindBy(id = "fltResult")
	public WebElement numberOfCruises;
	@FindBy(id = "btnSubmit")
	public WebElement submitCruiseOptionsButton;
	@FindBy(id = "ddDCity_link")
	public WebElement depaturePortLink;
	@FindBy(id = "ddDCity_li_0")
	public WebElement anyCity;
	@FindBy(xpath = "//li[@data-value='3' and starts-with(@id, 'ddDCity')]")
	public WebElement Genoa;
	@FindBy(xpath = "//li[@data-value='15']")
	public WebElement Rome;
	@FindBy(xpath = "//li[@data-value='111']")
	public WebElement Savona;
	@FindBy(xpath = "//li[@data-value='32']")
	public WebElement Marseille;
	@FindBy(css = "span[class='linkselect-link-text']")
	public List<WebElement> cruiseSearchOptions;
}

