package pages.patientinfo;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InsuranceMasterMaintenancePage extends WebApi {

    @FindBy(xpath = "//div[text()='保険マスタメンテナンス']")
    private WebElement title;

    @FindBy(name = "hokenSbtKbn")
    private WebElement classificationSelect;

    @FindBy(xpath = "(//button[@class='btn-close'])[last()]")
    private WebElement closeBtn;

    @Step
    public InsuranceMasterMaintenancePage verifyInsuranceMasterMaintenanceDisplayed(){
        verifyControlDisplayed(title, "Title");
        verifyControlDisplayed(classificationSelect, "Classification");
        return this;
    }
    @Step
    public PatientInfoPage clickCloseBtn(){
        clickElement(closeBtn);
        return new PatientInfoPage();
    }
}
