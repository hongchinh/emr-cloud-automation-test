import action.CommonAction;
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
import pages.common.ErrorPopup;
import pages.common.LoginPage;
import pages.medical.MedicalPage;
import pages.visitinglist.PatientSearchPopup;
import pages.visitinglist.ReceptionInfoPopup;
import pages.visitinglist.VisitingListPage;
import utils.DownloadUtil;
import utils.PageFactoryManager;
import utils.PdfUtil;

@Feature("Accounting")
public class AccountingTests extends AbstractWebTest {
    private String ptId = "100244200";

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

    @Test(groups = {"SMAR-TC-42", "reg-pdf"})
    public void TC_42_verify_actions_in_accounting_screen() throws Exception {

        //1. Check if button is enabled then open
        boolean isPaymentEnabled = PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .getStatusOfPayment();
        if(isPaymentEnabled){
            PageFactoryManager.get(VisitingListPage.class)
                    .clickOpenPayment()
                    .switchToTabNumber(2);
        }
        else {
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
        boolean errorDisplayed = PageFactoryManager.get(ErrorPopup.class)
                        .isErrorPopupDisplayed();
        if(errorDisplayed){
            PageFactoryManager.get(ErrorPopup.class)
                    .clickConfirmBtn()
                    .waitForNumberOfTabsToBe(1)
                    .switchToTabNumber(1);

            //Re save calculation

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
        PageFactoryManager.get(AccountingPage.class)
                .verifyAccountingPageDisplayed();

        //2
        PageFactoryManager.get(AccountingPage.class)
                .clickDocumentBtn()
                .verifyDocumentPopupDisplayed()
                .clickCloseBtn();
        //3
        PageFactoryManager.get(AccountingPage.class)
                .verifyAccountingPageDisplayed()
                .clickRecalculationBtn();
        //4
        PageFactoryManager.get(AccountingPage.class)
                .clickReloadBtn();
        //5
        PageFactoryManager.get(AccountingPage.class)
                .clickFullModeCheckbox();
        //6
        PageFactoryManager.get(AccountingPage.class)
                .clickOpenAccDuelListBtn()
                .verifyAccDuelListPopupDisplayed()
                .clickCloseButton();
        //7
        PageFactoryManager.get(AccountingPage.class)
                .verifyAccountingPageDisplayed()
                .selectHoken();
        //8
        boolean isEnabled = PageFactoryManager.get(AccountingPage.class)
                .isDischargeBtnEnabled();
        if(isEnabled){
            PageFactoryManager.get(AccountingPage.class)
                    .verifyAccountingPageDisplayed()
                    .uncheckAllPrintOptions()
                    .clickDischargeAccounting();
            PageFactoryManager.get(CommonPopUp.class)
                    .clickAcceptButton()
                    .switchToTabNumber(1);
            PageFactoryManager.get(VisitingListPage.class)
                    .verifyVisitingListPageDisplayed();

            PageFactoryManager.get(VisitingListPage.class)
                    .verifyVisitingListPageDisplayed()
                    .clickOpenPayment()
                    .switchToTabNumber(2);
        }
        //9
        PageFactoryManager.get(AccountingPage.class)
                .verifyAccountingPageDisplayed()
                .selectPrintReceiptAndPrint()
                .sleepTimeInMilSecond(3);
        String fileName = DownloadUtil.waitUntilDownloadCompleted();
        PdfUtil.verifyPdfTextContainAfterDownload(fileName, "診療費請求書");
        PdfUtil.verifyPdfTextContainAfterDownload(fileName, "兼");
        PdfUtil.verifyPdfTextContainAfterDownload(fileName, "領収証");
        //10
//        PageFactoryManager.get(AccountingPage.class)
//                .verifyAccountingPageDisplayed()
//                .clickSaveAccounting();

        //11
        PageFactoryManager.get(AccountingPage.class)
                .verifyAccountingPageDisplayed()
                .clickCancelAccounting()
                .switchToTabNumber(1);
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed();
    }
}
