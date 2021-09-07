package framework;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private Logger logger = LoggerFactory.getLogger(WebDriverManager.class);

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    public void prepareWebDriver() {
        ConfigProvider config = new ConfigProvider();
        WebDriver driver = config.getDriver();
        setWebDriver(driver);
        logger.info("WebDriver started.");
        driver.get(config.getBaseUrl());
        logger.info("Navigate to: " + config.getBaseUrl());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public static void closeDriver() {
        if (!getDriver().equals(null)) getDriver().quit();
    }

}
