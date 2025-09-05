package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
public class ProductsPage {
    private final AndroidDriver driver;

    public ProductsPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void addFirstProductToCart() {
        driver.findElement(By.xpath("(//android.view.ViewGroup[@content-desc='test-ADD TO CART'])[1]")).click();
    }

    public void openCart() {
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView")).click();
    }
}
