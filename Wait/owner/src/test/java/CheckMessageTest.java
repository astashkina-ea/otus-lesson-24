import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckMessageTest {
    protected static WebDriver driver;
    private final Logger logger = LogManager.getLogger(CheckMessageTest.class);
    private final String  URL= "https://ng-bootstrap.github.io/#/components/alert/examples";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
    }

    @Test
    public void checkMessage() throws InterruptedException {
        By button = By.xpath("//button[contains(text(),'Change message')]") ;
        By message = By.xpath("//ngb-alert[contains(text(), 'Message successfully changed')]");
        driver.get(URL);

        JavascriptExecutor je = (JavascriptExecutor)driver;
        je.executeScript("arguments[0].scrollIntoView()", getElement(button));
        je.executeScript("arguments[0].click()", getElement(button));

        String textBefore = getElement(message).getText();

        Thread.sleep(1500);

        getElement(button).click();
        String textAfter = getElement(message).getText();

        Assert.assertNotEquals(textBefore, textAfter);
    }

    private WebElement getElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 4);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
