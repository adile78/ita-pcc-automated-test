@RightShoulder
Feature: Dashoard Right Shoulder

  @Shoulder
  Scenario Outline: User sees the agency box on dashboard page
    Given user is logged in <username> and <password>
    Then user sees the agency box on the right shoulder of dashboard page

    Examples: 
      | username            | password            |
      | "shoulder.username" | "shoulder.password" |
