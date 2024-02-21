package pages.medical;

import config.WebAppDriverManager;
import core.WebApi;
import io.qameta.allure.Step;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.common.CommonPopUp;
import utils.CommonUtil;
import utils.DateUtil;
import utils.PageFactoryManager;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.awaitility.Awaitility.await;

public class MedicalPage extends WebApi {

    @FindBy(id = "btnOrderHistory_Mdc")
    private WebElement btnOrderHistory;

    @FindBy(xpath = "//button[text()='オーダーシート']")
    private WebElement btnOrderSheet;

    @FindBy(id = "btnNextOrder_Mdc")
    private WebElement btnNextOrder;

    private By btnNextOrderBy = By.id("btnNextOrder_Mdc");

    @FindBy(id = "btnCancelPage_Mdc")
    private WebElement cancelPageBtn;

    //ORDER HISTORY TAB
    @FindBy(id = "btnChangeCategory_OrderHistory")
    private WebElement changeCategoryOrderBtn;

    @FindBy(xpath = "//div[contains(@class,'OrderHistory')]")
    private WebElement orderHistoryTab;

    @FindBy(xpath = "//span[contains(@title,'Do')]/following-sibling::*")
    private List<WebElement> arrowBtnOrderHistoryTab;

    @FindBy(xpath = "//span[contains(@title,'Do')]")
    private List<WebElement> doBtnOrderHistoryTab;

    @FindBy(id = "btnNextItem_HistoryNav")
    private WebElement btnNextItem;

    @FindBy(xpath = "//div[contains(@class,'history_item_container')]")
    private WebElement recordHistoryItem;

    //NEXT ORDER TAB
    @FindBy(xpath = "//button[@id='btnCut_NextOrder']")
    private WebElement doBtnNextOrderTab;

    @FindBy(id = "btnDeleteDisease_Mdc")
    private WebElement btnDeleteDisease;

    @FindBy(id = "btnDeleteDisease_Mdc")
    private List<WebElement> listBtnDeleteDisease;

    @FindBy(xpath = "//article[contains(@class,'NextOrder__nextOrderTab')]")
    private WebElement nextOrderTab;

    @FindBy(xpath = "//button[@id='btnCut_NextOrder']/following-sibling::div")
    private WebElement arrowBtnNextOrderTab;

    @FindBy(xpath = "//button[text()='すべて']")
    private WebElement doAllBtn;

    @FindBy(xpath = "//div[contains(@class,'NextOrder')]/div[contains(@class,'RichEditor')]")
    private WebElement nextOrderRecord;

    @FindBy(xpath = "//div[contains(@class,'TodayOrder')]/div[contains(@class,'RichEditor')]")
    private WebElement todayOrderRecord;

    @FindBy(xpath = "//div[contains(@class,'TodayOrder')]//div[contains(@class,'Orders__hokenGroup')]")
    private WebElement todayOrderHokenGroup;

    @FindBy(xpath = "//div[contains(@class,'TodayOrder__left')]//span[2]")
    private WebElement todayOrderDate;

    @FindBy(xpath = "//div[contains(@class,'HISTORY_CONTAINER')]//div[contains(@class,'Orders__hokenGroup')]")
    private List<WebElement> historyOrderHokenGroup;

    @FindBy(xpath = "//div[contains(@class,'HISTORY_CONTAINER')]//div[contains(@class,'Orders__hokenGroup')]//p[contains(@class,'OrderDetail__orderTitle')]")
    private List<WebElement> historyOrderHokenDetail;

    @FindBy(xpath = "//div[@role='columnheader' and @col-id='10']")
    private WebElement addDiseaseBtn;

    @FindBy(id = "btnShowDiseaseAddModal_Mdc")
    private WebElement addDiseaseInDiseaseListBtn;

    @FindBy(id = "syubyoKbn")
    private WebElement diseaseCheckBox;

    @FindBy(xpath = "//button[text()='フリー病名']")
    private WebElement inputCustomNameBtn;

    @FindBy(xpath = "//input[@name='byomei']")
    private WebElement customNameInput;

    @FindBy(xpath = "//input[@name='hosokuCmt']")
    private WebElement commentInput;

    @FindBy(xpath = "//label[text()='疾患区分']/following-sibling::div[1]/select")
    private WebElement classificationSelect1;

    @FindBy(xpath = "//label[text()='疾患区分']/following-sibling::div[2]/select")
    private WebElement classificationSelect2;

    @FindBy(xpath = "//div[contains(@class,'AppSplit__borderHorizontal')]//div[contains(@class,'NextOrder')]/span")
    private WebElement addMedicineBtn;

    @FindBy(xpath = "//div[contains(@class,'SearchInputItem')]//input[@placeholder='キーワードを入力']")
    private WebElement medicineSearch;

