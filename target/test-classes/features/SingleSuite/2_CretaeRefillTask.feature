#09/01/2024
Feature: Verify the functionality of Create Task Endpoint

  Background: access token id by
    Given Send Generic data using Feature File
      | username | 675432     |
      | password | Sweety123@ |

  @CreateRefillTask
  Scenario: Verify the functionality of Create RefillTask
    When  user calls create task endpoint
               | articleName       | Dabur Gulabari Pr Rose Watr (250ml) |
               | articlePrice      | 1099                                |
               | ean               | 8901207040801                       |
               | categoryCode      | 4                                   |
               | articleCode       | 400020722                           |
               | articleMrp        | 1080                                |
               | uom               | BOXES                               |
               | caselot           | 10                                  |
               | requestedQuantity | 15                                  |
               | requesterComments | creating refill task                |
               | taskType          | REFILL                              |
    Then verify that status code be equal to "201"
    And verify that requestPayload is same as responsePayload in Create Task
    And verify that schema should be equal "CreateRefillTask.json" for Create Task

