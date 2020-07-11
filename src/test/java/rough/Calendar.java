package rough;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Calendar {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.cruclub.ru/");

		String endMonth = "июнь";
		String endDate = "15";

		// Open calendar
		driver.findElement(By.id("drpStartDate")).click();

		// Choose end month and then day in right calendar
		String calendar = "//div[@class='calendar first right']";
		chooseMonth(endMonth, calendar);
		Thread.sleep(2000);
		chooseDay(endDate, calendar);
		Thread.sleep(2000);

		// Press apply button
		driver.findElement(By.xpath("//button[@class='applyBtn bs-btn bs-btn-xs bs-btn-primary']")).click();
	}

	private static void chooseDay(String date, String calendar) {
		String dateBlock = calendar + "/div/table/tbody";
		WebElement dateBlockCalendar = driver.findElement(By.xpath(dateBlock));
		List<WebElement> dates = dateBlockCalendar.findElements(By.tagName("td"));

		for (int i = 0; i < dates.size(); i++) {
			WebElement element = dates.get(i);
			if (element.getText().equals(date)) {
				if (element.getAttribute("class").contains("available")) {
					element.click();
					break;
				}
			}
		}
	}

	private static void chooseMonth(String month, String calendar) {
		String currentMonth = calendar + " " + "//th[@class='month']";
		String next = calendar + " " + "//*[@class='next available']";
		while (!driver.findElement(By.xpath(currentMonth)).getText().contains(month)) {
			driver.findElement(By.xpath(next)).click();
		}
	}
}
