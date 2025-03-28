package testcase.Login;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ScreenshotUtils;
import utils.loginUtils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class testlogin_StudentWeb_1acct {
    WebDriver driver;
    LoginPage loginPage;
    loginUtils loginUtils;
    private WebDriverWait wait;
    private ScreenshotUtils screenshotUtils; // Tạo đối tượng ScreenshotUtils

    @BeforeClass
    public void createDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        loginUtils = new loginUtils(driver);
        // Khởi tạo class ScreenshotUtils
        screenshotUtils = new ScreenshotUtils(driver);
    }

    @Description("Verify that a user can successfully login")
    @Story("Login with 1 account")
    @Test
    public void testLogin1actt () {
        loginUtils.login("https://lms-test.ivyglobalschool.org/","auto.test03", "12345678");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("home")) {
            Assert.assertTrue(true);
            System.out.println("Successful_Login");
            // Chụp màn hình nếu login thành công
            screenshotUtils.captureScreenshot("Successful_Login_");
        } else {
            Assert.fail("Login fail: " + currentUrl);
            System.out.println("Login Fail");
        }
    }

    @AfterClass
    public void closeDriver() {
        if (driver != null) {
           driver.quit();
        }
    }
}