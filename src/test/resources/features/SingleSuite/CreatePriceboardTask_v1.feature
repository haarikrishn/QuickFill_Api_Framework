Feature: Verify the functionality of Create Task by creating Refill Task from v1 endpoint

  Background: Generate access token from username and password
    Given Give Username and Password to get Access Token
      | username | 6789014     |
      | password | Dmart@12345 |

#  @PriceboardTask
  Scenario: Create Priceboard Task with all the fields and valid data from v1 endpoint
    And Get the unique requestId to create Priceboard Task from v1 endpoint
    And Get the requestAt time to created Priceboard Task from v1 endpoint
    And Provide the priceboards for a Priceboard task to be created from v1 endpoint
      | priceBoard                   | pbStatus |
      | A4/2 - Half page price board | false    |
    And Provide article details to create Priceboard task from v1 endpoint
      | articleName  | Dabur Gulabari Pr Rose Watr (250ml) |
      | sp           | 1099                                |
      | ean          | 8901207040801                       |
      | categoryCode | 4                                   |
      | articleCode  | 400000995                           |
      | mrp          | 1080                                |
    And Give requester comments for a Priceboard task from v1 endpoint
      | requesterComments | Testing the priceboard task |
    And Give task type for a new task to be created
      | taskType | PRICEBOARD |
    When Requester call the Create Task v1 endpoint to create Priceboard task
    Then verify that status code be equal to "201"
    And Check the response time for the Priceboard Task created from v1 endpoint
    And Verify that Priceboard Task is created successfully from v1 endpoint

  @PriceboardTask @UPTR @UF
  Scenario: Create Priceboard Task with multiple priceboards from v1 endpoint
    And Get the unique requestId to create Priceboard Task from v1 endpoint
    And Get the requestAt time to created Priceboard Task from v1 endpoint
    And Provide the priceboards for a Priceboard task to be created from v1 endpoint
      | priceBoard                   | pbStatus |
      | A4/1 - Full page price board | false    |
      | A4/9 - Cosmetic small price board | false    |
#      | A4/2 - Half page price board      | false    |
#      | A4/4 - Medium price board         | false    |
    And Provide article details to create Priceboard task from v1 endpoint
      | articleName  | Dabur Gulabari Pr Rose Watr (250ml) |
      | sp           | 109 9                               |
      | ean          | 8901207040801                       |
      | categoryCode | 4                                   |
      | articleCode  | 400000995                           |
      | mrp          | 1080                                |
    And Give requester comments for a Priceboard task from v1 endpoint
      | requesterComments | Testing the priceboard task |
    And Give task type for a new task to be created
      | taskType | PRICEBOARD |
    When Requester call the Create Task v1 endpoint to create Priceboard task
    Then verify that status code be equal to "201"
    And Check the response time for the Priceboard Task created from v1 endpoint
    And Verify that Priceboard Task is created successfully from v1 endpoint

#  @PriceboardTask
  Scenario: Create Duplicate Priceboard Task from v1 endpoint
    And Get the unique requestId to create Priceboard Task from v1 endpoint
    And Get the requestAt time to created Priceboard Task from v1 endpoint
    And Provide the priceboards for a Priceboard task to be created from v1 endpoint
      | priceBoard                   | pbStatus |
      | A4/2 - Half page price board | false    |
    And Provide article details to create Priceboard task from v1 endpoint
      | articleName  | Dabur Gulabari Pr Rose Watr (250ml) |
      | sp           | 1099                                |
      | ean          | 8901207040801                       |
      | categoryCode | 4                                   |
      | articleCode  | 400000995                           |
      | mrp          | 1080                                |
    And Give requester comments for a Priceboard task from v1 endpoint
      | requesterComments | Testing the priceboard task |
    And Give task type for a new task to be created
      | taskType | PRICE BOARD |
    When Requester call the Create Task v1 endpoint to create Priceboard task
    Then verify that status code be equal to "201"
    And Check the response time for the Priceboard Task created from v1 endpoint
    And Verify that Priceboard Task is created successfully from v1 endpoint

#  @PriceboardTask
  Scenario: Create Priceboard Task with mandatory fields only from v1 endpoint
    And Get the unique requestId to create Priceboard Task from v1 endpoint
    And Get the requestAt time to created Priceboard Task from v1 endpoint
    And Provide the priceboards for a Priceboard task to be created from v1 endpoint
      | priceBoard                   | pbStatus |
      | A4/2 - Half page price board | false    |
    And Provide article details to create Priceboard task from v1 endpoint
      | articleName  | Real Masala Mixed Fruit Juice-1ltr |
      | sp           | 182                                |
      | ean          | 8901888008008                      |
      | categoryCode | 4                                  |
      | articleCode  | 400028341                          |
      | mrp          | 178                                |
    And Give task type for a new task to be created
      | taskType | PRICE BOARD |
    When Requester call the Create Task v1 endpoint to create Priceboard task
    Then verify that status code be equal to "201"
    And Check the response time for the Priceboard Task created from v1 endpoint
    And Verify that Priceboard Task is created successfully from v1 endpoint

