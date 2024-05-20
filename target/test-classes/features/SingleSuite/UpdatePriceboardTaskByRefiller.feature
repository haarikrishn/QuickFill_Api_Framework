Feature: Verify the functionality of Update Refill Task by Refiller

  Background: Generate access token from username and password
    Given Give Username and Password to get Access Token
      | username | 797978    |
      | password | Dmart@123 |

#  @UPTR
  Scenario: Update Priceboard Task by Refiller with valid data
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "COMPLETED"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "200"
    And Check the response time for the Priceboard Task updated
    And Verify that priceboard task is updated successfully

#  @UPTR
  Scenario: Update Priceboard Task partially by Refiller
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "COMPLETED"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | false    |
    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "200"
    And Check the response time for the Priceboard Task updated
    And Verify that priceboard task is updated successfully

#  @UPTR
  Scenario: Update Priceboard Task by Refiller without refillID
    And Give the status to update the priceboard task "COMPLETED"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTR
  Scenario: Update Priceboard Task by Refiller without status
    And Get the refillId to update priceboard task
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTR
  Scenario: Update Priceboard Task by Refiller without priceBoards field
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "COMPLETED"
    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTR
  Scenario: Update Priceboard Task by Refiller with priceBoards field as null
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "COMPLETED"
    And Provide the priceboards to be updated as null
    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTR
  Scenario: Update Priceboard Task by Refiller without priceBoard and pbStatus field
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "COMPLETED"
    And Provide the priceboards to be updated without priceBoard and pbStatus field
    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTR
  Scenario: Update Priceboard Task by Refiller with only priceBoard in priceBoards field
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "COMPLETED"
    And Provide the priceboards to be updated
      | priceBoard                        |
      | A4/1 - Full page price board      |
      | A4/9 - Cosmetic small price board |
    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTR
    Scenario: Update Priceboard Task by Refiller with only pbStatus in priceBoards field
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "COMPLETED"
    And Provide the priceboards to be updated
      | pbStatus |
      | true     |
      | true     |
    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTR
  Scenario: Re-Update COMPLETED Priceboard Task by Refiller with new values
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "COMPLETED"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | false    |
    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTR
  Scenario Outline: Update Priceboard Task by Refiller with invalid refillId
    And Get the refillId to update priceboard task "<refillId>"
    And Give the status to update the priceboard task "COMPLETED"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"
  Examples:
    | refillId                                            |
    |                                                     |
    | null                                                |
    | 888c2-685a-a5bf-4d4-654a-q67e-345d-769a-2dc38v022bn |

#  @UPTR
  Scenario Outline: Update Priceboard Task by Refiller with invalid status
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "<status>"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"
  Examples:
    | status   |
    |          |
    | null     |
    | CLOSED   |
    | VERIFIED |
    | UPDATED  |

#  @UPTR
  Scenario: Update Priceboard Task by Refiller of other siteID
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "COMPLETED"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "401"

#  @UPTR
  Scenario: Update Priceboard Task by Refiller without closing FloorWalk
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "COMPLETED"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "405"

#  @UPTR
  Scenario: Update Priceboard Task by Refill Requester
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "COMPLETED"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
    Then Verify that status code be equal to "200"

#  @UPTR
  Scenario: Update Priceboard Task by Refiller with Update Refill Task URI
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "COMPLETED"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
    When Requester calls the Update Refill Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTR
  Scenario: Update Priceboard Task by Refiller with Update OTHERS Task URI
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task "COMPLETED"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
    When Requester calls the Update Others Task's endpoint to update priceboard task
    Then Verify that status code be equal to "400"


