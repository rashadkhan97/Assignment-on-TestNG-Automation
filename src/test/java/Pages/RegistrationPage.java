package Pages;

import Setup.UserModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegistrationPage {

    // this one used only for just click on register button for registering with ***CSV file***
    @FindBy(partialLinkText = "Register")
    public WebElement clickRegisterBtn;

    @FindBy(id = "firstName")
    WebElement txtFirstName;

    @FindBy(id = "lastName")
    WebElement txtLastName;

    @FindBy(id = "email")
    WebElement txtEmail;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(id ="phoneNumber")
    WebElement txtPhoneNumber;

    @FindBy(id = "address")
    WebElement txtAddress;

    @FindBy(css = "[type=radio]")
    List<WebElement> radioBtn;

    @FindBy(css = "[type=checkbox]")
    WebElement chkTerms;

    @FindBy(id = "register")
    WebElement registerBtn;

    // Initializes @FindBy elements with actual web page elements using the driver
    public RegistrationPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void doRegister(UserModel userModel){
        txtFirstName.sendKeys(userModel.getFirstname());
        txtLastName.sendKeys(userModel.getLastname());
        txtEmail.sendKeys(userModel.getEmail());
        txtPassword.sendKeys(userModel.getPassword());
        txtPhoneNumber.sendKeys(userModel.getPhonenumber());
        txtAddress.sendKeys(userModel.getAddress());
        radioBtn.get(0).click(); // selecting male with get(0)
        chkTerms.click();
        registerBtn.click();
    }

    // Registration process with CSV File
    public void doLoginWithCSVFile(String firstname,String lastname, String email,String password,String phoneNumber,String address)
    {
        clickRegisterBtn.click();
        txtFirstName.sendKeys(firstname);
        txtLastName.sendKeys(lastname);
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        txtPhoneNumber.sendKeys(phoneNumber);
        txtAddress.sendKeys(address);
        radioBtn.get(1).click();
        chkTerms.click();
        registerBtn.click();

    }

}
