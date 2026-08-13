package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public WebDriver driver;
    public ExtentReports extentReports = ExtentReportManager.getInstace();
    
    
    
    @BeforeMethod
    public void setup(java.lang.reflect.Method method) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        ExtentTest test = extentReports.createTest(method.getName());
        ExtentReportManager.setTest(test);
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentReportManager.getTest().fail(result.getThrowable());
            ScreenshotUtils.TakeScreenshot(driver, result.getName());
        } else {
            ExtentReportManager.getTest().pass("Test Passed!");
        }
        driver.quit();
    }

    @AfterSuite
    public void teardownReport() {
        extentReports.flush();
    }
}