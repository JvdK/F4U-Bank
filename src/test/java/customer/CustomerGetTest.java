package customer;
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
}
