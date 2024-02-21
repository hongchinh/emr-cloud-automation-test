package utils;

import config.WebAppDriverManager;
import core.WebApi;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;

public class DownloadUtil {

    public static String waitUntilDownloadCompleted() throws InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // Store the current window handle

        // open a new tab
        JavascriptExecutor js = (JavascriptExecutor) WebAppDriverManager.getDriver();
        js.executeScript("window.open()");
        // switch to new tab
        // Switch to new window opened
        PageFactoryManager.get(WebApi.class).switchToLastTab();
        // navigate to chrome downloads
        WebAppDriverManager.getDriver().get("chrome://downloads");

        JavascriptExecutor js1 = (JavascriptExecutor) WebAppDriverManager.getDriver();
        // wait until the file is downloaded
        Long percentage = (long) 0;
        int count = 0;
        while (percentage != 100 && count < 3) {
            try {
                percentage = (Long) js1.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('#progress').value");
                LogHelper.getInstance().info("Download progress: " + percentage.toString());
            } catch (Exception e) {
                count++;
            }
            PageFactoryManager.get(WebApi.class).sleepTimeInSecond(5);
        }
        // get the latest downloaded file name
        String fileName;
        try{
            fileName = (String) js1.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('div#content #file-link').text");
        }
        catch (JavascriptException e){
            PageFactoryManager.get(WebApi.class).sleepTimeInSecond(5);
            fileName = (String) js1.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('div#content #file-link').text");
        }
        // get the latest downloaded file url
        String sourceURL = (String) js1.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('div#content #file-link').href");
        // file downloaded location
        String downloadedAt = (String) js1.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('div.is-active.focus-row-active #file-icon-wrapper img').src");
        System.out.println("Download details");
        System.out.println("File Name :-" + fileName);
        System.out.println("Downloaded path :- " + downloadedAt);
        System.out.println("Downloaded from url :- " + sourceURL);
        // print the details
        System.out.println(fileName);
        System.out.println(sourceURL);
        // close the downloads tab2
        PageFactoryManager.get(WebApi.class)
                .closeCurrentWindow()
                .switchToLastTab();
        return fileName;
    }
}
