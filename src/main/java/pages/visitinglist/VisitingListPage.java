package pages.visitinglist;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.medical.MedicalPage;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.awaitility.Awaitility.await;

public class VisitingListPage extends WebApi {

    @FindBy(id = "btnAddPatient_Vst")
    private WebElement addPatientBtn;

    @FindBy(id = "btnSearchWithPtNum_Vst")
    private WebElement searchPatientBox;

    @FindBy(xpath = "//span[@title='患者検索']")
    private WebElement searchPatientIcon;

    @FindBy(id = "btnAddNewVisiting_Vst")
    private WebElement visitReceptionBtn;

    @FindBy(id = "btnNewKaruteDaicho_Vst")
    private WebElement createChartBtn;

    @FindBy(id = "btnNewKarteDaicho_Vst")
    private WebElement medicalRecordLedger;

    @FindBy(xpath = "//span[@title='メンテナンス']")
    private WebElement setting;

    @FindBy(id = "btnShowVisitingList_Vst")
    private WebElement btnShowVisitingList;

    @FindBy(id = "btnShowModalKubunSetting_Vst")
    private WebElement btnShowModelKubunSetting;

    @FindBy(id = "btnShowVisitingFilter_Vst")
    private WebElement btnShowVisitingFilter;

    @FindBy(id = "btnShowVisitingTodo_Vst")
    private WebElement btnShowVisitingTodo;

    @FindBy(id = "btnShowApprovalInf_Vst")
    private WebElement btnShowApprovalInfo;

    @FindBy(xpath = "//div[@col-id='kanaName' and not(contains(@class,'header'))]")
    private List<WebElement> kanaNames;

    @FindBy(xpath = "//div[@col-id='name' and not(contains(@class,'header'))]")
    private List<WebElement> names;

    @FindBy(xpath = "//div[@col-id='ptNum' and not(contains(@class,'header'))]")
    private List<WebElement> patientIds;

    private final String patientIdLocator = "//div[@col-id='status']/following-sibling::div[@col-id='ptNum' and @role='gridcell' and text()='%s']";

    @FindBy(xpath = "//div[@col-id='sex' and not(contains(@class,'header'))]")
    private List<WebElement> sex;

    @FindBy(xpath = "//div[@col-id='birthday' and not(contains(@class,'header'))]")
    private List<WebElement> dob;

    @FindBy(xpath = "//div[@col-id='age' and not(contains(@class,'header'))]")
    private List<WebElement> age;

    @FindBy(xpath = "//div[@col-id='lastVisitDate' and not(contains(@class,'header'))]")
    private List<WebElement> lastVisit;

    @FindBy(id = "btnOpenPatientView_Vst")
    private WebElement openPatientInfoBtn;

    @FindBy(id = "btnKaruteDaicho_Vst")
    private WebElement medicalBtn;

    @FindBy(id = "btnOpenPaymentView_Vst")
    private WebElement openPayment;

    @FindBy(id = "btnOpenModalMonshinInput_Vst")
    private WebElement interviewBtn;

    @FindBy(id = "btnOpenSpecialNotes_VSt")
    private WebElement specialNotesBtn;

    @FindBy(id = "btnOpenDocument_Vst")
    private WebElement documentBtn;

    @FindBy(id = "btnOpenDiseaseRegistration_Vst")
    private WebElement diseaseBtn;

    @FindBy(id = "btnOpenAccDueList")
    private WebElement accDueBtn;

    @FindBy(xpath = "//button[text()='印刷']")
    private WebElement printBtn;

    @FindBy(xpath = "//button[text()='TODO']")
    private WebElement todoBtn;

    @FindBy(id = "btnOpenReceptionUpdate_Vst")
    private WebElement openReceptionUpdateBtn;

    @FindBy(id = "btnOpenReception_Vst")
    private WebElement openReceptionBtn;

    @FindBy(id = "btnDeleteReception_Vst")
    private WebElement deleteReceptionBtn;

