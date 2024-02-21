package pages.visitinglist;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.common.CommonPopUp;

public class AccDuelListPopup extends CommonPopUp {
    @FindBy(xpath = "//div[text()='収納一覧']")
    private WebElement title;

    @Step
    public AccDuelListPopup verifyAccDuelListPopupDisplayed(){
        waitForLoadingIconToDisappear();
        verifyControlDisplayed(title, "Title");
        verifyControlDisplayed(closeBtn, "Close button");
        waitForLoadingIconToDisappear();
        sleepTimeInSecond(1);
        return this;
    }
}
