@RetrieveUsername
Feature: Username Retrievement

  Scenario Outline: User succesfully retrieves his username as a person
    Given user wants to retrieve his username
    When user inserts his person data <fiscalCode> and <policyNumber>
    And user successfully validates them accessing the next username retrievement step
    Then user successfully complete the username retrieving process as a person selecting his <username>

    Examples: 
      | fiscalCode    | policyNumber        | username              |
      | "retr.usr.fc" | "retr.usr.p.policy" | "retr.usr.p.username" |

  Scenario Outline: User can not retrieve his username as a person because his data are not valid
    Given user wants to retrieve his username
    When user inserts his person data <fiscalCode> and <policyNumber> and validates them
    Then a data not valid message appears

    Examples: 
      | fiscalCode    | policyNumber              |
      | "retr.usr.fc" | "retr.usr.p.wrong.policy" |

  Scenario Outline: User succesfully retrieves his username as organization
    Given user wants to retrieve his username
    When user inserts his organization data <vatNumber> and <policyNumber>
    And user successfully validates them accessing the next username retrievement step
    Then user successfully complete the username retrieving process as organization entering the new <username>

    Examples: 
      | vatNumber             | policyNumber          | username                |
      | "retr.usr.vat.number" | "retr.usr.org.policy" | "retr.usr.org.username" |

  Scenario Outline: User can not retrieve his username as organization because his data are not valid
    Given user wants to retrieve his username
    When user inserts his organization data <vatNumber> and <policyNumber> and validates them
    Then a data not valid message appears

    Examples: 
      | vatNumber             | policyNumber                |
      | "retr.usr.vat.number" | "retr.usr.org.wrong.policy" |
