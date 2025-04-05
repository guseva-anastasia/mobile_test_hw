package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.RealConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

import static utils.FileUtils.getAbsolutePath;

public class RealMobileDriver implements WebDriverProvider {

    private static final RealConfig REAL_CONFIG = ConfigFactory.create(RealConfig.class, System.getProperties());
    private static final String DEVICE_NAME = REAL_CONFIG.deviceName();
    private static final String PLATFORM_NAME = REAL_CONFIG.platformName();
    private static final String VERSION = REAL_CONFIG.version();
    private static final String LOCALE = REAL_CONFIG.locale();
    private static final String LANGUAGE = REAL_CONFIG.language();
    private static final String APP_PACKAGE = REAL_CONFIG.appPackage();
    private static final String APP_ACTIVITY = REAL_CONFIG.appActivity();
    private static final String APP = REAL_CONFIG.app();
    private static final String URL = REAL_CONFIG.remoteURL();

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setCapability("deviceName", DEVICE_NAME);
        options.setCapability("platformName", PLATFORM_NAME);
        options.setCapability("version", VERSION);

        options.setCapability("locale", LOCALE);
        options.setCapability("language", LANGUAGE);

        options.setCapability("appPackage", APP_PACKAGE);
        options.setCapability("appActivity", APP_ACTIVITY);

        options.setCapability("app",
                getAbsolutePath(APP));

        try {
            return new AndroidDriver(
                    new URL(URL), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }
}
