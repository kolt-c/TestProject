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
import static org.testng.Assert.assertTrue;

@Listeners(TestListener.class)
public class MainPageTest {

    private Logger logger = LoggerFactory.getLogger(MainPageTest.class);

    @Test
    public void checkBasicMainPageElements(){
        MainPage mainPage = initPage(MainPage.class);
        assertEquals(Utils.getCurrentUrl(), Utils.getBaseURL());
        assertTrue(mainPage.isPageTitleCorrect(), "Page title should be correct.");
        assertVisibilityOfBasicMainPageElements(mainPage);

    }

    public void assertVisibilityOfBasicMainPageElements(MainPage mainPage){
        logger.info("Asserting visibility of basic main page elements.");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(mainPage.isGreatestFactorialTextVisible(), "Greatest factorial text should be visible.");
        softAssert.assertTrue(mainPage.isNumberInputVisible(), "Number input field should be visible.");
        softAssert.assertTrue(mainPage.isGetFactorialButtonVisible(), "Get factorial button should be visible.");
        softAssert.assertTrue(mainPage.isTermsAndConditionsLinkVisible(), "Terms and conditions link should be visible.");
        softAssert.assertTrue(mainPage.isPrivacyLinkLinkVisible(), "Privacy link should be visible.");
        softAssert.assertTrue(mainPage.isCopyrightTextVisible(), "Copyright text should be visible.");
        softAssert.assertTrue(mainPage.isQxf2ServicesLinkVisible(), "Qxf2 services link should be visible.");
        softAssert.assertAll();
    }
}
