Feature: Search car

  Scenario: Search car by name
    Given User is logged in
    And User goes to the car listing page
    When User searches for cars with names
      | CarName  |
      | Toyota   |
      | Honda    |
      | Ford     |
    Then The results should be visible

  Scenario: Search car by year
    Given User is logged in
    And User goes to the car listing page
    And User click on the filter button
    And User selects a year range from <from> to <to>
      | from | to   |
      | 2000 | 2022 |
    When User press the search button
    Then The results should be visible

  Scenario: Search car by mileage
    Given User is logged in
    And User goes to the car listing page
    And User click on the filter button
    And User selects a mileage range from <from> to <to>
      | from   | to      |
      | 50.000 | 150.000 |
    When User press the search button
    Then The results should be visible
