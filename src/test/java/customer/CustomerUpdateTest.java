package customer;

import com.bank.command.customer.CustomerUpdateCommand;
import com.bank.projection.customer.CustomerDetailsProjection;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CustomerUpdateTest {

    CustomerUpdateCommand restore = new CustomerUpdateCommand();

    @Before
    public void setup() {
        Response response = when().get("/rest/customer/1").then().
                statusCode(200).
                body("firstName", equalTo("donot")).
                body("lastName", equalTo("remove")).
                body("initials", equalTo("T.")).
                body("address", equalTo("Testlaan")).
                body("phone", equalTo("12345679")).
                body("postalCode", equalTo("1234 AA")).
                body("city", equalTo("Test")).
                body("country", equalTo("Netherlands")).
                body("email", equalTo("test@gmail.com")).
                extract().response();
        JsonNode node = response.body().as(JsonNode.class);
        restore.setCustomerId(node.get("customerId").asInt());
        restore.setFirstName(node.get("firstName").asText());
        restore.setLastName(node.get("lastName").asText());
        restore.setInitials(node.get("initials").asText());
        restore.setAddress(node.get("address").asText());
        restore.setPhone(node.get("phone").asText());
        restore.setPostalCode(node.get("postalCode").asText());
        restore.setCity(node.get("city").asText());
        restore.setCountry(node.get("country").asText());
        restore.setEmail(node.get("email").asText());
    }

    @Test
    public void successChange() {
        CustomerUpdateCommand updateCommand = new CustomerUpdateCommand();
        updateCommand.setCustomerId(1);
        updateCommand.setFirstName("changedFirstName");
        updateCommand.setLastName("changedLastName");
        updateCommand.setInitials("C. H. A. N. G. E. D.");
        updateCommand.setAddress("changedAddress");
        updateCommand.setPhone("changedPhone");
        updateCommand.setPostalCode("changedPostal");
        updateCommand.setCity("changedCity");
        updateCommand.setCountry("changedCountry");
        updateCommand.setEmail("changedEmail");

        try {
            given().
                    contentType(ContentType.JSON).
                    body(updateCommand).
                    when().
                    put("/rest/customer/update").
                    then().
                    statusCode(200);

            when().
                    get("/rest/customer/1").
                    then().
                    body("firstName", equalTo("changedFirstName")).
                    body("lastName", equalTo("changedLastName")).
                    body("initials", equalTo("C. H. A. N. G. E. D.")).
                    body("address", equalTo("changedAddress")).
                    body("phone", equalTo("changedPhone")).
                    body("postalCode", equalTo("changedPostal")).
                    body("city", equalTo("changedCity")).
                    body("country", equalTo("changedCountry")).
                    body("email", equalTo("changedEmail"));
        } finally {
            restore();
        }
    }

    @Test
    public void partialUpdate() {
        CustomerUpdateCommand updateCommand = new CustomerUpdateCommand();
        updateCommand.setCustomerId(1);
        updateCommand.setFirstName("changedFirstName");
        updateCommand.setLastName("changedLastName");
        try {
            given().
                    contentType(ContentType.JSON).
                    body(updateCommand).
                    when().
                    put("/rest/customer/update").
                    then().
                    statusCode(200);

            when().
                    get("/rest/customer/1").
                    then().
                    body("firstName", equalTo("changedFirstName")).
                    body("lastName", equalTo("changedLastName")).
                    body("initials", equalTo("T.")).
                    body("address", equalTo("Testlaan")).
                    body("phone", equalTo("12345679")).
                    body("postalCode", equalTo("1234 AA")).
                    body("city", equalTo("Test")).
                    body("country", equalTo("Netherlands")).
                    body("email", equalTo("test@gmail.com"));
        } finally {
            restore();
        }
    }

    private void restore() {
        given().
                contentType(ContentType.JSON).
                body(restore).
                when().
                put("/rest/customer/update").
                then().
                statusCode(200);
    }


}
