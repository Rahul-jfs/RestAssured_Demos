Feature: validate json.placeholder resources

  Scenario: verify user gets list of resources
    Given user calls endpoint "/posts"
    When user makes get call
    Then verify status code is 200
    And verify list is not empty

  Scenario: verify user can create a resource and cannot access the posted data
    Given user calls endpoint "/posts"
    And set header "Content-Type" as "application/json"
    And set the body from the file "/create_resource.json"
    When user makes post call
    Then verify status code is 201
    And store the id value to "resource.id"
    When user calls endpoint "/posts/@id"
    And user makes get call
    Then verify status code is 404