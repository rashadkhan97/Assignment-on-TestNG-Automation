package Pages;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserProfilePage {

    @FindBy(css = "[type=button]")
    public List<WebElement> btnElements;

    @FindBy(css = "[type=email]")
    WebElement txtEmail;

    @FindBy(css = "[role=menuitem]")
    public List<WebElement> menuRole;

    public UserProfilePage(WebDriver driver) {

        PageFactory.initElements(driver, this);

    }

    public void updateEmail(String newEmail) {

        btnElements.get(1).click();
        txtEmail.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
        txtEmail.sendKeys(newEmail);
        btnElements.get(2).click();

    }


}