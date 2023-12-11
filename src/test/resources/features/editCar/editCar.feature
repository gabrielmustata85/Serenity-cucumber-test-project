Feature: Edit car details

  Scenario: Edit car name
    Given User is logged in
    And User navigates to profiles
    And User clicks on My Cars button
    And Selects the car he want to edit
    When User clicks on edit button
    Then The car details page is open
    And User edits the car name
    When User clicks on Save button
    Then The car name should be saved.

