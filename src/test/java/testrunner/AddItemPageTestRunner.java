package testrunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.AddItemPage;
import Pages.LoginPage;
import Setup.Setup;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;

public class AddItemPageTestRunner extends Setup{

    // details on this method is given in --> LoginWithUpdatesPasswordTestRunner
    @Test(priority = 1, description = "First User will login successfully")
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

    @Test(priority = 2, description = "First Item Check if it's added successfully")
    public void AddItem_01 () throws InterruptedException {

        AddItemPage addItemPage = new AddItemPage(driver);

        driver.findElement(By.className("add-cost-button")).click();
        addItemPage.AddSkinCareProduct_01("Cerave Blemish Control Cleanser", "3000", "Face-wash for Blemish Skin");
        Utils.handleAlerts(driver);
        Thread.sleep(1000);
        System.out.println("Item 1 Added Successfully");

    }

    @Test(priority = 3, description = "Second Item Check if it's added successfully")
    public void AddItem_02 () throws InterruptedException {

        AddItemPage addItemPage = new AddItemPage(driver);

        driver.findElement(By.className("add-cost-button")).click();
        addItemPage.AddSkinCareProduct_02("Skin1004 Madagascar Centella Sun Serum", "2100", "Sun-serum for Oily Skin ");
        Utils.handleAlerts(driver);
        Thread.sleep(2000);
        System.out.println("Item 2 Added Successfully");

    }

}
