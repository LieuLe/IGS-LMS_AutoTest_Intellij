package testcase;

import Utils.ExcelUtils;
import Utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import Utils.ScreenshotUtils;

import java.io.IOException;
import java.util.List;
import java.time.Duration;
import io.qameta.allure.*;

public class testlogin_StudentWeb {
    WebDriver driver;
    LoginPage loginPage;
    private WebDriverWait wait;
    private ScreenshotUtils screenshotUtils; // Tạo đối tượng ScreenshotUtils

    @BeforeClass
    public void createDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);

        // Khởi tạo class ScreenshotUtils
        screenshotUtils = new ScreenshotUtils(driver);
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        List<Object[]> testData = ExcelUtils.getTestDataLogin("src\\test\\java\\testdata\\login_data.xlsx");
        return testData.toArray(new Object[testData.size()][]);
        // username, password, expectedErrorMessage
    }

    @Description("Verify that a user can successfully login")
    @Story("Login with test data")
    @Step("Enter username and password, then click login")

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, String expectedErrorMessage) {
        driver.get("https://lms-test.ivyglobalschool.org/");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        // Thêm thời gian chờ
        try {
            Thread.sleep(3000); // Chờ 3 giây
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Kiểm tra kết quả test case và chụp màn hình
        if (!expectedErrorMessage.isEmpty()) {
            String actualErrorMessage = loginPage.getLoginError();
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

            // Chụp màn hình nếu test kiểm tra lỗi
            screenshotUtils.captureScreenshot("Failed_Login_" + username);

        } else {
            // Kiểm tra login thành công
            Assert.assertTrue(driver.getCurrentUrl().contains("home"));

            // Chụp màn hình nếu login thành công
            screenshotUtils.captureScreenshot("Successful_Login_" + username);
        }
    }

    @AfterClass
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}