package base;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.*;
import utils.AOUiAutomator2Options;

import java.net.URL;
import java.time.Duration;
import java.util.Objects;


public class BaseClass {
    protected static AndroidDriver driver;


    @BeforeTest // or @BeforeSuite
    public void startServerOnce() {

    }

    @BeforeMethod
    public void setup() {
        AOUiAutomator2Options.startAppiumServer();
        driver = AOUiAutomator2Options.getNativeAndroidDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }


    @AfterTest // or @AfterSuite
    public void stopServerOnce() {
        AOUiAutomator2Options.stopAppiumService();
    }

    //Method for the swipe
    protected static void swipe(Point start, Point end, Duration duration ){
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(swipe));
    }

    //Method for the scroll
    public void scroll(String pageDirection, double scrollRatio) throws InterruptedException {
        Duration SCROLL_DUR = Duration.ofMillis(300);
        if (scrollRatio < 0 || scrollRatio > 1){
            throw new Error("Scroll distance must be between 0 and 1");

        }

        Dimension size = driver.manage().window().getSize();
        System.out.println("Screen Size =" + size);

        Point midPoint = new Point((int)(size.width * 0.5), (int)(size.height * 0.5));

        System.out.println(midPoint);

        int bottom = midPoint.y + (int)(midPoint.y * scrollRatio); //50 + 25 B
        int top = midPoint.y - (int)(midPoint.y * scrollRatio); // 50 -25 A
        int left = midPoint.x -(int)(midPoint.x * scrollRatio); // 25 - 12.5 M
        int right = midPoint.x + (int)(midPoint.x * scrollRatio); // 25 + 12.5 N

        System.out.println(right);

        if (Objects.equals(pageDirection, "UP")){
            Thread.sleep(2000);
            swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);

        }else if (Objects.equals(pageDirection, "DOWN")){
            Thread.sleep(2000);
            swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
        }

        else if(Objects.equals(pageDirection, "LEFT")){
            Thread.sleep(2000);
            swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DUR);

        }else{
            Thread.sleep(2000);
            swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DUR);

        }

    }



}
