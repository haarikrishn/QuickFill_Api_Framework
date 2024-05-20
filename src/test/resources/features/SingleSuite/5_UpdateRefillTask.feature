Feature: Verify the functionality of Create Task Endpoint

  Background: access token id by
    Given Send Generic data using Feature File
      | username | 675432     |
      | password | Sweety123@ |

  @UpdateRefillTaskByRefiller
  Scenario: Verify the functionality of UpdateRefillTaskByRefiller
    When  user calls create UpdateRefillTask  endpoint
      | status          | COMPLETED             |
      | refiledQuantity | 100                   |
      | refillrComments | successfully refilled |
    Then verify that status code be equal to "200"
    And verify that requestPayload is same as responsePayload in UpdateRefillTask Task

  @UpdateRefillTaskByAuditor
  Scenario: Verify the functionality of UpdateRefillTaskByAuditor
    When  user calls create UpdateRefillTaskByAuditor  endpoint
      | status          | VERIFIED              |
      | refiledQuantity | 100                   |
      | AuditorComments | successfully VERIFIED |
    Then verify that status code be equal to "200"
    And verify that requestPayload is same as responsePayload in UpdateRefillTaskByAuditor

