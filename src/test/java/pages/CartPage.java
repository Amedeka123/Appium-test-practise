package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class CartPage {
    private final AndroidDriver driver;

    public CartPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public String getFirstItemName() {
        return driver.findElement(By.xpath("//android.widget.TextView[@text=\"Sauce Labs Backpack\"]")).getText();
    }

    public void clickCheckout() {
        driver.findElement(AppiumBy.accessibilityId("test-CHECKOUT")).click();
    }
}
