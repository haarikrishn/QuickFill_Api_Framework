Feature: Verify the functionality of Create Others task

  Background: Generate access token from username and password
    Given Give Username and Password to get Access Token
      | username | 6789014     |
      | password | Dmart@12345 |

  @OthersTask @UF @UOTR
  Scenario: Create OTHERS Task with all the mandatory fields and valid data from v1 endpoint
    And Get the unique requestId to create Others Task from v1 endpoint
    And Get the requestAt time to created Others Task from v1 endpoint
    And Provide article details to create Others task from v1 endpoint
      | articleName  | Dabur Gulabari Pr Rose Watr (250ml) |
      | sp           | 1099                                |
      | ean          | 8901207040801                       |
      | categoryCode | 4                                   |
      | articleCode  | 400000995                           |
      | mrp          | 1080                                |
    And Give comments for a Others task to be created from v1 endpoint
      | requesterComments | Testing the others task |
    And Give task type for a new task to be created
      | taskType | OTHERS |
    When Requester call the Create Task v1 endpoint to create Others task
    Then verify that status code be equal to "201"
    And Check the response time for the Others Task created from v1 endpoint
    And Verify that Others Task is created successfully from v1 endpoint

#  @OthersTask
  Scenario: Create Duplicate OTHERS Task from v1 endpoint
    And Get the unique requestId to create Others Task from v1 endpoint
    And Get the requestAt time to created Others Task from v1 endpoint
    And Provide article details to create Others task from v1 endpoint
      | articleName  | Dabur Gulabari Pr Rose Watr (250ml) |
      | sp           | 1099                                |
      | ean          | 8901207040801                       |
      | categoryCode | 4                                   |
      | articleCode  | 400000995                           |
      | mrp          | 1080                                |
    And Give comments for a Others task to be created from v1 endpoint
      | requesterComments | Testing the others task |
    And Give task type for a new task to be created
      | taskType | OTHERS |
    When Requester call the Create Task v1 endpoint to create Others task
    Then verify that status code be equal to "201"
    And Check the response time for the Others Task created from v1 endpoint
    And Verify that Others Task is created successfully from v1 endpoint

#  @OthersTask
  Scenario: Create OTHERS Task without requesterComments field from v1 endpoint
    And Get the unique requestId to create Others Task from v1 endpoint
    And Get the requestAt time to created Others Task from v1 endpoint
    And Provide article details to create Others task from v1 endpoint
      | articleName  | Dabur Lemoneez-250 Ml |
      | sp           | 62                    |
      | ean          | 8901888005717         |
      | categoryCode | 4                     |
      | articleCode  | 400001005             |
      | mrp          | 50                    |
    And Give task type for a new task to be created
      | taskType | OTHERS |
    When Requester call the Create Task v1 endpoint to create Others task
    Then verify that status code be equal to "400"

#  @OthersTask
  Scenario: Create OTHERS Task with refillerComments from v1 endpoint
    And Get the unique requestId to create Others Task from v1 endpoint
    And Get the requestAt time to created Others Task from v1 endpoint
    And Provide article details to create Others task from v1 endpoint
      | articleName  | Dabur Gulabari Pr Rose Watr (250ml) |
      | sp           | 1099                                |
      | ean          | 8901207040801                       |
      | categoryCode | 4                                   |
      | articleCode  | 400000995                           |
      | mrp          | 1080                                |
    And Give comments for a Others task to be created from v1 endpoint
      | refillerComments | Testing the others task with refillerComments field |
    And Give task type for a new task to be created
      | taskType | OTHERS |
    When Requester call the Create Task v1 endpoint to create Others task
    Then verify that status code be equal to "400"