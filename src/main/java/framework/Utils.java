package framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import static framework.BasePage.logger;
import static framework.WebDriverManager.getDriver;

public class Utils {

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

    public static String getFactorialWithBigInteger(int number) {
        BigInteger n = BigInteger.valueOf(number);
        BigInteger result = BigInteger.ONE;

        while (!n.equals(BigInteger.ZERO)) {
            result = result.multiply(n);
            n = n.subtract(BigInteger.ONE);
        }

        NumberFormat formatter;
        if (number < 22)
            formatter = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ROOT));
        else if (number < 70)
            formatter = new DecimalFormat("0.0000000000000000E0", DecimalFormatSymbols.getInstance(Locale.ROOT));
        else
            formatter = new DecimalFormat("0.000000000000000E0", DecimalFormatSymbols.getInstance(Locale.ROOT));

        return formatter.format(new BigDecimal(result)).replace("E","e+");
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String getExpectedResultText(int value) {
        String expectedResultText = "";
        logger.info("Getting expected result text for: " + value);

        if (value >= 0 & value < 22)
            expectedResultText = "The factorial of " + value + " is: " + Utils.getFactorialWithBigInteger(value);
        if (value >= 22 & value < 171)
            expectedResultText = "The factorial of " + value + " is: " + Utils.getFactorialWithBigInteger(value);
        else if (value >= 171)
            expectedResultText = "The factorial of " + value + " is: Infinity";

        return expectedResultText;
    }
}
