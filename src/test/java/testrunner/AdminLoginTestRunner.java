package testrunner;
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
import Setup.Setup;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class AdminLoginTestRunner extends Setup {

    @Test(priority = 1, description = "Check if admin can login")
    public void doAdminLogin() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.doLogin(System.getProperty("email"), System.getProperty("password"));

        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "Admin Dashboard";
        Assert.assertTrue(headerActual.contains(headerExpected));
        Assert.assertTrue(driver.findElement(By.className("total-count")).isDisplayed());

    }

    @Test(priority = 2, description = "Check if admin can search user by updated email")
    public void searchByUpdatedEmail() throws IOException, ParseException, InterruptedException {

        String filePath = "./src/test/resources/users.json";

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));
        JSONObject userObj = (JSONObject) jsonArray.get(jsonArray.size() - 1);
        String updatedEmail = userObj.get("updated_email").toString();

        WebElement searchBox = driver.findElement(By.className("search-box"));
        searchBox.sendKeys(updatedEmail);
        Thread.sleep(2000);

        List<WebElement> displayedEmail = driver.findElements(By.tagName("td"));
        String actualEmail = displayedEmail.get(2).getText();

        Assert.assertTrue(actualEmail.contains("mdroshungpt"));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogout();
    }

}