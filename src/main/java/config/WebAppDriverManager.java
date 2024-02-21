package config;

import config.enums.DeviceMetrics;
import core.WebApi;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariDriver;
import utils.LogHelper;
import utils.PageFactoryManager;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class WebAppDriverManager {
    public static ThreadLocal<Boolean> responsiveStatus = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final String workingDir = System.getProperty("user.dir");
    @Getter
    @Setter
    private static Duration LONG_TIMEOUT = Duration.ofSeconds(30);
    @Getter
    private static Duration MILLI_TIMEOUT = Duration.ofMillis(400);
    @Getter
    @Setter
    private static Duration SHORT_TIMEOUT = Duration.ofSeconds(5);

    @Getter
    @Setter
    private static Duration MID_TIMEOUT = Duration.ofSeconds(15);

    @Getter
    @Setter
    private static Duration VERY_LONG_TIMEOUT = Duration.ofSeconds(90);

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static void openMultiBrowser(String browserName) {
        WebDriver tmpDriver = null;
        // init custom download folder
        new File(System.getProperty("user.dir") + "/downloadFile").mkdirs();
        System.setProperty("web.downloadPath", new File(System.getProperty("user.dir") + "/downloadFile").getPath());
        if (browserName.equalsIgnoreCase("hfirefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            System.setProperty("webdriver.firefox.marionette", "true");
            System.setProperty("webdriver.firefox.logfile", workingDir + "\\FirefoxLog.txt");

            FirefoxProfile firefoxProfile = new FirefoxProfile();
            firefoxProfile.setPreference("browser.download.folderList", 2);
            firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
            firefoxProfile.setPreference("browser.download.dir", System.getProperty("web.downloadPath"));
            firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");

            firefoxProfile.setPreference("pdfjs.disabled", true);

            // Use this to disable Acrobat plugin for previewing PDFs in Firefox (if you have Adobe reader installed on your computer)
            firefoxProfile.setPreference("plugin.scan.Acrobat", "99.0");
            firefoxProfile.setPreference("plugin.scan.plid.all", false);

            firefoxOptions.setProfile(firefoxProfile);

            tmpDriver = new FirefoxDriver(firefoxOptions);

        } else if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.getInstance(DriverManagerType.CHROME).clearResolutionCache();
            WebDriverManager.getInstance(DriverManagerType.CHROME).clearDriverCache();
            WebDriverManager.chromedriver().setup();
            ChromeOptions option = new ChromeOptions();
            option.addArguments("--no-sandbox");
            option.addArguments("--incognito");
            option.addArguments("--disable-extensions");
            option.addArguments("--disable-infobars");
            option.addArguments("window-size=1920,1080");
            String os = System.getProperty("os.name");
            if (os.toLowerCase().contains("linux")) {
                option.addArguments("--disable-dev-shm-usage");
                option.addArguments("--disable-gpu");
                option.addArguments("--disable-features=NetworkService");
                option.addArguments("--dns-prefetch-disable");
                option.addArguments("--disable-features=VizDisplayCompositor");

            }
            // Set custom download folder
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("download.directory_upgrade", true);
            chromePrefs.put("plugins.always_open_pdf_externally", true);
            chromePrefs.put("download.default_directory", System.getProperty("web.downloadPath"));
            chromePrefs.put("download.prompt_for_download", false);
            chromePrefs.put("plugins.plugins_disabled", "Chrome PDF Viewer");
            option.setExperimentalOption("prefs", chromePrefs);
            tmpDriver = new ChromeDriver(option);

        }
        else if(browserName.equalsIgnoreCase("chromeNoIncognito")){
            WebDriverManager.getInstance(DriverManagerType.CHROME).clearResolutionCache();
            WebDriverManager.getInstance(DriverManagerType.CHROME).clearDriverCache();
            WebDriverManager.chromedriver().setup();
            ChromeOptions option = new ChromeOptions();
            option.addArguments("--no-sandbox");
            option.addArguments("--headless=new");
            option.addArguments("--disable-extensions");
            option.addArguments("--disable-infobars");
            option.addArguments("window-size=1920,1080");
            String os = System.getProperty("os.name");
            if (os.toLowerCase().contains("linux")) {
                option.addArguments("--disable-dev-shm-usage");
                option.addArguments("--disable-gpu");
                option.addArguments("--disable-features=NetworkService");
                option.addArguments("--dns-prefetch-disable");
                option.addArguments("--disable-features=VizDisplayCompositor");
            }
            // Set custom download folder
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("download.directory_upgrade", true);
            chromePrefs.put("plugins.always_open_pdf_externally", true);
            chromePrefs.put("download.default_directory", System.getProperty("web.downloadPath"));
            chromePrefs.put("download.prompt_for_download", false);
            chromePrefs.put("plugins.plugins_disabled", "Chrome PDF Viewer");
            option.setExperimentalOption("prefs", chromePrefs);
            tmpDriver = new ChromeDriver(option);
        }
        else if (browserName.equalsIgnoreCase("hchrome")) {
            WebDriverManager.getInstance(DriverManagerType.CHROME).clearResolutionCache();
            WebDriverManager.getInstance(DriverManagerType.CHROME).clearDriverCache();
            WebDriverManager.chromedriver().setup();
            ChromeOptions option = new ChromeOptions();
            option.addArguments("--no-sandbox");
            option.addArguments("--headless=new");
            option.addArguments("--incognito");
            option.addArguments("--disable-extensions");
            option.addArguments("--disable-infobars");
            option.addArguments("window-size=1920,1080");
            String os = System.getProperty("os.name");
            if (os.toLowerCase().contains("linux")) {
                option.addArguments("--disable-dev-shm-usage");
                option.addArguments("--disable-gpu");
                option.addArguments("--disable-features=NetworkService");
                option.addArguments("--dns-prefetch-disable");
                option.addArguments("--disable-features=VizDisplayCompositor");
            }
            // Set custom download folder
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("download.default_directory", System.getProperty("web.downloadPath"));
            option.setExperimentalOption("prefs", chromePrefs);
            tmpDriver = new ChromeDriver(option);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            System.setProperty("webdriver.firefox.marionette", "true");
            System.setProperty("webdriver.firefox.logfile", workingDir + "\\FirefoxLog.txt");

            FirefoxProfile firefoxProfile = new FirefoxProfile();
            firefoxProfile.setPreference("browser.download.folderList", 2);
            firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
            firefoxProfile.setPreference("browser.download.dir", System.getProperty("web.downloadPath"));
            firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");

            firefoxProfile.setPreference("pdfjs.disabled", true);

            // Use this to disable Acrobat plugin for previewing PDFs in Firefox (if you have Adobe reader installed on your computer)
            firefoxProfile.setPreference("plugin.scan.Acrobat", "99.0");
            firefoxProfile.setPreference("plugin.scan.plid.all", false);

            firefoxOptions.setProfile(firefoxProfile);

            tmpDriver = new FirefoxDriver(firefoxOptions);
        } else if (browserName.equalsIgnoreCase("safari")) {
            tmpDriver = new SafariDriver();
        }
        setDriver(tmpDriver);
        responsiveStatus.set(false);
        getDriver().manage().timeouts().implicitlyWait(getLONG_TIMEOUT());
        getDriver().manage().window().maximize();
    }

    public static void openMultiBrowser(String browserName, DeviceMetrics metrics) {
        WebDriver tmpDriver = null;
        // init custom download folder
        new File(System.getProperty("user.dir") + "/downloadFile").mkdirs();
        System.setProperty("web.downloadPath", new File(System.getProperty("user.dir") + "/downloadFile").getPath());
        if (browserName.equalsIgnoreCase("hfirefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            System.setProperty("webdriver.firefox.marionette", "true");
            System.setProperty("webdriver.firefox.logfile", workingDir + "\\FirefoxLog.txt");
            tmpDriver = new FirefoxDriver(firefoxOptions);

        } else if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.getInstance(DriverManagerType.CHROME).clearResolutionCache();
            WebDriverManager.getInstance(DriverManagerType.CHROME).clearDriverCache();
            WebDriverManager.chromedriver().setup();
            ChromeOptions option = new ChromeOptions();
            option.addArguments("--incognito");
            option.addArguments("--disable-extensions");
            option.addArguments("--disable-infobars");
            String os = System.getProperty("os.name");
            if (os.toLowerCase().contains("linux")) {
                option.addArguments("--disable-dev-shm-usage");
                option.addArguments("--no-sandbox");
                option.addArguments("--disable-gpu");
                option.addArguments("--disable-features=NetworkService");
                option.addArguments("--dns-prefetch-disable");
                option.addArguments("--disable-features=VizDisplayCompositor");
            }

            Map<String, Object> deviceMetrics = new HashMap<>();
            deviceMetrics.put("width", metrics.getWidth());
            deviceMetrics.put("height", metrics.getHeight());
            deviceMetrics.put("pixelRatio", metrics.getRatio());

            Map<String, Object> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceMetrics", deviceMetrics);
            mobileEmulation.put("userAgent", metrics.getUserAgent());
            // Set custom download folder
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("download.default_directory", System.getProperty("web.downloadPath"));
            option.setExperimentalOption("prefs", chromePrefs);
            option.setExperimentalOption("mobileEmulation", mobileEmulation);
            tmpDriver = new ChromeDriver(option);

        } else if (browserName.equalsIgnoreCase("hchrome")) {
            WebDriverManager.getInstance(DriverManagerType.CHROME).clearResolutionCache();
            WebDriverManager.getInstance(DriverManagerType.CHROME).clearDriverCache();
            WebDriverManager.chromedriver().setup();
            ChromeOptions option = new ChromeOptions();
            option.addArguments("--headless");
            option.addArguments("--incognito");
            option.addArguments("--disable-extensions");
            option.addArguments("--disable-infobars");
            option.addArguments("window-size=1920,1080");
            String os = System.getProperty("os.name");
            if (os.toLowerCase().contains("linux")) {
                option.addArguments("--disable-dev-shm-usage");
                option.addArguments("--no-sandbox");
                option.addArguments("--disable-gpu");
                option.addArguments("--disable-features=NetworkService");
                option.addArguments("--dns-prefetch-disable");
                option.addArguments("--disable-features=VizDisplayCompositor");
            }
            Map<String, Object> deviceMetrics = new HashMap<>();
            deviceMetrics.put("width", metrics.getWidth());
            deviceMetrics.put("height", metrics.getHeight());
            deviceMetrics.put("pixelRatio", metrics.getRatio());

            Map<String, Object> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceMetrics", deviceMetrics);
            mobileEmulation.put("userAgent", metrics.getUserAgent());

            // Set custom download folder
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("download.default_directory", System.getProperty("web.downloadPath"));
            option.setExperimentalOption("prefs", chromePrefs);
            option.setExperimentalOption("mobileEmulation", mobileEmulation);
            tmpDriver = new ChromeDriver(option);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            System.setProperty("webdriver.firefox.marionette", "true");
            System.setProperty("webdriver.firefox.logfile", workingDir + "\\FirefoxLog.txt");
            // TODO: Could be add device metrics to FireFox Profile instead of extension
            tmpDriver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            tmpDriver = new SafariDriver();
        }
        setDriver(tmpDriver);
        responsiveStatus.set(true);
        getDriver().manage().timeouts().implicitlyWait(getLONG_TIMEOUT());
        getDriver().manage().timeouts().pageLoadTimeout(getLONG_TIMEOUT());
        getDriver().manage().window().maximize();
    }

    public static void closeBrowserAndDriver(WebDriver driver) {
        try {
            getDriver().manage().deleteAllCookies();
            PageFactoryManager.get(WebApi.class).closeAllWindows();
            getDriver().quit();
            LogHelper.getInstance().info("-------------QUIT BROWSER SUCCESSFULLY -----------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            LogHelper.getInstance().info(e.getMessage());
        }
    }

    public static void cleanAllBrowsers(WebDriver driver) {
        try {
            String driverName = Objects.requireNonNull(driver).toString().toLowerCase();
            String osName = System.getProperty("os.name").toLowerCase();
            LogHelper.getInstance().info("OS name= " + osName);
            String cmd = "";
            if (driverName.contains("chrome")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "killAll Google\\ Chrome";
                    executeCommand(cmd);
                    cmd = "killAll chromedriver";
                    executeCommand(cmd);
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI\"IMAGENAME eq chromedriver*\"";
                    executeCommand(cmd);
                }
            } else if (driverName.contains("safari")) {
                cmd = "killAll safaridriver";
                executeCommand(cmd);
                cmd = "killAll Safari";
                executeCommand(cmd);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + "cannot kill browser");
            LogHelper.getInstance().info(e.getMessage());
        }

    }

    private static void executeCommand(String cmd) throws InterruptedException, IOException {
        Process process = Runtime.getRuntime().exec(cmd);
        process.waitFor();
    }

}
