Feature: Verify the functionality of Update Refill Task by Refiller

  Background: Generate access token from username and password
    Given Give Username and Password to get Access Token
      | username | 678787    |
      | password | Dmart@123 |

  @Excel
  Scenario: Update all the Priceboard Task by Auditor from v1 endpoint
    And Give the Excel file to get the request payload for updating Priceboad task "QuickFill_TestData.xlsx"
    And Give the sheet name "UpdatePriceboardTasksByAuditor" to get all the Priceboard Task to be updated
    When Requester calls the Update Priceboard Task v1 endpoint to update list of all priceboard task
    Then Verify that status code be equal to "200"
    And Check the response time for the Priceboard Task updated from v1 endpoint
    And Verify that priceboard task is updated successfully from v1 endpoint

  @UPTR
  Scenario: Update Priceboard Task by Refill Auditor with valid data from v1 endpoint
    And Get the refillId to update priceboard task from v1 endpoint
    And Give the status to update the priceboard task from v1 endpoint "VERIFIED"
    And Provide the priceboards to be updated from v1 endpoint
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task by auditor from v1 endpoint "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task v1 endpoint to update priceboard task
    Then Verify that status code be equal to "200"
    And Check the response time for the Priceboard Task updated from v1 endpoint
    And Verify that priceboard task is updated successfully by Auditor from v1 endpoint

#  @UPTR
  Scenario: Update Priceboard Task partially by Refill Auditor from v1 endpoint
    And Get the refillId to update priceboard task from v1 endpoint
    And Give the status to update the priceboard task from v1 endpoint "VERIFIED"
    And Provide the priceboards to be updated from v1 endpoint
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | false    |
    And Give the comments for updating priceboard task by auditor from v1 endpoint "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task v1 endpoint to update priceboard task
    Then Verify that status code be equal to "200"
    And Check the response time for the Priceboard Task updated from v1 endpoint
    And Verify that priceboard task is updated successfully by Auditor from v1 endpoint

#  @UPTR
  Scenario: Update Priceboard Task by Refill Auditor without refillID from v1 endpoint
    And Give the status to update the priceboard task from v1 endpoint "VERIFIED"
    And Provide the priceboards to be updated from v1 endpoint
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task by auditor from v1 endpoint "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task v1 endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTR
  Scenario: Update Priceboard Task by Refill Auditor without status from v1 endpoint
    And Get the refillId to update priceboard task from v1 endpoint
    And Provide the priceboards to be updated from v1 endpoint
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task by auditor from v1 endpoint "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task v1 endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTR
  Scenario: Update Priceboard Task by Refill Auditor without priceBoards field from v1 endpoint
    And Get the refillId to update priceboard task from v1 endpoint
    And Give the status to update the priceboard task from v1 endpoint "VERIFIED"
    And Give the comments for updating priceboard task by auditor from v1 endpoint "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task v1 endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTR
  Scenario: Update Priceboard Task by Refill Auditor with priceBoards field as null from v1 endpoint
    And Get the refillId to update priceboard task from v1 endpoint
    And Give the status to update the priceboard task from v1 endpoint "VERIFIED"
    And Provide the priceboards to be updated from v1 endpoint as null
    And Give the comments for updating priceboard task by auditor from v1 endpoint "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task v1 endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTR
  Scenario: Update Priceboard Task by Refill Auditor without priceBoard and pbStatus field from v1 endpoint
    And Get the refillId to update priceboard task from v1 endpoint
    And Give the status to update the priceboard task from v1 endpoint "VERIFIED"
    And Provide the priceboards to be updated without priceBoard and pbStatus field from v1 endpoint
    And Give the comments for updating priceboard task by auditor from v1 endpoint "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task v1 endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTR
  Scenario: Update Priceboard Task by Refill Auditor with only priceBoard in priceBoards field from v1 endpoint
    And Get the refillId to update priceboard task from v1 endpoint
    And Give the status to update the priceboard task from v1 endpoint "VERIFIED"
    And Provide the priceboards to be updated from v1 endpoint
      | priceBoard                        |
      | A4/1 - Full page price board      |
      | A4/9 - Cosmetic small price board |
    And Give the comments for updating priceboard task by auditor from v1 endpoint "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task v1 endpoint to update priceboard task
    Then Verify that status code be equal to "400"

#  @UPTR
  Scenario: Update Priceboard Task by Refill Auditor with only pbStatus in priceBoards field from v1 endpoint
    And Get the refillId to update priceboard task from v1 endpoint
    And Give the status to update the priceboard task from v1 endpoint "VERIFIED"
    And Provide the priceboards to be updated from v1 endpoint
      | pbStatus |
      | true     |
      | true     |
    And Give the comments for updating priceboard task by auditor from v1 endpoint "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task v1 endpoint to update priceboard task
    Then Verify that status code be equal to "400"

