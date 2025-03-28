package testcase.learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SubjectPage;
import utils.AnswerUtils;
import utils.loginUtils;
import utils.DriverSetup;
import utils.ScreenshotUtils;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.List;

public class test_ReadingTextHybrid {
    WebDriver driver;
    ScreenshotUtils screenshotUtils;
    HomePage homePage;
    loginUtils loginUtils;
    DriverSetup driverSetup;// Declare setup utility class
    JavascriptExecutor js;
    SubjectPage subjectPage;
    AnswerUtils answerUtils;

    @BeforeClass
    public void setup() {
        driverSetup = new DriverSetup();
        driver = driverSetup.initializeDriver(); // Initialize driver using utility class
        js = (JavascriptExecutor) driver;
        loginUtils = new loginUtils(driver); // Use initialized driver
        screenshotUtils = new ScreenshotUtils(driver);
        subjectPage = new SubjectPage(driver);
        answerUtils = new AnswerUtils(driver);
    }

    @Test(priority = 1)
    public void homeCourseTest() throws InterruptedException {
        // Step 1: Login
        loginUtils.login("https://lms-test.ivyglobalschool.org/","dqc_student2", "123456789");

        // Step 2: Click on the subject card
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        subjectPage.clickSubject();

        // Step 3: Click on the lesson package
        subjectPage.clickLessonPackage();

        // Step 4: Click on the lesson
        subjectPage.clickLesson();
    }

    @Test(priority = 2)
    public void courseTest() throws InterruptedException {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");
        Thread.sleep(2000);
        driver.switchTo().frame(0);
        //List of answers in question1
        List<WebElement> question1 = driver.findElements(By.cssSelector("label[class='text-answer py-1']"));
        System.out.println("Số lượng phần tử trong danh sách: " + question1.size());

        //Check answer type
        if (driver.findElements(By.cssSelector("input[type='radio']")).isEmpty()) {
            //subjectPage.clickradioAnswer(2);
            answerUtils.clickradioAnswer(2);
        } else if (driver.findElements(By.cssSelector("input[type='checkbox']")).isEmpty()) {
            // Câu hỏi dạng checkbox (chọn nhiều đáp án)
            List<WebElement> checkboxOptions = driver.findElements(By.cssSelector("input[type='checkbox']"));
            for (WebElement checkbox : checkboxOptions) {
                checkbox.click(); // Chọn tất cả checkbox (tuỳ chỉnh theo logic)
            }
        } else if (driver.findElements(By.cssSelector("input[type='text']")).isEmpty()) {
            // Câu hỏi dạng input text
            WebElement textInput = driver.findElement(By.cssSelector("input[type='text']"));
            textInput.sendKeys("Nhập được rồi nè"); // Nhập câu trả lời
        }
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button[class='btn-next']")).click();

    }

    @AfterClass
    public void tearDown() {
        //driverSetup.tearDown(); // Quit the driver
    }
}