Feature: Verify the functionality of Update Others Task by Auditor

  Background: Generate access token from username and password
    Given Give Username and Password to get Access Token
      | username | 678787    |
      | password | Dmart@123 |

  @UOTA
  Scenario: Update OTHERS Task by Auditor with valid data
    And Get the refillId to update others task
    And Give the status to update the others task "VERIFIED"
    And Give the comments for updating others task by auditor "checking the update others task functionality"
    When Requester calls the Update Others Task's endpoint to update others task
    Then Verify that status code be equal to "200"
    And Check the response time for the Others Task updated
    And Verify that others task is updated successfully by Auditor

  @UOTA
  Scenario: Update OTHERS Task by Auditor without closing FloorWalk
    And Get the refillId to update others task
    And Give the status to update the others task "VERIFIED"
    And Give the comments for updating others task by auditor "checking the update others task functionality"
    When Requester calls the Update Others Task's endpoint to update others task
    Then Verify that status code be equal to "200"
    And Check the response time for the Others Task updated
    And Verify that others task is updated successfully
    And Verify that others task is updated successfully by Auditor

  @UOTA
  Scenario: Update OTHERS Task by Auditor without refillID
    And Give the status to update the others task "VERIFIED"
    And Give the comments for updating others task by auditor "checking the update others task functionality"
    When Requester calls the Update Others Task's endpoint to update others task
    Then Verify that status code be equal to "400"

  @UOTA
  Scenario: Update OTHERS Task by Auditor without status
    And Get the refillId to update others task
    And Give the comments for updating others task by auditor "checking the update others task functionality"
    When Requester calls the Update Others Task's endpoint to update others task
    Then Verify that status code be equal to "400"

  @UOTA
  Scenario Outline: Update OTHERS Task by Auditor with invalid refillId
    And Get the refillId to update others task "<refillId>"
    And Give the status to update the others task "VERIFIED"
    And Give the comments for updating others task by auditor "checking the update others task functionality"
    When Requester calls the Update Others Task's endpoint to update others task
    Then Verify that status code be equal to "400"
  Examples:
    | refillId                                           |
    |                                                    |
    | null                                               |
    | 888c2-685a-a5bf-4d4-654a-q67e-345d-769a-2dc38v022b |

  @UOTA
  Scenario: Update OTHERS Task by Auditor of other siteID
    And Get the refillId to update others task
    And Give the status to update the others task "VERIFIED"
    And Give the comments for updating others task by auditor "checking the update others task functionality"
    When Requester calls the Update Others Task's endpoint to update others task
    Then Verify that status code be equal to "401"



