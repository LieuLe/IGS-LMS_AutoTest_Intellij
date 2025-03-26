package pages.content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ReadingTextHybrid {
    private WebDriver driver;

    // Constructor
    public ReadingTextHybrid(WebDriver driver) {
        this.driver = driver;
    }

    // Method to click label dynamically based on 'for' value
    public void clickLabelByForValue(String forValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement label = driver.findElement(By.cssSelector("label[for='question" + forValue + "']"));
        label.click();
    }
}