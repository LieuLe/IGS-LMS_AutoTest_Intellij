package testcase.learning;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ModulePage;
import pages.LessonPackPage;
import pages.ItemPractivePage;
import pages.content.GAP2Page;
import utils.loginUtils;
import utils.DriverSetup;
import utils.ScreenshotUtils;

import java.time.Duration;
import java.util.Random;

@Slf4j
public class PracticeGAP2 {
    private WebDriver driver; // Class-level driver
    private ScreenshotUtils screenshotUtils;
    private loginUtils loginUtils;
    private DriverSetup driverSetup;
    private HomePage subjectPage;
    private ModulePage modulePage;
    private LessonPackPage lessonPackPage;
    private ItemPractivePage itemPractivePage;
    private GAP2Page gap2Page;

    @BeforeClass
    public void setup() {
        /*try {*/
            // Initialize DriverSetup and start WebDriver
            driverSetup = new DriverSetup();
            driver = driverSetup.initializeDriver();

            // Initialize utilities and page objects
            screenshotUtils = new ScreenshotUtils(driver);
            loginUtils = new loginUtils(driver);
            subjectPage = new HomePage(driver);
            modulePage = new ModulePage(driver);
            lessonPackPage = new LessonPackPage(driver);
            itemPractivePage = new ItemPractivePage(driver);
        /*
            // Initialize PageGAP2
            gap2Page = new GAP2Page(driver);


        } catch (Exception e) {
            log.error("Error during setup: " + e.getMessage());
        }*/
    }


    @Test(priority = 1)
    public void testInputAnswerWithClicks() {

            // Perform login
            loginUtils.login("https://lms-test.ivyglobalschool.org/", "dqc_student2", "123456789");
            log.info("Login successful!");




    }

    // Helper method to generate random string


    @AfterClass
    public void tearDown() {
        driverSetup.tearDown(); // Quit the driver
    }
}