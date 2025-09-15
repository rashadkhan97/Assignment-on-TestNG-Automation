package Pages;

import Setup.UserModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgetPasswordPage {
    @FindBy(id = ":r1:")
    WebElement txtResetEmail;

    @FindBy(css = "[type=submit]")
    WebElement ResetLinkBtn;

    // Initializes @FindBy elements with actual web page elements using the driver
    public ForgetPasswordPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void doResetPassword(String email){
        txtResetEmail.sendKeys(email);
        ResetLinkBtn.click();
    }

}
