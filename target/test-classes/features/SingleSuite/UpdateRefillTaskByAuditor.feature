Feature: Verify the functionality of Update Refill task by Auditor

  Background: Generate access token from username and password
    Given Give Username and Password to get Access Token
      | username | 678787    |
      | password | Dmart@123 |

#  @URTR
#  Scenario: Update Refill Task by Refiller with refilledQuantity similar to requestedQuantity
#    Given Give Username and Password to get Access Token
#      | username | 797978    |
#      | password | Dmart@123 |
#    And Get the refillId to update refill task
#    And Give the status to update the refill task "COMPLETED"
#    And Provide the refilled quantity for updating refill task 15
#    And Give the comments for updating refill task "checking the update refill task functionality"
#    When Requester calls the Update Refill Task's endpoint to update refill task
#    Then Verify that status code be equal to "200"
#    And Check the response time for the Refill Task updated
#    And Verify that refill task is updated successfully

  @URTA
  Scenario: Update Refill Task by Auditor
    And Get the refillId to update refill task
    And Give the status to update the refill task "VERIFIED"
    And Provide the refilled quantity for updating refill task 15
    And Give the comments for updating refill task by auditor "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "200"
    And Check the response time for the Refill Task updated
    And Verify that refill task is updated successfully by Auditor

  @URTA
  Scenario: Update Refill Task by Auditor without Refill task being updated by Refiller
    And Get the refillId to update refill task
    And Give the status to update the refill task "VERIFIED"
    And Provide the refilled quantity for updating refill task 15
    And Give the comments for updating refill task by auditor "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "200"
    And Check the response time for the Refill Task updated
    And Verify that refill task is updated successfully by Auditor

  @URTA
  Scenario: Update Refill Task by Auditor without closing FloorWalk
    And Get the refillId to update refill task
    And Give the status to update the refill task "VERIFIED"
    And Provide the refilled quantity for updating refill task 15
    And Give the comments for updating refill task by auditor "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "200"
    And Check the response time for the Refill Task updated
    And Verify that refill task is updated successfully by Auditor

  @URTA
  Scenario: Update Refill Task by Auditor without refillID
    And Give the status to update the refill task "VERIFIED"
    And Provide the refilled quantity for updating refill task 15
    And Give the comments for updating refill task by auditor "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "400"

  @URTA
  Scenario: Update Refill Task by Auditor without status
    And Get the refillId to update refill task
    And Provide the refilled quantity for updating refill task 15
    And Give the comments for updating refill task by auditor "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "400"

  @URTA
  Scenario: Update Refill Task by Auditor without refilledQuantity field
    And Get the refillId to update refill task
    And Give the status to update the refill task "VERIFIED"
    And Give the comments for updating refill task by auditor "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "400"

#  @URTR
#  Scenario: Update Refill Task by Refiller with refilledQuantity more than requestedQuantity
#    And Get the refillId to update refill task
#    And Give the status to update the refill task "COMPLETED"
#    And Provide the refilled quantity for updating refill task 30
#    And Give the comments for updating refill task by auditor "checking the update refill task functionality"
#    When Requester calls the Update Refill Task's endpoint to update refill task
#    Then Verify that status code be equal to "400"

#  @URTR
#  Scenario: Update Refill Task by Refiller with negative refilledQuantity
#    And Get the refillId to update refill task
#    And Give the status to update the refill task "COMPLETED"
#    And Provide the refilled quantity for updating refill task -15
#    And Give the comments for updating refill task by auditor "checking the update refill task functionality"
#    When Requester calls the Update Refill Task's endpoint to update refill task
#    Then Verify that status code be equal to "400"

#  @URTR
#  Scenario: Re-Update COMPLETED Refill Task by Refiller with new refilledQuantity
#    And Get the refillId to update refill task
#    And Give the status to update the refill task "COMPLETED"
#    And Provide the refilled quantity for updating refill task 14
#    And Give the comments for updating refill task by auditor "checking the update refill task functionality"
#    When Requester calls the Update Refill Task's endpoint to update refill task
#    Then Verify that status code be equal to "400"

  @URTA
  Scenario Outline: Update Refill Task by auditor with invalid refillId
    And Get the refillId to update refill task "<refillId>"
    And Give the status to update the refill task "VERIFIED"
    And Provide the refilled quantity for updating refill task 15
    And Give the comments for updating refill task by auditor "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "400"
    Examples:
      | refillId                                            |
      |                                                     |
      | null                                                |
      | 888c2-685a-a5bf-4d4-654a-q67e-345d-769a-2dc38v022b3 |

  @URTA
  Scenario Outline: Update Refill Task by Auditor with invalid status
    And Get the refillId to update refill task
    And Give the status to update the refill task "<status>"
    And Provide the refilled quantity for updating refill task 15
    And Give the comments for updating refill task by auditor "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "400"
    Examples:
      | status   |
      |          |
      | null     |
      | CLOSED   |
      | VERIFIED |
      | UPDATED  |

  @URTA
  Scenario: Update Refill Task by Auditor of other siteID
    And Get the refillId to update refill task
    And Give the status to update the refill task "VERIFIED"
    And Provide the refilled quantity for updating refill task 15
    And Give the comments for updating refill task by auditor "checking the update refill task functionality"
    When Requester calls the Update Refill Task's endpoint to update refill task
    Then Verify that status code be equal to "401"

