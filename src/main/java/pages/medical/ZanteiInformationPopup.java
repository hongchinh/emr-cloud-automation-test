package pages.medical;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.common.CommonPopUp;

public class ZanteiInformationPopup extends CommonPopUp {

    @FindBy(xpath = "//div[text()='算定情報']")
    private WebElement title;
    @Step
    public ZanteiInformationPopup verifyZanteiInformationPopupDisplayed(){
        verifyControlDisplayed(title, "Title");
        verifyControlDisplayed(closeBtn, "Close button");
        waitForLoadingIconToDisappear();
        return this;
    }



}
