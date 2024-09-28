Feature: Validate get booking end point
  Scenario: verify getting list of booking
    Given user wants to call "/booking" end point
    And user performs get call
    Then verify status code is 200