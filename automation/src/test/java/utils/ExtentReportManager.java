package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public static ExtentReports getInstace() {
		if (extent == null) {
			 new java.io.File("reports").mkdirs();
			 ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("reports/TestReport.html");
			extentSparkReporter.config().setReportName("QA Protfoloi Report");
			extentSparkReporter.config().setDocumentTitle("Report Resutl ");
            extent = new ExtentReports();
            extent.attachReporter(extentSparkReporter);
			
		}
		return extent;
	}
	
	public static ExtentTest getTest() {
		return test.get();
		
	}
	public static void setTest(ExtentTest extentTest) {
		test.set(extentTest);
	}
}