##  @UPTR
#  Scenario: Re-Update COMPLETED Priceboard Task by Refiller with new values
#    And Get the refillId to update priceboard task
#    And Give the status to update the priceboard task "COMPLETED"
#    And Provide the priceboards to be updated
#      | priceBoard                        | pbStatus |
#      | A4/1 - Full page price board      | true     |
#      | A4/9 - Cosmetic small price board | false    |
#    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
#    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
#    Then Verify that status code be equal to "400"

#  @UPTR
  Scenario Outline: Update Priceboard Task by Refill Auditor with invalid refillId from v1 endpoint
    And Get the refillId to update priceboard task "<refillId>" from v1 endpoint
    And Give the status to update the priceboard task from v1 endpoint "VERIFIED"
    And Provide the priceboards to be updated
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task by auditor from v1 endpoint "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task v1 endpoint to update priceboard task
    Then Verify that status code be equal to "400"
  Examples:
    | refillId                                            |
    |                                                     |
    | null                                                |
    | 888c2-685a-a5bf-4d4-654a-q67e-345d-769a-2dc38v022bn |

#  @UPTR
  Scenario Outline: Update Priceboard Task by Refill Auditor with invalid status from v1 endpoint
    And Get the refillId to update priceboard task
    And Give the status to update the priceboard task from v1 endpoint "<status>"
    And Provide the priceboards to be updated from v1 endpoint
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task by auditor from v1 endpoint "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task v1 endpoint to update priceboard task
    Then Verify that status code be equal to "400"
  Examples:
    | status    |
    |           |
    | null      |
    | CLOSED    |
    | COMPLETED |
    | UPDATED   |

#  @UPTR
  Scenario: Update Priceboard Task by Refill Auditor of other siteID from v1 endpoint
    And Get the refillId to update priceboard task from v1 endpoint
    And Give the status to update the priceboard task from v1 endpoint "VERIFIED"
    And Provide the priceboards to be updated from v1 endpoint
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task by auditor from v1 endpoint "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task v1 endpoint to update priceboard task
    Then Verify that status code be equal to "401"

##  @UPTR
#  Scenario: Update Priceboard Task by Refiller without closing FloorWalk
#    And Get the refillId to update priceboard task
#    And Give the status to update the priceboard task "COMPLETED"
#    And Provide the priceboards to be updated
#      | priceBoard                        | pbStatus |
#      | A4/1 - Full page price board      | true     |
#      | A4/9 - Cosmetic small price board | true     |
#    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
#    When Requester calls the Update Priceboard Task's endpoint to update priceboard task
#    Then Verify that status code be equal to "405"

#  @UPTR
  Scenario: Update Priceboard Task by Refill Auditor Requester from v1 endpoint
    And Get the refillId to update priceboard task from v1 endpoint
    And Give the status to update the priceboard task from v1 endpoint "VERIFIED"
    And Provide the priceboards to be updated from v1 endpoint
      | priceBoard                        | pbStatus |
      | A4/1 - Full page price board      | true     |
      | A4/9 - Cosmetic small price board | true     |
    And Give the comments for updating priceboard task by auditor from v1 endpoint "checking the update priceboard task functionality"
    When Requester calls the Update Priceboard Task v1 endpoint to update priceboard task
    Then Verify that status code be equal to "200"

#  @UPTR
#  Scenario: Update Priceboard Task by Refiller with Update Refill Task URI
#    And Get the refillId to update priceboard task
#    And Give the status to update the priceboard task "COMPLETED"
#    And Provide the priceboards to be updated
#      | priceBoard                        | pbStatus |
#      | A4/1 - Full page price board      | true     |
#      | A4/9 - Cosmetic small price board | true     |
#    And Give the comments for updating priceboard task by auditor from v1 endpoint "checking the update priceboard task functionality"
#    When Requester calls the Update Refill Task's endpoint to update priceboard task
#    Then Verify that status code be equal to "400"

#  @UPTR
#  Scenario: Update Priceboard Task by Refiller with Update OTHERS Task URI
#    And Get the refillId to update priceboard task
#    And Give the status to update the priceboard task "COMPLETED"
#    And Provide the priceboards to be updated
#      | priceBoard                        | pbStatus |
#      | A4/1 - Full page price board      | true     |
#      | A4/9 - Cosmetic small price board | true     |
#    And Give the comments for updating priceboard task "checking the update priceboard task functionality"
#    When Requester calls the Update Others Task's endpoint to update priceboard task
#    Then Verify that status code be equal to "400"


