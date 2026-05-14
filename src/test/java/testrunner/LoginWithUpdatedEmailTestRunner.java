package testrunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.LoginPage;
import Setup.Setup;

import java.io.FileReader;
import java.io.IOException;

public class LoginWithUpdatedEmailTestRunner extends Setup {

    @Test(priority = 1, description = "Check if user cannot login using old email")
    public void doLoginByOldEmail() throws IOException, ParseException {

        String filePath = "./src/test/resources/users.json";

        LoginPage loginPage = new LoginPage(driver);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));
        JSONObject userObj1 = (JSONObject) jsonArray.get(jsonArray.size() - 3);
        String email = userObj1.get("email").toString();

        JSONObject userObj2 = (JSONObject) jsonArray.get(jsonArray.size() - 2);
        String password = userObj2.get("new_password").toString();
        loginPage.doLogin(email, password);

        String validationErrorActual = driver.findElement(By.tagName("p")).getText();
        String validationErrorExpected = "Invalid email or password";
        Assert.assertTrue(validationErrorActual.contains(validationErrorExpected));

        driver.navigate().refresh();

    }

    @Test(priority = 2, description = "Check if user can login with Updated email")
    public void doLoginByUpdatedEmail() throws IOException, ParseException {

        String filePath = "./src/test/resources/users.json";

        LoginPage loginPage = new LoginPage(driver);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));
        JSONObject userObj1 = (JSONObject) jsonArray.get(jsonArray.size() - 1);
        String email = userObj1.get("updated_email").toString();

        JSONObject userObj2 = (JSONObject) jsonArray.get(jsonArray.size() - 2);
        String password = userObj2.get("new_password").toString();
        loginPage.doLogin(email, password);

        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "User Daily Costs";
        Assert.assertTrue(headerActual.contains(headerExpected));
        loginPage.doLogout();

    }

}
