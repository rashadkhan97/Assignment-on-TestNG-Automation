package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utils {
    public static void handleAlerts(WebDriver driver) throws InterruptedException {
        // wait for alert and accept it
        WebDriverWait waitAlert = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitAlert.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

    }

    public static int generateRandomId(int min, int max){
        //generating random id with math.random
        double randomId = Math.random()*(max-min)+min;
        return (int) randomId; // double value parse to int
    }
}
