package com.automation.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ResponseObjectPojo {

    String id;
    String name;
    DataPojo data;
    String createdAt;

}
