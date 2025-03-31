package testcase.learning;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ModulePage;
import pages.LessonPackPage;
import pages.ItemPractivePage;
import pages.content.GAP2Page;
import testcase.Login.loginUtils;
import utils.DriverSetup;
import utils.ScreenshotUtils;

import java.util.Random;

@Slf4j
public class PracticeGAP2 {
    private WebDriver driver; // Class-level driver
    private ScreenshotUtils screenshotUtils;
    private loginUtils loginUtils;
    private DriverSetup driverSetup;
    private HomePage homePage;
    private ModulePage modulePage;
    private LessonPackPage lessonPackPage;
    private ItemPractivePage itemPractivePage;
    private GAP2Page gap2Page;

    @BeforeClass
    public void setup() {
        // Initialize DriverSetup and start WebDriver
        driverSetup = new DriverSetup();
        driver = driverSetup.initializeDriver();

        // Initialize utilities and page objects
        screenshotUtils = new ScreenshotUtils(driver);
        loginUtils = new loginUtils(driver);
        homePage = new HomePage(driver);
        modulePage = new ModulePage(driver);
        lessonPackPage = new LessonPackPage(driver);
        itemPractivePage = new ItemPractivePage(driver);

        // Initialize PageGAP2
        gap2Page = new GAP2Page(driver);

        System.out.println("Setup completed successfully!");
    }

    @SneakyThrows
    @Test(priority = 1)
    public void testInputAnswerWithClicks() {
        try {
            // Perform login
            loginUtils.login("dqc_student2", "123456789");
            System.out.println("Login successful!");

            // Interact with the subject card
            homePage.clickOnSubjectCard();

            // Wait for navigation and interact with the lesson
            modulePage.clickOnLesson();

            // Wait for the next step and interact with the item
            lessonPackPage.clickOnItem();

            // Wait for the next step and interact with the practice item
            itemPractivePage.clickNextItem();

            // Wait before interacting with the GAP2 page
            Thread.sleep(5000);

            // Generate random 10-character string
            String randomString = generateRandomString(10);

            // Scroll to the inputAnswer element using JavascriptExecutor
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            WebElement inputAnswer = gap2Page.getInputAnswerElement();
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", inputAnswer);

            // Enter random string into the input field
            inputAnswer.sendKeys(randomString);
            System.out.println("Entered random text: " + randomString);

            // Take a screenshot
            screenshotUtils.captureScreenshot("entered_random_text");

        } catch (Exception e) {
            System.err.println("An error occurred during the test: " + e.getMessage());
        }
    }

    // Helper method to generate random string
    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int index = 0; index < length; index++) {
            randomString.append(characters.charAt(random.nextInt(characters.length())));
        }
        return randomString.toString();
    }

    @AfterClass
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit(); // Quit WebDriver
                System.out.println("Test completed and browser closed.");
            }
        } catch (Exception e) {
            System.err.println("Error during teardown: " + e.getMessage());
        }
    }
}