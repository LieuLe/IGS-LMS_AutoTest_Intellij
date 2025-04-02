package testcase.learning;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ItemPractivePage;
import pages.LessonPackPage;
import pages.ModulePage;
import pages.content.MCQPage;
import utils.DriverSetup;
import utils.ScreenshotUtils;
import utils.loginUtils;

public class PracticeMCQ {

    private WebDriver driver; // WebDriver điều khiển trình duyệt
    private ScreenshotUtils screenshotUtils; // Công cụ chụp ảnh màn hình
    private utils.loginUtils loginUtils; // Công cụ đăng nhập
    private DriverSetup driverSetup; // Cài đặt WebDriver
    private HomePage homePage; // Trang chủ
    private ModulePage modulePage; // Trang module
    private LessonPackPage lessonPackPage; // Trang bài học
    private ItemPractivePage itemPractivePage; // Trang thực hành
    private MCQPage mcqPage;

    @BeforeClass
    public void setup() {
        // Khởi tạo WebDriver và các tiện ích
        driverSetup = new DriverSetup();
        driver = driverSetup.initializeDriver();
        screenshotUtils = new ScreenshotUtils(driver);
        loginUtils = new loginUtils(driver);
        homePage = new HomePage(driver);
        modulePage = new ModulePage(driver);
        lessonPackPage = new LessonPackPage(driver);
        itemPractivePage = new ItemPractivePage(driver);

        // Khởi tạo MCQPage
        mcqPage = new MCQPage(driver);

    }

    @Test(priority = 1)
    public void testChooseAnswerAndSubmit() throws InterruptedException {
        loginUtils.login("https://lms-test.ivyglobalschool.org/", "auto.test03", "12345678");
        System.out.println("Login successful!");

        // Step 2: Nhấn vào thẻ môn học
        homePage.clickOnSubjectCard();

        // Step 3: Nhấn vào bài học và item
        modulePage.clickOnLesson();
        lessonPackPage.clickOnItem();
        itemPractivePage.clickNextItem();
        itemPractivePage.clickNextItem();
        itemPractivePage.clickNextItem();
        itemPractivePage.clickNextItem();
        itemPractivePage.clickNextItem();
        itemPractivePage.clickNextItem();

        mcqPage.clickBoxAnswer(1);
        Thread.sleep(25000);
        mcqPage.clickSubmitButtonDisabeled();
        Thread.sleep(5000);

    }

    @AfterClass
    public void tearDown() {
        // Đóng trình duyệt sau kiểm thử
        driverSetup.tearDown();
        System.out.println("Test completed and browser closed.");
    }

}
