package com.automation.steps;

import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RequestStep {


    @Given("user calls {string} endpoint")
    public void user_calls_endpoint(String endpoint) {
        RestAssuredUtils.setEndpoint(endpoint);
    }

    @Given("user sets body from the file {string}")
    public void user_sets_body_from_the_file(String fileName) {
        RestAssuredUtils.setBody(fileName);
    }

    @Given("set the header content {string} to {string}")
    public void set_the_header_content_to(String key, String value) {
        RestAssuredUtils.setHeader(key, value);
    }

    @When("user performs post call")
    public void user_performs_post_call() {
        RestAssuredUtils.post();
    }


}
