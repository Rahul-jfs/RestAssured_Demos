Feature: Token generation validation

  Scenario Outline: Verify token is not generated when invalid credentials entered
    Given user wants to call "/auth" end point
    And set header "Content-Type" to "application/json"
    And set the body from the file "/create_token.json" with "<username>" and password "<password>"
    When user performs post call
    Then verify status code is 200
    And verify user gets response as "Bad credentials"
    Examples:
      | username | password |
      | abc      | abs123   |
      | ADMIN    | 123213   |
      | qqq      | 1211     |
      | adMIN    | 1111     |
