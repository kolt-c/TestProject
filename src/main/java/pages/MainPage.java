package pages;

import framework.BasePage;
import framework.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import static framework.Utils.*;
import static framework.WebDriverManager.getDriver;

public class MainPage extends BasePage {

    @FindBy(xpath = GREATEST_FACTORIAL_TEXT)
    WebElement greatestFactorialText;
    @FindBy(id = NUMBER_INPUT)
    WebElement numberInput;
    @FindBy(id = GET_FACTORIAL_BTN)
    WebElement getFactorialButton;
    @FindBy(xpath = TERMS_AND_CONDITIONS_LINK)
    WebElement termsAndConditionsLink;
    @FindBy(xpath = PRIVACY_LINK)
    WebElement privacyLink;
    @FindBy(xpath = COPYRIGHT_TEXT)
    WebElement copyrightText;
    @FindBy(xpath = QXF2_SERVICES_LINK)
    WebElement qxf2ServicesLink;
    @FindBy(xpath = RESULT_TEXT)
    WebElement resultText;


    public static final String GREATEST_FACTORIAL_TEXT = "//h1[@class='margin-base-vertical text-center']";
    public static final String NUMBER_INPUT = "number";
    public static final String GET_FACTORIAL_BTN = "getFactorial";
    public static final String TERMS_AND_CONDITIONS_LINK = "//a[text() = 'Terms and Conditions']";
    public static final String PRIVACY_LINK = "//a[text() = 'Privacy']";
    public static final String COPYRIGHT_TEXT = "//p[@class='margin-base-vertical text-center wor_copyright' and contains(text(), '©')]";
    public static final String QXF2_SERVICES_LINK = "//a[text() = 'Qxf2 Services']";
    public static final String RESULT_TEXT = "//p[@id='resultDiv']";

    public boolean isPageTitleCorrect() {
        logger.info("Asserting main page title.");
        return Utils.getPageTitleText().equals("Factoriall");
    }

    public boolean isGreatestFactorialTextVisible() {
        logger.info("Asserting Greatest factorial text visibility.");
        return isElementVisible(greatestFactorialText);
    }

    public boolean isNumberInputVisible() {
        logger.info("Asserting number input field visibility.");
        return isElementVisible(numberInput);
    }

    public boolean isGetFactorialButtonVisible() {
        logger.info("Asserting get factorial button visibility.");
        return isElementVisible(getFactorialButton);
    }

    public boolean isTermsAndConditionsLinkVisible() {
        logger.info("Asserting terms and conditions link visibility.");
        return isElementVisible(termsAndConditionsLink);
    }

    public boolean isPrivacyLinkLinkVisible() {
        logger.info("Asserting Privacy link visibility.");
        return isElementVisible(privacyLink);
    }

    public boolean isCopyrightTextVisible() {
        logger.info("Asserting copyright text visibility.");
        return isElementVisible(copyrightText);
    }

    public boolean isQxf2ServicesLinkVisible() {
        logger.info("Asserting Qxf2 services link visibility.");
        return isElementVisible(qxf2ServicesLink);
    }

}
