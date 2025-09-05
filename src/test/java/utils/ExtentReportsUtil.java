package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsUtil {
    public static ExtentReports extent;
    private static boolean isConfigured = false;

    // Set output file path
    private static final String REPORT_PATH = "reports/Report.html";

    // Initialize and configure the report (singleton-style)
    public static void configureExtentReports() {
        if (!isConfigured) {
            ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_PATH);
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("Automation Test Report");
            spark.config().setReportName("Test Execution Results");
            spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Framework", "Appium TestNG");
            extent.setSystemInfo("Environment", "Staging");

            isConfigured = true;
        }
    }

    public static ExtentReports getExtentReports() {
        if (!isConfigured) {
            configureExtentReports();
        }
        return extent;
    }
}
