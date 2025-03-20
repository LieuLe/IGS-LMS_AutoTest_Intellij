package testcase.learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testcase.content.ReadingTextHybrid;
import utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.Item;
import pages.Lesson;
import testcase.Login.loginUtils;
import utils.DriverSetup;

import java.time.Duration;

public class test {
    WebDriver driver;
    ScreenshotUtils screenshotUtils;
    HomePage homePage;
    loginUtils loginUtils;

    DriverSetup driverSetup; // Declare setup utility class

    @BeforeClass
    public void setup() {
        driverSetup = new DriverSetup();
        driver = driverSetup.initializeDriver(); // Initialize driver using utility class

        loginUtils = new loginUtils(driver); // Use initialized driver
        screenshotUtils = new ScreenshotUtils(driver);
    }

    @Test
    public void homeCourseTest() {
        // Reuse the login method
        loginUtils.login("dqc_student2", "123456789");
/*
        // Step 2: Click the subject card using homePage
        HomePage homePage = new HomePage(driver);
        homePage.clickOnSubjectCard();


        // Step 3: Wait for the lesson page to load and click the lesson
        Lesson lesson = new Lesson(driver);
        lesson.clickOnLesson();

        // Step 4: Verify the lesson page content using assertions
        Item item = new Item(driver);
        item.clickOnItem();

/*
        // Tạo instance của ReadingTextHybrid
        ReadingTextHybrid readingTextHybrid = new ReadingTextHybrid(driver);
        // Gọi phương thức với giá trị '2' để chọn question2
        readingTextHybrid.clickLabelByForValue("1");
*/


            // Step 2: Click on the subject card with explicit wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            HomePage homePage = new HomePage(driver);
            WebElement subjectCard = wait.until(ExpectedConditions.elementToBeClickable(By.className("subject-card")));
            subjectCard.click();

            // Step 3: Click on the lesson
            Lesson lesson = new Lesson(driver);
            WebElement lessonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/themes/web/assets/images/icon/btn-play.png']")));
            lessonElement.click();

            // Step 4: Click on the item
            Item item = new Item(driver);
            WebElement itemElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/themes/web/assets/images/icon/lesson-package/icon-play.png']")));
            itemElement.click();


    }

    @AfterClass
    public void tearDown() {
        //driverSetup.tearDown(); // Quit the driver
    }
}