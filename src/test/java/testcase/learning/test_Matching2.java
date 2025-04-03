package testcase.learning;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ItemPractivePage;
import pages.LessonPackPage;
import pages.ModulePage;
import pages.content.Matching2Page;
import pages.content.ReadingTextHybridPage;
import utils.DriverSetup;
import utils.ScreenshotUtils;
import utils.loginUtils;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import java.util.List;

public class test_Matching2 {
    WebDriver driver;
    ScreenshotUtils screenshotUtils;

    loginUtils loginUtils;
    DriverSetup driverSetup;// Declare setup utility class
    JavascriptExecutor js;
    ReadingTextHybridPage readingTextHybridPage;
    ModulePage modulePage;
    LessonPackPage lessonPackPage;
    HomePage homePage;
    ItemPractivePage itemPractivePage;
    Matching2Page matching2Page;

    By numberanswer = By.cssSelector("label[class='text-answer py-1']");
    By rd_answer = By.cssSelector("input[type='radio']");

    @BeforeClass
    public void setup() {
        driverSetup = new DriverSetup();
        driver = driverSetup.initializeDriver(); // Initialize driver using utility class
        js = (JavascriptExecutor) driver;
        loginUtils = new loginUtils(driver); // Use initialized driver
        screenshotUtils = new ScreenshotUtils(driver);
        readingTextHybridPage = new ReadingTextHybridPage(driver);
        modulePage = new ModulePage(driver);
        lessonPackPage = new LessonPackPage(driver);
        homePage = new HomePage(driver);
        itemPractivePage = new ItemPractivePage(driver);
        matching2Page = new Matching2Page(driver);
    }

    @Test(priority = 1)
    public void TestContent_Matching2 () throws InterruptedException {
        // Step 1: Login
        loginUtils.login("https://lms-test.ivyglobalschool.org/", "auto.test03", "12345678");

        // Step 2: Click on the subject card
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //homePage.clickOnSubjectCard();
        homePage.clickOnSubjectName("DQC - Course - Tổng Hợp 17");

        // Step 3: Click on the lesson package
        modulePage.clickOnLesson();

        // Step 4: Click on the lesson
        lessonPackPage.clickOnItem();

        //Step 5: Chuyển tới matching 2
        itemPractivePage.clickNextItem();
        itemPractivePage.clickNextItem();
        itemPractivePage.clickNextItem();
        itemPractivePage.clickNextItem();

        // Scroll xuống giữa trang để load nội dung
        js.executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");
        Thread.sleep(2000);
        driver.switchTo().frame(0);

        matching2Page.drap_and_drop();
        //matching2Page.clickSubmit();
        //matching2Page.clickconfirmsubmit();
    }

    @AfterClass
    public void tearDown() {
        //driverSetup.tearDown(); // Quit the driver
    }
}