package pages.medical;

import pages.common.CommonPopUp;

public class BirthDatePopup extends CommonPopUp {

    private final String fieldInput = "//span[text()='%s']/parent::div//input";

    public BirthDatePopup inputDates(String start, String end){
        sendKeyToElement(fieldInput, start, "開始日");
        sendKeyToElement(fieldInput, end, "終了日");
        return this;
    }

}
