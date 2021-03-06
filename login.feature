Feature: Login Action
 
Scenario: Header labels for the data set
Given Header labels
Then Generate Header to the report

Scenario: Validate Login with valid credentials
 Given User is on Home Page
 When User enters "test@qa-experience.com" and "Password1"
 Then Login Successful
 
 Scenario: Validate Login with valid username and invalid password
 Given User is on Home Page
 When User enters "test@qa-experience.com" and "Password3"
 Then Login Not Successful
 
 Scenario: Validate Login with invalid username and valid password
 Given User is on Home Page
 When User enters "test@qa-experience1.com" and "Password1"
 Then Login Not Successful
 
 Scenario: Validate Login with valid username and invalid password(inconsistent)
 Given User is on Home Page
 When User enters "test@qa-experience.com" and "pass"
 Then Login Not Successful
 
 Scenario: Validate Login with invalid username(inconsistent) and valid password
 Given User is on Home Page
 When User enters "testqa-experience.com" and "Password1"
 Then Login Not Successful
 
 Scenario: Validate Login with invalid username(inconsistent) and invalid password
 Given User is on Home Page
 When User enters "test@qa-experience.com7" and "Password2"
 Then Login Not Successful
 
 Scenario: Validate Login with invalid username and invalid password
 Given User is on Home Page
 When User enters "test@qa-expfcdsfederience.com" and "fakepwd111"
 Then Login Not Successful