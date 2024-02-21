package core;

import api.IamApi;
import api.SettingApi;
import com.jcraft.jsch.JSchException;
import config.Url;
import config.WebAppDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.*;
import postgresql.DatabaseConnectionManager;
import postgresql.StatementExecutor;
import utils.LogHelper;
import utils.PageFactoryManager;
import utils.TestNgWebCustomListener;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Listeners({TestNgWebCustomListener.class})
public class AbstractWebTest {

    private final String workingDir = System.getProperty("user.dir");
    protected StatementExecutor statementExecutor = new StatementExecutor();

    @BeforeMethod(alwaysRun = true)
    public void startConnection() throws JSchException {
        if(Url.URL.contains("datadog")){
            DatabaseConnectionManager.startConnectionNoSSH();
        }
        else {
            DatabaseConnectionManager.startConnection();
        }
    }

    @BeforeMethod(alwaysRun = true, onlyForGroups = {"reg"})
    public void openBrowser() {
        WebAppDriverManager.openMultiBrowser(System.getProperty("smartkarte.browser"));
    }

    @BeforeMethod(alwaysRun = true, onlyForGroups = {"reg-pdf"})
    public void openChromeNoIncognito() {
        WebAppDriverManager.openMultiBrowser("chromeNoIncognito");
    }

    @BeforeMethod(alwaysRun = true)
    public void resetSettings() throws Exception {
        BaseApi.initReqSpec();
        PageFactoryManager.get(IamApi.class).login();
        PageFactoryManager.get(SettingApi.class).setInput();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        WebAppDriverManager.closeBrowserAndDriver(WebAppDriverManager.getDriver());
    }


    @BeforeSuite
    @Parameters({"browser", "env"})
    public void setUpBrowser(@Optional("chrome") String browser, @Optional("uat") String env) throws JSchException {
        System.setProperty("smartkarte.browser", browser);
        new Url(env);
    }

    @AfterMethod(alwaysRun = true)
    public void closePostgresqlConnection() throws SQLException {
        DatabaseConnectionManager.closeConnection();

    }


    @AfterSuite(alwaysRun = true)
    public void cleanUpBrowser() {
        WebAppDriverManager.cleanAllBrowsers(WebAppDriverManager.getDriver());
    }

    private boolean checkPassed(boolean condition) {
        boolean pass = true;
        try {
            if (condition) {
                LogHelper.getInstance().info("===PASSED===");
            } else {
                LogHelper.getInstance().warn("===FAILED===");
            }
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            pass = false;
        }
        return pass;
    }

    protected boolean verifyTrue(boolean condition) {
        return checkPassed(condition);
    }

    private boolean checkFailed(boolean condition) {
        boolean pass = true;
        try {
            if (!condition) {
                LogHelper.getInstance().info("===PASSED===");
            } else {
                LogHelper.getInstance().warn("===FAILED===");
            }
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        return checkFailed(condition);
    }

    private boolean checkEquals(Object actual, Object expected) {
        boolean pass = true;
        boolean status;
        try {
            if (actual instanceof String && expected instanceof String) {
                status = (actual.equals(expected));
            } else {
                status = (actual == expected);
            }

            LogHelper.getInstance().info("Compare value = " + status);
            if (status) {
                LogHelper.getInstance().info("===PASSED===");
            } else {
                LogHelper.getInstance().warn("===FAILED===");
            }
            Assert.assertEquals(actual, expected, "Value is not matching!");
        } catch (Throwable e) {
            pass = false;
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        return checkEquals(actual, expected);
    }

    public void sleepTimeInSecond(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sleepTimeInMilSecond(int milliSecond) {
        try {
            Thread.sleep(milliSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean verifySortAscending(String[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            int compare = list[i].compareTo(list[i + 1]);
            if (compare > 0) {
                return false;
            }
        }
        return true;

    }

    public boolean verifySortDescending(String[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            int compare = list[i].compareTo(list[i + 1]);
            if (compare < 0) {
                return false;
            }
        }
        return true;

    }

    public boolean verifyItemIncludeInList(String[] list, String itemNeedToCompare) {
        for (String eachItem : list) {
            if (itemNeedToCompare.equals(eachItem)) {
                return true;
            }
        }
        return false;

    }

    public void captureAnyScreenshot(String methodName) {
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
            File source = ((TakesScreenshot) WebAppDriverManager.getDriver()).getScreenshotAs(
                    OutputType.FILE);
            String screenPath = workingDir + "\\ProjectScreenShots\\" + methodName + "_"
                    + formatter.format(calendar.getTime()) + ".png";
            FileUtils.copyFile(source, new File(screenPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
