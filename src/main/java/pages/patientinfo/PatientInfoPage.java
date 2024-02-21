package pages.patientinfo;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.common.CommonPopUp;
import utils.CommonUtil;
import utils.DateUtil;
import utils.PageFactoryManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static config.Constant.JIHI_INSURANCE;
import static org.awaitility.Awaitility.await;

public class PatientInfoPage extends WebApi {

    @FindBy(id = "btnClear_PtFormHeader")
    private WebElement clearInfoBtn;

    @FindBy(id = "btnCopy_PtFormHeader")
    private WebElement copyInfoBtn;

    @FindBy(id = "btDelete_PtFormHeader")
    private WebElement deleteInfoBtn;

    @FindBy(id = "btnWrite_PtFormHeader")
    private WebElement documentBtn;

    @FindBy(xpath = "//div[contains(@class,'PtFormHeader__ptFormSettings')]")
    private WebElement settingBtn;

    @FindBy(id = "btnKyusei_PatienInfoForm")
    private WebElement maidenNameBtn;

    @FindBy(id = "btnOpenPostCode_PatientInfoForm")
    private WebElement openPostCodeBtn;

    @FindBy(xpath = "//div[text()='連絡先']/following-sibling::div/div")
    private WebElement contactAddressBtn;

    @FindBy(xpath = "//div[text()='勤務先']/following-sibling::div/div")
    private WebElement workplaceBtn;

    @FindBy(xpath = "//div[text()='分類']/following-sibling::span")
    private WebElement addCalculationBtn;

    @FindBy(name = "patientInfo.ptNum")
    private WebElement patientInfoNumInput;

    @FindBy(name = "patientInfo.kanaName")
    private WebElement patientInfoKanaName;

    @FindBy(name = "patientInfo.name")
    private WebElement patientInfoName;

    @FindBy(xpath = "//div[text()='生年月日']/following-sibling::div//input")
    private WebElement dateOfBirth;

    @FindBy(xpath = "//div[text()='性別']/following-sibling::div//div[contains(@class,'control')]")
    private WebElement sex;

    @FindBy(id = "btnRegister_PatientHeader")
    private WebElement registerBtn;

    @FindBy(id = "btnAccept_PatientHeader")
    private WebElement acceptBtn;

    @FindBy(id = "btnValidate_InsurancePattern")
    private WebElement addInsuranceBtn;

    @FindBy(id = "btnDelete_InsurancePattern")
    private WebElement deleteInsuranceBtn;

    @FindBy(id = "btnCopy_InsurancePattern")
    private WebElement copyInsuranceBtn;

    @FindBy(id = "btnChange_InsurancePattern")
    private WebElement switchInsuranceBtn;

    @FindBy(xpath = "//div[contains(@class,'InsurancePattern__listPattern')]/div")
    private List<WebElement> insuranceList;

    @FindBy(xpath = "//textarea[contains(@name,'hokenMemo')]")
    private WebElement memoArea;

    @FindBy(xpath = "//div[contains(@class,'InsuranceInfo__info')]/div")
    private WebElement insuranceTable;

    @FindBy(xpath = "//div[contains(@class,'InsuranceInfo__info')]")
    private WebElement insuranceTableAlt;

    @FindBy(xpath = "//div[contains(@class,'HokenInfo__insuranceContainer')]//input[not(@hidden)]")
    private List<WebElement> allInputsInsuranceTable;

    @FindBy(xpath = "//div[text()='保険者番号']/following-sibling::div//input[not(@type='hidden')]")
    private WebElement insuranceNumberInput;

    @FindBy(xpath = "//div[contains(@class,'HokenInfo__buttonGroup')]")
    private WebElement insuranceInfoGroupInput;

    @FindBy(xpath = "//div[contains(@class,'HokenInfo__header_')]/div[contains(@class,'container')]//div[contains(@class,'singleValue')]")
    private WebElement insuranceName;

