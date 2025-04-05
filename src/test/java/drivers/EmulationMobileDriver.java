package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.EmulationConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

import static utils.FileUtils.getAbsolutePath;

public class EmulationMobileDriver implements WebDriverProvider {

    private static final EmulationConfig EMULATION_CONFIG = ConfigFactory.create(EmulationConfig.class, System.getProperties());
    private static final String DEVICE_NAME = EMULATION_CONFIG.deviceName();
    private static final String PLATFORM_NAME = EMULATION_CONFIG.platformName();
    private static final String VERSION = EMULATION_CONFIG.version();
    private static final String APP_PACKAGE = EMULATION_CONFIG.appPackage();
    private static final String APP_ACTIVITY = EMULATION_CONFIG.appActivity();
    private static final String APP = EMULATION_CONFIG.app();
    private static final String URL = EMULATION_CONFIG.remoteURL();
    private static final String AUTOMATION_NAME = EMULATION_CONFIG.automationName();

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("appium:deviceName", DEVICE_NAME);
        caps.setCapability("appium:platformName", PLATFORM_NAME);
        caps.setCapability("appium:version", VERSION);

        caps.setCapability("appium:appPackage", APP_PACKAGE);
        caps.setCapability("appium:appActivity", APP_ACTIVITY);
        caps.setCapability("appium:automationName", AUTOMATION_NAME);

        caps.setCapability("appium:app",
                getAbsolutePath(APP));

        try {
            return new RemoteWebDriver(
                    new URL(URL), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }
}
