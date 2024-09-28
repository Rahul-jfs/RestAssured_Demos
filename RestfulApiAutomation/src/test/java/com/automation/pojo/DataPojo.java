package com.automation.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class DataPojo {

    @JsonProperty("year")
    int year;

    @JsonProperty("price")
    double price;

    @JsonProperty("CPU model")
    String cpuModel;

    @JsonProperty("Hard disk size")
    String hardDiskSize;
}
