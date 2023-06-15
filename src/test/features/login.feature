@Login
Feature: Log into AXA private area

  @PCC_01.20
  Scenario Outline: User successfully logs into PCC Area Clienti
    Given user is on PCC login page
    When user enters all required login fields <username> and <password>
    Then user is logged in

    Examples: 
      | username     | password     |
      | "login.user" | "login.pswd" |

  @PCC_01.20
  Scenario Outline: User does not login to PCC Area Clienti because he enters wrong data
    Given user is on PCC login page
    When user enters wrong required login fields <username> and <password>
    Then a <wrongData> error message appears

    Examples: 
      | username           | password     | wrongData  |
      | "login.user"       | "pswd.wrong" | "password" |
      | "login.user.wrong" | "login.pswd" | "username" |
