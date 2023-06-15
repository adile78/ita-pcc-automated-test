@ignore @Profile
Feature: Agreements visibility, access data and personal contacts

  @PROF_01
  Scenario Outline: <companyName> user consults his access data in the profile section
    Given user is logged in <username> and <password>
    When user accesses his profile section
    Then user consults his access data

    Examples: 
      | companyName       | username               | password       |
      | Axa Assicurazioni | "contacts.user.aai.p"  | "profile.pswd" |
      | Axa MPS           | "contacts.user.amps.p" | "profile.pswd" |

  @PROF_02
  Scenario Outline: <companyName> user modifies his password in the profile section
    Given user is logged in <username> and <password>
    When user accesses his profile section
    Then user inserts a <newPassword> and succesfully modifies his <oldPassword>

    Examples: 
      | companyName       | username               | password       | newPassword    | oldPassword    |
      | Axa Assicurazioni | "contacts.user.aai.p"  | "profile.pswd" | "profile.pswd" | "profile.pswd" |
      | Axa MPS           | "contacts.user.amps.p" | "profile.pswd" | "profile.pswd" | "profile.pswd" |

  @PROF_03
  Scenario Outline: AAI company user consults his personal contacts data in the profile section
    Given user is logged in <username> and <password>
    When user accesses his profile section
    Then user consults his personal contacts data

    Examples: 
      | username              | password       |
      | "contacts.user.aai.p" | "profile.pswd" |

  @PROF_04
  Scenario Outline: Mono NDG AAI company user modifies his contacts data in the profile section
    Given user is logged in <username> and <password>
    When user accesses his profile section
    Then user modifies his contacts data <newNumber> <email> <emailConfirmation>

    Examples: 
      | username                        | password       | newNumber            | email           | emailConfirmation            |
      | "profile.aai.mono.ndg.username" | "profile.pswd" | "profile.new.number" | "profile.email" | "profile.email.confirmation" |

  @PROF_05
  Scenario Outline: Multi NDG AAI company user can not modify his contacts data in the profile section
    Given user is logged in <username> and <password>
    When user accesses his profile section
    Then user can not view his contacts data

    Examples: 
      | username                          | password       |
      | "doc.user.multi.company.aai.amps" | "profile.pswd" |

  @PROF_06
  Scenario Outline: AAI company user consults his agreements in the profile section
    Given user is logged in <username> and <password>
    When user accesses his profile section
    Then user consults his agreements

    Examples: 
      | username                          | password       |
      | "doc.user.multi.company.aai.amps" | "profile.pswd" |

  @PROF_07
  Scenario Outline: Mono NDG AAI company user modifies his agreements in the profile section
    Given user is logged in <username> and <password>
    When user accesses his profile section
    Then user modifies his agreements

    Examples: 
      | username                        | password       |
      | "profile.aai.mono.ndg.username" | "profile.pswd" |

  @PROF_08
  Scenario Outline: Multi NDG AAI company user can not modify his agreements in the profile section
    Given user is logged in <username> and <password>
    When user accesses his profile section
    Then user can not modify his agreements

    Examples: 
      | username                          | password       |
      | "doc.user.multi.company.aai.amps" | "profile.pswd" |

  @PROF_09
  Scenario Outline: Not AAI company user can only consults his access data in the profile section
    Given user is logged in <username> and <password>
    When user accesses his profile section
    Then user can only consults his access data

    Examples: 
      | username                          | password       |
      | "doc.user.multi.company.aai.amps" | "profile.pswd" |

  @PROF_10
  Scenario Outline: Multi company AAI <companyName> user consults his access data in the AAI profile section
    Given user is logged in <username> and <password>
    When user accesses his AAI profile section
    Then user consults his access data

    Examples: 
      | companyName       | username                          | password       |
      | Axa MPS           | "doc.user.multi.company.aai.amps" | "profile.pswd" |
      | Axa MPS Financial | "doc.user.multi.company.aai.amps" | "profile.pswd" |

  @PROF_11
  Scenario Outline: Multi company AAI <companyName> user modifies his password in the AAI profile section
    Given user is logged in <username> and <password>
    When user accesses his AAI profile section
    Then user modifies his password

    Examples: 
      | companyName       | username                          | password       |
      | Axa Assicurazioni | "doc.user.multi.company.aai.amps" | "profile.pswd" |
      | Axa MPS           | "doc.user.multi.company.aai.amps" | "profile.pswd" |

  @PROF_12
  Scenario Outline: Multi company AAI <companyName> user consults his personal contacts data in the AAI profile section
    Given user is logged in <username> and <password>
    When user accesses his AAI profile section
    Then user consults his personal contacts data

    Examples: 
      | companyName       | username                          | password       |
      | Axa Assicurazioni | "doc.user.multi.company.aai.amps" | "profile.pswd" |
      | Axa MPS           | "doc.user.multi.company.aai.amps" | "profile.pswd" |

  @PROF_13
  Scenario Outline: Multi company mono NDG AAI <companyName> user modifies his contacts data in the AAI profile section
    Given user is logged in <username> and <password>
    When user accesses his AAI profile section
    Then user modifies his contacts data <newNumber> <email> <emailConfirmation>

    Examples: 
      | companyName       | username                          | password       | newNumber            | email           | emailConfirmation            |
      | Axa Assicurazioni | "doc.user.multi.company.aai.amps" | "profile.pswd" | "profile.new.number" | "profile.email" | "profile.email.confirmation" |
      | Axa MPS           | "doc.user.multi.company.aai.amps" | "profile.pswd" | "profile.new.number" | "profile.email" | "profile.email.confirmation" |

  @PROF_14
  Scenario Outline: Multi company multi NDG AAI <companyName> user can not modify his contacts data in the AAI profile section
    Given user is logged in <username> and <password>
    When user accesses his AAI profile section
    Then user can not view his contacts data

    Examples: 
      | companyName       | username                          | password       |
      | Axa Assicurazioni | "doc.user.multi.company.aai.amps" | "profile.pswd" |
      | Axa MPS           | "doc.user.multi.company.aai.amps" | "profile.pswd" |

  @PROF_15
  Scenario Outline: Multi company AAI <companyName> user consults his agreements in the AAI profile section
    Given user is logged in <username> and <password>
    When user accesses his AAI profile section
    Then user consults his agreements

    Examples: 
      | companyName       | username                          | password       |
      | Axa Assicurazioni | "doc.user.multi.company.aai.amps" | "profile.pswd" |
      | Axa MPS           | "doc.user.multi.company.aai.amps" | "profile.pswd" |

  @PROF_16
  Scenario Outline: Multi company mono NDG AAI <companyName> user modifies his agreements in the AAI profile section
    Given user is logged in <username> and <password>
    When user accesses his AAI profile section
    Then user modifies his agreements

    Examples: 
      | companyName       | username                          | password       |
      | Axa Assicurazioni | "doc.user.multi.company.aai.amps" | "profile.pswd" |
      | Axa MPS           | "doc.user.multi.company.aai.amps" | "profile.pswd" |

  @PROF_17
  Scenario Outline: Multi company multi NDG AAI <companyName> user can not modify his agreements in the profile section
    Given user is logged in <username> and <password>
    When user accesses his AAI profile section
    Then user can not modify his agreements

    Examples: 
      | companyName       | username                          | password       |
      | Axa Assicurazioni | "doc.user.multi.company.aai.amps" | "profile.pswd" |
      | Axa MPS           | "doc.user.multi.company.aai.amps" | "profile.pswd" |

  @PROF_18
  Scenario Outline: Multi company AAI <companyName> user consults his <companyName> profile section with only an information paragraph
    Given user is logged in <username> and <password>
    When user accesses his <company> profile section
    Then user sees only a paragraph with information about his profile

    Examples: 
      | companyName       | username                          | password       | company |
      | Axa MPS           | "doc.user.multi.company.aai.amps" | "profile.pswd" | "amps"  |
      | Axa MPS Financial | "doc.user.multi.company.aai.amps" | "profile.pswd" | "amf"   |

  @PROF_19
  Scenario Outline: Multi company, not AAI, <companyName1> <companyName2> user consults his access data in the profile section
    Given user is logged in <username> and <password>
    When user accesses his profile section
    Then user consults his access data

    Examples: 
      | companyName1      | companyName2 | username                          | password       |
      | Axa MPS Financial | Axa MPS      | "doc.user.multi.company.aai.amps" | "profile.pswd" |

  @PROF_20
  Scenario Outline: Multi company, not AAI, <companyName1> <companyName2> user modifies his password in the single profile section
    Given user is logged in <username> and <password>
    When user accesses his profile section
    Then user inserts a <newPassword> and succesfully modifies his <oldPassword>

    Examples: 
      | companyName1      | companyName2 | username                          | password       | newPassword | oldPassword |
      | Axa MPS Financial | Axa MPS      | "doc.user.multi.company.aai.amps" | "profile.pswd" | ""          | ""          |