    @FindBy(xpath = "//div[contains(@class,'HokenInfo__header_')]/div[contains(@class,'container')]//div[contains(@class,'singleValue')]")
    private List<WebElement> insuranceNames;

    @FindBy(xpath = "//div[contains(@class,'HokenInfo__header_')]/div[contains(@class,'container')]//input/../../following-sibling::div")
    private List<WebElement> insuranceNameInputs;

    @FindBy(xpath = "//div[contains(@class,'HokenInfo__confirmButtonWrapper_')]/span")
    private WebElement dateConfirmButton;

    @FindBy(xpath = "//div[contains(@class,'HokenInfo__confirmButtonWrapper_')]/span[2]")
    private WebElement clockIconBtn;

    @FindBy(xpath = "//div[contains(@class,'HokenInfo__confirmButtonWrapper_')]/preceding-sibling::div/input")
    private WebElement dateAfterConfirmText;

    @FindBy(xpath = "//span[@title='保険マスタメンテナンス']")
    private WebElement insuranceMasterMaintenanceBtn;

    @FindBy(xpath = "//span[@title='自費']")
    private WebElement creditCardIcon;

    @FindBy(xpath = "//input[@name='selectedHoken.hokenMst']/preceding-sibling::div")
    private WebElement openListOfInsuranceIcon;

    @FindBy(xpath = "//input[contains(@name,'selected.kohi') and @type='hidden']/preceding-sibling::div")
    private List<WebElement> openListOfInsuranceIconForKohi;

    @FindBy(xpath = "//input[@name='selectedHoken.hokenMst']/preceding-sibling::div//div[contains(@class,'singleValue')]")
    private WebElement insuranceTypeName;

    @FindBy(xpath = "//div[contains(@class,'menu') and contains(@id,'react-select')]/div/div")
    private List<WebElement> listOfOptions;

    @FindBy(xpath = "//div[contains(@class,'InsurancePattern__item')]")
    private List<WebElement> insuranceItems;

    @FindBy(xpath = "//div[text()='有効期限']/following-sibling::div/input[not(@readonly)][1]")
    private List<WebElement> startDateInputs;

    @FindBy(xpath = "//div[text()='有効期限']/following-sibling::div/input[not(@readonly)][2]")
    private List<WebElement> endDateInputs;

    @FindBy(xpath = "//input[contains(@name,'futansyaNoInput') and not(@readonly)]")
    private List<WebElement> futansyaNoInput;

    @FindBy(xpath = "//input[contains(@name,'jyukyusyaNo') and not(@readonly)]")
    private List<WebElement> jyukyusyaNoInput;

    @FindBy(xpath = "//*[@class='HokenInfo__expandIcon_rjEEE']")
    private List<WebElement> expandButtons;

    @FindBy(xpath = "//*[@class='HokenInfo__expandIcon_rjEEE HokenInfo__expand_DMmZT']")
    private List<WebElement> minimizeButtons;

    @FindBy(xpath = "//*[@id='btnUpdateActive_PatientHeader']")
    private WebElement updatePatientInfoBtn;

    @FindBy(xpath = "//*[@id='btnCancel_PatientHeader']")
    private WebElement cancelPatientInfoBtn;

    @FindBy(xpath = "//div[@class='d-flex']")
    private WebElement threeDotBtnMainHoken;

    @FindBy(xpath = "//div[@x-placement='bottom-start']/a")
    private List<WebElement> mainHokenOptions;

    @FindBy(xpath = "//*[contains(@class,'JibaiDetail__buttonActionJibai')]")
    private WebElement selectOutComeReasonBtn;

    @FindBy(xpath = "//button[text()='確定']")
    private WebElement confirmBtn;


