package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SubjectPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By lessonCardLocator = By.className("subject-card");

    public SubjectPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Chờ tối đa 10 giây
    }
}
