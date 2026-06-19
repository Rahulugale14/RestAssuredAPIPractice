package day2;

/*\
    Different ways to create POST request body
    1. POST Request body using HashMap
    2. POST request body using Org.JSON
    3. POST request body using POJO Class
    4. POST Request body using external json file data
 */

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class DiffWaysToCreatePostRequestBody {

    //===============   1. POST Request body using HashMap     =====================
    //@Test(priority=1)
    void testPostUsingHashMap() {

        HashMap data = new HashMap();
        data.put("name", "Rahul");
        data.put("location", "Pune");
        data.put("phone", "797291528");
        String courseArr[] = {"C", "C++"};      //creating this java array to add it in below method
        data.put("courses", courseArr);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Rahul"))
                .body("location", equalTo("Pune"))
                .body("phone", equalTo("797291528"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();

    }


    //===============   2. POST request body using Org.JSON     =====================
    //@Test(priority=1)
    void testPostUsingJsonLibrary() {

        JSONObject data = new JSONObject();     //preparing data using JSON Object
        data.put("name", "Rahul");
        data.put("location", "Pune");
        data.put("phone", "797291528");
        String coursesArr[] = {"C", "C++"};
        data.put("courses", coursesArr);

        given()
                .contentType("application/json")
                .body(data.toString())              //here data is in string format & later will be converted in json format

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Rahul"))
                .body("location", equalTo("Pune"))
                .body("phone", equalTo("797291528"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();

    }


    //===============   3. POST request body using POJO Class     =====================
    //@Test(priority=1)
    void testPostUsingPOJOClass() {

        Pojo_PostRequest data = new Pojo_PostRequest();
        data.setName("Rahul");
        data.setLocation("Pune");
        data.setPhone("797291528");
        data.setCourses(new String[]{"C", "C++"});          //this array can be written as shown in above methods as well

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Rahul"))
                .body("location", equalTo("Pune"))
                .body("phone", equalTo("797291528"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();

    }


    //===============   4. POST request body using external json file data     =====================
    @Test(priority=1)
    void testPostUsingUsingExtJsonFile() throws FileNotFoundException {

        File f = new File(".\\body.json");           //this path is current project location
        FileReader fr = new FileReader(f);                    //reading data here from file
        JSONTokener jt = new JSONTokener(fr);                 // passing fr here to get data in json format
        JSONObject data = new JSONObject(jt);      // extracting data here

        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Rahul"))
                .body("location", equalTo("Pune"))
                .body("phone", equalTo("797291528"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();

    }

    //deleting student record
    @Test(priority=2)
    void testDelete() {

        given()

                .when()
                .delete("http://localhost:3000/students/104")

                .then()
                .statusCode(200);




    }


}
