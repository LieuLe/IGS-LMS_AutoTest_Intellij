package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutPage {
    WebDriver driver;
    WebDriverWait wait;

    private By profileAccount = By.id("account");
    private By profileMenu_Account = By.className("profile-modal");
    private By logoutText = By.xpath("//*[contains(text(), ' LOG OUT ')]");
    private By popUp_LogOut = By.className("change-password-modal");
    private By confirmLogoutText = By.xpath("//*[contains(text(), 'YES, LOG OUT NOW')]");
    private By welcomeText = By.xpath("//*[contains(text(), 'Welcome to LMS')]");
    By btnLogout = By.xpath("//button[@type='submit']");

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
    }

    public void clickLogoutPage() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileAccount));
        //Click vào profileAccount
        WebElement profileAccountElement = driver.findElement(profileAccount);
        profileAccountElement.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileAccount));

        // Click vào profileAccount
        WebElement profileAccountElement = driver.findElement(profileAccount);
        profileAccountElement.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
