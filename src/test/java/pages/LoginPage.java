package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    By txtUsername = By.name("username");
    By txtPassword = By.name("password");
    By btnLogin = By.xpath("//*[@id=\"create_form\"]/button");
    By lbErrorMessage =  By.xpath("//*[@id=\"signin\"]/div/div/div[2]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
    }

    public void enterUsername(String username) {
        WebElement userName = driver.findElement(txtUsername);
        userName.clear();
        userName.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passWord = driver.findElement(txtPassword);
        passWord.clear();
        passWord.sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(btnLogin).click();
    }

    public String getLoginError() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(lbErrorMessage));
            return error.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }
}
