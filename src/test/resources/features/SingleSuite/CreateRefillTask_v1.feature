Feature: Verify the functionality of Create Task by creating Refill Task from v1 endpoint

  Background: Generate access token from username and password
    Given Give Username and Password to get Access Token
      | username | 6789014     |
      | password | Dmart@12345 |

  @RefillTask @URTR @UF
  Scenario: Create Refill Task from v1 endpoint with valid data
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp           | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | mrp          | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "201"
    And Check the response time for the Refill Task created with v1 endpoint
    And Verify that Refill Task is created successfully with v1 endpoint

#  @RefillTask
  Scenario: Create Refill Task with mandatory fields only from v1 endpoint
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp           | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | mrp          | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "201"
    And Check the response time for the Refill Task created with v1 endpoint
    And Verify that Refill Task is created successfully with v1 endpoint

#  @RefillTask @URTR @UF
  Scenario: Create Duplicate Refill Task from v1 endpoint
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp           | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | mrp          | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "201"
    And Check the response time for the Refill Task created with v1 endpoint
    And Verify that Refill Task is created successfully with v1 endpoint

#  @RefillTask
  Scenario: Create Refill Task without articleDetails field from v1 endpoint
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Give task type for a new task to be created
      | taskType | REFILL |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"

#  @RefillTask
  Scenario: Create Refill Task with refilledQuantity field from v1 endpoint
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp           | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | mrp          | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | refilledQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"

##  @RefillTask
#  Scenario: Create Task without uom and caselot in articleDetails field
#    And Get the unique requestId to create Refill Task
#    And Get the requestAt time to created Refill Task
#    And Provide article details to create Refill task
#      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
#      | articlePrice | 820                               |
#      | ean          | 8901207019999                     |
#      | categoryCode | 4                                 |
#      | articleCode  | 400020722                         |
#      | articleMrp   | 800                               |
#    And Provide the quantity for a Refill task
#      | requestedQuantity | 15 |
#    And Give requester comments for a Refill task
#      | requesterComments | Testing the refill task |
#    And Give task type for a new task to be created
#      | taskType | REFILL |
#    When Requester call the Create Task endpoint to create Refill task
#    Then verify that status code be equal to "400"

#  @RefillTask
  Scenario: Create Refill Task without requestedQuantity from v1 endpoint
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp           | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | mrp          | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"

#  @RefillTask
  Scenario:Create Task without taskType field from v1 endpoint
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp           | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | mrp          | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"

#  @RefillTask
  Scenario Outline: Create Task with invalid ean number from v1 endpoint
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Give the invalid ean numbers to verify the functionality of Create Task from v1 endpoint "<eanNumbers>"
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp           | 820                               |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | mrp          | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"
  Examples:
    | eanNumbers            |
    | 89012                 |
    | 890120701999965437853 |
    |                       |
    | null                  |

#  @RefillTask
  Scenario Outline: Create Task with invalid categoryCode from v1 endpoint
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Give the invalid categoryCode to verify the functionality of Create Task from v1 endpoint "<categoryCode>"
    And Provide article details to create Refill task from v1 endpoint
      | articleName | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp          | 820                               |
      | ean         | 8901207019999                     |
      | articleCode | 400020722                         |
      | mrp         | 800                               |
      | uom         | BOXES                             |
      | caselot     | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"
    Examples:
      | categoryCode          |
      | 89012                 |
      | 890120701999965437853 |
      |                       |
      | null                  |

#  @RefillTask
  Scenario Outline: Create Task with invalid articleCode from v1 endpoint
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Give the invalid articleCode to verify the functionality of Create Task from v1 endpoint "<articleCode>"
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp           | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | mrp          | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"
  Examples:
    | articleCode           |
    | 444444444567898765412 |
    |                       |
    | null                  |

#  @RefillTask
  Scenario Outline: Create Refill Task with invalid articleName from v1 endpoint
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Give the invalid articleName to verify the functionality of Create Task from v1 endpoint "<articleName>"
    And Provide article details to create Refill task from v1 endpoint
      | sp           | 820           |
      | ean          | 8901207019999 |
      | categoryCode | 4             |
      | articleCode  | 400020722     |
      | mrp          | 800           |
      | uom          | BOXES         |
      | caselot      | 10            |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"
  Examples:
    | articleName |
    | D           |
    |             |
    | null        |

