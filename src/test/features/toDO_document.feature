@ignore @Documents
Feature: Document section consultation

  @PCC_10.01
  Scenario Outline: User with multicompany policies [AAI and AMPS] consults his documents section
    Given user is logged in <username> and <password>
    And user accesses documents section
    Then user with multicompany policies sees two section <company1> and <company2>, one for each company

    Examples: 
      | username                          | password   | company1 | company2 |
      | "doc.user.multi.company.aai.amps" | "doc.pswd" | "aai"    | "amps"   |
      | "doc.user.multi.company.amps.amf" | "doc.pswd" | "amps"   | "amf"    |

  @PCC_10.04
  Scenario Outline: User with multicompany policies [AAI and AMPS] consults one of his [AAI and AMPS] documents
    Given user is logged in <username> and <password>
    And user accesses documents section
    When user with multicompany policies consults his <company> document
    Then the <company> document is downloaded

    Examples: 
      | username                                    | password   | company |
      | "doc.user.multi.company.aai.amps"           | "doc.pswd" | "aai"   |
      | "doc.user.multi.company.aai.amps"           | "doc.pswd" | "amps"  |
      | "doc.user.multi.company.no.amf.documents"   | "doc.pswd" | "amf"   |
      | "doc.user.multi.company.amps.not.available" | "doc.pswd" | "amps"  |
    
