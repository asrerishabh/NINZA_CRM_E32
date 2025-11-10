package genericutilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener, ISuiteListener {
	public ExtentReports report;
	public ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		JavaUtility jLib = new JavaUtility();
		ExtentSparkReporter spark = new ExtentSparkReporter(
				"./ExtentReports/report_" + jLib.getCurrentDateAndTime() + ".html");
		spark.config().setDocumentTitle("CRM Report");
		spark.config().setReportName("NINZA_CRM Report");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Window");
		report.setSystemInfo("BROWSER", "chrome");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testCaseName = result.getMethod().getMethodName();
		ExtentTest test = report.createTest(testCaseName);
		test.log(Status.INFO, testCaseName + "  Execusion started");
		// System.out.println(testCaseName+" Execusion started");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testCaseName = result.getMethod().getMethodName();
		System.out.println(testCaseName + " Execusion Success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testCaseName = result.getMethod().getMethodName();
		test.log(Status.FAIL, testCaseName + " Execusion Failed");
		// System.out.println(testCaseName+" Execusion Failed");
		JavaUtility jLib = new JavaUtility();
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		String src = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src);
	}
	// change in SS due to extentreport--it didnt accept FILE formate;

	@Override
	public void onTestSkipped(ITestResult result) {

		String testCaseName = result.getMethod().getMethodName();
		test.log(Status.SKIP, testCaseName + " Execusion Skipped");
		// System.out.println(testCaseName+" Execusion Skipped");
	}

}
