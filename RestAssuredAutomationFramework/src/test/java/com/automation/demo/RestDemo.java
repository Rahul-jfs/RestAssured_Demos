package com.automation.demo;

import io.restassured.RestAssured;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestDemo {
    public static void main(String[] args) throws FileNotFoundException {
//        RestAssured.given().baseUri("https://restful-booker.herokuapp.com")
//                .log().all().when().get("/booking")
//                .then().log().all().assertThat().statusCode(200);

        String body = getDataFromFile("src/test/resources/json/create_booking.json");

        String id = RestAssured.given().baseUri("https://restful-booker.herokuapp.com")
                .body(body)
                .contentType("application/json")
                .log().all().when().post("/booking").jsonPath().getString("bookingid");
        System.out.println(id);
    }

    public static String getDataFromFile(String filePath) throws FileNotFoundException {
        String content = new Scanner(new FileInputStream(filePath)).useDelimiter("\\Z").next();
        return content;
    }
}
