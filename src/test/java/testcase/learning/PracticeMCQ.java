package testcase.learning;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
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

    }

}
