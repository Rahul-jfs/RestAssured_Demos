package com.automation.steps;

import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.Assert;

public class ResponseSteps {

    @Then("verify status code is {int}")
    public void verify_status_code_is(int code) {
        Assert.assertEquals(code, RestAssuredUtils.verifyStatusCode());
    }

    @And("store the response to object")
    public void storeTheResponseToObject() {
        RestAssuredUtils.setResponseObject();
    }

    @Then("verify object is same as created object")
    public void verifyObjectIsSameAsCreatedObject() {
        Assert.assertEquals(RestAssuredUtils.getCreatedObject(), RestAssuredUtils.getResponseObject());
    }

    @And("verify the response is in the json format {string}")
    public void verifyTheResponseIsInTheJsonFormat(String fileName) {
        RestAssuredUtils.getResponse().then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/create_object_schema.json"));
    }
}
