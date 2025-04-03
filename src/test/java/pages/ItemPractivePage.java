package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ItemPractivePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By btnNextRight = By.cssSelector("img[src='/themes/web/assets/images/icon/arrow-right.svg']");
    private final By btnNextQuestion = By.className("btn-next");


    public ItemPractivePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to click on the item element
    public void clickNextItem() {
        try {
            WebElement itemElement = wait.until(ExpectedConditions.elementToBeClickable(btnNextRight));
            itemElement.click(); // Click the item element
            System.out.println("Item clicked next successfully!");
        } catch (Exception e) {
            System.err.println("Error while clicking on the item: " + e.getMessage());
        }
    }

    public void clickNextQuestion() {
        try {
            WebElement itemElement = wait.until(ExpectedConditions.elementToBeClickable(btnNextQuestion));
            itemElement.click(); // Click the item element
            System.out.println("Item clicked next Question successfully!");
        } catch (Exception e) {
            System.err.println("Error while clicking on the Question: " + e.getMessage());
        }
    }
}
