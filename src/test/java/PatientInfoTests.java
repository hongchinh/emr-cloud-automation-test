import config.Constant;
import config.Url;
import core.AbstractWebTest;
import core.WebApi;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.common.CommonPopUp;
import pages.common.LoginPage;
import pages.patientinfo.PatientInfoPage;
import pages.patientinfo.ReceptionPage;
import pages.visitinglist.PatientSearchPopup;
import pages.visitinglist.ReceptionInfoPopup;
import pages.visitinglist.VisitingListPage;
import utils.CommonUtil;
import utils.PageFactoryManager;

@Feature("Patient Info")
public class PatientInfoTests extends AbstractWebTest {
    private String testHoken001 = "001";
    private String testHoken002 = "002";
    private String hokenNumber = "25000000";
    private String createdPtName;
    private String createdPtNum;


    @Test(groups = {"SMAR-TC-28", "reg"})
    public void TC_28_add_new_patient_info() throws Exception {
        String randomName1 = "automation name " + CommonUtil.getRandomString(10);
        System.out.println(randomName1);
        createdPtName = randomName1;
        String randomName2 = "automation name " + CommonUtil.getRandomString(10);
        PageFactoryManager.get(WebApi.class)
                .openAnyUrl(Url.URL);
        PageFactoryManager.get(LoginPage.class)
                .verifyLoginPageDisplayed()
                .inputCredentials(Constant.DEFAULT_USERNAME, Constant.DEFAULT_PASSWORD)
                .clickSubmitButton();
        PageFactoryManager.get(CommonPopUp.class)
                .skipUpdateLogIfDisplayed();
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickAddPatientBtn()
                .switchToTabNumber(2);
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                .inputBasicInfo(randomName1, randomName1, "1997/12/10", Constant.MALE);

        //Add hoken 1
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyInsuranceTableDisplayed()
                .inputHokenNumber(hokenNumber)
                .verifyHokenNumber(hokenNumber)
                .verifyInsuranceName(hokenNumber);

        //Add hoken 2
        PageFactoryManager.get(PatientInfoPage.class)
                .clickAddInsuranceBtn()
                .clickThreeDotBtnAndVerifyOptions()
                .selectMainHokenOption("種別変更（自賠）")
                .inputOutcomeReason("継続")
                .updateAndVerifyDate();

        //Add hoken 3
        PageFactoryManager.get(PatientInfoPage.class)
                .clickAddInsuranceBtn()
                .clickThreeDotBtnAndVerifyOptions()
                .selectMainHokenOption("種別変更（労災）")
                .selectSystemSetting("アフターケア")
                .verifyIndustryAccidentType3Displayed()
                .scrollDownToBottomOfInsuranceTableAlt()
                .inputHealthRecordNumber(CommonUtil.getRandomNumber(13))
                .scrollToBottomPage();
        PageFactoryManager.get(PatientInfoPage.class)
                .inputSicknessCode("せき髄損傷")
                .sleepTimeInMilSecond(200);
        PageFactoryManager.get(PatientInfoPage.class)
                .updateAndVerifyDate()
                .scrollUpToTopOfInsuranceTableAlt()
                .scrollToTopOfPage();
        //Verify add insurance successfully
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyInsuranceListNumber(3);


        //Register
        PageFactoryManager.get(PatientInfoPage.class)
                .clickRegisterBtn();

        PageFactoryManager.get(CommonPopUp.class)
                .clickAcceptButton() //Accept insurance as default
                .switchToTabNumber(1);

        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickAddPatientBtn()
                .switchToTabNumber(2);
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                .inputBasicInfo(randomName1, randomName1, "1997/12/10", Constant.MALE);
        PageFactoryManager.get(CommonPopUp.class)
                .verifyDuplicatedPatient()
                .clickCloseButton()
                .clickCloseButton();
        PageFactoryManager.get(PatientInfoPage.class)
                .clickRegisterBtn();
        PageFactoryManager.get(CommonPopUp.class)
                .verifyDuplicatedPatient()
                .clickCloseButton()
                .clickCloseButton();
        PageFactoryManager.get(PatientInfoPage.class)
                .clickAcceptBtn();
        PageFactoryManager.get(CommonPopUp.class)
                .verifyDuplicatedPatient()
                .clickCloseButton()
                .clickCloseButton();
        PageFactoryManager.get(PatientInfoPage.class)
                .inputBasicInfo(randomName2, randomName2, "1997/12/10", Constant.MALE)
                .clickAcceptBtn();
        PageFactoryManager.get(ReceptionPage.class)
                .verifyReceptionPageDisplayed();
        PageFactoryManager.get(CommonPopUp.class)
                .skipPopUps();
        PageFactoryManager.get(ReceptionPage.class)
                .clickAcceptBtn()
                .switchToTabNumber(1);
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .verifyPatientKanaNameDisplayed(randomName2);
    }

