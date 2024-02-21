package pages.patientinfo;

import core.WebApi;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InsuranceConfirmationHistoryPopup extends WebApi {

    @FindBy(xpath = "//div[text()='保険確認履歴']")
    private WebElement title;

    @FindBy(xpath = "(//button[@class='btn-close'])[last()]")
    private WebElement closeBtn;

    public InsuranceConfirmationHistoryPopup verifyHistoryPopupDisplayed(){
        verifyControlDisplayed(title, "Title");
        verifyControlDisplayed(closeBtn, "Close button");
        return this;
    }

    public PatientInfoPage clickCloseBtn(){
        clickElement(closeBtn);
        return new PatientInfoPage();
    }
}
