Feature: Verify the functionality of Create Task with v3 endpoint

  Background: Generate access token from username and password
    Given Give Username and Password to get Access Token
      | username | 6789014     |
      | password | Dmart@12345 |

  @Excel
  Scenario: Create multiple tasks with valid data
    And Give the Excel file to get the request payload "QuickFill_TestData.xlsx"
    And Give the sheet name "TaskList" and the number of tasks to be created 3
    When Requester call the Create Task v1 endpoint to create new tasks
    Then verify that status code be equal to "201"
    And Check the response time for the task created
    And Verify that Task is created successfully with v1 endpoint