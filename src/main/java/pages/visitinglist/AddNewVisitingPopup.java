package pages.visitinglist;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class AddNewVisitingPopup extends WebApi {

    @FindBy(xpath = "//div[text()='患者検索（再来)']")
    private WebElement title;

    @FindBy(className = "btn-close")
    private WebElement closeBtn;

    @FindBy(xpath = "//div[text()='患者検索（再来)']/parent::div/following-sibling::div//div[@role='presentation']//div[@role='rowgroup' and not(contains(@class,'hidden')) and not(contains(@class,'header'))]//div[@role='row']")
    private List<WebElement> listOfResults;

    @Step
    public AddNewVisitingPopup verifyAddVisitingPopupDisplayed(){
        waitForLoadingIconToDisappear();
        verifyControlDisplayed(title, "Title");
        verifyControlDisplayed(closeBtn, "Close button");
        return this;
    }
    @Step
    public VisitingListPage clickCloseBtn(){
        clickElement(closeBtn);
        return new VisitingListPage();
    }
    @Step
    public AddNewVisitingPopup verifyListOfResultsDisplayed(){
        Assert.assertTrue(listOfResults.size() > 0);
        return this;
    }
}
