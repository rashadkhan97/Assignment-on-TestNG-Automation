package GmailService;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GmailService {

    Properties prop;
    public GmailService() throws IOException {
        prop=new Properties();
        FileInputStream fs=new FileInputStream("./src/test/resources/config.properties");
        prop.load(fs);
    }

    public String getGmailList()
    {
        RestAssured.baseURI="https://gmail.googleapis.com";
        Response res=given().contentType("application/json").header("Authorization","Bearer "+prop.getProperty("token"))
                .when().get("/gmail/v1/users/me/messages");

        System.out.println(res.asString());
        JsonPath jsonObj=res.jsonPath();
        String listId=jsonObj.get("messages[0].id");
        return listId;


    }

    public String readMailById_01() throws IOException {
        GmailService gmailService=new GmailService();
        String messageId=gmailService.getGmailList();

        RestAssured.baseURI="https://gmail.googleapis.com";
        Response res=given().contentType("application/json").header("Authorization","Bearer "+prop.getProperty("token"))
                .when().get("/gmail/v1/users/me/messages/"+messageId);

        JsonPath jsonObj=res.jsonPath();
        String myEmail=jsonObj.get("snippet");
        return myEmail;

    }

    public String getGmailLink() throws IOException {

        RestAssured.baseURI = "https://www.googleapis.com";
        Response response = given().contentType("application/json")
                .header("Authorization", "Bearer "+prop.getProperty("token"))
                .when().get("/gmail/v1/users/me/messages");
        System.out.println(response.asString());

        JsonPath jsonPath = response.jsonPath();
        String mailID = jsonPath.get("messages[0].id");
        System.out.println(mailID);
        return mailID;

    }

    public void readMailById_02() throws IOException, ConfigurationException {

        String mail_ID2 = getGmailLink();
        RestAssured.baseURI = "https://www.googleapis.com";
        Response response = given().contentType("application/json")
                .header("Authorization", "Bearer "+prop.getProperty("token"))
                .when().get("/gmail/v1/users/me/messages/"+mail_ID2); //properties.getProperty("mail_ID2"));
        System.out.println(response.asString());

        JsonPath jsonPath = response.jsonPath();
        String mailBody = jsonPath.get("snippet");
        System.out.println(mailBody);
        Utils.setEnvironmentVariable("reset_link", mailBody);

    }



//    public static void main(String[] args) throws IOException {
//        GmailService gmailService=new GmailService();
//        String myEmail=gmailService.readMailById1();
//
//
//    }


}