package testcase.learning;

import utils.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.loginUtils;


public class testReadingData {

    WebDriver driver;
    loginUtils loginUtils;

    ScreenshotUtils screenshotUtils;




    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginUtils = new loginUtils(driver);

        screenshotUtils = new ScreenshotUtils(driver);
    }



    @Test
    public void ReadingData() {
        // Reuse the login method
        loginUtils.login("https://lms-test.ivyglobalschool.org/","dqc_student2", "123456789");

    }



}
