package testrunner;

import Pages.RegistrationPage;
import Setup.Setup;
import Setup.UserModel;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import utils.Utils;

public class RegistrationTestRunner extends Setup {
    @Test(description = "Check if registration is successful")
    public void userRegistration() throws InterruptedException {
        RegistrationPage registrationPage = new RegistrationPage(driver);

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
        userModel.setEmail(firstName.toLowerCase()+"@gmail.com");
        userModel.setPassword(faker.internet().password(8,12));
        //phone number last 8 digit will be taken from utils
        userModel.setPhonenumber("014"+ Utils.generateRandomId(10000000, 99999999));
        userModel.setAddress(faker.address().city());
        registrationPage.doRegister(userModel);
        Thread.sleep(3000);
        // successful message appear -- which is actually called toast message
        String successMessage = driver.findElement(By.className("Toastify__toast")).getText();
        System.out.println(successMessage);
        Assert.assertTrue(successMessage.contains("registered successfully"));

    }
}
