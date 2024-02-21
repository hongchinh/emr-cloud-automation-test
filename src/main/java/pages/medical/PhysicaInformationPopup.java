package pages.medical;

import pages.common.CommonPopUp;

public class PhysicaInformationPopup extends CommonPopUp {

    private String inputField = "//span[text()='%s']/following-sibling::input";

    public PhysicaInformationPopup inputBMI(String height, String weight){
        sendKeyToElement(inputField, height, "身長(cm)");
        sendKeyToElement(inputField, weight, "体重(kg)");
        return this;
    }
    public String getBmi(){
        return getAttributeValue(inputField, "value", "BMI");
    }

}
