package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ModulePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locator for the lesson element
    private final By lessonLocator = By.xpath("//img[@src='/themes/web/assets/images/icon/btn-play.png']"); // Fixed missing bracket

    // Constructor to initialize driver and WebDriverWait
    public ModulePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set explicit wait
    }

    // Method to click on the lesson element
    public void clickOnLesson() {
        try {
            // Wait until the element is clickable
            WebElement lessonElement = wait.until(ExpectedConditions.elementToBeClickable(lessonLocator));
            lessonElement.click(); // Perform click action
            //Kiểm tra ulr lesson package
            wait.until(ExpectedConditions.urlContains("lesson-package"));
            Thread.sleep(2000);
            System.out.println("Item clicked successfully!");
            System.out.println("Lesson clicked successfully!");
        } catch (Exception e) {
            // Log error details
            System.err.println("Error while clicking on the lesson element: " + e.getMessage());
        }
    }
}