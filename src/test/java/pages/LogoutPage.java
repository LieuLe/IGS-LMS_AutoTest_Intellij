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

    By Menu_profileAccount = By.id("account");
    By Menu_profile = By.className("profile-modal");
    By btnLogout = By.xpath("//*[contains(text(), ' LOG OUT ')]");
    By popUp_LogOut = By.className("change-password-modal");
    By btnconfirmLogout = By.xpath("//*[contains(text(), 'YES, LOG OUT NOW')]");
    By btn_No =  By.xpath("//*[contains(text(), 'NO, I WANT TO STAY')]");


    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
    }

    public void clickProfile() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Menu_profileAccount));
        //Click vào profileAccount
        WebElement profileAccountElement = driver.findElement(Menu_profileAccount);
        profileAccountElement.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickLogout() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Menu_profile));
        //Click vào profileAccount
        WebElement Logoutbuttion = driver.findElement(btnLogout);
        Logoutbuttion.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickYesLogout() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(popUp_LogOut));
        //Click vào profileAccount
        WebElement ConfirmLogout = driver.findElement(btnconfirmLogout);
        ConfirmLogout.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickNoLogout() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(popUp_LogOut));
        //Click vào profileAccount
        WebElement NotLogout = driver.findElement(btn_No);
        NotLogout.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
