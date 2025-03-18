package testcase.Learning;

import Utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.Item;
import pages.Lesson;
import testcase.Login.loginUtils;

public class test {
    private WebDriver driver;
    private loginUtils loginUtils;
    private ScreenshotUtils screenshotUtils;
    private HomePage homePage;

    @BeforeClass
    public void setup() {
        // Khởi tạo ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Khởi tạo các class hỗ trợ
        loginUtils = new loginUtils(driver);
        screenshotUtils = new ScreenshotUtils(driver);
        homePage = new HomePage(driver);
    }

    @Test
    public void homeCourseTest() {
        // Đăng nhập sử dụng loginUtils
        loginUtils.login("dqc_student2", "123456789");

        // Nhấn vào "subject card" thông qua HomePage
        homePage.clickOnSubjectCard();

        // Nhấn vào phần "Lesson"
        Lesson lesson = new Lesson(driver);
        lesson.clickOnLesson();

        // Nhấn vào phần "Item"
        Item item = new Item(driver);
        item.clickOnItem();
    }
}
