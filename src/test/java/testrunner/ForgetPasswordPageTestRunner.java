package testrunner;

import GmailService.GmailService;
import Pages.ForgetPasswordPage;
import Setup.Setup;
import com.github.javafaker.Faker;
import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class ForgetPasswordPageTestRunner extends Setup {

    @Test(priority = 1, description = "Negative Test: User can't reset password with wrong info")
    public void resetPasswordWithWrongInfo() throws InterruptedException {
        ForgetPasswordPage forgetPasswordPage = new ForgetPasswordPage(driver);
        Faker faker = new Faker();
        // Forget Password button click to go to Forget Password Page
        driver.findElement(By.partialLinkText("Reset")).click();
        String email=faker.name().firstName().toLowerCase()+"@gmail.com";
        forgetPasswordPage.doResetPassword(email);

        Thread.sleep(1500);
        // unsuccessful message assertion for first try
        String warningActual = driver.findElement(By.tagName("p")).getText();
        String warningExpected = "Your email is not registered";
        System.out.println(warningActual);
        Assert.assertTrue(warningActual.contains(warningExpected));

        // After test complete it will refresh the page
        driver.navigate().refresh();
        Thread.sleep(1000);


    }

    @Test(priority = 2, description = "Negative Test: User can not reset password with wrong email address ")
    public void resetPasswordWithWrongEmail() throws InterruptedException {
        ForgetPasswordPage forgetPasswordPage = new ForgetPasswordPage(driver);
        Faker faker = new Faker();
        String email=faker.name().firstName().toLowerCase()+"@gmail.com";
        forgetPasswordPage.doResetPassword(email);
        Thread.sleep(1500);

        // unsuccessful message assertion for second try
        String warningActual = driver.findElement(By.tagName("p")).getText();
        String warningExpected = "Your email is not registered";
        System.out.println(warningActual);
        Assert.assertTrue(warningActual.contains(warningExpected));

        // After test complete it will refresh the page
        driver.navigate().refresh();
        Thread.sleep(1000);

    }

    @Test(priority = 3, description = "Providing Valid Gmail Account for Reset Password Link ")
    public void validGmailForResetLink() throws InterruptedException, IOException, ParseException, ConfigurationException {
        ForgetPasswordPage forgetPasswordPage = new ForgetPasswordPage(driver);

        String filePath = "./src/test/resources/users.json";
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));
        JSONObject userObj = (JSONObject) jsonArray.get(jsonArray.size() - 1);

        String email= userObj.get("email").toString(); //getting the email saved in userModel.
        forgetPasswordPage.doResetPassword(email);
        Thread.sleep(1500);

        // Successful - Reset Link message assertion for third try
        String messageActual = driver.findElement(By.tagName("p")).getText();
        String messageExpected = "Password reset link sent to your email";
        Assert.assertTrue(messageActual.contains(messageExpected));
        Thread.sleep(1200);

        GmailService gmailService = new GmailService();
        gmailService.getGmailLink();
        gmailService.readMailById_02();

    }

}