    @FindBy(xpath = "//div[contains(@class,'SearchInputItem__item')]")
    private List<WebElement> searchItem;

    @FindBy(xpath = "//div[@row-id='MAIN_USAGE_INPUT_ITEM_ID']/div[@col-id='displayedSuryo']/div")
    private WebElement mainUsageInputBox;

    @FindBy(xpath = "//div[@row-id='MAIN_USAGE_INPUT_ITEM_ID']/div[@col-id='displayedSuryo']/input")
    private WebElement mainUsageInput;

    @FindBy(xpath = "//span[text()='項目入力']/following-sibling::div//select")
    private WebElement inOutDrugSelect;

    @FindBy(xpath = "//div[contains(@class,'NextOrder')]//p[contains(@class,'OrderDetail__orderTitle_')]")
    private List<WebElement> medicineItems;

    @FindBy(xpath = "//div[contains(@class,'NextOrder')]//div[contains(@class,'Orders__hokenGroup')]//input[contains(@class,'QuantityInput')]")
    private List<WebElement> quantityInputs;

    @FindBy(xpath = "//div[contains(@class,'Medical__body')]/div[@class='gutter gutter-horizontal']")
    private WebElement gutter;

    @FindBy(id = "btnUploadFile_Toolbar")
    private WebElement uploadBtn;

    @FindBy(xpath = "//div[contains(@class,'HistoryHeader__left_')]//span[2]")
    private List<WebElement> orderHistoryDates;

    //SPECIAL NOTE
    @FindBy(id = "btnSummary_Mdc")
    private WebElement specialNoteBtn;

    @FindBy(xpath = "//button[text()='注意事項']")
    private WebElement noteTabBtn;

    @FindBy(xpath = "//button[text()='患者情報']")
    private WebElement patientInformationTabBtn;

    @FindBy(xpath = "//div[@class='notranslate public-DraftEditor-content']")
    private List<WebElement> commentEdits;

    @FindBy(xpath = "//div[contains(@class,'NextOrder')]//*[contains(@class,'ListAttachFile__list_')]")
    private WebElement attachedList;

    @FindBy(xpath = "//div[contains(@class,'TabImportantNotes')]//*[@viewBox='0 0 14 14' and @style='cursor: pointer;']")
    private List<WebElement> allDeleteButtonsInSpecialNotes;

    private String allDeleteButtons = "//div[contains(@class,'TabImportantNotes')]//*[@viewBox='0 0 14 14' and @style='cursor: pointer;']";

    //TOOL BAR
    @FindBy(xpath = "//*[@id='btnTemporarySave_Mdc']/parent::span")
    private WebElement temporarySaveBtnParent;

    @FindBy(id = "btnTemporarySave_Mdc")
    private WebElement temporarySaveBtn;

    @FindBy(id = "btnCommonSaveCalculation_Mdc")
    private WebElement saveCalculationBtn;

    @FindBy(id = "btnCommonSaveOption_Mdc")
    private WebElement saveOptionBtn;

    String settingButtons = "//a[@role='button' and text()='%s']";

    //RIGHT SIDE BAR
    @FindBy(id = "btnTrialCalculation_Mdc_Controller")
    private WebElement btnTrialCalculation;

    @FindBy(id = "btnPrint_Mdc_Controller")
    private WebElement btnPrint;

    @FindBy(id = "btnZanteiInformation_Mdc_Controller")
    private WebElement btnZanteiInformation;

    @FindBy(id = "iconSettingMedical")
    private WebElement btnSetting;




    private final String addNoteIcon = "//span[text()='%s']/following-sibling::span";
    private final String addInfoIcon = "//span[text()='%s']/following-sibling::div/span[2]";
    private final String infoInput = "//span[text()='%s']/parent::div//input";
    private final String nextOrderDiseaseCell = "(//div[contains(@class,'NextOrder')]//div[@col-id='%s' and contains(@class,'editing')])[last()]";
    private final String diseaseListDiseaseCell = "(//div[@id='DiseaseTableContainerId']//div[@col-id='%s' and @role='gridcell'])[last()]";

