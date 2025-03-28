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
    //By popUp_warning = By.className("warning-modal");
    By popUp_warning = By.cssSelector("div[class='warning-modal']");
    By btn_Igotit =  By.xpath("//*[contains(text(), 'I got it')]");

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

    public void clickIgotit (){
        wait.until(ExpectedConditions.visibilityOfElementLocated(popUp_warning));
        if (driver.findElement(popUp_warning).isDisplayed())
        {
            driver.findElement(btn_Igotit).click();
        }
        else
        {
            System.out.println("Check I got it popup fail");
        }
    }
}
