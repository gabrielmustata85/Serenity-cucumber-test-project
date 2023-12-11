Feature: Delete car

  Scenario: Delete car
    Given User is logged in
    And User navigates to profiles
    And User clicks on My Cars button
    And Selects the car he want to delete
    When User clicks on delete button
    Then A confirmation popup should be visible
    When User clicks on confirm delete button
    Then The car should be deleted.