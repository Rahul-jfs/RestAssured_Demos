package com.automation.demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestDemo {
    public static void main(String[] args) throws FileNotFoundException {

//        RestAssured.given().baseUri("https://restful-booker.herokuapp.com")
//                .log().all().when().get("/booking")
//                .then().log().all().assertThat().statusCode(200);6

        String body = getDataFromFile("src/test/resources/json/create_booking.json");

        RequestSpecification requestSpecs = RestAssured.given();

        requestSpecs= requestSpecs.baseUri("https://restful-booker.herokuapp.com");

        requestSpecs = requestSpecs.body(body);

        requestSpecs.contentType(ContentType.JSON);

        Response response = requestSpecs.post("/booking");

        System.out.println("Status code: "+ response.getStatusCode());
        System.out.println("Response: \n"+ response.prettyPrint());

//        RestAssured.given().baseUri("https://restful-booker.herokuapp.com")
////                .contentType(ContentType.JSON)
//                .body(body)
//                .contentType(ContentType.JSON)
//                .when().post("/booking")
//                .then().log().all()
//                .assertThat().statusCode(200);
    }

    public static String getDataFromFile(String filePath) throws FileNotFoundException {
        String content = new Scanner(new FileInputStream(filePath)).useDelimiter("\\Z").next();
        return content;
    }
}
