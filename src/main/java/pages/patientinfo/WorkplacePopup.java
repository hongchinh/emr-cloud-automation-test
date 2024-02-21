package pages.patientinfo;

import core.WebApi;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WorkplacePopup extends WebApi {

    @FindBy(xpath = "//div[text()='勤務先情報']")
    private WebElement title;

    @FindBy(className = "btn-close")
    private WebElement closeBtn;

    public WorkplacePopup verifyWorkplacePopupDisplayed(){
        verifyControlDisplayed(title, "Title");
        verifyControlDisplayed(closeBtn, "Close button");
        return this;
    }

    public PatientInfoPage clickCloseBtn(){
        clickElement(closeBtn);
        return new PatientInfoPage();
    }
}
