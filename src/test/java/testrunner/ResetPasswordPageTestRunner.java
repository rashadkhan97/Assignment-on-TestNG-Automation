package testrunner;

import Pages.ResetPasswordPage;
import Setup.Setup;
import Setup.UserModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class ResetPasswordPageTestRunner extends Setup {
    @Test(description = "Check if user can reset password")
    public void resetPassword() throws InterruptedException, ParseException, IOException {

        Utils utils = new Utils();
        String resPass = utils.getResetLink();
        driver.get(resPass);

        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        UserModel userModel = new UserModel();
        userModel.setPassword("UsEr!5%");

        resetPasswordPage.resetPassword(userModel);

// reset successful assertion message
        System.out.println("Reset Password Successful!");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("new_password", userModel.getPassword());
        Utils.saveRegistrationData(jsonObject);

        Thread.sleep(2000);

    }
}
