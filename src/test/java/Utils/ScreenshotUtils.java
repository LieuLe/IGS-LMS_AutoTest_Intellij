package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    private WebDriver driver;

    public ScreenshotUtils(WebDriver driver) {
        this.driver = driver;
    }

    // Phương thức chụp ảnh màn hình
    public void captureScreenshot(String screenshotName) {
        TakesScreenshot ts = (TakesScreenshot) this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        try {
            // Lưu ảnh chụp màn hình vào thư mục "./Screenshots/"
            FileHandler.copy(source, new File("D:\\AutoTest\\IntelliJ\\IGS-LMS_AutoTest_Intellij\\src\\test\\java\\Utils" + screenshotName + ".png"));
            System.out.println("Screenshot taken: " + screenshotName);
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }
}