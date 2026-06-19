package day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParsingJSONResponseData {

    @Test(priority=1)
    void testJSONResponse() {

        // ====================================== APPROACH 1 ============================================
        /*
        given()
                .contentType(ContentType.JSON)

                .when()
                .get("http://localhost:3000/store")

                .then()
                .statusCode(200)
                .header("content-type", equalTo("application/json; charset=UTF-8"))
                .body("book[3].title", equalTo("The Lord of the Rings"));
         */


        // ====================================== APPROACH 2 ==============================================
            //using this 2nd approach below two or more types of validations can be performed

        Response res = given()
                .contentType(ContentType.JSON)

                .when()
                .get("https://localhost:3000/store");

        Assert.assertEquals(res.getStatusCode(), "200");              //using TestNG assertions for validations
        Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");

        String bookName = res.jsonPath().get("book[3].title").toString();//here get method returns data in Object form, so added toString method to get in string format
        Assert.assertEquals(bookName, "The Lord of the Rings");

        //JSONObject class
        JSONObject jo = new JSONObject(res.toString());             //converting Response to JSONObject type

        //print all titles of books
        /* for(int i=0; i<jo.getJSONArray("book").length(); i++) {
            String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
            System.out.println(bookTitle); */

        //VALIDATION 1-> Search for a book title
        boolean status = false;
        for(int i=0; i<jo.getJSONArray("book").length(); i++) {
            String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
            if(bookTitle.equals("The Lord of the Rings")){
                status = true;
                break;
            }
        }
        Assert.assertEquals(status, true);

        //VALIDATION 2 -> total prices of all books
        double totalPrice = 0;
        for(int i=0; i<jo.getJSONArray("book").length(); i++) {
            String price = jo.getJSONArray("books").getJSONObject(i).get("price").toString();
            totalPrice = totalPrice + Double.parseDouble(price);

        }
        System.out.println("Total price of books: " + totalPrice);
        Assert.assertEquals(totalPrice, 53.92);


    }


}
