package pages.content;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class GAP2Page {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locator cho phần tử input_answer và iframe
    private final By InputAnswerGAP2 = By.className("input_answer");
    private final By SubmitButtonGAP2_Disabled = By.className("btn-submit-disabled");
    private final By SubmitButtonGAP2_Enabled = By.className("btn-submit");

    private final By IframeLocator = By.tagName("iframe"); // Định danh iframe (tag name là ví dụ)

    // Constructor để khởi tạo driver
    public GAP2Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method nhập giá trị vào input_answer trong iframe
    public void InputAnswer(String text, int index) {
        try {
            // Chuyển vào iframe nếu phần tử nằm trong iframe
            List<WebElement> iframes = driver.findElements(IframeLocator);
            if (!iframes.isEmpty()) {
                System.out.println("Switching to iframe...");
                driver.switchTo().frame(iframes.get(0)); // Chọn iframe đầu tiên (hoặc chỉnh theo yêu cầu)
            }

            // Lấy danh sách các phần tử input_answer
            List<WebElement> inputAnswers = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(InputAnswerGAP2));

            // Kiểm tra index có hợp lệ không
            if (index < 0 || index >= inputAnswers.size()) {
                throw new IndexOutOfBoundsException("Invalid index for input_answer: " + index);
            }

            // Chọn phần tử theo index
            WebElement inputAnswerElement = inputAnswers.get(index);

            // Cuộn đến phần tử sử dụng JavascriptExecutor
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", inputAnswerElement);

            // Nhập giá trị
            inputAnswerElement.sendKeys(text);
            System.out.println("Successfully entered text into input_answer at index " + index + ": " + text);

            // Quay lại nội dung chính sau khi thao tác
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            System.err.println("Error in InputAnswer method: " + e.getMessage());
        }
    }

    public void clickSubmitButtonDisabeled() {
        try {
            // Chuyển vào iframe nếu nút Submit nằm trong iframe
            List<WebElement> iframes = driver.findElements(IframeLocator);
            if (!iframes.isEmpty()) {
                System.out.println("Switching to iframe...");
                driver.switchTo().frame(iframes.get(0)); // Chọn iframe đầu tiên (hoặc chỉnh theo yêu cầu)
            }

            // Tìm nút Submit
            WebElement submitButton = driver.findElement(SubmitButtonGAP2_Disabled);

            // Kiểm tra trạng thái "disabled" của nút Submit
            String disabledAttribute = submitButton.getAttribute("disabled");

            // Nếu nút Submit bị vô hiệu hóa (disabled), bài kiểm thử pass
            if (disabledAttribute != null && disabledAttribute.equals("disabled")) {
                System.out.println("Test passed: The Submit button is disabled.");
            } else if (!submitButton.isEnabled()) {
                System.out.println("Test passed: The Submit button is not enabled.");
            } else {
                // Nếu nút có thể nhấn, bài kiểm thử fail
                System.err.println("Test failed: The Submit button is enabled and clickable.");
                throw new AssertionError("Submit button should be disabled, but it's enabled.");
            }

            // Quay lại nội dung chính sau khi kiểm tra
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            System.err.println("Error in clickSubmitButtonDisabeled method: " + e.getMessage());
        }
    }


    public void fillAllAnswers(String baseAnswer) {
        try {
            // Chuyển vào iframe nếu các phần tử input nằm trong iframe
            List<WebElement> iframes = driver.findElements(IframeLocator);
            if (!iframes.isEmpty()) {
                System.out.println("Switching to iframe...");
                driver.switchTo().frame(iframes.get(0)); // Chọn iframe đầu tiên (hoặc thay đổi theo yêu cầu)
            }

            // Lấy danh sách tất cả các trường input_answer
            List<WebElement> inputAnswers = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(InputAnswerGAP2));
            System.out.println("Found " + inputAnswers.size() + " input fields.");

            // Lặp qua tất cả các trường input và nhập dữ liệu
            int index = 1; // Biến để đánh dấu thứ tự của từng đáp án
            for (WebElement inputField : inputAnswers) {
                // Tạo đáp án cho từng trường
                String answer = baseAnswer + " " + index; // Ví dụ: "Answer 1", "Answer 2", ...

                // Cuộn tới trường input
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", inputField);

                // Nhập đáp án vào trường
                inputField.clear(); // Xóa dữ liệu cũ trong trường nếu cần
                inputField.sendKeys(answer); // Nhập dữ liệu
                System.out.println("Entered answer for input field " + index + ": " + answer);

                index++; // Tăng biến đếm
            }

            // Quay lại nội dung chính sau khi xử lý iframe (nếu đã chuyển vào iframe)
            driver.switchTo().defaultContent();
            System.out.println("Completed filling all answers.");
        } catch (Exception e) {
            System.err.println("Error while filling all answers: " + e.getMessage());
        }
    }



    public void clickSubmitButtonEnabled() {
        try {
            // Chuyển vào iframe nếu nút Submit nằm trong iframe
            List<WebElement> iframes = driver.findElements(IframeLocator);
            if (!iframes.isEmpty()) {
                System.out.println("Switching to iframe...");
                driver.switchTo().frame(iframes.get(0)); // Chọn iframe đầu tiên (hoặc chỉnh theo yêu cầu)
            }

            // Tìm nút Submit
            WebElement submitButton = driver.findElement(SubmitButtonGAP2_Enabled);

            // Kiểm tra trạng thái "disabled" của nút Submit
            String disabledAttribute = submitButton.getAttribute("disabled");

            // Nếu nút Submit bị vô hiệu hóa (disabled), bài kiểm thử fail
            if (disabledAttribute != null && disabledAttribute.equals("disabled")) {
                System.err.println("Test failed: The Submit button is disabled.");
                throw new AssertionError("Submit button should not be disabled, but it is disabled.");
            } else if (!submitButton.isEnabled()) {
                System.err.println("Test failed: The Submit button is not enabled.");
                throw new AssertionError("Submit button should be enabled, but it's not.");
            } else {
                // Nếu nút có thể nhấn, bài kiểm thử pass
                System.out.println("Test passed: The Submit button is enabled and clickable.");
            }

            // Quay lại nội dung chính sau khi kiểm tra
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            System.err.println("Error in clickSubmitButtonEnabled method: " + e.getMessage());
        }
    }

    // Method nhấn nút Submit


}