    @FindBy(xpath = "//div[@class='ag-center-cols-viewport']")
    private WebElement table;

    @FindBy(id = "btnCaret_Vst")
    private WebElement expandBtn;

    @FindBy(xpath = "//button[text()='OK']")
    private WebElement okBtn;

    @FindBy(xpath = "(//div[@id='headerIdRecep_Vst'])[2]")
    private WebElement changeReceiptBtn;

    @FindBy(xpath = "(//div[@id='headerIdRecep_Vst'])[2]/span")
    private WebElement receiptName;

    @FindBy(id = "selectDate_Vst")
    private WebElement dateBtn;

    @FindBy(xpath = "//div[@id='selectDate_Vst']//span")
    private WebElement dateValue;

    @FindBy(xpath = "//button[contains(@id,'btnLink_')]")
    private List<WebElement> sortBtns;

    private String patientNumberLocator = "//div[@col-id='uketukeNo' and not(contains(@class,'header'))]";
    private String patientId = "//div[@col-id='status']/following-sibling::div[(@col-id='ptNum' or @col-id='kanaName') and not(contains(@class,'header')) and text()='%s']";
    private String patientIdHeader = "//div[(@col-id='ptNum' or @col-id='kanaName') and not(contains(@class,'header')) and text()='%s']/preceding-sibling::div[@col-id='status']/parent::div[contains(@class,'selected')]";
    private String headers = "//div[@col-id='%s' and contains(@class,'header')]";
    private String statusLocator = "(//div[@col-id='ptNum' and @role='gridcell' and text()='%s']/preceding-sibling::div)[last()]";
    private String selectedColumn = "//div[contains(@class,'row-selected')]//div[@col-id='%s' and not(contains(@class,'header'))]";
    private String textToBeSelected = "//div[contains(@id,'react-select-')]//div[@title='%s']";
    private String buttonNamed = "//button[contains(@id,'btnLink_')]";
    private String sortingSelect = "//label[text()='%s']/following-sibling::div//select";
    private String sortingInput = "//label[text()='%s']/following-sibling::div//input";
    private String headerOptions = "//div[contains(@class, 'HeaderOption') and text()='%s']";
    private String sortingCheckbox = "//label[text()='%s']/preceding-sibling::input";


