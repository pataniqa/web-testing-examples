Feature: Bing search

  Scenario: Query Bing
    Given I am on the Bing home page
    When I search for "rest testing"
    Then the title should contain: "rest testing"


