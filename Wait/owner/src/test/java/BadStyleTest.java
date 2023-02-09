import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class BadStyleTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(BadStyleTest.class);

    @Test
    public void testBootstrap() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://ng-bootstrap.github.io/#/components/alert/examples");
        logger.info("Открыта страница https://ng-bootstrap.github.io/#/components/alert/examples");
        WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Change message')]"));

        logger.info("Найдена кнопка");
        String alertText = GetAlertText(element);
        logger.info(String.format("alertText %s", alertText));
        logger.info("Начато ожидание");

        Thread.sleep(1000);

        logger.info("Ожидание закончено");
        String alertText2 = GetAlertText(element);
        logger.info(String.format("alertText %s", alertText2));
        Assert.assertNotEquals(alertText, alertText2);
    }

    private String GetAlertText(WebElement element){
        element.click();
        WebElement alertBox = driver.findElement(By.xpath("//div[@class='card-body']//ngb-alert[contains(text(), 'Message successfully changed')]"));
        //ждём появления бокса
        new WebDriverWait(driver, 4).until(visibilityOf(alertBox));
        return alertBox.getText();
    }
}
