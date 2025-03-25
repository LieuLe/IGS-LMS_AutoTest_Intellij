package testcase.learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.content.PageGAP2;
import testcase.Click.ClickActions; // Import ClickActions
import testcase.Login.loginUtils;
import utils.DriverSetup;
import utils.ScreenshotUtils;

import java.time.Duration;

public class PracticeGAP2Test {
    WebDriver driver;
    ScreenshotUtils screenshotUtils;
    loginUtils loginUtils;
    DriverSetup driverSetup;
    PageGAP2 gap2Page;
    ClickActions clickActions;

    @BeforeClass
    public void setup() {
        // Khởi tạo DriverSetup và các tiện ích
        driverSetup = new DriverSetup();
        driver = driverSetup.initializeDriver(); // Start WebDriver
        screenshotUtils = new ScreenshotUtils(driver); // Initialize ScreenshotUtils

        // Khởi tạo PageGAP2
        gap2Page = new PageGAP2();
        gap2Page.PageElements(driver); // Initialize PageElements

        // Khởi tạo ClickActions
        clickActions = new ClickActions(driver, screenshotUtils, new WebDriverWait(driver, Duration.ofSeconds(15)));
    }
    public void ClickActions(WebDriver driver, ScreenshotUtils screenshotUtils, WebDriverWait wait) {
        // Constructor implementation
    }

    @Test(priority = 1)
    public void testInputAnswerWithClicks() {
        // Step 1: Login using ClickActions
        clickActions.login();

        // Step 2: Click on the subject card
        clickActions.clickSubjectCard();

        // Step 3: Click on the lesson package
        clickActions.clickLessonPackage();

        // Step 4: Click on the lesson
        clickActions.clickLesson();

        // Step 5: Find and click on the button with class="igs-btn small"
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Increase wait duration
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".igs-btn.small")));
            button.click();
            System.out.println("Successfully clicked on the button with class='igs-btn small'.");
            screenshotUtils.captureScreenshot("clicked_igs_btn");
        } catch (Exception e) {
            System.err.println("Failed to click on the button with class='igs-btn small': " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            driverSetup.tearDown(); // Clean up resources if implemented in DriverSetup
            if (driver != null) {
                driver.quit(); // Quit WebDriver
                System.out.println("Test completed and browser closed.");
            }
        } catch (Exception e) {
            System.err.println("Error during teardown: " + e.getMessage());
        }
    }
}