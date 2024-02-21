import action.CommonAction;
import config.Constant;
import config.Url;
import core.AbstractWebTest;
import core.WebApi;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.common.CommonPopUp;
import pages.common.LoginPage;
import pages.patientinfo.PatientInfoPage;
import pages.visitinglist.PatientSearchPopup;
import pages.visitinglist.ReceptionInfoPopup;
import pages.visitinglist.VisitingListPage;
import utils.DateUtil;
import utils.PageFactoryManager;

@Feature("Reception")
public class ReceptionTests extends AbstractWebTest {

    private String ptId = "100244199";

    @BeforeMethod(alwaysRun = true)
    public void unlockPatient() throws Exception {
        statementExecutor.unlockPatient(ptId);
    }

    @BeforeMethod(alwaysRun = true)
    public void loginAndSelectPatient() throws Exception {
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

    @Test(groups = {"SMAR-TC-59", "reg"})
    public void TC_59_verify_actions_in_reception_screen_1() throws Exception {
        //3+4
        PageFactoryManager.get(VisitingListPage.class)
                .clickOpenReceptionUpdateBtn()
                .verifyReceptionInfoPopupDisplayed()
                .clickGroupNameAndVerifyTextNotBlank()
                .clickGroupNameAndVerifyTextBlank();
        //5
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .selectOptionInKubun("済");
        //6
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .selectRandomOrder();
        //7
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .clickResetFormAndVerify();
        //9
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .selectRandomClinicalDepartment();
        //10
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .selectRandomDoctor();
        //12
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .clickViewPatientInfo()
                .switchToTabNumber(2);
        PageFactoryManager.get(PatientInfoPage.class)
                .verifyPatientInfoPageDisplayed()
                .closeCurrentWindow()
                .switchToTabNumber(1);
        //13
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .verifyReceptionInfoPopupDisplayed()
                .clickOpenSpecialNote()
                .verifySpecialNotePopupDisplayed()
                .clickCloseBtn();
        //14
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .verifyReceptionInfoPopupDisplayed()
                .clickOpenMonshin()
                .verifyModalMonshinInputPopupDisplayed()
                .clickCloseBtn();
        //15
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .uncheckAllCheckboxes()
                .clickUpdateBtn();
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed();
        //16
        PageFactoryManager.get(VisitingListPage.class)
                .clickOpenReceptionUpdateBtn()
                .verifyReceptionInfoPopupDisplayed()
                .clickCancelBtn();
        PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed();
        //17
        PageFactoryManager.get(VisitingListPage.class)
                .clickOpenReceptionUpdateBtn()
                .verifyReceptionInfoPopupDisplayed()
                .selectOptionDisease();
        //18
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .inputTenkiDate(DateUtil.getTodayJapaneseDate());

    }
    @Test(groups = {"SMAR-TC-60", "reg"})
    public void TC_60_verify_actions_in_reception_screen_2() throws Exception {

        PageFactoryManager.get(VisitingListPage.class)
                .clickOpenReceptionBtn()
                .verifyReceptionInfoPopupDisplayed();
        PageFactoryManager.get(CommonPopUp.class)
                .skipPopUps();
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .selectRandomReExam()
                .selectRandomTime()
                .clickExpiredCheckbox()
                .clickClockIcon()
                .verifyHistoryPopupDisplayed()
                .clickCloseBtn();
        PageFactoryManager.get(ReceptionInfoPopup.class)
                .clickUpdateDateAndVerify();

    }
}
