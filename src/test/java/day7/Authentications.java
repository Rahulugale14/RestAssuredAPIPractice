package day7;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Authentications {

    @Test(priority=1)
    void testBasicAuthentication() {

        given()
                .auth().basic("postman", "password")

                .when()
                .get("https://postman-echo.com/basic-auth")

                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    @Test(priority=2)
    void testDigestAuthentication() {

        given()
                .auth().digest("postman", "password")

                .when()
                .get("https://postman-echo.com/basic-auth")

                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    @Test(priority=3)
    void testPreemptiveAuthentication() {       // this auth is combination of basic & digest authentication

        given()
                .auth().preemptive().basic("postman", "password")

                .when()
                .get("https://postman-echo.com/basic-auth")

                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();
    }

    @Test(priority=4)
    void bearerTokenAuthentication() {
         String bearerToken = System.getenv("GITHUB_TOKEN");

         given()
                 .headers("Authorization", "Bearer " +  bearerToken  )

                 .when()
                 .get("https://api.github.com/user/repos")

                 .then()
                 .statusCode(200)
                 .log().all();

    }

    @Test(priority=5)
    void testOAuth1Authentication() {
        given()
                .auth().oauth("consumerKey", "consumerSecrat", "accessToken", "tokenSecrate") //oauth1.0 authentication

                .when()
                .get("url")

                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority=6)
    void testOAuth2Authentication() {
        given()
                .auth().oauth2(System.getenv("GITHUB_TOKEN")) //oauth2.0 authentication

                .when()
                .get("https://api.github.com/user/repos")

                .then()
                .statusCode(200)
                .log().all();
    }

    //@Test(priority=7)
    void testAPIKeyAuthentication() {

        //method1
        /*
        given()

                .queryParam("appid", "ask chatgpt & learn about this test")

                .when()
                .get("api.openweathermap.org ====> paste such URL here after learning from chatgpt")

                .then()
                .statusCode(200)
                .log().all();*/

        //method2           //ask same from above method1 to method2
        given()
                .queryParam(" ", " ")
                .pathParam(" ", " ")
                .queryParam(" ", " ")
                .queryParam(" ", " ")

                .when()
                .get("https://api.openwaether.org/{mypath}")

                .then()
                .statusCode(200)
                .log().all();
    }


}
