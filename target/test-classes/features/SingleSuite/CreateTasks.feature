Feature: Verify the functionality of Create Task

  Background: Generate access token from username and password
    Given Give Username and Password to get Access Token
      | username | 6789014     |
      | password | Dmart@12345 |

#  @Task
  Scenario: Create Task without taskType field
    And Get the unique requestId to create Refill Task
    And Get the requestAt time to created Refill Task
    And Provide article details to create Refill task
      | articleName  | Real Litchi Nectar(180ml) |
      | articlePrice | 239                       |
      | ean          | 8901207032158             |
      | categoryCode | 4                         |
      | articleCode  | 400002867                 |
      | articleMrp   | 234                       |
      | uom          | BOXES                     |
      | caselot      | 10                        |
    And Provide the quantity for a Refill task
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task
      | requesterComments | Creating Refill task without taskType field |
    When Requester call the Create Task endpoint to create Refill task
    Then verify that status code be equal to "400"

#  @Task
  Scenario Outline: Create Task with invalid ean number
    And Get the unique requestId to create a task
    And Get the requestAt time to created a task
    And Provide the details for a task to be created "<articleName>" "<articlePrice>" "<ean>" "<categoryCode>" "<articleCode>" "<articleMrp>" "<uom>" "<caselot>" "<requestedQuantity>" "<requesterComments>" "<taskType>"
    When Requester call the Create Task endpoint to create task
    Then verify that status code be equal to "400"
  Examples:
    | articleName                       | articlePrice | ean                   | categoryCode | articleCode | articleMrp | uom   | caselot | requestedQuantity | requesterComments                   | taskType |
    | Dabur Lemon Glucoplus-C Jar(400g) | 820          | 89012                 | 4            | 400020722   | 800        | BOXES | 10      | 100               | Verifying create task functionality | REFILL   |
    | Dabur Lemon Glucoplus-C Jar(400g) | 820          | 890120701999965437853 | 4            | 400020722   | 800        | BOXES | 10      | 100               | Verifying create task functionality | REFILL   |
    | Dabur Lemon Glucoplus-C Jar(400g) | 820          |                       | 4            | 400020722   | 800        | BOXES | 10      | 100               | Verifying create task functionality | REFILL   |
    | Dabur Lemon Glucoplus-C Jar(400g) | 820          | null                  | 4            | 400020722   | 800        | BOXES | 10      | 100               | Verifying create task functionality | REFILL   |

#  @Task
  Scenario Outline: Create Task with invalid categoryCode
    And Get the unique requestId to create a task
    And Get the requestAt time to created a task
    And Provide the details for a task to be created "<articleName>" "<articlePrice>" "<ean>" "<categoryCode>" "<articleCode>" "<articleMrp>" "<uom>" "<caselot>" "<requestedQuantity>" "<requesterComments>" "<taskType>"
    When Requester call the Create Task endpoint to create task
    Then verify that status code be equal to "400"
    Examples:
      | articleName                       | articlePrice | ean           | categoryCode          | articleCode | articleMrp | uom   | caselot | requestedQuantity | requesterComments                   | taskType |
      | Dabur Lemon Glucoplus-C Jar(400g) | 820          | 8901207019999 | 444444444567898765412 | 400020722   | 800        | BOXES | 10      | 100               | Verifying create task functionality | REFILL   |
      | Dabur Lemon Glucoplus-C Jar(400g) | 820          | 8901207019999 |                       | 400020722   | 800        | BOXES | 10      | 100               | Verifying create task functionality | REFILL   |
      | Dabur Lemon Glucoplus-C Jar(400g) | 820          | 8901207019999 | null                  | 400020722   | 800        | BOXES | 10      | 100               | Verifying create task functionality | REFILL   |

#  @Task
  Scenario Outline: Create Task with invalid articleCode
    And Get the unique requestId to create a task
    And Get the requestAt time to created a task
    And Provide the priceboards to create a Priceboard task
      | priceBoard                   | pbStatus |
      | A4/2 - Half page price board | false    |
    And Provide the details for a task to be created "<articleName>" "<articlePrice>" "<ean>" "<categoryCode>" "<articleCode>" "<articleMrp>" "<requesterComments>" "<taskType>"
    When Requester call the Create Task endpoint to create task
    Then verify that status code be equal to "400"
    Examples:
      | articleName           | articlePrice | ean           | categoryCode | articleCode           | articleMrp | requesterComments                   | taskType    |
      | Real Mix Fruit(180ml) | 153          | 8901888007780 | 4            | 444444444567898765412 | 145        | Verifying create task functionality | PRICE BOARD |
      | Real Mix Fruit(180ml) | 153          | 8901888007780 | 4            |                       | 145        | Verifying create task functionality | PRICE BOARD |
      | Real Mix Fruit(180ml) | 153          | 8901888007780 | 4            | null                  | 145        | Verifying create task functionality | PRICE BOARD |

