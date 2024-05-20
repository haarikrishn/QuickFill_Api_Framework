Feature: Verify the functionality of Create Task Endpoint

  Background: access token id by
    Given Send Generic data using Feature File
      | username | 675432     |
      | password | Sweety123@ |

  @CreateOthersTask
  Scenario: Verify the functionality of Create OthersTask
    When  user calls OthersTask endpoint
      | articleName       | Dabur Gulabari Pr Rose Watr (250ml) |
      | articlePrice      | 1099                                |
      | ean               | 8901207040801                       |
      | categoryCode      | 4                                   |
      | articleCode       | 400020722                           |
      | articleMrp        | 1080                                |
      | requesterComments | creating others task                |
      | taskType          | OTHERS                              |
    Then verify that status code be equal to "201"
    And verify that requestPayload is same as responsePayload in OthersTask
    And verify that schema should be equal "othersTaskSchema.json" for Create Task