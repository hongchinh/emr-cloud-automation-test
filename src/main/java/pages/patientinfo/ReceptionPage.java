package pages.patientinfo;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReceptionPage extends WebApi {
    @FindBy(id = "btnAddNew_ReceptionHeader")
    private WebElement acceptBtn;

    @Step
    public ReceptionPage verifyReceptionPageDisplayed(){
        waitForPageLoaded();
        verifyControlDisplayed(acceptBtn, "Accept button");
        waitForLoadingIconToDisappear();
        return this;
    }
    @Step
    public ReceptionPage clickAcceptBtn(){
        clickElement(acceptBtn);
        return this;
    }
}
