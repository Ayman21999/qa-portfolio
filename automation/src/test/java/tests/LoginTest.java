package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @BeforeMethod
    public void setupLogin(java.lang.reflect.Method method) {
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testValidLogin() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed!");
    }

    @Test
    public void testInvalidLogin() {
        loginPage.login("wrong_user", "wrong_pass");
        Assert.assertTrue(loginPage.getErrorMessage()
            .contains("Username and password do not match"), "Error not shown!");
    }

    @Test
    public void testSuccessfulLogin() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
            "Login failed: URL does not contain inventory.html");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
            {"standard_user", "secret_sauce", true},
            {"locked_out_user", "secret_sauce", false},
            {"wrong_user", "wrong_pass", false}
        };
    }

    @Test(dataProvider = "loginData")
    public void testLoginWithMultipleUsers(String name, String pass, boolean shouldPass) {
        loginPage.login(name, pass);
        if (shouldPass) {
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "Login failed for: " + name);
        } else {
            Assert.assertFalse(driver.getCurrentUrl().contains("inventory"),
                "Should have failed for: " + name);
        }
    }
}