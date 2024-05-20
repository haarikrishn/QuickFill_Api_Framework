Feature: Verify the functionality of Create Task Endpoint

  Background: access token id by
    Given Send Generic data using Feature File
      | username | 1231456   |
      | password | Dmart@123 |

  @CreatePriceBoardTaskDT
  Scenario: Verify the functionality of PriceBoardTask
    When  user calls create priceBoard taskDT endpoint
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | false    |
      | A4/2 - Half page price board      | false    |
      | A4/4 - Medium price board         | false    |
      | A4/9 - Cosmetic small price board | false    |
    And pass article details to createPriceboardtaskDT End point
      | articleName       | Dabur Gulabari Pr Rose Watr (250ml) |
      | articlePrice      | 1099                                |
      | ean               | 8901207040801                       |
      | categoryCode      | 4                                   |
      | articleCode       | 400020722                           |
      | articleMrp        | 1080                                |
      | requesterComments | creating PRICEBOARD task            |
      | taskType          | PRICEBOARD                          |
    Then verify that status code be equal to "201"
    And verify that requestPayload is same as responsePayload in Create PRICEBOARD Task
    And verify that schema should be equal "CreatePriceBoardTaskSchema.json" for Create Task

#    ============================================================================================
#  @NegativeScenarios
#  Scenario Outline: Verify create Priceboard task with invalid pbStatus
#    When User calls the Create Task endpoint to create new Tasks "<task>"   "<articleName>"	"<articlePrice>"  "<ean>" 	"<categoryCode>"	"<articleCode>"	  "<articleMrp>"	"<uom>"	  "<requestedQuantity>"	  "<refilledQuantity>"	 "<commentsBy>"   "<comment>"	"<priceBoards1>"	"<pbStatus1>"	"<priceBoards2>"	"<pbStatus2>"	"<priceBoards3>"	"<pbStatus3>"	"<priceBoards4>"	"<pbStatus4>"	"<priceBoards5>"	"<pbStatus5>"	"<priceBoards6>"	"<pbStatus6>"	"<priceBoards7>"	"<pbStatus7>"	"<priceBoards8>"	"<pbStatus8>".
#    Then verify that status code be equal to "400"
#    Examples:
#      | task        | articleName                | articlePrice | ean           | categoryCode | articleCode | articleMrp | uom   | requestedQuantity | refilledQuantity | commentsBy       | comment | priceBoards1                      | pbStatus1 | priceBoards2 | pbStatus2 | priceBoards3 | pbStatus3 | priceBoards4 | pbStatus4 | priceBoards5 | pbStatus5 | priceBoards6 | pbStatus6 | priceBoards7 | pbStatus7 | priceBoards8 | pbStatus8 |
#      | PRICE BOARD | Real Masala Guava Juice-1l | 235          | 8901888008022 | 4            | 400026970   | 230        | BOXES | 0                 | 0                | REFILL REQUESTER |         | A4/1 - Full page price board      | as df     |              |           |              |           |              |           |              |           |              |           |              |           |              |           |
#
#  @createPriceboardTaskNegativeScenarios
#  Scenario Outline:  verify create Priceboard task with invalid details
#    When  user calls the  create Priceboard task "<articleName>" "<articlePrice>" "<ean>" "<categoryCode>" "<articleCode>" "<articleMrp>" "<uom>" "<caselot>" "<>" "<>" "<>" "<>" "<>" "<>" "<>" "<>" "<>" "<>" "<>" "<>" "<>" "<>"
