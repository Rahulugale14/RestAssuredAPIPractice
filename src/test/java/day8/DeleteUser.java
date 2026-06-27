package day8;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class DeleteUser {

    @Test
    public void testDeleteUser(ITestContext context) {

        String bearerToken = "7af0bbafabd9d5065084d1d55062c816fca52be51f376938370b11ea12c75c7e";
       //int id = (Integer) context.getAttribute("userid");      //this should come from create user request/method
        int id = (Integer) context.getSuite().getAttribute("userid");      //to run testng2.xml file


        given()
                .header("Authorization", "Bearer " + bearerToken)
                .pathParam("id", id)

                .when()
                .delete("https://gorest.co.in/public/v2/users/{id}")

                .then()
                .statusCode(204)
                .log().all();


    }
}
