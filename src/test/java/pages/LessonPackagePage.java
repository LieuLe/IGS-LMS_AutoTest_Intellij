package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LessonPackagePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By imgPack = By.xpath("//img[@src=\"/themes/web/assets/images/icon/lesson-package/icon-play.png\"]");
    public LessonPackagePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Chờ tối đa 10 giây
    }

    public void clickOnPack() {
        // Chờ đến khi phần tử "subject-card" xuất hiện
        WebElement PackCard = wait.until(ExpectedConditions.visibilityOfElementLocated(imgPack));

        // Click vào phần tử
        PackCard.click();
    }

}


