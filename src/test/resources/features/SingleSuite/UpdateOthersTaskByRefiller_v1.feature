Feature: Verify the functionality of Update Others Task by Refiller

  Background: Generate access token from username and password
    Given Give Username and Password to get Access Token
      | username | 797978    |
      | password | Dmart@123 |

  @Excel
  Scenario: Update all the Others Task by Refiller from v1 endpoint
    And Give the Excel file to get the request payload for updating Others task "QuickFill_TestData.xlsx"
    And Give the sheet name "UpdateOthersTasksByRefiller" to get all the Others Task to be updated
    When Requester calls the Update Others Task v1 endpoint to update list of all others task
    Then Verify that status code be equal to "200"
    And Check the response time for the Others Task updated from v1 endpoint
    And Verify that others task is updated successfully from v1 endpoint

  @UOTR
  Scenario: Update OTHERS Task by Refiller with valid data from v1 endpoint
    And Get the refillId to update others task from v1 endpoint
    And Give the status to update the others task from v1 endpoint "COMPLETED"
    And Give the comments for updating others task from v1 endpoint "checking the update others task functionality"
    When Requester calls the Update Others Task v1 endpoint to update others task
    Then Verify that status code be equal to "200"
    And Check the response time for the Others Task updated from v1 endpoint
    And Verify that others task is updated successfully from v1 endpoint

##  @UOTR
#  Scenario: Update OTHERS Task by Refiller without closing FloorWalk
#    And Get the refillId to update others task from v1 endpoint
#    And Give the status to update the others task from v1 endpoint "COMPLETED"
#    And Give the comments for updating others task from v1 endpoint "checking the update others task functionality"
#    When Requester calls the Update Others Task v1 endpoint to update others task
#    Then Verify that status code be equal to "200"
#    And Check the response time for the Others Task updated from v1 endpoint
#    And Verify that others task is updated successfully from v1 endpoint

#  @UOTR
  Scenario: Update OTHERS Task by Refiller without refillID from v1 endpoint
    And Give the status to update the others task from v1 endpoint "COMPLETED"
    And Give the comments for updating others task from v1 endpoint "checking the update others task functionality"
    When Requester calls the Update Others Task v1 endpoint to update others task
    Then Verify that status code be equal to "400"

#  @UOTR
  Scenario: Update OTHERS Task by Refiller without status from v1 endpoint
    And Get the refillId to update others task from v1 endpoint
    And Give the comments for updating others task from v1 endpoint "checking the update others task functionality"
    When Requester calls the Update Others Task v1 endpoint to update others task
    Then Verify that status code be equal to "400"

#  @UOTR
  Scenario Outline: Update OTHERS Task by Refiller with invalid refillId from v1 endpoint
    And Get the refillId to update others task from v1 endpoint "<refillId>"
    And Give the status to update the others task from v1 endpoint "COMPLETED"
    And Give the comments for updating others task from v1 endpoint "checking the update others task functionality"
    When Requester calls the Update Others Task v1 endpoint to update others task
    Then Verify that status code be equal to "400"
  Examples:
    | refillId                                           |
    |                                                    |
    | null                                               |
    | 888c2-685a-a5bf-4d4-654a-q67e-345d-769a-2dc38v022b |

#  @UOTR
  Scenario: Update OTHERS Task by Refiller of other siteID from v1 endpoint
    And Get the refillId to update others task from v1 endpoint
    And Give the status to update the others task from v1 endpoint "COMPLETED"
    And Give the comments for updating others task from v1 endpoint "checking the update others task functionality"
    When Requester calls the Update Others Task v1 endpoint to update others task
    Then Verify that status code be equal to "401"


