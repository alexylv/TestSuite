@US4 @MR6 @MR7
Feature: Moon Creation
    As a user I want to add new moons to the Planetarium so I can update my findings

    Background:
        Given the user is on the login page
        When  the user enters a valid username
        And   the user enters a valid password
        And   the user submits the login form
        Then  the user should be directed to the home page
    
    @MR4 
    Scenario: User adds a new moon successfully
        When   user inputs valid moon creation data
        Then   table refreshes
        And    updated table contains new moon info in addition to old stuff

    @MR1 @MR2 @MR3 
    Scenario Outline: User attempts to add a new moon with invalid data
        When   user inputs moon name "<name>"
        And    owning planet "<planet id>"
        And    submits the moon info
        Then   browser alert message "<alert>" should be displayed
        And    table should stay the same

    Examples:
    |name	                        |planet id|alert            |
    |Xenathron Nebula_Venture-000000|	1	  |Invalid moon name|
    |		                        |   1	  |Invalid moon name|
    |Ganymede***                    |	1     |Invalid moon name|
    |Titan		                    | 	1     |Invalid moon name|
    |Nyx_Orion 6-7                  |	11    |Invalid planet id|