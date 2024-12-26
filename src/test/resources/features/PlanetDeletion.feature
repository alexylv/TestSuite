@US5 @SR2
Feature: Planet Deletion
    As a user I want to remove planets from the Planetarium so I can correct my findings

    Background:
        Given the user is on the login page
        When  the user enters a valid username
        And   the user enters a valid password
        And   the user submits the login form
        Then  the user should be directed to the home page
        When  user selects "planet" from the dropdown

    @MR5
    Scenario: User deletes a planet successfully
        When   user inputs valid planet deletion data
        Then   table with planet info refreshes
        And    updated table no longer contains the deleted planet

    Scenario: User tries to delete a planet with an invalid name
        When   user inputs invalid planet name to delete
        Then   browser alert message "Invalid planet name" appears
        And    table with planet info remains the same
