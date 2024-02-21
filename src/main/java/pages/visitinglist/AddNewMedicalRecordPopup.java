package pages.visitinglist;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddNewMedicalRecordPopup extends WebApi {
    @FindBy(xpath = "//div[text()='カルテ台帳']")
    private WebElement title;

    @FindBy(className = "btn-close")
    private WebElement closeBtn;
    @Step
    public AddNewMedicalRecordPopup verifyAddNewMedicalRecordPopupDisplayed(){
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