#  @RefillTask
  Scenario Outline: Create Refill Task with invalid articlePrice from v1 endpoint
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Give the invalid articlePrice to verify the functionality of Create Task from v1 endpoint "<articlePrice>"
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | mrp          | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"
  Examples:
    | articlePrice |
    |              |
    | null         |

#  @RefillTask
  Scenario Outline: Create Refill Task with invalid articleMrp from v1 endpoint
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Give the invalid articleMrp to verify the functionality of Create Task from v1 endpoint "<articleMrp>"
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp           | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"
  Examples:
    | articleMrp |
    |            |
    | null       |

#  @RefillTask
  Scenario Outline: Create Refill Task with invalid taskType from 1 endpoint
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp           | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | mrp          | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give the invalid task type for a new task to be created from v1 endpoint "<taskType>"
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"
  Examples:
    | taskType                                            |
    | REFILLQWERTYUIOPASDFGHJKLZXCVBNMQSDCVPOJBZXFGHJOPMB |
    |                                                     |
    | null                                                |
    | @#$%/                                               |

#  @RefillTask
  Scenario Outline: Create Task with invalid requestId from 1 endpoint
    And Get the invalid requestId to verify the functionality of Create Task from v1 endpoint "<requestId>"
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp           | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | mrp          | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"
  Examples:
    | requestId                                           |
    |                                                     |
    | 888c2-685a-a5bf-4d4-654a-q67e-345d-769a-2dc38v022b3 |
    | 888c2-685                                           |
    | null                                                |

#  @RefillTask
  Scenario Outline: Create Task with invalid requestedAt field from 1 endpoint
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the inavlid requestAt time to verify the functionality of Create Task from v1 endpoint "<requestedAt>"
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp           | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | mrp          | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"
  Examples:
    | requestedAt |
    |             |
    | 2024-03-13  |
    | null        |

#  @RefillTask
  Scenario: Create Task from 1 endpoint without requestId
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp           | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | mrp          | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"

#  @RefillTask
  Scenario: Create Task from v1 endpoint without requestedAt field
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp           | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | mrp          | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"

#  @RefillTask
  Scenario: Create Task from v1 endpoint without articleDetails field
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"

#  @RefillTask
  Scenario: Create Task from v1 endpoint without articlePrice in articleDetails field
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | mrp          | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"

#  @RefillTask
  Scenario: Create Task from v1 endpoint without articleName in articleDetails field
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Provide article details to create Refill task from v1 endpoint
      | sp           | 820           |
      | ean          | 8901207019999 |
      | categoryCode | 4             |
      | articleCode  | 400020722     |
      | mrp          | 800           |
      | uom          | BOXES         |
      | caselot      | 10            |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"

#  @RefillTask
  Scenario: Create Task from v1 endpoint without ean in articleDetails field
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp           | 820                               |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | mrp          | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"

#  @RefillTask
  Scenario: Create Task from v1 endpoint without categoryCode in articleDetails field
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Provide article details to create Refill task from v1 endpoint
      | articleName | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp          | 820                               |
      | ean         | 8901207019999                     |
      | articleCode | 400020722                         |
      | mrp         | 800                               |
      | uom         | BOXES                             |
      | caselot     | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"

#  @RefillTask
  Scenario: Create Task from v1 endpoint without articleCode in articleDetails field
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp           | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | mrp          | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"

#  @RefillTask
  Scenario: Create Task from v1 endpoint without articleMrp in articleDetails field
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp           | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    And Give task type for a new task to be created
      | taskType | REFILL |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"

#  @RefillTask
  Scenario: Create Task from v1 endpoint without taskType field
    And Get the unique requestId to create Refill Task from v1 endpoint
    And Get the requestAt time to created Refill Task from v1 endpoint
    And Provide article details to create Refill task from v1 endpoint
      | articleName  | Dabur Lemon Glucoplus-C Jar(400g) |
      | sp           | 820                               |
      | ean          | 8901207019999                     |
      | categoryCode | 4                                 |
      | articleCode  | 400020722                         |
      | mrp          | 800                               |
      | uom          | BOXES                             |
      | caselot      | 10                                |
    And Provide the quantity for a Refill task to be created from v1 endpoint
      | requestedQuantity | 15 |
    And Give requester comments for a Refill task to be created from v1 endpoint
      | requesterComments | Testing the refill task |
    When Requester call the Create Task v1 endpoint to create Refill task
    Then verify that status code be equal to "400"