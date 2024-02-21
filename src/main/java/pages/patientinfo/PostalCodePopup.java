package pages.patientinfo;

import core.WebApi;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostalCodePopup extends WebApi {

    @FindBy(xpath = "//div[text()='郵便番号検索']")
    private WebElement title;

    @FindBy(className = "btn-close")
    private WebElement closeBtn;

    public PostalCodePopup verifyPostalCodePopupDisplayed(){
        verifyControlDisplayed(title, "Title");
        verifyControlDisplayed(closeBtn, "Close button");
        return this;
    }

    public PatientInfoPage clickCloseBtn(){
        clickElement(closeBtn);
        return new PatientInfoPage();
    }
}