    private String systemTypeSelect = "//*[text()='%s']/following-sibling::div";
    private String textToBeSelected = "//div[contains(@id,'react-select-2-option') and text()='%s']";
    private String insuranceItem = "//div[contains(@class,'InsurancePattern__item') and contains(text(),'%s')]";
    private String industrialAccidentInputs1 = "//div[text()='%s']/following-sibling::input";
    private String industrialAccidentInputs2 = "//div[text()='%s']/following-sibling::div//input";
    private String settingOption = "//a[text()='%s']";


    @Step
    public PatientInfoPage verifyPatientInfoPageDisplayed() {
        waitForPageLoaded();
        verifyControlDisplayed(patientInfoKanaName, "Kana name");
        try{
            verifyControlDisplayed(patientInfoName, "Kanji name");
        }
        catch (StaleElementReferenceException ignore){

        }
        verifyControlDisplayed(dateOfBirth, "Date of birth");
        verifyControlDisplayed(sex, "Sex");
        waitForLoadingIconToDisappear();
        return this;
    }

    @Step
    public PatientInfoPage inputPatientName(String name){
        clearTextElement(patientInfoName);
        sendKeyToElement(patientInfoName, name);
        return this;
    }

    @Step
    public PatientInfoPage verifyPatientInfoNumber(){
        verifyControlDisplayed(patientInfoNumInput, "Patient Info number");
        return this;
    }

