@US2 @SR1 @SR3
Feature: Login
    As a user I want to securely access my account so I can interact with the Planetarium in a secure environment

    Background: Login Starting Actions
        Given the user is on the login page
        And   the user has an account

    Scenario: User logs in with valid credentials
        When  the user enters a valid username
        And   the user enters a valid password
        And   the user submits the login form
        Then  the user should be directed to the home page

    Scenario Outline: User attempts to log in with invalid credentials
        When  the user enters username "<username>"
        And   the user enters password "<password>"
        And   the user submits the login form
        Then  the user should see "Invalid credentials"
        And   the user should remain on the login page 

        Examples:
        |username|password       |expected_result    |
        |Batman  |b0Ts           |Invalid credentials|
        |Robin   |Iamthenight1939|Invalid credentials|
        |Robin   |b0Ts           |Invalid credentials|

    Scenario: Logged in user logs out and returns to login page
        Given the user is on the home page
        When  the user clicks "Logout"
        Then  the user is taken from home page to login

