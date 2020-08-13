package Tests;


import Base.TestBase;
import Pages.BuildWebSitePage;
import Pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class CreateWebSiteTest extends TestBase {
    LoginPage loginPage;
    BuildWebSitePage buidWebSitePage;
    String eMail = "damith.chamika@gmail.com";
    String password = "damith2012";
    String siteName = "SITE NAME";
    String siteDescription = "SITE DESCRIPTION";
    String socialProfile = "https://www.facebook.com/viqiai/";
    String embeddedUrl = "https://www.youtube.com/watch?v=SQ4QOk89ucs";

    @BeforeMethod
    public void launchBrowser() {
        driver = initialization();
        navigateToUrl();
        loginPage = new LoginPage(driver);
        buidWebSitePage = new BuildWebSitePage(driver);
    }

    @Test
    public void testNavigate(){
        loginPage.clickLoginLink(eMail,password);
        buidWebSitePage.clickBuildNow();
        buidWebSitePage.selectTemplateAndType();
        buidWebSitePage.addSiteInformation(siteName,siteDescription);
        buidWebSitePage.moreInformation();
        buidWebSitePage.addBusinessDetails(socialProfile);
        buidWebSitePage.addYoutubeUrl(embeddedUrl);
        buidWebSitePage.gotoDashBoard();
    }
}
