package com.automation.steps;

import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class RequestSteps {


    @Given("user calls endpoint {string}")
    public void user_calls_endpoint(String endPoint) {
        if (endPoint.contains("@")) {
            String id = null;
            if(ConfigReader.getConfigValue("application.name").equals("restful-api")){
                id = ConfigReader.getConfigValue("object.id");
            } else {
                id = ConfigReader.getConfigValue("resource.id");
            }
            endPoint = endPoint.replace("@id", id);
        }
        RestAssuredUtils.setEndPoint(endPoint);
    }

    @Given("set header {string} as {string}")
    public void set_header_as(String key, String value) {
        RestAssuredUtils.setHeader(key, value);
    }

    @When("user makes get call")
    public void user_makes_get_call() {
        RestAssuredUtils.get();
    }

    @When("user makes post call")
    public void userMakesPostCall() {
        RestAssuredUtils.post();
    }

    @And("set the body from the file {string}")
    public void setTheBodyFromTheFile(String fileName) {
        RestAssuredUtils.setBody(fileName);
    }

    @And("store the id value to {string}")
    public void storeTheIdValueTo(String key) {
        String id = RestAssuredUtils.getResponse().jsonPath().getString("id");
        ConfigReader.setConfigValue(key, id);
    }

    @And("verify list is not empty")
    public void verifyListIsNotEmpty() {
        Assert.assertTrue(RestAssuredUtils.getResponse().jsonPath().getList("id").size() > 1);
    }
}