    @Step
    public VisitingListPage verifyVisitingListPageDisplayed() {
        waitForPageLoaded();
        waitForLoadingIconToDisappear();
        verifyControlDisplayed(addPatientBtn, "Add patient button");
//        verifyControlDisplayed(searchPatientBox, "Search patient area");
        verifyControlDisplayed(visitReceptionBtn, "Visit Reception button");
        verifyControlDisplayed(createChartBtn, "Create chart button");
        verifyControlDisplayed(medicalRecordLedger, "Medical Record ledger");
        verifyControlDisplayed(setting, "Setting button");
//        Assert.assertTrue(names.size()>0);
        waitForLoadingIconToDisappear();
        return this;
    }
    @Step
    public VisitingListPage verifyPatientVisitButtons() {
        waitForPageLoaded();
        verifyControlDisplayed(openPatientInfoBtn, "Open patient button");
        verifyControlDisplayed(medicalBtn, "Create chart button");
        verifyControlDisplayed(openPayment, "Open payment button");
        verifyControlDisplayed(interviewBtn, "Interview button");
        verifyControlDisplayed(specialNotesBtn, "Special notes button");
        verifyControlDisplayed(documentBtn, "Document button");
        verifyControlDisplayed(diseaseBtn, "Disease button");
        verifyControlDisplayed(accDueBtn, "Acc due button");
        verifyControlDisplayed(printBtn, "Print button");
        verifyControlDisplayed(todoBtn, "TODO button");
        verifyControlDisplayed(openReceptionUpdateBtn, "Open reception update button");
        verifyControlDisplayed(openReceptionBtn, "Open reception button");
        verifyControlDisplayed(deleteReceptionBtn, "Delete reception button");
        waitForLoadingIconToDisappear();
        return this;
    }
    @Step
    public VisitingListPage verifyHeaders() {
        waitForPageLoaded();
        moveToHeaderAndVerify("uketukeNo");
        moveToHeaderAndVerify("sameVisit");
        moveToHeaderAndVerify("status");
        moveToHeaderAndVerify("kanaName");
        moveToHeaderAndVerify("name");
        moveToHeaderAndVerify("ptNum");
        moveToHeaderAndVerify("sex");
        moveToHeaderAndVerify("birthday");
        moveToHeaderAndVerify("age");
        moveToHeaderAndVerify("nameDuplicateState");
        moveToHeaderAndVerify("yoyakuTime");
        moveToHeaderAndVerify("reservationName");
        moveToHeaderAndVerify("uketukeSbtId");
        moveToHeaderAndVerify("uketukeTime");
        moveToHeaderAndVerify("sinStartTime");
        moveToHeaderAndVerify("sinEndTime");
        moveToHeaderAndVerify("kaikeiTime");
        moveToHeaderAndVerify("raiinCmt");
        moveToHeaderAndVerify("ptComment");
        moveToHeaderAndVerify("hokenPatternName");
        moveToHeaderAndVerify("tantoId");
        moveToHeaderAndVerify("kaId");
        moveToHeaderAndVerify("lastVisitDate");
        waitForLoadingIconToDisappear();
        return this;
    }
    @Step
    public VisitingListPage scrollToRightOfTable(double rate){
        scrollToRightOfElement(table, rate);
        sleepTimeInMilSecond(200);
        return this;
    }
    @Step
    public VisitingListPage clickAddPatientBtn() {
        clickElement(addPatientBtn);
        return this;
    }
    @Step
    public boolean isPatientNameDisplayed(String expected) {
        return names.stream().anyMatch(n -> n.getText().equalsIgnoreCase(expected));
    }
    @Step
    public VisitingListPage verifyPatientNameDisplayed(String expected) {
        await().atMost(10, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).untilAsserted(()->{
            Assert.assertTrue(isPatientNameDisplayed(expected));
        });
        return this;
    }
    @Step
    public boolean isPatientKanaNameDisplayed(String expected) {
        return kanaNames.stream().anyMatch(n -> n.getText().equalsIgnoreCase(expected));
    }
    @Step
    public VisitingListPage verifyPatientKanaNameDisplayed(String expected){
        await().atMost(10, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).untilAsserted(()->{
            Assert.assertTrue(isPatientKanaNameDisplayed(expected));
        });
        return this;
    }
    @Step
    public VisitingListPage verifyPatientKanaNotDisplayed(String expected) {
        boolean isTrue = kanaNames.stream().anyMatch(n -> n.getText().equalsIgnoreCase(expected));
        Assert.assertFalse(isTrue);
        return this;
    }
    @Step
    public boolean isPatientIdDisplayed(String expected) {
        return isControlDisplayed(patientIdLocator, expected);
//        return patientIds.stream().anyMatch(n -> n.getText().equalsIgnoreCase(expected));
    }

    @Step
    public VisitingListPage verifyPatientIdDisplayed(String expected){
        await().atMost(10, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).untilAsserted(()->{
            Assert.assertTrue(isPatientIdDisplayed(expected));
        });
        return this;
    }

    @Step
    public PatientSearchPopup clickSearchPatientIcon(){
        clickElement(searchPatientIcon);
        return new PatientSearchPopup();
    }
    @Step
    public VisitingListPage searchForPatientId(String id) {
        clearTextElement(searchPatientBox);
        sendKeyToElement(searchPatientBox, id + Keys.ENTER);
        sleepTimeInSecond(2);
        return this;
    }
    @Step
    public VisitingListPage inputPatientId(String id) {
        clearTextElement(searchPatientBox);
        sendKeyToElement(searchPatientBox, id);
        return this;
    }

