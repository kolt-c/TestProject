import framework.TestListener;
import framework.Utils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.MainPage;
import java.util.Random;

import static framework.BasePage.initPage;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners(TestListener.class)
public class MainPageTest {

    private Logger logger = LoggerFactory.getLogger(MainPageTest.class);

    @Test(groups = {"tests", "positive"})
    public void checkVisibilityOfBasicMainPageElements() {
        logger.info("Checking visibility of basic main page elements.");
        MainPage mainPage = initPage(MainPage.class);
        assertEquals(Utils.getCurrentUrl(), Utils.getBaseURL());
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

    @Test(groups = {"tests", "positive"})
    public void checkDefaultValuesOfBasicMainPageElements() {
        logger.info("Validating default values of basic main page elements.");
        MainPage mainPage = initPage(MainPage.class);
        assertEquals(Utils.getCurrentUrl(), Utils.getBaseURL());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(mainPage.getPageTitleText(), "Factorial", "Page title should be correct.");
        softAssert.assertEquals(mainPage.getGreatestFactorialText(), "The greatest factorial calculator!", "Text should be correct.");
        softAssert.assertEquals(mainPage.getGreatestFactorialColor(), "rgba(92, 184, 92, 1)", "Greatest factorial text color should be correct.");
        softAssert.assertEquals(mainPage.getNumberInputPlaceholderText(), "Enter an integer", "Number input placeholder text color should be correct.");
        softAssert.assertEquals(mainPage.getGetFactorialButtonText(), "Calculate!", "Get factorial button text should be equal.");
        softAssert.assertEquals(mainPage.getGetFactorialButtonTextColor(), "rgba(255, 255, 255, 1)", "Get factorial button text color should be equal.");
        softAssert.assertEquals(mainPage.getGetFactorialButtonBackgroundColor(), "rgba(92, 184, 92, 1)", "Get factorial button color should be equal.");
        softAssert.assertTrue(mainPage.isCopyrightTextCorrect(), "Copyright text should be correct.");
        softAssert.assertAll();
    }

    @Test(groups = {"tests", "positive"})
    public void verifyLinksOnMainPage() {
        logger.info("Verifying links on main page.");
        MainPage mainPage = initPage(MainPage.class);
        assertEquals(Utils.getCurrentUrl(), Utils.getBaseURL());
        String startUrl = Utils.getCurrentUrl();
        SoftAssert softAssert = new SoftAssert();

        mainPage.clickOnTermsAndConditionsLink();
        Utils.waitForUrlToBeChanged(startUrl, 5);
        softAssert.assertEquals(Utils.getCurrentUrl(), "http://qainterview.pythonanywhere.com/terms", "Terms and conditions link should be correct. ");
        Utils.navigateToURL(startUrl);

        mainPage.clickOnPrivacyLink();
        Utils.waitForUrlToBeChanged(startUrl, 5);
        softAssert.assertEquals(Utils.getCurrentUrl(), "http://qainterview.pythonanywhere.com/privacy", "Privacy link should be correct. ");
        Utils.navigateToURL(startUrl);

        mainPage.clickOnQxf2ServicesLink();
        Utils.waitForUrlToBeChanged(startUrl, 5);
        softAssert.assertEquals(Utils.getCurrentUrl(), "https://qxf2.com/?utm_source=qa-interview&utm_medium=click&utm_campaign=From%20QA%20Interview", "Qxf2 services link should be correct. ");
        Utils.navigateToURL(startUrl);

        softAssert.assertAll();
    }

    @Test(groups = {"tests", "positive"})
    public void checkFactorialCalculationWithValidValues() {
        int[] values = new int[]{0, 1, 21, 22, 170, 171, Utils.getRandomNumber(172,900)};
        MainPage mainPage = initPage(MainPage.class);
        SoftAssert softAssert = new SoftAssert();
        for (int value : values) {
            softAssert.assertTrue(isFactorialCalculationWithValueCorrect(mainPage, value), "Correct result should be shown.");
        }
        softAssert.assertAll();
    }

    public boolean isFactorialCalculationWithValueCorrect(MainPage mainPage, int value) {
        logger.info("Verifying factorial calculator with: " + value);

        mainPage.typeValueToNumberInput(String.valueOf(value)).clickCalculateButtonAndWaitForAjax();
        if (!mainPage.isResultVisible())
            return false;

        return mainPage.getResultText().equals(Utils.getExpectedResultText(value));
    }

    @Test(groups = {"tests", "negative"})
    public void checkFactorialCalculationWithNegativeInteger() {
        logger.info("Checking factorial calculation with negative integer.");
        MainPage mainPage = initPage(MainPage.class);

        mainPage.typeValueToNumberInput(String.valueOf(Utils.getRandomNumber(-20,-1))).clickCalculateButtonAndWaitForAjax();
        assertTrue(mainPage.isResultVisible() & mainPage.getResultText().equals("Please enter an integer"), "Error message should be shown.");
    }

    @Test(groups = {"tests", "negative"})
    public void checkFactorialCalculationWithNonIntegerNumber() {
        logger.info("Checking factorial calculation with negative integer.");
        MainPage mainPage = initPage(MainPage.class);

        mainPage.typeValueToNumberInput(String.valueOf(new Random().nextDouble())).clickCalculateButtonAndWaitForAjax();
        assertTrue(mainPage.isResultVisible() & mainPage.getResultText().equals("Please enter an integer"), "Error message should be shown.");
    }

    @Test(groups = {"tests", "negative"})
    public void checkFactorialCalculationWithNonNumber() {
        logger.info("Checking factorial calculation with non-number.");
        MainPage mainPage = initPage(MainPage.class);

        mainPage.typeValueToNumberInput(RandomStringUtils.randomAlphanumeric(8)).clickCalculateButtonAndWaitForAjax();
        assertTrue(mainPage.isResultVisible() & mainPage.getResultText().equals("Please enter an integer"), "Error message should be shown.");
    }

    @Test(groups = {"tests", "negative"})
    public void checkFactorialCalculationWithEmptyValue() {
        logger.info("Checking factorial calculation with empty value.");
        MainPage mainPage = initPage(MainPage.class);

        mainPage.clickCalculateButtonAndWaitForAjax();
        assertTrue(mainPage.isResultVisible() & mainPage.getResultText().equals("Please enter an integer"), "Error message should be shown.");
    }

    @Test(groups = {"tests", "negative"})
    public void checkFactorialCalculationWithAddingSpaceBeforeValidInteger() {
        logger.info("Checking factorial calculation with adding space before valid value.");
        MainPage mainPage = initPage(MainPage.class);
        int value = Utils.getRandomNumber(0,22);
        mainPage.typeValueToNumberInput(" " + value).clickCalculateButtonAndWaitForAjax();
        assertEquals(mainPage.getResultText(), Utils.getExpectedResultText(value), "Correct result should be shown.");
    }

    @Test(groups = {"tests", "negative"})
    public void checkFactorialCalculationWithAddingSpaceAfterValidInteger() {
        logger.info("Checking factorial calculation with adding space after valid value.");
        MainPage mainPage = initPage(MainPage.class);
        int value = Utils.getRandomNumber(0,22);
        mainPage.typeValueToNumberInput(value + " ").clickCalculateButtonAndWaitForAjax();
        assertEquals(mainPage.getResultText(), Utils.getExpectedResultText(value), "Correct result should be shown.");
    }
}
