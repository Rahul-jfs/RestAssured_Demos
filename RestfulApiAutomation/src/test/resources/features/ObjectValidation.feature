Feature: Same object access validation

  Scenario: verify the same object is accessed as created object

    Given user calls endpoint "/objects"
    And set header "Content-Type" as "application/json"
    And set the body from the file "/Create_object.json"

    When user makes post call
    Then verify status code is 200
    And store the response to object
    And store the id value to "object.id"

    When user calls endpoint "/objects/@id"
    And user makes get call
    Then verify status code is 200
    And store the response to object
    Then verify object is same as created object
    And verify the response is in the json format "create_object_schema.json"