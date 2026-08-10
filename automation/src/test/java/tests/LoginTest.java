package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
public class LoginTest {
    WebDriver driver;
    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
    }

    @Test
    public void testLogin() {
        String title = driver.getTitle();
       driver.findElement(By.id("user-name")).sendKeys("wrong_user");
       driver.findElement(By.id("password")).sendKeys("wrong_user");
       driver.findElement(By.id("login-button")).click();
       
       String error = driver.findElement(By.cssSelector("[data-test='error']")).getText();
       Assert.assertTrue(error.contains("Username and password do not match"), "Error message not shown!");
       	
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}