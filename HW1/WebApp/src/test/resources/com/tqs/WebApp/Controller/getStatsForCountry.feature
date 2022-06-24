Feature: Get covid status for a country

  Scenario: Get status
    When I navigate to "http://localhost:8080/"
    And I choose the country "Portugal"
    And I click Submit
    Then I should be see in the page body the pattern "Updated:  New Cases:  New Deaths:  Total Cases:  Total Deaths: "