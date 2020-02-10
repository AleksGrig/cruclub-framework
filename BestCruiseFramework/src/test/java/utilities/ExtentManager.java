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

import testcases.BaseTest;

public class ExtentManager extends BaseTest {

	private static ExtentReports extent;
	private static String screenshotName;
	private static String screenshotFolder = "reports/";
	private static Integer counter = 1;

	public static String getScreenshotName() {
		return screenshotName;
	}

	public static ExtentReports createInstance(String fileName) {
		if (extent == null) {
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);

			htmlReporter.config().setTheme(Theme.STANDARD);
			htmlReporter.config().setDocumentTitle(fileName);
			htmlReporter.config().setEncoding("utf-8");
			htmlReporter.config().setReportName(fileName);

			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("Automation Tester", "Alex");
			extent.setSystemInfo("Build no", "1.0");
		}
		return extent;
	}

	public static void captureScreenshot(WebDriver driver) {
		Actions action = new Actions(driver);
		for (int i = 0; i < 15; i++) {
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
		}
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		screenshotName = counter.toString() + ".jpg";
		counter++;
		try {
			FileUtils.copyFile(scrFile, new File(screenshotFolder + screenshotName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
