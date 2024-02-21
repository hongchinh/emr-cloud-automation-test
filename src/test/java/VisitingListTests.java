import action.CommonAction;
import config.Constant;
import config.Url;
import core.AbstractWebTest;
import core.WebApi;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.accounting.AccountingPage;
import pages.common.CommonPopUp;
import pages.common.LoginPage;
import pages.medical.MedicalPage;
import pages.patientinfo.PatientInfoPage;
import pages.visitinglist.*;
import utils.CommonUtil;
import utils.DateUtil;
import utils.PageFactoryManager;

@Feature("Visiting List")
public class VisitingListTests extends AbstractWebTest {
    private String ptId = "100244197";
    private String partialId = "1866";
    private String ptName = "auto";
    private String lastHeader = "26";
    private String nearLastHeader = "25";

    @BeforeMethod(alwaysRun = true)
    public void unlockPatient() throws Exception {
        statementExecutor.unlockPatient(ptId);
    }

    @BeforeMethod(alwaysRun = true)
    public void login() throws Exception {
        PageFactoryManager.get(WebApi.class)
                .openAnyUrl(Url.URL);
        PageFactoryManager.get(LoginPage.class)
                .verifyLoginPageDisplayed()
                .inputCredentials(Constant.DEFAULT_USERNAME, Constant.DEFAULT_PASSWORD)
                .clickSubmitButton();
        PageFactoryManager.get(CommonPopUp.class)
                .skipUpdateLogIfDisplayed();

    }

