package pages.medical;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.common.CommonPopUp;

public class SaveInsuranceConfirmPopup extends CommonPopUp {

    @FindBy(xpath = "//p[contains(text(),'すべてのオーダーを組合せ') and contains(text(),'に変更しますか？')]")
    private WebElement text;

    public boolean isSaveInsuranceConfirmPopupDisplayed(){
        return isControlDisplayed(text, 5);
    }
}
