#Author: abhinav.dev@gmail.com
#Comments: Demo project
#Scenario Description: User needs to check if there are any broken links

@test1
Feature: Check broken links

  @test1
  Scenario: Validate broken links on home page
    Given Amazon home page is launched
    When User fetches all links on the page
    Then user validates if any link is broken and reports

