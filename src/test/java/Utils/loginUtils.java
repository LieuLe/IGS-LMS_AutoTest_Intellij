package Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

public class loginUtils {

    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;

    public loginUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.loginPage = new LoginPage(driver);
    }

    // Login method
    public void login(String username, String password) {
        driver.get("https://lms-test.ivyglobalschool.org/");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }
}