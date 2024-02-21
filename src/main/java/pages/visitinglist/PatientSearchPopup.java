package pages.visitinglist;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PatientSearchPopup extends WebApi {

    @FindBy(xpath = "//div[text()='患者検索']")
    private WebElement title;

    @FindBy(xpath = "//div[contains(@class,'AdvanceSearch__headerSearchTable')]/input")
    private WebElement searchPatientBox;

    @FindBy(className = "btn-close")
    private WebElement closeBtn;

    private String ptId = "//div[@col-id='ptNum' and not(@title) and @role='gridcell']/div[text()='%s']";
    private String ptName = "//div[@col-id='kanaName']/div[text()='%s']";

    @Step
    public PatientSearchPopup searchForName(String name){
        sendKeyToElement(searchPatientBox, name + Keys.ENTER);
        waitForLoadingIconToDisappear();
        return this;
    }

    @Step
    public PatientSearchPopup selectPatientId(String id){
        waitForLoadingIconToDisappear();
        sleepTimeInMilSecond(500);
        if(isControlDisplayed(title, 1)){
            doubleClickToElement(ptId, id);
        }
        return this;
    }
    @Step
    public PatientSearchPopup selectPatientName(String name){
        waitForLoadingIconToDisappear();
        if(isControlDisplayed(title, 1)){
            doubleClickToElement(ptName, name);
        }
        return this;
    }
    @Step
    public PatientSearchPopup verifyPatientSearchPopupDisplayed(){
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
