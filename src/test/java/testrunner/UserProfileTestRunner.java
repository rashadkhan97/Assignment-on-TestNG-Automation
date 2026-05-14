package testrunner;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.LoginPage;
import Pages.UserProfilePage;
import Setup.Setup;
import Setup.UserModel;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class UserProfileTestRunner extends Setup {

   // doing login with updated pass as lastly we have updated the password. Its details in ResetPasswordTestRunner
    @Test(priority = 1, description = "User can login successfully")
    public void userLoginByRegisteredAccount() throws IOException, ParseException {

        String filePath = "./src/test/resources/users.json";

        LoginPage loginPage = new LoginPage(driver);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));
        JSONObject userObj1 = (JSONObject) jsonArray.get(jsonArray.size() - 2);
        String email = userObj1.get("email").toString();

        JSONObject userObj2 = (JSONObject) jsonArray.get(jsonArray.size() - 1);
        String password = userObj2.get("new_password").toString();
        loginPage.doLogin(email, password);

        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "User Daily Costs";
        Assert.assertTrue(headerActual.contains(headerExpected));

    }

    @Test(priority = 2, description = "Check if user can update email")
    public void doUpdateEmail() throws InterruptedException, IOException, ParseException {

        UserProfilePage userProfilePage = new UserProfilePage(driver);

        userProfilePage.btnElements.get(0).click();
        userProfilePage.menuRole.get(0).click();

        String newEmail = "mdroshungpt+" + Utils.generateRandomId(200, 500) + "@gmail.com";
        userProfilePage.updateEmail(newEmail);
        Utils.handleAlerts(driver);

        userProfilePage.btnElements.get(0).click();
        userProfilePage.menuRole.get(1).click();

        String filePath = "./src/test/resources/users.json";
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("updated_email", newEmail);
        jsonArray.add(jsonObject);

        Utils.saveRegistrationData(jsonObject);

    }

}