package testcase.learning;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ItemPractivePage;
import pages.LessonPackPage;
import pages.ModulePage;
import pages.content.MCQvsGAP1Page;
import utils.DriverSetup;
import utils.ScreenshotUtils;
import utils.loginUtils;

public class PracticeMCQvsGAP1 {

    private WebDriver driver; // WebDriver điều khiển trình duyệt
    private ScreenshotUtils screenshotUtils; // Công cụ chụp ảnh màn hình
    private utils.loginUtils loginUtils; // Công cụ đăng nhập
    private DriverSetup driverSetup; // Cài đặt WebDriver
    private HomePage homePage; // Trang chủ
    private ModulePage modulePage; // Trang module
    private LessonPackPage lessonPackPage; // Trang bài học
    private ItemPractivePage itemPractivePage; // Trang thực hành
    private MCQvsGAP1Page MCQvsGAP1Page;

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
        MCQvsGAP1Page = new MCQvsGAP1Page(driver);

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
        Thread.sleep(5000);
        MCQvsGAP1Page.clickBoxAnswer(1);
        Thread.sleep(5000);
        MCQvsGAP1Page.clickNextQuestion();
        String answer = "My First Answer";
        MCQvsGAP1Page.InputAnswer(answer, 0);
        Thread.sleep(5000);
        MCQvsGAP1Page.clickSubmitButtonDisabeled();
        Thread.sleep(5000);

    }


    @AfterClass
    public void tearDown() {
        // Đóng trình duyệt sau kiểm thử
        driverSetup.tearDown();
        System.out.println("Test completed and browser closed.");
    }

}
