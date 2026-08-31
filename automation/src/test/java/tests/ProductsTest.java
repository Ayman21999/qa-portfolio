package tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import utils.BaseTest;

public class ProductsTest extends BaseTest {

	LoginPage loginPage;
	ProductPage page;
	
	@BeforeMethod
	public void setupProducts(java.lang.reflect.Method method) {
		driver.get("https://www.saucedemo.com");
		loginPage = new LoginPage(driver);
		page = new ProductPage(driver);
		loginPage.login("standard_user", "secret_sauce");
	};
	
	@Test(groups = {"smoke", "regression"})
	public void testProductCount() {
		
		int count =page.getProductCount();
		Assert.assertEquals(count , 6, "Should have 6 products !! ");
		
	}
	@Test(groups = {"regression"})
	public void testSortByPriceLowToHigh() {
		page.sortByPriceLowToHigh();
	    String firstPrice = page.getFirstProductPrice();
	    System.out.println("First price after sort: " + firstPrice);
	    Assert.assertNotNull(firstPrice, "Price should not be null!");
	    Assert.assertFalse(firstPrice.isEmpty(), "Price should not be empty!");
	}
	@Test(groups = {"regression"})
	public void testFirstProductName() {
		String nameFirstProduct  = page.getFirstProductName();
		Assert.assertNotNull(nameFirstProduct," Prosduct name should not be null");
		Assert.assertFalse(nameFirstProduct.isEmpty(),"The product should has a name");
	}
}
