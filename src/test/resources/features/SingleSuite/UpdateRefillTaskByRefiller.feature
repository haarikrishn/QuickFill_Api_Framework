Feature: Verify the functionality of Update Refill Task by Refiller

  Background: Generate access token from username and password
    Given Give Username and Password to get Access Token
      | username | 797978    |
      | password | Dmart@123 |

#  @URTR
  Scenario: Update Refill Task by Refiller with refilledQuantity similar to requestedQuantity
    And Get the refillId to update refill task
    And Give the status to update the refill task "COMPLETED"
    And Provide the refilled quantity for updating refill task 15
    And Give the comments for updating refill task "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "200"
    And Check the response time for the Refill Task updated
    And Verify that refill task is updated successfully

#  @URTR
  Scenario: Update Refill Task by Refiller with refilledQuantity less than requestedQuantity
    And Get the refillId to update refill task
    And Give the status to update the refill task "COMPLETED"
    And Provide the refilled quantity for updating refill task 10
    And Give the comments for updating refill task "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "200"
    And Check the response time for the Refill Task updated
    And Verify that refill task is updated successfully

#  @URTR
  Scenario: Update Refill Task by Refiller without refillID
    And Give the status to update the refill task "COMPLETED"
    And Provide the refilled quantity for updating refill task 15
    And Give the comments for updating refill task "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "400"

#  @URTR
  Scenario: Update Refill Task by Refiller without status
    And Get the refillId to update refill task
    And Provide the refilled quantity for updating refill task 15
    And Give the comments for updating refill task "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "400"

#  @URTR
  Scenario: Update Refill Task by Refiller without refilledQuantity field
    And Get the refillId to update refill task
    And Give the status to update the refill task "COMPLETED"
    And Give the comments for updating refill task "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "400"

#  @URTR
  Scenario: Update Refill Task by Refiller with refilledQuantity more than requestedQuantity
    And Get the refillId to update refill task
    And Give the status to update the refill task "COMPLETED"
    And Provide the refilled quantity for updating refill task 30
    And Give the comments for updating refill task "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "400"

#  @URTR
  Scenario: Update Refill Task by Refiller with negative refilledQuantity
    And Get the refillId to update refill task
    And Give the status to update the refill task "COMPLETED"
    And Provide the refilled quantity for updating refill task -15
    And Give the comments for updating refill task "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "400"

#  @URTR
  Scenario: Re-Update COMPLETED Refill Task by Refiller with new refilledQuantity
    And Get the refillId to update refill task
    And Give the status to update the refill task "COMPLETED"
    And Provide the refilled quantity for updating refill task 14
    And Give the comments for updating refill task "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "400"

#  @URTR
  Scenario Outline: Update Refill Task by Refiller with invalid refillId
    And Get the refillId to update refill task "<refillId>"
    And Give the status to update the refill task "COMPLETED"
    And Provide the refilled quantity for updating refill task 15
    And Give the comments for updating refill task "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "400"
  Examples:
    | refillId                                            |
    |                                                     |
    | null                                                |
    | 888c2-685a-a5bf-4d4-654a-q67e-345d-769a-2dc38v022b3 |

#  @URTR
  Scenario Outline: Update Refill Task by Refiller with invalid status
    And Get the refillId to update refill task
    And Give the status to update the refill task "<status>"
    And Provide the refilled quantity for updating refill task 15
    And Give the comments for updating refill task "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "400"
  Examples:
    | status   |
    |          |
    | null     |
    | CLOSED   |
    | VERIFIED |
    | UPDATED  |

#  @URTR
  Scenario: Update Refill Task by Refiller of other siteID
    And Get the refillId to update refill task
    And Give the status to update the refill task "COMPLETED"
    And Provide the refilled quantity for updating refill task 15
    And Give the comments for updating refill task "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "401"

#  @URTR
  Scenario: Update Refill Task by Refiller without closing FloorWalk
    And Get the refillId to update refill task
    And Give the status to update the refill task "COMPLETED"
    And Provide the refilled quantity for updating refill task 15
    And Give the comments for updating refill task "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "200"

#  @URTR
  Scenario: Update Refill Task by Refill Requester
    And Get the refillId to update refill task
    And Give the status to update the refill task "COMPLETED"
    And Provide the refilled quantity for updating refill task 15
    And Give the comments for updating refill task "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "401"

#  @URTR
  Scenario: Update Refill Task by Refiller after Refill Task is updated by Auditor
    And Get the refillId to update refill task
    And Give the status to update the refill task "COMPLETED"
    And Provide the refilled quantity for updating refill task 14
    And Give the comments for updating refill task "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "409"


