package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class CheckoutPage {
    private final AndroidDriver driver;

    public CheckoutPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void enterCustomerInfo(String firstName, String lastName, String zipCode) {
        driver.findElement(AppiumBy.accessibilityId("test-First Name")).sendKeys(firstName);
        driver.findElement(AppiumBy.accessibilityId("test-Last Name")).sendKeys(lastName);
        driver.findElement(AppiumBy.accessibilityId("test-Zip/Postal Code")).sendKeys(zipCode);
        driver.findElement(AppiumBy.accessibilityId("test-CONTINUE")).click();
    }

    public String getCheckoutOverviewTitle() {
        return driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"CHECKOUT: OVERVIEW\"]")).getText();
    }

    public void finishCheckout() {
        driver.findElement(AppiumBy.accessibilityId("test-FINISH")).click();
    }

}