#  @Task
  Scenario Outline: Create Task with invalid articleName
    And Get the unique requestId to create a task
    And Get the requestAt time to created a task
    And Provide the details for a task to be created "<articleName>" "<articlePrice>" "<ean>" "<categoryCode>" "<articleCode>" "<articleMrp>" "<requesterComments>" "<taskType>"
    When Requester call the Create Task endpoint to create task
    Then verify that status code be equal to "400"
    Examples:
      | articleName | articlePrice | ean           | categoryCode | articleCode | articleMrp | requesterComments                   | taskType |
      | D           | 1099         | 8901207040801 | 4            | 400000995   | 1080       | Verifying create task functionality | OTHERS   |
      |             | 1099         | 8901207040801 | 4            | 400000995   | 1080       | Verifying create task functionality | OTHERS   |
      | null        | 1099         | 8901207040801 | 4            | 400000995   | 1080       | Verifying create task functionality | OTHERS   |

#  @Task
  Scenario Outline: Create Task with invalid articlePrice
    And Get the unique requestId to create a task
    And Get the requestAt time to created a task
    And Provide the details for a task to be created "<articleName>" "<articlePrice>" "<ean>" "<categoryCode>" "<articleCode>" "<articleMrp>" "<uom>" "<caselot>" "<requestedQuantity>" "<requesterComments>" "<taskType>"
    When Requester call the Create Task endpoint to create task
    Then verify that status code be equal to "400"
    Examples:
      | articleName                       | articlePrice | ean           | categoryCode | articleCode | articleMrp | uom   | caselot | requestedQuantity | requesterComments                   | taskType |
      | Dabur Lemon Glucoplus-C Jar(400g) |              | 8901207019999 | 4            | 400020722   | 800        | BOXES | 10      | 100               | Verifying create task functionality | REFILL   |
      | Dabur Lemon Glucoplus-C Jar(400g) | null         | 8901207019999 | 4            | 400020722   | 800        | BOXES | 10      | 100               | Verifying create task functionality | REFILL   |

#  @Task
  Scenario Outline: Create Task with invalid articleMrp
    And Get the unique requestId to create a task
    And Get the requestAt time to created a task
    And Provide the details for a task to be created "<articleName>" "<articlePrice>" "<ean>" "<categoryCode>" "<articleCode>" "<articleMrp>" "<uom>" "<caselot>" "<requestedQuantity>" "<requesterComments>" "<taskType>"
    When Requester call the Create Task endpoint to create task
    Then verify that status code be equal to "400"
    Examples:
      | articleName                       | articlePrice | ean           | categoryCode | articleCode | articleMrp | uom   | caselot | requestedQuantity | requesterComments                   | taskType |
      | Dabur Lemon Glucoplus-C Jar(400g) | 820          | 8901207019999 | 4            | 400020722   |            | BOXES | 10      | 100               | Verifying create task functionality | REFILL   |
      | Dabur Lemon Glucoplus-C Jar(400g) | 820          | 8901207019999 | 4            | 400020722   | null       | BOXES | 10      | 100               | Verifying create task functionality | REFILL   |

#  @Task
  Scenario Outline: Create Task with invalid taskType
    And Get the unique requestId to create a task
    And Get the requestAt time to created a task
    And Provide the details for a task to be created "<articleName>" "<articlePrice>" "<ean>" "<categoryCode>" "<articleCode>" "<articleMrp>" "<requesterComments>" "<taskType>"
    When Requester call the Create Task endpoint to create task
    Then verify that status code be equal to "400"
    Examples:
      | articleName                         | articlePrice | ean           | categoryCode | articleCode | articleMrp | requesterComments                   | taskType                                            |
      | Dabur Gulabari Pr Rose Watr (250ml) | 1099         | 8901207040801 | 4            | 400000995   | 1080       | Verifying create task functionality | REFILLQWERTYUIOPASDFGHJKLZXCVBNMQSDCVPOJBZXFGHJOPMB |
      | Dabur Gulabari Pr Rose Watr (250ml) | 1099         | 8901207040801 | 4            | 400000995   | 1080       | Verifying create task functionality |                                                     |
      | Dabur Gulabari Pr Rose Watr (250ml) | 1099         | 8901207040801 | 4            | 400000995   | 1080       | Verifying create task functionality | null                                                |
      | Dabur Gulabari Pr Rose Watr (250ml) | 1099         | 8901207040801 | 4            | 400000995   | 1080       | Verifying create task functionality | @#$%/                                               |

