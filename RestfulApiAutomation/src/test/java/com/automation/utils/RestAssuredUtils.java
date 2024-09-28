package com.automation.utils;

import com.automation.pojo.CreateResourcePojo;
import com.automation.pojo.ResponseObjectPojo;
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

    static Response response;
    static String endPoint;
    static ResponseObjectPojo[] responseObjectPojo = new ResponseObjectPojo[2];

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
        String content = getDataFromFile(fileName);
        ObjectMapper mapper = new ObjectMapper();
        try {
            requestSpecification = requestSpecification.body(mapper.readValue(content, CreateResourcePojo.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getDataFromFile(String fileName) {
        try {
            return new Scanner(new FileInputStream(ConfigReader.getConfigValue("json.folder.path") + fileName)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static int verifyStatusCode() {
        return response.getStatusCode();
    }

    static int i=0;
    public static void setResponseObject() {
        String content = response.asString();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            responseObjectPojo[i++] = objectMapper.readValue(content, ResponseObjectPojo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResponseObjectPojo getCreatedObject(){
        responseObjectPojo[0].setCreatedAt(null);
        System.out.println(responseObjectPojo[0]);
        return responseObjectPojo[0];
    }

    public static ResponseObjectPojo getResponseObject(){
        System.out.println(responseObjectPojo[1]);
        return responseObjectPojo[1];
    }
}
