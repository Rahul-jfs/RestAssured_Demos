package com.automation.steps;

import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class ResponseStep {

    @Then("verify status code is {int}")
    public void verify_status_code_is(int statusCode) {
        Assert.assertEquals(statusCode, RestAssuredUtils.getResponse().statusCode());
    }

}
