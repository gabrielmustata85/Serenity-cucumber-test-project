Feature: Add new car

  Scenario: Add new car
    Given User is logged in
    When User navigates to the add new car page
    Then The new care page is visible
    And User enters car details
      | make       | model      | year | color | engineSize |
      | Toyota     | Camry      | 2023 | Red   | 2.5L       |
    When User click on add new car button
    Then Success message will be visible