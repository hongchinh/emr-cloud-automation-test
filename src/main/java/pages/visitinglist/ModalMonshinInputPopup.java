package pages.visitinglist;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ModalMonshinInputPopup extends WebApi {
    @FindBy(xpath = "//div[text()='問診入力']")
    private WebElement title;

    @FindBy(xpath = "(//button[@class='btn-close'])[last()]")
    private WebElement closeBtn;

    @Step
    public ModalMonshinInputPopup verifyModalMonshinInputPopupDisplayed(){
        waitForLoadingIconToDisappear();
        verifyControlDisplayed(title, "Title");
        verifyControlDisplayed(closeBtn, "Close button");
        return this;
    }
    @Step
    public VisitingListPage clickCloseBtn(){
        clickElement(closeBtn);
        return new VisitingListPage();
    }
}
