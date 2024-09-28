package com.automation.demo;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestDemo {
    public static void main(String[] args) throws FileNotFoundException {

//        RestAssured.given().baseUri("https://restful-booker.herokuapp.com")
//                .log().all().when().get("/booking")
//                .then().log().all().assertThat().statusCode(200);6

        RestAssured.config = RestAssuredConfig.newConfig()
                .sslConfig(new SSLConfig().relaxedHTTPSValidation());


        String body = getDataFromFile("src/test/resources/json/create_booking.json");

//        RestAssured.given().baseUri("https://restful-booker.herokuapp.com")
//                .contentType(ContentType.JSON)
//                .body(body)
////                .contentType(ContentType.JSON)
//                .when().post("/booking")
//                .then().log().all()
//                .assertThat().statusCode(200);

        RestAssured.given().baseUri("https://restful-booker.herokuapp.com")
                .when().get("/booking/1")
                .then().log().all().assertThat().statusCode(200).log();
    }

    public static String getDataFromFile(String filePath) throws FileNotFoundException {
        String content = new Scanner(new FileInputStream(filePath)).useDelimiter("\\Z").next();
        return content;
    }
}
