package utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

import java.io.FileInputStream;
import java.util.Properties;

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

    public static void saveRegistrationData(JSONObject jsonObject) throws ParseException, IOException {
        String filePath ="./src/test/resources/users.json";
        JSONParser jsonParser = new JSONParser(); //JSONParser obj. to read exist data

        // Parse the existing JSON data in the file (it contains an array of users)
        // Cast the parsed object into a JSONArray because our file stores multiple user objects
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(filePath));
        jsonArray.add(jsonObject); // Add the newly created user object into the existing JSON array

        // Write the updated JSON array back into the file (overwrite the old content
        FileWriter writer = new FileWriter(filePath);
        writer.write(jsonArray.toJSONString()); // Convert JSONArray to String and write
        writer.flush();
        writer.close();

    }

    public static void setEnvironmentVariable(String key, String value) throws ConfigurationException {
        PropertiesConfiguration configuration = new PropertiesConfiguration("./src/test/resources/config.properties");
        configuration.setProperty(key, value);
        configuration.save();

    }

    //Getting a token after login
    public static void getAuthToken(WebDriver driver) throws IOException {
        //wait until the authToken is available
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until((ExpectedCondition<Boolean>) wd -> js.executeScript("return window.localStorage.getItem('authToken')") != null);

        //get the authToken from the localstorage
        String authToken = (String) js.executeScript("return window.localStorage.getItem('authToken');");
        String authTokenData=(String) js.executeScript("return window.localStorage.getItem('authTokenData');");
        System.out.println("Auth Token Retrieved: " + authToken);
        System.out.println("Auth Token Retrieved: " + authTokenData);

        // Save the auth token to a local storage.json file
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("authToken", authToken);
        jsonObject.put("authTokenData", authTokenData);
        FileWriter writer=new FileWriter("./src/test/resources/localstorage.json");
        writer.write(jsonObject.toJSONString());
        writer.flush();
        writer.close();
    }

    // Setting token for further use without login, and we will set the token after getting it
    public static void setAuthToken(WebDriver driver) throws ParseException, InterruptedException, IOException {
        JSONParser jsonParser=new JSONParser();
        JSONObject authObj= (JSONObject) jsonParser.parse(new FileReader( "./src/test/resources/localstorage.json"));
        String authToken= authObj.get("authToken").toString();
        String authTokenData= authObj.get("authTokenData").toString();
        System.out.println(authToken);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('authToken', arguments[0]);", authToken);
        js.executeScript("window.localStorage.setItem('authTokenData', arguments[0]);", authTokenData);
        Thread.sleep(2000);
    }

    public String getResetLink() {
        String link = "";
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("./src/test/resources/config.properties"));
            link = extractLink(properties.getProperty("reset_link"));
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return link;
    }

    public static String extractLink(String text) {
        for (String word : text.split(" ")) {
            if (word.startsWith("http")) {
                return word;
            }
        }
        return "";
    }

}