    @Step
    public MedicalPage verifyMedicalPageDisplayed(){
        waitForPageLoaded();
        waitForLoadingIconToDisappear();
        verifyControlDisplayed(btnOrderHistory, "Order History Button");
        verifyControlDisplayed(btnOrderSheet, "Order Sheet Button");
//        verifyControlDisplayed(btnNextOrder, "Next Order Button");
        return this;
    }
    @Step
    public MedicalPage verifyRecordTodayBlank(){
        Assert.assertEquals(todayOrderRecord.getText(), "");
        return this;
    }
    @Step
    public MedicalPage verifyRecordTodayNotBlank(){
        Assert.assertNotEquals(todayOrderRecord.getText(), "");
        return this;
    }
    @Step
    public String getHokenToday(){
        return todayOrderHokenGroup.getText();
    }
    @Step
    public MedicalPage verifyHokenTodayBlank(){
        await().atMost(20, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(()->{
                    Assert.assertEquals(todayOrderHokenGroup.getText(), "");
                });
        return this;
    }
    @Step
    public MedicalPage verifyHokenTodayNotBlank(){
        await().atMost(20, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(()->{
                    Assert.assertNotEquals(todayOrderHokenGroup.getText(), "");
                });
        return this;
    }
    @Step
    public MedicalPage verifyHokenTodayIsNot(String expected){
        await().atMost(20, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(()->{
                    Assert.assertNotEquals(todayOrderHokenGroup.getText(), expected);
                });
        return this;
    }
    @Step
    public MedicalPage verifyHokenTodayContains(String expected){
        await().atMost(20, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(()->{
                    Assert.assertTrue(todayOrderHokenGroup.getText().contains(expected));
                });
        return this;
    }
    @Step
    public String clickDoAllHistoryTab(){
        boolean foundMessage = false;
        String doMessage = "";
        waitForLoadingIconToDisappear();
        for(int i = 0; i < historyOrderHokenGroup.size(); i++){
            if(!historyOrderHokenGroup.get(i).getText().equalsIgnoreCase("")){
                clickElement(arrowBtnOrderHistoryTab.get(i));
                doMessage = historyOrderHokenDetail.get(i).getText();
                foundMessage = true;
                break;
            }
        }
        if(foundMessage) clickElement(doAllBtn);
        waitForLoadingIconToDisappear();
        return doMessage;
    }
    @Step
    public MedicalPage clickDoAllNextOrderTab(){
        clickElement(arrowBtnNextOrderTab);
        clickElement(doAllBtn);
        waitForLoadingIconToDisappear();
        return this;
    }
    @Step
    public MedicalPage clickDoAllInNextOrderTab(){
        clickElement(doBtnNextOrderTab);
        waitForLoadingIconToDisappear();
        return this;
    }
    @Step
    public MedicalPage clickNextOrder(){
        if(isElementDisplayedBy(btnNextOrderBy)){
            clickElement(btnNextOrder);
        }
        else {
            dragGutterRight();
            clickElement(btnNextOrder);
        }
        return this;
    }
    @Step
    public MedicalPage verifyNextOrderTabDisplayed(){
        verifyControlDisplayed(nextOrderTab, "Next order tab");
        waitForLoadingIconToDisappear();
        return this;
    }
    @Step
    public MedicalPage verifyOrderHistoryTabDisplayed(){
        verifyControlDisplayed(orderHistoryTab, "Order history tab");
        waitForLoadingIconToDisappear();
        return this;
    }

    @Step
    public MedicalPage clickOrderHistory(){
        clickElement(btnOrderHistory);
        return this;
    }
    @Step
    public MedicalPage clickDocumentIcon(){
        clickElement(changeCategoryOrderBtn);
        return this;
    }
    @Step
    public String clickDoInHistoryTab(){
        String doMessage = "";
        waitForLoadingIconToDisappear();
        for(int i = 0; i < historyOrderHokenGroup.size(); i++){
            if(!historyOrderHokenGroup.get(i).getText().equalsIgnoreCase("")){
                clickElement(doBtnOrderHistoryTab.get(i));
                doMessage = historyOrderHokenDetail.get(i).getText();
                break;
            }
        }
        waitForLoadingIconToDisappear();
        return doMessage;
    }
    @Step
    public String getLocationOfFirstRecord(){
        return recordHistoryItem.getAttribute("style");
    }
    @Step
    public MedicalPage verifyPositionOfRecordChanged(String oldPosition){
        await().atMost(5, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(()->{
                    Assert.assertNotEquals(getLocationOfFirstRecord(), oldPosition);
                });
        return this;
    }
    @Step
    public MedicalPage clickNextItemBtn(){
        clickElement(btnNextItem);
        return this;
    }
    @Step
    public MedicalPage inputRecord(String value){
        clearTextElementWithKeys(nextOrderRecord);
        nextOrderRecord.sendKeys(value);
        await().atMost(10, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(()->{
                    Assert.assertEquals(nextOrderRecord.getText(), value);
                });
        return this;
    }
    @Step
    public MedicalPage addNewDiseaseInDiseaseList(String disease, String incurable, String name, String comment) throws Exception {
        clickElement(addDiseaseInDiseaseListBtn);
        diseaseCheckBox.click();
        inputCustomNameBtn.click();
        customNameInput.clear();
        customNameInput.sendKeys(name);
        commentInput.clear();
        commentInput.sendKeys(comment);
        selectItemInDropdown(classificationSelect1, disease);
        selectItemInDropdown(classificationSelect2, incurable);
        PageFactoryManager.get(CommonPopUp.class).clickOkButton();
        return this;
    }
    @Step
    public MedicalPage verifyLastDiseaseInfoInDiseaseList(String disease, String incurable, String name){
        await().atMost(10, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(()->{
                    Assert.assertEquals(getTextElement(diseaseListDiseaseCell, "sikkanKbn"), disease);
                    Assert.assertEquals(getTextElement(diseaseListDiseaseCell, "nanbyoCd"), incurable);
                    Assert.assertEquals(getTextElement(diseaseListDiseaseCell, "byomei"), name);
                });

        return this;
    }
    @Step
    public MedicalPage addNewDisease(String disease, String incurable, String name, String comment) throws Exception {
        clickElement(addDiseaseBtn);
        diseaseCheckBox.click();
        inputCustomNameBtn.click();
        customNameInput.clear();
        customNameInput.sendKeys(name);
        commentInput.clear();
        commentInput.sendKeys(comment);
        selectItemInDropdown(classificationSelect1, disease);
        selectItemInDropdown(classificationSelect2, incurable);
        PageFactoryManager.get(CommonPopUp.class).clickOkButton();
        return this;
    }
    @Step
    public MedicalPage verifyLastDiseaseInfo(String disease, String incurable, String name, String comment){
        await().atMost(10, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(()->{
                    Assert.assertEquals(getTextElement(nextOrderDiseaseCell, "sikkanKbn"), disease);
                    Assert.assertEquals(getTextElement(nextOrderDiseaseCell, "nanbyoCd"), incurable);
                    Assert.assertEquals(getTextElement(nextOrderDiseaseCell, "byomei"), name);
                });

        return this;
    }
    @Step
    public MedicalPage clickAddNewMedicineInNextOrderTab(){
        clickElement(addMedicineBtn);
        return this;
    }
    @Step
    public String addNewMedicine(String medicineName) throws Exception {
        medicineSearch.clear();
        medicineSearch.sendKeys(medicineName + Keys.ENTER);
        sleepTimeInSecond(2);
        //Wait for loading icon disappear
        waitForElementInvisible("//div[contains(@class,'SearchInputItem__spinner_h')]");
        waitForLoadingIconToDisappear();
        clickElement(searchItem.get(0));
        String actualNameOfMedicine = "//div[@col-id='itemName' and not(contains(@class,'header'))]";
        String name = getTextElement(actualNameOfMedicine);
        PageFactoryManager.get(CommonPopUp.class).clickConfirmBtn();
        return name;
    }
    @Step
    public String addNewMedicine(String medicineName, String usage, String amount, String inOutDrug) throws Exception {
        medicineSearch.clear();
        medicineSearch.sendKeys(medicineName + Keys.ENTER);
        sleepTimeInSecond(2);
        //Wait for loading icon disappear
        waitForElementInvisible("//div[contains(@class,'SearchInputItem__spinner_h')]");
        waitForLoadingIconToDisappear();
        clickElement(searchItem.get(0));
        //Click expand btn
        clickElement("//div[contains(@class,'UsageInputItem')]//span[contains(@class,'rc-tree-switcher rc-tree-switcher_close')]");
        //Select usage
        String usageLocator = String.format("//span[text()='%s']",usage);
        clickElement(usageLocator);
        sleepTimeInSecond(1);
        if(isControlDisplayed(mainUsageInputBox, 1)){
            clickElement(mainUsageInputBox);
        }
        mainUsageInput.clear();
        mainUsageInput.sendKeys(amount);
        selectItemInDropdown(inOutDrugSelect, inOutDrug);
        String actualNameOfMedicine = "//div[@col-id='itemName' and not(contains(@class,'header'))]";
        String name = getTextElement(actualNameOfMedicine);
        PageFactoryManager.get(CommonPopUp.class).clickConfirmBtn();
        return name;
    }
    @Step
    public String addNewMedicine(String medicineName, String usage) throws Exception {
        medicineSearch.clear();
        medicineSearch.sendKeys(medicineName + Keys.ENTER);
        sleepTimeInSecond(2);
        //Wait for loading icon disappear
        waitForElementInvisible("//div[contains(@class,'SearchInputItem__spinner_h')]");
        waitForLoadingIconToDisappear();
        clickElement(searchItem.get(0));
        waitForPageLoaded();
        //Select usage
        String usageLocator = String.format("//span[text()='%s']",usage);
        clickElement(usageLocator);
        waitForPageLoaded();
        waitForLoadingIconToDisappear();
        String actualNameOfMedicine = "//div[@col-id='itemName' and not(contains(@class,'header'))]";
        String name = getTextElement(actualNameOfMedicine);
        PageFactoryManager.get(CommonPopUp.class).clickConfirmBtn();
        return name;
    }
    @Step
    public MedicalPage verifyNewMedicineAdded(String medicineName, String usage, String amount) throws Exception {
        waitForLoadingIconToDisappear();
        sleepTimeInMilSecond(500);
        dragAndDropBy(gutter, 250, 0);

        Assert.assertEquals(medicineItems.stream().anyMatch(m -> m.getAttribute("title").equalsIgnoreCase(medicineName)), true);
        Assert.assertEquals(medicineItems.stream().anyMatch(m -> m.getAttribute("title").equalsIgnoreCase(usage)), true);
        Assert.assertEquals(quantityInputs.stream().anyMatch(m -> m.getAttribute("value").equalsIgnoreCase(amount)), true);
        return this;
    }
    @Step
    public MedicalPage dragGutterRight(){
        sleepTimeInSecond(1);
        waitForLoadingIconToDisappear();
        dragAndDropBy(gutter, 250, 0);
        sleepTimeInSecond(1);
        waitForLoadingIconToDisappear();
        return this;
    }
    @Step
    public MedicalPage dragGutterLeft(int value){
        sleepTimeInSecond(1);
        waitForLoadingIconToDisappear();
        dragAndDropBy(gutter, -value, 0);
        sleepTimeInSecond(1);
        waitForLoadingIconToDisappear();
        return this;
    }
    @Step
    public MedicalPage clickSpecialNote(){
        dragGutterRight();

        clickElement(specialNoteBtn);
        return this;
    }
    @Step
    public MedicalPage verifySpecialNoteTabDisplayed(){
        verifyControlDisplayed(noteTabBtn, "Note tab");
        verifyControlDisplayed(patientInformationTabBtn, "Patient Information tab");
        return this;
    }
    @Step
    public MedicalPage clickNoteTab(){
        clickElement(noteTabBtn);
        return this;
    }
    @Step
    public MedicalPage clickPatientInformationTab(){
        clickElement(patientInformationTabBtn);
        return this;
    }
    @Step
    public MedicalPage deleteAllNote() throws Exception{
        overrideGlobalTimeout(Duration.ZERO);
        while(driver.findElements(By.xpath(allDeleteButtons)).size() != 0){
            clickElement(allDeleteButtons);
            PageFactoryManager.get(CommonPopUp.class).clickAcceptButton();
            sleepTimeInMilSecond(500);
        }
        overrideGlobalTimeout(WebAppDriverManager.getLONG_TIMEOUT());
//        for (WebElement e : allDeleteButtonsInSpecialNotes) {
//            clickElement(e);
//            PageFactoryManager.get(CommonPopUp.class).clickAcceptButton();
//            sleepTimeInMilSecond(500);
//        }
        return this;
    }
    @Step
    public MedicalPage fillNoteTab() throws Exception{
        deleteAllNote();
        AddNotePopup addNotePopup = PageFactoryManager.get(AddNotePopup.class);
        String col = "//span[text()='%s']/ancestor::div[@role='gridcell']/following-sibling::div[@col-id='%s' and not(contains(@class,'header'))]";
        String dateToday = DateUtil.getWesternDate(0);
        String randomComment = "rd cm " + CommonUtil.getRandomString(2);

        clickElement(addNoteIcon, "薬品");
        addNotePopup.searchValue("a");
        String name = addNotePopup.getNameField();
        addNotePopup.inputField("発症日",dateToday);
        addNotePopup.inputField("消失日",dateToday);
        addNotePopup.inputField("コメント",randomComment);
        addNotePopup.clickOkButton();
        Assert.assertEquals(getTextElement(col, "薬品", "1"), name);
        Assert.assertEquals(getTextElement(col, "薬品", "startDate"), dateToday);
        Assert.assertEquals(getTextElement(col, "薬品", "endDate"), dateToday);
        Assert.assertEquals(getTextElement(col, "薬品", "2"), randomComment);

        clickElement(addNoteIcon, "食物");
        addNotePopup.selectFirstFoodResult();
        name = addNotePopup.getNameField();
        addNotePopup.inputField("発症日",dateToday);
        addNotePopup.inputField("消失日",dateToday);
        addNotePopup.inputField("コメント",randomComment);
        addNotePopup.clickOkButton();
        Assert.assertEquals(getTextElement(col, "食物", "1"), name);
        Assert.assertEquals(getTextElement(col, "食物", "startDate"), dateToday);
        Assert.assertEquals(getTextElement(col, "食物", "endDate"), dateToday);
        Assert.assertEquals(getTextElement(col, "食物", "2"), randomComment);

        clickElement(addNoteIcon, "その他");
        name = "allergy " + CommonUtil.getRandomString(2);
        addNotePopup.inputField("名称", name);
        addNotePopup.inputField("発症日",dateToday);
        addNotePopup.inputField("消失日",dateToday);
        addNotePopup.inputField("コメント",randomComment);
        addNotePopup.clickOkButton();
        Assert.assertEquals(getTextElement(col, "その他", "1"), name);
        Assert.assertEquals(getTextElement(col, "その他", "startDate"), dateToday);
        Assert.assertEquals(getTextElement(col, "その他", "endDate"), dateToday);
        Assert.assertEquals(getTextElement(col, "その他", "2"), randomComment);

        clickElement(addNoteIcon, "感染症");
        addNotePopup.searchValue("a");
        name = addNotePopup.getNameField();
        addNotePopup.inputField("発症時期",dateToday);
        addNotePopup.inputField("コメント",randomComment);
        addNotePopup.clickOkButton();
        Assert.assertEquals(getTextElement(col, "感染症", "1"), name);
        Assert.assertEquals(getTextElement(col, "感染症", "startDate"), dateToday);
        Assert.assertEquals(getTextElement(col, "感染症", "2"), randomComment);

        clickElement(addNoteIcon, "既往歴");
        addNotePopup.searchValue("a");
        name = addNotePopup.getNameField();
        addNotePopup.inputField("発症時期",dateToday);
        addNotePopup.inputField("コメント",randomComment);
        addNotePopup.clickOkButton();
        Assert.assertEquals(getTextElement(col, "既往歴", "1"), name);
        Assert.assertEquals(getTextElement(col, "既往歴", "startDate"), dateToday);
        Assert.assertEquals(getTextElement(col, "既往歴", "2"), randomComment);

        clickElement(addNoteIcon, "他院");
        addNotePopup.searchValue("a");
        name = addNotePopup.getNameField();
        addNotePopup.inputField("開始日",dateToday);
        addNotePopup.inputField("終了日",dateToday);
        addNotePopup.inputField("コメント",randomComment);
        addNotePopup.clickOkButton();
        Assert.assertEquals(getTextElement(col, "他院", "1"), name);
        Assert.assertEquals(getTextElement(col, "他院", "startDate"), dateToday);
        Assert.assertEquals(getTextElement(col, "他院", "endDate"), dateToday);
        Assert.assertEquals(getTextElement(col, "他院", "2"), randomComment);

        clickElement(addNoteIcon, "OTC");
        addNotePopup.searchValue("1");
        name = addNotePopup.getNameField();
        addNotePopup.inputField("開始日",dateToday);
        addNotePopup.inputField("終了日",dateToday);
        addNotePopup.inputField("コメント",randomComment);
        addNotePopup.clickOkButton();
        Assert.assertEquals(getTextElement(col, "OTC", "1"), name);
        Assert.assertEquals(getTextElement(col, "OTC", "startDate"), dateToday);
        Assert.assertEquals(getTextElement(col, "OTC", "endDate"), dateToday);
        Assert.assertEquals(getTextElement(col, "OTC", "2"), randomComment);

        clickElement(addNoteIcon, "サプリ");
        addNotePopup.searchValue("a");
        name = addNotePopup.getNameField();
        addNotePopup.inputField("開始日",dateToday);
        addNotePopup.inputField("終了日",dateToday);
        addNotePopup.inputField("コメント",randomComment);
        addNotePopup.clickOkButton();
        Assert.assertEquals(getTextElement(col, "サプリ", "1"), name);
        Assert.assertEquals(getTextElement(col, "サプリ", "startDate"), dateToday);
        Assert.assertEquals(getTextElement(col, "サプリ", "endDate"), dateToday);
        Assert.assertEquals(getTextElement(col, "サプリ", "2"), randomComment);

        return this;
    }
    @Step
    public MedicalPage fillInformationTab() throws Exception{
        String bmiLocator = "//div[text()='%s']/following-sibling::div[@aria-colindex='2']";
        String comments = "Sick" + CommonUtil.getRandomString(3);
        String todayDate = DateUtil.getWesternDate(0);

        clearTextElementWithKeys(commentEdits.get(0));
        commentEdits.get(0).sendKeys(comments);
        clearTextElementWithKeys(commentEdits.get(1));
        commentEdits.get(1).sendKeys(comments);

        clickElement(addInfoIcon, "身体情報");
        PhysicaInformationPopup physicaInformationPopup = PageFactoryManager.get(PhysicaInformationPopup.class);
        String randomHeight = String.valueOf(CommonUtil.getRandomIntegerBetweenRange(100, 200));
        String randomWeight = String.valueOf(CommonUtil.getRandomIntegerBetweenRange(50, 100));
        physicaInformationPopup.inputBMI(randomHeight, randomWeight);
        String bmi = physicaInformationPopup.getBmi();
        physicaInformationPopup.clickOkButton();
        Assert.assertEquals(getTextElement(bmiLocator, "身長(cm)"), randomHeight);
        Assert.assertEquals(getTextElement(bmiLocator, "体重(kg)"), randomWeight);
        Assert.assertEquals(getTextElement(bmiLocator, "BMI"), bmi);

        clickElement("//div[@role='treegrid' and @aria-colcount='3']" + addNoteIcon, "開始日");
        BirthDatePopup birthDatePopup = PageFactoryManager.get(BirthDatePopup.class);
        birthDatePopup.inputDates(todayDate, todayDate);
        birthDatePopup.clickOkButton();

        String startBirthDate = "//span[text()='開始日']/ancestor::div[@role='treegrid' and @aria-colcount='3']//div[@col-id='startDate' and not(contains(@class,'header'))]";
        String endBirthDate = "//span[text()='開始日']/ancestor::div[@role='treegrid' and @aria-colcount='3']//div[@col-id='endDate' and not(contains(@class,'header'))]";

        Assert.assertEquals(getTextElement(startBirthDate), todayDate);
        Assert.assertEquals(getTextElement(endBirthDate), todayDate);

        sendKeyToElement(infoInput, todayDate + Keys.ENTER, "最終月経日");
        sendKeyToElement(infoInput, todayDate + Keys.ENTER, "排卵日");

        Assert.assertEquals(getAttributeValue(infoInput, "value", "出産予定日"), DateUtil.getWesternDate(280));
        Assert.assertEquals(getAttributeValue("(" + infoInput + ")[last()]", "value", "出産予定日"), DateUtil.getWesternDate(266));

        return this;
    }

    @Step
    public MedicalPage uploadFile(){
        uploadBtn.sendKeys(System.getProperty("user.dir") + "/src/test/resources/apple.jpeg");
        verifyControlDisplayed(attachedList,"List of attached files");
    return this;
    }
    @Step
    public boolean clickSaveTemporaryBtn(){
        //check if you can click or not
        if(getAttributeValue(temporarySaveBtnParent, "style").equalsIgnoreCase("pointer-events: auto;")){
            clickElement(temporarySaveBtn);
            return true;
        }
        else return false;
    }
    @Step
    public MedicalPage clickSaveCalculationBtn(){
        clickElement(saveCalculationBtn);
        return this;
    }
    @Step
    public SaveOptionConfirmPopup clickSaveOptionBtn(){
        clickElement(saveOptionBtn);
        return new SaveOptionConfirmPopup();
    }
    @Step
    public boolean isDiseaseAlreadyAdded(){
        return isControlDisplayed(btnDeleteDisease, 3);
    }

    @Step
    public MedicalPage verifyHistoryDateCorrect(String expected){
        try{
            await()
                    .atMost(8, TimeUnit.SECONDS)
                    .pollInterval(1, TimeUnit.SECONDS)
                    .ignoreExceptionsInstanceOf(WebDriverException.class)
                    .untilAsserted(()->{
                        Assert.assertEquals(orderHistoryDates.get(0).getText(), expected);
                    });
        }
        catch (ConditionTimeoutException|AssertionError e){
            try{
                await()
                        .atMost(8, TimeUnit.SECONDS)
                        .pollInterval(1, TimeUnit.SECONDS)
                        .ignoreExceptionsInstanceOf(WebDriverException.class)
                        .untilAsserted(()->{
                            Assert.assertEquals(orderHistoryDates.get(1).getText(), expected);
                        });
            }
            catch (ConditionTimeoutException|AssertionError ee){
                await()
                        .atMost(8, TimeUnit.SECONDS)
                        .pollInterval(1, TimeUnit.SECONDS)
                        .ignoreExceptionsInstanceOf(WebDriverException.class)
                        .untilAsserted(()->{
                            Assert.assertEquals(orderHistoryDates.get(2).getText(), expected);
                        });
            }
        }

        return this;
    }
    @Step
    public MedicalPage verifyTodayDateCorrect(String expected){
        await().atMost(20, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(()->{
                    Assert.assertEquals(todayOrderDate.getText(), expected);
        });
        return this;
    }
    @Step
    public int getNumberOfDisease(){
        return listBtnDeleteDisease.size();
    }
    @Step
    public MedicalPage verifyNumberOfDisease(int expected){
        await().atMost(10, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(()->{
                    Assert.assertEquals(getNumberOfDisease(), expected);
                });
        return this;
    }

    @Step
    public MedicalPage deleteLastDisease() throws Exception {
        clickElement(listBtnDeleteDisease.get(getNumberOfDisease()-1));
        PageFactoryManager.get(CommonPopUp.class).clickAcceptButton();
        return this;
    }
    @Step
    public MedicalPage clickSortAndVerify(){
        List<WebElement> buttons = this.driver.findElements(By.xpath("//button[contains(@id,'btnKbn')]"));
        String option = "//section[@id='tree-container']//div[@aria-grabbed='false']";
        waitForAllElementsPresence(option);

        for(int i = 9; i >= 0; i--){
            List<WebElement> listBeforeSort = this.driver.findElements(By.xpath(option));
            List<Integer> rectBeforeSort = listBeforeSort.stream().map(id -> id.getRect().getY()).collect(Collectors.toList());
            if(listBeforeSort.size() <= 2){
                return this;
            }
            clickElement(buttons.get(i));
            waitForLoadingIconToDisappear();
            waitForPageLoaded();
            await().atMost(10, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).ignoreExceptionsInstanceOf(WebDriverException.class)
                    .untilAsserted(()->{
                        waitForAllElementsPresence(option);
                        List<WebElement> listAfter = this.driver.findElements(By.xpath(option));
                        List<Integer> rectAfterSort = listAfter.stream().map(id -> id.getRect().getY()).collect(Collectors.toList());
                        Assert.assertFalse(rectBeforeSort.equals(rectAfterSort));
                    });
        }

        return this;
    }

    @Step
    public MedicalPage clickSortAndVerify(String nameOfButton){
        String option = "//section[@id='tree-container']//div[@aria-grabbed='false']";
        String buttonNamed = "//span[text()='%s']/parent::button[contains(@id,'btnKbn')]";
        waitForAllElementsPresence(option);
        List<WebElement> listBeforeSort = this.driver.findElements(By.xpath(option));
        List<Integer> rectBeforeSort = listBeforeSort.stream().map(id -> id.getRect().getY()).collect(Collectors.toList());
        if(listBeforeSort.size() <= 2){
            return this;
        }
        clickElement(buttonNamed, nameOfButton);
        waitForLoadingIconToDisappear();
        waitForPageLoaded();
        await().atMost(10, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(()->{
                    waitForAllElementsPresence(option);
                    List<WebElement> listAfter = this.driver.findElements(By.xpath(option));
                    List<Integer> rectAfterSort = listAfter.stream().map(id -> id.getRect().getY()).collect(Collectors.toList());
                    Assert.assertFalse(rectBeforeSort.equals(rectAfterSort));
                });
        return this;
    }
    @Step
    public MedicalPage doSuperSetItem(){
        String firstSuperSetItem = "//section[@id='tree-container']//div[@aria-grabbed='false']";
        String threeDotBtn = "//section[@id='tree-container']//div[@aria-grabbed='false']//div[contains(@class,'TreeTitle__action_3Q')]/span[1]";
        String dotBtn = "//li[@role='menuitem']/span[text()='Do']";
        hoverMouseToElement(firstSuperSetItem);
        clickElement(threeDotBtn);
        sleepTimeInSecond(1);
        clickElement(dotBtn);
        sleepTimeInSecond(3);
        waitForLoadingIconToDisappear();
        return this;
    }

    @Step
    public TrialCalculationPopup clickTrialCalculationBtn(){
        clickElement(btnTrialCalculation);
        return new TrialCalculationPopup();
    }
    @Step
    public ZanteiInformationPopup clickZanteiInformation(){
        clickElement(btnZanteiInformation);
        return new ZanteiInformationPopup();
    }
    @Step
    public MedicalPage clickSettingBtn(){
        clickElement(btnSetting);
        verifyControlDisplayed(settingButtons, "Setting buttons レセコメント入力", "レセコメント入力");
        verifyControlDisplayed(settingButtons, "Setting buttons 症状詳記入力", "症状詳記入力");
        verifyControlDisplayed(settingButtons, "Setting buttons カルテ設定", "カルテ設定");
        return new MedicalPage();
    }
    @Step
    public MedicalPage inputCommentInTodayOrder(String value){
        clearTextElementWithKeys(todayOrderRecord);
        sendKeyToElement(todayOrderRecord, value);
        return this;
    }
    @Step
    public MedicalPage verifyCommentInTodayOrder(String expected){
        Assert.assertEquals(todayOrderRecord.getText(), expected);
        return this;
    }

    @Step
    public MedicalPage clickCancelPageBtn(){
        clickElement(cancelPageBtn);
        return this;
    }
}
