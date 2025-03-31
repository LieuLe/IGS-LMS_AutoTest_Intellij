package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SubjectPage {
    WebDriver driver;
    WebDriverWait wait;

    By btn_subject = By.className("subject-card");
    By btn_lessonpackage = By.xpath("//img[@src='/themes/web/assets/images/icon/btn-play.png']");
    By btn_lesson = By.xpath("//img[@src='/themes/web/assets/images/icon/lesson-package/icon-play.png']");
    By rd_answer = By.cssSelector("input[type='radio']");
    By cb_answer = By.cssSelector("input[type='checkbox']");
    By input_answer = By.cssSelector("input[type='text']");


    public SubjectPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
    }

    public void clickSubject() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_subject));
        WebElement subjectCard = driver.findElement(btn_subject);
        subjectCard.click();
        //Kiểm tra ulr subject
        wait.until(ExpectedConditions.urlContains("subject"));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickLessonPackage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_lessonpackage));
        WebElement lessonElement = driver.findElement(btn_lessonpackage);
        lessonElement.click();
        //Kiểm tra ulr lesson package
        wait.until(ExpectedConditions.urlContains("lesson-package"));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickLesson() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(btn_lesson));
        WebElement itemElement = driver.findElement(btn_lesson);
        itemElement.click();
        wait.until(ExpectedConditions.urlContains("lesson"));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

