package pages.common;

import config.Url;
import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CommonPopUp extends WebApi {

    @FindBy(xpath = "//div[contains(@class,'AppModalMessage__content')]/p")
    private WebElement message;

    @FindBy(xpath = "//button[text()='はい']")
    private WebElement acceptBtn;

    @FindBy(xpath = "//button[text()='キャンセル']")
    private WebElement cancelBtn;

    @FindBy(xpath = "//button[text()='いいえ']")
    private WebElement declineBtn;

    @FindBy(xpath = "//button[text()='OK']")
    private WebElement okBtn;

    @FindBy(xpath = "//button[text()='確定']")
    private WebElement confirmBtn;

    @FindBy(xpath = "//button[text()='無視する']")
    private WebElement ignoreBtn;

    @FindBy(xpath = "(//button[@class='btn-close'])[last()]")
    public WebElement closeBtn;

    @FindBy(xpath = "//div[contains(@class,'AppModal__title') and text()='更新履歴']")
    public WebElement updateLogs;

    private final String updateLogLocator = "//div[contains(@class,'AppModal__title') and text()='更新履歴']";

    @Step
    public CommonPopUp verifyDuplicatedPatient(){
        verifyControlDisplayed(message,"message");
        Assert.assertTrue(message.getText().contains("同姓同名の患者が既に登録されています。"));
        Assert.assertTrue(message.getText().contains("登録しますか？"));
        verifyControlDisplayed(acceptBtn,"Accept button");
        verifyControlDisplayed(declineBtn,"Decline button");
        verifyControlDisplayed(closeBtn,"Close button");
        return this;
    }
    @Step
    public CommonPopUp verifyChangeConfirmPopupDisplayed(){
        verifyControlDisplayed(message,"message");
        Assert.assertTrue(message.getText().contains("属性情報が編集されています。"));
        Assert.assertTrue(message.getText().contains("変更を破棄しますか？"));
        verifyControlDisplayed(acceptBtn,"Accept button");
        verifyControlDisplayed(declineBtn,"Decline button");
        verifyControlDisplayed(closeBtn,"Close button");
        return this;
    }
    @Step
    public CommonPopUp verifyDeleteConfirmPopupDisplayed(){
        verifyControlDisplayed(message,"message");
        Assert.assertTrue(message.getText().contains("この組合せを削除しますか？"));
        verifyControlDisplayed(acceptBtn,"Accept button");
        verifyControlDisplayed(declineBtn,"Decline button");
        verifyControlDisplayed(closeBtn,"Close button");
        return this;
    }
    @Step
    public CommonPopUp verifyTimeAdditionPopupDisplayed(){
        verifyControlDisplayed(message,"message");
        Assert.assertTrue(message.getText().contains("時間加算を算定できる時間になりました。"));
        Assert.assertTrue(message.getText().contains("この患者から、夜・早加算を算定するように変更しますか？"));
        verifyControlDisplayed(acceptBtn,"Accept button");
        verifyControlDisplayed(declineBtn,"Decline button");
        verifyControlDisplayed(closeBtn,"Close button");
        return this;
    }
    @Step
    public CommonPopUp verifyMissingInfoPopupDisplayed(){
        verifyControlDisplayed(message,"message");
        Assert.assertTrue(message.getText().contains("保険者番号を入力してください。"));
        Assert.assertTrue(message.getText().contains("保険者番号は 0 〜 9 の範囲で入力してください。"));
        Assert.assertTrue(message.getText().contains("被保険者証記号を入力してください。"));
        verifyControlDisplayed(okBtn,"OK button");
        verifyControlDisplayed(closeBtn,"Close button");
        return this;
    }
    @Step
    public CommonPopUp verifyMissingInfoPopupDisplayed2(){
        verifyControlDisplayed(message,"message");
        Assert.assertTrue(message.getText().contains("傷病コードを入力してください。"));
        Assert.assertTrue(message.getText().contains("健康管理手帳番号を入力してください。"));
        Assert.assertTrue(message.getText().contains("健康管理手帳番号は 13桁で入力してください。"));
        verifyControlDisplayed(okBtn,"OK button");
        verifyControlDisplayed(closeBtn,"Close button");
        return this;
    }
    @Step
    public CommonPopUp verifyMissingInfoPopupDisplayed3(){
        verifyControlDisplayed(message,"message");
        Assert.assertTrue(message.getText().contains("転帰事由を入力してください。"));
        verifyControlDisplayed(okBtn,"OK button");
        verifyControlDisplayed(closeBtn,"Close button");
        return this;
    }
    @Step
    public CommonPopUp skipPopUps(){
        skipPopupNamed("日以上 経過しました",true, 5);
        skipPopupNamed("時間加算を算定できる時間になりました。",true, 1);
        skipPopupNamed("時間加算を算定できない時間になりました。",true, 1);
        return this;
    }
    @Step
    public CommonPopUp skipPopUpsNoWaits(){
        skipPopupNamed("日以上 経過しました",false, 5);
        skipPopupNamed("時間加算を算定できる時間になりました。",false, 1);
        return this;
    }
    @Step
    public CommonPopUp skipPopUpsLong(){
        skipPopupNamed("日以上 経過しました",false, 30);
        skipPopupNamed("時間加算を算定できる時間になりました。",false, 1);
        return this;
    }
    @Step
    public CommonPopUp skipPopupNamed(String text, boolean waitForLoadingIcon, int timeOut) {
        if(waitForLoadingIcon) waitForLoadingIconToDisappear();
        if(isControlDisplayed(message, timeOut)){
            if(message.getText().contains(text)){
                clickDeclineButton();
            }
        };

        return this;
    }
    @Step
    public CommonPopUp clickAcceptButtonIfDisplayed(int timeout){
        By by = By.xpath("//button[text()='はい']");
        if(isControlDisplayed(acceptBtn, timeout)){
            clickElement(acceptBtn);
        }
        return this;
    }
    @Step
    public CommonPopUp clickAcceptButton(){
        clickElement(acceptBtn);
        return this;
    }
    @Step
    public CommonPopUp clickDeclineButton(){
        clickElement(declineBtn);
        return this;
    }
    @Step
    public CommonPopUp clickCloseButton(){
        clickElement(closeBtn);
        return this;
    }
    @Step
    public CommonPopUp clickOkButton(){
        clickElement(okBtn);
        return this;
    }
    @Step
    public CommonPopUp clickIgnoreBtn(){
        clickElement(ignoreBtn);
        return this;
    }
    @Step
    public CommonPopUp clickCancelBtn(){
        clickElement(cancelBtn);
        return this;
    }
    @Step
    public CommonPopUp clickConfirmBtn(){
        clickElement(confirmBtn);
        return this;
    }
    @Step
    public CommonPopUp skipUpdateLogIfDisplayed(){
        if(isControlDisplayed(updateLogs, 5)){
            waitForLoadingIconToDisappear();
            waitForElementVisible(updateLogs);
            clickCloseButton();
        }
        return this;
    }
}
