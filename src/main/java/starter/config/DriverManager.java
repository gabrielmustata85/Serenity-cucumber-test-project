package starter.config;

import com.google.common.base.CaseFormat;
import com.ibm.icu.impl.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class DriverManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<RemoteWebDriver> threadLocalDriver = new ThreadLocal<>();

    @Before
    public static synchronized void initWebDriver() {
        threadLocalDriver.set(setDriverFromConfig());
        if (threadLocalDriver.get() == null)
            Assert.fail("THE DRIVER IS NOT INITIALIZED, VERIFY MVN/VM -Driver PARAM ");
    }

    private static ChromeDriver setChromeDriver() {
        LOGGER.info("SET CHROME DRIVER ...");
        String chromeDriverPath = System.getProperty("chromeDriverPath");
        if (chromeDriverPath == null)
            WebDriverManager.chromedriver().setup();
        else {
            LOGGER.warn("The CHROME DRIVER is set manually using path {}", chromeDriverPath);
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        }
        return new ChromeDriver(OptionsManager.getChromeOptions());
    }

    private static FirefoxDriver setFirefoxDriver() {
        LOGGER.info("SET FIREFOX DRIVER ...");
        WebDriverManager.firefoxdriver().clearDriverCache().setup();
        return new FirefoxDriver(OptionsManager.getFirefoxOptions());
    }

    private static SafariDriver setSafariDriver() {
        LOGGER.info("SET SAFARI DRIVER ...");
        WebDriverManager.safaridriver().setup();
        return new SafariDriver(OptionsManager.getSafariOptions());
    }

    private static EdgeDriver setEdgeDriver() {
        LOGGER.info("SET EDGE DRIVER ...");
        WebDriverManager.edgedriver().clearDriverCache().setup();
        return new EdgeDriver(OptionsManager.getEdgeOptions());
    }

    private static RemoteWebDriver setRemoteDriver(String browser, String hubUrl) {
        LOGGER.info("SET REMOTE WEB DRIVER ...");
        switch (browser) {
            case "chrome":
                threadLocalDriver.set(setRemoteChromeDriver(hubUrl));
                break;
            case "firefox":
                threadLocalDriver.set(setRemoteFirefoxDriver(hubUrl));
                break;
        }
        return threadLocalDriver.get();
    }

    @SneakyThrows
    private static RemoteWebDriver setRemoteChromeDriver(String hubUrl) {
        LOGGER.info("SET CHROME REMOTE WEB DRIVER ...");
        LOGGER.info("Chrome is running on: {}", hubUrl);
        WebDriverManager.chromedriver().setup();
        return new RemoteWebDriver(new URL(hubUrl), OptionsManager.getChromeOptions());
    }

    @SneakyThrows
    private static RemoteWebDriver setRemoteFirefoxDriver(String hubUrl) {
        LOGGER.info("SET FIREFOX REMOTE WEB DRIVER ...");
        LOGGER.info("Firefox is running on: {}", hubUrl);
        WebDriverManager.firefoxdriver().setup();
        return new RemoteWebDriver(new URL(hubUrl), OptionsManager.getFirefoxOptions());
    }

    public static ChromeDriver setDriverManual() {
        final String driverBinaryPath = System.getProperty("Path");
        LOGGER.info("Driver binary path is: {}", driverBinaryPath);
        if (driverBinaryPath == null)
            Assert.fail("The binary for driver is null, double check the \"-DPath\" param");
        System.setProperty("webdriver.chrome.driver", driverBinaryPath);
        return new ChromeDriver(OptionsManager.getChromeOptions());
    }

    public static WebDriver getDriver() {
        if (threadLocalDriver.get() == null) {
            initWebDriver();
        }
        return threadLocalDriver.get();
    }

    @After
    public static void killDriver() {
        if (null != threadLocalDriver.get()) {
            threadLocalDriver.get().quit();
            threadLocalDriver.remove();
        }
    }

    private static RemoteWebDriver setDriverFromConfig() {
        final String DRIVER = System.getProperty("driver");
        if (DRIVER == null) {
            LOGGER.warn("DRIVER IS NOT SET FROM THE MVN/VM CONFIG, IS USED DEFAULT DRIVER (CHROME)");
            return setChromeDriver();
        }
        LOGGER.info("Get the driver from MVN/VM -Ddriver={}", DRIVER);

        switch (DRIVER) {
            case "Chrome":
                return setChromeDriver();
            case "Safari":
                return setSafariDriver();
            case "Firefox":
                return setFirefoxDriver();
            case "Edge":
                return setEdgeDriver();
            case "Manual":
                return setDriverManual();
            default:
                return threadLocalDriver.get();
        }
    }

    public static String getDriverName() {
        Capabilities cap = threadLocalDriver.get().getCapabilities();
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, cap.getBrowserName());
    }
}
