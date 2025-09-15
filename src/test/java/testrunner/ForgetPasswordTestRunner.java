package testrunner;

import Pages.ForgetPasswordPage;
import Setup.Setup;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ForgetPasswordTestRunner extends Setup {

    @Test(priority = 1, description = "User can't reset password with wrong info")
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

    @Test(priority = 2, description = "User can not reset password with wrong email address ")
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

}
