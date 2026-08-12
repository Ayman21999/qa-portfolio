package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
public class BaseTest {
	 public WebDriver driver;
	 
	 
	   @BeforeMethod
	    public void setup() {
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().deleteAllCookies();
	    }
	   
	   @AfterMethod
	    public void teardown(ITestResult result) {
	        if (result.getStatus() == ITestResult.FAILURE) {
	            ScreenshotUtils.TakeScreenshot(driver, result.getName());
	        }
	        driver.quit();
	    }
}
