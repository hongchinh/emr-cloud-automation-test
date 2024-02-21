package pages.patientinfo;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GroupMasterMaintenancePopup extends WebApi {

    @FindBy(xpath = "//div[text()='グループマスタメンテナンス']")
    private WebElement title;

    @FindBy(className = "btn-close")
    private WebElement closeBtn;

    @Step
    public GroupMasterMaintenancePopup verifyGroupMasterMaintenanceDisplayed(){
        verifyControlDisplayed(title, "Title");
        verifyControlDisplayed(closeBtn, "Close button");
        return this;
    }
    @Step
    public PatientInfoPage clickCloseBtn(){
        clickElement(closeBtn);
        return new PatientInfoPage();
    }
}
