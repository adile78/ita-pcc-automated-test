@ignore @DashoardSingularCases
Feature: Dashboard policy detail

  @SRT
  Scenario Outline: User consults his AAI <category> policy detail with SRT tool
    Given user is logged in <username> and <password>
    When user consults his AAI <categoryType> <policyNumber> policy detail with SRT tool
    Then user sees all required <categoryType> policy detail with SRT tool features

    Examples: 
      | category | username                              | password     | categoryType | policyNumber                        |
      | savings  | "dashb.savings.srt.user.aai.username" | "dashb.pswd" | "savings"    | "dashb.savings.srt.user.aai.policy" |
 
  @clear_language @ignore
  Scenario Outline: User consults his AAI <category> policy detail with clear language coverages
    Given user is logged in <username> and <password>
    When user consults his AAI <categoryType> policy detail with clear language coverages on dashboard page
    Then user sees all required <categoryType> policy detail with clear language coverages fields

    Examples: 
      | category   | username                                             | password     | categoryType |
      | motor      | "dashb.motor.clear.coverages.user.aai.username"      | "dashb.pswd" | "motor"      |
      | savings    | "dashb.savings.clear.coverages.user.aai.username"    | "dashb.pswd" | "savings"    |
      | protection | "dashb.protection.clear.coverages.user.aai.username" | "dashb.pswd" | "protection" |
      