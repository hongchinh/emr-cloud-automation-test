package pages.common;

import core.WebApi;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends WebApi {
    @FindBy(id = "userId")
    private WebElement userId;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "btnSubmit_LoginForm")
    private WebElement submitBtn;

    @Step
    public LoginPage verifyLoginPageDisplayed(){
        waitForPageLoaded();
        verifyControlDisplayed(userId, "User id box");
        verifyControlDisplayed(password, "Password box");
        verifyControlDisplayed(submitBtn, "Submit button");
        return this;
    }
    @Step
    public LoginPage inputCredentials(String id, String pass){
        sendKeyToElement(userId, id);
        sendKeyToElement(password, pass);
        return this;
    }
    @Step
    public LoginPage clickSubmitButton(){
        clickElement(submitBtn);
        return this;
    }
}
