package com.automation.steps;

import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class ResponseSteps {

    @Then("verify status code is {int}")
    public void verify_status_code_is(int code) {
        Assert.assertEquals(code, RestAssuredUtils.verifyStatusCode());
    }
}
