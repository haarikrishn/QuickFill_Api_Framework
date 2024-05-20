Feature: Verify the functionality of Create Priceboard task

  Background: Generate access token from username and password
    Given Give Username and Password to get Access Token
      | username | 6789014     |
      | password | Dmart@12345 |

#  @PriceboardTask @UPTR @UF
  Scenario: Create Priceboard Task with all the fields and valid data
    And Get the unique requestId to create Priceboard Task
    And Get the requestAt time to created Priceboard Task
    And Provide the priceboards for a Priceboard task
      | priceBoard                   | pbStatus |
      | A4/2 - Half page price board | false    |
    And Provide article details to create Priceboard task
      | articleName  | Dabur Gulabari Pr Rose Watr (250ml) |
      | articlePrice | 1099                                |
      | ean          | 8901207040801                       |
      | categoryCode | 4                                   |
      | articleCode  | 400000995                           |
      | articleMrp   | 1080                                |
    And Give requester comments for a Priceboard task
      | requesterComments | Testing the priceboard task |
    And Give task type for a new task to be created
      | taskType | PRICEBOARD |
    When Requester call the Create Task endpoint to create Priceboard task
    Then verify that status code be equal to "201"
    And Verify that Create Task schema should be equal to "CreatePriceBoardTaskSchema.json"
    And Check the response time for the Priceboard Task created
    And Verify that Priceboard Task is created successfully

#  @PriceboardTask @UPTR @UF
  Scenario: Create Priceboard Task with multiple priceboards
    And Get the unique requestId to create Priceboard Task
    And Get the requestAt time to created Priceboard Task
    And Provide the priceboards for a Priceboard task
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | false    |
      | A4/9 - Cosmetic small price board | false    |
#      | A4/2 - Half page price board      | false    |
#      | A4/4 - Medium price board         | false    |
    And Provide article details to create Priceboard task
      | articleName  | Dabur Gulabari Pr Rose Watr (250ml) |
      | articlePrice | 1099                                |
      | ean          | 8901207040801                       |
      | categoryCode | 4                                   |
      | articleCode  | 400000995                           |
      | articleMrp   | 1080                                |
    And Give requester comments for a Priceboard task
      | requesterComments | Testing the priceboard task |
    And Give task type for a new task to be created
      | taskType | PRICEBOARD |
    When Requester call the Create Task endpoint to create Priceboard task
    Then verify that status code be equal to "201"
    And Verify that Create Task schema should be equal to "CreatePriceBoardTaskSchema.json"
    And Check the response time for the Priceboard Task created
    And Verify that Priceboard Task is created successfully

#  @PriceboardTask
  Scenario: Create Duplicate Priceboard Task
    And Get the unique requestId to create Priceboard Task
    And Get the requestAt time to created Priceboard Task
    And Provide the priceboards for a Priceboard task
      | priceBoard                   | pbStatus |
      | A4/2 - Half page price board | false    |
    And Provide article details to create Priceboard task
      | articleName  | Dabur Gulabari Pr Rose Watr (250ml) |
      | articlePrice | 1099                                |
      | ean          | 8901207040801                       |
      | categoryCode | 4                                   |
      | articleCode  | 400000995                           |
      | articleMrp   | 1080                                |
    And Give requester comments for a Priceboard task
      | requesterComments | Testing the priceboard task |
    And Give task type for a new task to be created
      | taskType | PRICE BOARD |
    When Requester call the Create Task endpoint to create Priceboard task
    Then verify that status code be equal to "201"
    And Verify that Create Task schema should be equal to "CreatePriceBoardTaskSchema.json"
    And Check the response time for the Priceboard Task created
    And Verify that Priceboard Task is created successfully

#  @PriceboardTask
  Scenario: Create Priceboard Task with mandatory fields only
    And Get the unique requestId to create Priceboard Task
    And Get the requestAt time to created Priceboard Task
    And Provide the priceboards for a Priceboard task
      | priceBoard                   | pbStatus |
      | A4/2 - Half page price board | false    |
    And Provide article details to create Priceboard task
      | articleName  | Real Masala Mixed Fruit Juice-1ltr |
      | articlePrice | 182                                |
      | ean          | 8901888008008                      |
      | categoryCode | 4                                  |
      | articleCode  | 400028341                          |
      | articleMrp   | 178                                |
    And Give task type for a new task to be created
      | taskType | PRICE BOARD |
    When Requester call the Create Task endpoint to create Priceboard task
    Then verify that status code be equal to "201"
    And Verify that Create Task schema should be equal to "CreatePriceBoardTaskSchema.json"
    And Check the response time for the Priceboard Task created
    And Verify that Priceboard Task is created successfully

