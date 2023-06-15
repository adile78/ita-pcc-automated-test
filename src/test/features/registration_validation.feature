# encoding: utf8
# language: en
@Validation @ValidationRegistration
Feature: Validate registration

  @PCC_01.08
  Scenario Outline: User gets blocked when he does not insert a correct fiscal code
    Given user wants to register to PCC private area as a person
    When user inserts an incorrect fiscal code <fiscalCode> in the registration page
    Then a fiscal code not correct warning message appears

    Examples: 
      | fiscalCode                          |
      | "reg.validation.user.incomplete.fc" |
      | "reg.validation.user.wrong.fc"      |

  @PCC_01.09
  Scenario Outline: User gets blocked when he does not insert a correct vat number
    Given user wants to register to PCC private area as organization
    When user inserts an incorrect vat number <vatNumber> in the registration page
    Then a vat number not correct warning message appears

    Examples: 
      | vatNumber                                   |
      | "reg.validation.user.incomplete.vat.number" |

  @PCC_01.04
  Scenario Outline: User gets blocked beacuse he inserts a not valid value for username
    Given user wants to register to PCC private area as a person
    When user inserts a not valid value for <username>
    Then a not valid email addresses warning message appears

    Examples: 
      | username                                 |
      | "reg.validation.user.wrong.username_I"   |
      | "reg.validation.user.wrong.username_II"  |
      | "reg.validation.user.wrong.username_III" |

  @PCC_01.05
  Scenario Outline: User gets blocked when he inserts different value for username and username confermation fields
    Given user wants to register to PCC private area as a person
    When user inserts different value for <username> and <usernameConfirmation>
    Then a not equal email addresses warning message appears

    Examples: 
      | username                            | usernameConfirmation                             |
      | "reg.validation.different.username" | "reg.validation.different.username.confirmation" |

  @PCC_01_06
  Scenario Outline: User can not register to PCC as a person because he did not set info privacy
    Given user wants to register to PCC private area as a person
    When user enters all required person registration fields <fiscalCode> <policyNumber> <username> <usernameConfirmation> in the registration page
    And user validates registration fields
    Then an info privacy requirement settings warning message appears

    Examples: 
      | fiscalCode                    | policyNumber                      | username                            | usernameConfirmation                |
      | "reg.user.no.data.privacy.fc" | "reg.user.no.data.privacy.policy" | "reg.user.no.data.privacy.username" | "reg.user.no.data.privacy.username" |

  @PCC_01_07
  Scenario Outline: User can not register to PCC as a person because he did not set info about usage
    Given user wants to register to PCC private area as a person
    When user enters all required person registration fields <fiscalCode> <policyNumber> <username> <usernameConfirmation> and privacy info in the registration page
    And user validates registration fields
    Then an usage info requirement settings warning message appears

    Examples: 
      | fiscalCode                     | policyNumber                       | username                             | usernameConfirmation                 |
      | "reg.user.no.usage.privacy.fc" | "reg.user.no.usage.privacy.policy" | "reg.user.no.usage.privacy.username" | "reg.user.no.usage.privacy.username" |

  @ignore @PCC_01_X
  Scenario Outline: User can not register to PCC because he inserts different value for password and password confirmation fields
    Given user wants to register to PCC private area as a person
    When user enters all required person registration fields <fiscalCode> <policyNumber> <username> <usernameConfirmation> and privacy permissions in the registration page
    And user successfully validates them accessing the next registration step
    Then user enters different values for <password> and <passwordConfirmation> in registration process
    And user cannot complete his registration

    Examples: 
      | fiscalCode               | policyNumber                 | username                       | usernameConfirmation           | password   | passwordConfirmation |
      | "reg.user.aai.p.fc.0001" | "reg.user.aai.p.policy.0001" | "reg.user.aai.p.username.0001" | "reg.user.aai.p.username.0001" | "reg.pswd" | "reg.pswd"           |
