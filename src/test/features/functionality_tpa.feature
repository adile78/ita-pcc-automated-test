# @author Di Lembo Annalisa
@Ignore @FunctionalityTpa
Feature: Verify Tpa Functionality

  @PCC_TPA.01
  Scenario Outline: Verify Refund Request Tpa Functionality.
    Given user is logged in <username> and <password>
    And user wants to require a <request type> for health policy
    When user fills out refund first form:
      | calendarDay    | refund.calendarDay   |
      | calendarMonth  | refund.calendarMonth |
      | calendarYear   | refund.calendarYear  |
      | policyIdRefund | refund.policyNumber  |
      | isAssisted     | refund.isAssisted    |
      | service        | refund.service       |
    And user fills out refund second form:
      | eventCategories | refund.eventCategories |
      | structureType   | refund.structureType   |
      | documentType    | refund.documentType    |
      | fileUpload      | refund.fileUpload      |
      | documentExpense | refund.documentExpense |
      | amountExpense   | refund.amountExpense   |
      | numberExpense   | refund.numberExpense   |
      | dayExpense      | refund.dayExpense      |
      | monthExpense    | refund.monthExpense    |
      | yearExpense     | refund.yearExpense     |
      | iban            | refund.iban            |
      | phone           | refund.phone           |
      | mail            | refund.mail            |
    And user sees summary page
    Then user sees thank you page for <request type>

    Examples: 
      | username       | password   | request type   |
      | "tpa.username" | "tpa.pswd" | "request.type" |

  @PCC_TPA.02
  Scenario Outline: Verify Request A Reservation Functionality.
    Given user is logged in <username> and <password>
    And user wants to require a <request type> for health policy
    When user fills out reservation first form:
      | calendarDay   | reservation.calendarDay   |
      | calendarMonth | reservation.calendarMonth |
      | calendarYear  | reservation.calendarYear  |
      | policyIdRes   | reservation.policyNumber  |
      | isAssisted    | reservation.isAssisted    |
      | service       | reservation.service       |
    And user fills out reservation second form:
      | eventCategories | reservation.eventCategories |
      | structureType   | reservation.structureType   |
      | doctor          | reservation.doctor          |
      | documentType    | reservation.documentType    |
      | fileUpload      | reservation.fileUpload      |
      | phone           | reservation.phone           |
      | mail            | reservation.mail            |
    And user sees summary page
    Then user sees thank you page for <request type>

    Examples: 
      | username       | password   | request type   |
      | "tpa.username" | "tpa.pswd" | "request.type" |

  @PCC_TPA.03
  Scenario Outline: Verify Request Check Up Functionality.
    Given user is logged in <username> and <password>
    And user wants to require a <request type> for health policy
    When user fills out check up form:
      | policyId       | checkup.policyId       |
      | fileUpload     | checkup.fileUpload     |
      | description    | checkup.description    |
      #| phone          | checkup.phone          |
      | recontactDay   | checkup.recontactDay   |
      | recontactMonth | checkup.recontactMonth |
      | recontactYear  | checkup.recontactYear  |
      | time           | checkup.time           |
    Then user sees thank you page for <request type>

    Examples: 
      | username       | password   | request type   |
      | "tpa.username" | "tpa.pswd" | "request.type" |

  @PCC_TPA.04
  Scenario Outline: Verify Request Daily Allowance Functionality.
    Given user is logged in <username> and <password>
    And user wants to require a <request type> for health policy
    When user fills out daily allowance first tier form:
      | calendarStartDay   | daily.calendarStartDay   |
      | calendarStartMonth | daily.calendarStartMonth |
      | calendarStartYear  | daily.calendarStartYear  |
      | calendarEndDay     | daily.calendarEndDay     |
      | calendarEndMonth   | daily.calendarEndMonth   |
      | calendarEndYear    | daily.calendarEndYear    |
      | policyId           | daily.policyNumber       |
      | isAssisted         | daily.isAssisted         |
      | service            | daily.service            |
    And user fills out daily allowance second tier form:
      | eventCategories | daily.eventCategories |
      | assetInput      | daily.assetInput      |
      | documentType    | daily.documentType    |
      | fileUpload      | daily.fileUpload      |
      | iban            | daily.iban            |
      | phone           | daily.phone           |
      | description     | daily.description     |
      | recontactDay    | daily.recontactDay    |
      | recontactMonth  | daily.recontactMonth  |
      | recontactYear   | daily.recontactYear   |
      | time            | daily.time            |
    And user sees summary page
    Then user sees thank you page for <request type>

    Examples: 
      | username       | password   | request type   |
      | "tpa.username" | "tpa.pswd" | "request.type" |
