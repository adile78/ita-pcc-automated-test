@ignore @RcaOpening
Feature: RCA claim opening

  @rca
  Scenario Outline: User opens a RCA claim
    Given user is logged in <username> and <password>
    And user wants to open a <claimType> claim
    When user inserts all <claimType> claim first step details:
      | date               | claims.rca.date                 |
      | time               | claims.rca.time                 |
      | city               | claims.rca.city                 |
      | cityFirstLetters   | claims.rca.city.first.letters   |
      | address            | claims.rca.address              |
      | cityAv             | claims.rca.cityAv               |
      | cityAvFirstLetters | claims.rca.cityAv.first.letters |
      | addressAv          | claims.rca.addressAv            |
      | policyNumber       | claims.rca.policy.number        |
    And user selects documents and events details validating them:
      | documentCategoryList                  | claims.rca.document.category.list                     |
      | documentPathList                      | claims.rca.document.path.list                         |
      | eventCategory                         | claims.rca.event.category                             |
      | damageGood                            | claims.rca.damage.good                                |
      | isOwner                               | claims.rca.is.owner                                   |
      | isDriver                              | claims.rca.is.driver                                  |
      | driverGender                          | claims.rca.driver.gender                              |
      | driverName                            | claims.rca.driver.name                                |
      | driverSurname                         | claims.rca.driver.surname                             |
      | driverBirthday                        | claims.rca.driver.birthday                            |
      | driverBirthdayCityFirstLetters        | claims.rca.driver.birthday.city.first.letters         |
      | driverBirthdayCity                    | claims.rca.driver.birthday.city                       |
      | driverAddress                         | claims.rca.driver.address                             |
      | driverCityFirstLetters                | claims.rca.driver.city.first.letters                  |
      | driverCity                            | claims.rca.driver.city                                |
      | driverCap                             | claims.rca.driver.cap                                 |
      | driverPhone                           | claims.rca.driver.phone                               |
      | isInjury                              | claims.rca.is.injury                                  |
      | injuryDescription                     | claims.rca.injury.description                         |
      | numberOfPassengers                    | claims.rca.number.passengers                          |
      | passengerGenderList                   | claims.rca.passenger.gender.list                      |
      | passengerNameList                     | claims.rca.passenger.name.list                        |
      | passengerSurnameList                  | claims.rca.passenger.surname.list                     |
      | passengerBirthdayList                 | claims.rca.passenger.birthday.list                    |
      | passengerBirthdayCityFirstLettersList | claims.rca.passenger.birthday.city.first.letters.list |
      | passengerBirthdayCityList             | claims.rca.passenger.birthday.city.list               |
      | passengerAddressList                  | claims.rca.passenger.address.list                     |
      | passengerCityFirstLettersList         | claims.rca.passenger.city.first.letters.list          |
      | passengerCityList                     | claims.rca.passenger.city.list                        |
      | passengerCapList                      | claims.rca.passenger.cap.list                         |
      | passengerPhoneList                    | claims.rca.passenger.phone.list                       |
      | eventDescription                      | claims.rca.event.description                          |
      | iban                                  | claims.rca.iban                                       |
    And user inserts claim last details:
      | phoneNumber | claims.rca.phoneNumber |
    Then user save his claim

    Examples: 
      | username              | password      | claimType |
      | "claims.rca.username" | "claims.pswd" | "rca"     |
