Feature: Verify the functionality of Update Refill Task by Refiller from v1 endpoint

  Background: Generate access token from username and password
    Given Give Username and Password to get Access Token
      | username | 797978    |
      | password | Dmart@123 |

  @Excel
  Scenario: Update all the Refill Task by Refiller from v1 endpoint
    And Give the Excel file to get the request payload for updating Refill task "QuickFill_TestData.xlsx"
    And Give the sheet name "UpdateRefillTasksByRefiller" to get all the Refill Task to be updated
    When Requester calls the Update Refill Task's v1 endpoint to update list of all refill task
    Then verify that status code be equal to "200"
    And Check the response time for the Refill Task updated from v1 endpoint
    And Verify that refill task is updated successfully from v1 endpoint

  @URTR
  Scenario: Update Refill Task by Refiller with refilledQuantity similar to requestedQuantity from v1 endpoint
    And Get the refillId to update refill task from v1 endpoint
    And Give the status to update the refill task "COMPLETED" from v1 endpoint
    And Provide the refilled quantity for updating refill task 15 from v1 endpoint
    And Give the comments for updating refill task from v1 endpoint "checking the update refill task functionality"
    When Requester calls the Update Refill Task's v1 endpoint to update refill task
    Then Verify that status code be equal to "200"
    And Check the response time for the Refill Task updated from v1 endpoint
    And Verify that refill task is updated successfully from v1 endpoint

#  @URTR
  Scenario: Update Refill Task by Refiller with refilledQuantity less than requestedQuantity from v1 endpoint
    And Get the refillId to update refill task from v1 endpoint
    And Give the status to update the refill task "COMPLETED" from v1 endpoint
    And Provide the refilled quantity for updating refill task 10 from v1 endpoint
    And Give the comments for updating refill task from v1 endpoint "checking the update refill task functionality"
    When Requester calls the Update Refill Task's v1 endpoint to update refill task
    Then Verify that status code be equal to "200"
    And Check the response time for the Refill Task updated from v1 endpoint
    And Verify that refill task is updated successfully from v1 endpoint

#  @URTR
  Scenario: Update Refill Task by Refiller without refillID from v1 endpoint
    And Give the status to update the refill task "COMPLETED" from v1 endpoint
    And Provide the refilled quantity for updating refill task 15 from v1 endpoint
    And Give the comments for updating refill task from v1 endpoint "checking the update refill task functionality"
    When Requester calls the Update Refill Task's v1 endpoint to update refill task
    Then Verify that status code be equal to "400"

#  @URTR
  Scenario: Update Refill Task by Refiller without status from v1 endpoint
    And Get the refillId to update refill task from v1 endpoint
    And Provide the refilled quantity for updating refill task 15 from v1 endpoint
    And Give the comments for updating refill task from v1 endpoint "checking the update refill task functionality"
    When Requester calls the Update Refill Task's v1 endpoint to update refill task
    Then Verify that status code be equal to "400"

#  @URTR
  Scenario: Update Refill Task by Refiller without refilledQuantity field from v1 endpoint
    And Get the refillId to update refill task from v1 endpoint
    And Give the status to update the refill task "COMPLETED" from v1 endpoint
    And Give the comments for updating refill task from v1 endpoint "checking the update refill task functionality"
    When Requester calls the Update Refill Task's v1 endpoint to update refill task
    Then Verify that status code be equal to "400"

#  @URTR
  Scenario: Update Refill Task by Refiller with refilledQuantity more than requestedQuantity from v1 endpoint
    And Get the refillId to update refill task from v1 endpoint
    And Give the status to update the refill task "COMPLETED" from v1 endpoint
    And Provide the refilled quantity for updating refill task 30 from v1 endpoint
    And Give the comments for updating refill task from v1 endpoint "checking the update refill task functionality"
    When Requester calls the Update Refill Task's v1 endpoint to update refill task
    Then Verify that status code be equal to "400"

#  @URTR
#  Scenario: Update Refill Task by Refiller with negative refilledQuantity from v1 endpoint
#    And Get the refillId to update refill task
#    And Give the status to update the refill task "COMPLETED"
#    And Provide the refilled quantity for updating refill task -15
#    And Give the comments for updating refill task "checking the update refill task functionality"
#    When Requester calls the Update Refill Task's endpoint to update refill task
#    Then Verify that status code be equal to "400"

#  @URTR
#  Scenario: Re-Update COMPLETED Refill Task by Refiller with new refilledQuantity from v1 endpoint
#    And Get the refillId to update refill task
#    And Give the status to update the refill task "COMPLETED"
#    And Provide the refilled quantity for updating refill task 14
#    And Give the comments for updating refill task "checking the update refill task functionality"
#    When Requester calls the Update Refill Task's endpoint to update refill task
#    Then Verify that status code be equal to "400"

#  @URTR
  Scenario Outline: Update Refill Task by Refiller with invalid refillId from v1 endpoint
    And Get the refillId to update refill task "<refillId>" from v1 endpoint
    And Give the status to update the refill task "COMPLETED" from v1 endpoint
    And Provide the refilled quantity for updating refill task 15 from v1 endpoint
    And Give the comments for updating refill task from v1 endpoint "checking the update refill task functionality"
    When Requester calls the Update Refill Task's v1 endpoint to update refill task
    Then Verify that status code be equal to "400"
  Examples:
    | refillId                                            |
    |                                                     |
    | null                                                |
    | 888c2-685a-a5bf-4d4-654a-q67e-345d-769a-2dc38v022b3 |

#  @URTR
  Scenario Outline: Update Refill Task by Refiller with invalid status from v1 endpoint
    And Get the refillId to update refill task from v1 endpoint
    And Give the status to update the refill task "<status>" from v1 endpoint
    And Provide the refilled quantity for updating refill task 15 from v1 endpoint
    And Give the comments for updating refill task from v1 endpoint "checking the update refill task functionality"
    When Requester calls the Update Refill Task's v1 endpoint to update refill task
    Then Verify that status code be equal to "400"
  Examples:
    | status  |
    |         |
    | null    |
    | CLOSED  |
    | UPDATED |

#  @URTR
  Scenario: Update Refill Task by Refiller of other siteID from v1 endpoint
    And Get the refillId to update refill task from v1 endpoint
    And Give the status to update the refill task "COMPLETED" from v1 endpoint
    And Provide the refilled quantity for updating refill task 15 from v1 endpoint
    And Give the comments for updating refill task from v1 endpoint "checking the update refill task functionality"
    When Requester calls the Update Refill Task's v1 endpoint to update refill task
    Then Verify that status code be equal to "401"

#  @URTR
  Scenario: Update Refill Task by Refill Requester from v1 endpoint
    And Get the refillId to update refill task from v1 endpoint
    And Give the status to update the refill task "COMPLETED" from v1 endpoint
    And Provide the refilled quantity for updating refill task 15 from v1 endpoint
    And Give the comments for updating refill task from v1 endpoint "checking the update refill task functionality"
    When Requester calls the Update Refill Task's v1 endpoint to update refill task
    Then Verify that status code be equal to "401"

#  @URTR
  Scenario: Update Refill Task by Refiller after Refill Task is updated by Auditor from v1 endpoint
    And Get the refillId to update refill task from v1 endpoint
    And Give the status to update the refill task "COMPLETED" from v1 endpoint
    And Provide the refilled quantity for updating refill task 14 from v1 endpoint
    And Give the comments for updating refill task from v1 endpoint "checking the update refill task functionality"
    When Requester calls the Update Refill Task's v1 endpoint to update refill task
    Then Verify that status code be equal to "409"


