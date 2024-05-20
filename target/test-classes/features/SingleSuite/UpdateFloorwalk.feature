Feature: Verify the functionality of update floorwalk

  Background: Generate access token from username and password
    Given Give Username and Password to get Access Token
      | username | 6789014     |
      | password | Dmart@12345 |

#  @UF
#  Scenario: Create Refill Task with all the fields and valid data
#    And Get the unique requestId to create Refill Task
#    And Get the requestAt time to created Refill Task
#    And Provide article details to create Refill task
#      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
#      | articlePrice | 820                               |
#      | ean          | 8901207019999                     |
#      | categoryCode | 4                                 |
#      | articleCode  | 400020722                         |
#      | articleMrp   | 800                               |
#      | uom          | BOXES                             |
#      | caselot      | 10                                |
#    And Provide the quantity for a Refill task
#      | requestedQuantity | 15 |
#    And Give requester comments for a Refill task
#      | requesterComments | Testing the refill task |
#    And Give task type for a new task to be created
#      | taskType | REFILL |
#    When Requester call the Create Task endpoint to create Refill task
#    Then Verify that status code be equal to "201"
#    And Verify that Create Task schema should be equal to "CreateRefillTask.json"
#    And Check the response time for the Refill Task created
#    And Verify that Refill Task is created successfully

#  @UF
  Scenario: Update FloorWalk with valid data
    And Get the floorwalkId to close the Floorwalk
    And Provide the time of closing the Floorwalk
    And Give the status for closing the Floorwalk "CLOSED"
    When Requester call the Update Floorwalk endpoint to close the Floorwalk
    Then Verify that status code be equal to "200"
    And Check the response time for closing the Floorwalk
    And Verify that Floorwalk is closed successfully

#  @UF
  Scenario: Update FloorWalk without completedAt
    And Get the floorwalkId to close the Floorwalk
    And Give the status for closing the Floorwalk "CLOSED"
    When Requester call the Update Floorwalk endpoint to close the Floorwalk
    Then Verify that status code be equal to "200"
    And Check the response time for closing the Floorwalk
    And Verify that Floorwalk is closed successfully

#  @UF
  Scenario: Update FloorWalk without floorwalkId
    And Provide the time of closing the Floorwalk
    And Give the status for closing the Floorwalk "CLOSED"
    When Requester call the Update Floorwalk endpoint to close the Floorwalk
    Then Verify that status code be equal to "400"

#  @UF
  Scenario: Update FloorWalk without status
    And Get the floorwalkId to close the Floorwalk
    And Provide the time of closing the Floorwalk
    When Requester call the Update Floorwalk endpoint to close the Floorwalk
    Then Verify that status code be equal to "400"

#  @UF
  Scenario: Update FloorWalk with floowalkId not generated
    And Give random floorwalkId not generated
    And Provide the time of closing the Floorwalk
    And Give the status for closing the Floorwalk "CLOSED"
    When Requester call the Update Floorwalk endpoint to close the Floorwalk
    Then Verify that status code be equal to "404"

#  @UF
  Scenario Outline: Update FloorWalk with invalid floowalkId
    And Give invalid floorwalkId "<floorwalkId>"
    And Provide the time of closing the Floorwalk
    When Requester call the Update Floorwalk endpoint to close the Floorwalk
    Then Verify that status code be equal to "400"
  Examples:
    | floorwalkId |
    |             |
    | null        |

#  @UF
  Scenario Outline: Update FloorWalk with invalid status
    And Get the floorwalkId to close the Floorwalk
    And Provide the time of closing the Floorwalk
    And Give the invalid status for closing the Floorwalk "<status>"
    When Requester call the Update Floorwalk endpoint to close the Floorwalk
    Then Verify that status code be equal to "400"
  Examples:
    | status    |
    | OPEN      |
    | COMPLETED |
    |           |
    | null      |

#  @UF
  Scenario: Update FloorWalk by again closing already closed floorwalk
    And Get the floorwalkId to close the Floorwalk
    And Provide the time of closing the Floorwalk
    And Give the status for closing the Floorwalk "CLOSED"
    When Requester call the Update Floorwalk endpoint to close the Floorwalk
    Then Verify that status code be equal to "409"



