import framework.TestListener;
import framework.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.reporters.jq.Main;
import pages.MainPage;

import static framework.BasePage.initPage;

@Listeners(TestListener.class)
public class MyTest {

    private Logger logger = LoggerFactory.getLogger(MyTest.class);

    @Test
    public void sampleTest(){
        MainPage mainPage = initPage(MainPage.class);
        mainPage.assertVisibilityOfMainPageElements();
    }
}
