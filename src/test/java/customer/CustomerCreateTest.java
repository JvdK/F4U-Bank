package customer;

import com.bank.command.customer.CustomerCreateCommand;
import org.junit.Test;

import java.security.SecureRandom;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CustomerCreateTest {

    @Test
    public void createUserSuccess() throws ParseException {
        CustomerCreateCommand command = new CustomerCreateCommand();
        command.setUserName(generateRandomString(10));
        command.setPassword("createTest");
        command.setCountry("to be deleted");
        command.setFirstName("to be deleted");
        command.setAddress("to be deleted");
        command.setCity("to be deleted");
        command.setDateOfBirth(new Date(new SimpleDateFormat("ddMMyyyy").parse("01012000").getTime()));
        command.setEmail("tobedeleted");
        command.setInitials("T.");
        command.setLastName("createTest");
        command.setPhone("000");
        command.setPostalCode("5555 AA");

        given().
                contentType("application/json").
                body(command).
                when().
                put("/rest/customer").
                then().
                statusCode(200);

        //adding same user again should raise an error
        given().
                contentType("application/json").
                body(command).
                when().
                put("/rest/customer").
                then().
                statusCode(400);
    }

    @Test
    public void userNameToLong() throws ParseException {
        CustomerCreateCommand command = new CustomerCreateCommand();
        command.setUserName("abcdefghijklmnopqrstuvwxyz");
        command.setPassword("createTest");
        command.setCountry("to be deleted");
        command.setFirstName("to be deleted");
        command.setAddress("to be deleted");
        command.setCity("to be deleted");
        command.setDateOfBirth(new Date(new SimpleDateFormat("ddMMyyyy").parse("01012000").getTime()));
        command.setEmail("tobedeleted");
        command.setInitials("T.");
        command.setLastName("createTest");
        command.setPhone("000");
        command.setPostalCode("5555 AA");

        given().
                contentType("application/json").
                body(command).
                when().
                put("/rest/customer").
                then().
                statusCode(400);
    }


    public static String generateRandomString(int size) {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < size; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

}
