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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    // Phương thức để click vào subject card
    public void clickOnSubjectCard() {
        try {
            WebElement subjectCard = wait.until(ExpectedConditions.visibilityOfElementLocated(subjectCardLocator));

            subjectCard.click();
            System.out.println("Subject card clicked successfully!");
        } catch (Exception e) {
            System.err.println("Error while clicking on the subject card: " + e.getMessage());
        }
    }

    public void clickOnSubjectName(String subjectname) {
        try {
            WebElement courseElement = driver.findElement(By.xpath("//p[@class='studing' and text()='" + subjectname + "']"));
            courseElement.click();
            System.out.println("Subject card clicked successfully: "+subjectname);
        } catch (Exception e) {
            System.err.println("Error while clicking on the subject card: " + e.getMessage());
        }
    }
}
