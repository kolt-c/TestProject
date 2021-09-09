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
public class MainPageTest {

    private Logger logger = LoggerFactory.getLogger(MainPageTest.class);

    @Test(groups = {"tests", "basic"})
    public void checkBasicMainPageElements(){
        MainPage mainPage = initPage(MainPage.class);
        assertEquals(Utils.getCurrentUrl(), Utils.getBaseURL());
        assertVisibilityOfBasicMainPageElements(mainPage);
        validateDefaultValuesOfBasicMainPageElements(mainPage);
        verifyLinksOnMainPage(mainPage);
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

    public void validateDefaultValuesOfBasicMainPageElements(MainPage mainPage) {
        logger.info("Validating default values of basic main page elements.");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(mainPage.getPageTitleText(), "Factoriall", "Page title should be correct.");
        softAssert.assertEquals(mainPage.getGreatestFactorialText(), "The greatest factorial calculator!", "Text should be correct.");
        softAssert.assertEquals(mainPage.getGreatestFactorialColor(), "rgba(92, 184, 92, 1)", "Greatest factorial text color should be correct.");
        softAssert.assertEquals(mainPage.getNumberInputPlaceholderText(), "Enter an integer", "Number input placeholder text color should be correct.");
        softAssert.assertEquals(mainPage.getGetFactorialButtonText(),"Calculate!", "Get factorial button text should be equal.");
        softAssert.assertEquals(mainPage.getGetFactorialButtonTextColor(), "rgba(255, 255, 255, 1)", "Get factorial button text color should be equal.");
        softAssert.assertEquals(mainPage.getGetFactorialButtonBackgroundColor(), "rgba(92, 184, 92, 1)", "Get factorial button color should be equal.");
        softAssert.assertTrue(mainPage.isCopyrightTextCorrect(), "Copyright text should be correct.");
        softAssert.assertAll();
    }

    public void verifyLinksOnMainPage(MainPage mainPage){
        logger.info("Verifying links on main page.");
        String startUrl = Utils.getCurrentUrl();
        SoftAssert softAssert = new SoftAssert();

        mainPage.clickOnTermsAndConditionsLink();
        Utils.waitForUrlToBeChanged(startUrl, 5);
        softAssert.assertEquals(Utils.getCurrentUrl(), "http://qainterview.pythonanywhere.com/privacy", "Terms and conditions link should be correct. ");
        Utils.navigateToURL(startUrl);

        mainPage.clickOnPrivacyLink();
        Utils.waitForUrlToBeChanged(startUrl, 5);
        softAssert.assertEquals(Utils.getCurrentUrl(), "http://qainterview.pythonanywhere.com/terms", "Privacy link should be correct. ");
        Utils.navigateToURL(startUrl);

        mainPage.clickOnQxf2ServicesLink();
        Utils.waitForUrlToBeChanged(startUrl, 5);
        softAssert.assertEquals(Utils.getCurrentUrl(), "https://qxf2.com/?utm_source=qa-interview&utm_medium=click&utm_campaign=From%20QA%20Interview", "Qxf2 services link should be correct. ");
        Utils.navigateToURL(startUrl);

        softAssert.assertAll();
    }

    //in this calc valid positive numbers: 0 - 989
    @Test(groups = {"tests", "calculation"})
    public void checkFactorialCalculationWithValidValues() {
        MainPage mainPage = initPage(MainPage.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isFactorialCalculationWithValueCorrect(mainPage, 0), "Correct result should be shown.");
        softAssert.assertTrue(isFactorialCalculationWithValueCorrect(mainPage, 1), "Correct result should be shown.");
        softAssert.assertTrue(isFactorialCalculationWithValueCorrect(mainPage, 10), "Correct result should be shown.");
        softAssert.assertTrue(isFactorialCalculationWithValueCorrect(mainPage, 171), "Correct result should be shown.");
        softAssert.assertTrue(isFactorialCalculationWithValueCorrect(mainPage, 1710), "Correct result should be shown.");

        softAssert.assertAll();
    }

    public boolean isFactorialCalculationWithValueCorrect(MainPage mainPage, int value){
        String expectedResultText = "";
        logger.info("Verifying calculator with: " + value);

        mainPage.typeValueToNumberInput(String.valueOf(value)).clickCalculateButtonAndWaitForAjax();
        if (!mainPage.isResultVisible())
            return false;

        if(value >=0 & value < 22)
            expectedResultText = "The factorial of " + value + " is: " + String.valueOf(Utils.factorial(value));
        else if(value > 170)
            expectedResultText = "The factorial of " + value + " is: Infinity";

        System.out.println(expectedResultText);
        System.out.println(mainPage.getResultText());
        return mainPage.getResultText().equals(expectedResultText);
    }
    }