    @Test(groups = {"SMAR-TC-34", "reg"})
    public void TC_34_UI() throws Exception {
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .verifyHeaders()
                .verifyPatientVisitButtons();

    }
    @Test(groups = {"SMAR-TC-36", "reg"})
    public void TC_36_config_column() throws Exception {
        String japDate = DateUtil.getJapaneseDate(2);
        String westernDate = DateUtil.getWesternDate(2);

        PageFactoryManager.get(CommonAction.class)
                .selectPatient(ptId);

        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .selectPatient(ptId)
                .insertColumnForSelectedPatient("uketukeNo", String.valueOf(CommonUtil.getRandomIntegerBetweenRange(10,999)), false)
                .selectOptionInSelectedColumn("uketukeSbtId", "夜深夜")
                .scrollToRightOfTable(0.3)
                .insertColumnForSelectedPatient("uketukeTime", "11:" + CommonUtil.getRandomIntegerBetweenRange(11, 59), false)
                .insertColumnForSelectedPatient("sinStartTime", "11:" + CommonUtil.getRandomIntegerBetweenRange(11, 59), false)
                .scrollToRightOfTable(0.4)
                .insertColumnForSelectedPatient("ptComment", "I'm sick " + CommonUtil.getRandomString(5), true)
                .scrollToRightOfTable(0.4)
                .selectOptionInSelectedColumn("tantoId", "中島Dr")
                .scrollToRightOfTable(0.4)
                .selectOptionInSelectedColumn("kaId", "眼科")
                .scrollToRightOfTable(0.4)
                .insertColumnForSelectedPatient("raiinRemark", "He is sick " + CommonUtil.getRandomString(5), false)

                .scrollToRightOfTable(-1)

                //13
                .clickSortAndVerify()
//                .clickSortAndVerify("精算済み")
//                .clickSortAndVerify("診察待ち")
//                .clickSortAndVerify("精算待ち")
                //14
                .expandSortingOptionsAndVerify()
                //15
                .selectSortSelect("受付種別", "夜深夜")
                .verifyPatientIdDisplayed(ptId)
                .selectSortSelect("担当医", "中島Dr")
                .verifyPatientIdDisplayed(ptId)
                .selectSortSelect("診療科", "眼科")
                .verifyPatientIdDisplayed(ptId)
                //Reset sort
                .selectSortSelect("受付種別", "すべて")
                .selectSortSelect("担当医", "すべて")
                .selectSortSelect("診療科", "すべて")


                //17
                .scrollToRightOfTable(1)
                .rightClickHeader(lastHeader)
                .selectHeaderOption("フィールドの非表示")
                .verifyHeaderNotDisplayed(lastHeader)
                //18
                .rightClickHeader(nearLastHeader)
                .selectHeaderOption("フィールドの再表示")
                .selectHeaderCheckbox(lastHeader)
                .scrollToRightOfTable(1)
                .verifyHeaderDisplayed(lastHeader);

        int originalPosition = PageFactoryManager.get(VisitingListPage.class)
                .getHeaderPosition(lastHeader);
                //19
        PageFactoryManager.get(VisitingListPage.class)
                .rightClickHeader(lastHeader)
                .selectHeaderOption("フィールドの固定")
                .scrollToRightOfTable(-1)
                .verifyHeaderPosition(lastHeader, 9)
                //20
                .rightClickHeader(lastHeader)
                .selectHeaderOption("フィールドの固定解除")
                .scrollToRightOfTable(1)
                .verifyHeaderPosition(lastHeader,originalPosition)
                //21
                .scrollToRightOfTable(1)
                .rightClickHeader(lastHeader)
                .selectHeaderOption("フィールドの固定")
                .scrollToRightOfTable(-1)
                .verifyHeaderPosition(lastHeader, 9)
                .rightClickHeader(lastHeader)
                .selectHeaderOption("すべてのフィールドの固定解除")
                .scrollToRightOfTable(1)
                .verifyHeaderPosition(lastHeader,originalPosition)
                //22
                .scrollToRightOfTable(-1)
                .selectSortingCheckbox("予約")
                .verifySelectedHeader("status")
                .selectSortingCheckbox("予約")
                .verifyUnselectedHeader("status")
                .rightClickHeader("status")

                //24
                .clickHeader("uketukeNo")
                .verifySelectedHeader("uketukeNo")
                .clickHeader("uketukeNo")
                .verifyUnselectedHeader("uketukeNo")
                .rightClickHeader("uketukeNo")

                //26
                .clickHeader("sameVisit")
                .verifySelectedHeader("sameVisit")
                .clickHeader("sameVisit")
                .verifyUnselectedHeader("sameVisit")
                .rightClickHeader("sameVisit")

                //28
                .clickHeader("sex")
                .selectSortingCheckbox("女")
                .verifySelectedHeader("sex")
                .clickHeader("sex")
                .selectSortingCheckbox("(すべて選択)")
                .verifyUnselectedHeader("sex")

                //30
                .clickHeader("nameDuplicateState")
                .verifySelectedHeader("nameDuplicateState")
                .clickHeader("nameDuplicateState")
                .verifyUnselectedHeader("nameDuplicateState")
                .rightClickHeader("nameDuplicateState")

                //32
                .clickHeader("yoyakuTime")
                .verifySelectedHeader("yoyakuTime")
                .clickHeader("yoyakuTime")
                .verifyUnselectedHeader("yoyakuTime")
                .rightClickHeader("yoyakuTime")

                //34
                .scrollToRightOfTable(-1)
                .scrollToRightOfTable(0.2)
                .clickHeader("uketukeSbtId")
                .selectSortingCheckbox("その他")
                .verifySelectedHeader("uketukeSbtId")
                .clickHeader("uketukeSbtId")
                .selectSortingCheckbox("(すべて選択)")
                .verifyUnselectedHeader("uketukeSbtId")

                //36
                .clickHeader("reservationName")
                .verifySelectedHeader("reservationName")
                .clickHeader("reservationName")
                .verifyUnselectedHeader("reservationName")
                .rightClickHeader("reservationName")

                //38
                .scrollToRightOfTable(0.3)
                .clickHeader("uketukeTime")
                .verifySelectedHeader("uketukeTime")
                .clickHeader("uketukeTime")
                .verifyUnselectedHeader("uketukeTime")
                .rightClickHeader("uketukeTime")


                //40
                .clickHeader("sinStartTime")
                .verifySelectedHeader("sinStartTime")
                .clickHeader("sinStartTime")
                .verifyUnselectedHeader("sinStartTime")
                .rightClickHeader("sinStartTime")

                //42
                .clickHeader("sinEndTime")
                .verifySelectedHeader("sinEndTime")
                .clickHeader("sinEndTime")
                .verifyUnselectedHeader("sinEndTime")
                .rightClickHeader("sinEndTime")

                //44
                .clickHeader("kaikeiTime")
                .verifySelectedHeader("kaikeiTime")
                .clickHeader("kaikeiTime")
                .verifyUnselectedHeader("kaikeiTime")
                .rightClickHeader("kaikeiTime")

                //46
                .clickHeader("raiinCmt")
                .verifySelectedHeader("raiinCmt")
                .clickHeader("raiinCmt")
                .verifyUnselectedHeader("raiinCmt")
                .rightClickHeader("raiinCmt")

                //48
                .clickHeader("ptComment")
                .verifySelectedHeader("ptComment")
                .clickHeader("ptComment")
                .verifyUnselectedHeader("ptComment")
                .rightClickHeader("ptComment")

                //50
                .clickHeader("tantoId")
                .selectSortingCheckbox("中島Dr")
                .verifySelectedHeader("tantoId")
                .clickHeader("tantoId")
                .selectSortingCheckbox("(すべて選択)")
                .verifyUnselectedHeader("tantoId")

                //52
                .clickHeader("kaId")
                .selectSortingCheckbox("眼科")
                .verifySelectedHeader("kaId")
                .clickHeader("kaId")
                .selectSortingCheckbox("(すべて選択)")
                .verifyUnselectedHeader("kaId")

                //54
                .clickHeader("lastVisitDate")
                .verifySelectedHeader("lastVisitDate")
                .clickHeader("lastVisitDate")
                .verifyUnselectedHeader("lastVisitDate")
                .rightClickHeader("lastVisitDate")

                //56
                .clickHeader("sname")
                .verifySelectedHeader("sname")
                .clickHeader("sname")
                .verifyUnselectedHeader("sname")
                .rightClickHeader("sname")

                //58
                .clickHeader("raiinRemark")
                .verifySelectedHeader("raiinRemark")
                .clickHeader("raiinRemark")
                .verifyUnselectedHeader("raiinRemark")
                .rightClickHeader("raiinRemark")
                //64
                .selectDate(japDate)
                .verifyDate(westernDate)
                .sleepTimeInSecond(2);

                //65
        String oldReceipt = PageFactoryManager.get(VisitingListPage.class).getReceipt();

        PageFactoryManager.get(VisitingListPage.class)
                .clickChangeReceiptBtn();
        PageFactoryManager.get(CommonPopUp.class)
                .clickDeclineButton();

        String newReceipt = PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .getReceipt();
        Assert.assertEquals(newReceipt, oldReceipt);

        PageFactoryManager.get(VisitingListPage.class)
                .clickChangeReceiptBtn();
        PageFactoryManager.get(CommonPopUp.class)
                .clickAcceptButton();

        newReceipt = PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .getReceipt();
        Assert.assertNotEquals(newReceipt, oldReceipt);
    }
    @Test(groups = {"SMAR-TC-37", "reg"})
    public void TC_37_open_screens_from_visiting_list() throws Exception {
        //1
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickAddPatientBtn()
                .switchToTabNumber(2);
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                .closeCurrentWindow()
                .switchToTabNumber(1);
        //2
        PageFactoryManager.get(VisitingListPage.class)
                .clickSearchPatientIcon()
                .verifyPatientSearchPopupDisplayed()
                .clickCloseBtn()
                .verifyVisitingListPageDisplayed()
        //3
                .clickAddNewVisitingBtn()
                .verifyAddVisitingPopupDisplayed()
                .clickCloseBtn()
                .verifyVisitingListPageDisplayed()
        //4
                .clickAddChartBtn()
                .verifyAddNewChartPopupDisplayed()
                .clickCloseBtn()
                .verifyVisitingListPageDisplayed()
        //5
                .clickAddMedicalRecordBtn()
                .verifyAddNewMedicalRecordPopupDisplayed()
                .clickCloseBtn()
                .verifyVisitingListPageDisplayed();
        //6
        PageFactoryManager.get(VisitingListPage.class)
                .searchForPatientId(partialId);
        PageFactoryManager.get(PatientSearchPopup.class)
                .verifyPatientSearchPopupDisplayed()
                .clickCloseBtn()
                .verifyVisitingListPageDisplayed();
        //7
        PageFactoryManager.get(VisitingListPage.class)
                .inputPatientId(ptName)
                .clickAddNewVisitingBtn();
        PageFactoryManager.get(AddNewVisitingPopup.class)
                .verifyAddVisitingPopupDisplayed()
                .verifyListOfResultsDisplayed()
                .clickCloseBtn()
                .verifyVisitingListPageDisplayed();
        //8
        PageFactoryManager.get(VisitingListPage.class)
                .inputPatientId(ptName)
                .clickAddChartBtn();
        PageFactoryManager.get(AddNewChartPopup.class)
                .verifyAddNewChartPopupDisplayed()
                .verifyListOfResultsDisplayed()
                .clickCloseBtn()
                .verifyVisitingListPageDisplayed();
        //9
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickSettingBtn()
                .clickShowVisitingListBtn()
                .verifyVisitingListPopupDisplayed()
                .clickCloseBtn();
        //10
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickSettingBtn()
                .clickShowModalKubunSettingBtn()
                .verifyModalKubunSettingPopupDisplayed()
                .clickCloseBtn();
        //11
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickSettingBtn()
                .clickShowVisitingFilterBtn()
                .verifyVisitingFilterPopupDisplayed()
                .clickCloseBtn();
        //
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickSettingBtn()
                .clickShowVisitingTodoBtn()
                .verifyVisitingTodoPopupDisplayed()
                .clickCloseBtn();
        //12
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickSettingBtn()
                .clickShowApprovalInfoBtn()
                .verifyVisitingApprovalPopupDisplayed()
                .clickCloseBtn();
        //13
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .selectPatient(ptId)
                .clickMedicalBtn()
                .switchToTabNumber(2);
        PageFactoryManager.get(CommonPopUp.class)
                .skipPopUpsNoWaits();
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed()
                .closeCurrentWindow()
                .switchToTabNumber(1);
        //14 disable because it is tested on other class already
//        PageFactoryManager.get(VisitingListPage.class)
//                .verifyVisitingListPageDisplayed()
//                .selectPatient(ptId)
//                .openPatientInfo()
//                .switchToTabNumber(2);
//        PageFactoryManager.get(PatientInfoPage.class)
//                .verifyPatientInfoPageDisplayed()
//                .closeCurrentWindow()
//                .switchToTabNumber(1);
        //15
        boolean isPaymentEnabled = PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .getStatusOfPayment();
        if(isPaymentEnabled){
            PageFactoryManager.get(VisitingListPage.class)
                    .clickOpenPayment()
                    .switchToTabNumber(2);
            PageFactoryManager.get(AccountingPage.class)
                    .verifyAccountingPageDisplayed()
                    .closeCurrentWindow()
                    .switchToTabNumber(1);
        }
        //16
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickOpenInterViewBtn()
                .verifyModalMonshinInputPopupDisplayed()
                .clickCloseBtn();
        //17
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickOpenSpecialNoteBtn()
                .verifySpecialNotePopupDisplayed()
                .clickCloseBtn();
        //18
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickOpenDocumentBtn()
                .verifyDocumentPopupDisplayed()
                .clickCloseBtn();
        //19
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickOpenDiseaseBtn()
                .verifyDiseasePopupDisplayed()
                .clickCloseBtn();

        //20
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickOpenAccDuelListBtn()
                .verifyAccDuelListPopupDisplayed()
                .clickCloseButton();

        //21
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickOpenReceptionUpdateBtn()
                .verifyReceptionInfoPopupDisplayed()
                .clickCloseBtn();

        //22
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickOpenReceptionBtn()
                .verifyReceptionInfoPopupDisplayed();
        PageFactoryManager.get(CommonPopUp.class)
                .skipPopUps();
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .clickCloseBtn();

        //23
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickDeleteReceptionBtn()
                .verifyDeleteReceptionPopupDisplayed()
                .clickCloseBtn();


    }
}
