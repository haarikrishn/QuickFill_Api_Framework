
Feature: Verify the functionality of Create Task Endpoint

  Background: access token id by
    Given Send Generic data using Feature File
      | username | 675432     |
      | password | Sweety123@ |
@UpdatePriceBoardTaskDTByRefiller
Scenario: Verify the functionality of UpdatePriceBoardTaskDTByRefiller
When  user calls create UpdatePriceBoardTaskDT  endpoint
  | priceBoard                        | pbStatus |
  | A4/1 - Full page price board      | true     |
  | A4/2 - Half page price board      | true     |
  | A4/4 - Medium price board         | true     |
  | A4/9 - Cosmetic small price board | true     |
  And user pass the data to  create UpdatePriceBoardTask  endpoint
    | status           | COMPLETED          |
    | refillerComments | priceTaskCompleted |
  Then verify that status code be equal to "200"
And verify that requestPayload is same as responsePayload in UpdatePriceBoardTask

  @UpdatePriceBoardTaskDTByAuditor
  Scenario: Verify the functionality of UpdatePriceBoardTaskDTByAuditor
    When  user calls create UpdatePriceBoardTaskDTByAuditor  endpoint
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/2 - Half page price board      | true     |
      | A4/4 - Medium price board         | true     |
      | A4/9 - Cosmetic small price board | true     |
    And user pass the data to  UpdatePriceBoardTaskDTByAuditor endpoint
      | status          | VERIFIED          |
      | auditorComments | priceTaskVERIFIED |
    Then verify that status code be equal to "200"
    And verify that requestPayload is same as responsePayload in UpdatePriceBoardTask