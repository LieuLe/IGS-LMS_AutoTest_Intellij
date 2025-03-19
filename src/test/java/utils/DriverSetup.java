package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSetup {
    private WebDriver driver;

    public WebDriver initializeDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}