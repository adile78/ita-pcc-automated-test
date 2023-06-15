@DashoardPolicyDetail
Feature: Dashboard policy detail

  Scenario Outline: User consults his <category> policies detail
    Given user is logged in <username> and <password>
    When user consults his <categoryType> policy detail on dashboard page
    Then user sees all required <categoryType> policy detail fields

    @motor
    Examples: 
      | category | username                             | password     | categoryType |
      #     | motor    | "dashb.motor.user.aai.pol.username"  | "dashb.pswd" | "motor"      |
      | motor    | "dashb.motor.user.amps.pol.username" | "dashb.pswd" | "motor"      |

    @home
    Examples: 
      | category | username                            | password     | categoryType |
      | home     | "dashb.home.user.aai.pol.username"  | "dashb.pswd" | "home"       |
      | home     | "dashb.home.user.amps.pol.username" | "dashb.pswd" | "home"       |

    @protection
    Examples: 
      | category   | username                                  | password     | categoryType |
      | protection | "dashb.protection.user.amps.pol.username" | "dashb.pswd" | "protection" |

    @savings
    Examples: 
      | category | username                               | password     | categoryType |
      | savings  | "dashb.savings.user.aai.pol.username"  | "dashb.pswd" | "savings"    |
      | savings  | "dashb.savings.user.amps.pol.username" | "dashb.pswd" | "savings"    |

    @unitlinked
    Examples: 
      | category    | username                                   | password     | categoryType  |
      #    | unit linked | "dashb.unit.linked.user.aai.pol.username"  | "dashb.pswd" | "unit.linked" |
      #| unit linked | "dashb.unit.linked.user.amps.pol.username" | "dashb.pswd" | "unit.linked" |
      | unit linked | "dashb.unit.linked.user.aai.amps.username" | "dashb.pswd" | "unit.linked" |

    @workers
    Examples: 
      | category           | username                               | password     | categoryType         |
      | workers protection | "dashb.workers.user.aai.pol.username"  | "dashb.pswd" | "workers.protection" |
      | workers protection | "dashb.workers.user.amps.pol.username" | "dashb.pswd" | "workers.protection" |
