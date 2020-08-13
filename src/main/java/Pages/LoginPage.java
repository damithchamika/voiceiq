package Pages;

import Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 13);
        PageFactory.initElements(factory, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Login')]")
    WebElement loginLink;

    @FindBy(id = "um-login-email")
    WebElement loginEmail;
    
    @FindBy(id = "um-login-password")
    WebElement loginPassword;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement SignInButton;

    public void clickLoginLink(String eMail,String passWord){
        loginLink.click();
        waitForPageLoad(MIN_WAIT);
        loginEmail.sendKeys(eMail);
        loginPassword.sendKeys(passWord);
        waitForPageLoad(MIN_WAIT);
        SignInButton.click();
    }

}
