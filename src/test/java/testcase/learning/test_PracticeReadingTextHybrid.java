package testcase.learning;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LessonPackPage;
import pages.ModulePage;
import pages.content.ReadingTextHybridPage;
import utils.DriverSetup;
import utils.ScreenshotUtils;
import utils.loginUtils;

import java.time.Duration;
import java.util.List;


public class test_PracticeReadingTextHybrid {
    WebDriver driver;
    ScreenshotUtils screenshotUtils;

    loginUtils loginUtils;
    DriverSetup driverSetup;// Declare setup utility class
    JavascriptExecutor js;
    HomePage subjectPage;
    ReadingTextHybridPage answerUtils;
    ReadingTextHybridPage readingTextHybridPage;
    ModulePage modulePage;
    LessonPackPage lessonPackPage;

    By numberanswer = By.cssSelector("label[class='text-answer py-1']");
    By rd_answer = By.cssSelector("input[type='radio']");

    @BeforeClass
    public void setup() {
        driverSetup = new DriverSetup();
        driver = driverSetup.initializeDriver(); // Initialize driver using utility class
        js = (JavascriptExecutor) driver;
        loginUtils = new loginUtils(driver); // Use initialized driver
        screenshotUtils = new ScreenshotUtils(driver);
        subjectPage = new HomePage(driver);
        answerUtils = new ReadingTextHybridPage(driver);
        readingTextHybridPage = new ReadingTextHybridPage(driver);
        modulePage = new ModulePage(driver);
        lessonPackPage = new LessonPackPage(driver);
    }

    @Test(priority = 1)
    public void TestContent_ReadingTextHybrid() throws InterruptedException {
        // Step 1: Login
        loginUtils.login("https://lms-test.ivyglobalschool.org/", "auto.test03", "12345678");

        // Step 2: Click on the subject card
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        subjectPage.clickOnSubjectCard();

        // Step 3: Click on the lesson package
        modulePage.clickOnLesson();

        // Step 4: Click on the lesson
        lessonPackPage.clickOnItem();

        // Scroll xuống giữa trang để load nội dung
        js.executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");
        Thread.sleep(2000);
        driver.switchTo().frame(0);

        //Lấy số lượng câu hỏi
        int totalQuestions = readingTextHybridPage.getTotalQuestions(driver);
        System.out.println("Tổng số câu hỏi trong package: " + totalQuestions);

        //Chọn đáp án
       for (int i = 0; i < totalQuestions; i++) {
            System.out.println("Đang làm câu hỏi " + (i + 1) + "/" + totalQuestions);
            // Biến kiểm tra đã chọn được đáp án hay chưa
            boolean answered = false;
            if (!driver.findElements(By.cssSelector("input[type='radio']")).isEmpty()) {
                readingTextHybridPage.clickRadioAnswer(0);
                answered = true;
                readingTextHybridPage.clickNext();
            } else if (!driver.findElements(By.cssSelector("input[type='checkbox']")).isEmpty()) {
                readingTextHybridPage.clickCheckboxAnswer();
                answered = true;
                readingTextHybridPage.clickNext();
            } else if (!driver.findElements(By.cssSelector("input[type='text']")).isEmpty()) {
                readingTextHybridPage.inputtext("abcd");
                Thread.sleep(2000);
                answered = true;
                readingTextHybridPage.clickNext();
            }
            // Nếu không chọn được đáp án nào, dừng lại
            if (!answered) {
                System.out.println("Không tìm thấy đáp án phù hợp, dừng vòng lặp.");
                break;
            }
        }

       //Submit bài học
       readingTextHybridPage.clickSubmit();
       //readingTextHybridPage.clickconfirmsubmit();
    }

    @AfterClass
    public void tearDown() {
        //driverSetup.tearDown(); // Quit the driver
    }
}