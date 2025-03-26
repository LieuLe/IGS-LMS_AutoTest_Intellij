package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AnswerUtils {
    WebDriver driver;
    WebDriverWait wait;
    
    By rd_answer = By.cssSelector("input[type='radio']");
    By cb_answer = By.cssSelector("input[type='checkbox']");
    By input_answer = By.cssSelector("input[type='text']");
    By numberanswer = By.cssSelector("label[class='text-answer py-1']");

    public AnswerUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
    }

    public void numberAnswer() {
        List<WebElement> question = driver.findElements(numberanswer);
        System.out.println("Số lượng phần tử trong danh sách: " + question.size());
    }

    public void clickradioAnswer(int index) {
        List<WebElement> radioOption = driver.findElements(rd_answer);
        if (radioOption.isEmpty()) {
            System.out.println("Không tìm thấy radio button nào!");
            return;
        }
        else  {
            radioOption.get(index).click(); // Chọn đáp án đầu tiên
        }

        if (index < 0 || index >= radioOption.size()) {
            System.out.println("Chỉ mục không hợp lệ! Vui lòng nhập từ 0 đến " + (radioOption.size() - 1));
            return;
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeSelected(radioOption.get(index)));
        System.out.println("Đã chọn radio button tại vị trí: " + index);
    }

    public void clickCheckboxAnswer() {
        List<WebElement> checkboxOptions = driver.findElements(cb_answer);
        for (WebElement checkbox : checkboxOptions) {
            checkbox.click(); // Chọn tất cả checkbox (tuỳ chỉnh theo logic)
        }
    }
}

