package day5;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class FileUploadAndDownload {

    //@Test(priority=1)
    void singleFileUpload() {

        File myFile = new File("C:\\AutomationPractice\\Test1.txt");
        given()
                .multiPart("file", myFile)
                .contentType("multipart/form-data")

                .when()
                .post("https://localhost:8080/uploadFile")

                .then()
                .statusCode(200)
                .body("fileName", equalTo("Test1.txt"))
                .log().all();
    }

    @Test(priority=1)
    void multipleFilesUpload() {

        File myFile1 = new File("C:\\AutomationPractice\\Test1.txt");
        File myFile2 = new File("C:\\AutomationPractice\\Test2.txt");

        given()
                .multiPart("files", myFile1)
                .multiPart("files", myFile2)
                .contentType("multipart/form-data")

                .when()
                .post("https://localhost:8080/uploadMultipleFile")

                .then()
                .statusCode(200)
                .body("[0].fileName", equalTo("Test1.txt"))
                .body("[1].fileName", equalTo("Test2.txt"))
                .log().all();
    }

    @Test(priority=2)
    void fileDownload() {

        given()

                .when()
                .get("https://localhost:8080/downloadFile/Test1.txt")

                .then()
                .statusCode(200)
                .log().all();

    }
}
