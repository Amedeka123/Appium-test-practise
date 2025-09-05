package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ConfirmationPage {
    private final AndroidDriver driver;

    public ConfirmationPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public String getThankYouMessage() {
        return driver.findElement(By.xpath("//android.widget.TextView[@text=\"THANK YOU FOR YOU ORDER\"]")).getText();
    }

    public void goBackHome() {
        driver.findElement(AppiumBy.accessibilityId("test-BACK HOME")).click();
    }
}
