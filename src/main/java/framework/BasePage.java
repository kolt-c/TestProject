package framework;


import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public static <T> T initPage(Class<T> pageClass) {
        return PageFactory.initElements(WebDriverManager.getDriver(), pageClass);
    }

}
