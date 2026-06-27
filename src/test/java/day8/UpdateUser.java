package day8;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UpdateUser {

    @Test
    void testUpdateUser(ITestContext context) {

        Faker faker = new Faker();

        JSONObject data = new JSONObject();
        data.put("name", faker.name().fullName());
        data.put("gender", "Male");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "active");

        String bearerToken = "7af0bbafabd9d5065084d1d55062c816fca52be51f376938370b11ea12c75c7e";

        //int id = (Integer) context.getAttribute("userid");      //this should come from create user request/method
        int id = (Integer) context.getSuite().getAttribute("userid");      //to run testng2.xml file

        given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType("application/json")
                .pathParam("id", id)
                .body(data.toString())

                .when()
                .put("https://gorest.co.in/public/v2/users/{id}")

                .then()
                .statusCode(200)
                .log().all();


    }

}
