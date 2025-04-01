package testcase.learning;

import org.openqa.selenium.By;
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
import pages.content.ReadingTextHybrid;
import utils.loginUtils;
import utils.DriverSetup;
import utils.ScreenshotUtils;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.List;


public class PracticeReadingTextHybrid {
    WebDriver driver;
    ScreenshotUtils screenshotUtils;

    loginUtils loginUtils;
    DriverSetup driverSetup;// Declare setup utility class
    JavascriptExecutor js;
    HomePage subjectPage;


    ReadingTextHybrid answerUtils;

    @BeforeClass
    public void setup() {
        driverSetup = new DriverSetup();
        driver = driverSetup.initializeDriver(); // Initialize driver using utility class
        js = (JavascriptExecutor) driver;
        loginUtils = new loginUtils(driver); // Use initialized driver
        screenshotUtils = new ScreenshotUtils(driver);
        subjectPage = new HomePage(driver);
        answerUtils = new ReadingTextHybrid(driver);

    }

    @Test(priority = 1)
    public void homeCourseTest() throws InterruptedException {
        // Step 1: Login
        loginUtils.login("https://lms-test.ivyglobalschool.org/","auto.test03", "12345678");

        // Step 2: Click on the subject card
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        subjectPage.clickOnSubjectCard();

        // Step 3: Click on the lesson package

        ModulePage lesson = new ModulePage(driver);
        WebElement lessonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/themes/web/assets/images/icon/btn-play.png']")));
        lessonElement.click();
        //Kiểm tra ulr lesson package
        wait.until(ExpectedConditions.urlContains("lesson-package"));
        Thread.sleep(2000);

        // Step 4: Click on the lesson
        LessonPackPage lessonPackPage = new LessonPackPage(driver);
        WebElement itemElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/themes/web/assets/images/icon/lesson-package/icon-play.png']")));
        itemElement.click();

        //Kiểm tra ulr lesson
        wait.until(ExpectedConditions.urlContains("lesson"));
        Thread.sleep(5000);


    }

    @Test(priority = 2)
    public void courseTest() throws InterruptedException {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");
        Thread.sleep(2000);
        driver.switchTo().frame(0);

        while (true)
        {
            if (driver.findElements(By.cssSelector("input[type='radio']")).isEmpty()) {
                answerUtils.clickradioAnswer(2);
                Thread.sleep(2000);
                answerUtils.clickNext();
            } else if (driver.findElements(By.cssSelector("input[type='checkbox']")).isEmpty()) {
                answerUtils.clickCheckboxAnswer();
                Thread.sleep(2000);
                answerUtils.clickNext();
            } else if (driver.findElements(By.cssSelector("input[type='text']")).isEmpty()) {
                answerUtils.inputtext("abcd");
                Thread.sleep(2000);
                answerUtils.clickNext();
            }
            Thread.sleep(1000);
            // Tìm nút Next
            List<WebElement> nextButton = driver.findElements(By.cssSelector("button.btn-next"));

            // Nếu không tìm thấy nút Next hoặc nút Next bị vô hiệu hóa, thoát vòng lặp
            if (nextButton.isEmpty() || nextButton.get(0).getAttribute("disabled") != null) {
                System.out.println("Không còn nút Next, kết thúc bài kiểm tra!");
                break;
            }

            // Nếu còn nút Next, bấm để qua trang tiếp theo
            nextButton.get(0).click();
            Thread.sleep(2000); // Đợi trang load
        }


        //List of answers in question1
        //List<WebElement> question1 = driver.findElements(By.cssSelector("label[class='text-answer py-1']"));
        //System.out.println("Số lượng phần tử trong danh sách: " + question1.size());
    }

    @AfterClass
    public void tearDown() {
        //driverSetup.tearDown(); // Quit the driver
    }
}