Feature: UI Testing

  Background: 
    Given User Navigates to Homepage

  Scenario: Testing GET LIST USERS button
    When User click GET LIST USERS button
    Then User should see sample1 request and response

  Scenario: Testing GET SINGLE USERS button
    When User click GET SINGLE USERS button
    Then User should see sample2 request and response

  Scenario: Testing GET SINGLE USER NOT FOUND button
    When User click GET SINGLE USER NOT FOUND button
    Then User should see sample3 request and response

  Scenario: Testing GET LIST RESOURCE button
    When User click GET LIST RESOURCE button
    Then User should see sample4 request and response

  Scenario: Testing GET SINGLE RESOURCE button
    When User click GET SINGLE RESOURCE button
    Then User should see sample5 request and response

  Scenario: Testing GET SINGLE RESOURCE NOT FOUND button
    When User click GET SINGLE RESOURCE NOT FOUND button
    Then User should see sample6 request and response

  Scenario: Testing POST CREATE button
    When User click POST CREATE button
    Then User should see sample7 request and response

  Scenario: Testing PUT UPDATE button
    When User click PUT UPDATE button
    Then User should see sample8 request and response

  Scenario: Testing Support button
    When User click Support button
    Then User should see Support page

  Scenario: Navigating to Support page
    Given User Navigates to Support page
    Then User should see Support page

  Scenario: Testing Onetime payment button
    When User click Onetime payment button
    Then User should see Support ReqRes button

  Scenario: Testing Monthly payment button
    When User click on Monthly payment button
    Then User should see Support ReqRes button
