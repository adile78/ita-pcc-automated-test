@RetrievePassword
Feature: Password Retrievement

  Scenario Outline: User succesfully retrieves his password
    Given user wants to retrieve his password
    When user inserts his <username>
    And user successfully validates it accessing the next password retrievement step
    Then user successfully complete the password retrieving process entering the new <password>

    Examples: 
      | username     | password     |
      | "login.user" | "login.pswd" |

  Scenario Outline: User cannot retrieve his password because the username is not found
    Given user wants to retrieve his password
    When user inserts his <username> and validates it
    Then a not found username message appears

    Examples: 
      | username                   |
      | "retr.pswd.wrong.username" |
