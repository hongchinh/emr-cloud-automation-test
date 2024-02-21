package pages.patientinfo;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InsuranceCompanyInfoPage extends WebApi {

    @FindBy(xpath = "//div[text()='保険者情報']")
    private WebElement title;

    @FindBy(xpath = "//span[text()='保険者番号']/following-sibling::div//input")
    private WebElement insuranceNumber;

    @FindBy(xpath = "//span[text()='名称']/following-sibling::div//input")
    private WebElement nameInput;

    @FindBy(xpath = "//span[text()='〒']/following-sibling::div//input")
    private WebElement zipcode;

    @FindBy(xpath = "//span[text()='住所']/following-sibling::div//input")
    private WebElement address;

    @FindBy(xpath = "//span[text()='電話番号']/following-sibling::div//input")
    private WebElement phone;

    @FindBy(xpath = "//span[text()='記号']/following-sibling::div//input")
    private WebElement symbol;

    @FindBy(xpath = "//span[text()='番号']/following-sibling::div//input")
    private WebElement numbers;

    @FindBy(xpath = "//button[text()='OK']")
    private WebElement okBtn;

    @FindBy(xpath = "//button[text()='キャンセル']")
    private WebElement cancelBtn;

    @Step
    public InsuranceCompanyInfoPage verifyInsuranceCompanyInfoPageDisplayed(){
        verifyControlDisplayed(title, "Title");
        verifyControlDisplayed(insuranceNumber, "Insurance number");
        verifyControlDisplayed(nameInput, "Name input");
        verifyControlDisplayed(zipcode, "Zip code");
        verifyControlDisplayed(address, "Address");
        verifyControlDisplayed(phone, "Phone number");
        verifyControlDisplayed(symbol, "Symbol");
        verifyControlDisplayed(numbers, "Numbers");
        verifyControlDisplayed(okBtn, "Ok button");
        verifyControlDisplayed(cancelBtn, "Cancel butotn");
        return this;
    }
    @Step
    public PatientInfoPage closeInfoGroup(){
        clickElement(cancelBtn);
        return new PatientInfoPage();
    }
}
