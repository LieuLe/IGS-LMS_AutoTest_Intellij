package testcase.Click;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import testcase.Login.loginUtils;
import utils.DriverSetup;
import utils.ScreenshotUtils;

import java.time.Duration;
import java.util.Objects;

public class ClickActions {
    public WebDriver driver;
    public ScreenshotUtils screenshotUtils;
    HomePage homePage;
    loginUtils loginUtils;
    DriverSetup driverSetup; // Declare driver setup utility class
    public WebDriverWait wait;

    @BeforeClass
    public void setup() {
        // Initialize WebDriver and utility classes
        driverSetup = new DriverSetup();
        driver = driverSetup.initializeDriver(); // Initialize driver
        loginUtils = new loginUtils(driver); // Use initialized driver
        screenshotUtils = new ScreenshotUtils(driver); // Screenshot utility
        homePage = new HomePage(driver); // Initialize homepage object
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // WebDriverWait instance
    }

    // Method to login
    public void login() {
        loginUtils.login("dqc_student2", "123456789");
        System.out.println("Login successful!");
    }

    // Method to click on subject card
    public void clickSubjectCard() {
        WebElement subjectCard = wait.until(ExpectedConditions.elementToBeClickable(By.className("subject-card")));
        subjectCard.click();

        // Verify URL for subject page
        wait.until(ExpectedConditions.urlContains("subject"));
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("subject"), "Failed to navigate to subject page.");
        System.out.println("Successfully navigated to subject page.");
        screenshotUtils.captureScreenshot("subject_page");
    }

    // Method to click on lesson package
    public void clickLessonPackage() {
        WebElement lessonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/themes/web/assets/images/icon/btn-play.png']")));
        lessonElement.click();

        // Verify URL for lesson package
        wait.until(ExpectedConditions.urlContains("lesson-package"));
        Assert.assertTrue(driver.getCurrentUrl().contains("lesson-package"), "Failed to navigate to lesson package page.");
        System.out.println("Successfully navigated to lesson package page.");
        screenshotUtils.captureScreenshot("lesson_package_page");
    }

    // Method to click on lesson
    public void clickLesson() {
        WebElement itemElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/themes/web/assets/images/icon/lesson-package/icon-play.png']")));
        itemElement.click();

        // Verify URL for lesson
        wait.until(ExpectedConditions.urlContains("lesson"));
        Assert.assertTrue(driver.getCurrentUrl().contains("lesson"), "Failed to navigate to lesson page.");
        System.out.println("Successfully navigated to lesson page.");
        screenshotUtils.captureScreenshot("lesson_page");
    }

    @Test(priority = 1)
    public void courseAndLessonFlow() throws InterruptedException {
        // Step 1: Login
        login();

        // Step 2: Click on the subject card
        clickSubjectCard();

        // Step 3: Click on the lesson package
        clickLessonPackage();

        // Step 4: Click on the lesson
        clickLesson();

        // Wait for a few seconds to ensure page load
        Thread.sleep(3000);
    }
}