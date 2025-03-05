package testcase;

import Utils.ScreenshotUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.LogoutPage;
import java.time.Duration;

public class testlogout_StudentWeb {
    WebDriver driver;
    LoginPage loginPage;
    LogoutPage logoutPage;
    private WebDriverWait wait;
    private ScreenshotUtils screenshotUtils; // Tạo đối tượng ScreenshotUtils

    By popUp_LogOut = By.className("change-password-modal");
    By Menu_profile = By.className("profile-modal");
    By txt_confirmLogout = By.xpath("//*[contains(text(), 'YES, LOG OUT NOW')]");
    By txt_welcome = By.xpath("//*[contains(text(), 'Welcome to LMS')]");

    @BeforeClass
    public void createDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        logoutPage = new LogoutPage(driver);
        // Khởi tạo class ScreenshotUtils
        screenshotUtils = new ScreenshotUtils(driver);
    }

    @BeforeMethod
    public void login() {
        driver.get("https://lms-test.ivyglobalschool.org/");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
        loginPage.enterUsername("dqc_student2");
        loginPage.enterPassword("123456789");
        loginPage.clickLogin();
        wait.until(ExpectedConditions.urlContains("home"));
        //Kiểm tra đăng nhập thành công
        Assert.assertTrue(driver.getCurrentUrl().contains("home"),"Successfully  logged in");
    }

    @Description("Verify that a user can successfully logout")
    @Story("Logout")
    @Step("click profile menu , click logout, verify error message")
    @Test(suiteName = "logout")
    public void testLogout() {
        logoutPage.clickProfile(); //Click vào profile menu
        logoutPage.clickLogout(); //Click vào button logout
        logoutPage.clickYesLogout(); //Click vào yes logout now
        wait.until(ExpectedConditions.visibilityOfElementLocated(txt_welcome));
            if (driver.findElement(txt_welcome).isDisplayed()) {
                System.out.println("successfully logout!");
            } else {
                System.out.println("Logout fail!");
            }

        // Chụp ảnh màn hình
        screenshotUtils.captureScreenshot("testLogout_valid");
    }

    @Description("Verify that a user can successfully logout")
    @Story("Logout")
    @Step("click profile menu , click logout, verify error message")
    @Test(suiteName = "logout")
    public void testNotLogout() {
        logoutPage.clickProfile(); //Click vào profile menu
        logoutPage.clickLogout(); //Click vào button logout
        logoutPage.clickNoLogout(); //Click vào yes logout now
        wait.until(ExpectedConditions.visibilityOfElementLocated(Menu_profile));
        if (driver.findElement(Menu_profile).isDisplayed()) {
            System.out.println("Logout cancelled successfully!");
        } else {
            System.out.println("Testcase Fail!");
        }

        // Chụp ảnh màn hình
        screenshotUtils.captureScreenshot("testLogout_invalid");
    }

    @AfterClass
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
