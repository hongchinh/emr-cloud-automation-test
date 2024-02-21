import action.CommonAction;
import api.MedicalSettingApi;
import config.Constant;
import config.Url;
import config.WebAppDriverManager;
import core.AbstractWebTest;
import core.WebApi;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.accounting.AccountingPage;
import pages.common.CommonPopUp;
import pages.common.LoginPage;
import pages.medical.MedicalPage;
import pages.medical.SaveOptionConfirmPopup;
import pages.visitinglist.VisitingListPage;
import utils.DownloadUtil;
import utils.PageFactoryManager;
import utils.PdfUtil;

@Feature("Reporting")
public class ReportingTests extends AbstractWebTest {

    private String ptId = "9669";

    @BeforeMethod(alwaysRun = true)
    public void a_resetMedicalSetting() throws Exception {
        PageFactoryManager.get(MedicalSettingApi.class).resetMedicalSetting();
    }

    @BeforeMethod(alwaysRun = true)
    public void b_unlockPatient() throws Exception {
        statementExecutor.unlockPatient(ptId);
    }

    @BeforeMethod(alwaysRun = true)
    public void d_loginAndSelectPatient() throws Exception {
        PageFactoryManager.get(WebApi.class)
                .openAnyUrl(Url.URL);
        PageFactoryManager.get(LoginPage.class)
                .verifyLoginPageDisplayed()
                .inputCredentials(Constant.DEFAULT_USERNAME, Constant.DEFAULT_PASSWORD)
                .clickSubmitButton();
        PageFactoryManager.get(CommonPopUp.class)
                .skipUpdateLogIfDisplayed();

        PageFactoryManager.get(CommonAction.class)
                .selectPatient(ptId);
    }

    @Test(groups = {"SMAR-TC-44", "SMAR-TC-47", "SMAR-TC-48", "reg-pdf"})
    public void TC_44_47_48_report_reception() throws Exception {
        //Add  disease and do before print
        PageFactoryManager.get(VisitingListPage.class)
                .clickMedicalBtn()
                .switchToTabNumber(2);
        boolean diseaseAlreadyAdded = PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed()
                .isDiseaseAlreadyAdded();
        if (!diseaseAlreadyAdded) {
            PageFactoryManager.get(MedicalPage.class)
                    .clickNextOrder()
                    .verifyNextOrderTabDisplayed()
                    .addNewDisease("皮1", "難病", "random disease name", "random comment")
                    .verifyLastDiseaseInfo("皮1", "難病", "random disease name", "random comment")
                    .clickDoAllInNextOrderTab()
                    .waitForLoadingIconToDisappear();
            PageFactoryManager.get(MedicalPage.class)
                    .clickSaveCalculationBtn();
            PageFactoryManager.get(CommonPopUp.class)
                    .clickAcceptButton()
                    .sleepTimeInSecond(2)
                    .switchToTabNumber(1);
        } else PageFactoryManager.get(VisitingListPage.class)
                .closeCurrentWindow()
                .switchToTabNumber(1);

        //44
        PageFactoryManager.get(VisitingListPage.class)
                .clickOpenReceptionUpdateBtn()
                .verifyReceptionInfoPopupDisplayed()
                .clickByomeiAndSave()
                .sleepTimeInSecond(3);
        String fileName = DownloadUtil.waitUntilDownloadCompleted();
        PdfUtil.verifyPdfTextContainAfterDownload(fileName, "患者病名一覧");
        PageFactoryManager.get(WebApi.class).switchToTabNumber(1);

        //47
        PageFactoryManager.get(VisitingListPage.class)
                .clickOpenReceptionUpdateBtn()
                .verifyReceptionInfoPopupDisplayed()
                .clickGhoshi1AndSave();
        fileName = DownloadUtil.waitUntilDownloadCompleted();
        PdfUtil.verifyPdfTextContainAfterDownload(fileName, "診 療 録");
        PageFactoryManager.get(WebApi.class).switchToTabNumber(1);

        //48
        PageFactoryManager.get(VisitingListPage.class)
                .clickOpenReceptionUpdateBtn()
                .verifyReceptionInfoPopupDisplayed()
                .clickGhoshi2AndSave();
        fileName = DownloadUtil.waitUntilDownloadCompleted();
        PdfUtil.verifyPdfTextContainAfterDownload(fileName, ptId);
    }