    @Test(groups = {"SMAR-TC-29", "reg"})
    public void TC_29_add_existing_patient_info() throws Exception {
        String randomName1 = "automation name " + CommonUtil.getRandomString();
        PageFactoryManager.get(WebApi.class)
                .openAnyUrl(Url.URL);
        PageFactoryManager.get(LoginPage.class)
                .verifyLoginPageDisplayed()
                .inputCredentials(Constant.DEFAULT_USERNAME, Constant.DEFAULT_PASSWORD)
                .clickSubmitButton();
        PageFactoryManager.get(CommonPopUp.class)
                .skipUpdateLogIfDisplayed();
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickAddPatientBtn()
                .switchToTabNumber(2);
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                .inputBasicInfo(randomName1, randomName1, "1997/12/10", Constant.MALE)
                .clickRegisterBtn()
                .switchToTabNumber(1);

        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickAddPatientBtn()
                .switchToTabNumber(2);
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                .inputBasicInfo(randomName1, randomName1, "1997/12/10", Constant.MALE);
        PageFactoryManager.get(CommonPopUp.class)
                .verifyDuplicatedPatient()
                .clickCloseButton()
                .clickCloseButton();
        PageFactoryManager.get(PatientInfoPage.class)
                .clickRegisterBtn();
        PageFactoryManager.get(CommonPopUp.class)
                .verifyDuplicatedPatient()
                .clickAcceptButton()
                .switchToTabNumber(1);

        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickAddPatientBtn()
                .switchToTabNumber(2);
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                .inputBasicInfo(randomName1, randomName1, "1997/12/10", Constant.MALE);
        PageFactoryManager.get(CommonPopUp.class)
                .verifyDuplicatedPatient()
                .clickCloseButton()
                .clickCloseButton();
        PageFactoryManager.get(PatientInfoPage.class)
                .clickRegisterBtn();
        PageFactoryManager.get(CommonPopUp.class)
                .verifyDuplicatedPatient()
                .clickDeclineButton()
                .verifyChangeConfirmPopupDisplayed()
                .clickAcceptButton()
                .switchToTabNumber(1);

        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .clickAddPatientBtn()
                .switchToTabNumber(2);
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                .inputBasicInfo(randomName1, randomName1, "1997/12/10", Constant.MALE);
        PageFactoryManager.get(CommonPopUp.class)
                .verifyDuplicatedPatient()
                .clickCloseButton()
                .clickCloseButton();
        PageFactoryManager.get(PatientInfoPage.class)
                .clickRegisterBtn();
        PageFactoryManager.get(CommonPopUp.class)
                .verifyDuplicatedPatient()
                .clickDeclineButton()
                .verifyChangeConfirmPopupDisplayed()
                .clickDeclineButton();
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed();
    }


