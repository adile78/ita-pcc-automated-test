@ignore @KaskoOpening
Feature: Kasko claim opening

  @kasko
  Scenario Outline: User opens a kasco claim
    Given user is logged in <username> and <password>
    And user wants to open a <claimType> claim
    When user inserts all <claimType> claim first step details:
      | date               | claims.kasco.date               |
      | time               | claims.kasco.time               |
      | city               | claims.kasco.city               |
      | cityFirstLetters   | claims.kasco.cityFirstLetters   |
      | address            | claims.kasco.address            |
      | cityAv             | claims.kasco.cityAv             |
      | cityAvFirstLetters | claims.kasco.cityAvFirstLetters |
      | addressAv          | claims.kasco.addressAv          |
      | policyNumber       | claims.kasco.policyNumber       |
    And user selects documents and events details validating them:
      | documentCategoryList           | claims.kasco.documentCategoryList           |
      | documentPathList               | claims.kasco.documentPathList               |
      | eventCategory                  | claims.kasco.eventCategory                  |
      | damageGood                     | claims.kasco.damageGood                     |
      | isOwner                        | claims.kasco.isOwner                        |
      | isDriver                       | claims.kasco.isDriver                       |
      | driverGender                   | claims.kasco.driverGender                   |
      | driverName                     | claims.kasco.driverName                     |
      | driverSurname                  | claims.kasco.driverSurname                  |
      | driverBirthday                 | claims.kasco.driverBirthday                 |
      | driverBirthdayCityFirstLetters | claims.kasco.driverBirthdayCityFirstLetters |
      | driverBirthdayCity             | claims.kasco.driverBirthdayCity             |
      | driverAddress                  | claims.kasco.driverAddress                  |
      | driverCityFirstLetters         | claims.kasco.driverCityFirstLetters         |
      | driverCity                     | claims.kasco.driverCity                     |
      | driverCap                      | claims.kasco.driverCap                      |
      | driverPhone                    | claims.kasco.driverPhone                    |
      | isInjury                       | claims.kasco.isInjury                       |
      | injuryDescription              | claims.kasco.injuryDescription              |
      | eventDescription               | claims.kasco.eventDescription               |
      | iban                           | claims.kasco.iban                           |
    And user inserts claim last details:
      | phoneNumber | claims.kasco.phoneNumber |
    Then user save his claim

    Examples: 
      | username                | password      | claimType |
      | "claims.kasco.username" | "claims.pswd" | "kasco"   |

 