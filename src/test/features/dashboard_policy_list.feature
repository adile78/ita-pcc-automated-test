@DashboardPolicyList
Feature: Dashboard policy list

  Scenario Outline: User consults his <category> policy
    Given user is logged in <username> and <password>
    When user consults his <categoryType> policies on dashboard policy list
    Then user sees all required <categoryType> policy fields

    @PCC_03.02
    Examples: 
      | category | username                             | password     | categoryType |
  #    | motor    | "dashb.motor.user.aai.pol.username"  | "dashb.pswd" | "motor"      |
      | motor    | "dashb.motor.user.amps.pol.username" | "dashb.pswd" | "motor"      |

    @PCC_03.05
    Examples: 
      | category | username                           | password     | categoryType |
      | home     | "dashb.home.user.aai.pol.username" | "dashb.pswd" | "home"       |

    # | home     | "dashb.home.user.amps.pol.username" | "dashb.pswd" | "home"       |
    
    @PCC_03.07
    Examples: 
      | category   | username                                  | password     | categoryType |
      | protection | "dashb.protection.user.aai.pol.username"  | "dashb.pswd" | "protection" |
      | protection | "dashb.protection.user.amps.pol.username" | "dashb.pswd" | "protection" |

    @PCC_03.09
    Examples: 
      | category | username                               | password     | categoryType |
      | savings  | "dashb.savings.user.aai.pol.username"  | "dashb.pswd" | "savings"    |
      | savings  | "dashb.savings.user.amps.pol.username" | "dashb.pswd" | "savings"    |

    @PCC_03.11
    Examples: 
      | category    | username                                   | password     | categoryType  |
      # | unit linked | "dashb.unit.linked.user.aai.pol.username"  | "dashb.pswd" | "unit.linked" |
      #  | unit linked | "dashb.unit.linked.user.amps.pol.username" | "dashb.pswd" | "unit.linked" |
      | unit linked | "dashb.unit.linked.user.aai.amps.username" | "dashb.pswd" | "unit.linked" |

    @PCC_03.13
    Examples: 
      | category           | username                               | password     | categoryType         |
      #     | workers protection | "dashb.workers.user.aai.pol.username"  | "dashb.pswd" | "workers.protection" |
      | workers protection | "dashb.workers.user.amps.pol.username" | "dashb.pswd" | "workers.protection" |
