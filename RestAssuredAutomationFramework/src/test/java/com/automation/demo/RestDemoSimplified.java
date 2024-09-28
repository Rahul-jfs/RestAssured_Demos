package com.automation.demo;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestDemoSimplified {
    public static void main(String[] args) throws FileNotFoundException {
        String body = getDataFromFile("src/test/resources/json/create_booking.json");

        RequestSpecification requestSpecs = RestAssured.given();

        requestSpecs = requestSpecs.baseUri("https://restful-booker.herokuapp.com");

        requestSpecs = requestSpecs.body(body);

        requestSpecs = requestSpecs.contentType("application/json");

        Response response = requestSpecs.post("/booking");

        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());

    }

    public static String getDataFromFile(String filePath) throws FileNotFoundException {
        String content = new Scanner(new FileInputStream(filePath)).useDelimiter("\\Z").next();
        return content;
    }

}

