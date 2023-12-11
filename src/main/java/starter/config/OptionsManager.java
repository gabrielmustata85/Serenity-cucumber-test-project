package starter.config;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import starter.models.Browser;

import java.io.File;
import java.util.HashMap;

public class OptionsManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(OptionsManager.class);
    private static final String RUN_HEADLESS = System.getProperty("headless");
    private static final Browser BROWSER_OPTIONS = new Browser().getBrowserTestData(System.getProperty("driver"));
    static final String DRIVER = System.getProperty("driver");
    static boolean areBrowserCapabilitiesSet = BROWSER_OPTIONS == null || BROWSER_OPTIONS.getArgs().isEmpty();

    //Get Chrome Options
    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        String downloadFilepath = System.getProperty("downloadPath");
        HashMap<String, Object> chromePrefs = new HashMap<>();

        if (downloadFilepath != null) {
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFilepath);
        }
        options.setExperimentalOption("prefs", chromePrefs);
        options.setExperimentalOption("useAutomationExtension", false);
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);


        if (DRIVER == null) {
            LOGGER.warn("Chrome will be started with default options");
            options.addArguments(getHeadlessStatus());
            options.addArguments("--window-size=1900,1600");
            options.addArguments("--no-sandbox");
            options.addArguments("--start-maximized");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-browser-side-navigation");
            options.addArguments("--disable-gpu");
            options.addArguments("--remote-allow-origins=*");
            options.setCapability("browserName", "chrome");
            return options;
        }
        LOGGER.info("Chrome will start with new configuration: {}", BROWSER_OPTIONS);
        options.setCapability("browserName", BROWSER_OPTIONS.getBrowserName());
        options.addArguments(BROWSER_OPTIONS.getArgs());
        return options;
    }

    //Get Firefox Options
    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();

        if ((DRIVER.equals("Firefox")) && areBrowserCapabilitiesSet) {
            LOGGER.warn("Firefox will be started with default options");
            options.setCapability("browserName", "firefox");
            options.addArguments(getHeadlessStatus());
            options.addArguments("--width=1900");
            options.addArguments("--height=1600");
            options.addArguments("--no-sandbox");
            options.addArguments("--start-maximized");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-browser-side-navigation");
            options.addArguments("--disable-gpu");
            //Accept Untrusted Certificates
            profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(false);
            //Use No Proxy Settings
            profile.setPreference("network.proxy.type", 0);
            //Set Firefox profile to capabilities
            return options;
        }
        LOGGER.info("Firefox will start with new configuration: {}", BROWSER_OPTIONS);
        options.setCapability("browserName", BROWSER_OPTIONS.getBrowserName());
        options.addArguments(BROWSER_OPTIONS.getArgs());
        profile.setAcceptUntrustedCertificates(BROWSER_OPTIONS.isWebdriverAcceptUntrustedCerts());
        profile.setAcceptUntrustedCertificates(BROWSER_OPTIONS.isWebdriverAcceptUntrustedCerts());
        return options;
    }

    //Get Safari Options
    public static SafariOptions getSafariOptions() {
        SafariOptions options = new SafariOptions();

        if ((DRIVER.equals("Safari")) && areBrowserCapabilitiesSet) {
            LOGGER.warn("Safari will be started with default options");
            options.setCapability("browserName", "safari");
            return options;
        }
        LOGGER.info("Safari will start with new configuration: {}", BROWSER_OPTIONS);
        options.setCapability("browserName", BROWSER_OPTIONS.getBrowserName());
        return options;
    }

    //Get Edge Options
    public static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();

        if ((DRIVER.equals("Edge")) && areBrowserCapabilitiesSet) {
            LOGGER.warn("Edge will be started with default options");
            options.addArguments(getHeadlessStatus());
            options.addArguments("--no-headless");
            options.addArguments("--window-size=1900,1600");
            options.addArguments("--no-sandbox");
            options.addArguments("--start-maximized");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-browser-side-navigation");
            options.addArguments("--disable-gpu");
            return options;
        }
        LOGGER.info("Edge will start with new configuration: {}", BROWSER_OPTIONS);
        options.addArguments(BROWSER_OPTIONS.getArgs());
        return options;
    }

    public static String getHeadlessStatus() {
        if (RUN_HEADLESS == null || !(RUN_HEADLESS.contains("true")))
            return "--no-headless";

        LOGGER.warn("HEADLESS mode is set ON!");
        return "--headless";
    }
}
