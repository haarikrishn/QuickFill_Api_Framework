Feature: Verify the functionality of Create Task by creating 1000 tasks

  Background: Generate access token from username and password
    Given Send Generic data using Feature File
      | username | 6789014     |
      | password | Dmart@12345 |

  @BulkTasks
  Scenario: Create 1000 Tasks
    And Get all task from Excel file
      | fileName  | QuickFill_TestData.xlsx |
      | sheetName | TaskList                |
    When User calls create task endpoint to create one thousand tasks
#    Then verify that status code be equal to "201"
#    And verify that requestPayload is same as responsePayload
#    And verify that schema should be equal "CreatePriceBoardTaskSchema.json" for Create Task