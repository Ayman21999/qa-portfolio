package tests;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import utils.BaseTest;

public class CartTest extends BaseTest {

    LoginPage loginPage;
    CartPage cartPage;
    
    
    @BeforeMethod
    public void setUpCart() {
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        
    }
    @Test
    public void addItemTestToCart() {
    	cartPage.addFirstItemToCart();
    	cartPage.goToCart();
    assertEquals(cartPage.getCartItemCount(),1, "Cart should have 1 item!"	);	
    }
    
    @Test
    public void removeitemTestFormCart() {
    	cartPage.getCartItemCount();
    	cartPage.goToCart();
    	cartPage.removeItemFromCart();
        Assert.assertEquals(cartPage.getCartItemCount(), 0, "Cart should be empty!");

    }
    
    
    
}
