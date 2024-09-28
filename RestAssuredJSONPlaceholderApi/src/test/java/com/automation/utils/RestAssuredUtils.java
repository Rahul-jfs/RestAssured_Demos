package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestAssuredUtils {

    static RequestSpecification requestSpecification = RestAssured.given();

    static Response response;
    static String endPoint;

    public static void setEndPoint(String endPoint) {
        RestAssuredUtils.endPoint = endPoint;
    }

    public static void setHeader(String key, String value) {
        requestSpecification=requestSpecification.header(key, value);
    }

    public static Response get() {
        requestSpecification.log().all();
        response = requestSpecification.get(endPoint);
        response.then().log().all();
        return response;
    }

    public static Response post() {
        requestSpecification.log().all();
        response = requestSpecification.post(endPoint);
        response.then().log().all();
        return response;
    }

    public static Response getResponse(){
        return response;
    }

    public static void setBody(String fileName) {
        String jsonFolderPath = ConfigReader.getConfigValue("json.folder.path");
        requestSpecification= requestSpecification.body(getBodyFromFile(jsonFolderPath + fileName));
    }

    public static String getBodyFromFile(String filePath) {
        try {
            return new Scanner(new FileInputStream(filePath)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static int verifyStatusCode() {
        return response.getStatusCode();
    }
}
