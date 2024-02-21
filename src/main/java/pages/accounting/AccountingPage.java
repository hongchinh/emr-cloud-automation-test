package pages.accounting;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.patientinfo.DocumentPopup;
import pages.visitinglist.AccDuelListPopup;

import java.util.List;

public class AccountingPage extends WebApi {

    @FindBy(id = "btnViewDocument_accounting")
    private WebElement btnViewDocument;

    @FindBy(id = "btnViewReceiptPreview_accounting")
    private WebElement btnViewReceiptPreview;

    @FindBy(id = "btnReCalculation_accounting")
    private WebElement btnRecalculation;

    @FindBy(id = "btnReLoad_accounting")
    private WebElement btnReload;

    @FindBy(id = "fullmode-checkbox")
    private WebElement fullModeCheckbox;

    @FindBy(id = "btnViewAccDue_accounting")
    private WebElement accDueBtn;

    @FindBy(id = "btnDischarge_accounting")
    private WebElement btnDischargeAccounting;

    @FindBy(xpath = "//select[contains(@class,'AccountingController__select')]")
    private WebElement hokenSelect;

    @FindBy(id = "print-receipt")
    private WebElement printReceiptOption;

    @FindBy(id = "print-detail")
    private WebElement printDetailOption;

    @FindBy(id = "print-out-drg")
    private WebElement printOutDrugOption;

    @FindBy(xpath = "//button[text()='印 刷']")
    private WebElement printBtn;

    @FindBy(id = "btnSaveAccounting_accounting")
    private WebElement btnSaveAccounting;

    @FindBy(id = "btnCancelSaveAccounting_accounting")
    private WebElement btnCancelAccounting;

    @FindBy(xpath = "//span[text()='印刷']/ancestor::div[contains(@class,'AccountingSection__section_')]//input")
    private List<WebElement> allPrintOptions;

    private String accountingTabs = "//div[contains(@class,'AccountingTabHeaderChildren') and text()='%s']";

    @Step
    public AccountingPage verifyAccountingPageDisplayed(){
        waitForPageLoaded();
        waitForLoadingIconToDisappear();
        verifyControlDisplayed(btnViewDocument, "View Document Button");
        verifyControlDisplayed(btnViewReceiptPreview, "View Receipt Preview Button");
        verifyControlDisplayed(btnRecalculation, "Recalculation Button");
        verifyControlDisplayed(btnReload, "Reload Button");
        waitForLoadingIconToDisappear();
        return this;
    }
    @Step
    public boolean printBtnDisplayed(){
        return isControlDisplayed(printBtn, 2);
    }
    @Step
    public DocumentPopup clickDocumentBtn(){
        clickElement(btnViewDocument);
        return new DocumentPopup();
    }
    @Step
    public AccountingPage clickRecalculationBtn(){
        clickElement(btnRecalculation);
        waitForLoadingIconToDisappear();
        verifyAccountingPageDisplayed();
        return this;
    }
    @Step
    public AccountingPage clickReloadBtn(){
        clickElement(btnReload);
        waitForLoadingIconToDisappear();
        verifyAccountingPageDisplayed();
        return this;
    }
    @Step
    public AccountingPage clickFullModeCheckbox(){
        clickElement(fullModeCheckbox);
        verifyControlDisplayed(accountingTabs, "Accounting tab", "診療明細");
        verifyControlDisplayed(accountingTabs, "Accounting tab", "診療内訳");
        return this;
    }
    @Step
    public AccDuelListPopup clickOpenAccDuelListBtn(){
        clickElement(accDueBtn);
        return new AccDuelListPopup();
    }
    @Step
    public boolean isDischargeBtnEnabled(){
        return btnDischargeAccounting.isEnabled();
    }
    @Step
    public AccountingPage clickDischargeAccounting(){
        clickElement(btnDischargeAccounting);
        return this;
    }
    @Step
    public AccountingPage selectHoken(){
        if(getSizeSelectOption(hokenSelect) > 1){
            selectItemInDropdown(hokenSelect, 1);
        }
        return this;
    }
    @Step
    public AccountingPage uncheckAllPrintOptions(){
        for (WebElement e:allPrintOptions) {
            unCheckToCheckBox(e);
        }
        return this;
    }
    @Step
    public AccountingPage selectPrintReceiptAndPrint(){
        uncheckAllPrintOptions();
        checkToCheckBoxOrRadioButton(printReceiptOption);
        sleepTimeInMilSecond(200);
        clickElement(printBtn);
        return this;
    }
    @Step
    public AccountingPage selectPrintDetailAndPrint(){
        uncheckAllPrintOptions();
        checkToCheckBoxOrRadioButton(printDetailOption);
        sleepTimeInMilSecond(200);
        clickElement(printBtn);
        return this;
    }
    @Step
    public AccountingPage selectPrintOutDrugAndPrint(){
        uncheckAllPrintOptions();
        checkToCheckBoxOrRadioButton(printOutDrugOption);
        clickElement(printBtn);
        return this;
    }
    @Step
    public AccountingPage clickSaveAccounting(){
        clickElement(btnSaveAccounting);
        return this;
    }
    @Step
    public AccountingPage clickCancelAccounting(){
        clickElement(btnCancelAccounting);
        return this;
    }
}
