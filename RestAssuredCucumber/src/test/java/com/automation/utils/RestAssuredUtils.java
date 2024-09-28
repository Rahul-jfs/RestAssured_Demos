package com.automation.utils;

import com.automation.pojo.CreateBookingPojo;
import com.automation.pojo.TokenBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestAssuredUtils {

    static RequestSpecification requestSpecification = RestAssured.given();
    static String endPoint;
    static Response response;

    public static void setEndPoint(String endPoint) {
        RestAssuredUtils.endPoint = endPoint;
    }

    public static void setHeader(String key, String value) {
        requestSpecification = requestSpecification.header(key, value);
    }

    public static Response post() {
        requestSpecification.log().all(); // Just for logging purpose
        response = requestSpecification.post(endPoint);
        response.then().log().all(); // Just for logging purpose
        return response;
    }

    public static Response get() {
        requestSpecification.log().all(); // Just for logging purpose
        response = requestSpecification.get(endPoint);
        response.then().log().all(); // Just for logging purpose
        return response;
    }

    public static Response put() {
        requestSpecification.log().all(); // Just for logging purpose
        response = requestSpecification.put(endPoint);
        response.then().log().all(); // Just for logging purpose
        return response;
    }


    public static void setBody(String fileName) {
        String content = getDataFromFile(ConfigReader.getConfigValue("json.folder.path") + fileName);
        ObjectMapper mapper = new ObjectMapper();
        try {
            requestSpecification = requestSpecification.body(mapper.readValue(content, CreateBookingPojo.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setTokenBody(String fileName) {
        String content = getDataFromFile(ConfigReader.getConfigValue("json.folder.path") + fileName);
        ObjectMapper mapper = new ObjectMapper();
        try {
            requestSpecification = requestSpecification.body(mapper.readValue(content, TokenBody.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public static String getDataFromFile(String filePath) {
        String content = null;
        try {
            content = new Scanner(new FileInputStream(filePath)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return content;
    }


    public static int getStatusCode() {
        return response.getStatusCode();
    }

    public static Response getResponse(){
        return response;
    }

    public static void setBodyUsingPojo(Object object) {
        requestSpecification = requestSpecification.body(object);
    }

    public static void setBodyOfToken(String fileName) {
        String content = getDataFromFile(fileName);
        ObjectMapper mapper = new ObjectMapper();
        try {
            requestSpecification = requestSpecification.body(mapper.readValue(content, TokenBody.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
