package framework;


import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.MainPage;

public class BasePage {

    protected static Logger logger;

    public static <T> T initPage(Class<T> pageClass) {
        logger = LoggerFactory.getLogger(MainPage.class);
        return PageFactory.initElements(WebDriverManager.getDriver(), pageClass);
    }


}
