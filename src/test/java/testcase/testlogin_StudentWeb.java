package testcase;
import config.ExcelUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

import java.io.IOException;
import java.util.List;
import java.time.Duration;

public class testlogin_StudentWeb {
    WebDriver driver;
    LoginPage loginPage;
    private WebDriverWait wait;

    @BeforeClass
    public void createDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        List<Object[]> testData = ExcelUtils.getTestData("D:/Automation Test/IGS-LMS_AutoTest_Intellij/src/test/java/resources/login_data.xlsx");
        return testData.toArray(new Object[testData.size()][]);
        // username, password, expectedErrorMessage
    }

    @Test (dataProvider = "loginData")
    public void testLogin(String username, String password, String expectedErrorMessage){
        driver.get("http://igs-web-test.edunext.edu.vn/");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        // Thêm thời gian chờ, bắt ngoại lệ InterruptedException
        try {
            Thread.sleep(3000); // Chờ 3 giây
        } catch (InterruptedException e) {
            e.printStackTrace(); // In ra lỗi nếu có
        }

        if (!expectedErrorMessage.isEmpty()) {
            String actualErrorMessage = loginPage.getLoginError();
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

        } else {
            // Assert that login is successful, e.g., check for a new page or element
            Assert.assertTrue(driver.getCurrentUrl().contains("home"));
        }
    }

    @AfterClass
    public void closeDriver() {
        if (driver != null) {
           driver.quit();
        }
    }
}
