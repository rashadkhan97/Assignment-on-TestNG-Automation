package testrunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
//import Pages.AddItemPage;
import Pages.LoginPage;
import Setup.Setup;

import java.io.FileReader;
import java.io.IOException;

public class LoginWithUpdatedPasswordTestRunner extends Setup {

    @Test(description = "Check if user can login with new password / Reset Password")
    public void userLoginByRegisteredAccount() throws IOException, ParseException {

        String filePath = "./src/test/resources/users.json"; // since user email and pass stored in user.json

        LoginPage loginPage = new LoginPage(driver);

        // we will login with last user's info and since it's a jsonObject so storing into it
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));
        // for email
        JSONObject userObj1 = (JSONObject) jsonArray.get(jsonArray.size() - 2);//jsonArray.size()-1 for email
        String email = userObj1.get("email").toString();

        // for updated password
        JSONObject userObj2 = (JSONObject) jsonArray.get(jsonArray.size() - 1); //jsonArray.size()-1 for  updated password
        String password = userObj2.get("new_password").toString();

        loginPage.doLogin(email, password);

        System.out.println("Login Successful with reset Password");

        //Assertion after login with updated password
        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "User Daily Costs";
        Assert.assertTrue(headerActual.contains(headerExpected));

    }
}