#  @Task
  Scenario: Create Task without requestId field
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
    Then verify that status code be equal to "400"

#  @Task
  Scenario: Create task without requestedAt field
    And Get the unique requestId to create Refill Task
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

#  @Task
  Scenario: Create Task without articleDetails field
    And Get the unique requestId to create Refill Task
    And Get the requestAt time to created Refill Task
    And Provide the quantity for a Refill task
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task endpoint to create Refill task
    Then verify that status code be equal to "400"

  @Task
  Scenario: Create Task without articlePrice in articleDetails field
    And Get the unique requestId to create Refill Task
    And Get the requestAt time to created Refill Task
    And Provide article details to create Refill task
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
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

  @Task
  Scenario: Create Task without articleName in articleDetails field
    And Get the unique requestId to create Refill Task
    And Get the requestAt time to created Refill Task
    And Provide article details to create Refill task
      | articlePrice | 820           |
      | ean          | 8901207019999 |
      | categoryCode | 4             |
      | articleCode  | 400020722     |
      | articleMrp   | 800           |
      | uom          | BOXES         |
      | caselot      | 10            |
    And Provide the quantity for a Refill task
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task endpoint to create Refill task
    Then verify that status code be equal to "400"

  @Task
  Scenario: Create Task without ean in articleDetails field
    And Get the unique requestId to create Refill Task
    And Get the requestAt time to created Refill Task
    And Provide article details to create Refill task
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | articlePrice | 820                               |
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

  @Task
  Scenario: Create Task without categoryCode in articleDetails field
    And Get the unique requestId to create Refill Task
    And Get the requestAt time to created Refill Task
    And Provide article details to create Refill task
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | articlePrice | 820                               |
      | ean          | 8901207019999                     |
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

  @Task
  Scenario: Create Task without articleCode in articleDetails field
    And Get the unique requestId to create Refill Task
    And Get the requestAt time to created Refill Task
    And Provide article details to create Refill task
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | articlePrice | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
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

  @Task
  Scenario: Create Task without articleMrp in articleDetails field
    And Get the unique requestId to create Refill Task
    And Get the requestAt time to created Refill Task
    And Provide article details to create Refill task
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | articlePrice | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
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

#  @Task
  Scenario: Create Task without taskType field
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
    When Requester call the Create Task endpoint to create Priceboard task
    Then verify that status code be equal to "400"

#  @Task
  Scenario Outline: Create Task with invalid requestId
    And Get the invalid requestId "<requestId>"
    And Get the requestAt time to created Task
    And Provide article details to create task
      | articleName  | Dabur Gulabari Pr Rose Watr (250ml) |
      | articlePrice | 1099                                |
      | ean          | 8901207040801                       |
      | categoryCode | 4                                   |
      | articleCode  | 400000995                           |
      | articleMrp   | 1080                                |
    And Give comments for a task
      | requesterComments | Testing the others task |
    And Give task type for a new task to be created
      | taskType | OTHERS |
    When Requester call the Create Task endpoint to create task
    Then verify that status code be equal to "400"
  Examples:
    | requestId                                           |
    |                                                     |
    | 888c2-685a-a5bf-4d4-654a-q67e-345d-769a-2dc38v022b3 |
    | 888c2-685                                           |
    | null                                                |

#  @Task
  Scenario Outline: Create Task with invalid requestedAt field
    And Get the unique requestId to create a task
    And Get the invalid requestedAt time "<requestedAt>"
    And Provide the priceboards to create a Priceboard task
      | priceBoard                   | pbStatus |
      | A4/2 - Half page price board | false    |
    And Provide article details to create task
      | articleName  | Dabur Gulabari Pr Rose Watr (250ml) |
      | articlePrice | 1099                                |
      | ean          | 8901207040801                       |
      | categoryCode | 4                                   |
      | articleCode  | 400000995                           |
      | articleMrp   | 1080                                |
    And Give comments for a task
      | requesterComments | Testing the priceboard task |
    And Give task type for a new task to be created
      | taskType | PRICE BOARD |
    When Requester call the Create Task endpoint to create task
    Then verify that status code be equal to "400"
  Examples:
    | requestedAt |
    |             |
    | 2024-03-13  |
    | null        |