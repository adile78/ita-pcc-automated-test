@Redemption
Feature: Redemption policies

  Scenario Outline: User consults tracking of his open redemption request
    Given user is logged in <username> and <password>
    And user has a policy <policyNumber> with an open redemption requests
    Then user sees the <redeemStatus> tracking

    Examples: 
      | username             | password          | policyNumber           | redeemStatus           |
      | "redemption.user.tc" | "redemption.pswd" | "redemption.policy.tc" | "redemption.status.tc" |
      | "redemption.user.ge" | "redemption.pswd" | "redemption.policy.ge" | "redemption.status.ge" |
      | "redemption.user.ap" | "redemption.pswd" | "redemption.policy.ap" | "redemption.status.ap" |
      | "redemption.user.gp" | "redemption.pswd" | "redemption.policy.gp" | "redemption.status.gp" |

  Scenario Outline: User consults tracking of his closed redemption request
    Given user is logged in <username> and <password>
    And user has a policy <policyNumber> with a closed redemption requests
    Then user sees the <redeemStatus> tracking

    Examples: 
      | username             | password          | policyNumber           | redeemStatus           |
      | "redemption.user.na" | "redemption.pswd" | "redemption.policy.na" | "redemption.status.na" |
      | "redemption.user.po" | "redemption.pswd" | "redemption.policy.po" | "redemption.status.po" |

  Scenario Outline: User opens a total redemption request for a total redemption policy
    Given user is logged in <username> and <password>
    And user has a redeemable policy <policyNumber> with no open redemption requests
    And user wants to open a redemption request for a <partialTotal> redemption policy
    When user checks amount <finalRedemptionValue> and <redemptionType> redemption type and confirms his will
    Then user succesfully opens a redemption request
    And user can access the redemption request tracking of his policy <policyNumber>

    Examples: 
      | username             | password          | policyNumber           | partialTotal       | finalRedemptionValue     | redemptionType     |
      | "redemption.user.po" | "redemption.pswd" | "redemption.policy.po" | "redemption.total" | "redemption.final.value" | "redemption.total" |

  Scenario Outline: Partial redemption request for a partial redemption policy becomes total
    Given user is logged in <username> and <password>
    And user has a redeemable policy <policyNumber> with no open redemption requests
    And user wants to open a redemption request for a <partialTotal> redemption policy
    When user selects the redemption partial value <redemptionValue>
    Then partial redemption request becomes total

    Examples: 
      | username             | password          | policyNumber           | redemptionValue              | partialTotal         |
      | "redemption.user.na" | "redemption.pswd" | "redemption.policy.na" | "redemption.partial.value.2" | "redemption.partial" |

  Scenario Outline: User opens a total redemption request for a partial redemption policy
    Given user is logged in <username> and <password>
    And user has a redeemable policy <policyNumber> with no open redemption requests
    And user wants to open a redemption request for a <partialTotal> redemption policy
    When user selects the redemption partial value <redemptionValue> and goes to next step
    And user checks amount <finalRedemptionValue> and <redemptionType> redemption type and confirms his will
    Then user succesfully opens a redemption request
    And user can access the redemption request tracking of his policy <policyNumber>

    Examples: 
      | username             | password          | policyNumber           | redemptionValue              | partialTotal         | finalRedemptionValue       | redemptionType     |
      | "redemption.user.po" | "redemption.pswd" | "redemption.policy.po" | "redemption.partial.value.2" | "redemption.partial" | "redemption.final.value.2" | "redemption.total" |

  Scenario Outline: User opens a partial redemption request for a partial redemption policy
    Given user is logged in <username> and <password>
    And user has a redeemable policy <policyNumber> with no open redemption requests
    And user wants to open a redemption request for a <partialTotal> redemption policy
    When user selects the redemption partial value <redemptionValue> and goes to next step
    And user checks amount <redemptionValue> and <redemptionType> redemption type and confirms his will
    Then user succesfully opens a redemption request
    And user can access the redemption request tracking of his policy <policyNumber>

    Examples: 
      | username             | password          | policyNumber           | redemptionValue            | partialTotal         | finalRedemptionValue       | redemptionType     |
      | "redemption.user.pk" | "redemption.pswd" | "redemption.policy.pk" | "redemption.partial.value" | "redemption.partial" | "redemption.final.value.2" | "redemption.total" |

@prova
  Scenario Outline: User is informed that his policy has been renewed
    Given user is logged in <username> and <password>
    When user consults his renewed policy <policyNumber> on dashboard policy list
    Then user sees the renewed policy status information

    Examples: 
      | username             | password          | policyNumber           |
      | "redemption.user.po" | "redemption.pswd" | "redemption.policy.po" |
