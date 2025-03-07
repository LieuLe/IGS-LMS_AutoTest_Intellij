package testcase.Learning;

import Utils.ScreenshotUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import Utils.loginUtils;

import java.time.Duration;

public class test {
    WebDriver driver;
    Utils.loginUtils loginUtils;
    ScreenshotUtils screenshotUtils;
    HomePage homePage;

    WebElement subjectCard;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginUtils = new loginUtils(driver);


        screenshotUtils = new ScreenshotUtils(driver);
    }

    @Test
    public void homeCourseTest() {
        // Reuse the login method
        loginUtils.login("dqc_student2", "123456789");

        // Add assertions or further steps for the HomeCourse test
        // Example: Check if the home page/course page is loaded
        // screenshotUtils.captureScreenshot("HomeCourse_Screenshot");

        // Step 2: Click the subject card using homePage
        HomePage homePage = new HomePage(driver);
        homePage.clickOnSubjectCard();

        By imgLocator = By.xpath("//img[@src='/themes/web/assets/images/icon/btn-play.png']");

        WebElement imgElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(imgLocator));
        imgElement.click();








    }

}
