Feature: Verify the functionality of Create Task Endpoint

  Background: access token id by
    Given Send Generic data using Feature File
      | username | 675432     |
      | password | Sweety123@ |

  @Updatefloorwalk
  Scenario: Verify the functionality of Updatefloorwalk
    When  user calls create Updatefloorwalk endpoint
      | status | CLOSED |
    Then verify that status code be equal to "200"
    And verify that requestPayload is same as responsePayload in Updatefloorwalk

