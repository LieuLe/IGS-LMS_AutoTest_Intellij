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
    private By subjectCardLocator = By.className("subject-card");

    // Constructor để khởi tạo driver và WebDriverWait
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Chờ tối đa 10 giây
    }

    // Phương thức để click vào subject card
    public void clickOnSubjectCard() {
        // Chờ đến khi phần tử "subject-card" xuất hiện
        WebElement subjectCard = wait.until(ExpectedConditions.visibilityOfElementLocated(subjectCardLocator));

        // Click vào phần tử
        subjectCard.click();
    }



}