#  @PriceboardTask
  Scenario: Create Priceboard Task without priceBoards field
    And Get the unique requestId to create Priceboard Task
    And Get the requestAt time to created Priceboard Task
    And Provide article details to create Priceboard task
      | articleName  | Real Cranberry Nectar-1 L |
      | articlePrice | 267                       |
      | ean          | 8901207021367             |
      | categoryCode | 4                         |
      | articleCode  | 400002861                 |
      | articleMrp   | 277                       |
    And Give task type for a new task to be created
      | taskType | PRICE BOARD |
    When Requester call the Create Task endpoint to create Priceboard task
    Then verify that status code be equal to "400"

#  @PriceboardTask
  Scenario: Create Priceboard Task without priceBoard and pbStatus in priceBoards field
    And Get the unique requestId to create Priceboard Task
    And Get the requestAt time to created Priceboard Task
    And Provide empty priceboards for a Priceboard task
    And Provide article details to create Priceboard task
      | articleName  | Dabur Gulabari Pr Rose Watr (250ml) |
      | articlePrice | 1099                                |
      | ean          | 8901207040801                       |
      | categoryCode | 4                                   |
      | articleCode  | 400000995                           |
      | articleMrp   | 1080                                |
    And Give requester comments for a Priceboard task
      | requesterComments | Testing the priceboard task |
    And Give task type for a new task to be created
      | taskType | PRICE BOARD |
    When Requester call the Create Task endpoint to create Priceboard task
    Then verify that status code be equal to "400"

#  @PriceboardTask
  Scenario: Create Priceboard Task without priceBoard in priceBoards field
    And Get the unique requestId to create Priceboard Task
    And Get the requestAt time to created Priceboard Task
    And Provide only pbStatus or priceboard in priceBoards for a Priceboard task
      | pbStatus |
      | false    |
      | false    |
    And Provide article details to create Priceboard task
      | articleName  | Dabur Orange Glucoplus-C Jar(400g) |
      | articlePrice | 800                                |
      | ean          | 8901207020001                      |
      | categoryCode | 4                                  |
      | articleCode  | 400020723                          |
      | articleMrp   | 790                                |
    And Give requester comments for a Priceboard task
      | requesterComments | Testing the priceboard task |
    And Give task type for a new task to be created
      | taskType | PRICE BOARD |
    When Requester call the Create Task endpoint to create Priceboard task
    Then verify that status code be equal to "400"

#  @PriceboardTask
  Scenario: Create Priceboard Task without pbStatus in priceBoards field
    And Get the unique requestId to create Priceboard Task
    And Get the requestAt time to created Priceboard Task
    And Provide only pbStatus or priceboard in priceBoards for a Priceboard task
      | priceBoard                        |
      | A4/2 - Half page price board      |
      | A4/9 - Cosmetic small price board |
    And Provide article details to create Priceboard task
      | articleName  | Dabur Orange Glucoplus-C Jar(400g) |
      | articlePrice | 800                                |
      | ean          | 8901207020001                      |
      | categoryCode | 4                                  |
      | articleCode  | 400020723                          |
      | articleMrp   | 790                                |
    And Give requester comments for a Priceboard task
      | requesterComments | Testing the priceboard task |
    And Give task type for a new task to be created
      | taskType | PRICE BOARD |
    When Requester call the Create Task endpoint to create Priceboard task
    Then verify that status code be equal to "400"

#  @PriceboardTask
  Scenario: Create Priceboard Task with pbStatus as true
    And Get the unique requestId to create Priceboard Task
    And Get the requestAt time to created Priceboard Task
    And Provide the priceboards for a Priceboard task
      | priceBoard                   | pbStatus |
      | A4/2 - Half page price board | true     |
    And Provide article details to create Priceboard task
      | articleName  | Real Fruit Power Apple Tetr-1ltr) |
      | articlePrice | 135                               |
      | ean          | 8901888007353                     |
      | categoryCode | 4                                 |
      | articleCode  | 400013136                         |
      | articleMrp   | 128                               |
    And Give requester comments for a Priceboard task
      | requesterComments | Testing the priceboard task |
    And Give task type for a new task to be created
      | taskType | PRICE BOARD |
    When Requester call the Create Task endpoint to create Priceboard task
    Then verify that status code be equal to "400"

#  @PriceboardTask
  Scenario: Create Priceboard Task with quantity field
    And Get the unique requestId to create Priceboard Task
    And Get the requestAt time to created Priceboard Task
    And Provide the priceboards for a Priceboard task
      | priceBoard                   | pbStatus |
      | A4/2 - Half page price board | false    |
    And Provide article details to create Priceboard task
      | articleName  | Real Fruit Power Apple Tetr-1ltr) |
      | articlePrice | 135                               |
      | ean          | 8901888007353                     |
      | categoryCode | 4                                 |
      | articleCode  | 400013136                         |
      | articleMrp   | 128                               |
    And Provide the quantity for a Priceboard task
      | requestedQuantity | 15 |
    And Give requester comments for a Priceboard task
      | requesterComments | Testing the priceboard task |
    And Give task type for a new task to be created
      | taskType | PRICE BOARD |
    When Requester call the Create Task endpoint to create Priceboard task
    Then verify that status code be equal to "400"
