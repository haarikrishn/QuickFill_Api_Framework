Feature: Verify the functionality of Create Refill task

  Background: Generate access token from username and password
    Given Give Username and Password to get Access Token
      | username | 6789014     |
      | password | Dmart@12345 |

#  @RefillTask @URTR @UF
  Scenario: Create Refill Task with all the fields and valid data
    And Get the unique requestId to create Refill Task
    And Get the requestAt time to created Refill Task
    And Provide article details to create Refill task
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | articlePrice | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | articleMrp   | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task endpoint to create Refill task
    Then verify that status code be equal to "201"
    And Verify that Create Task schema should be equal to "CreateRefillTask.json"
    And Check the response time for the Refill Task created
    And Verify that Refill Task is created successfully

#  @RefillTask @URTR @UF
  Scenario: Create Refill Task with mandatory fields only
    And Get the unique requestId to create Refill Task
    And Get the requestAt time to created Refill Task
    And Provide article details to create Refill task
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | articlePrice | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | articleMrp   | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task
      | requestedQuantity | 15 |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task endpoint to create Refill task
    Then verify that status code be equal to "201"
    And Verify that Create Task schema should be equal to "CreateRefillTask.json"
    And Check the response time for the Refill Task created
    And Verify that Refill Task is created successfully

#  @RefillTask @URTR @UF
  Scenario: Create Duplicate Refill Task
    And Get the unique requestId to create Refill Task
    And Get the requestAt time to created Refill Task
    And Provide article details to create Refill task
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | articlePrice | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | articleMrp   | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task
      | requestedQuantity | 15 |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task endpoint to create Refill task
    Then verify that status code be equal to "201"
    And Verify that Create Task schema should be equal to "CreateRefillTask.json"
    And Check the response time for the Refill Task created
    And Verify that Refill Task is created successfully

#  @RefillTask
  Scenario: Create Refill Task without articleDetails field
    And Get the unique requestId to create Refill Task
    And Get the requestAt time to created Refill Task
    And Provide the quantity for a Refill task
      | requestedQuantity | 15 |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task endpoint to create Refill task
    Then verify that status code be equal to "400"

#  @RefillTask
  Scenario: Create Refill Task with priceboard feild
    And Get the unique requestId to create Refill Task
    And Get the requestAt time to created Refill Task
    And Provide the priceboards for a task
      | priceBoard                        | pbStatus |
      | A4/2 - Half page price board      | false    |
      | A4/9 - Cosmetic small price board | false    |
    And Provide article details to create Refill task
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | articlePrice | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | articleMrp   | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task endpoint to create Refill task
    Then verify that status code be equal to "400"

#  @RefillTask
  Scenario: Create Refill Task with refilledQuantity field
    And Get the unique requestId to create Refill Task
    And Get the requestAt time to created Refill Task
    And Provide article details to create Refill task
      | articleName  | Real Masala Guava Juice-1l |
      | articlePrice | 235                               |
      | ean          | 8901888008022                     |
      | categoryCode | 4                                 |
      | articleCode  | 400026970                         |
      | articleMrp   | 230                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task
      | refilledQuantity | 15 |
    And Give requester comments for a Refill task
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task endpoint to create Refill task
    Then verify that status code be equal to "400"

#  @RefillTask
  Scenario: Create Task without uom and caselot in articleDetails field
    And Get the unique requestId to create Refill Task
    And Get the requestAt time to created Refill Task
    And Provide article details to create Refill task
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | articlePrice | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | articleMrp   | 800                               |
    And Provide the quantity for a Refill task
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task endpoint to create Refill task
    Then verify that status code be equal to "400"

#  @RefillTask
  Scenario: Create Refill Task without requestedQuantity
    And Get the unique requestId to create Refill Task
    And Get the requestAt time to created Refill Task
    And Provide article details to create Refill task
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | articlePrice | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | articleMrp   | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Give requester comments for a Refill task
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task endpoint to create Refill task
    Then verify that status code be equal to "400"