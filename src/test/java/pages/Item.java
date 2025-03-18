package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Item {
    private WebDriver driver;
    private WebDriverWait wait;

    public Item(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickOnItem() {
        By itemLocator = By.xpath("//img[@src='/themes/web/assets/images/icon/lesson-package/icon-play.png']");

        try {
            WebElement itemElement = wait.until(ExpectedConditions.refreshed(
                    ExpectedConditions.elementToBeClickable(itemLocator)
            ));
            itemElement.click();
            System.out.println("Item clicked successfully!");
        } catch (StaleElementReferenceException e) {
            System.out.println("Retrying to find the Item element...");
            WebElement itemElement = wait.until(ExpectedConditions.elementToBeClickable(itemLocator));
            itemElement.click();
        }
    }

}
