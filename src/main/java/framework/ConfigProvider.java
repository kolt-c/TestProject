package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProvider {
    private static final String SELENIUM_BASEURL = "selenium.baseUrl";
    private static final String SELENIUM_BROWSER = "selenium.browser";
    private static final String SELENIUM_PROPERTIES = "selenium.properties";

    private String baseUrl;
    private BrowserType browser;
    private Properties properties = new Properties();

    public ConfigProvider() {
        properties = loadPropertiesFile();
        baseUrl = getProperty(SELENIUM_BASEURL);
        browser = BrowserType.Browser(getProperty(SELENIUM_BROWSER));
    }

    private String getProperty(String name) {
        String result;
        if ((result = System.getProperty(name, null)) != null && result.length() > 0) {
            return result;
        } else if ((result = getPropertyFromPropertiesFile(name)) != null && result.length() > 0) {
            return result;
        }
        return result;
    }

    private String getPropertyFromPropertiesFile(String name) {
        Object result = properties.get(name);
        if (result == null) {
            return null;
        } else {
            return result.toString();
        }
    }

    private Properties loadPropertiesFile() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(SELENIUM_PROPERTIES);
        Properties result = new Properties();
        try {
            result.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public WebDriver getDriver() {
        return getDriver(browser);
    }

    private WebDriver getDriver(BrowserType browserType) {
        switch (browserType) {
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "target//test-classes//geckodriver.exe");
                return new FirefoxDriver();
            default:
                System.setProperty("webdriver.chrome.driver", "target//test-classes//chromedriver.exe");
                return new ChromeDriver();
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
