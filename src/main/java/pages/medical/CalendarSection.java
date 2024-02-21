package pages.medical;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.common.CommonPopUp;
import utils.DateUtil;
import utils.PageFactoryManager;

import java.util.List;;

public class CalendarSection extends MedicalPage{

    @FindBy(xpath = "//button[contains(@class,'react-calendar__tile react-calendar__month-view__days__day CalendarGrid__calendarCalHasCallAgain')]/abbr")
    private List<WebElement> dates;

    @FindBy(xpath = "//button[contains(@class,'react-calendar__tile react-calendar__month-view__days__day CalendarGrid__calendarCalHasCallAgain')]")
    private List<WebElement> dateButtons;

    @Step
    public String selectHistoryDate(){
        String date = dates.get(0).getAttribute("aria-label");
        clickElement(dateButtons.get(0));
        return DateUtil.convertJapaneseDateToWesternDate(date);
    }
    @Step
    public String doubleClickHistoryDate() throws Exception{
        String date = dates.get(0).getAttribute("aria-label");
        doubleClickElement(dateButtons.get(0));
        if(isAlertPresent()){
            acceptAlert();
        }
        else {
            PageFactoryManager.get(CommonPopUp.class).clickAcceptButton();
        }
        return DateUtil.convertJapaneseDateToWesternDate(date);
    }
}
