package pages.content;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GAP2Page {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By InputAnswerGAP2 = By.className("input_answer");

    // Constructor để khởi tạo driver
    public GAP2Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method cuộn đến phần tử "input_answer" và nhập giá trị
    public void InputAnswer(String text) {
        try {
            // Lấy phần tử "input_answer"
            WebElement inputAnswerElement = driver.findElement(InputAnswerGAP2);

            // Sử dụng JavascriptExecutor để cuộn đến phần tử
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", inputAnswerElement);

            // Nhập giá trị vào input
            inputAnswerElement.sendKeys(text);
            System.out.println("Successfully scrolled to and entered text into input_answer.");
        } catch (Exception e) {
            System.err.println("Error in InputAnswer method: " + e.getMessage());
        }
    }

    /*
    // Thêm các phương thức khác nếu cần quản lý các phần tử khác
    public WebElement getSubmitButton() {
        return driver.findElement(By.className("btn-submit")); // Ví dụ: Tìm nút submit theo ID
    }
    */
}    