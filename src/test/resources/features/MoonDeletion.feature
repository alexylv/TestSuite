@US5 @SR2
Feature: Moon Deletion
    As a user I want to remove moons from the Planetarium so I can correct my findings

    Background:
        Given user is on home page

    Scenario: User deletes a moon successfully
        When  user inputs valid moon deletion data
        Then  table with moon info refreshes 
        And   updated table no longer contains the deleted moon

    Scenario: User tries to delete a moon with an invalid name
        When  user inputs invalid moon name to delete
        Then  browser alert message "Invalid moon name" appears
        And   table with moon info remains the same
