package pages.medical;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.CommonUtil;
import utils.DateUtil;

import java.util.List;

;

public class FlowSheetSection extends MedicalPage{

    @FindBy(xpath = "//div[contains(@class,'LoadList__loadListContent_')]//div[@col-id='sinDate' and @role='gridcell']/div")
    private List<WebElement> dates;

    @FindBy(xpath = "//button[contains(@class,'react-calendar__tile react-calendar__month-view__days__day CalendarGrid__calendarCalHasCallAgain')]")
    private List<WebElement> dateButtons;

    @Step
    public String selectRandomDateInFlowSheet(){
        int randomIndex = CommonUtil.getRandomIntegerBetweenRange(2, dates.size()-2);
        String date = dates.get(randomIndex).getText();
        clickElement(dates.get(randomIndex));
        return date;
    }
    @Step
    public String doubleClickRandomDateInFlowSheet(){
        int randomIndex = CommonUtil.getRandomIntegerBetweenRange(2, dates.size()-2);
        String date = dates.get(randomIndex).getText();
        doubleClickElement(dates.get(randomIndex));
        return date;
    }
}
