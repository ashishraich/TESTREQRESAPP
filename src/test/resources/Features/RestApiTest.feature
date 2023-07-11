Feature: API Testing

  Scenario: GET Employee Service API Test
    Given I Set GET employee service api endpoint
    And I Set request HEADER
    When Send a GET HTTP request
    Then I receive valid Response

  Scenario: POST Employee Service API Test
    Given I Set POST employee service api endpoint
    When I Set POST request HEADER
    And Send a POST HTTP request
    Then I receive POST valid Response
