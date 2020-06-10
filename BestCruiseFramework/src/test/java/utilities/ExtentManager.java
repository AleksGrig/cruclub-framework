package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);

		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(htmlReporter);
		extentReports.setSystemInfo("Automation Tester", "Alex");
		extentReports.setSystemInfo("Build no", "1.0");
		return extentReports;
	}

	public static void captureScreenshot(WebDriver driver, String screenshotFolder, String screenshotName) {
		Actions action = new Actions(driver);
		for (int i = 0; i < 15; i++) {
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
		}
//		String cruiseInfoLocator = ".win.green";
//		WebElement element = driver.findElement(By.cssSelector(cruiseInfoLocator));
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(scrFile, new File(screenshotFolder + screenshotName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
