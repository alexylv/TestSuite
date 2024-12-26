@US4 @PR5 @PR6
Feature: Planet Creation
    As a user I want to add new planets to the Planetarium so I can update my findings

    Background:
        Given the user is on the login page
        When  the user enters a valid username
        And   the user enters a valid password
        And   the user submits the login form
        Then  the user should be directed to the home page
        When  user selects "planet" from the dropdown

    Scenario: User adds a new planet successfully
        And     user inputs valid planet creation data
        Then    table refreshes
        And     updated table contains new planet info in addition to old stuff

    @PR1 @PR2 @PR3
    Scenario Outline: User attempts to add a new planet with invalid data
        When    user inputs planet name "<name>"
        And     file type "<type>"
        And     submits the planet info
        Then    browser alert message "<alert>" should be displayed
        And     table should remain unchanged

    Examples:
    |name                           |type     |alert              |
    |Xenothron Nebula_Venture-999999|JPEG	  |Invalid planet name|
	|                               |JPEG	  |Invalid planet name|
    |Aurelia???		                |JPEG	  |Invalid planet name|
    |Earth	                        |JPEG     |Invalid planet name|
    |Nova_Terra 6-2		            |GIF	  |Invalid file type  |