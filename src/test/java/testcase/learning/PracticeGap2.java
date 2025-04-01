package testcase.learning;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LessonPackPage;
import pages.ModulePage;
import pages.content.GAP2Page;
import pages.ItemPractivePage;
import utils.DriverSetup;
import utils.ScreenshotUtils;
import utils.loginUtils;

import java.util.Random;

public class PracticeGap2 {

    private WebDriver driver; // WebDriver điều khiển trình duyệt
    private ScreenshotUtils screenshotUtils; // Công cụ chụp ảnh màn hình
    private loginUtils loginUtils; // Công cụ đăng nhập
    private DriverSetup driverSetup; // Cài đặt WebDriver
    private HomePage homePage; // Trang chủ
    private ModulePage modulePage; // Trang module
    private LessonPackPage lessonPackPage; // Trang bài học
    private ItemPractivePage itemPractivePage; // Trang thực hành
    private GAP2Page gap2Page; // Trang GAP2

    JavascriptExecutor js;
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
        gap2Page = new GAP2Page(driver);
    }

    @Test(priority = 1)
    public void testInputSpecificAnswer() {
        try {
            // Đăng nhập
            loginUtils.login("https://lms-test.ivyglobalschool.org/", "auto.test03", "12345678");
            System.out.println("Login successful!");

            // Nhấn vào thẻ môn học
            homePage.clickOnSubjectCard();

            // Nhấn vào bài học và item
            modulePage.clickOnLesson();
            lessonPackPage.clickOnItem();
            itemPractivePage.clickNextItem();

            // Nhập liệu vào phần tử input_answer thứ 0 (đầu tiên)
            String answer = "My First Answer";
            gap2Page.InputAnswer(answer, 0);
            Thread.sleep(5000);
            // Nhấn nút Submit
            gap2Page.clickSubmitButtonDisabeled();

            System.out.println("Test completed successfully.");
        } catch (Exception e) {
            System.err.println("Error during test execution: " + e.getMessage());
        }
    }
    @Test(priority = 2)
    public void testSubmitAllAnswer() {
        try {
            // Step 1: Đăng nhập
            loginUtils.login("https://lms-test.ivyglobalschool.org/", "auto.test03", "12345678");
            System.out.println("Login successful!");

            // Step 2: Nhấn vào thẻ môn học
            homePage.clickOnSubjectCard();

            // Step 3: Nhấn vào bài học và item
            modulePage.clickOnLesson();
            lessonPackPage.clickOnItem();
            itemPractivePage.clickNextItem();

            // Step 4: Điền tất cả các đáp án
            gap2Page.fillAllAnswers("Answer"); // Gọi phương thức điền tất cả các đáp án với "Answer" làm prefix

            // Step 5: Nhấn nút Submit
            gap2Page.clickSubmitButtonEnabled(); // Kiểm tra nút Submit và thực hiện nhấn nếu điều kiện thỏa mãn

            System.out.println("Test completed successfully: All answers filled and submitted.");
        } catch (Exception e) {
            System.err.println("Error during test execution: " + e.getMessage());
        }
    }



    @AfterClass
    public void tearDown() {
        // Đóng trình duyệt sau kiểm thử
        driverSetup.tearDown();
        System.out.println("Test completed and browser closed.");
    }
}