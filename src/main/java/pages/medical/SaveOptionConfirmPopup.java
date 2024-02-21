package pages.medical;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.common.CommonPopUp;
import utils.PageFactoryManager;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SaveOptionConfirmPopup extends CommonPopUp {

    @FindBy(xpath = "//div[text()='確認']")
    private WebElement title;

    @FindBy(id = "院外処方箋")
    private WebElement outDrugCheckbox;

    @FindBy(id = "薬情")
    private WebElement drugInfoCheckbox;

    @FindBy(xpath = "//div[contains(@class,'PrinterSetting')]//input")
    private List<WebElement> printerSettingCheckboxes;

    @FindBy(xpath = "//*[contains(@class,'ModalChecking__button_')]")
    private List<WebElement> saveBtns;

    @FindBy(xpath = "//*[contains(text(),'採用にする項目にチェックしてください')]")
    private WebElement confirmText;

    @Step
    public SaveOptionConfirmPopup verifySaveOptionConfirmPopupDisplayed() {
        verifyControlDisplayed(title, "Title");
        verifyControlDisplayed(closeBtn, "Close button");
        waitForLoadingIconToDisappear();
        return this;
    }

    public SaveOptionConfirmPopup clickOkIfDisplayed() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        boolean clicked = false;
        int count = 0;
        while (count < 3) {
            if (isControlDisplayed(confirmText, 2)) {
                PageFactoryManager.get(CommonPopUp.class).clickOkButton();
                waitForLoadingIconToDisappear();
                count = 0;
            } else {
                sleepTimeInMilSecond(500);
                count++;
            }
        }
        return this;
    }

    @Step
    public MedicalPage clickCloseBtn() {
        clickElement(closeBtn);
        return new MedicalPage();
    }

    @Step
    public SaveOptionConfirmPopup uncheckAllCheckboxes() {
        for (WebElement e : printerSettingCheckboxes) {
            unCheckToCheckBox(e);
        }
        return this;
    }

    @Step
    public SaveOptionConfirmPopup clickOutDrugCheckbox() {
        checkToCheckBoxOrRadioButton(outDrugCheckbox);
        return this;
    }

    @Step
    public SaveOptionConfirmPopup clickDrugInfoCheckbox() {
        checkToCheckBoxOrRadioButton(drugInfoCheckbox);
        return this;
    }

    @Step
    public SaveOptionConfirmPopup clickSaveBtn() {
        clickElement(saveBtns.get(1));
        return this;
    }

    @Step
    public SaveOptionConfirmPopup clickSaveAndBackBtn() {
        clickElement(saveBtns.get(0));
        return this;
    }
}
