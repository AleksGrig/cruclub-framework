package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseTest {

	private static ThreadLocal<WebDriver> localDriver = new ThreadLocal<>();

	void checkRunmode(String runmode) {
		if (!runmode.equalsIgnoreCase("Y")) {
			throw new SkipException("Runmode set to N");
		}
	}

	public static WebDriver getDriver() {
		return localDriver.get();
	}

	@BeforeSuite
	public void systemSetup() {
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
	}

	@BeforeTest
	public void setUp() {
		WebDriver driver = new ChromeDriver();
		localDriver.set(driver);
		localDriver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		localDriver.get().manage().window().maximize();
	}

	@AfterTest
	public void tearDown() {
		localDriver.get().quit();
	}
}
