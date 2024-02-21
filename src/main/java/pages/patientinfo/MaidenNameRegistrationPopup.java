package pages.patientinfo;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MaidenNameRegistrationPopup extends WebApi {

    @FindBy(xpath = "//div[text()='旧姓登録画面']")
    private WebElement title;

    @FindBy(className = "btn-close")
    private WebElement closeBtn;

    @Step
    public MaidenNameRegistrationPopup verifyMaidenNameRegistrationDisplayed(){
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
