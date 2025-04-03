package pages.content;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;


public class Matching2Page {
    private WebDriver driver;
    private Actions actions;
    WebDriverWait wait;
    JavascriptExecutor js;
    private Random random = new Random();
    By drap = By.cssSelector("[id^='drag']");
    By drop = By.cssSelector("[id^='drop']");
    By btn_submit = By.cssSelector("button[class='btn-submit']");
    By btn_yessubmit =  By.xpath("//*[contains(text(), 'YES, SUBMIT NOW')]");

    // Constructor
    public Matching2Page(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public void drap_and_drop() {
        // Tìm tất cả phần tử có id bắt đầu với "drop"
        List<WebElement> drops = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(drop));

        // Tìm tất cả phần tử có id bắt đầu với "drag"
        List<WebElement> drags = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(drap));

        System.out.println("Số lượng drop: " + drops.size());
        System.out.println("Số lượng drag: " + drags.size());
        try {
            Thread.sleep(5000);  // Dừng lại 5 giây để trang ổn định
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (WebElement drop : drops) {
            if (drags.isEmpty()) break; // Nếu hết drag thì dừng lại
            int randomIndex = random.nextInt(drags.size()); // Chọn drag ngẫu nhiên
            WebElement randomDrag = drags.get(randomIndex);
            actions.clickAndHold(randomDrag)
                    .pause(Duration.ofSeconds(5))
                    .moveToElement(drop)
                    .pause(Duration.ofSeconds(5))
                    .release()
                    .perform();

            System.out.println("Đã thả " + randomDrag.getAttribute("id") + " vào " + drop.getAttribute("id"));
            drags.remove(randomIndex); // Xóa drag đã sử dụng
            // Chờ vài giây để UI cập nhật
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Kiểm tra lại vị trí sau khi kéo thả
        verifyElementMoved("parent-drag0");
        verifyElementMoved("parent-drag1");
    }

    // 🛠 Hàm kiểm tra nếu phần tử **đã rời khỏi** `box-answer-2`
    public void verifyElementMoved(String parentDragId) {
        try {
            WebElement answerBox = driver.findElement(By.id("box-answer-2"));
            List<WebElement> elementsInside = answerBox.findElements(By.id(parentDragId));

            if (elementsInside.isEmpty()) {
                System.out.println("✅ " + parentDragId + " đã di chuyển khỏi box-answer-2");
            } else {
                System.out.println("❌ " + parentDragId + " vẫn còn trong box-answer-2");
            }
        } catch (Exception e) {
            System.out.println("⚠ Không tìm thấy phần tử: " + parentDragId);
        }
    }

    public void clickSubmit (){
        if (driver.findElement(btn_submit).isDisplayed()){
            driver.findElement(btn_submit).click();}
        else {
            System.out.println("Click submit is fail");}
    }

    public void clickconfirmsubmit (){
        if (driver.findElement(btn_yessubmit).isDisplayed()){
            driver.findElement(btn_yessubmit).click();}
        else {
            System.out.println("Click confirm is fail");}
    }

}