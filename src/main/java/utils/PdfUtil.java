package utils;

import config.WebAppDriverManager;
import core.WebApi;
import io.qameta.allure.Step;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PdfUtil {

    @Step
    public static String getPdfContent(String url) throws IOException {

        URL pdfURL = new URL(url);

        InputStream is = pdfURL.openStream();

        BufferedInputStream bis = new BufferedInputStream(is);

        PDDocument doc = PDDocument.load(bis);

        PDFTextStripper strip = new PDFTextStripper();

        String stripText = strip.getText(doc);

        doc.close();

        return stripText;
    }
    @Step
    public static String getPdfContentFromFile(String fileName) throws IOException, InterruptedException {

        File file = new File(System.getProperty("web.downloadPath") + "/" + fileName);

        PDDocument doc = PDDocument.load(file, MemoryUsageSetting.setupMainMemoryOnly());

        PDFTextStripper strip = new PDFTextStripper();

        String stripText = strip.getText(doc);

        doc.close();

        return stripText;
    }
    @Step
    public void verifyPdfTextContain(String expectedText) throws IOException {
        String content = PdfUtil.getPdfContent(WebAppDriverManager.getDriver().getCurrentUrl());
        boolean contain = content.contains(expectedText);
        Assert.assertTrue(contain);
    }
    @Step
    public static void verifyPdfTextContainAfterDownload(String fileName, String expectedText) throws IOException, InterruptedException {
        String content = PdfUtil.getPdfContentFromFile(fileName);
        boolean contain = content.contains(expectedText);
        Assert.assertTrue(contain);
    }
}