    @Test(groups = {"SMAR-TC-49", "SMAR-TC-50", "reg-pdf"})
    public void TC_49_50_report_medical() throws Exception {
        //49
        PageFactoryManager.get(VisitingListPage.class)
                .clickMedicalBtn()
                .switchToTabNumber(2);

        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed()
                .clickNextOrder()
                .verifyNextOrderTabDisplayed()
                .clickAddNewMedicineInNextOrderTab()
                .addNewMedicine("a", "分１　起床時", "1", "院内");
        PageFactoryManager.get(MedicalPage.class)
                .waitForLoadingIconToDisappear();
        PageFactoryManager.get(MedicalPage.class)
                .clickDoAllInNextOrderTab()
                .waitForLoadingIconToDisappear();


        PageFactoryManager.get(MedicalPage.class)
                .clickSaveOptionBtn();
        PageFactoryManager.get(SaveOptionConfirmPopup.class)
                .verifySaveOptionConfirmPopupDisplayed()
                .uncheckAllCheckboxes()
                .clickDrugInfoCheckbox()
                .clickSaveBtn()
                .sleepTimeInSecond(5)
                .waitForNumberOfTabsToBe(1)
                .switchToLastTab();
        String fileName = DownloadUtil.waitUntilDownloadCompleted();
        PdfUtil.verifyPdfTextContainAfterDownload(fileName, ptId);
        PageFactoryManager.get(WebApi.class)
                .switchToTabNumber(1);

        //50
        PageFactoryManager.get(VisitingListPage.class)
                .clickMedicalBtn()
                .switchToTabNumber(2);
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed();

        //Add out drug
        PageFactoryManager.get(MedicalPage.class)
                .clickNextOrder()
                .verifyNextOrderTabDisplayed()
                .clickAddNewMedicineInNextOrderTab()
                .addNewMedicine("a", "分１　起床時", "1", "院外");
        PageFactoryManager.get(MedicalPage.class)
                .waitForLoadingIconToDisappear();
        PageFactoryManager.get(MedicalPage.class)
                .clickDoAllInNextOrderTab()
                .waitForLoadingIconToDisappear();
        PageFactoryManager.get(MedicalPage.class)
                .clickSaveOptionBtn();
        PageFactoryManager.get(SaveOptionConfirmPopup.class)
                .verifySaveOptionConfirmPopupDisplayed()
                .uncheckAllCheckboxes()
                .clickOutDrugCheckbox()
                .clickSaveBtn()
                .sleepTimeInSecond(5)
                .waitForNumberOfTabsToBe(1)
                .switchToLastTab();
        fileName = DownloadUtil.waitUntilDownloadCompleted();
        PdfUtil.verifyPdfTextContainAfterDownload(fileName, "処 方 箋");

    }

    @Test(groups = {"SMAR-TC-51", "SMAR-TC-52", "reg-pdf"})
    public void TC_51_52_report_accounting() throws Exception {

        boolean isPaymentEnabled = PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .getStatusOfPayment();
        if (isPaymentEnabled) {
            PageFactoryManager.get(VisitingListPage.class)
                    .clickOpenPayment()
                    .switchToTabNumber(2);
        } else {
            PageFactoryManager.get(VisitingListPage.class)
                    .clickMedicalBtn()
                    .switchToTabNumber(2);
            PageFactoryManager.get(MedicalPage.class)
                    .verifyMedicalPageDisplayed()
                    .clickSaveCalculationBtn();
            PageFactoryManager.get(CommonPopUp.class)
                    .clickAcceptButton()
                    .switchToTabNumber(1);
            PageFactoryManager.get(VisitingListPage.class)
                    .verifyVisitingListPageDisplayed()
                    .clickOpenPayment()
                    .switchToTabNumber(2);

        }
        //if print button not displayed -> save then reopen
        boolean printBtnDisplayed = PageFactoryManager.get(AccountingPage.class)
                .verifyAccountingPageDisplayed()
                .printBtnDisplayed();

        if (!printBtnDisplayed) {
            PageFactoryManager.get(AccountingPage.class)
                    .uncheckAllPrintOptions()
                    .clickSaveAccounting()
                    .sleepTimeInMilSecond(500)
                    .switchToTabNumber(1);
            PageFactoryManager.get(VisitingListPage.class)
                    .verifyVisitingListPageDisplayed()
                    .clickOpenPayment()
                    .switchToTabNumber(2);
        }

        //51
        PageFactoryManager.get(AccountingPage.class)
                .verifyAccountingPageDisplayed()
                .scrollToBottomPage();
        PageFactoryManager.get(AccountingPage.class)
                .selectPrintReceiptAndPrint();
        String fileName = DownloadUtil.waitUntilDownloadCompleted();
        PdfUtil.verifyPdfTextContainAfterDownload(fileName, "診療費請求書");
        PdfUtil.verifyPdfTextContainAfterDownload(fileName, "兼");
        PdfUtil.verifyPdfTextContainAfterDownload(fileName, "領収証");

        //52
        PageFactoryManager.get(AccountingPage.class)
                .verifyAccountingPageDisplayed()
                .selectPrintDetailAndPrint();
        fileName = DownloadUtil.waitUntilDownloadCompleted();
        PdfUtil.verifyPdfTextContainAfterDownload(fileName, "診療費明細内訳");

    }
}
