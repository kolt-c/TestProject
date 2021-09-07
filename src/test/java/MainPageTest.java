import framework.ConfigProvider;
import framework.TestListener;
import framework.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.MainPage;

import static framework.BasePage.initPage;
import static org.testng.Assert.assertEquals;

@Listeners(TestListener.class)
public class MyTest {

    private Logger logger = LoggerFactory.getLogger(MyTest.class);

    @Test
    public void checkBasicMainPageElements(){
        MainPage mainPage = initPage(MainPage.class);
        assertEquals(Utils.getCurrentUrl(), Utils.getBaseURL());
        assertEquals(Utils.getPageTitleText(), "Factoriall");
        mainPage.assertVisibilityOfBasicMainPageElements();

    }
}
