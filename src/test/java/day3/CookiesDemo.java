package day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CookiesDemo {

    @Test(priority=1)
    void testCookies() {

        given()

                .when()
                .get("https://www.google.com/")

                .then()
                .cookie("AEC", "AdJVEatDDYcrVb4mpwZkquuC7dlIeCYU5rdGpijkpnvGwJvi_jY8gGB69w")
                .log().all();

    }

    @Test(priority=2)
    void getCookiesInfo(){

        Response res = given()

                .when()
                .get("https://www.google.com/");


        //====== get single cookie info =======
        /*
        String cookie_value = res.getCookie("AEC");               //THIS will get value of AEC cookie
        System.out.println("The value of cookie is =====> " + cookie_value);
         */



        //====== get all cookie info =======
        Map<String, String> cookies_values = res.getCookies();
        //System.out.println(cookies_values.keySet());              //keyset was used for getting different key

        for(String key : cookies_values.keySet()){

            String cookie_value = res.getCookie(key);
            System.out.println(key + " : " + cookie_value);

            //OR below single line instead of above two
            //System.out.println(key + ": " + cookies_values.get(key));
        }



        //      [__Secure-STRP, AEC, NID, __Secure-BUCKET]
    }

}
