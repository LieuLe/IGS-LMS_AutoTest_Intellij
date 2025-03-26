package pages.content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageGAP2 {

    private WebDriver driver;

    // Constructor để khởi tạo driver
    public void PageElements(WebDriver driver) {
        this.driver = driver;
    }

    // Getter cho phần tử có class "input_answer"
    public WebElement getInputAnswerElement() {
        return driver.findElement(By.className("input_answer"));
    }

    // Thêm các phương thức khác nếu cần quản lý các phần tử khác
    public WebElement getSubmitButton() {
        return driver.findElement(By.className("btn-submit")); // Ví dụ: Tìm nút submit theo ID
    }




}
