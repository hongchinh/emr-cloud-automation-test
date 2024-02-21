package pages.visitinglist;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class AddNewChartPopup extends WebApi {
    @FindBy(xpath = "//div[text()='患者検索（カルテ)']")
    private WebElement title;

    @FindBy(className = "btn-close")
    private WebElement closeBtn;

    @FindBy(id = "search")
    private WebElement search;

    @FindBy(xpath = "//div[text()='患者検索（カルテ)']/parent::div/following-sibling::div//div[@role='presentation']//div[@role='rowgroup' and not(contains(@class,'hidden')) and not(contains(@class,'header'))]//div[@role='row']")
    private List<WebElement> listOfResults;

    @Step
    public AddNewChartPopup verifyAddNewChartPopupDisplayed(){
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
    public AddNewChartPopup verifyListOfResultsDisplayed(){
        clickElement(search);
        Assert.assertTrue(listOfResults.size() > 0);
        return this;
    }
}
