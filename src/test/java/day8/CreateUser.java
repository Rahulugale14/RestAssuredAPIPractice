package day8;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CreateUser {

    @Test
    void testCreateUser(ITestContext context) {

        Faker faker = new Faker();

        JSONObject data = new JSONObject();
        data.put("name", faker.name().fullName());
        data.put("gender", "Male");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "inactive");

        String bearerToken = "7af0bbafabd9d5065084d1d55062c816fca52be51f376938370b11ea12c75c7e";

                int id = given()
                        .header("Authorization", "Bearer " + bearerToken)
                        .contentType("application/json")
                        .body(data.toString())

                        .when()
                        .post("https://gorest.co.in/public/v2/users")
                        .jsonPath().getInt("id");

                System.out.println("Generated id is: " + id);

                //context.setAttribute("userid", id);
                context.getSuite().setAttribute("userid", id);      //to run testng2.xml file


    }

}
