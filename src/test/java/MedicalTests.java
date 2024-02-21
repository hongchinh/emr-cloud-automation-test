import action.CommonAction;
import config.Constant;
import config.Url;
import core.AbstractWebTest;
import core.WebApi;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.common.CommonPopUp;
import pages.common.LoginPage;
import pages.medical.*;
import pages.visitinglist.VisitingListPage;
import utils.CommonUtil;
import utils.PageFactoryManager;

@Feature("Medical")
public class MedicalTests extends AbstractWebTest {
    private String ptId = "100244198";

    @BeforeMethod(alwaysRun = true)
    public void b_unlockPatient() throws Exception {
        statementExecutor.unlockPatient(ptId);
    }

    @BeforeMethod(alwaysRun = true)
    public void c_loginAndSelectPatient() throws Exception {
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

    @Test(groups = {"SMAR-TC-38", "reg"})
    public void TC_38_1_verify_actions_in_medical_screen() throws Exception {
        String medicineName;
        //1.1
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickMedicalBtn()
                .switchToTabNumber(2);
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed()
                ;
//                .closeCurrentWindow()
//                .switchToTabNumber(1);
//        //1.2
//        PageFactoryManager.get(VisitingListPage.class)
//                .verifyVisitingListPageDisplayed()
//                .selectPatient(ptId)
//                .clickOpenReceptionBtn()
//                .verifyReceptionInfoPopupDisplayed();
//        PageFactoryManager.get(CommonPopUp.class)
//                .skipPopUps();
//        PageFactoryManager.get(ReceptionInfoPopup.class)
//                .uncheckAllCheckboxes()
//                .clickOpenMedicalBtn()
//                .switchToTabNumber(2);
//        PageFactoryManager.get(MedicalPage.class)
//                .verifyMedicalPageDisplayed()
//                .closeCurrentWindow()
//                .switchToTabNumber(1);
//
//        //1.3
//        PageFactoryManager.get(VisitingListPage.class)
//                .verifyVisitingListPageDisplayed()
//                .inputPatientId(ptId)
//                .clickAddChartBtn()
//                .sleepTimeInMilSecond(500)
//                .waitForLoadingIconToDisappear()
//                .switchToTabNumber(2);
//        PageFactoryManager.get(CommonPopUp.class)
//                .skipPopUpsLong();
//        PageFactoryManager.get(MedicalPage.class)
//                .verifyMedicalPageDisplayed();
        //2.
        String doMessage = PageFactoryManager.get(MedicalPage.class)
                .clickOrderHistory()
                .verifyOrderHistoryTabDisplayed()
                .clickDoAllHistoryTab();
        PageFactoryManager.get(SaveOptionConfirmPopup.class)
                .clickOkIfDisplayed()
                .waitForLoadingIconToDisappear();
        PageFactoryManager.get(MedicalPage.class)
                .verifyHokenTodayContains(doMessage);
        //3
        PageFactoryManager.get(MedicalPage.class)
                .refreshCurrentPage()
                .acceptAlert();
//        PageFactoryManager.get(CommonPopUp.class)
//                .skipPopUpsLong();
        doMessage = PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed()
                .clickOrderHistory()
                .verifyOrderHistoryTabDisplayed()
                .clickDocumentIcon()
                .clickDoInHistoryTab();
        PageFactoryManager.get(SaveOptionConfirmPopup.class)
                .clickOkIfDisplayed()
                .waitForLoadingIconToDisappear();
        PageFactoryManager.get(MedicalPage.class)
                .verifyHokenTodayContains(doMessage);
        //4
        String oldRecord =  PageFactoryManager.get(MedicalPage.class)
                .getLocationOfFirstRecord();
        PageFactoryManager.get(MedicalPage.class)
                .clickNextItemBtn()
                .verifyPositionOfRecordChanged(oldRecord);

        //5
        PageFactoryManager.get(MedicalPage.class)
                .clickNextOrder()
                .verifyNextOrderTabDisplayed();
        //6
        PageFactoryManager.get(MedicalPage.class)
                .inputRecord(CommonUtil.getRandomString(10))
                .addNewDisease("皮1", "難病", "random disease name", "random comment")
                .verifyLastDiseaseInfo("皮1", "難病", "random disease name", "random comment");
        medicineName = PageFactoryManager.get(MedicalPage.class)
                .clickAddNewMedicineInNextOrderTab()
                .addNewMedicine("a", "分１　起床時", "1", "院内");
        PageFactoryManager.get(MedicalPage.class)
                .verifyNewMedicineAdded(medicineName, "分１　起床時", "1");


        //7
        PageFactoryManager.get(MedicalPage.class)
                .clickNextOrder()
                .verifyNextOrderTabDisplayed()
                .verifyRecordTodayBlank()
                .clickDoAllNextOrderTab();
        PageFactoryManager.get(MedicalPage.class)
                .verifyRecordTodayNotBlank();
        //8
        PageFactoryManager.get(MedicalPage.class)
                .clickSpecialNote()
                .verifySpecialNoteTabDisplayed();
        //9
        PageFactoryManager.get(MedicalPage.class)
                .clickNoteTab()
                .fillNoteTab();

        PageFactoryManager.get(MedicalPage.class)
                .clickPatientInformationTab()
                .fillInformationTab();

        //10
        PageFactoryManager.get(MedicalPage.class)
                .clickOrderHistory()
                .verifyOrderHistoryTabDisplayed();
        String selectedDate = PageFactoryManager.get(CalendarSection.class)
                .selectHistoryDate();
        PageFactoryManager.get(MedicalPage.class)
                .verifyHistoryDateCorrect(selectedDate);

        //11
        selectedDate = PageFactoryManager.get(CalendarSection.class)
                .doubleClickHistoryDate();
        PageFactoryManager.get(MedicalPage.class)
                .sleepTimeInSecond(5);
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed()
                .verifyTodayDateCorrect(selectedDate);
        //12
        PageFactoryManager.get(MedicalPage.class)
                .clickOrderHistory()
                .verifyOrderHistoryTabDisplayed();
        selectedDate = PageFactoryManager.get(FlowSheetSection.class)
                .selectRandomDateInFlowSheet();
        PageFactoryManager.get(MedicalPage.class)
                .verifyHistoryDateCorrect(selectedDate);

        //13
        selectedDate = PageFactoryManager.get(FlowSheetSection.class)
                .doubleClickRandomDateInFlowSheet();
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed()
                .verifyTodayDateCorrect(selectedDate);


        //35-1
        String randomComment = CommonUtil.getRandomString(10);
        PageFactoryManager.get(MedicalPage.class)
                .dragGutterLeft(500)
                .inputCommentInTodayOrder(randomComment)
                .verifyCommentInTodayOrder(randomComment);

        //14
        int diseaseListBefore = PageFactoryManager.get(MedicalPage.class)
                .getNumberOfDisease();
        PageFactoryManager.get(MedicalPage.class)
                .addNewDiseaseInDiseaseList("皮1", "難病", "random disease name", "random comment")
                .verifyLastDiseaseInfoInDiseaseList("皮1", "難病", "random disease name（random comment）");
        PageFactoryManager.get(MedicalPage.class)
                .verifyNumberOfDisease(diseaseListBefore + 1)
                .deleteLastDisease()
                .verifyNumberOfDisease(diseaseListBefore);

        //35-2
        PageFactoryManager.get(MedicalPage.class)
                .verifyCommentInTodayOrder(randomComment);
        //15
        PageFactoryManager.get(TodaySection.class)
                .selectRandomOptionInTodaySection();

        boolean confirmPopupDisplayed = PageFactoryManager.get(SaveInsuranceConfirmPopup.class)
                        .isSaveInsuranceConfirmPopupDisplayed();
        if(confirmPopupDisplayed){
            PageFactoryManager.get(SaveInsuranceConfirmPopup.class)
                    .clickDeclineButton();
        }


        //16
        PageFactoryManager.get(MedicalPage.class)
                .clickNextOrder()
                .verifyNextOrderTabDisplayed()
                .uploadFile()
                .sleepTimeInSecond(1);
        //17
        PageFactoryManager.get(MedicalPage.class)
                .dragGutterRight()
                .verifyMedicalPageDisplayed()
                .clickSortAndVerify();

        //18

        //19
        PageFactoryManager.get(MedicalPage.class)
                .refreshCurrentPage()
                .acceptAlert();
        String hokenBefore = PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed()
                .getHokenToday();
        PageFactoryManager.get(MedicalPage.class)
                .dragGutterLeft(250)
                .doSuperSetItem();
        PageFactoryManager.get(CommonPopUp.class)
                .clickAcceptButtonIfDisplayed(10)
                .waitForLoadingIconToDisappear();
        PageFactoryManager.get(CommonPopUp.class)
                .clickAcceptButtonIfDisplayed(10)
                .waitForLoadingIconToDisappear();
        PageFactoryManager.get(MedicalPage.class)
                .verifyHokenTodayIsNot(hokenBefore);

        //20
        PageFactoryManager.get(MedicalPage.class)
                .clickTrialCalculationBtn()
                .verifyTrialCalculationPopupDisplayed()
                .clickCloseButton();
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed();

    }
    @Test(groups = {"SMAR-TC-38", "reg"})
    public void TC_38_2_verify_actions_in_medical_screen() throws Exception {
        String medicineName;
        //1.1
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickMedicalBtn()
                .switchToTabNumber(2);
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed();

        //21
        medicineName = PageFactoryManager.get(TodaySection.class)
                .clickAddNewMedicineInTodaySection()
                .addNewMedicine("a", "分１　起床時", "1", "院内");
        PageFactoryManager.get(TodaySection.class)
                .verifyNewMedicineAddedInTodaySection(medicineName,"分１　起床時");

        //22
        PageFactoryManager.get(MedicalPage.class)
                .refreshCurrentPage()
                .acceptAlert();
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed();
        medicineName = PageFactoryManager.get(TodaySection.class)
                .clickAddNewMedicineInTodaySection()
                .addNewMedicine("KN1291");
        PageFactoryManager.get(TodaySection.class)
                .verifyNewMedicineAddedInTodaySection(medicineName);

        //23
        PageFactoryManager.get(MedicalPage.class)
                .refreshCurrentPage()
                .acceptAlert();
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed();
        medicineName = PageFactoryManager.get(TodaySection.class)
                .clickAddNewMedicineInTodaySection()
                .addNewMedicine("620000343", "皮内、皮下及び筋肉内注射");
        PageFactoryManager.get(TodaySection.class)
                .verifyNewMedicineAddedInTodaySection(medicineName);

        //24 - done

        //25

        PageFactoryManager.get(MedicalPage.class)
                .refreshCurrentPage()
                .acceptAlert();
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed();
        medicineName = PageFactoryManager.get(TodaySection.class)
                .clickAddNewMedicineInTodaySection()
                .addNewMedicine("820000198");
        PageFactoryManager.get(TodaySection.class)
                .verifyNewMedicineAddedInTodaySection(medicineName);

        //26 - done

        //27 - done

        //28-1
        boolean enabled = PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed()
                .clickTrialCalculationBtn()
                .verifyTrialCalculationPopupDisplayed()
                .temporarySaveEnabled();
        if(enabled){
            PageFactoryManager.get(TrialCalculationPopup.class)
                    .clickTemporarySave()
                    .verifySaveOptionConfirmPopupDisplayed()
                    .clickCloseBtn();
        }
        else {
            PageFactoryManager.get(TrialCalculationPopup.class)
                    .clickCloseButton();
        }

        //28-2
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed()
                .clickTrialCalculationBtn()
                .verifyTrialCalculationPopupDisplayed()
                .clickSaveBtn()
                .clickDeclineButton();
        //28-3
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed()
                .clickTrialCalculationBtn()
                .verifyTrialCalculationPopupDisplayed()
                .clickKeepBtn()
                .verifySaveOptionConfirmPopupDisplayed()
                .clickCloseBtn();

        //29
        boolean clicked = PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed()
                .clickSaveTemporaryBtn();
        if(clicked){
            PageFactoryManager.get(SaveOptionConfirmPopup.class)
                    .verifySaveOptionConfirmPopupDisplayed()
                    .uncheckAllCheckboxes()
                    .clickSaveBtn()
                    .waitForNumberOfTabsToBe(1)
                    .switchToTabNumber(1);
            PageFactoryManager.get(VisitingListPage.class)
                    .verifyVisitingListPageDisplayed()
                    .verifyPatientStatus(ptId, "一時保存");

            PageFactoryManager.get(VisitingListPage.class)
                    .clickMedicalBtn()
                    .switchToTabNumber(2);
            PageFactoryManager.get(MedicalPage.class)
                    .verifyMedicalPageDisplayed();

            //30
            PageFactoryManager.get(MedicalPage.class)
                    .verifyMedicalPageDisplayed()
                    .clickSaveTemporaryBtn();
            PageFactoryManager.get(SaveOptionConfirmPopup.class)
                    .verifySaveOptionConfirmPopupDisplayed()
                    .uncheckAllCheckboxes()
                    .clickSaveAndBackBtn()
                    .waitForLoadingIconToDisappear();
            PageFactoryManager.get(MedicalPage.class)
                    .verifyMedicalPageDisplayed();
        }



        //31
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed()
                .clickSaveCalculationBtn();
        PageFactoryManager.get(CommonPopUp.class)
                .clickAcceptButton()
                .waitForNumberOfTabsToBe(1)
                .switchToTabNumber(1);

        if(clicked){
            PageFactoryManager.get(VisitingListPage.class)
                    .verifyVisitingListPageDisplayed()
                    .verifyPatientStatus(ptId, "計算");
        }
        else {
            PageFactoryManager.get(VisitingListPage.class)
                    .verifyVisitingListPageDisplayed()
                    .verifyPatientStatus(ptId, "精算");
        }

        PageFactoryManager.get(VisitingListPage.class)
                .clickMedicalBtn()
                .switchToTabNumber(2);
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed();

        //32
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed()
                .clickSaveOptionBtn()
                .verifySaveOptionConfirmPopupDisplayed()
                .uncheckAllCheckboxes()
                .clickSaveBtn()
                .waitForNumberOfTabsToBe(1)
                .switchToTabNumber(1);
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .verifyPatientStatus(ptId, "精算");

        PageFactoryManager.get(VisitingListPage.class)
                .clickMedicalBtn()
                .switchToTabNumber(2);
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed();

        //33
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed()
                .clickSaveOptionBtn()
                .verifySaveOptionConfirmPopupDisplayed()
                .uncheckAllCheckboxes()
                .clickSaveAndBackBtn()
                .waitForLoadingIconToDisappear();
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed();

        //34 - done

        //35 - done

        //36 - no document btn?

        //37

        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed()
                .clickZanteiInformation()
                .verifyZanteiInformationPopupDisplayed()
                .clickCloseButton();

        //38

        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed()
                .clickSettingBtn();
        //39
        PageFactoryManager.get(MedicalPage.class)
                .verifyMedicalPageDisplayed()
                .clickCancelPageBtn()
                .waitForNumberOfTabsToBe(1)
                .switchToTabNumber(1);
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed();



    }

}
