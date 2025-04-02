package pages.content;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MCQvsGAP1Page {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By boxAnswer = By.className("box-item-answer");

    private final By SubmitButtonMCQ_Disabled = By.className("btn-submit-disabled");
    private final By SubmitButtonMCQ_Enabled = By.className("btn-submit");
    private final By InputAnswerGAP1 = By.className("gap-answer-warpper");
    private final By IframeLocator = By.tagName("iframe"); // Định danh iframe (tag name là ví dụ)

    private final By btnNextQuestion = By.className("btn-next");

    public MCQvsGAP1Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickBoxAnswer(int index) {
        try {
            // Chuyển vào iframe nếu phần tử nằm trong iframe
            List<WebElement> iframes = driver.findElements(IframeLocator);
            if (!iframes.isEmpty()) {
                System.out.println("Switching to iframe...");
                driver.switchTo().frame(iframes.getFirst()); // Chọn iframe đầu tiên (hoặc chỉnh theo yêu cầu)
            }

            // Lấy danh sách tất cả các boxAnswer
            List<WebElement> answers = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(boxAnswer));
            System.out.println("Found " + answers.size() + " answer boxes.");

            // Kiểm tra chỉ số hợp lệ
            if (index < 0 || index >= answers.size()) {
                throw new IndexOutOfBoundsException("Invalid index for boxAnswer: " + index);
            }

            // Click vào phần tử boxAnswer theo chỉ số
            WebElement answerToClick = answers.get(index);
            answerToClick.click();
            System.out.println("Clicked on boxAnswer at index " + index + ".");

            // Quay lại nội dung chính sau khi xử lý iframe (nếu đã chuyển vào iframe)
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            System.err.println("Error while clicking boxAnswer: " + e.getMessage());
        }
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
            List<WebElement> inputAnswers = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(InputAnswerGAP1));

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
            WebElement submitButton = driver.findElement(SubmitButtonMCQ_Disabled);

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

    public void clickNextQuestion() {
        try {
            List<WebElement> iframes = driver.findElements(IframeLocator);
            if (!iframes.isEmpty()) {
                System.out.println("Switching to iframe...");
                driver.switchTo().frame(iframes.get(0)); // Chọn iframe đầu tiên (hoặc chỉnh theo yêu cầu)
            }

            // Tìm nút Submit
            WebElement ButtonNextQuestion = driver.findElement(btnNextQuestion);

            ButtonNextQuestion.click(); // Click the item element
            System.out.println("Item clicked next Question successfully!");
        } catch (Exception e) {
            System.err.println("Error while clicking on the Question: " + e.getMessage());
        }
    }
}
