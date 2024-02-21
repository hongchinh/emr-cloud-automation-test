package pages.visitinglist;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.testng.Assert;
import pages.medical.MedicalPage;
import pages.patientinfo.InsuranceConfirmationHistoryPopup;
import utils.DateUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class ReceptionInfoPopup extends WebApi {

    @FindBy(xpath = "//div[text()='受付情報']")
    private WebElement title;

    @FindBy(id = "btnOpenPatientView_ReceptionHeader")
    private WebElement viewPatientBtn;

    @FindBy(id = "btnAddNew_ReceptionHeader")
    private WebElement addToReceptionBtn;

    @FindBy(id = "btnshowOrder_ReceptionHeader")
    private WebElement openMedicalBtn;

    @FindBy(id = "btnOpenSpecialNote_ReceptionHeader")
    private WebElement openSpecialNoteBtn;

    @FindBy(id = "btnOpenMonshinInput_ReceptionHeader")
    private WebElement openMonshinInputBtn;

    @FindBy(className = "btn-close")
    private WebElement closeBtn;

    @FindBy(id = "btnUpdateAction_ReceptionHeader")
    private WebElement btnUpdate;

    @FindBy(id = "cancel")
    private WebElement cancelBtn;

    @FindBy(xpath = "//div[contains(@class,'PrintForm__infoContainer')]//input")
    private List<WebElement> allPrintCheckboxes;

    @FindBy(id = "isByomeiChecked")
    private WebElement byomeiCheckbox;

    @FindBy(id = "isGoshi1Checked")
    private WebElement goshi1Checkbox;

    @FindBy(id = "isGoshi2Checked")
    private WebElement ghoshi2Checkbox;

    @FindBy(xpath = "//div[@col-id='groupName' and @role='gridcell']")
    private List<WebElement> groupName;

    @FindBy(xpath = "//div[@col-id='kubunCd' and @role='gridcell']/div")
    private List<WebElement> kubunText;

    @FindBy(name = "kbnId")
    private WebElement kubunIdSelect;

    @FindBy(id = "btnResetForm_ReceptionForm")
    private WebElement btnResetForm;

    @FindBy(name = "yoyakuInfo")
    private WebElement formInfo;

    @FindBy(name = "kaId")
    private WebElement kaIdSelect;

    @FindBy(name = "doctorId")
    private WebElement doctorSelect;

    @FindBy(xpath = "//div[@col-id='tenkiKbn' and @role='gridcell']")
    private WebElement tenkiBtn;

    @FindBy(xpath = "//div[@col-id='tenkiDate' and @role='gridcell']")
    private WebElement tenkiDate;

    @FindBy(xpath = "//div[@col-id='tenkiDate' and @role='gridcell']/div")
    private WebElement tenkiDateText;

    @FindBy(xpath = "//div[@col-id='tenkiDate' and @role='gridcell']/input")
    private WebElement tenkiDateInput;

    @FindBy(xpath = "//div[@col-id='tenkiKbn' and @role='gridcell']//select")
    private WebElement tenkiSelect;

    @FindBy(xpath = "(//div[text()='初再診']/following-sibling::div/select)[1]")
    private WebElement reExamSelect;

    @FindBy(xpath = "(//div[text()='初再診']/following-sibling::div/select)[2]")
    private WebElement timeSelect;

    @FindBy(id = "expired")
    private WebElement expiredCheckbox;

    @FindBy(id = "btnHistory_InsuranceInfoDetail")
    private WebElement clockIcon;

    @FindBy(xpath = "//td[contains(@class,'InsuranceInfo__dateConfirm_')]")
    private List<WebElement> dateConfirmText;

    @FindBy(xpath = "//*[@id='btnMikakuninException_InsuranceInfoDetail' or @id='btnMikakunin_InsuranceInfoDetail']")
    private List<WebElement> btnUpdateDate;


    private String kubunOption = "//div[contains(@id,'react-select')]//div[@title='%s']";

    @Step
    public ReceptionInfoPopup verifyReceptionInfoPopupDisplayed() {
        waitForPageLoaded();
        verifyControlDisplayed(title, "Title");
        waitForLoadingIconToDisappear();
        return this;
    }

    @Step
    public ReceptionInfoPopup clickViewPatientInfo() {
        clickElement(viewPatientBtn);
        return this;
    }

    @Step
    public VisitingListPage addPatientToReception() {
        clickElement(addToReceptionBtn);
        return new VisitingListPage();
    }

    @Step
    public VisitingListPage clickCloseBtn() {
        clickElement(closeBtn);
        return new VisitingListPage();
    }

    @Step
    public MedicalPage clickOpenMedicalBtn() {
        clickElement(openMedicalBtn);
        return new MedicalPage();
    }

    @Step
    public ReceptionInfoPopup clickUpdateBtn() {
        clickElement(btnUpdate);
        return this;
    }

    @Step
    public ReceptionInfoPopup clickCancelBtn() {
        clickElement(cancelBtn);
        return this;
    }

    @Step
    public ReceptionInfoPopup uncheckAllCheckboxes() {
        for (WebElement e : allPrintCheckboxes) {
            unCheckToCheckBox(e);
        }
        return this;
    }

    @Step
    public ReceptionInfoPopup clickByomeiAndSave() {
        uncheckAllCheckboxes();
        checkToCheckBoxOrRadioButton(byomeiCheckbox);
        clickUpdateBtn();
        return this;
    }

    @Step
    public ReceptionInfoPopup clickGhoshi1AndSave() {
        uncheckAllCheckboxes();
        checkToCheckBoxOrRadioButton(goshi1Checkbox);
        clickUpdateBtn();
        return this;
    }

    @Step
    public ReceptionInfoPopup clickGhoshi2AndSave() {
        uncheckAllCheckboxes();
        checkToCheckBoxOrRadioButton(ghoshi2Checkbox);
        clickUpdateBtn();
        return this;
    }

    @Step
    public ReceptionInfoPopup clickGroupNameAndVerifyTextNotBlank() {
        if (!kubunText.get(1).getText().equalsIgnoreCase("")) {
            return this;
        }
        clickElement(groupName.get(1));
        await().atMost(10, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(() -> {
                    Assert.assertNotEquals(kubunText.get(1).getText(), "");
                });
        return this;
    }

    @Step
    public ReceptionInfoPopup clickGroupNameAndVerifyTextBlank() {
        clickElement(groupName.get(1));
        await().atMost(10, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(() -> {
                    Assert.assertEquals(kubunText.get(1).getText(), "");
                });
        return this;
    }

    @Step
    public ReceptionInfoPopup selectOptionInKubun(String option) {
        clickElement(groupName.get(1));
        clickElement(kubunText.get(1));
        clickElement(kubunOption, option);
        Assert.assertEquals(kubunText.get(1).getText(), option);
        return this;
    }

    @Step
    public ReceptionInfoPopup selectRandomOrder() {
        selectRandomItemInDropdown(kubunIdSelect);
        return this;
    }

    @Step
    public ReceptionInfoPopup selectRandomClinicalDepartment() {
        selectRandomItemInDropdown(kaIdSelect);
        return this;
    }

    @Step
    public ReceptionInfoPopup selectRandomDoctor() {
        selectRandomItemInDropdown(doctorSelect);
        return this;
    }

    @Step
    public ReceptionInfoPopup clickResetFormAndVerify() {
        clickElement(btnResetForm);
        Assert.assertEquals(formInfo.getAttribute("value"), "新規来院");
        return this;
    }

    @Step
    public SpecialNotePopup clickOpenSpecialNote() {
        clickElement(openSpecialNoteBtn);
        return new SpecialNotePopup();
    }

    @Step
    public ModalMonshinInputPopup clickOpenMonshin() {
        clickElement(openMonshinInputBtn);
        return new ModalMonshinInputPopup();
    }

    @Step
    public ReceptionInfoPopup selectOptionDisease() {
        clickElement(tenkiBtn);
        sleepTimeInMilSecond(200);
        clickElement(tenkiBtn);
        sleepTimeInMilSecond(200);
        selectRandomItemInDropdownExclude(tenkiSelect, "当月");
        return this;
    }

    @Step
    public ReceptionInfoPopup inputTenkiDate(String value) {
        clickElement(tenkiDate);
        clearTextElementWithKeys(tenkiDateInput);
        sendKeyToElement(tenkiDateInput, value + Keys.ENTER);
        Assert.assertEquals(tenkiDateText.getText(), "令 " + value);
        return this;
    }

    @Step
    public ReceptionInfoPopup selectRandomReExam() {
        selectRandomItemInDropdown(reExamSelect);
        return this;
    }

    @Step
    public ReceptionInfoPopup selectRandomTime() {
        selectRandomItemInDropdown(timeSelect);
        return this;
    }

    @Step
    public ReceptionInfoPopup clickExpiredCheckbox() {
        checkToCheckBoxOrRadioButton(expiredCheckbox);
        return this;
    }

    @Step
    public InsuranceConfirmationHistoryPopup clickClockIcon() {
        clickElement(clockIcon);
        return new InsuranceConfirmationHistoryPopup();
    }

    @Step
    public ReceptionInfoPopup clickUpdateDateAndVerify() {
        String todayDate = DateUtil.getTodayJapaneseDate();
        for (int i = 0; i < dateConfirmText.size(); i++) {
            if (!dateConfirmText.get(i).getText().equalsIgnoreCase("令 " + todayDate)) {
                clickElement(btnUpdateDate.get(i));
                sleepTimeInMilSecond(150);
                Assert.assertEquals(dateConfirmText.get(i).getText(), "令 " + todayDate);
            }
        }
        return this;
    }
}
