package extentlisteners;

import java.io.IOException;
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
	private static final String priceLocator = "ctl00_Content_ctlOffer_ctlPrice_lblPrice";
	
	@Override
	public void onTestStart(ITestResult result) {
		HashMap<String, String> data = (HashMap<String, String>) result.getParameters()[0];
		ExtentTest test = extentReports.createTest(result.getTestClass().getName() + "\n   @TestCase : " + data.get("cruiseName"));
		testReports.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String cruisePrice = BaseTest.getDriver().findElement(By.id(priceLocator)).getText().split(" ")[0];
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		testReports.get().pass("<a href='" + BaseTest.getDriver().getCurrentUrl() + "'>Cruises found</a>" 
				+ 	" --- CURRENT PRICE IS: " +  cruisePrice);
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReports.get().pass(m);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		HashMap<String, String> data = (HashMap<String, String>) result.getParameters()[0];
		String cruisePrice = BaseTest.getDriver().findElement(By.id(priceLocator)).getText().split(" ")[0];
		try {
			String screenshotFolder = "reports/";
			String screenshotName = data.get("cruiseName") + ".jpg";
			ExtentManager.captureScreenshot(BaseTest.getDriver(), screenshotFolder, screenshotName);
			testReports.get().fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotName).build());
			testReports.get().fail("<a href='" + BaseTest.getDriver().getCurrentUrl() + "'>Screenshot Link</a>" 
					+ 	" --- CURRENT PRICE IS: " +  cruisePrice);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		testReports.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
						+ " \n");
		String failureLogg = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReports.get().log(Status.FAIL, m);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		testReports.get().skip(result.getThrowable().getMessage());
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped, message: " + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReports.get().skip(m);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		if (extentReports != null) {
			extentReports.flush();
		}
	}
}