    @Step
    private boolean isPatientAlreadySelected(String ptId){
        return isControlDisplayed(patientIdHeader, ptId);
    }

    @Step
    public VisitingListPage selectPatient(String ptId){
        //Check if already selected or not
        if(!isPatientAlreadySelected(ptId)){
            clickElement(patientId, ptId);
        }
        return this;
    }
    @Step
    public VisitingListPage selectPatient(int number){
        clickElement(patientIds.get(number-1));
        return this;
    }
    @Step
    public VisitingListPage openPatientInfo(){
        clickElement(openPatientInfoBtn);
        return this;
    }
    @Step
    public int getNumberOfPatientRecord(String id){
        return getAllElement(String.format(patientId, id)).size();
    }
    @Step
    public VisitingListPage verifyNumberOfPatientWithId(String id, int expectedNumber){
        await().atMost(10, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS)
                .untilAsserted(()->{
                    Assert.assertEquals(getNumberOfPatientRecord(id), expectedNumber);
                });
        return this;
    }
    @Step
    public VisitingListPage insertColumnForSelectedPatient(String columnName, String value, boolean isTextArea){
        moveToColumnNamed(columnName);
        WebElement inputBox = waitForElementVisible(selectedColumn, columnName);
        Actions action = new Actions(driver);
        action.moveToElement(inputBox, 0, -10).doubleClick().build().perform();
        WebElement input;
        if(!isTextArea){
            input = inputBox.findElement(By.tagName("input"));
            sendKeyToElement(input, Keys.DELETE + value + Keys.ENTER);

        }
        else {
            input = driver.findElement(By.tagName("textarea"));
            input.clear();
            sendKeyToElement(input, Keys.DELETE + value);
            //click outside
            scrollToRightOfTable(-1);
            action.moveToElement(waitForElementVisible(selectedColumn, "uketukeNo"), 0, -10).click().build().perform();
        }

        await().atMost(5, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(()->{
                    Assert.assertEquals(inputBox.getText(), value);
                });
        return this;
    }
    @Step
    private void moveToHeaderAndVerify(String headerName){
        scrollToRightOfTable(-1);
        int count = 0;
        while(count < 5 && !isControlDisplayed(headers, headerName)){
            scrollToRightOfTable(0.2);
            count ++;
        }
        Assert.assertTrue(isControlDisplayed(headers, headerName), "Header not found");
    }
    @Step
    private void moveToColumnNamed(String columnName){
        scrollToRightOfTable(-1);
        int count = 0;
        while(count < 5 && !isControlDisplayed(selectedColumn, columnName)){
            scrollToRightOfTable(0.2);
            count++;
        }
        Assert.assertTrue(isControlDisplayed(selectedColumn, columnName), "Column not found");
    }
    @Step
    public VisitingListPage selectOptionInSelectedColumn(String columnName, String value){
        moveToColumnNamed(columnName);
        WebElement inputBox = waitForElementVisible(selectedColumn, columnName);
        Actions action = new Actions(driver);
        action.moveToElement(inputBox, 0, -10).click().build().perform();
        clickElement(textToBeSelected, value);
        //click outside
        scrollToRightOfTable(-1);
        action.moveToElement(waitForElementVisible(selectedColumn, "uketukeNo"), 0, -10).click().build().perform();
        await().atMost(5, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(()->{
                    Assert.assertEquals(inputBox.getText(), value);
                });
        return this;
    }
    @Step
    public VisitingListPage clickSortAndVerify(){
        for (WebElement button : sortBtns) {
            waitForAllElementsPresence(patientNumberLocator);
            List<WebElement> listBeforeSort = this.driver.findElements(By.xpath(patientNumberLocator));
            List<Integer> rectBeforeSort = listBeforeSort.stream().map(id -> id.getRect().getY()).collect(Collectors.toList());
            clickElement(button);
            if(listBeforeSort.size() <= 2){
                continue;
            }
            waitForPageLoaded();
            await().atMost(10, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).ignoreExceptionsInstanceOf(WebDriverException.class)
                    .untilAsserted(()->{
                        waitForAllElementsPresence(patientNumberLocator);
                        List<WebElement> listAfter = this.driver.findElements(By.xpath(patientNumberLocator));
                        List<Integer> rectAfterSort = listAfter.stream().map(id -> id.getRect().getY()).collect(Collectors.toList());
                        Assert.assertFalse(rectBeforeSort.equals(rectAfterSort));
                    });
        }

        return this;
    }
    @Step
    public VisitingListPage expandSortingOptionsAndVerify(){
        clickElement(expandBtn);
        verifyControlDisplayed(sortingSelect, "Sort select", "受付種別");
        verifyControlDisplayed(sortingSelect, "Sort select", "担当医");
        verifyControlDisplayed(sortingSelect, "Sort select", "診療科");
        verifyControlDisplayed(sortingInput, "Sort input", "状態");
        verifyControlDisplayed(sortingInput, "Sort input", "来院区分");
        return this;
    }
    @Step
    public VisitingListPage selectSortSelect(String type, String value){
        selectItemInDropdown(sortingSelect, value, type);
        return this;
    }
    @Step
    public VisitingListPage clickHeader(String value){
        moveToHeaderAndVerify(value);
        clickElement(headers, value);
        return this;
    }
    @Step
    public VisitingListPage rightClickHeader(String value){
        rightClickElement(headers, value);
        verifyControlDisplayed(headerOptions, "header option", "フィールドの非表示");
        verifyControlDisplayed(headerOptions, "header option", "フィールドの再表示");
        verifyControlDisplayed(headerOptions, "header option", "フィールドの固定");
        verifyControlDisplayed(headerOptions, "header option", "フィールドの固定解除");
        verifyControlDisplayed(headerOptions, "header option", "すべてのフィールドの固定解除");
        return this;
    }
    @Step
    public VisitingListPage selectHeaderOption(String value){
        clickElement(headerOptions, value);
        return this;
    }
    @Step
    public VisitingListPage selectHeaderCheckbox(String value){
        String locator = "//input[@type='checkbox' and @id='%s']";
        clickElement(locator, value);
        clickElement(okBtn);
        return this;
    }
    @Step
    public VisitingListPage verifyHeaderNotDisplayed(String value){
        Assert.assertFalse(isControlDisplayed(headers, value));
        return this;
    }
    @Step
    public VisitingListPage verifyHeaderDisplayed(String value){
        verifyControlDisplayed(headers, "header", value);
        return this;
    }
    @Step
    public int getHeaderPosition(String value){
        WebElement e = waitForElementVisible(headers, value);
        return e.getRect().getX();
    }
    @Step
    public VisitingListPage verifyHeaderPosition(String value, int expectedPosition){
        WebElement e = waitForElementVisible(headers, value);
        Assert.assertEquals(e.getRect().getX(), expectedPosition);
        return this;
    }
    @Step
    public VisitingListPage selectSortingCheckbox(String value){
        clickElement(sortingCheckbox, value);
        return this;
    }
    @Step
    public VisitingListPage verifySelectedHeader(String headerName){
        WebElement e = waitForElementVisible(headers + "//div[contains(@class,'HeaderCustom_')]", headerName);
        Assert.assertEquals(e.getAttribute("style"), "background: var(--primary-darker); color: white;");
        return this;
    }
    @Step
    public VisitingListPage verifyUnselectedHeader(String headerName){
        WebElement e = waitForElementVisible(headers + "//div[contains(@class,'HeaderCustom_')]", headerName);
        Assert.assertEquals(e.getAttribute("style"), "background: var(--secondary); color: var(--text-color);");
        return this;
    }
    @Step
    public VisitingListPage selectDate(String value){
        clickElement(dateBtn);
        String dateLocator = "//abbr[@aria-label='%s']/parent::button";
        clickElement(dateLocator, value);
        return this;
    }
    @Step
    public VisitingListPage clickChangeReceiptBtn(){
        clickElement(changeReceiptBtn);
        return this;
    }
    @Step
    public VisitingListPage verifyDate(String expected){
        Assert.assertTrue(dateValue.getText().contains(expected));
        return this;
    }
    @Step
    public String getReceipt(){
        return receiptName.getText();
    }

    @Step
    public AddNewVisitingPopup clickAddNewVisitingBtn(){
        clickElement(visitReceptionBtn);
        return new AddNewVisitingPopup();
    }
    @Step
    public AddNewChartPopup clickAddChartBtn(){
        clickElement(createChartBtn);
        return new AddNewChartPopup();
    }
    @Step
    public AddNewMedicalRecordPopup clickAddMedicalRecordBtn(){
        clickElement(medicalRecordLedger);
        return new AddNewMedicalRecordPopup();
    }
    @Step
    public VisitingListPopup clickShowVisitingListBtn(){
        clickElement(btnShowVisitingList);
        return new VisitingListPopup();
    }
    @Step
    public VisitingListPage clickSettingBtn(){
        clickElement(setting);
        return this;
    }
    @Step
    public VisitingModalKubunSettingPopup clickShowModalKubunSettingBtn(){
        clickElement(btnShowModelKubunSetting);
        return new VisitingModalKubunSettingPopup();
    }
    @Step
    public VisitingFilterPopup clickShowVisitingFilterBtn(){
        clickElement(btnShowVisitingFilter);
        return new VisitingFilterPopup();
    }
    @Step
    public VisitingTodoPopup clickShowVisitingTodoBtn(){
        clickElement(btnShowVisitingTodo);
        return new VisitingTodoPopup();
    }
    @Step
    public VisitingApprovalPopup clickShowApprovalInfoBtn(){
        clickElement(btnShowApprovalInfo);
        return new VisitingApprovalPopup();
    }
    @Step
    public MedicalPage clickMedicalBtn(){
        clickElement(medicalBtn);
        return new MedicalPage();
    }
    @Step
    public boolean getStatusOfPayment(){
        return openPayment.isEnabled();
    }
    @Step
    public VisitingListPage clickOpenPayment(){
        clickElement(openPayment);
        return this;
    }
    @Step
    public ModalMonshinInputPopup clickOpenInterViewBtn(){
        clickElement(interviewBtn);
        return new ModalMonshinInputPopup();
    }
    @Step
    public SpecialNotePopup clickOpenSpecialNoteBtn(){
        clickElement(specialNotesBtn);
        return new SpecialNotePopup();
    }
    @Step
    public DocumentPopup clickOpenDocumentBtn(){
        clickElement(documentBtn);
        return new DocumentPopup();
    }
    @Step
    public DiseasePopup clickOpenDiseaseBtn(){
        clickElement(diseaseBtn);
        return new DiseasePopup();
    }
    @Step
    public AccDuelListPopup clickOpenAccDuelListBtn(){
        clickElement(accDueBtn);
        return new AccDuelListPopup();
    }
    @Step
    public ReceptionInfoPopup clickOpenReceptionUpdateBtn(){
        clickElement(openReceptionUpdateBtn);
        return new ReceptionInfoPopup();
    }
    @Step
    public ReceptionInfoPopup clickOpenReceptionBtn(){
        clickElement(openReceptionBtn);
        return new ReceptionInfoPopup();
    }
    @Step
    public DeleteReceptionPopup clickDeleteReceptionBtn(){
        clickElement(deleteReceptionBtn);
        return new DeleteReceptionPopup();
    }
    @Step
    public VisitingListPage verifyPatientStatus(String ptId, String expectedStatus){
        await()
                .atMost(10, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(()-> {
                    if(getTextElement(statusLocator, ptId).equalsIgnoreCase("済み")){
                        Assert.assertTrue(true);
                    }
                    else {
                        Assert.assertEquals(getTextElement(statusLocator, ptId), expectedStatus);
                    }
                });
        return this;
    }
}
