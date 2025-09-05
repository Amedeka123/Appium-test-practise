package tests;

import base.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.ExtentReportsUtil;

import static utils.ExtentReportsUtil.extent;

public class SampleTest extends BaseClass {


    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    ConfirmationPage confirmationPage;


    private ExtentReports extent;

    @BeforeMethod
    public void setUpTestPages() {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        confirmationPage = new ConfirmationPage(driver);
    }


    @BeforeClass
    public void setUp() {
        // Initialize ExtentReports
        ExtentReportsUtil.configureExtentReports();
        extent = ExtentReportsUtil.getExtentReports();

    }

    @AfterMethod
    public void tearDownTest() {
        if (extent != null) {
            extent.flush(); // Flush after each test for real-time reporting
        }
    }

    @Test
    public void checkOut() throws InterruptedException {
        extent.createTest("Verify that user can checkout successfully").assignAuthor("Israel").assignCategory("Functional Test");
        loginPage.login("standard_user", "secret_sauce");

        productsPage.addFirstProductToCart();
        productsPage.openCart();

        String firstItem = cartPage.getFirstItemName();
        Assert.assertEquals(firstItem, "Sauce Labs Backpack");

        cartPage.clickCheckout();
        checkoutPage.enterCustomerInfo("Israel", "Amedeka", "123456");

        Assert.assertEquals(checkoutPage.getCheckoutOverviewTitle(), "CHECKOUT: OVERVIEW");

        scroll("DOWN",0.5);
        checkoutPage.finishCheckout();

        String thanksMessage = confirmationPage.getThankYouMessage();
        Assert.assertEquals(thanksMessage, "THANK YOU FOR YOU ORDER");

        confirmationPage.goBackHome();
    }


}
