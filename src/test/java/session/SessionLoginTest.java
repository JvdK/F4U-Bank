package session;

import com.bank.command.customer.CustomerCreateCommand;
import com.bank.command.session.LoginCommand;
import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class SessionLoginTest {



    @Test
    public void correctLogin(){
        LoginCommand command = new LoginCommand();
        command.setUsername("testacount");
        command.setPassword("test");
        given().
                contentType("application/json").
                body(command).
            when().
                post("/rest/session").
            then().
                statusCode(200);
    }

    @Test
    public void wrongPasswordLogin(){
        LoginCommand command = new LoginCommand();
        command.setUsername("testacount");
        command.setPassword("testasfdfdsaafds");
        given().
                contentType("application/json").
                body(command).
                when().
                post("/rest/session").
                then().
                statusCode(403);
    }

    @Test
    public void wrongUsernameLogin(){
        LoginCommand command = new LoginCommand();
        command.setUsername("testacount1fdas");
        command.setPassword("test");
        given().
                contentType("application/json").
                body(command).
                when().
                post("/rest/session").
                then().
                statusCode(403);
    }

    @Test
    public void nonExistingLogin(){
        LoginCommand command = new LoginCommand();
        command.setUsername("fajdlk;jfdal;");
        command.setPassword("faskldjfldsa");
        given().
                contentType("application/json").
                body(command).
                when().
                post("/rest/session").
                then().
                statusCode(403);
    }

    @Test
    public void emptyStringLogin(){
        LoginCommand command = new LoginCommand();
        command.setUsername("");
        command.setPassword("");
        given().
                contentType("application/json").
                body(command).
                when().
                post("/rest/session").
                then().
                statusCode(403);
    }

    @Test
    public void emptyLogin(){
        LoginCommand command = new LoginCommand();
        given().
                contentType("application/json").
                body(command).
                when().
                post("/rest/session").
                then().
                statusCode(403);
    }

    @Test
    public void incorrectFormatLogin(){
        CustomerCreateCommand command = new CustomerCreateCommand();
        given().
                contentType("application/json").
                body(command).
                when().
                post("/rest/session").
                then().
                statusCode(403);
    }

    @Test
    public void incorrectContentTypeLogin(){
        LoginCommand command = new LoginCommand();
        command.setUsername("testacount");
        command.setPassword("test");
        given().
                contentType("text/plain").
                body(command.toString()).
                when().
                post("/rest/session").
                then().
                statusCode(415);
    }

    @Test
    public void notActiveLogin(){
        LoginCommand command = new LoginCommand();
        command.setUsername("inactive");
        command.setPassword("inactive");
        given().
                contentType(ContentType.JSON).
                body(command).
                when().
                post("/rest/session").
                then().
                statusCode(403);
    }

}
