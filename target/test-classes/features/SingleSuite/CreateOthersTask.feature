Feature: Verify the functionality of Create Others task

  Background: Generate access token from username and password
    Given Give Username and Password to get Access Token
      | username | 6789014     |
      | password | Dmart@12345 |

#  @OthersTask @UF @UOTR
  Scenario: Create OTHERS Task with all the mandatory fields and valid data
    And Get the unique requestId to create Others Task
    And Get the requestAt time to created Others Task
    And Provide article details to create Others task
      | articleName  | Dabur Gulabari Pr Rose Watr (250ml) |
      | articlePrice | 1099                                |
      | ean          | 8901207040801                       |
      | categoryCode | 4                                   |
      | articleCode  | 400000995                           |
      | articleMrp   | 1080                                |
    And Give comments for a Others task
      | requesterComments | Testing the others task |
    And Give task type for a new task to be created
      | taskType | OTHERS |
    When Requester call the Create Task endpoint to create Others task
    Then verify that status code be equal to "201"
    And Check the response time for the Others Task created
    And Verify that Create Task schema should be equal to "CreateOthersTaskSchema.json"
    And Verify that Others Task is created successfully

#  @OthersTask @UF
  Scenario: Create Duplicate OTHERS Task
    And Get the unique requestId to create Others Task
    And Get the requestAt time to created Others Task
    And Provide article details to create Others task
      | articleName  | Dabur Gulabari Pr Rose Watr (250ml) |
      | articlePrice | 1099                                |
      | ean          | 8901207040801                       |
      | categoryCode | 4                                   |
      | articleCode  | 400000995                           |
      | articleMrp   | 1080                                |
    And Give comments for a Others task
      | requesterComments | Testing the others task |
    And Give task type for a new task to be created
      | taskType | OTHERS |
    When Requester call the Create Task endpoint to create Others task
    Then verify that status code be equal to "201"
    And Check the response time for the Others Task created
    And Verify that Create Task schema should be equal to "CreateOthersTaskSchema.json"
    And Verify that Others Task is created successfully

#  @OthersTask
  Scenario: Create OTHERS Task without requesterComments field
    And Get the unique requestId to create Others Task
    And Get the requestAt time to created Others Task
    And Provide article details to create Others task
      | articleName  | Dabur Lemoneez-250 Ml |
      | articlePrice | 62                    |
      | ean          | 8901888005717         |
      | categoryCode | 4                     |
      | articleCode  | 400001005             |
      | articleMrp   | 50                    |
    And Give task type for a new task to be created
      | taskType | OTHERS |
    When Requester call the Create Task endpoint to create Others task
    Then verify that status code be equal to "400"

#  @OthersTask
  Scenario: Create OTHERS Task with refillerComments
    And Get the unique requestId to create Others Task
    And Get the requestAt time to created Others Task
    And Provide article details to create Others task
      | articleName  | Dabur Gulabari Pr Rose Watr (250ml) |
      | articlePrice | 1099                                |
      | ean          | 8901207040801                       |
      | categoryCode | 4                                   |
      | articleCode  | 400000995                           |
      | articleMrp   | 1080                                |
    And Give comments for a Others task
      | refillerComments | Testing the others task with refillerComments field |
    And Give task type for a new task to be created
      | taskType | OTHERS |
    When Requester call the Create Task endpoint to create Others task
    Then verify that status code be equal to "400"

#  @OthersTask
  Scenario: Create Others Task with priceboard and requestedQuantity feild
    And Get the unique requestId to create Others Task
    And Get the requestAt time to created Others Task
    And Provide the priceboards for Others task
      | priceBoard                        | pbStatus |
      | A4/2 - Half page price board      | false    |
      | A4/9 - Cosmetic small price board | false    |
    And Provide article details to create Others task
      | articleName  | Real Cranberry Nectar-1 L |
      | articlePrice | 267                       |
      | ean          | 8901207021367             |
      | categoryCode | 4                         |
      | articleCode  | 400002861                 |
      | articleMrp   | 277                       |
    And Provide the quantity for Others task
      | requestedQuantity | 15 |
    And Give comments for a Others task
      | requesterComments | Testing the others task |
    And Give task type for a new task to be created
      | taskType | OTHERS |
    When Requester call the Create Task endpoint to create Others task
    Then verify that status code be equal to "400"
