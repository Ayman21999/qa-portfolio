package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;
import org.openqa.selenium.WebElement;

public class ProductPage {
	 WebDriver driver;

	    By productNames = By.cssSelector(".inventory_item_name");
	    By sortDropdown = By.cssSelector(".product_sort_container");
	    By productPrices = By.className("inventory_item_price");
	    public ProductPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    public int getProductCount() {
	        return driver.findElements(productNames).size();
	    }

	    public String getFirstProductName() {
	        return driver.findElements(productNames).get(0).getText();
	    }

	    public void sortByPriceLowToHigh() {
	        driver.findElement(sortDropdown).click();
	        driver.findElement(By.cssSelector("option[value='lohi']")).click();
	    }

	    public String getFirstProductPrice() {
	        return driver.findElements(productPrices).get(0).getText();
	    }
}
