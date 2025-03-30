package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebDriver;
import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;


public class BrowserstackDriver implements WebDriverProvider {

    private static final BrowserstackConfig config = ConfigFactory.create(
            BrowserstackConfig.class,
            System.getProperties()
    );

    private static final String USER = config.browserstackUser();
    private static final String KEY = config.browserstackKey();
    private static final String APP = config.app();
    private static final String URL = config.browserstackUrl();
    private static final String DEVICE = config.device();
    private static final String OS_VERSION = config.osVersion();
    private static final String PROJECT = config.projectName();
    private static final String BUILD = config.buildName();
    private static final String NAME = config.name();


    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", USER);
        caps.setCapability("browserstack.key", KEY);

        // Set URL of the application under test
        caps.setCapability("app", APP);

        // Specify device and os_version for testing
        caps.setCapability("device", DEVICE);
        caps.setCapability("os_version", OS_VERSION);

        // Set other BrowserStack capabilities
        caps.setCapability("project", PROJECT);
        caps.setCapability("build", BUILD);
        caps.setCapability("name", NAME);
        caps.setCapability("url", URL);

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        try {
            return new RemoteWebDriver(
                    new URL(config.browserstackUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
