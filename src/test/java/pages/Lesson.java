package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Lesson {
    private WebDriver driver;
    private WebDriverWait wait;

    public Lesson(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickOnLesson() {
        By lessonLocator = By.xpath("//img[@src='/themes/web/assets/images/icon/btn-play.png']");

        try {
            WebElement lessonElement = wait.until(ExpectedConditions.refreshed(
                    ExpectedConditions.elementToBeClickable(lessonLocator)
            ));
            lessonElement.click();
            System.out.println("Lesson clicked successfully!");
        } catch (StaleElementReferenceException e) {
            System.out.println("Retrying to find the Lesson element...");
            WebElement lessonElement = wait.until(ExpectedConditions.elementToBeClickable(lessonLocator));
            lessonElement.click();
        }
    }

}
