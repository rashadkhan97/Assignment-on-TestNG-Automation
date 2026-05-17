package testrunner;

import Pages.RegistrationPage;
import org.testng.annotations.Test;
import Setup.CSVDataForLogin;
import Setup.Setup;

public class LoginWithCSVTestRunner extends Setup {
    @Test(priority = 1, dataProvider = "CSVDataForLogin",dataProviderClass = CSVDataForLogin.class)
    public void doSignUp(String firstname,String lastname,String email,String password,String phonenumber,String address) throws InterruptedException {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        Thread.sleep(2000);
        registrationPage.doLoginWithCSVFile(firstname,lastname,email,password,phonenumber,address);
    }
}
