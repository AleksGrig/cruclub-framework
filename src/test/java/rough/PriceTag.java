package rough;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PriceTag {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.get(
				"https://www.cruclub.ru/offers/?RegionID=10&CountryID=GB&DCityID=3&DateRange=20200205_20201231&Duration=6_&sb=price&act=scroll");
		System.out.println(
				driver.findElement(By.id("ctl00_Content_rptrOffers_ctl01_ctlOffer_ctlPrice_lblPrice")).getText()
						.split(" ")[0]);
	}

}
