package Setup;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVDataForLogin {

    @DataProvider(name = "CSVDataForLogin")
//  Reads login test data from a CSV file and returns it as a 2D Object array.
    public Object[][] getCSVData() throws IOException {
        String filePath = "./src/test/resources/userReg.csv";
   // Create a CSVParser to read the file
   // withFirstRecordAsHeader() means the first row is treated as header (column names: email, password)
        CSVParser csvParser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader());

   // Temporary list to store each record as an Object[] (firstname, lastname ...... address)
        List<Object> data = new ArrayList<>();
   // Loop through each row (record) in the CSV file
        for (CSVRecord csvRecord : csvParser) {
       // Extract values based on column names from the header
            String firstname=csvRecord.get("firstname");
            String lastname=csvRecord.get("lastname");
            String email=csvRecord.get("email");
            String password=csvRecord.get("password");
            String phonenumber=csvRecord.get("phonenumber");
            String address=csvRecord.get("address");

       // Store the email and password as an Object[] and add it to the list
            data.add(new Object[]{firstname,lastname,email,password,phonenumber,address});
        }

        // Convert the list into a 2D Object array and return
        // Example return format: [ [firstname, lastname, email, password, phonenumber, address]... ]
        return data.toArray(new Object[0][]);
    }
}
