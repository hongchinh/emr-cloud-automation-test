package pages.patientinfo;

import core.WebApi;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalculationPopup extends WebApi {

    @FindBy(xpath = "//div[text()='算定条件']")
    private WebElement title;

    @FindBy(className = "btn-close")
    private WebElement closeBtn;

    public CalculationPopup verifyCalculationPopupDisplayed(){
        verifyControlDisplayed(title, "Title");
        verifyControlDisplayed(closeBtn, "Close button");
        return this;
    }

    public PatientInfoPage clickCloseBtn(){
        clickElement(closeBtn);
        return new PatientInfoPage();
    }
}
