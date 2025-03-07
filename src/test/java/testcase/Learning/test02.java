package testcase.Learning;

import Utils.ScreenshotUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import java.time.Duration;

public class test02 {
    WebDriver driver;
    Utils.loginUtils loginUtils;
    private WebDriverWait wait;
    private ScreenshotUtils screenshotUtils; // Tạo đối tượng ScreenshotUtils

    @BeforeClass
    public void createDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        screenshotUtils = new ScreenshotUtils(driver);
    }

    @BeforeMethod
    public void login() {
        driver.get("https://lms-test.ivyglobalschool.org/");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
        loginUtils.login("dqc_student2",    "123456789");
        wait.until(ExpectedConditions.urlContains("home"));
    }

    @Description("Verify learning content")
    @Story("Verify learning content")
    @Step("Choose a cource and learning with student acct")
    @Test (suiteName = "Test choose course")
    public void choosecourse() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnSubjectCard(); //Chọn course đầu tiên

        By imgLocator = By.xpath("//img[@src='/themes/web/assets/images/icon/btn-play.png']");

        WebElement imgElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(imgLocator));
        imgElement.click();
    }
}
