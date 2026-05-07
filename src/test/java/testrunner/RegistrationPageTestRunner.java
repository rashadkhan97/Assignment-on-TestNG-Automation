package testrunner;

import GmailService.GmailService;
import Pages.RegistrationPage;
import Setup.Setup;
import Setup.UserModel;
import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import utils.Utils;

import java.io.IOException;

public class RegistrationPageTestRunner extends Setup {
    @Test(description = "Check if registration is successful")
    public void userRegistration() throws InterruptedException, ParseException, IOException {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        GmailService gmailService = new GmailService();

        Faker faker = new Faker();
        UserModel userModel = new UserModel();

        // register button click to go to registration page
        driver.findElement(By.partialLinkText("Register")).click();

        // First getting firstName and lastName through Faker then it will set .
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        // Now firstName and lastName setting inside userModel Firstname & userModel lastname .
        userModel.setFirstname(firstName);
        userModel.setLastname(lastName);
        userModel.setEmail("mdroshungpt+" + Utils.generateRandomId(1, 999) + "@gmail.com");
        userModel.setPassword(faker.internet().password(8,12));
        //phone number last 8 digit will be taken from utils -> generateRandomId
        userModel.setPhonenumber("014"+ Utils.generateRandomId(10000000, 99999999));
        userModel.setAddress(faker.address().city());
        registrationPage.doRegister(userModel);
        Thread.sleep(3000);
        // successful message appear -- which is actually called toast message
        String successMessage = driver.findElement(By.className("Toastify__toast")).getText();
        System.out.println(successMessage);
        Assert.assertTrue(successMessage.contains("registered successfully"));

        // Create a new JSON object to store the new user's details
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName",userModel.getFirstname());  // which info need we will just put. If any not need just remove
        jsonObject.put("lastName", userModel.getLastname());
        jsonObject.put("email", userModel.getEmail());
        jsonObject.put("password", userModel.getPassword());
        jsonObject.put("phoneNUmber", userModel.getPhonenumber());
        jsonObject.put("address", userModel.getAddress());

 // other code as comments

//        // saving data
//        Utils.saveRegistrationData(jsonObject);
//
//        //
//        Thread.sleep(3000);
//
//        GmailService gmailService = new GmailService();
//        Thread.sleep(1000);
//        String mailBody = gmailService.readMailById_01();
//        Assert.assertTrue(mailBody.contains("Welcome to our platform!"));


     //saving registration data
        try{
            Utils.saveRegistrationData(jsonObject);
            System.out.println("Data saved successfully!");
            Thread.sleep(5000);
            String myEmail = gmailService.readMailById_01();
            System.out.println(myEmail);

            Assert.assertTrue(myEmail.contains("Welcome to our platform"));
        }
        catch (Exception ex){
            System.out.println("Not saved "+ex);
        }


    }
}
