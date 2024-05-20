Feature: Verify the functionality of Update Refill Task by Refiller

  Background: Generate access token from username and password
    Given Give Username and Password to get Access Token
      | username | 678787    |
      | password | Dmart@123 |

#  @UPTA
  Scenario: Update Priceboard Task by Auditor with valid data
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "VERIFIED"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task by auditor "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "200"
    And Check the response time for the Priceboard Task updated
    And Verify that priceboard task is updated successfully by Auditor

#  @UPTA
  Scenario: Update Priceboard Task partially by Auditor
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "VERIFIED"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | false    |
    And Give the comments for updating priceboard task by auditor "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "200"
    And Check the response time for the Priceboard Task updated
    And Verify that priceboard task is updated successfully by Auditor

#  @UPTA
  Scenario: Update Priceboard Task by Auditor without closing FloorWalk
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "VERIFIED"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task by auditor "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "200"
    And Check the response time for the Priceboard Task updated
    And Verify that priceboard task is updated successfully by Auditor

#  @UPTA
  Scenario: Update Priceboard Task by Auditor without refillID
    And Give the status to update the priceboard task "VERIFIED"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task by auditor "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTA
  Scenario: Update Priceboard Task by Auditor without status
    And Get the refillId to update priceboard task
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task by auditor "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTA
  Scenario: Update Priceboard Task by Auditor without priceBoards field
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "VERIFIED"
    And Give the comments for updating priceboard task by auditor "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTA
  Scenario: Update Priceboard Task by Auditor with priceBoards field as null
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "VERIFIED"
    And Provide the priceboards to be updated as null
    And Give the comments for updating priceboard task by auditor "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTA
  Scenario: Update Priceboard Task by Auditor without priceBoard and pbStatus field
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "VERIFIED"
    And Provide the priceboards to be updated without priceBoard and pbStatus field
    And Give the comments for updating priceboard task by auditor "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTA
  Scenario: Update Priceboard Task by Auditor with only priceBoard in priceBoards field
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "VERIFIED"
    And Provide the priceboards to be updated
      | priceBoard                        |
      | A4/1 - Full page price board      |
      | A4/9 - Cosmetic small price board |
    And Give the comments for updating priceboard task by auditor "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTA
  Scenario: Update Priceboard Task by Auditor with only pbStatus in priceBoards field
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "VERIFIED"
    And Provide the priceboards to be updated
      | pbStatus |
      | true     |
      | true     |
    And Give the comments for updating priceboard task by auditor "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTA
  Scenario Outline: Update Priceboard Task by Auditor with invalid refillId
    And Get the refillId to update priceboard task "<refillId>"
    And Give the status to update the priceboard task "VERIFIED"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task by auditor "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"
  Examples:
    | refillId                                            |
    |                                                     |
    | null                                                |
    | 888c2-685a-a5bf-4d4-654a-q67e-345d-769a-2dc38v022bn |

#  @UPTA
  Scenario Outline: Update Priceboard Task by Auditor with invalid status
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "<status>"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task by auditor "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"
  Examples:
    | status   |
    |          |
    | null     |
    | CLOSED   |
    | VERIFIED |
    | UPDATED  |

#  @UPTA
  Scenario: Update Priceboard Task by Auditor of other siteID
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "VERIFIED"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task by auditor "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "401"




