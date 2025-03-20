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
import pages.Item;
import pages.Lesson;
import testcase.Login.loginUtils;
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

    @BeforeClass
    public void setup() {
        driverSetup = new DriverSetup();
        driver = driverSetup.initializeDriver(); // Initialize driver using utility class
        js = (JavascriptExecutor) driver;
        loginUtils = new loginUtils(driver); // Use initialized driver
        screenshotUtils = new ScreenshotUtils(driver);
    }

    @Test(priority = 1)
    public void homeCourseTest() throws InterruptedException {
        // Reuse the login method
        loginUtils.login("dqc_student2", "123456789");

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

        //Kiểm tra ulr lesson
        wait.until(ExpectedConditions.urlContains("lesson"));
        Thread.sleep(5000);
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
            // Câu hỏi dạng radio (chỉ chọn 1 đáp án)
            List<WebElement> radioOption = driver.findElements(By.cssSelector("input[type='radio']"));
            if (!radioOption.isEmpty()) {
                radioOption.get(0).click(); // Chọn đáp án đầu tiên
            }
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
        //driver.findElement(By.xpath("//div[@id='sandbox']/div[3]/div/div/div[2]/div[2]/div[2]/div/div/div/label/span")).click();
        //Click next buttion
        driver.findElement(By.cssSelector("button[class='btn-next']")).click();

    }

    @AfterClass
    public void tearDown() {
        //driverSetup.tearDown(); // Quit the driver
    }
}