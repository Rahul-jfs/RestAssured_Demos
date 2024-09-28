package com.automation.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateResourcePojo {

    @JsonProperty("name")
    private String name;

    @JsonProperty("data")
    private DataPojo data;

}