    @Test(groups = {"SMAR-TC-32", "reg"}, dependsOnMethods = {"TC_28_add_new_patient_info"})
    public void TC_32_hoken_in_patient_info() throws Exception {
        String memo = "random memo" + CommonUtil.getRandomString(15);
        createdPtNum = statementExecutor.getPtNum(createdPtName);

        PageFactoryManager.get(WebApi.class)
                .openAnyUrl(Url.URL);
        PageFactoryManager.get(LoginPage.class)
                .verifyLoginPageDisplayed()
                .inputCredentials(Constant.DEFAULT_USERNAME, Constant.DEFAULT_PASSWORD)
                .clickSubmitButton();
        PageFactoryManager.get(CommonPopUp.class)
                .skipUpdateLogIfDisplayed();

        boolean isDisplayed = PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .isPatientKanaNameDisplayed(createdPtName);


        if (!isDisplayed) {
            PageFactoryManager.get(VisitingListPage.class)
                    .searchForPatientId(createdPtNum);
            PageFactoryManager.get(PatientSearchPopup.class)
                    .selectPatientName(createdPtName);
            PageFactoryManager.get(CommonPopUp.class)
                    .skipPopUps();
            PageFactoryManager.get(ReceptionInfoPopup.class)
                    .verifyReceptionInfoPopupDisplayed()
                    .uncheckAllCheckboxes()
                    .addPatientToReception()
                    .verifyVisitingListPageDisplayed()
                    .verifyPatientKanaNameDisplayed(createdPtName);
        } else {
            PageFactoryManager.get(VisitingListPage.class)
                    .selectPatient(createdPtName);
        }

        PageFactoryManager.get(VisitingListPage.class)
                .openPatientInfo()
                .switchToTabNumber(2);
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed();
        int oldInsuranceList = PageFactoryManager.get(PatientInfoPage.class)
                .getInsuranceList();
        PageFactoryManager.get(PatientInfoPage.class)
                .clickAddInsuranceBtn()
                .verifyInsuranceListNumber(oldInsuranceList + 1)
                .clickDeleteInsuranceBtn();
        PageFactoryManager.get(CommonPopUp.class)
                .verifyDeleteConfirmPopupDisplayed()
                .clickAcceptButton();
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyInsuranceListNumber(oldInsuranceList)
                .selectInsuranceItem(testHoken001)
                .clickCopyInsuranceBtn()
                .verifyInsuranceListNumber(oldInsuranceList + 1)
                .clickSwitchInsuranceBtn()
                .verifySwitchInsurancePageDisplayed()
                .clickCloseBtn();

        PageFactoryManager.get(PatientInfoPage.class)
                .inputMemo(memo)
                .selectInsuranceItem(testHoken001)
                .verifyInsuranceTableDisplayed()
                .inputHokenNumber(hokenNumber)
                .verifyHokenNumber(hokenNumber)
                .verifyInsuranceName(hokenNumber)
                .clickInsuranceInfoGroupInput()
                .verifyInsuranceCompanyInfoPageDisplayed()
                .closeInfoGroup()
                .verifyPatientInfoPageDisplayed()
                .selectInsuranceItem(testHoken002)
                .updateAndVerifyDate()
                .selectInsuranceItem(1)
                .clickMasterMaintenance()
                .verifyInsuranceMasterMaintenanceDisplayed()
                .clickCloseBtn()
                .verifyPatientInfoPageDisplayed()
                .clickCreditCardAndVerify()
                .openListOfInsurance()
                .selectInsurance("034")
                .clickUpdatePatientInfo();
        PageFactoryManager.get(CommonPopUp.class)
                .verifyMissingInfoPopupDisplayed()
                .clickOkButton();
        PageFactoryManager.get(PatientInfoPage.class)
                .openListOfInsurance()
                .verifyInsuranceListAvailable()
                .selectInsurance("068")
                .openListOfInsurance()
                .verifyThatOnlyJihiInsuranceAvailable()
                .selectInsurance("108")
                .verifyAllInputsInInsuranceDisabled()
                .openListOfInsurance()
                .selectInsurance("068")
                .openKohiListAndVerify()
                .inputInsuranceDatesAndVerify()
                .inputFutansyaAndVerify("25000000")
                .openKohiInsuranceListAndVerify()
                .scrollToTopOfPage();
        PageFactoryManager.get(PatientInfoPage.class)
                .clickAcceptBtn();
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .verifyReceptionInfoPopupDisplayed();
        PageFactoryManager.get(CommonPopUp.class)
                .skipPopUps();
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .clickCloseBtn();
        PageFactoryManager.get(PatientInfoPage.class)
                .selectInsuranceItem(1)
                .clickUpdatePatientInfo()
                .waitForNumberOfTabsToBe(1)
                .switchToTabNumber(1);

        PageFactoryManager.get(VisitingListPage.class)
                .openPatientInfo()
                .switchToTabNumber(2);

        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                .selectInsuranceItem(1);
        int hokenId = PageFactoryManager.get(PatientInfoPage.class)
                .clickThreeDotBtnAndVerifyOptions()
                .selectMainHokenOption("保険コピー")
                .getInsuranceId();
        PageFactoryManager.get(PatientInfoPage.class)
                .clickThreeDotBtnAndVerifyOptions()
                .selectMainHokenOption("保険コピー")
                .verifyInsuranceId(hokenId + 1)
                .clickThreeDotBtnAndVerifyOptions()
                .selectMainHokenOption("保険削除")
                .verifyInsuranceName("blank");

        //労災
        PageFactoryManager.get(PatientInfoPage.class)
                .clickThreeDotBtnAndVerifyOptions()
                .selectMainHokenOption("種別変更（労災）")
                .verifyIndustryAccidentType1Displayed()
                .selectSystemSetting("傷病年金")
                .verifyIndustryAccidentType2Displayed()
                .selectSystemSetting("アフターケア")
                .verifyIndustryAccidentType3Displayed();

        PageFactoryManager.get(PatientInfoPage.class)
                .clickUpdatePatientInfo();
        PageFactoryManager.get(CommonPopUp.class)
                .verifyMissingInfoPopupDisplayed2()
                .clickOkButton();

        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                .scrollDownToBottomOfInsuranceTableAlt()
                .inputHealthRecordNumber(CommonUtil.getRandomNumber(13))
                .inputSicknessCode("せき髄損傷")
                .scrollToBottomPage();
        PageFactoryManager.get(PatientInfoPage.class)
                .updateAndVerifyDate()
                .scrollUpToTopOfInsuranceTableAlt()
                .scrollToTopOfPage();
        PageFactoryManager.get(PatientInfoPage.class)
                .clickAcceptBtn();
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .verifyReceptionInfoPopupDisplayed();
        PageFactoryManager.get(CommonPopUp.class)
                .skipPopUps();
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .clickCloseBtn();
        PageFactoryManager.get(PatientInfoPage.class)
                .selectInsuranceItem(1)
                .clickUpdatePatientInfo()
                .waitForNumberOfTabsToBe(1)
                .switchToTabNumber(1);
        PageFactoryManager.get(VisitingListPage.class)
                .openPatientInfo()
                .switchToTabNumber(2);

        //自賠
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                .selectInsuranceItem(1)
                .clickThreeDotBtnAndVerifyOptions()
                .selectMainHokenOption("種別変更（自賠）");
        PageFactoryManager.get(CommonPopUp.class)
                .clickAcceptButton();
        PageFactoryManager.get(PatientInfoPage.class)
                .clickUpdatePatientInfo();
        PageFactoryManager.get(CommonPopUp.class)
                .verifyMissingInfoPopupDisplayed3()
                .clickOkButton();
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                .inputOutcomeReason("継続")
                .updateAndVerifyDate()
                .clickAcceptBtn();
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .verifyReceptionInfoPopupDisplayed();
        PageFactoryManager.get(CommonPopUp.class)
                .skipPopUps();
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .clickCloseBtn();

        PageFactoryManager.get(PatientInfoPage.class)
                .selectInsuranceItem(1)
                .clickUpdatePatientInfo()
                .waitForNumberOfTabsToBe(1)
                .switchToTabNumber(1);

    }

