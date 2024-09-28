Feature: Validate create booking end point

  Scenario:  Verify user can create booking
    Given user wants to call "/booking" end point
    And set header "Content-Type" to "application/json"
    And set request body from the file "create_booking.json"
    When user performs post call
    Then verify status code is 200

