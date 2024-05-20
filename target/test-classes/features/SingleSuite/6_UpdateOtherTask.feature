Feature: Verify the functionality of Create Task Endpoint

  Background: access token id by
    Given Send Generic data using Feature File
      | username | 675432     |
      | password | Sweety123@ |
  @UpdateOthersTaskByRefiller
  Scenario: Verify the functionality of UpdateOthersTaskByRefiller
    When  user calls create UpdateOthersTask  endpoint
      | status          | COMPLETED                     |
      | refillrComments | task is completed by refiller |
    Then verify that status code be equal to "200"
    And verify that requestPayload is same as responsePayload in UpdateOthersTask

  @UpdateOthersTaskByAuditor
  Scenario: Verify the functionality of UpdateOthersTaskByAuditor
    When  user calls create UpdateOthersTaskByRefiller  endpoint
      | status          | VERIFIED                    |
      | auditorComments | task is verified by auditor |
    Then verify that status code be equal to "200"
    And verify that requestPayload is same as responsePayload in UpdateOthersTaskByAuditor