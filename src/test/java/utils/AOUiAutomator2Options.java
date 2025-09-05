package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static utils.Config.get;

public class AOUiAutomator2Options {
    static AndroidDriver driver;
    static AppiumDriverLocalService server;

    // --- Programmatic Appium (optional; uses .env if present) ---
    static void setInstance() {
        String js   = get("APPIUM_JS", null);
        String node = get("NODE_BIN", null);
        if (js == null || node == null) return; // skip programmatic start if not configured

        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withAppiumJS(new File(js))
                .usingDriverExecutable(new File(node))
                .withIPAddress(get("APPIUM_HOST", "127.0.0.1"))
                .usingPort(Integer.parseInt(get("APPIUM_PORT", "4723")))
                .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
                .withLogFile(new File(get("APPIUM_LOG", "appium_logs.txt")));

        server = AppiumDriverLocalService.buildService(builder);
    }

    static AppiumDriverLocalService getInstance() {
        if (server == null) setInstance();
        return server;
    }

    public static void startAppiumServer() {
        if (getInstance() != null && !server.isRunning()) server.start();
    }

    public static void stopAppiumService() {
        if (server != null && server.isRunning()) server.stop();
    }

    public UiAutomator2Options getMyPartnerAppOptionsForAndroid() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setUdid(get("ANDROID_UDID", "emulator-5554"));
        options.setDeviceName(get("ANDROID_DEVICE_NAME", "sdk_gphone64_x86_64"));
        options.setAppPackage("com.swaglabsmobileapp");
        options.setAppActivity("com.swaglabsmobileapp.MainActivity");
        options.setNewCommandTimeout(Duration.ofSeconds(60));
        return options;
    }

    // --- Driver ---
    public static AndroidDriver getNativeAndroidDriver() {
        UiAutomator2Options options = new AOUiAutomator2Options().getMyPartnerAppOptionsForAndroid();

        String host = get("APPIUM_HOST", "127.0.0.1");
        String port = get("APPIUM_PORT", "4723");
        try {
            return new AndroidDriver(new URL("http://" + host + ":" + port + "/"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


}
