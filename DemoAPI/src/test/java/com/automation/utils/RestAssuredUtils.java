package com.automation.utils;

import com.automation.pojo.CreateBookingPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestAssuredUtils {

    private static final Log log = LogFactory.getLog(RestAssuredUtils.class);
    static RequestSpecification requestSpecification= RestAssured.given();

    static Response response;
    static String endpoint;

    public static void setEndpoint(String endpoint){
        RestAssuredUtils.endpoint = endpoint;
    }

    public static void setBody(String fileName){
        String content = getBodyContent(fileName);
        ObjectMapper mapper = new ObjectMapper();

        try {
            requestSpecification.body(mapper.readValue(content, CreateBookingPojo.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getBodyContent(String fileName){

        String str="";
        try {
          str =  new Scanner(new FileInputStream(ConfigReader.getConfigValue("json.folder.path") + fileName)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
        return str;
    }

    public static void setHeader(String key, String value) {
        requestSpecification = requestSpecification.header(key, value);
    }


    public static void post() {
        requestSpecification.log().all();
        response = requestSpecification.post(endpoint);
        System.out.println("After post");
        response.then().log().all();
    }

    public static Response getResponse(){
        return response;
    }
}
