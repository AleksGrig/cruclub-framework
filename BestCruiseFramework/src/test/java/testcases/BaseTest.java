package testcases;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseTest {

	private static ThreadLocal<WebDriver> localDriver = new ThreadLocal<>();
	private static Properties properties = new Properties();
	
	static {
		try {
			properties.load(new FileReader("properties"));
		} catch (IOException e) {
			e.printStackTrace();
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

	@BeforeMethod
	public void setUp() {
		WebDriver driver;
		if(properties.get("browser").equals("chrome")) {
			driver = new ChromeDriver();
		} else if(properties.get("browser").equals("firefox")){
			driver = new FirefoxDriver();
		} else {
			driver = new FirefoxDriver();
		}
		localDriver.set(driver);
		localDriver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		localDriver.get().manage().window().maximize();
	}

	@AfterMethod
	public void tearDown() {
		localDriver.get().quit();
	}
}