#  @PriceboardTask
  Scenario: Create Priceboard Task without priceBoards field from v1 endpoint
    And Get the unique requestId to create Priceboard Task from v1 endpoint
    And Get the requestAt time to created Priceboard Task from v1 endpoint
    And Provide article details to create Priceboard task from v1 endpoint
      | articleName  | Real Cranberry Nectar-1 L |
      | sp           | 267                       |
      | ean          | 8901207021367             |
      | categoryCode | 4                         |
      | articleCode  | 400002861                 |
      | mrp          | 277                       |
    And Give task type for a new task to be created
      | taskType | PRICE BOARD |
    When Requester call the Create Task v1 endpoint to create Priceboard task
    Then verify that status code be equal to "400"

#  @PriceboardTask
  Scenario: Create Priceboard Task without priceBoard and pbStatus in priceBoards field from v1 endpoint
    And Get the unique requestId to create Priceboard Task from v1 endpoint
    And Get the requestAt time to created Priceboard Task from v1 endpoint
    And Provide empty priceboards for a Priceboard task in v1 endpoint
    And Provide article details to create Priceboard task from v1 endpoint
      | articleName  | Dabur Gulabari Pr Rose Watr (250ml) |
      | articlePrice | 1099                                |
      | ean          | 8901207040801                       |
      | categoryCode | 4                                   |
      | articleCode  | 400000995                           |
      | mrp          | 1080                                |
    And Give requester comments for a Priceboard task from v1 endpoint
      | requesterComments | Testing the priceboard task |
    And Give task type for a new task to be created
      | taskType | PRICE BOARD |
    When Requester call the Create Task v1 endpoint to create Priceboard task
    Then verify that status code be equal to "400"

#  @PriceboardTask
  Scenario: Create Priceboard Task without priceBoard in priceBoards field from v1 endpoint
    And Get the unique requestId to create Priceboard Task from v1 endpoint
    And Get the requestAt time to created Priceboard Task from v1 endpoint
    And Provide only pbStatus or priceboard in priceBoards for a Priceboard task to be created from v1 endpoint
      | pbStatus |
      | false    |
      | false    |
    And Provide article details to create Priceboard task from v1 endpoint
      | articleName  | Dabur Orange Glucoplus-C Jar(400g) |
      | sp           | 800                                |
      | ean          | 8901207020001                      |
      | categoryCode | 4                                  |
      | articleCode  | 400020723                          |
      | mrp          | 790                                |
    And Give requester comments for a Priceboard task from v1 endpoint
      | requesterComments | Testing the priceboard task |
    And Give task type for a new task to be created
      | taskType | PRICE BOARD |
    When Requester call the Create Task v1 endpoint to create Priceboard task
    Then verify that status code be equal to "400"

#  @PriceboardTask
  Scenario: Create Priceboard Task without pbStatus in priceBoards field from v1 endpoint
    And Get the unique requestId to create Priceboard Task from v1 endpoint
    And Get the requestAt time to created Priceboard Task from v1 endpoint
    And Provide only pbStatus or priceboard in priceBoards for a Priceboard task to be created from v1 endpoint
      | priceBoard                        |
      | A4/2 - Half page price board      |
      | A4/9 - Cosmetic small price board |
    And Provide article details to create Priceboard task from v1 endpoint
      | articleName  | Dabur Orange Glucoplus-C Jar(400g) |
      | sp           | 800                                |
      | ean          | 8901207020001                      |
      | categoryCode | 4                                  |
      | articleCode  | 400020723                          |
      | mrp          | 790                                |
    And Give requester comments for a Priceboard task from v1 endpoint
      | requesterComments | Testing the priceboard task |
    And Give task type for a new task to be created
      | taskType | PRICE BOARD |
    When Requester call the Create Task v1 endpoint to create Priceboard task
    Then verify that status code be equal to "400"

#  @PriceboardTask
  Scenario: Create Priceboard Task with pbStatus as true from v1 endpoint
    And Get the unique requestId to create Priceboard Task from v1 endpoint
    And Get the requestAt time to created Priceboard Task from v1 endpoint
    And Provide the priceboards for a Priceboard task to be created from v1 endpoint
      | priceBoard                   | pbStatus |
      | A4/2 - Half page price board | true     |
    And Provide article details to create Priceboard task from v1 endpoint
      | articleName  | Dabur Gulabari Pr Rose Watr (250ml) |
      | sp           | 1099                                |
      | ean          | 8901207040801                       |
      | categoryCode | 4                                   |
      | articleCode  | 400000995                           |
      | mrp          | 1080                                |
    And Give requester comments for a Priceboard task from v1 endpoint
      | requesterComments | Testing the priceboard task |
    And Give task type for a new task to be created
      | taskType | PRICEBOARD |
    When Requester call the Create Task v1 endpoint to create Priceboard task
    Then verify that status code be equal to "400"