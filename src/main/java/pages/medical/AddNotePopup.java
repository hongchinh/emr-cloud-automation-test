package pages.medical;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.common.CommonPopUp;

import java.util.List;

public class AddNotePopup extends CommonPopUp {


    private final String fieldInput = "//div[text()='%s']/following-sibling::div//input";

    @FindBy(xpath = "//div[text()='名称']/following-sibling::div/div")
    private WebElement nameField;

    @FindBy(xpath = "//div[@class='modal-content']//input[@placeholder='キーワードを入力']")
    private WebElement searchField;

    @FindBy(xpath = "//div[contains(@class,'ListRenderer__list')]//div[@role='button']")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//div[contains(@class,'ModalFood__item')]/div[@role='button']")
    private List<WebElement> foodResults;

    public String getNameField(){
        return nameField.getText();
    }
    public AddNotePopup searchValue(String searchValue){
        searchField.sendKeys(searchValue + Keys.ENTER);
        waitForLoadingIconToDisappear();
        clickElement(searchResults.get(0));
        return this;
    }
    public AddNotePopup inputField(String fieldName, String value){
        sendKeyToElement(fieldInput, value, fieldName);
        return this;
    }
    public AddNotePopup selectFirstFoodResult(){
        clickElement(foodResults.get(0));
        return this;
    }
}
