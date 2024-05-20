Feature: Verify the functionality of Create Task by creating task with different Roles

  @RefillRequester
  Scenario: Create Task by employee in Refill Requester Role
    Given Give Username and Password to get Access Token
      | username | 678910      |
      | password | Dmart@12345 |
    And Get the unique requestId to create Refill Task
    And Get the requestAt time to created Refill Task
    And Provide article details to create Refill task
      | articleName  | Dabur Gulabari Pr Rose Watr (250ml) |
      | articlePrice | 1099                                |
      | ean          | 8901207040801                       |
      | categoryCode | 4                                   |
      | articleCode  | 400000995                           |
      | articleMrp   | 1080                                |
      | uom          | BOXES                               |
      | caselot      | 10                                  |
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

  @RefillRequesterAuditor
  Scenario: Create Task by employee in Refill Requester and Refill Auditor Role
    Given Give Username and Password to get Access Token
      | username | 6789014     |
      | password | Dmart@12345 |
    And Get the unique requestId to create Priceboard Task
    And Get the requestAt time to created Priceboard Task
    And Provide the priceboards for a Priceboard task
      | priceBoard                   | pbStatus |
      | A4/2 - Half page price board | false    |
    And Provide article details to create Priceboard task
      | articleName  | Real Mix Fruit(180ml) |
      | articlePrice | 153                   |
      | ean          | 8901888007780         |
      | categoryCode | 4                     |
      | articleCode  | 400002875             |
      | articleMrp   | 145                   |
    And Give requester comments for a Priceboard task
      | requesterComments | Testing the priceboard task |
    And Give task type for a new task to be created
      | taskType | PRICE BOARD |
    When Requester call the Create Task endpoint to create Priceboard task
    Then verify that status code be equal to "201"
    And Verify that Create Task schema should be equal to "CreatePriceBoardTaskSchema.json"
    And Check the response time for the Priceboard Task created
    And Verify that Priceboard Task is created successfully

  @Refiller
  Scenario: Create Task by employee in Refiller Role
    Given Give Username and Password to get Access Token
      | username | 797978    |
      | password | Dmart@123 |
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
    Then verify that status code be equal to "401"

  @RefillerAuditor
  Scenario: Create Task by employee in Refill Auditor Role
    Given Give Username and Password to get Access Token
      | username | 678787    |
      | password | Dmart@123 |
    And Get the unique requestId to create Others Task
    And Get the requestAt time to created Others Task
    And Provide article details to create Others task
      | articleName  | Real Litchi Nectar(180ml) |
      | articlePrice | 239                       |
      | ean          | 8901207032158             |
      | categoryCode | 4                         |
      | articleCode  | 400002867                 |
      | articleMrp   | 234                       |
    And Give comments for a Others task
      | requesterComments | Testing the others task |
    And Give task type for a new task to be created
      | taskType | OTHERS |
    When Requester call the Create Task endpoint to create Others task
    Then verify that status code be equal to "401"



