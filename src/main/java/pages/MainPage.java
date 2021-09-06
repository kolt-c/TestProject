package pages;

import framework.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import static framework.Utils.*;

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

    public static final String GREATEST_FACTORIAL_TEXT = "//h1[@class='margin-base-vertical text-center']";
    public static final String NUMBER_INPUT = "number";
    public static final String GET_FACTORIAL_BTN = "getFactorial";
    public static final String TERMS_AND_CONDITIONS_LINK = "//a[text() = 'Terms and Conditions']";
    public static final String PRIVACY_LINK = "//a[text() = 'Privacy']";
    public static final String COPYRIGHT_TEXT = "//p[@class='margin-base-vertical text-center wor_copyright' and contains(text(), '©')]";
    public static final String QXF2_SERVICES_LINK = "//a[text() = 'Qxf2 Services']";

public MainPage assertVisibilityOfMainPageElements(){
    logger.info("Asserting visibility of main page elements.");
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(isElementVisible(greatestFactorialText), "Greatest factorial text should be visible.");
    softAssert.assertTrue(isElementVisible(numberInput), "Number input field should be visible.");
    softAssert.assertTrue(isElementVisible(getFactorialButton), "Get factorial button should be visible.");
    softAssert.assertTrue(isElementVisible(termsAndConditionsLink), "Terms and conditions link should be visible.");
    softAssert.assertTrue(isElementVisible(privacyLink), "Privacy link should be visible.");
    softAssert.assertTrue(isElementVisible(copyrightText), "Copyright text should be visible.");
    softAssert.assertTrue(isElementVisible(qxf2ServicesLink), "Qxf2 services link should be visible.");
    softAssert.assertAll();

    return this;
}

}
