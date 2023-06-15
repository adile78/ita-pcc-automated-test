# encoding: utf8
# language: en
@ignore @Registration
Feature: Registration to PCC Private Area

  @PCC_01.01
  Scenario Outline: User successfully registers to PCC private area as a person
    Given user wants to register to PCC private area as a person
    When user enters all required person registration fields <fiscalCode> <policyNumber> <username> <usernameConfirmation> and privacy permissions in the registration page
    And user successfully validates them accessing the next registration step
    Then user successfully complete the person registration entering the <password>

    Examples: 
      | fiscalCode             | policyNumber               | username                     | usernameConfirmation         | password   |
      | "reg.user.aai.p.fc001" | "reg.user.aai.p.policy001" | "reg.user.aai.p.username001" | "reg.user.aai.p.username001" | "reg.pswd" |
      | "reg.user.aai.p.fc002" | "reg.user.aai.p.policy002" | "reg.user.aai.p.username002" | "reg.user.aai.p.username002" | "reg.pswd" |
      | "reg.user.aai.p.fc003" | "reg.user.aai.p.policy003" | "reg.user.aai.p.username003" | "reg.user.aai.p.username003" | "reg.pswd" |
      | "reg.user.aai.p.fc004" | "reg.user.aai.p.policy004" | "reg.user.aai.p.username004" | "reg.user.aai.p.username004" | "reg.pswd" |
      | "reg.user.aai.p.fc005" | "reg.user.aai.p.policy005" | "reg.user.aai.p.username005" | "reg.user.aai.p.username005" | "reg.pswd" |
      | "reg.user.aai.p.fc006" | "reg.user.aai.p.policy006" | "reg.user.aai.p.username006" | "reg.user.aai.p.username006" | "reg.pswd" |
      | "reg.user.aai.p.fc007" | "reg.user.aai.p.policy007" | "reg.user.aai.p.username007" | "reg.user.aai.p.username007" | "reg.pswd" |
      | "reg.user.aai.p.fc008" | "reg.user.aai.p.policy008" | "reg.user.aai.p.username008" | "reg.user.aai.p.username008" | "reg.pswd" |

  @PCC_01.11
  Scenario Outline: User can not register to PCC as a person because user is already registered
    Given user has already registered to PCC private area as a person
    When user enters all required person registration fields <fiscalCode> <policyNumber> <username> <usernameConfirmation> and privacy permissions in the registration page
    And user validates registration fields
    Then an already registered warning message appears

    Examples: 
      | fiscalCode          | policyNumber            | username                  | usernameConfirmation      |
      | "reg.user.aai.p.fc" | "reg.user.aai.p.policy" | "reg.user.aai.p.username" | "reg.user.aai.p.username" |

  @PCC_01.12
  Scenario Outline: User can not register to PCC because user enters a not found fiscal code
    Given user wants to register to PCC private area as a person
    When user enters all required person registration fields <fiscalCode> <policyNumber> <username> <usernameConfirmation> and privacy permissions in the registration page
    And user validates registration fields
    Then a fiscal code not found warning message appears

    Examples: 
      | fiscalCode     | policyNumber       | username             | usernameConfirmation |
      | "not.found.fc" | "reg.valid.policy" | "reg.valid.username" | "reg.valid.username" |

  @PCC_01.14
  Scenario Outline: User does not register to PCC because of expired link
    Given user opens the <registrationLink> he received some time ago
    Then an expired link warning message appears

    Examples: 
      | registrationLink    |
      | "registration.link" |

  @PCC_01.15
  Scenario Outline: User successfully registers to PCC private area as organization
    Given user wants to register to PCC private area as organization
    When user enters all required organization registration fields <vatNumber> <policyNumber> <username> <usernameConfirmation> and privacy permissions in the registration page
    And user successfully validates them accessing the next registration step
    Then user successfully complete the organization registration entering the <password>

    Examples: 
      | vatNumber              | policyNumber               | username                     | usernameConfirmation         | password   |
      | "reg.user.aai.p.fc001" | "reg.user.aai.p.policy001" | "reg.user.aai.p.username001" | "reg.user.aai.p.username001" | "reg.pswd" |

  @PCC_01.18
  Scenario Outline: User can not register to PCC as organization because user is already registered
    Given user has already registered to PCC private area as organization
    When user enters all required organization registration fields <vatNumber> <policyNumber> <username> <usernameConfirmation> and privacy permissions in the registration page
    And user validates registration fields
    Then an already registered warning message appears

    Examples: 
      | vatNumber                     | policyNumber              | username                    | usernameConfirmation        |
      | "reg.user.aai.org.vat.number" | "reg.user.aai.org.policy" | "reg.user.aai.org.username" | "reg.user.aai.org.username" |

  @PCC_01.19
  Scenario Outline: User can not register to PCC because user enters a not found vat number
    Given user wants to register to PCC private area as organization
    When user enters all required organization registration fields <vatNumber> <policyNumber> <username> <usernameConfirmation> and privacy permissions in the registration page
    And user validates registration fields
    Then a vat number not found warning message appears

    Examples: 
      | vatNumber              | policyNumber       | username             | usernameConfirmation |
      | "not.found.vat.number" | "reg.valid.policy" | "reg.valid.username" | "reg.valid.username" |
