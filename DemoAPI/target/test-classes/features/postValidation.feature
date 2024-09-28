Feature: verify user can post and access the same object
  Scenario: verify user can post
    Given user calls "booking" endpoint
    And user sets body from the file "create_booking.json"
    And set the header content "Content-Type" to "application/json"
    When user performs post call
    Then verify status code is 200

