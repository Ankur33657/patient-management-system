import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthIntegrationTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI="http://localhost:4004/api";


    }

    @Test
    public void shouldReturnOkWithValidToken(){
        // step 1- arrange step-2 act and step 3 - assert
        String loginPayload= """
                {
                 "email":"ankur33657@gmail.com",
                  "password":"Ankur33657@"
                  }
                """;
        Response response=given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("JwtToken",notNullValue())
                .extract()
                .response();

        System.out.println("Generated token" + response.jsonPath().getString("JwtToken"));


    }

    @Test
    public void shouldReturnUnAuthorizedOnInvalidLogin(){

        String loginPayload= """
                {
                 "email":"ankur2@test.com",
                  "password":"Ankur3@"
                  }
                """;
        Response response= given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(401)
                .extract()
                .response();





    }

}
