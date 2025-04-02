package pages.content;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.List;


public class ReadingTextHybridPage {
    private WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    By rd_answer = By.cssSelector("span.radio-dot.ps-3");
    By cb_answer = By.cssSelector("span.checkbox.ps-3");
    By input_answer = By.cssSelector("input.input_answer");
    By nb_answer = By.cssSelector("label[class='text-answer py-1']");
    By btn_next = By.cssSelector("button[class='btn-next']");
    By btn_submit = By.cssSelector("button[class='btn-submit']");

    // Constructor
    public ReadingTextHybridPage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    // Method to click label dynamically based on 'for' value
    public void clickLabelByForValue(String forValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement label = driver.findElement(By.cssSelector("label[for='question" + forValue + "']"));
        label.click();
    }

    public void numberAnswer() {
        List<WebElement> question = driver.findElements(nb_answer);
        System.out.println("Số lượng phần tử trong danh sách: " + question.size());
    }

    public static int getTotalQuestions(WebDriver driver) {
        try {
            // Khởi tạo WebDriverWait mới
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

            // Chờ phần tử xuất hiện
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.question-total")));

            // Tìm phần tử chứa thông tin số lượng câu hỏi
            WebElement questionTotalElement = driver.findElement(By.cssSelector("span.question-total"));

            // Lấy số lượng câu hỏi từ nội dung text
            return Integer.parseInt(questionTotalElement.getText().trim());
        } catch (Exception e) {
            System.out.println("Không thể lấy số lượng câu hỏi: " + e.getMessage());
            return 0; // Trả về 0 nếu có lỗi
        }
    }

    public void clickRadioAnswer(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            List<WebElement> radios = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(rd_answer));
            if (index >= 0 && index < radios.size()) {
                WebElement radio = radios.get(index);
                // Cuộn đến element để tránh bị che khuất
                js.executeScript("arguments[0].scrollIntoView(true);", radio);
                // Click vào radio button theo index
                radio.click();
                System.out.println("Đã click vào radio button số " + index);
            } else {
                System.out.println("Index không hợp lệ. Tổng số radio buttons: " + radios.size());
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi click vào radio button: " + e.getMessage());
        }
    }

    public void clickCheckboxAnswer() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Tìm tất cả checkbox
            List<WebElement> checkboxes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cb_answer));
            if (checkboxes.isEmpty()) {
                System.out.println("Không tìm thấy checkbox nào.");
                return;
            }
            // Duyệt qua danh sách và click vào từng checkbox
            for (WebElement checkbox : checkboxes) {
                js.executeScript("arguments[0].scrollIntoView(true);", checkbox);
                checkbox.click();
            }
            System.out.println("Đã chọn tất cả " + checkboxes.size() + " checkbox.");
        } catch (Exception e) {
            System.out.println("Lỗi khi chọn checkbox: " + e.getMessage());
        }
    }

    public void inputtext(String text){
        // Tìm tất cả các ô input text
        List<WebElement> textInputs = driver.findElements(input_answer);
        // Kiểm tra xem có ô nào không
        if (!textInputs.isEmpty()) {
            // Duyệt qua từng ô input và nhập nội dung
            for (WebElement input : textInputs) {
                input.sendKeys(text);
            }
            System.out.println("Đã nhập text cho " + textInputs.size() + " ô trống.");
        } else {
            System.out.println("Không tìm thấy ô nhập văn bản nào!");
        }
    }

    public void clickNext (){
        if (driver.findElement(btn_next).isDisplayed()){
            driver.findElement(btn_next).click();}
        else {
            System.out.println("Click next is fail");}
    }

    public void clickSubmit (){
        if (driver.findElement(btn_submit).isDisplayed()){
            driver.findElement(btn_submit).click();}
        else {
            System.out.println("Click next is fail");}
    }

}