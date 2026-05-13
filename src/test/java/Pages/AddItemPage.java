package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddItemPage {

    @FindBy(id = "itemName")
    WebElement txtItemName;

    @FindBy(css = "[type=button]")
    List<WebElement> btnButton;

    @FindBy(id = "amount")
    WebElement txtAmount;

    @FindBy(id = "purchaseDate")
    WebElement selectPurchaseDate;

    @FindBy(id = "month")
    WebElement clickMonth;

    @FindBy(id = "remarks")
    WebElement txtRemarks;

    @FindBy(className = "submit-button")
    WebElement btnSubmit;

    //WebDriver driver;

    public AddItemPage(WebDriver driver) {

        //this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void AddSkinCareProduct_01(String itemName, String amount, String remarks) {

        txtItemName.sendKeys(itemName);
        btnButton.get(2).click();
        txtAmount.sendKeys(amount);
        Select selectMonth = new Select(clickMonth);
        selectMonth.selectByVisibleText("May");
        txtRemarks.sendKeys(remarks);
        btnSubmit.click();

    }

    public void AddSkinCareProduct_02(String itemName, String amount, String remarks) {

        txtItemName.sendKeys(itemName);
        //btnButton.get(2).click();
        txtAmount.sendKeys(amount);
        Select selectMonth = new Select(clickMonth);
        selectMonth.selectByVisibleText("May");
        txtRemarks.sendKeys(remarks);
        btnSubmit.click();

    }
}
