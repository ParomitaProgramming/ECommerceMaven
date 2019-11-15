@tag
Feature: Login Validation of Demo Page

  Scenario Outline: Demo Page title validation
    Given I am in demo login page
    When I enter "<username>" for login 
    Then I verify the "<title>" after login
    And  Logout from demo site

    Examples: 
      | username      | title  										|
      | abc@gmail.com | Successfully Logged in... |
      | xyz@gmail.com | ABC    										|