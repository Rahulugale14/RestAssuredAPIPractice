package day6;

import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class XMLSchemaValidation {

    @Test
    void validateXMLSchema() {

        given()

                .when()
                .get("http://restapi.adequateshop.com/api/Traveler")

                .then()
                .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("Traveler.xsd"));

    }
}
