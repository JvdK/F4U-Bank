package customer;
import com.bank.command.session.LoginCommand;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CustomerGetTest {

    @Test
    public void getTestUser(){
        given().
                contentType("application/json").
                when().
                get("/rest/customer/1").
                then().
                statusCode(200).
                body("userName", equalTo("testacount")).
                body("$", not(hasKey("password")));
    }

    @Test
    public void getNonExistingUser(){
        given().
                contentType("application/json").
                when().
                get("/rest/customer/-1").
                then().
                statusCode(200).
                body(isEmptyString());
    }

    @Test
    public void notLoggedInGet(){
        given().
                contentType("application/json").
                when().
                get("/rest/customer").
                then().
                statusCode(403);
    }

    @Test
    public void loggedInGet(){
        LoginCommand command = new LoginCommand();
        command.setUsername("testacount");
        command.setPassword("test");
        String cookie = given().
                contentType("application/json").
                body(command).
                when().
                post("/rest/session").
                then().
                statusCode(200).
                extract().cookie("JSESSIONID");
        System.out.println(cookie);
        given().
                contentType("application/json").
                cookie("JSESSIONID", cookie).
                when().
                get("/rest/customer").
                then().
                statusCode(200).
                body("userName", equalTo("testacount"));
    }
}
