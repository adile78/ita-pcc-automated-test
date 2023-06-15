#receipt=quietanza
@ignore @PolicyRenewal
Feature: Policy Renewal

  Scenario Outline: User has a motor policy that can be online renewal
    Given user is logged in <username> and <password>
    And user has a motor policy that can be online renewal
    Then user sees a banner with all the informations to renew it

    Examples: 
      | username      | password      |
      | "polren.user" | "polren.pswd" |

  Scenario Outline: User sees the receipt information
    Given user is logged in <username> and <password>
    And user has a motor policy that can be online renewal
    When user accesses receipt details by the policy box
    Then user sees receipt information

    Examples: 
      | username      | password      |
      | "polren.user" | "polren.pswd" |

  Scenario Outline: User successfully pays the policy title and downloads the receipt
    Given user is logged in <username> and <password>
    And user has a motor policy that can be online renewal
    And user wants to renewal the policy
    When user inserts all the requiered payment fields <card.number> and <owner.number> and <month.number> and <expired.year> and <cvv> and <email> on Bassilichi page and validates them
    Then user lands on the thank you page
    And user downloads the receipt

    Examples: 
      | username      | password      | card.number              | owner.number              | month.number              | expired.year              | cvv              | email              |
      | "polren.user" | "polren.pswd" | "bassilichi.card.number" | "bassilichi.owner.number" | "bassilichi.month.number" | "bassilichi.expired.year" | "bassilichi.cvv" | "bassilichi.email" |

  Scenario Outline: User cannot pay the policy title because of generic error from Bassilichi page
    Given user is logged in <username> and <password>
    And user has a motor policy that can be online renewal
    When user wants to renewal the policy
    Then a generic renewal policy error message appears
    And user returns to homepage from renewal policy detail page
    And a waiting box appears on the policy

    Examples: 
      | username      | password      |
      | "polren.user" | "polren.pswd" |

  Scenario Outline: User cannot pay the policy title because of waiting time after max payment attempts
    Given user is logged in <username> and <password>
    And user has a motor policy that can be online renewal
    When user wants to renewal the policy for the third time
    Then a max attemps renewal policy error message appears
    And user returns to homepage from renewal policy detail page
    And a waiting box appears on the policy

    Examples: 
      | username      | password      |
      | "polren.user" | "polren.pswd" |
