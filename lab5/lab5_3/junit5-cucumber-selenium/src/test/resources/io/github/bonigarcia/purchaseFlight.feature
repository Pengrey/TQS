Feature: Purchase flight in blazedemo site

  Scenario: Purchase flight
    When I navigate to "https://blazedemo.com/"
    And I choose departure city "Paris" and password "Buenos Aires"
    And I clickFind Flights
    And I choose the flight number "43"
    And I choose the name "Tricia Dire"
    And I choose the Address "751 Yeager St"
    And I choose the City "Vallejo"
    And I choose the State "Wyoming"
    And I choose the Zip Code "43912"
    And I choose the Card Type "visa"
    And I choose the Credit Card Number "4554963859117344"
    And I choose the Month "11"
    And I choose Year "2017"
    And I choose the Name on Card "joand askd"
    And I click Remember me
    And I click Purchase Flight
    Then I should be see the title "BlazeDemo Confirmation"