package Pages;

import Base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class BuildWebSitePage extends BasePage {

    public BuildWebSitePage(WebDriver driver) {
        super(driver);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 13);
        PageFactory.initElements(factory, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Start building now')]")
    WebElement buildNowButton;

    @FindBy(xpath = "//*[@id='app']/div/div[2]/div/div/span/div/div[1]")
    WebElement webTemplate;

    @FindBy(xpath = "//button[contains(text(),'Use this template')]")
    WebElement useThisTemplateButton;

    @FindBy(xpath = "//div[@id='app']/div[3]/div/section/div/form/div/label[1]")
    WebElement businessLink;

    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    WebElement continueButton;

    @FindBy(xpath = "//*[@id='siteName']")
    WebElement siteNameTextBox;

    @FindBy(xpath = "//*[@id='businessDescription']")
    WebElement siteDescriptionTextArea;

    @FindBy(xpath = "//*[@id='app']/div[3]/div/section/div/div[1]/div[3]/div/span[2]")
    WebElement siteLink;

    @FindBy(xpath = "//*[@id='app']/div[3]/div/section/div/form/div[6]/div/div/input")
    WebElement socialUrl;

    @FindBy(xpath = "//button[contains(text(),'Finish setup')]")
    WebElement finishButton;

    @FindBy(xpath = "//button[@class='ws-floating-action__button' and @type='button']")
    WebElement addBlockIcon;

    @FindBy(xpath = "//li[contains(text(),'Video')]")
    WebElement videoLink;

    @FindBy(xpath = "//img[contains(@src,'https://editor.ws-platform.net/images/stubs/youtube.jpg')]")
    WebElement blockTitleLink;

    @FindBy(css = "div.ws-trigger__inner > svg")
    WebElement iFrameIcon;

    @FindBy(xpath = "//*[@src='/ws/block-templates/media/index.html']/section/div/ws-columns/ws-column/div[2]")
    WebElement moveElement;

    @FindBy(xpath = "//input[contains(@class, 'ws-input') and @type='text']")
    WebElement embeddedUrl;

    @FindBy(xpath = "//span[contains(text(),'Submit')]")
    WebElement submitButton;

    @FindBy(xpath = "//div[contains(text(),'Save')]")
    WebElement saveButton;

    @FindBy(xpath = "//*[@id='nav-menu']/div[1]/div[1]")
    WebElement menuItem;

    @FindBy(xpath = "//div[@id='nav-menu']/div/div[2]/div/div[2]/ul/li/span")
    WebElement goToDashboardLink;

    public void clickBuildNow(){
        waitForPageLoad(MIN_WAIT);
        buildNowButton.click();
    }

    public void selectTemplateAndType(){
        waitForPageLoad(AVG_WAIT);
        webTemplate.click();
        waitForPageLoad(MIN_WAIT);
        useThisTemplateButton.click();
        waitForPageLoad(MAX_WAIT);
        webDriver.switchTo().frame(0);
        businessLink.click();
        waitForPageLoad(MIN_WAIT);
        continueButton.click();
    }

    public void addSiteInformation(String siteName,String siteDescription){
        siteNameTextBox.sendKeys(siteName);
        siteDescriptionTextArea.sendKeys(siteDescription);
        continueButton.click();
    }

    public void moreInformation(){
        siteLink.click();
        continueButton.click();
    }

    public void addBusinessDetails(String businessUrl) {
        socialUrl.sendKeys(businessUrl);
        finishButton.click();
    }

    public void addYoutubeUrl(String youtubeUrl){
        waitForPageLoad(MIN_WAIT);
        addBlockIcon.click();
        videoLink.click();
        waitForPageLoad(MIN_WAIT);
        webDriver.switchTo().frame(1);
        blockTitleLink.click();
        webDriver.switchTo().defaultContent();
        waitForPageLoad(MIN_WAIT);
        webDriver.switchTo().frame(0).switchTo().frame(0);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,1200)");
        moveElement.click();
        webDriver.switchTo().parentFrame();
        waitForPageLoad(MIN_WAIT);
        iFrameIcon.click();
        embeddedUrl.clear();
        embeddedUrl.sendKeys(youtubeUrl);
        submitButton.click();
        saveButton.click();
    }

    public void gotoDashBoard(){
        menuItem.click();
        waitForPageLoad(MIN_WAIT);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", goToDashboardLink);
        Alert alert = webDriver.switchTo().alert();
        alert.accept();
    }
}
