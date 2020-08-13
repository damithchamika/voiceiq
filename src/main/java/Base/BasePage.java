package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public class BasePage {
    private static final Logger LOG = Logger.getLogger(BasePage.class.getName());
    protected final String MAX_WAIT = "30000";
    protected final String AVG_WAIT = "10000";
    protected final String MIN_WAIT = "5000";
    protected WebDriver webDriver;
    protected WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.webDriver = driver;
    }

    private void wait(String waitTime) {
        try {
            Thread.sleep(Integer.parseInt(waitTime));
        } catch (InterruptedException e) {
            LOG.warning("Error loading on page" + e);
        }
    }
    public void waitForPageLoad(String waitType) {
        if (MAX_WAIT.equals(waitType)) {
            wait(MAX_WAIT);

        } else if (MIN_WAIT.equals(waitType)) {
            wait(MIN_WAIT);

        } else {
            wait(AVG_WAIT);
        }
    }
}
