package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LessonPackPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locator for the item element
    private final By itemLocator = By.xpath("//img[@src='/themes/web/assets/images/icon/lesson-package/icon-play.png']");

    // Constructor to initialize driver and WebDriverWait
    public LessonPackPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to click on the item element
    public void clickOnItem() {
        try {
            WebElement itemElement = wait.until(ExpectedConditions.elementToBeClickable(itemLocator));
            itemElement.click(); // Click the item element
            System.out.println("Item clicked successfully!");
        } catch (Exception e) {
            System.err.println("Error while clicking on the item: " + e.getMessage());
        }
    }
}