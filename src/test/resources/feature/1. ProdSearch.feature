#Author: abhinav.dev@gmail.com
#Comments: Demo project
#Scenario Description: User is able to search product Amazon successfully
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template


Feature: Product search on Amazon
    

  @test
  Scenario: Product search
    Given Amazon home page is launched
    When user searches for the product
    Then Lists result with minimum price on first page 
    
    
    
   #@negative
  #Scenario: Product search
    #Given Amazon home page is launched
    #When user searches for the product
    #Then Product not found message is displayed


