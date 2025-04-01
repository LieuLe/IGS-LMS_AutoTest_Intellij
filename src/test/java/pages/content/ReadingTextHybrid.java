package pages.content;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class ReadingTextHybrid {
    private WebDriver driver;

    WebDriverWait wait;

    By rd_answer = By.cssSelector("input[type='radio']");
    By cb_answer = By.cssSelector("input[type='checkbox']");
    By input_answer = By.cssSelector("input[type='text']");
    By numberanswer = By.cssSelector("label[class='text-answer py-1']");

    By btn_next = By.cssSelector("button[class='btn-next']");

    // Constructor
    public ReadingTextHybrid(WebDriver driver) {
        this.driver = driver;
    }

    // Method to click label dynamically based on 'for' value
    public void clickLabelByForValue(String forValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement label = driver.findElement(By.cssSelector("label[for='question" + forValue + "']"));
        label.click();
    }

    public void AnswerUtils(WebDriver driver) {
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
        if (!checkboxOptions.isEmpty()) { // Kiểm tra danh sách checkbox có phần tử không
            for (WebElement checkbox : checkboxOptions) {
                checkbox.click();
            }
        } else {
            System.out.println("Không tìm thấy checkbox nào!");
        }
    }

    public void inputtext(String text){
        // Tìm tất cả các ô input text
        List<WebElement> textInputs = driver.findElements(By.cssSelector("input.input_answer"));
        // Kiểm tra xem có ô nào không
        if (!textInputs.isEmpty()) {
            // Duyệt qua từng ô input và nhập nội dung
            for (WebElement input : textInputs) {
                input.sendKeys(text);
            }
        } else {
            System.out.println("Không tìm thấy ô nhập văn bản nào!");
        }
    }

    public void clickNext (){
        if (driver.findElement(btn_next).isDisplayed()){driver.findElement(btn_next).click();}
        else {System.out.println("Click next is fail");}
    }
}