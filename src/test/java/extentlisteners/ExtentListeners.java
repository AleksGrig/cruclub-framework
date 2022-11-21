package extentlisteners;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import testcases.BaseTest;
import utilities.ExtentManager;

@SuppressWarnings("unchecked")
public class ExtentListeners implements ITestListener {

	private static final ExtentReports extentReports = ExtentManager.createInstance("reports/extent.html");
	private static final ThreadLocal<ExtentTest> testReports = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		HashMap<String, String> data = (HashMap<String, String>) result.getParameters()[0];
		ExtentTest test = extentReports
				.createTest(result.getTestClass().getName() + "\n   @TestCase : " + data.get("cruiseName"));
		testReports.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		makeScreenshot(result);
		String logText = "<b>" + "TEST CASE:- " + result.getMethod().getMethodName().toUpperCase() + " PASSED" + "</b>";
		testReports.get().info("<a href='" + BaseTest.getDriver().getCurrentUrl() + "'>Cruise link</a>"
				+ " --- CURRENT PRICE IS: " + getPrice());
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReports.get().pass(m);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String message = null;
		Markup m = null;
		makeScreenshot(result);
		
		if ((message = result.getThrowable().getMessage()) != null && message.equals("Cruise found")) {
			testReports.get().info("<a href='" + BaseTest.getDriver().getCurrentUrl() + "'>Cruise link</a>"
					+ " --- CURRENT PRICE IS: " + getPrice());
			m = MarkupHelper.createLabel("Cruise found", ExtentColor.RED);
			testReports.get().log(Status.FAIL, m);
		} else {
			String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			testReports.get()
					.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
							+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>")
							+ "</details>" + " \n");
			m = MarkupHelper.createLabel("TEST CASE FAILED", ExtentColor.RED);
			testReports.get().log(Status.FAIL, m);
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		makeScreenshot(result);
		testReports.get().skip(result.getThrowable().getMessage());
		String logText = "<b>" + "Test Case:- " + result.getMethod().getMethodName() + " Skipped, message: " + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReports.get().skip(m);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
		if (extentReports != null) {
			extentReports.flush();
		}
	}

	private String getPrice() {
		String cruisePrice = null;
		try {
			cruisePrice = BaseTest.getDriver().findElement(By.id("ctl00_Content_ctlOffer_ctlPrice_lblPrice")).getText()
					.split(" ")[0];
		} catch (Throwable e) {
			cruisePrice = "Not found";
		}
		return cruisePrice;
	}

	private void makeScreenshot(ITestResult result) {
		String screenshotFolder = "reports/";
		try {
			String screenshotName = result.getMethod().getMethodName() + LocalTime.now().getSecond()
					+ LocalTime.now().getNano() + ".jpg";
			ExtentManager.captureScreenshot(BaseTest.getDriver(), screenshotFolder, screenshotName);
			testReports.get().info("Screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotName).build());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
