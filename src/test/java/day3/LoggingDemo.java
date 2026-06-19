package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class LoggingDemo {

    @Test(priority=1)
    void testLogs() {

        given()

                .when()
                .get("https://reqres.in/api/users?page=2")

                .then()
                //.log().body();                //this method prints only body from the response
                //.log().cookies();             // this method prints only cookies from the response
                //.log().headers();             // this method prints only headers from the response
                .log().all();                   //this prints everything


    }
}
