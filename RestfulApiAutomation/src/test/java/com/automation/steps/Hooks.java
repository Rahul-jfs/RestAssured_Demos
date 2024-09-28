package com.automation.steps;

import com.automation.utils.ConfigReader;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {

    @Before
    public void setUp(){
        ConfigReader.initConfigReader();
        if(ConfigReader.getConfigValue("application.name").equals("restful-api")){
            RestAssured.baseURI =ConfigReader.getConfigValue("base.uri2");
        } else{
            RestAssured.baseURI =ConfigReader.getConfigValue("base.uri1");
        }

    }



}
