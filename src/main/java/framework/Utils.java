package framework;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static framework.WebDriverManager.getDriver;

public class Utils {
    public static void waitForElementPresent(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static boolean isElementVisible(WebElement webElement){
        long timeLimitInSeconds = 2;
        try{
            WebDriverWait wait = new WebDriverWait(getDriver(), timeLimitInSeconds);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
