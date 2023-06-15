@ignore @Contacts
Feature: Contacts section consultation

  Scenario Outline: Single company person user sees only his company link to the webform in Contatti page
    Given user is logged in <username> and <password>
    When user accesses his contacts section
    Then single company person user sees only his <company> link to the webform

    Examples: 
      | username               | password        | company                    |
      | "contacts.user.aai.p"  | "contacts.pswd" | "axa.aai"                  |
      | "contacts.user.amps.p" | "contacts.pswd" | "axa.amps.persone.fisiche" |

  Scenario Outline: Single company organization sees only his company link to the webform in Contatti page
    Given user is logged in <username> and <password>
    When user accesses his contacts section
    Then single company organization sees only his <company> link to the webform

    Examples: 
      | username                | password        | company                       |
      | "contacts.user.amps.lp" | "contacts.pswd" | "axa.amps.persone.giuridiche" |
      | "contacts.user.aai.lp"  | "contacts.pswd" | "axa.aai"                     |

  Scenario Outline: Multi company person user sees only his company links to the webforms in Contatti page
    Given user is logged in <username> and <password>
    When user accesses his contacts section
    Then multi company person user sees only his <companies> links to the webforms

    Examples: 
      | username                   | password        | companies                      |
      | "contacts.user.aai.amps.p" | "contacts.pswd" | "axa.aai.amps.persone.fisiche" |

  Scenario Outline: Multi company organization sees only his company links to the webforms in Contatti page
    Given user is logged in <username> and <password>
    When user accesses his contacts section
    Then multi company organization sees only his <companies> links to the webforms

    Examples: 
      | username                    | password        | companies                         |
      | "contacts.user.aai.amps.lp" | "contacts.pswd" | "axa.aai.amps.persone.giuridiche" |
