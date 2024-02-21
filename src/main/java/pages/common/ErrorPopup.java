package pages.common;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ErrorPopup extends WebApi {

    @FindBy(xpath = "//*[text()='会計情報がないため、窓口精算画面が開けません。']")
    private WebElement errorMessage;

    @FindBy(xpath = "//button[text()='閉じる']")
    private WebElement confirmBtn;

    @Step
    public boolean isErrorPopupDisplayed(){
        waitForPageLoaded();
        return isControlDisplayed(errorMessage, 8);
    }

    @Step
    public ErrorPopup clickConfirmBtn(){
        clickElement(confirmBtn);
        return this;
    }

}
