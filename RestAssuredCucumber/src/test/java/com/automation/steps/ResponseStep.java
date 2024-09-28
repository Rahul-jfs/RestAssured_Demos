package com.automation.steps;

import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class ResponseStep {

    @Then("verify status code is {int}")
    public void verify_status_code_is(int statusCode) {
        Assert.assertEquals(statusCode, RestAssuredUtils.getStatusCode());
    }

    @And("stores created booking id into {string}")
    public void storesCreatedBookingIdInto(String key) {
        ConfigReader.setConfigValue(key, RestAssuredUtils.getResponse().jsonPath().getString("bookingid"));
    }

    @And("verify booking id is not empty")
    public void verifyBookingIdIsNotEmpty() {
        String bookingId = RestAssuredUtils.getResponse().jsonPath().getString("bookingid");
        Assert.assertTrue(!bookingId.isEmpty());
    }

    @And("store token value to {string}")
    public void storeTokenValueTo(String key) {
        String token = RestAssuredUtils.getResponse().jsonPath().getString("token");
        ConfigReader.setConfigValue(key, token);
    }

    @And("verify user gets response as {string}")
    public void verifyUserGetsResponseAs(String value) {
        Assert.assertEquals(RestAssuredUtils.getResponse().jsonPath().getString("reason"), value);
    }

}
