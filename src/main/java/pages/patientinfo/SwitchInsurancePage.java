package pages.patientinfo;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SwitchInsurancePage extends WebApi {

    @FindBy(xpath = "//div[text()='保険組合せ変換']")
    private WebElement title;

    @FindBy(className = "btn-close")
    private WebElement closeBtn;

    @FindBy(xpath = "//div[contains(@class,'ModalExchange__contentHonken')]")
    private WebElement tableView;

    @Step
    public SwitchInsurancePage verifySwitchInsurancePageDisplayed(){
        verifyControlDisplayed(title, "Title");
        verifyControlDisplayed(tableView, "Table");
        verifyControlDisplayed(closeBtn, "Close button");
        return this;
    }

    @Step
    public PatientInfoPage clickCloseBtn() {
        clickElement(closeBtn);
        return new PatientInfoPage();
    }
}
