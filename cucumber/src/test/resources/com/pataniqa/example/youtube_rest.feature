Feature: Youtube rest api gets

  Scenario: Get title metadata by VideoId
    Given I query video by "6acRHWnfZAE"
    When I make the rest call
    Then the title should be: "X-Men: Days of Future Past | Official Trailer 2 [HD] | 20th Century FOX"

  Scenario: Get category metadata by VideoId
    Given I query video by "6acRHWnfZAE"
    When I make the rest call
    Then the category should be: "Film & Animation"

