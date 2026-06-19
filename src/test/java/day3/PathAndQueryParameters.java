package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameters {

            //==== https://reqres.in/api/users?page=2&id=5

    @Test
    void testQueryAndPathParameters() {

        given()
                .pathParam("mypath", "users")         //this is path parameter
                .queryParam("page", 2)              //this is query parameter
                .queryParam("id", 5)                //this is query parameter

                .when()
                .get("https://reqres.in/api/{mypath}")          //removed url few parts as thy are already defined above

                .then()
                .statusCode(200)
                .log().all();
    }
}
