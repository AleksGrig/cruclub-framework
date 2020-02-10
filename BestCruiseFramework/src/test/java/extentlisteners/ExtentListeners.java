package extentlisteners;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
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

public class ExtentListeners implements ITestListener {

	private static String fileName = "extent.html";
	private static ExtentReports extent = ExtentManager
			.createInstance(System.getProperty("user.dir") + "\\reports\\" + fileName);
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		@SuppressWarnings("unchecked")
		HashMap<String, String> data = (HashMap<String, String>) result.getParameters()[0];

		ExtentTest test = extent
				.createTest(result.getTestClass().getName() + "\n   @TestCase : " + data.get("cruiseName"));
		testReport.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		testReport.get().pass("<a href='" + BaseTest.getDriver().getCurrentUrl() + "'>Cruises found</a>");
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = BaseTest.getDriver();
		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		testReport.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
						+ " \n");

		try {
			ExtentManager.captureScreenshot(driver);
			testReport.get().fail("Screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.getScreenshotName()).build());
			testReport.get().fail("<a href='" + BaseTest.getDriver().getCurrentUrl() + "'>Screenshot Link</a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String failureLogg = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		testReport.get().skip(result.getThrowable().getMessage());
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped, message: " + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);
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
		if (extent != null) {
			extent.flush();
		}
	}
}
