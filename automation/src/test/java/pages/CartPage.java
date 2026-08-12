package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CartPage {
	
	WebDriver driver; 
	
	By addToCartBtn = By.cssSelector(".btn_inventory");
	By cartIcon  = By.cssSelector(".shopping_cart_link");
	By cartItems  = By.cssSelector(".cart_item");
	By addToCartButton = By.cssSelector(".btn_inventory");
	By removeButton = By.cssSelector(".btn_secondary");
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    

    // Actions
    public void addFirstItemToCart() {
        driver.findElement(addToCartButton).click();
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public void removeItemFromCart() {
        driver.findElement(removeButton).click();
    }

}
