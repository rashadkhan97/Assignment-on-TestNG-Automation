package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Setup.Setup;
import Setup.UserModel;

import java.util.List;

public class LoginPage {

    @FindBy(id = "email")
    WebElement txtEmail;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(css = "[type=submit]")
    WebElement btnLogin;

    @FindBy(css = "[role=menuitem]")
    public List<WebElement> menuRole;

    WebDriver driver;

    public LoginPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void doLogin(String email, String password) {

        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnLogin.click();

    }

    public void doLogout() {

        LoginPage loginPage = new LoginPage(driver);
        UserProfilePage userProfilePage = new UserProfilePage(driver);

        userProfilePage.btnElements.get(0).click();
        loginPage.menuRole.get(1).click();

    }

}