    @Test(groups = {"SMAR-TC-33", "reg"}, dependsOnMethods = {"TC_28_add_new_patient_info"})
    public void TC_33_actions_in_patient_info() throws Exception {
        String newName = "random new name" + CommonUtil.getRandomString(4);
        String randomName1 = "automation name " + CommonUtil.getRandomString();
        createdPtNum = statementExecutor.getPtNum(createdPtName);

        PageFactoryManager.get(WebApi.class)
                .openAnyUrl(Url.URL);
        PageFactoryManager.get(LoginPage.class)
                .verifyLoginPageDisplayed()
                .inputCredentials(Constant.DEFAULT_USERNAME, Constant.DEFAULT_PASSWORD)
                .clickSubmitButton();
        PageFactoryManager.get(CommonPopUp.class)
                .skipUpdateLogIfDisplayed();

        boolean isDisplayed = PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .isPatientKanaNameDisplayed(createdPtName);


        if (!isDisplayed) {
            PageFactoryManager.get(VisitingListPage.class)
                    .searchForPatientId(createdPtNum);
            PageFactoryManager.get(PatientSearchPopup.class)
                    .selectPatientName(createdPtName);
            PageFactoryManager.get(CommonPopUp.class)
                    .skipPopUps();
            PageFactoryManager.get(ReceptionInfoPopup.class)
                    .verifyReceptionInfoPopupDisplayed()
                    .uncheckAllCheckboxes()
                    .addPatientToReception()
                    .verifyVisitingListPageDisplayed()
                    .verifyPatientKanaNameDisplayed(createdPtName);
        } else {
            PageFactoryManager.get(VisitingListPage.class)
                    .selectPatient(createdPtName);
        }
        int numberOfRecord = PageFactoryManager.get(VisitingListPage.class)
                .getNumberOfPatientRecord(createdPtName);


        PageFactoryManager.get(VisitingListPage.class)
                .openPatientInfo()
                .switchToTabNumber(2);
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                .clickUpdatePatientInfo()
                .waitForNumberOfTabsToBe(1)
                .switchToTabNumber(1);
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .verifyNumberOfPatientWithId(createdPtName, numberOfRecord)
                .openPatientInfo()
                .switchToTabNumber(2);
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                .clickAcceptBtn();
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .verifyReceptionInfoPopupDisplayed();
        PageFactoryManager.get(CommonPopUp.class)
                .skipPopUps();
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .uncheckAllCheckboxes()
                .addPatientToReception()
                .waitForNumberOfTabsToBe(1)
                .switchToTabNumber(1);
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .verifyNumberOfPatientWithId(createdPtName, numberOfRecord + 1);

        PageFactoryManager.get(VisitingListPage.class)
                .openPatientInfo()
                .switchToTabNumber(2);
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                //4
                .clickClearInfoBtnAndVerify()
                .verifyAllInfoBlank()
                .waitForLoadingIconToDisappear();
        PageFactoryManager.get(PatientInfoPage.class)
                .clickCancelPatientInfo()
                .waitForNumberOfTabsToBe(1)
                .switchToTabNumber(1);


        //Missing click copy and delete step step 5 6

        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .openPatientInfo()
                .switchToTabNumber(2);
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
//                //5
                .clickCopyInfoBtn()
                .sleepTimeInSecond(3);
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                .verifyPatientInfoNumber()
                .verifyPatientInfoKanaName("automation ")
                .inputBasicInfo(randomName1, randomName1, "1997/12/10", Constant.MALE)
                .clickThreeDotBtnAndVerifyOptions()
                .selectMainHokenOption("種別変更（健保）")
                .selectHeadFamily("本人")
                .inputHokenNumber(hokenNumber)
                .verifyHokenNumber(hokenNumber)
                .verifyInsuranceName(hokenNumber)
                .clickAcceptBtn();
//        PageFactoryManager.get(CommonPopUp.class)
//                .clickAcceptButton()
//                .waitForLoadingIconToDisappear();
////                .clickIgnoreBtn();
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .verifyReceptionInfoPopupDisplayed();
        PageFactoryManager.get(CommonPopUp.class)
                .skipPopUps();
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .uncheckAllCheckboxes()
                .addPatientToReception()
                .waitForNumberOfTabsToBe(1)
                .switchToTabNumber(1);
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .verifyPatientKanaNameDisplayed(randomName1)
                .openPatientInfo()
                .switchToTabNumber(2);
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                //6
                .clickDeleteInfoBtn();
        PageFactoryManager.get(CommonPopUp.class)
                .clickAcceptButton();
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                .verifyAllInfoBlank()
                .closeCurrentWindow()
                .waitForNumberOfTabsToBe(1)
                .switchToTabNumber(1);
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .verifyPatientKanaNotDisplayed(randomName1)
                .selectPatient(createdPtName)
                .openPatientInfo()
                .switchToTabNumber(2);
        PageFactoryManager.get(PatientInfoPage.class)
                //11
                .verifyPatientInfoPageDisplayed()
                .inputPatientName(newName)
                .clickUpdatePatientInfo()
                .waitForNumberOfTabsToBe(1)
                .switchToTabNumber(1);
        PageFactoryManager.get(VisitingListPage.class)
                .verifyPatientNameDisplayed(newName)
                .openPatientInfo()
                .switchToTabNumber(2);
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                .selectInsuranceItem(testHoken001)
                //7
                .clickDocumentBtn()
                .verifyDocumentPopupDisplayed()
                .clickCloseBtn()
                .verifyPatientInfoPageDisplayed()
                //8
                .clickSetting()
                .verifyGroupMasterMaintenanceDisplayed()
                .clickCloseBtn()
                .verifyPatientInfoPageDisplayed()
                //12
                .clickMaidenName()
                .verifyMaidenNameRegistrationDisplayed()
                .clickCloseBtn()
                .verifyPatientInfoPageDisplayed()
                //13
                .clickPostalCode()
                .verifyPostalCodePopupDisplayed()
                .clickCloseBtn()
                .verifyPatientInfoPageDisplayed()
                //14
                .clickContactAddress()
                .verifyContactAddressDisplayed()
                .clickCloseBtn()
                .verifyPatientInfoPageDisplayed()
                //15
                .clickWorkplaceBtn()
                .verifyWorkplacePopupDisplayed()
                .clickCloseBtn()
                .verifyPatientInfoPageDisplayed()
                //16
                .clickCalculation()
                .verifyCalculationPopupDisplayed()
                .clickCloseBtn()
                .verifyPatientInfoPageDisplayed()
                //22
                .clickMasterMaintenance()
                .verifyInsuranceMasterMaintenanceDisplayed()
                .clickCloseBtn()
                .verifyPatientInfoPageDisplayed()
                //23
                .clickClockIcon()
                .verifyHistoryPopupDisplayed()
                .clickCloseBtn()
                .verifyPatientInfoPageDisplayed()
                //17
                .clickAddInsuranceBtn()
                .verifyInsuranceName("blank")
                //18
                .clickDeleteInsuranceBtn();
        PageFactoryManager.get(CommonPopUp.class)
                .verifyDeleteConfirmPopupDisplayed()
                .clickAcceptButton();
        //19
        PageFactoryManager.get(PatientInfoPage.class)
                .clickSwitchInsuranceBtn()
                .verifySwitchInsurancePageDisplayed()
                .clickCloseBtn()
                .verifyPatientInfoPageDisplayed()
                //20
                .selectInsuranceItem(testHoken001)
                .minimizeAllAndVerify()
                //21
                .clickThreeDotBtnAndVerifyOptions();

        //TODO: Add step 9 10


    }
}
