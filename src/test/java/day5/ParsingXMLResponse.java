package day5;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParsingXMLResponse {

    //@Test
    void testXMLResponse() {

        //========================  APPROACH 1 =============================
        given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1")

                .then()
                .statusCode(200)
                .header("Content-Type", "application/xml; charset=utf-8")
                .body("TravelerinformationResponse.page", equalTo("1"))
                .body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Vijay Bharat Reddy"));


        //========================  APPROACH 2 =============================
        //returning response through variable


        Response res = given()

                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");

        Assert.assertEquals(res.getStatusCode(), "200");
        Assert.assertEquals(res.getHeader("Content-Type"), "application/xml; charset=utf-8");

        String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();
        Assert.assertEquals(pageNo, "1");

        String travelerName = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
        Assert.assertEquals(travelerName, "Vijay Bharat Reddy");

    }


    @Test
    void testXMLResponseBody() {

        //========================  APPROACH 2 =============================
        //this approach 2 is adding for this 2nd method just for some additional validations using XMLPath class as shown below

        Response res = given()

                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");

        XmlPath xmlobj = new XmlPath(res.asString());           //here asString is used for converting entire response into String

        //here below is to verify total number of travelers
        List<String> travelers = xmlobj.get("TravelerinformationResponse.travelers.Travelerinformation");
        Assert.assertEquals(travelers.size(), 10);

        //here below is to verify traveler name is present in the response
        List<String> travelerNames = xmlobj.get("TravelerinformationResponse.travelers.Travelerinformation[0].name");
        boolean status = false;
        for  (String travelerName : travelerNames) {
            if(travelerName.equals("Vijay Bharat Reddy")) {
                status = true;
                break;
            }
            Assert.assertEquals(status, true);
        }


    }


}
