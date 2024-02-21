package utils;

import config.WebAppDriverManager;
import core.WebApi;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;

public class TestNgWebCustomListener extends BaseTestNGListener {

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            PageFactoryManager.get(WebApi.class).switchToLastTab();
            File sc = ((TakesScreenshot) WebAppDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            Allure.addAttachment("Page source", WebAppDriverManager.getDriver().getPageSource());
            Allure.addAttachment("Failed test image", new FileInputStream(sc));
        } catch (Exception e) {
            LogHelper.getInstance().info("Cannot get screenshot");
            LogHelper.getInstance().info(e.getMessage());
        }
        super.onTestFailure(result);
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        try {
            PageFactoryManager.get(WebApi.class).switchToLastTab();
            File sc = ((TakesScreenshot) WebAppDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            Allure.addAttachment("Page source", WebAppDriverManager.getDriver().getPageSource());
            Allure.addAttachment("Failed test image", new FileInputStream(sc));
        } catch (Exception e) {
            LogHelper.getInstance().info("Cannot get screenshot");
            LogHelper.getInstance().info(e.getMessage());
        }
        super.onTestSkipped(result);
    }
}