    @Step
    public PatientInfoPage inputBasicInfo(String kanaName, String name, String dob, String sexValue) {
        inputPatientName(name);
        clearTextElement(patientInfoKanaName);
        sendKeyToElement(patientInfoKanaName, kanaName);
        clearTextElement(dateOfBirth);
        sendKeyToElement(dateOfBirth, dob);
        clickElement(sex);
        selectOptionFromList(sexValue);
        return this;
    }
    @Step
    public PatientInfoPage verifyPatientInfoKanaName(String expected){
        await()
                .atMost(10, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .untilAsserted(() -> Assert.assertEquals(getTextElementByValue(patientInfoKanaName),expected));
        return this;
    }

    @Step
    public PatientInfoPage clickRegisterBtn() {
        clickElement(registerBtn);
        return this;
    }

    @Step
    public PatientInfoPage clickAcceptBtn() {
        clickElement(acceptBtn);
        return this;
    }

    @Step
    public PatientInfoPage clickAddInsuranceBtn() {
        sleepTimeInSecond(1);
        clickElement(addInsuranceBtn);
        sleepTimeInSecond(1);
        return this;
    }

    @Step
    public PatientInfoPage clickDeleteInsuranceBtn() {
        clickElement(deleteInsuranceBtn);
        return this;
    }

    @Step
    public PatientInfoPage clickCopyInsuranceBtn() {
        clickElement(copyInsuranceBtn);
        return this;
    }

    @Step
    public SwitchInsurancePage clickSwitchInsuranceBtn() {
        clickElement(switchInsuranceBtn);
        return new SwitchInsurancePage();
    }

    @Step
    public PatientInfoPage verifyNewInsurancePatternDisplayed() {
        clickElement(switchInsuranceBtn);
        return this;
    }

    @Step
    public int getInsuranceList() {
        return insuranceList.size();
    }

    @Step
    public PatientInfoPage verifyInsuranceListNumber(int expected) {
        await()
                .atMost(10, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .untilAsserted(() -> Assert.assertEquals(getInsuranceList(), expected));
        return this;
    }

    @Step
    public PatientInfoPage inputMemo(String value) {
        clearTextElement(memoArea);
        sendKeyToElement(memoArea, value);
        return this;
    }

    @Step
    public PatientInfoPage selectInsuranceItem(String itemId) {
        clickElement(insuranceItem, itemId);
        return this;
    }

    @Step
    public PatientInfoPage selectInsuranceItem(int number) {
        clickElement(insuranceItems.get(number - 1));
        sleepTimeInMilSecond(150);
        return this;
    }

    @Step
    public PatientInfoPage verifyInsuranceTableDisplayed() {
        verifyControlDisplayed(insuranceTable, "Table");
        return this;
    }

    @Step
    public PatientInfoPage inputHokenNumber(String value) {
        clickElement(insuranceNumberInput);
        clearTextElementWithKeys(insuranceNumberInput);
        sendKeyToElement(insuranceNumberInput, value + Keys.TAB);
        Assert.assertEquals(getAttributeValue(insuranceNumberInput, "value"), value);
        return this;
    }

    @Step
    public PatientInfoPage verifyHokenNumber(String value) {
        Assert.assertEquals(getAttributeValue(insuranceNumberInput, "value"), value);
        return this;
    }

    @Step
    public String getInsuranceName() {
        sleepTimeInMilSecond(200);
        return getTextElement(insuranceName);
    }
    @Step
    public int getInsuranceId() {
        sleepTimeInSecond(1);
        return Integer.parseInt(getInsuranceName().split("\\s")[0]);
    }
    @Step
    public PatientInfoPage verifyInsuranceId(int expected) {
        await()
                .atMost(10, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .untilAsserted(() -> Assert.assertEquals(getInsuranceId(), expected));
        return this;
    }

    @Step
    public PatientInfoPage verifyInsuranceName(String value) {
        if(value.equalsIgnoreCase("blank")){
            await()
                    .atMost(10, TimeUnit.SECONDS)
                    .pollInterval(1, TimeUnit.SECONDS)
                    .untilAsserted(() -> Assert.assertEquals(getInsuranceName(), " "));
            return this;
        }
        await()
                .atMost(10, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .untilAsserted(() -> Assert.assertTrue(getInsuranceName().contains(value), "Assert failed: Expected " + getInsuranceName() + " to contain " + value));
        return this;
    }

    @Step
    public InsuranceCompanyInfoPage clickInsuranceInfoGroupInput() {
        clickElement(insuranceInfoGroupInput);
        return new InsuranceCompanyInfoPage();
    }

    @Step
    public PatientInfoPage updateAndVerifyDate() {
        sleepTimeInMilSecond(500);
        if (getAttributeValue(dateAfterConfirmText, "value").equalsIgnoreCase("令 " + DateUtil.getTodayJapaneseDate())) {
            return this;
        }
        clickElement(dateConfirmButton);
        await()
                .atMost(5, TimeUnit.SECONDS)
                .pollInterval(200, TimeUnit.MILLISECONDS)
                .untilAsserted(() -> Assert.assertEquals(getAttributeValue(dateAfterConfirmText, "value"), "令 " + DateUtil.getTodayJapaneseDate()));
        return this;
    }

    @Step
    public InsuranceMasterMaintenancePage clickMasterMaintenance() {
        clickElement(insuranceMasterMaintenanceBtn);
        return new InsuranceMasterMaintenancePage();
    }

    @Step
    public PatientInfoPage clickCreditCardAndVerify() {
        clickElement(creditCardIcon);
        verifyHokenNumber("");
        return this;
    }

    @Step
    public PatientInfoPage openListOfInsurance() {
        clickElement(openListOfInsuranceIcon);
        Assert.assertTrue(listOfOptions.size() > 0);
        return this;
    }

    @Step
    public PatientInfoPage selectInsurance(String value) {
        if (value.equalsIgnoreCase("random jihi")) {
            value = CommonUtil.getRandom(JIHI_INSURANCE.toArray(new String[0]));
        }
        selectOptionFromList(value);
        String finalValue = value;
        await()
                .atMost(5, TimeUnit.SECONDS)
                .pollInterval(200, TimeUnit.MILLISECONDS)
                .untilAsserted(() -> Assert.assertTrue(insuranceTypeName.getText().contains(finalValue)));

        return this;
    }

    @Step
    public PatientInfoPage verifyInsuranceListAvailable() {
        Assert.assertTrue(listOfOptions.size() > JIHI_INSURANCE.size());
        return this;
    }

    @Step
    public PatientInfoPage verifyThatOnlyJihiInsuranceAvailable() {
        Assert.assertEquals(listOfOptions.size(), JIHI_INSURANCE.size());
        return this;
    }

    @Step
    public PatientInfoPage verifyAllInputsInInsuranceDisabled() {
        for (int i = 4; i < allInputsInsuranceTable.size(); i++) {
            try {
                Assert.assertTrue(allInputsInsuranceTable.get(i).getAttribute("readonly").equalsIgnoreCase("true"));
                continue;
            } catch (NullPointerException e) {
                Assert.assertTrue(allInputsInsuranceTable.get(i).getAttribute("disabled").equalsIgnoreCase("true"));
            }
        }
        return this;
    }

    @Step
    public PatientInfoPage openKohiListAndVerify() {
        scrollDownToBottomOfInsuranceTable();
        scrollToBottomPage();
        for (int i = 1; i < insuranceNameInputs.size(); i++) {
            sleepTimeInMilSecond(300);
            clickElement(insuranceNameInputs.get(i));
           //clickElement(insuranceNameInputs.get(i));
            Assert.assertTrue(listOfOptions.size() > 0);
        }
        return this;
    }
    @Step
    public PatientInfoPage openHokenList() {
        clickElement(insuranceNameInputs.get(0));
        return this;
    }

    @Step
    public PatientInfoPage openKohiInsuranceListAndVerify() {
        scrollToTopOfElement(insuranceTable);
        scrollToBottomPage();
        for (int i = 0; i < openListOfInsuranceIconForKohi.size(); i++) {
            if (i > 1) {
                scrollDownToBottomOfInsuranceTable();
            }
            sleepTimeInMilSecond(300);
//            clickElement(openListOfInsuranceIconForKohi.get(i));
            clickElement(openListOfInsuranceIconForKohi.get(i));
            Assert.assertTrue(listOfOptions.size() > 0);
        }
        return this;
    }

    @Step
    public PatientInfoPage inputInsuranceDatesAndVerify() {
        for (int i = 0; i < startDateInputs.size(); i++) {
            if (i > 0) {
                scrollDownToBottomOfInsuranceTable();
                scrollToBottomPage();
            }
            sendKeyToElement(startDateInputs.get(i), DateUtil.getJapanesePastDate(10));
            sendKeyToElement(endDateInputs.get(i), DateUtil.getJapanesePastDate(7) + Keys.TAB);
            Assert.assertTrue(startDateInputs.get(i).getAttribute("class").contains("expiredDate"));
            Assert.assertTrue(endDateInputs.get(i).getAttribute("class").contains("expiredDate"));

            sendKeyToElement(startDateInputs.get(i), DateUtil.getJapanesePastDate(7));
            sendKeyToElement(endDateInputs.get(i), DateUtil.getJapaneseFutureDate(7) + Keys.TAB);
            Assert.assertFalse(startDateInputs.get(i).getAttribute("class").contains("expiredDate"));
            Assert.assertFalse(endDateInputs.get(i).getAttribute("class").contains("expiredDate"));
        }
        return this;
    }

    @Step
    public PatientInfoPage inputFutansyaAndVerify(String value) {
        while (expandButtons.size() != 0) {
            int size = expandButtons.size();
            sleepTimeInMilSecond(200);
            clickElement(expandButtons.get(0));
            sleepTimeInMilSecond(200);
            if (size == 1) break;
        }
        for (int i = 0; i < futansyaNoInput.size(); i++) {
            sendKeyToElement(futansyaNoInput.get(i), value + Keys.TAB);
            sendKeyToElement(jyukyusyaNoInput.get(i), CommonUtil.getRandomNumber(8) + Keys.TAB);
            await().atMost(10, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).untilAsserted(()->{
                boolean isTrue = insuranceNames.stream().anyMatch(n -> n.getText().contains(value));
                Assert.assertTrue(isTrue);
            });
        }
        return this;
    }

    @Step
    public PatientInfoPage clickUpdatePatientInfo() {
        clickElement(updatePatientInfoBtn);
        return this;
    }

    @Step
    public PatientInfoPage clickThreeDotBtnAndVerifyOptions() {
        scrollUpToTopOfInsuranceTable();
        clickElement(threeDotBtnMainHoken);
        Assert.assertEquals(mainHokenOptions.get(0).getText(), "種別変更（健保）");
        Assert.assertEquals(mainHokenOptions.get(1).getText(), "種別変更（労災）");
        Assert.assertEquals(mainHokenOptions.get(2).getText(), "種別変更（自賠）");
        Assert.assertEquals(mainHokenOptions.get(3).getText(), "保険コピー");
        Assert.assertEquals(mainHokenOptions.get(4).getText(), "保険削除");
        waitForPageLoaded();
        return this;
    }

    @Step
    public PatientInfoPage selectMainHokenOption(String value) {
        sleepTimeInMilSecond(500);
        for (WebElement e : mainHokenOptions) {
            if (e.getText().equalsIgnoreCase(value)) {
                clickElement(e);
                break;
            }
        }
        return this;
    }
    @Step
    public PatientInfoPage selectSystemSetting(String value){
        clickElement(systemTypeSelect, "制度種別");
        selectOptionFromList(value);
        return this;
    }
    @Step
    public PatientInfoPage verifyIndustryAccidentType1Displayed() {
        Assert.assertEquals(getAttributeValue(industrialAccidentInputs1, "readonly","年金証書番号"), "true");
        Assert.assertEquals(getAttributeValue(industrialAccidentInputs1, "readonly","健康管理手帳番号"), "true");
        Assert.assertEquals(getAttributeValue(industrialAccidentInputs2, "disabled","傷病コード"), "true");
        return this;
    }
    @Step
    public PatientInfoPage verifyIndustryAccidentType2Displayed() {
        Assert.assertEquals(getAttributeValue(industrialAccidentInputs1, "readonly","労働保険番号"), "true");
        Assert.assertEquals(getAttributeValue(industrialAccidentInputs1, "readonly","健康管理手帳番号"), "true");
        Assert.assertEquals(getAttributeValue(industrialAccidentInputs2, "disabled","傷病コード"), "true");
        return this;
    }
    @Step
    public PatientInfoPage verifyIndustryAccidentType3Displayed() {
        Assert.assertEquals(getAttributeValue(industrialAccidentInputs1, "readonly","労働保険番号"), "true");
        Assert.assertEquals(getAttributeValue(industrialAccidentInputs1, "readonly","年金証書番号"), "true");
        Assert.assertEquals(getAttributeValue(industrialAccidentInputs2, "disabled","労働局"), "true");
        Assert.assertEquals(getAttributeValue(industrialAccidentInputs2, "disabled","監督署"), "true");
        Assert.assertEquals(getAttributeValue(industrialAccidentInputs2, "disabled","災害区分"), "true");
        Assert.assertEquals(getAttributeValue(industrialAccidentInputs2, "readonly","傷病年月日"), "true");
        return this;
    }
    @Step
    public PatientInfoPage inputHealthRecordNumber(String value){
        sendKeyToElement(industrialAccidentInputs1, value, "健康管理手帳番号");
        return this;
    }
    @Step
    public PatientInfoPage inputSicknessCode(String value){
        clickElement(systemTypeSelect, "傷病コード");
        sleepTimeInMilSecond(200);
        selectOptionFromList(value);
        return this;
    }
    @Step
    public PatientInfoPage scrollDownToBottomOfInsuranceTable(){
        scrollToBottomOfElement(insuranceTable);
        return this;
    }
    @Step
    public PatientInfoPage scrollDownToBottomOfInsuranceTableAlt(){
        scrollToBottomOfElement(insuranceTableAlt);
        return this;
    }
    @Step
    public PatientInfoPage scrollUpToTopOfInsuranceTable(){
        scrollToTopOfElement(insuranceTable);
        return this;
    }
    @Step
    public PatientInfoPage scrollUpToTopOfInsuranceTableAlt(){
        scrollToTopOfElement(insuranceTableAlt);
        return this;
    }

    @Step
    public PatientInfoPage inputOutcomeReason(String value){
        clickElement(selectOutComeReasonBtn);
        clickElement(systemTypeSelect, "転帰事由");
        selectOptionFromList(value);
        clickElement(confirmBtn);
        return this;
    }
    @Step
    public PatientInfoPage verifyAllInfoBlank(){
        waitForLoadingIconToDisappear();
        await().atMost(10, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(()->{
                    Assert.assertEquals(getTextElementByValue(patientInfoKanaName),"");
                });
        Assert.assertEquals(getTextElementByValue(patientInfoName),"");
        Assert.assertEquals(getTextElementByValue(dateOfBirth),"");
        Assert.assertEquals(getTextElement(sex),"");
        return this;
    }
    @Step
    public PatientInfoPage clickClearInfoBtnAndVerify() throws Exception{
        clickElement(clearInfoBtn);
        PageFactoryManager.get(CommonPopUp.class).clickAcceptButton();
        waitForLoadingIconToDisappear();
        return this;
    }
    @Step
    public PatientInfoPage clickCancelPatientInfo(){
        clickElement(cancelPatientInfoBtn);
        return this;
    }
    @Step
    public DocumentPopup clickDocumentBtn(){
        clickElement(documentBtn);
        return new DocumentPopup();
    }
    @Step
    public GroupMasterMaintenancePopup clickSetting(){
        clickElement(settingBtn);
        clickElement(settingOption, "グループマスタメンテナンス");
        return new GroupMasterMaintenancePopup();
    }
    @Step
    public MaidenNameRegistrationPopup clickMaidenName(){
        clickElement(maidenNameBtn);
        return new MaidenNameRegistrationPopup();
    }
    @Step
    public PostalCodePopup clickPostalCode(){
        clickElement(openPostCodeBtn);
        return new PostalCodePopup();
    }
    @Step
    public ContactAddressPopup clickContactAddress(){
        clickElement(contactAddressBtn);
        return new ContactAddressPopup();
    }
    @Step
    public WorkplacePopup clickWorkplaceBtn(){
        clickElement(workplaceBtn);
        return new WorkplacePopup();
    }
    @Step
    public CalculationPopup clickCalculation(){
        clickElement(addCalculationBtn);
        return new CalculationPopup();
    }
    @Step
    public PatientInfoPage minimizeAllAndVerify(){
        for (WebElement minimizeButton : minimizeButtons) {
            clickElement(minimizeButton);
        }
        for (WebElement input : futansyaNoInput) {
            Assert.assertFalse(input.isDisplayed());
        }
        return this;
    }
    @Step
    public InsuranceConfirmationHistoryPopup clickClockIcon(){
        clickElement(clockIconBtn);
        return new InsuranceConfirmationHistoryPopup();
    }
    @Step
    public PatientInfoPage clickClearInfoBtn(){
        clickElement(clearInfoBtn);
        return this;
    }
    @Step
    public PatientInfoPage clickDeleteInfoBtn(){
        clickElement(deleteInfoBtn);
        return this;
    }
    @Step
    public PatientInfoPage clickCopyInfoBtn(){
        clickElement(copyInfoBtn);
        return this;
    }
    @Step
    private void selectOptionFromList(String value){
        for (int i = 0; i < listOfOptions.size(); i++) {
            if (listOfOptions.get(i).getText().contains(value)) {
                clickElement(listOfOptions.get(i));
                break;
            }
        }
    }
    @Step
    public PatientInfoPage selectHeadFamily(String value){
        clickElement(systemTypeSelect, "本家");
        selectOptionFromList(value);
        return this;
    }

}
