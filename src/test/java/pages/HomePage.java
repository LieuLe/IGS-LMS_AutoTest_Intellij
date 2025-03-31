package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locator cho subject card
    private final By subjectCardLocator = By.className("subject-card");

    // Constructor để khởi tạo driver và WebDriverWait
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Phương thức để click vào subject card
    public void clickOnSubjectCard() {
        try {// Chờ đến khi phần tử "subject-card" xuất hiện
            WebElement subjectElement = wait.until(ExpectedConditions.elementToBeClickable(subjectCardLocator));
            WebElement subjectCard = wait.until(ExpectedConditions.visibilityOfElementLocated(subjectCardLocator));

            // Click vào phần tử
            subjectCard.click();

        System.out.println("Subject card clicked successfully!");
        } catch (Exception e) {
            System.err.println("Error while clicking on the item: " + e.getMessage());
        }
    }
}
