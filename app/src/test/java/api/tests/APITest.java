package api.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APITest {

    @Test
    public void testGetUsers() {
        RestAssured.baseURI = "https://reqres.in/api";

        given()
            .when()
                .get("/users?page=2")
            .then()
                .statusCode(200)
                .body("data", notNullValue());
    }
}
