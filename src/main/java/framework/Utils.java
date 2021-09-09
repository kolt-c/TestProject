package framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static framework.BasePage.logger;
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

    public static void waitForUrlToBeChanged(String oldUrl, int seconds) {
        logger.info("Waiting until URL is changed");
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), seconds);
        wait.until((ExpectedCondition<Object>) webDriver -> !Utils.getCurrentUrl().equals(oldUrl));
    }

    public static String getCurrentUrl() {
        return WebDriverManager.getDriver().getCurrentUrl();
    }

    public static void navigateToURL(String url){
        String oldUrl = Utils.getCurrentUrl();
        logger.info("Navigate to: " + url);
        WebDriverManager.getDriver().get(url);
        Utils.waitForUrlToBeChanged(oldUrl, 5);
    }

    public static void waitForAjaxRequestToBeFinished() {
        int sleepTime = 5000;
        JavascriptExecutor jse = (JavascriptExecutor) WebDriverManager.getDriver();
        try {
            for (int i = 0; i < 10; i++) {
                if ((Boolean) jse.executeScript(
                        "return document.readyState == 'complete' && window.jQuery != undefined && jQuery.active == 0")) {
                    return;
                }
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String  getPageTitleText(){
        return getDriver().getTitle();
    }

    public static String getBaseURL(){
        return new ConfigProvider().getBaseUrl();
    }

    public static long factorial(int number) {
        long result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }
}
