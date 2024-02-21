package pages.medical;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.common.CommonPopUp;
import utils.PageFactoryManager;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class TodaySection extends MedicalPage{

    //TODAY SECTION
    @FindBy(name = "syosaisinKbn")
    private WebElement syosaisinKbnSelect;

    @FindBy(name = "jikanKbn")
    private WebElement jikanKbnSelect;

    @FindBy(name = "hokenPattern")
    private WebElement hokenPatternSelect;

    @FindBy(name = "santeiKbn")
    private WebElement santeiKbnSelect;

    @FindBy(name = "kaId")
    private WebElement kaIdSelect;

    @FindBy(name = "tantoId")
    private WebElement tantoIdSelect;

    @FindBy(id = "btnSearchItem_Mdc")
    private WebElement addMedicineBtn;

    @FindBy(xpath = "//div[contains(@class,'TodayOrder')]//p[contains(@class,'OrderDetail__orderTitle_')]")
    private List<WebElement> medicineItems;

    @FindBy(xpath = "//div[contains(@class,'TodayOrder')]//div[contains(@class,'Orders__hokenGroup')]//input[contains(@class,'QuantityInput')]")
    private List<WebElement> quantityInputs;

    @Step
    public TodaySection selectRandomOptionInTodaySection() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        selectRandomItemInDropdown(syosaisinKbnSelect);
        selectRandomItemInDropdown(jikanKbnSelect);
        selectRandomItemInDropdown(hokenPatternSelect);
        PageFactoryManager.get(CommonPopUp.class).clickAcceptButtonIfDisplayed(3);
        selectRandomItemInDropdown(santeiKbnSelect);
        selectRandomItemInDropdown(kaIdSelect);
        selectRandomItemInDropdown(tantoIdSelect);
        return this;
    }
    @Step
    public MedicalPage clickAddNewMedicineInTodaySection(){
        clickElement(addMedicineBtn);
        return this;
    }
    @Step
    public MedicalPage verifyNewMedicineAddedInTodaySection(String medicineName) throws Exception {
        waitForLoadingIconToDisappear();
        sleepTimeInMilSecond(500);
        await()
                .atMost(10, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(()->{
                    Assert.assertTrue(getHokenToday().contains(medicineName));
                });
        return this;
    }
    @Step
    public MedicalPage verifyNewMedicineAddedInTodaySection(String medicineName, String usage) throws Exception {
        waitForLoadingIconToDisappear();
        sleepTimeInMilSecond(500);
        await()
                .atMost(10, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .ignoreExceptionsInstanceOf(WebDriverException.class)
                .untilAsserted(()->{
                    Assert.assertTrue(getHokenToday().contains(medicineName));
                    Assert.assertTrue(getHokenToday().contains(usage));
                });

        return this;
    }
}
