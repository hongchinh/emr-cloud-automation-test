package pages.medical;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.common.CommonPopUp;

public class TrialCalculationPopup extends CommonPopUp {

    @FindBy(xpath = "//div[text()='試算結果']")
    private WebElement title;

    @FindBy(xpath = "//button[text()='一時保存']")
    private WebElement temporarySaveBtn;

    @FindBy(xpath = "//button[text()='計算保存']")
    private WebElement saveBtn;

    @FindBy(xpath = "//button[text()='保存']")
    private WebElement keepBtn;

    @Step
    public TrialCalculationPopup verifyTrialCalculationPopupDisplayed(){
        verifyControlDisplayed(title, "Title");
        verifyControlDisplayed(temporarySaveBtn, "Temporary Save button");
        verifyControlDisplayed(saveBtn, "Save button");
        verifyControlDisplayed(keepBtn, "Keep button");
        verifyControlDisplayed(closeBtn, "Close button");
        waitForLoadingIconToDisappear();
        return this;
    }
    @Step
    public SaveOptionConfirmPopup clickTemporarySave(){
        clickElement(temporarySaveBtn);
        return new SaveOptionConfirmPopup();
    }
    @Step
    public boolean temporarySaveEnabled(){
        return temporarySaveBtn.isEnabled();
    }
    @Step
    public SaveOptionConfirmPopup clickKeepBtn(){
        clickElement(keepBtn);
        return new SaveOptionConfirmPopup();
    }
    @Step
    public CommonPopUp clickSaveBtn(){
        clickElement(saveBtn);
        return new CommonPopUp();
    }



}
