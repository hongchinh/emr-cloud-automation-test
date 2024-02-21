package pages.patientinfo;

import core.WebApi;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DocumentPopup extends WebApi {

    @FindBy(xpath = "//div[text()='ファイル選択']")
    private WebElement title;

    @FindBy(className = "btn-close")
    private WebElement closeBtn;

    public DocumentPopup verifyDocumentPopupDisplayed(){
        verifyControlDisplayed(title, "Title");
        verifyControlDisplayed(closeBtn, "Close button");
        waitForLoadingIconToDisappear();
        return this;
    }

    public PatientInfoPage clickCloseBtn(){
        clickElement(closeBtn);
        return new PatientInfoPage();
    }
}
