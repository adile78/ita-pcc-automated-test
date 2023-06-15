@DashboardCountElements
Feature: Dashboard count elements cases

  @insured
  Scenario Outline: User consults his <category> policy insureds
    Given user is logged in <username> and <password>
    When user consults his <categoryType> <policyNumber> policy detail on dashboard page
    Then user sees all <number> of insureds of his <categoryType> policy

    Examples: 
      | category | username                            | password     | policyNumber                  | number | categoryType |
      | home     | "dashb.home.user.amps.pol.username" | "dashb.pswd" | "home.insureds.policy.number" |      5 | "home"       |

  @coverages
  Scenario Outline: User consults his <category> policy coverages
    Given user is logged in <username> and <password>
    When user consults his <categoryType> <policyNumber> policy detail on dashboard page
    Then user sees all <number> of coverages of his <categoryType> policy

    Examples: 
      | category           | username                               | password     | policyNumber                      | number | categoryType         |
      | workers protection | "dashb.workers.user.amps.pol.username" | "dashb.pswd" | "workers.coverages.policy.number" |      6 | "workers.protection" |

  @beneficiaries
  Scenario Outline: User consults his <category> policy beneficiaries
    Given user is logged in <username> and <password>
    When user consults his <categoryType> <policyNumber> policy detail on dashboard page
    Then user sees all <number> of beneficiaries of his <categoryType> policy

    Examples: 
      | category   | username                            | password     | policyNumber                             | number | categoryType |
      | protection | "protection.beneficiaries.username" | "dashb.pswd" | "protection.beneficiaries.policy.number" |      3 | "protection" |

  @movements
  Scenario Outline: User consults his <category> policy movements
    Given user is logged in <username> and <password>
    When user consults his <categoryType> <policyNumber> policy detail on dashboard page
    Then user sees all <number> of movements of his <categoryType> policy

    Examples: 
      | category    | username                                   | password     | policyNumber                          | number | categoryType  |
      # | protection  | "dashb.protection.user.amps.pol.username"  | "dashb.pswd" | "protection.movements.policy.number"  |      2 | "protection"  |
      | unit linked | "dashb.unit.linked.user.aai.amps.username" | "dashb.pswd" | "unit.linked.movements.policy.number" |     13 | "unit.linked" |

  @redemptionRequestsHistory
  Scenario Outline: User consults his redemption requests history
    Given user is logged in <username> and <password>
    When user consults his <categoryType> <policyNumber> policy detail on dashboard page
    Then user sees all <number> of his redemption requests history

    Examples: 
      | category   | username             | password          | policyNumber           | number | categoryType |
      | protection | "redemption.user.na" | "redemption.pswd" | "redemption.policy.na" |      1 | "protection" |
      | savings    | "redemption.user.ge" | "redemption.pswd" | "redemption.policy.ge" |      2 | "savings"    |
      | protection | "redemption.user.ap" | "redemption.pswd" | "redemption.policy.ap" |      2 | "protection" |
      | protection | "redemption.user.gp" | "redemption.pswd" | "redemption.policy.gp" |      3 | "protection" |
